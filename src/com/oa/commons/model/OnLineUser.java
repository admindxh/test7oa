/**  
 * @Project: jxoa
 * @Title: OnLineUser.java
 * @Package com.oa.commons.model
 * @date 2013-4-10 下午5:10:10
 * @Copyright: 2013 
 */
package com.oa.commons.model;

import java.util.Map;

/**
 * 
 * 类名：OnLineUser
 * 功能：全局变量存储用户在线基本信息
 * 详细：用户在线统计
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-10 下午5:10:10
 *
 */
public class OnLineUser implements java.io.Serializable{
	
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 真实姓名
	 */
	private String trueName;
	
	/**
	 * 用户登录信息集合，
	 * Map:
	 * 	  key:sessionId	  登录时sessionId
	 * 	  value:LoginInfo 登录信息
	 */
	private Map<String,LoginInfo> loginInfos;
	
	/**
	 * 性别 1:男,0:女
	 */
	private Short sex;
	/**
	 * 部门id
	 */
	private String deptId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Map<String, LoginInfo> getLoginInfos() {
		return loginInfos;
	}
	public void setLoginInfos(Map<String, LoginInfo> loginInfos) {
		this.loginInfos = loginInfos;
	}
	
	
	
}
