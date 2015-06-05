/**  
 * @Project: jxoa
 * @Title: TaskQueryModel.java
 * @Package com.oa.manager.workFlow.entity
 * @date 2013-7-26 上午9:48:55
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.entity;

import java.util.Date;

/**
 * 
 * 类名：TaskQueryModel
 * 功能：任务查询模型对象
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-26 上午9:48:55
 *
 */
public class TaskQueryModel {
	/**
	 *  流程key
	 */
	private String processDefinitionKey; 
	
	/**
	 * 流程名称
	 */
	private String flowName;
	/**
	 * 工作标题
	 */
	private String workTitle;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 大于等于任务创建时间
	 */
	private Date createdAfter;
	/**
	 * 小于等于任务创建时间
	 */
	private Date createdBefore;
	/**
	 * 大于等于任务完成时间
	 */
	private Date dueAfter;
	/**
	 * 小于等于任务完成时间
	 */
	private Date dueBefore;
	/**
	 * 任务执行人id
	 */
	private String assignee;
	/**
	 * 任务委托人
	 */
	private String owner;

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getCreatedAfter() {
		return createdAfter;
	}

	public void setCreatedAfter(Date createdAfter) {
		this.createdAfter = createdAfter;
	}

	public Date getCreatedBefore() {
		return createdBefore;
	}

	public void setCreatedBefore(Date createdBefore) {
		this.createdBefore = createdBefore;
	}

	public Date getDueAfter() {
		return dueAfter;
	}

	public void setDueAfter(Date dueAfter) {
		this.dueAfter = dueAfter;
	}

	public Date getDueBefore() {
		return dueBefore;
	}

	public void setDueBefore(Date dueBefore) {
		this.dueBefore = dueBefore;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	
	
	
}
