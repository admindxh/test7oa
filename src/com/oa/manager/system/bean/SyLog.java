package com.oa.manager.system.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作日志对应实体
 */
@Entity
@Table(name = "sy_log")
public class SyLog implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 操作用户id
	 */
	private String userId;
	/**
	 * 操作时间
	 */
	private Timestamp actionTime;
	/**
	 * 操作内容
	 */
	private String actionContent;
	/**
	 * 操作ip
	 */
	private String actionIp;
	/**
	 * ip信息
	 */
	private String actionIpInfo;
	/**
	 * 备注
	 */
	private String actionDesc;

	// Constructors

	/** default constructor */
	public SyLog() {
	}

	/** minimal constructor */
	public SyLog(String userId, Timestamp actionTime) {
		this.userId = userId;
		this.actionTime = actionTime;
	}

	/** full constructor */
	public SyLog(String userId, Timestamp actionTime, String actionContent,
			String actionIp, String actionIpInfo, String actionDesc) {
		this.userId = userId;
		this.actionTime = actionTime;
		this.actionContent = actionContent;
		this.actionIp = actionIp;
		this.actionIpInfo = actionIpInfo;
		this.actionDesc = actionDesc;
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
	@Column(name = "action_time", nullable = false, length = 19)
	public Timestamp getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}
	@Column(name = "action_content",length = 20)
	public String getActionContent() {
		return this.actionContent;
	}

	public void setActionContent(String actionContent) {
		this.actionContent = actionContent;
	}
	@Column(name = "action_ip", length = 20)
	public String getActionIp() {
		return this.actionIp;
	}

	public void setActionIp(String actionIp) {
		this.actionIp = actionIp;
	}
	@Column(name = "action_ip_info", length = 200)
	public String getActionIpInfo() {
		return this.actionIpInfo;
	}

	public void setActionIpInfo(String actionIpInfo) {
		this.actionIpInfo = actionIpInfo;
	}
	@Column(name = "action_desc", length = 100)
	public String getActionDesc() {
		return this.actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

}