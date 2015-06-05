package com.oa.manager.administration.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * VmDrivertable entity. @author MyEclipse Persistence Tools
 */

public class VmDrivertable implements java.io.Serializable {

	// Fields

	private String id;
	private String drivingno;
	private String drivingage;
	private String allowdriving;
	private Date registerdate;
	private Date papervaliddate;
	private String gender;
	private Date birthday;
	private String phone;
	private String address;
	private String checkrecord;
	private String remark;
	private String promulgator;
	private String drivingname;
	private Timestamp createdate;
	private Timestamp modydate;
	private Set vmVehiclemanagements = new HashSet(0);
	private Set vmAccidents = new HashSet(0);

	// Constructors

	/** default constructor */
	public VmDrivertable() {
	}

	/** minimal constructor */
	public VmDrivertable(String promulgator, String drivingname) {
		this.promulgator = promulgator;
		this.drivingname = drivingname;
	}

	/** full constructor */
	public VmDrivertable(String drivingno, String drivingage,
			String allowdriving, Date registerdate, Date papervaliddate,
			String gender, Date birthday, String phone, String address,
			String checkrecord, String remark, String promulgator,
			String drivingname, Timestamp createdate, Timestamp modydate,
			Set vmVehiclemanagements, Set vmAccidents) {
		this.drivingno = drivingno;
		this.drivingage = drivingage;
		this.allowdriving = allowdriving;
		this.registerdate = registerdate;
		this.papervaliddate = papervaliddate;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.checkrecord = checkrecord;
		this.remark = remark;
		this.promulgator = promulgator;
		this.drivingname = drivingname;
		this.createdate = createdate;
		this.modydate = modydate;
		this.vmVehiclemanagements = vmVehiclemanagements;
		this.vmAccidents = vmAccidents;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrivingno() {
		return this.drivingno;
	}

	public void setDrivingno(String drivingno) {
		this.drivingno = drivingno;
	}

	public String getDrivingage() {
		return this.drivingage;
	}

	public void setDrivingage(String drivingage) {
		this.drivingage = drivingage;
	}

	public String getAllowdriving() {
		return this.allowdriving;
	}

	public void setAllowdriving(String allowdriving) {
		this.allowdriving = allowdriving;
	}

	public Date getRegisterdate() {
		return this.registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public Date getPapervaliddate() {
		return this.papervaliddate;
	}

	public void setPapervaliddate(Date papervaliddate) {
		this.papervaliddate = papervaliddate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCheckrecord() {
		return this.checkrecord;
	}

	public void setCheckrecord(String checkrecord) {
		this.checkrecord = checkrecord;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPromulgator() {
		return this.promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public String getDrivingname() {
		return this.drivingname;
	}

	public void setDrivingname(String drivingname) {
		this.drivingname = drivingname;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getModydate() {
		return this.modydate;
	}

	public void setModydate(Timestamp modydate) {
		this.modydate = modydate;
	}

	public Set getVmVehiclemanagements() {
		return this.vmVehiclemanagements;
	}

	public void setVmVehiclemanagements(Set vmVehiclemanagements) {
		this.vmVehiclemanagements = vmVehiclemanagements;
	}

	public Set getVmAccidents() {
		return this.vmAccidents;
	}

	public void setVmAccidents(Set vmAccidents) {
		this.vmAccidents = vmAccidents;
	}

}