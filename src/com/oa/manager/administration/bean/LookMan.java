package com.oa.manager.administration.bean;

import java.util.Date;
/**
 * 类名：LookMan
 * 功能：查看投票人对应实体	  	 
 * 作者：MaFanghua
 * 版本：1.0
 * 日期：2013-6-17 下午9:05:37
 */
public class LookMan {
	
	private Date d;//投票时间
	private String  username;//投票人
	private String votetitle;//投票标题
	private String deptname;//部门名称
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVotetitle() {
		return votetitle;
	}
	public void setVotetitle(String votetitle) {
		this.votetitle = votetitle;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
}
