package com.oa.manager.personalOffice.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * PublicGroup entity. @author MyEclipse Persistence Tools
 */

public class PublicGroup implements java.io.Serializable {

	// Fields

	private String id;
	@NotEmpty 
	@Length(min=1,max=50,message="分组名称长度在1-50之间。")
	private String gaName;
	private String gaRemark;

	// Constructors

	/** default constructor */
	public PublicGroup() {
	}

	/** full constructor */
	public PublicGroup(String gaName, String gaRemark) {
		this.gaName = gaName;
		this.gaRemark = gaRemark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGaName() {
		return this.gaName;
	}

	public void setGaName(String gaName) {
		this.gaName = gaName;
	}

	public String getGaRemark() {
		return this.gaRemark;
	}

	public void setGaRemark(String gaRemark) {
		this.gaRemark = gaRemark;
	}

}