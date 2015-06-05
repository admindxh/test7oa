package com.oa.manager.administration.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * VmVehiclemanagement entity. @author MyEclipse Persistence Tools
 */

public class VmVehiclemanagement implements java.io.Serializable {

	// Fields

	private String id;
	private String vehicletypeId;
	private Date buydate;
	private String weight;
	private String status;
	private String usingunit;
	private Date insurancetime;
	private Date cinsurancetime;
	private String promulgator;
	private Double price;
	private String pono;
	private String remark;
	private String enginenumber;
	private Short seat;
	private Timestamp createdate;
	private Timestamp modydate;
	private String licenseno;
	private String usingpersonal;
	private String drivingno;
	private String bypromulgator;
	private Set vmAccidents = new HashSet(0);

	// Constructors

	/** default constructor */
	public VmVehiclemanagement() {
	}

	/** minimal constructor */
	public VmVehiclemanagement(String vehicletypeId, String status,
			String promulgator, String enginenumber, Timestamp createdate,
			Timestamp modydate, String licenseno) {
		this.vehicletypeId = vehicletypeId;
		this.status = status;
		this.promulgator = promulgator;
		this.enginenumber = enginenumber;
		this.createdate = createdate;
		this.modydate = modydate;
		this.licenseno = licenseno;
	}

	/** full constructor */
	public VmVehiclemanagement(String vehicletypeId, Date buydate,
			String weight, String status, String usingunit, Date insurancetime,
			Date cinsurancetime, String promulgator, Double price, String pono,
			String remark, String enginenumber, Short seat,
			Timestamp createdate, Timestamp modydate, String licenseno,
			String usingpersonal, String drivingno, String bypromulgator,
			Set vmAccidents) {
		this.vehicletypeId = vehicletypeId;
		this.buydate = buydate;
		this.weight = weight;
		this.status = status;
		this.usingunit = usingunit;
		this.insurancetime = insurancetime;
		this.cinsurancetime = cinsurancetime;
		this.promulgator = promulgator;
		this.price = price;
		this.pono = pono;
		this.remark = remark;
		this.enginenumber = enginenumber;
		this.seat = seat;
		this.createdate = createdate;
		this.modydate = modydate;
		this.licenseno = licenseno;
		this.usingpersonal = usingpersonal;
		this.drivingno = drivingno;
		this.bypromulgator = bypromulgator;
		this.vmAccidents = vmAccidents;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicletypeId() {
		return this.vehicletypeId;
	}

	public void setVehicletypeId(String vehicletypeId) {
		this.vehicletypeId = vehicletypeId;
	}

	public Date getBuydate() {
		return this.buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsingunit() {
		return this.usingunit;
	}

	public void setUsingunit(String usingunit) {
		this.usingunit = usingunit;
	}

	public Date getInsurancetime() {
		return this.insurancetime;
	}

	public void setInsurancetime(Date insurancetime) {
		this.insurancetime = insurancetime;
	}

	public Date getCinsurancetime() {
		return this.cinsurancetime;
	}

	public void setCinsurancetime(Date cinsurancetime) {
		this.cinsurancetime = cinsurancetime;
	}

	public String getPromulgator() {
		return this.promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPono() {
		return this.pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEnginenumber() {
		return this.enginenumber;
	}

	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}

	public Short getSeat() {
		return this.seat;
	}

	public void setSeat(Short seat) {
		this.seat = seat;
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

	public String getLicenseno() {
		return this.licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getUsingpersonal() {
		return this.usingpersonal;
	}

	public void setUsingpersonal(String usingpersonal) {
		this.usingpersonal = usingpersonal;
	}

	public String getDrivingno() {
		return this.drivingno;
	}

	public void setDrivingno(String drivingno) {
		this.drivingno = drivingno;
	}

	public String getBypromulgator() {
		return this.bypromulgator;
	}

	public void setBypromulgator(String bypromulgator) {
		this.bypromulgator = bypromulgator;
	}

	public Set getVmAccidents() {
		return this.vmAccidents;
	}

	public void setVmAccidents(Set vmAccidents) {
		this.vmAccidents = vmAccidents;
	}

}