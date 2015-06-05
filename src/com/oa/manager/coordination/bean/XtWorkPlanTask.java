package com.oa.manager.coordination.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 计划任务
 */
@Entity
@Table(name = "xt_work_plan_task")
public class XtWorkPlanTask implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String workPlanId;
	private Timestamp startTime;
	private Timestamp endTime;
	private String userId;
	private String content;
	private String files;

	// Constructors

	/** default constructor */
	public XtWorkPlanTask() {
	}

	/** full constructor */
	public XtWorkPlanTask(String workPlanId, Timestamp startTime,
			Timestamp endTime, String userId, String content, String files) {
		this.workPlanId = workPlanId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userId = userId;
		this.content = content;
		this.files = files;
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

	@Column(name = "_work_plan_id", length = 32)
	public String getWorkPlanId() {
		return this.workPlanId;
	}

	public void setWorkPlanId(String workPlanId) {
		this.workPlanId = workPlanId;
	}

	@Column(name = "_start_time", length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "_end_time", length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "_user_id", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "_content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "_files", length = 65535)
	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

}