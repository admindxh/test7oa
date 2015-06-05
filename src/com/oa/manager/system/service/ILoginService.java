/**  
 * @Project: jxoa
 * @Title: ILoginService.java
 * @Package com.oa.manager.system.service
 * @date 2013-4-1 下午3:23:07
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.IBaseService;

/**
 * 
 * 类名：ILoginService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-1 下午3:23:07
 *
 */

public interface ILoginService extends IBaseService{
	
	/**
	 * 用户登录验证
	 * @param vercode	验证码
	 * @param name		用户名
	 * @param password	登录密码
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateLogin(String vercode,String name,String password,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 安卓版用户登录
	 * @param vercode
	 * @param name
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	public Map updateLoginAndroid(String vercode,String name,String password,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 界面解锁
	 * @param password
	 * @return
	 */
	public boolean unlock(HttpSession session,String password);
}
