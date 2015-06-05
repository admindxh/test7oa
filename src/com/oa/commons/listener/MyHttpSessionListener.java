/**  
 * @Project: jxoa
 * @Title: MyHttpSessionListener.java
 * @Package com.oa.commons.listener
 * @date 2013-4-11 上午8:46:26
 * @Copyright: 2013 
 */
package com.oa.commons.listener;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oa.commons.cache.MyCache;
import com.oa.commons.model.IpInfo;
import com.oa.commons.model.LoginInfo;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.util.DateUtil;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.service.ILoginService;

/**
 * 
 * 类名：MyHttpSessionListener
 * 功能：session监听器
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-11 上午8:46:26
 *
 */
public class MyHttpSessionListener implements HttpSessionListener{
	
	/**
	 * session创建
	 */
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		HttpSession session=e.getSession();
		System.out.println("session创建===ID===="+session.getId());
		
		//获取全局在线用户
		Map<String,OnLineUser> usersMap =(Map<String,OnLineUser>)session.getServletContext().getAttribute("onLineUsers");
		
		System.out.println("当前用户在线数量:"+usersMap.size());
	}
	/**
	 * session销毁
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		
		HttpSession session=e.getSession();
		System.out.println("销毁的sessionID===="+session.getId());
		//获取当前用户
		Member me=(Member)session.getAttribute("minfo");
		//获取全局在线用户
		Map<String,OnLineUser> usersMap =(Map<String,OnLineUser>)session.getServletContext().getAttribute("onLineUsers");
		System.out.println("当前用户在线数量:"+usersMap.size());
		if(me!=null){
			OnLineUser ol=usersMap.get(me.getId());
			if(ol!=null){
				Map<String,LoginInfo> loginInfos=ol.getLoginInfos();
				if(loginInfos.containsKey(session.getId())){
					loginInfos.remove(session.getId());//当前销毁的session如果存在于在线集合，且用户登录记录有此session，就删除登录信息
				}
				if(loginInfos.isEmpty()){
					usersMap.remove(me.getId());//当用户没有任何登录记录时从在线用户集合中删除
				}
				//保存退出日志
				ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
				
				ILoginService service=ac.getBean(ILoginService.class);

				IpInfo ipInfo= me.getIpInfo();
				//登录日志
				SyLoginLog log=new SyLoginLog();
				log.setUserId(me.getId());
				log.setLoginType(((Integer)session.getAttribute("loginType")).shortValue());
				if(session.getAttribute("isOut")!=null){
					
					log.setLoginDesc("正常退出("+DateUtil.timestamp2String(DateUtil.currentTimestamp(), "MM-dd HH:mm:ss")+")");
					
				}else{
					
					log.setLoginDesc("非正常退出("+DateUtil.timestamp2String(DateUtil.currentTimestamp(), "MM-dd HH:mm:ss")+")");
					
				}
				log.setIpInfoCountry(ipInfo.getCountry());
				log.setIpInfoRegion(ipInfo.getRegion());
				log.setIpInfoCity(ipInfo.getCity());
				log.setIpInfoIsp(ipInfo.getIsp());
				log.setLoginIp(ipInfo.getIp());
				log.setLoginTime(me.getLoginTime());
				
				service.save(log);//保存登录日志
				
			}
			System.out.println("用户:"+MyCache.getInstance().getTrueName(me.getId())+"下线之后，用户在线数量:"+usersMap.size());
			Timestamp loginTime=me.getLoginTime();
			long nowTime=System.currentTimeMillis()-loginTime.getTime();
			System.out.println("登录时间:"+loginTime+"在线时长:"+((double)nowTime/1000/60)+"分钟");
			
		}else{
			System.out.println("session中无用户");
		}

		
	}

}
