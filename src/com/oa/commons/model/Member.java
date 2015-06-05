/**  
 * @Project: jxoa
 * @Title: Member.java
 * @Package com.oa.commons.model
 * @date 2013-4-1 上午9:31:14
 * @Copyright: 2013 
 */
package com.oa.commons.model;

import java.sql.Timestamp;

/**
 * 
 * 类名：Member
 * 功能：session中存储用户信息model
 * 详细：保存用户常用信息 放入session
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-1 上午9:31:14
 *
 */

public class Member implements java.io.Serializable{
	
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 登录信息 
	 * @return
	 */
	private IpInfo ipInfo;
	/**
	 * 登录时间
	 */
	private Timestamp loginTime;
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
	public IpInfo getIpInfo() {
		return ipInfo;
	}
	public void setIpInfo(IpInfo ipInfo) {
		this.ipInfo = ipInfo;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
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
	


}
