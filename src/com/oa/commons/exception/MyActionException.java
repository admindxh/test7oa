/**  
 * @Project: jxoa
 * @Title: MyActionException.java
 * @Package com.oa.commons.exception
 * @date 2013-4-2 下午3:39:49
 * @Copyright: 2013 
 */
package com.oa.commons.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.config.MsgConfig;
import com.oa.commons.util.ServletUtil;

/**
 * 
 * 类名：MyActionException
 * 功能：统一异常处理
 * 详细：统一处理所有请求异常
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-2 下午3:39:49
 *
 */
public class MyActionException implements HandlerExceptionResolver {
	
	private static Logger logger = Logger.getLogger(MyActionException.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse arg1, Object obj, Exception e) {
		
		String url=request.getRequestURI();//请求URL
		
		boolean isJson=ServletUtil.isJSONResponse(request);//是否需要返回json格式数据
		
		ModelAndView mav = new ModelAndView(MsgConfig.PAGE_AJAXDONE);
		
		if(e instanceof org.apache.shiro.authz.UnauthorizedException){
			//没有访问权限
			org.apache.shiro.authz.UnauthorizedException ue=(org.apache.shiro.authz.UnauthorizedException)e;
			System.out.println("***没有访问权限:"+url+"  ***"+ue.getMessage());
			mav.addObject(MsgConfig.MESSAGE, "抱歉，您没有操作权限！");//没有操作权限
				
		}else if(e instanceof MyRuntimeException){
			//自定义异常错误 用于提示
			mav.addObject(MsgConfig.MESSAGE, e.getMessage());//自定义异常信息
			
		}else if(e instanceof org.springframework.validation.BindException|| e instanceof org.springframework.beans.TypeMismatchException){
			//数据格式封装错误
			
			mav.addObject(MsgConfig.MESSAGE, "数据类型错误，请根据要求修正！");//数据类型错误，请根据要求修正！
			
			logger.error("发生异常：\n\t请求："+url+"\n\t异常名称:"+e.getClass().getName()+"\n\t异常信息:"+e.getMessage()+"\n\t异常来源:"+obj);
			e.printStackTrace();
		}else if(e instanceof org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException){
			
			mav.addObject(MsgConfig.MESSAGE, "操作失败！原因：数据操作失败，可能是您要操作的数据已经不存在，请更新为最新数据再操作！");//操作失败！原因：数据操作失败，可能是您要操作的数据已经不存在，请更新为最新数据再操作！
			
		}else if(e instanceof javax.validation.ConstraintViolationException){
			//java bean校验错误 hibernate save update 操作时
			ConstraintViolationException cve=(ConstraintViolationException)e;
			
			Set<ConstraintViolation<?>> cvs= cve.getConstraintViolations();
			List<String> errorMessages=new ArrayList<String>();
			for(ConstraintViolation<?> c:cvs){
				errorMessages.add(c.getMessage());
			}
			mav.setViewName("ajaxValidationMessage");
			mav.addObject("errorCount",errorMessages.size());
			mav.addObject(MsgConfig.MESSAGE,errorMessages);
			
			
		}else if(e instanceof org.springframework.transaction.CannotCreateTransactionException){
			
			mav.addObject(MsgConfig.MESSAGE, "数据库连接失败!");//无法连接上数据库

		}else{
			mav.addObject(MsgConfig.MESSAGE, "服务器异常...请稍后再试....");//服务器异常...请稍后再试....
			
			logger.error("发生异常：\n\t请求："+url+"\n\t异常名称:"+e.getClass().getName()+"\n\t异常信息:"+e.getMessage()+"\n\t异常来源:"+obj);
			e.printStackTrace();
		}
		//最后返回错误提示信息
		if(isJson){
			mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL );
			return mav;
		}else{
			//不需要返回json格式，直接返回错误提示页面
			mav.setViewName("exception");
			return mav;
		}
	}

}
