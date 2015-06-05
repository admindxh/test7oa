package com.oa.manager.workFlow.bean;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * WfWorkflow entity. @author MyEclipse Persistence Tools
 */

public class WfWorkflow implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 是否可用，1：可用，0:禁用
	 */
	private Short isEnable;
	/**
	 * 同流程定义id(key)一致
	 */
	@NotBlank(message="key不能为空")
	@Pattern(regexp="^\\w{1,255}$",message="key只能由数字、字母或者下划线组成，长度在1-255之间。")
	private String flowId;
	/**
	 * 流程类型 字典值
	 */
	@NotBlank(message="流程类型不能为空")
	private String flowType;
	/**
	 * 流程名称 
	 */
	@NotBlank(message="流程名称不能为空")
	@Length(min=1,max=255,message="流程名称不能超过255个字符。")
	private String flowName;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 创建人 id
	 */
	private String createUserId;
	/**
	 * 备注
	 */
	private String flowDesc;
	/**
	 * 发布状态,0 未发布，1已发布,2:已发布，有更新
	 */
	private Short releaseState;
	/**
	 * 现存的版本数量
	 */
	private Integer versionNum;
	/**
	 * 最后更新流程设计的用户id
	 */
	private String updateUserId;
	/**
	 * 最后更新流程设计时间
	 */
	private Timestamp updateTime;
	/**
	 * 最后更新流程设计说明
	 */
	private String updateDesc;
	/**
	 * 流程设计xml内容
	 */
	private String flowXml;

	// Constructors

	/** default constructor */
	public WfWorkflow() {
	}

	/** minimal constructor */
	public WfWorkflow(Short releaseState, Integer versionNum) {
		this.releaseState = releaseState;
		this.versionNum = versionNum;
	}

	/** full constructor */
	public WfWorkflow(Short isEnable, String flowId, String flowType,
			String flowName, Timestamp createTime, String createUserId,
			String flowDesc, Short releaseState, Integer versionNum,
			String updateUserId, Timestamp updateTime, String updateDesc,
			String flowXml) {
		this.isEnable = isEnable;
		this.flowId = flowId;
		this.flowType = flowType;
		this.flowName = flowName;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.flowDesc = flowDesc;
		this.releaseState = releaseState;
		this.versionNum = versionNum;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
		this.updateDesc = updateDesc;
		this.flowXml = flowXml;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Short isEnable) {
		this.isEnable = isEnable;
	}

	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowType() {
		return this.flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFlowDesc() {
		return this.flowDesc;
	}

	public void setFlowDesc(String flowDesc) {
		this.flowDesc = flowDesc;
	}

	public Short getReleaseState() {
		return this.releaseState;
	}

	public void setReleaseState(Short releaseState) {
		this.releaseState = releaseState;
	}

	public Integer getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateDesc() {
		return this.updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public String getFlowXml() {
		return this.flowXml;
	}

	public void setFlowXml(String flowXml) {
		this.flowXml = flowXml;
	}

}