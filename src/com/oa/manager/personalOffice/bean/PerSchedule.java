package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 日程安排表对应实体
 */

public class PerSchedule implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 所属用户id
	 */
	private String userId;
	/**
	 * 日程主题
	 */
	@NotBlank(message="日程主题不能为空")
	@Length(min=1, max=50, message="日程主题长度限制在1-50")
	private String schTitle;
	/**
	 * 日程类型，字典值id
	 */
	@NotBlank(message="日程类型不能为空")
	private String schType;
	/**
	 * 开始时间
	 */
	@NotNull(message="开始时间不能为空")
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	@NotNull(message="结束时间不能为空")
	private Timestamp endTime;
	/**
	 * 提醒时间
	 */
	private Timestamp warnTime;
	/**
	 * 日程内容
	 */
	@NotBlank(message="日程内容不能为空")
	@Length(min=1, max=65530, message="日程内容不能为空，限制65530字节")
	private String schContent;

	// Constructors

	/** default constructor */
	public PerSchedule() {
	}

	/** minimal constructor */
	public PerSchedule(String userId, String schTitle) {
		this.userId = userId;
		this.schTitle = schTitle;
	}

	/** full constructor */
	public PerSchedule(String userId, String schTitle, String schType,
			Timestamp startTime, Timestamp endTime, Timestamp warnTime,
			String schContent) {
		this.userId = userId;
		this.schTitle = schTitle;
		this.schType = schType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.warnTime = warnTime;
		this.schContent = schContent;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSchTitle() {
		return this.schTitle;
	}

	public void setSchTitle(String schTitle) {
		this.schTitle = schTitle;
	}

	public String getSchType() {
		return this.schType;
	}

	public void setSchType(String schType) {
		this.schType = schType;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getWarnTime() {
		return this.warnTime;
	}

	public void setWarnTime(Timestamp warnTime) {
		this.warnTime = warnTime;
	}

	public String getSchContent() {
		return this.schContent;
	}

	public void setSchContent(String schContent) {
		this.schContent = schContent;
	}

}