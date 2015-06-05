/**  
 * @Project: jxoa
 * @Title: LoginFilter.java
 * @Package com.oa.commons.filter
 * @date 2013-6-4 上午10:29:57
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
 * 类名：LoginFilter
 * 功能：登陆过滤
 * 详细：验证用户是否登录，用户访问权限
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-4 上午10:29:57
 *
 */
public class LoginFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String url=request.getRequestURI();//请求URL 包含工程名
		
		String accept=request.getHeader("Accept");
		System.out.println("请求："+url+"   ====accept:"+accept);
		
		chain.doFilter(request, response);
		return;
		
		
		/*String path = request.getContextPath();//当前工程名
		//0.判断当前请求是否需要返回json格式信息
		boolean isJson=false;//是否需要返回json格式数据
		if(request.getHeader("Accept").contains("application/json")){
			isJson=true;
			//System.out.println("需要返回json格式数据");
		}
		else if("1".equals(request.getParameter("ajax"))){
			//iframe方式上传表单，默认隐藏提交一个ajax参数
			isJson=true;
		}
		
		String url=request.getRequestURI();//请求URL 包含工程名
		
		url=url.substring(path.length()+1);//去除工程名称
		
		String refererUrl=request.getHeader("Referer");//来源URL
		
		//2.排序不需要过滤的请求 
		String[] urls={"sy_login","cascade"};
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
					if (isJson){
						 request.setAttribute("statusCode", 300);
						 request.setAttribute("message", "您没有权限进行此操作！");
						 request.getRequestDispatcher("/WEB-INF/jsp/ajaxDone.jsp").forward(request, response);
						 
					}else{
						response.setContentType("text/html; charset=UTF-8");
						response.sendRedirect(path+"/nopower.jsp");
						
					}
					return ;
				}
					
			}else{
				System.out.println("在线人员列表无此用户");
			}	
			
		}else{
			System.out.println("session中无用户信息"+session.getId());
		}
		if(refererUrl!=null){
			//有来源页面 提示登录超时
			if (isJson){
				 request.setAttribute("statusCode", 301);
				 request.setAttribute("message", "登陆超时！请重新登陆！");
				 request.getRequestDispatcher("/WEB-INF/jsp/ajaxDone.jsp").forward(request, response);
			}else{
				response.setContentType("text/html; charset=UTF-8");
				response.sendRedirect(path+"/login_timeout.jsp");
			}
			
		}else{
			response.setContentType("text/html; charset=UTF-8");
			response.sendRedirect(path+"/index.jsp");
		}
		return ;*/
	}
	
	@Override
	public void destroy() {
		
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	
	
}
