/**  
 * @Project: ktxs
 * @Title: MasConfig.java
 * @Package com.oa.commons.config
 * @date 2013-7-26 下午03:02:47
 * @Copyright: 2013 
 */
package com.oa.commons.config;

/**
 * 
 * 类名：MasConfig
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-7-26 下午03:02:47
 *
 */
public class MasConfig {

	/**
	 * 短信开关 0关闭,1开启
	 */
	private String open;
	
	/**
	 * 数据库链接地址
	 */
	private String url;
	/**
	 * mas账号
	 */
	private String user;
	/**
	 * mas地址
	 */
	private String pw;
	/**
	 * 数据库账号
	 */
	private String luser;
	/**
	 * 数据库密码
	 */
	private String lpw;
	/**
	 * 服务代码（短信的源地址）
	 */
	private String orgaddr;
	/**
	 * 业务代码
	 */
	private String serviceid;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getLuser() {
		return luser;
	}
	public void setLuser(String luser) {
		this.luser = luser;
	}
	public String getLpw() {
		return lpw;
	}
	public void setLpw(String lpw) {
		this.lpw = lpw;
	}
	 
	public String getOrgaddr() {
		return orgaddr;
	}
	public void setOrgaddr(String orgaddr) {
		this.orgaddr = orgaddr;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	
}
