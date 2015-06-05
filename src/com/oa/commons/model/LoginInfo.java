/**  
 * @Title: LoginInfo.java
 * @date 2013-10-11 上午9:26:54
 * @Copyright: 2013 
 */
package com.oa.commons.model;

import java.sql.Timestamp;

/**
 * 用户登录时的信息
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public class LoginInfo implements java.io.Serializable{
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * id uuid 自定义，用于一些业务操作
	 */
	private String id;
	/**
	 * 登录类型 1：网页 2：安卓客户端
	 */
	private int loginType;
	/**
	 * 登录ip 信息
	 */
	private IpInfo ipInfo;
	/**
	 * 登陆时间
	 */
	private Timestamp loginTime;
	
	
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public IpInfo getIpInfo() {
		return ipInfo;
	}
	public void setIpInfo(IpInfo ipInfo) {
		this.ipInfo = ipInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
