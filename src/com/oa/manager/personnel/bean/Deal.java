package com.oa.manager.personnel.bean;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

/**
 * Deal entity. @author MyEclipse Persistence Tools
 */

public class Deal implements java.io.Serializable {

	// Fields

	private String id;
	
	private String pdUid;
	private String pdIdNo;
	private String pdNumber;
	@Length(min=1,max=50,message="合同名称长度在1-50之间。")
	private String pdName;
	private String pdType;
	private String pdStatus;
	private Date pdSignDate;
	private String pdSignNumber;
	private Date pdStart;
	private Date pdEnd;
	private String pdContent;
	private String pdStaffId;
	private Timestamp pdDate;

	// Constructors

	/** default constructor */
	public Deal() {
	}

	/** full constructor */
	public Deal(String pdUid, String pdIdNo, String pdNumber, String pdName,
			String pdType, String pdStatus, Date pdSignDate,
			String pdSignNumber, Date pdStart, Date pdEnd, String pdContent,
			String pdStaffId, Timestamp pdDate) {
		this.pdUid = pdUid;
		this.pdIdNo = pdIdNo;
		this.pdNumber = pdNumber;
		this.pdName = pdName;
		this.pdType = pdType;
		this.pdStatus = pdStatus;
		this.pdSignDate = pdSignDate;
		this.pdSignNumber = pdSignNumber;
		this.pdStart = pdStart;
		this.pdEnd = pdEnd;
		this.pdContent = pdContent;
		this.pdStaffId = pdStaffId;
		this.pdDate = pdDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPdUid() {
		return this.pdUid;
	}

	public void setPdUid(String pdUid) {
		this.pdUid = pdUid;
	}

	public String getPdIdNo() {
		return this.pdIdNo;
	}

	public void setPdIdNo(String pdIdNo) {
		this.pdIdNo = pdIdNo;
	}

	public String getPdNumber() {
		return this.pdNumber;
	}

	public void setPdNumber(String pdNumber) {
		this.pdNumber = pdNumber;
	}

	public String getPdName() {
		return this.pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getPdType() {
		return this.pdType;
	}

	public void setPdType(String pdType) {
		this.pdType = pdType;
	}

	public String getPdStatus() {
		return this.pdStatus;
	}

	public void setPdStatus(String pdStatus) {
		this.pdStatus = pdStatus;
	}

	public Date getPdSignDate() {
		return this.pdSignDate;
	}

	public void setPdSignDate(Date pdSignDate) {
		this.pdSignDate = pdSignDate;
	}

	public String getPdSignNumber() {
		return this.pdSignNumber;
	}

	public void setPdSignNumber(String pdSignNumber) {
		this.pdSignNumber = pdSignNumber;
	}

	public Date getPdStart() {
		return this.pdStart;
	}

	public void setPdStart(Date pdStart) {
		this.pdStart = pdStart;
	}

	public Date getPdEnd() {
		return this.pdEnd;
	}

	public void setPdEnd(Date pdEnd) {
		this.pdEnd = pdEnd;
	}

	public String getPdContent() {
		return this.pdContent;
	}

	public void setPdContent(String pdContent) {
		this.pdContent = pdContent;
	}

	public String getPdStaffId() {
		return this.pdStaffId;
	}

	public void setPdStaffId(String pdStaffId) {
		this.pdStaffId = pdStaffId;
	}

	public Timestamp getPdDate() {
		return this.pdDate;
	}

	public void setPdDate(Timestamp pdDate) {
		this.pdDate = pdDate;
	}

}