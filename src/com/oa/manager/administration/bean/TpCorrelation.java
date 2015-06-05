package com.oa.manager.administration.bean;

/**
 * TpCorrelation entity. @author MyEclipse Persistence Tools
 */

public class TpCorrelation implements java.io.Serializable {

	// Fields

	private String id;
	private String fkid;//部门人员id
	private String voteid;//投票id
	private String type;//类别  0 部门ID  1 人员ID

	// Constructors

	/** default constructor */
	public TpCorrelation() {
	}

	/** full constructor */
	public TpCorrelation(String fkid, String voteid, String type) {
		this.fkid = fkid;
		this.voteid = voteid;
		this.type = type;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkid() {
		return this.fkid;
	}

	public void setFkid(String fkid) {
		this.fkid = fkid;
	}

	public String getVoteid() {
		return this.voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}