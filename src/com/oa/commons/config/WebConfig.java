/**  
 * @Project: jxoa
 * @Title: WebConfig.java
 * @Package com.oa.commons.config
 * @date 2013-6-25 下午4:36:23
 * @Copyright: 2013 
 */
package com.oa.commons.config;

/**
 * 
 * 类名：WebConfig
 * 功能：系统配置文件对应javaBean
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-25 下午4:36:23
 *
 */
public class WebConfig {
	/**
	 * 系统开启,1:开启，0：关闭
	 */
	private Integer appStart;
	/**
	 * 限制登录开始时间
	 */
	private String loginStartTime;
	/**
	 * 限制登录结束时间
	 */
	private String loginEndTime;
	/**
	 * 设置限制登录的ip地址多个ip以,隔开。可以用*做匹配，例：192.168.1.*
	 */
	private String limitIps;
	/**
	 * 设置允许登录的ip地址多个ip以,隔开,注：如果设置了IP允许，则IP限制失效。可以用*做匹配，例：192.168.1.*
	 */
	private String allowIps;
	/**
	 * 登录密码允许错误的次数
	 */
	private Integer pwdErrorNum;
	/**
	 * 密码输入错误之后冷却的分钟数，整数，单位是分钟
	 */
	private Integer pwdErrorTime;
	/**
	 * 页面ajax定时请求未读信息的刷新间隔时间，单位分钟
	 */
	private Integer msgwarnTime;
	
	public Integer getAppStart() {
		return appStart;
	}
	public void setAppStart(Integer appStart) {
		this.appStart = appStart;
	}
	public String getLoginStartTime() {
		return loginStartTime;
	}
	public void setLoginStartTime(String loginStartTime) {
		this.loginStartTime = loginStartTime;
	}
	public String getLoginEndTime() {
		return loginEndTime;
	}
	public void setLoginEndTime(String loginEndTime) {
		this.loginEndTime = loginEndTime;
	}
	public String getLimitIps() {
		return limitIps;
	}
	public void setLimitIps(String limitIps) {
		this.limitIps = limitIps;
	}
	public String getAllowIps() {
		return allowIps;
	}
	public void setAllowIps(String allowIps) {
		this.allowIps = allowIps;
	}
	public Integer getPwdErrorNum() {
		return pwdErrorNum;
	}
	public void setPwdErrorNum(Integer pwdErrorNum) {
		this.pwdErrorNum = pwdErrorNum;
	}
	public Integer getPwdErrorTime() {
		return pwdErrorTime;
	}
	public void setPwdErrorTime(Integer pwdErrorTime) {
		this.pwdErrorTime = pwdErrorTime;
	}
	public Integer getMsgwarnTime() {
		return msgwarnTime;
	}
	public void setMsgwarnTime(Integer msgwarnTime) {
		this.msgwarnTime = msgwarnTime;
	}
	
	
	
}
