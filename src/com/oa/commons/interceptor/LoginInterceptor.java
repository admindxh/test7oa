/**  
 * @Project: jxoa
 * @Title: LoginInterceptor.java
 * @Package com.oa.commons.interceptor
 * @date 2013-4-2 上午10:13:29
 * @Copyright: 2013 
 */
package com.oa.commons.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 类名：LoginInterceptor
 * 功能：显示此次请求参数，处理完成耗时
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-2 上午10:13:29
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	private long st=0;
	
	//afterCompletion()方法在DispatcherServlet完全处理完请求后被调用
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse arg1, Object arg2, Exception arg3)throws Exception {
		String path = request.getContextPath();//当前工程名
		
		System.out.println("***请求处理完成***"+request.getRequestURI().substring(path.length()+1)+"耗时:"+(System.currentTimeMillis()-st));
		
	}
	//postHandle()方法在业务处理器处理请求之后被调用
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1,Object arg2, ModelAndView arg3) throws Exception {
		
		//System.out.println("***请求执行之后***"+request.getRequestURI());
		
	}
	//preHandle()方法在业务处理器处理请求之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj) throws Exception {
		st=System.currentTimeMillis();
		
		Map<String, String[]> params = request.getParameterMap();
        for(String key : params.keySet()) {
            Object value = params.get(key);
            if(value instanceof String[]){
	            String[] o=(String[])value;
	            for(int i=0;i<o.length;i++){
	            	
	        	     System.out.println("==参数==="+key+" ："+o[i]);
	            }
            }
      
        }
		return true;
	}
	

}
