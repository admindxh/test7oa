/**  
 * @Project: jxoa
 * @Title: IpInfo.java
 * @Package com.oa.commons.model
 * @date 2013-4-11 下午4:41:01
 * @Copyright: 2013 
 */
package com.oa.commons.model;

/**
 * 
 * 类名：IpInfo
 * 功能：ip信息
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-11 下午4:41:01
 *
 */
public class IpInfo implements java.io.Serializable{
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 国家
	 */
	private String country; 
	/**
	 * 省份
	 */
	private String region;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 运营商
	 */
	private String isp;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	
	
}
