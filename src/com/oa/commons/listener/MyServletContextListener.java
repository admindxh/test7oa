/**  
 * @Project: jxoa
 * @Title: MyServletContextListener.java
 * @Package com.oa.commons.listener
 * @date 2013-3-28 下午4:49:24
 * @Copyright: 2013 
 */
package com.oa.commons.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.oa.commons.model.OnLineUser;

/**
 * 
 * 类名：MyServletContextListener
 * 功能：servlet监听器
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-3-28 下午4:49:24
 *
 */
public class MyServletContextListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("********服务器关闭******");
	}
	
	//该方法在ServletContext启动之后被调用，并准备好处理客户端请求  
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		System.out.println("********服务器开启******");
		
		ServletContext servletContext= event.getServletContext();
		
		
		//保存在线用户的Map
		Map<String,OnLineUser> usersMap=new HashMap<String,OnLineUser>();
		servletContext.setAttribute("onLineUsers", usersMap);//放入全局保存
		
		
		
	}
	
}
