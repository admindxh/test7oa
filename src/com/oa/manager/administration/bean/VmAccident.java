package com.oa.manager.administration.bean;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * VmAccident entity. @author MyEclipse Persistence Tools
 */

public class VmAccident implements java.io.Serializable {

	// Fields

	private String id;
	private Date happendate;
	private String summary;
	private String byname;
	private String byaddress;
	private String byphone;
	private Float insurancecompensation;
	private Float indemnitycompensation;
	private String happenplace;
	@NotBlank(message="车牌号不能为空")
	private String licenseno;
	private Timestamp createdate;
	private String promulgator;
	@NotBlank(message="驾驶员不能为空")
	private String drivingno;
	private String accidentconfirm;
	private String contentreconciliation;
	private Float cgmoney;
	private Float selfgmomey;
	private String bylicensenumberId;
	private String remark;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	// Constructors

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** default constructor */
	public VmAccident() {
	}

	/** full constructor */
	public VmAccident(Date happendate, String summary, String byname,
			String byaddress, String byphone, Float insurancecompensation,
			Float indemnitycompensation, String happenplace, String licenseno,
			Timestamp createdate, String promulgator, String drivingno,
			String accidentconfirm, String contentreconciliation,
			Float cgmoney, Float selfgmomey, String bylicensenumberId,
			String remark) {
		this.happendate = happendate;
		this.summary = summary;
		this.byname = byname;
		this.byaddress = byaddress;
		this.byphone = byphone;
		this.insurancecompensation = insurancecompensation;
		this.indemnitycompensation = indemnitycompensation;
		this.happenplace = happenplace;
		this.licenseno = licenseno;
		this.createdate = createdate;
		this.promulgator = promulgator;
		this.drivingno = drivingno;
		this.accidentconfirm = accidentconfirm;
		this.contentreconciliation = contentreconciliation;
		this.cgmoney = cgmoney;
		this.selfgmomey = selfgmomey;
		this.bylicensenumberId = bylicensenumberId;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getHappendate() {
		return this.happendate;
	}

	public void setHappendate(Date happendate) {
		this.happendate = happendate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getByname() {
		return this.byname;
	}

	public void setByname(String byname) {
		this.byname = byname;
	}

	public String getByaddress() {
		return this.byaddress;
	}

	public void setByaddress(String byaddress) {
		this.byaddress = byaddress;
	}

	public String getByphone() {
		return this.byphone;
	}

	public void setByphone(String byphone) {
		this.byphone = byphone;
	}

	public Float getInsurancecompensation() {
		return this.insurancecompensation;
	}

	public void setInsurancecompensation(Float insurancecompensation) {
		this.insurancecompensation = insurancecompensation;
	}

	public Float getIndemnitycompensation() {
		return this.indemnitycompensation;
	}

	public void setIndemnitycompensation(Float indemnitycompensation) {
		this.indemnitycompensation = indemnitycompensation;
	}

	public String getHappenplace() {
		return this.happenplace;
	}

	public void setHappenplace(String happenplace) {
		this.happenplace = happenplace;
	}

	public String getLicenseno() {
		return this.licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getPromulgator() {
		return this.promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public String getDrivingno() {
		return this.drivingno;
	}

	public void setDrivingno(String drivingno) {
		this.drivingno = drivingno;
	}

	public String getAccidentconfirm() {
		return this.accidentconfirm;
	}

	public void setAccidentconfirm(String accidentconfirm) {
		this.accidentconfirm = accidentconfirm;
	}

	public String getContentreconciliation() {
		return this.contentreconciliation;
	}

	public void setContentreconciliation(String contentreconciliation) {
		this.contentreconciliation = contentreconciliation;
	}

	public Float getCgmoney() {
		return this.cgmoney;
	}

	public void setCgmoney(Float cgmoney) {
		this.cgmoney = cgmoney;
	}

	public Float getSelfgmomey() {
		return this.selfgmomey;
	}

	public void setSelfgmomey(Float selfgmomey) {
		this.selfgmomey = selfgmomey;
	}

	public String getBylicensenumberId() {
		return this.bylicensenumberId;
	}

	public void setBylicensenumberId(String bylicensenumberId) {
		this.bylicensenumberId = bylicensenumberId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}