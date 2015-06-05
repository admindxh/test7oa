package com.oa.manager.workFlow.bean;

import java.sql.Timestamp;

/**
 * WfTask entity. @author MyEclipse Persistence Tools
 */

public class WfTask implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 工作id，同流程实例id
	 */
	private String workId;
	/**
	 * 任务执行人
	 */
	private String taskAssignee;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 任务委托人
	 */
	private String taskOwner;
	/**
	 * 任务表单内容
	 */
	private String formHtml;
	/**
	 * 完成任务时间
	 */
	private Timestamp completeTime;

	// Constructors

	/** default constructor */
	public WfTask() {
	}

	/** minimal constructor */
	public WfTask(String workId) {
		this.workId = workId;
	}

	/** full constructor */
	public WfTask(String workId, String taskAssignee, String taskName,
			String taskOwner, String formHtml, Timestamp completeTime) {
		this.workId = workId;
		this.taskAssignee = taskAssignee;
		this.taskName = taskName;
		this.taskOwner = taskOwner;
		this.formHtml = formHtml;
		this.completeTime = completeTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getTaskAssignee() {
		return this.taskAssignee;
	}

	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskOwner() {
		return this.taskOwner;
	}

	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}

	public String getFormHtml() {
		return this.formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}

	public Timestamp getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

}