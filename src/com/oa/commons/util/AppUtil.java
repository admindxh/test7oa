/**  
 * @Project: jxoa
 * @Title: AppUtil.java
 * @Package com.oa.commons.util
 * @date 2013-6-26 下午4:03:54
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.util.Date;

/**
 * 
 * 类名：AppUtil
 * 功能：应用工具类
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-26 下午4:03:54
 *
 */
public class AppUtil {

	
	/**
	 * 封装打印方法，不换行
	 * @param key
	 * @param value
	 */
	public static void print(String value){
		System.out.print(value);
	}
	/**
	 * 封装打印方法，换行
	 * @param key
	 * @param value
	 */
	public static void println(String value){
		System.out.println(value);
	}
	
	/**
	 * 封装打印方法，不换行 name=====value
	 * @param key
	 * @param value
	 */
	public static void print(String name,String value){
		System.out.print(name+"========"+value);
	}
	/**
	 * 封装打印方法，换行	name=====value
	 * @param key
	 * @param value
	 */
	public static void println(String name,String value){
		System.out.println(name+"========"+value);
	}
	
	
	
	
	/**
	 * 验证是否在可以登录的范围
	 * @param nowTime
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean checkLoginTime(Date nowTime,String start,String end){
		
		String nd=DateUtil.date2String(nowTime, "yyyy-MM-dd");
		long startTime=DateUtil.string2Timestamp(nd+" "+start).getTime();
		long endTime=DateUtil.string2Timestamp(nd+" "+end).getTime();
		if(nowTime.getTime()>=startTime&&nowTime.getTime()<=endTime){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 验证ip是否匹配
	 * @param ip1	规则
	 * @param ip2	需要匹配的ip
	 * @return		匹配成功，返回true
	 */
	public static boolean checkIp(String ipgzs,String loginIp){
		
		String[] z_ipgz=ipgzs.split(",");//192.168.1.*,202.202.123.4
		
		String[] z_loginIp=loginIp.split("\\.");//当前登陆ip 192.168.1.1
		
		for(int i=0;i<z_ipgz.length;i++){
			
			String gz=z_ipgz[i];
			
			if(gz.contains("*")){
				//如果含有匹配符*
				String[] ipgz=gz.split("\\.");
				for(int k=0;k<ipgz.length;k++){
					
					String str_ip=ipgz[k];
					
					if(!"*".equals(str_ip)&&!str_ip.equals(z_loginIp[k])){
						return false;
					}
				}
			}else{
				if(!gz.equals(loginIp)){
					return false;
				}
			}
			
		}
		return true;
	}
	
}
