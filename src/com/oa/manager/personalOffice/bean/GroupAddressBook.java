package com.oa.manager.personalOffice.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Group entity. @author MyEclipse Persistence Tools
 */

public class GroupAddressBook implements java.io.Serializable {

	// Fields

	private String id;
	private String grNo;
	@NotEmpty 
	@Length(min=1,max=50,message="分组名称长度在1-50之间。")
	private String grName;
	private String grRemark;
	private String grUid;

	// Constructors

	/** default constructor */
	public GroupAddressBook() {
	}

	/** full constructor */
	public GroupAddressBook(String grName, String grNo,String grUid,String grRemark) {
		this.grName = grName;
		this.grRemark = grRemark;
		this.grNo=grNo;
		this.grUid=grUid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrName() {
		return this.grName;
	}



	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public void setGrName(String grName) {
		this.grName = grName;
	}

	public String getGrRemark() {
		return this.grRemark;
	}

	public void setGrRemark(String grRemark) {
		this.grRemark = grRemark;
	}

	public String getGrUid() {
		return grUid;
	}

	public void setGrUid(String grUid) {
		this.grUid = grUid;
	}

}