package com.oa.manager.system.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 登陆日志对应实体
 */
@Entity
@Table(name = "sy_login_log")
public class SyLoginLog implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 登陆时间
	 */
	private Timestamp loginTime;
	/**
	 * 登陆ip
	 */
	private String loginIp;
	/**
	 * ip信息：国家
	 */
	private String ipInfoCountry;
	/**
	 * ip信息：省份
	 */
	private String ipInfoRegion;
	/**
	 * ip信息：城市
	 */
	private String ipInfoCity;
	/**
	 * ip信息：运营商
	 */
	private String ipInfoIsp;
	/**
	 * 登录来源方式，1：web,2:android
	 */
	private Short loginType;
	/**
	 * 日志 备注
	 */
	private String loginDesc;
	
	// Constructors

	/** default constructor */
	public SyLoginLog() {
	}

	/** minimal constructor */
	public SyLoginLog(String userId, Timestamp loginTime) {
		this.userId = userId;
		this.loginTime = loginTime;
	}

	/** full constructor */
	public SyLoginLog(String userId, Timestamp loginTime, String loginIp,
			String ipInfoCountry, String ipInfoRegion, String ipInfoCity,
			String ipInfoIsp, Short loginType,String loginDesc) {
		this.userId = userId;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.ipInfoCountry = ipInfoCountry;
		this.ipInfoRegion = ipInfoRegion;
		this.ipInfoCity = ipInfoCity;
		this.ipInfoIsp = ipInfoIsp;
		this.loginType = loginType;
		this.loginDesc=loginDesc;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "user_id", nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "login_time", nullable = false, length = 19)
	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	@Column(name = "login_ip",  length = 20)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	@Column(name = "ip_info_country", length = 50)
	public String getIpInfoCountry() {
		return this.ipInfoCountry;
	}
	
	public void setIpInfoCountry(String ipInfoCountry) {
		this.ipInfoCountry = ipInfoCountry;
	}
	@Column(name = "ip_info_region", length = 30)
	public String getIpInfoRegion() {
		return this.ipInfoRegion;
	}

	public void setIpInfoRegion(String ipInfoRegion) {
		this.ipInfoRegion = ipInfoRegion;
	}
	@Column(name = "ip_info_city",  length = 30)
	public String getIpInfoCity() {
		return this.ipInfoCity;
	}

	public void setIpInfoCity(String ipInfoCity) {
		this.ipInfoCity = ipInfoCity;
	}
	@Column(name = "ip_info_isp", length = 20)
	public String getIpInfoIsp() {
		return this.ipInfoIsp;
	}

	public void setIpInfoIsp(String ipInfoIsp) {
		this.ipInfoIsp = ipInfoIsp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name = "login_type")
	public Short getLoginType() {
		return loginType;
	}

	public void setLoginType(Short loginType) {
		this.loginType = loginType;
	}
	@Column(name = "login_desc", length = 50)
	public String getLoginDesc() {
		return loginDesc;
	}

	public void setLoginDesc(String loginDesc) {
		this.loginDesc = loginDesc;
	}

}