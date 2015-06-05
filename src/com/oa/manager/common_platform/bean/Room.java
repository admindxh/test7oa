package com.oa.manager.common_platform.bean;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */

public class Room implements java.io.Serializable {

	// Fields

	private String id;
	@NotEmpty 
	@Length(min=1,max=50,message="编号长度在1-50之间。")
	private String mrNo;
	@NotEmpty 
	@Length(min=1,max=50,message="会议室名称长度在1-50之间。")
	private String mrName;
	private String mrNum;
	private String mrRemark;

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(String mrNo, String mrName, String mrNum, String mrRemark) {
		this.mrNo = mrNo;
		this.mrName = mrName;
		this.mrNum = mrNum;
		this.mrRemark = mrRemark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMrNo() {
		return this.mrNo;
	}

	public void setMrNo(String mrNo) {
		this.mrNo = mrNo;
	}

	public String getMrName() {
		return this.mrName;
	}

	public void setMrName(String mrName) {
		this.mrName = mrName;
	}

	public String getMrNum() {
		return this.mrNum;
	}

	public void setMrNum(String mrNum) {
		this.mrNum = mrNum;
	}

	public String getMrRemark() {
		return this.mrRemark;
	}

	public void setMrRemark(String mrRemark) {
		this.mrRemark = mrRemark;
	}


}