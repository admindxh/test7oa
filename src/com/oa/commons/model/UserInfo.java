/**  
 * @Title UserInfo.java
 * @date 2013-11-11 下午2:20:15
 * @Copyright: 2013 
 */
package com.oa.commons.model;

/**
 * 缓存用户基本信息 
 * @author LiuJincheng
 * @version 1.0
 */
public class UserInfo implements java.io.Serializable{
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 登录名称
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String trueName;
	/**
	 * 部门id
	 */
	private String deptId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
