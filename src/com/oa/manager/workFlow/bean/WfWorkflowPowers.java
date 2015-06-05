package com.oa.manager.workFlow.bean;

/**
 * WfWorkflowPowers entity. @author MyEclipse Persistence Tools
 */

public class WfWorkflowPowers implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String workId;
	private String powerId;
	private Short powerType;

	// Constructors

	/** default constructor */
	public WfWorkflowPowers() {
	}

	/** minimal constructor */
	public WfWorkflowPowers(String workId) {
		this.workId = workId;
	}

	/** full constructor */
	public WfWorkflowPowers(String workId, String powerId, Short powerType) {
		this.workId = workId;
		this.powerId = powerId;
		this.powerType = powerType;
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

	public String getPowerId() {
		return this.powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public Short getPowerType() {
		return this.powerType;
	}

	public void setPowerType(Short powerType) {
		this.powerType = powerType;
	}

}