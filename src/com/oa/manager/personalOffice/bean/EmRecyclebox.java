package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;

/**
 * EmRecyclebox entity. @author MyEclipse Persistence Tools
 */

public class EmRecyclebox implements java.io.Serializable {

	// Fields

	private String id;
	private Integer erTable;
	private String erName;
	private String erDetail;
	private String erInuid;
	private String erOutuid;
	private Timestamp erDate;

	// Constructors

	/** default constructor */
	public EmRecyclebox() {
	}

	/** full constructor */
	public EmRecyclebox(Integer erTable, String erName, String erDetail,
			String erInuid, String erOutuid, Timestamp erDate) {
		this.erTable = erTable;
		this.erName = erName;
		this.erDetail = erDetail;
		this.erInuid = erInuid;
		this.erOutuid = erOutuid;
		this.erDate = erDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getErTable() {
		return this.erTable;
	}

	public void setErTable(Integer erTable) {
		this.erTable = erTable;
	}

	public String getErName() {
		return this.erName;
	}

	public void setErName(String erName) {
		this.erName = erName;
	}

	public String getErDetail() {
		return this.erDetail;
	}

	public void setErDetail(String erDetail) {
		this.erDetail = erDetail;
	}

	public String getErInuid() {
		return this.erInuid;
	}

	public void setErInuid(String erInuid) {
		this.erInuid = erInuid;
	}

	public String getErOutuid() {
		return this.erOutuid;
	}

	public void setErOutuid(String erOutuid) {
		this.erOutuid = erOutuid;
	}

	public Timestamp getErDate() {
		return this.erDate;
	}

	public void setErDate(Timestamp erDate) {
		this.erDate = erDate;
	}

}