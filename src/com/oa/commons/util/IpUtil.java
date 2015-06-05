/**  
 * @Project: jxoa
 * @Title: IpUtil.java
 * @Package com.oa.commons.util
 * @date 2013-4-11 下午1:47:49
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oa.commons.model.IpInfo;

/**
 * 
 * 类名：IpUtil
 * 功能：IP地址信息查询
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-11 下午1:47:49
 *
 */
public class IpUtil {
	
	public static void main(String[] args){
		getIpInfo("221.0.62.238");
	}
	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	/**
	 * 根据ip获取ip 归属地    调用淘宝ip 接口   http://ip.taobao.com/
	 *
	 * 	1. 请求接口（GET）：	
	 * 	http://ip.taobao.com/service/getIpInfo.php?ip=[ip地址字串]
	 * 	
	 * 	2. 响应信息：（json格式的）国家 、省（自治区或直辖市）、市（县）、运营商
	 * 	
	 * 	3. 返回数据格式：
	 * 	
	 * 	{"code":0,"data":{"ip":"210.75.225.254","country":"\u4e2d\u56fd","area":"\u534e\u5317",
		"region":"\u5317\u4eac\u5e02","city":"\u5317\u4eac\u5e02","county":"","isp":"\u7535\u4fe1",
	 * 	"country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000",
	 * 	"county_id":"-1","isp_id":"100017"}}
	 * 	其中code的值的含义为，0：成功，1：失败。
     *
	 * @param ip
	 * @return IpInfo ip信息对象
	 */
	public static  IpInfo getIpInfo(String ip) {
		
		
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		//创建GET方法的实例
		GetMethod getMethod = new GetMethod("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		//使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		IpInfo info=null;
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			
			if (statusCode == HttpStatus.SC_OK) {
				
				//读取内容 返回信息为JSON格式
				String  responseBody=getMethod.getResponseBodyAsString();
				
				System.out.println("ip信息===="+responseBody);
				
				JSONObject jo=(JSONObject)JSON.parse(responseBody);
				//处理内容
				if("0".equals(jo.getString("code"))){
					
					//JSONObject dataJson=jo.getJSONObject("data");
					
					/*System.out.println("country=="+dataJson.get("country"));//国家
					System.out.println("country_id=="+dataJson.get("country_id"));//国家编码 CN 中国
					System.out.println("area=="+dataJson.get("area"));//地区   华东
					System.out.println("area_id=="+dataJson.get("area_id"));//地区编码
					System.out.println("region="+dataJson.get("region"));//省份
					System.out.println("region_id=="+dataJson.get("region_id"));//省份编码
					System.out.println("city=="+dataJson.get("city"));//城市
					System.out.println("city_id=="+dataJson.get("city_id"));//城市编码
					System.out.println("county=="+dataJson.get("county"));//县城
					System.out.println("county_id=="+dataJson.get("county_id"));//县城id
					System.out.println("isp=="+dataJson.get("isp"));//运营商
					System.out.println("isp_id==="+dataJson.get("isp_id"));//运营商编码
					System.out.println("ip==="+dataJson.get("ip"));
					*/
					info=JSON.parseObject(jo.getString("data"), IpInfo.class);
					
					//System.out.println("*****===="+info.getCountry());
					
					return info;
				}
				
			}
			
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			//System.out.println("ip地址查询的HTTP协议有问题，请求失败");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			//System.out.println("网络异常");
			e.printStackTrace();
		} finally {
			//释放连接
			getMethod.releaseConnection();
			if(info==null){
				info=new IpInfo();
				info.setCountry("获取IP来源失败");
				info.setCity("");
				info.setIsp("");
				info.setRegion("");
				info.setIsp("");
				info.setIp(ip);
			}
		}
		return info;
	}
	
	
	
	
}
