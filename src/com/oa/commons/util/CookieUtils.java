/**  
 * @Project: jxoa
 * @Title: CookieUtils.java
 * @Package com.oa.commons.util
 * @date 2013-6-2 下午6:03:32
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类名：CookieUtils
 * 功能：Cookie 辅助类
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-2 下午6:03:32
 *
 */
public class CookieUtils {
	/**
	 * 用户ID加密之后的cookie名称
	 */
	public static final String NAME_UID = "SJMCOOKIE";
	
	private CookieUtils(){}
	
	/**
	 * 获得cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie name
	 * @return if exist return cookie, else return null.
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * 根据部署路径，将cookie保存在根目录。
	 * 
	 * @param request
	 * @param response
	 * @param name		cookie名字
	 * @param value		cookie值
	 * @param expiry	cookie生命周期  以秒为单位，可以为空
	 * @param domain	设置域名
	 * @return
	 */
	public static Cookie addCookie(HttpServletRequest request,HttpServletResponse response, String name, String value,
			Integer expiry, String domain) {
		Cookie cookie = new Cookie(name, value);
		if (expiry != null) {
			cookie.setMaxAge(expiry);
		}
		if (StringUtils.isNotBlank(domain)) {
			cookie.setDomain(domain);
		}
		String ctx = request.getContextPath();
		cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);//设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
		response.addCookie(cookie);
		return cookie;
	}

	/**
	 * 取消cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param domain
	 */
	public static void cancleCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String domain) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		String ctx = request.getContextPath();
		cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
		if (StringUtils.isNotBlank(domain)) {
			cookie.setDomain(domain);
		}
		response.addCookie(cookie);
	}
}
