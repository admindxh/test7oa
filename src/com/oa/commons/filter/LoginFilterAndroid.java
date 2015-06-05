/**  
 * @Project: jxoa
 * @Title: LoginFilterAndroid.java
 * @Package com.oa.commons.filter
 * @date 2013-7-4 上午9:34:20
 * @Copyright: 2013 
 */
package com.oa.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 类名：LoginFilterAndroid
 * 功能: android应用拦截
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-4 上午9:34:20
 */
public class LoginFilterAndroid implements Filter{
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String url=request.getRequestURI();//请求URL 包含工程名
		
		String accept=request.getHeader("Accept");
		System.out.println("android 请求："+url+"   ====accept:"+accept);
		
		chain.doFilter(request, response);
		return;
		
		/*HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		String path = request.getContextPath();//当前工程名
		String url=request.getRequestURI();//请求URL 包含工程名
		url=url.substring(path.length()+1);
		
		String accept=request.getHeader("Accept");
		System.out.println("请求："+url+"   ====accept:"+accept);
		
		//2.排序不需要过滤的请求 
		String[] urls={"android/login","cascade"};
		for(String u:urls){
			if(url.startsWith(u)){
				chain.doFilter(request, response);
				return;
			}
		}
		//3. 验证当前请求是否通过认证
		System.out.println("***请求URL***"+url);
		Subject currentUser =SecurityUtils.getSubject();//当前用户
		Session session=currentUser.getSession();
		String userId=(String)currentUser.getPrincipal();//获取用户id
		if(currentUser.isAuthenticated()){
			//3.验证当前用户是否还在全局在线用户列表, 如果不在，则属于登录超时
			Map<String,OnLineUser> usersMap =ServletUtil.getOnLineUsers();
			OnLineUser om=usersMap.get(userId);//获取在线人员列表
			//判断当前用户是否在在线人员列表里面，如果不在，需要重新登陆
			if(om!=null){
				Boolean isDev=(Boolean)session.getAttribute("dev");
				//4. 验证访问权限    注：开发者不需要访问权限
				if(isDev||currentUser.isPermitted(url)){
					chain.doFilter(request, response);
					return;
				}else{
					System.out.println("***无权限访问:"+url);
					request.setAttribute("statusCode", 300);
					request.setAttribute("message", "您没有权限进行此操作！");
					request.getRequestDispatcher("/WEB-INF/jsp/ajaxDone.jsp").forward(request, response);
					return ;
				}
					
			}else{
				System.out.println("在线人员列表无此用户");
			}	
			
		}else{
			System.out.println("session中无用户信息"+session.getId());
		}
		
		request.setAttribute("statusCode", 301);
		request.setAttribute("message", "登陆超时！请重新登陆！");
		request.getRequestDispatcher("/WEB-INF/jsp/ajaxDone.jsp").forward(request, response);
			
		return ;*/
	}

	
	
	
	@Override
	public void destroy() {
		
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	
	
}
