package com.oa.manager.workFlow.bean;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * WfWork entity. @author MyEclipse Persistence Tools
 */

public class WfWork implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * wf_workflow表id
	 */
	private String workflowId;
	/**
	 * 工作标题
	 */
	@NotBlank(message="工作标题")
	@Length(min=1,max=255,message="工作标题不能超过255个字符。")
	private String workTitle;
	/**
	 * 流程名称
	 */
	private String flowName;
	/**
	 * 创建人id
	 */
	private String userId;
	/**
	 * 创建时间
	 */
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	private Timestamp endTime;
	/**
	 * 工作表单
	 */
	private String formHtml;
	/**
	 * 状态,0:运行中，1:结束
	 */
	private Short workStatus;

	// Constructors

	/** default constructor */
	public WfWork() {
	}

	/** minimal constructor */
	public WfWork(String processDefinitionId, String workflowId, String userId,
			Short workStatus) {
		this.processDefinitionId = processDefinitionId;
		this.workflowId = workflowId;
		this.userId = userId;
		this.workStatus = workStatus;
	}

	/** full constructor */
	public WfWork(String processDefinitionId, String workflowId,
			String workTitle, String flowName, String userId,
			Timestamp startTime, Timestamp endTime, String formHtml,
			Short workStatus) {
		this.processDefinitionId = processDefinitionId;
		this.workflowId = workflowId;
		this.workTitle = workTitle;
		this.flowName = flowName;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.formHtml = formHtml;
		this.workStatus = workStatus;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkTitle() {
		return this.workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getFormHtml() {
		return this.formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}

	public Short getWorkStatus() {
		return this.workStatus;
	}

	public void setWorkStatus(Short workStatus) {
		this.workStatus = workStatus;
	}

}