package com.oa.manager.personnel.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtAttendanceSign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_attendance_sign")
public class XtAttendanceSign implements java.io.Serializable {

	// Fields

	// Fields

		private String id;
		/**
		 * 考勤登记类型 1 第一次上班 2第一次下班 3 第二次上班 4 第二次下班 5 第三次上班 6 第三次下班
		 */
		private String attType;
		/**
		 * 登记时间 HH:mm:ss
		 */
		private String signtime;
		/**
		 * 登记时间 签到日期yyyy-mm-dd
		 */
		private String signdate;
		/***
		 * 登记人员
		 */
		private String signuser;
		/**
		 * 登记状态
		 */
		private String islate;
		/**
		 * 补录时间
		 */
		private Timestamp repairtime;
		/***
		 * 补录人
		 */
		private String repairuser;
		/**
		 * 补录说明
		 */
		private String repairexplain;
		 
		
	/**
	 * 创建时间
	 */
	private Timestamp creattime;

	// Constructors

	/** default constructor */
	public XtAttendanceSign() {
	}

	/** full constructor */
	public XtAttendanceSign(String attType, String signtime, String signdate,
			String signuser, String islate, Timestamp repairtime,
			String repairuser, String repairexplain, Timestamp creattime) {
		this.attType = attType;
		this.signtime = signtime;
		this.signdate = signdate;
		this.signuser = signuser;
		this.islate = islate;
		this.repairtime = repairtime;
		this.repairuser = repairuser;
		this.repairexplain = repairexplain;
		this.creattime = creattime; 
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "att_type", length = 1)
	public String getAttType() {
		return this.attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	@Column(name = "signtime", length = 10)
	public String getSigntime() {
		return this.signtime;
	}

	public void setSigntime(String signtime) {
		this.signtime = signtime;
	}

	@Column(name = "signdate", length = 20)
	public String getSigndate() {
		return this.signdate;
	}

	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}

	@Column(name = "signuser", length = 32)
	public String getSignuser() {
		return this.signuser;
	}

	public void setSignuser(String signuser) {
		this.signuser = signuser;
	}

	@Column(name = "islate", length = 1)
	public String getIslate() {
		return this.islate;
	}

	public void setIslate(String islate) {
		this.islate = islate;
	}

	@Column(name = "repairtime", length = 19)
	public Timestamp getRepairtime() {
		return this.repairtime;
	}

	public void setRepairtime(Timestamp repairtime) {
		this.repairtime = repairtime;
	}

	@Column(name = "repairuser", length = 32)
	public String getRepairuser() {
		return this.repairuser;
	}

	public void setRepairuser(String repairuser) {
		this.repairuser = repairuser;
	}

	@Column(name = "repairexplain", length = 150)
	public String getRepairexplain() {
		return this.repairexplain;
	}

	public void setRepairexplain(String repairexplain) {
		this.repairexplain = repairexplain;
	}

	@Column(name = "creattime", length = 19)
	public Timestamp getCreattime() {
		return this.creattime;
	}

	public void setCreattime(Timestamp creattime) {
		this.creattime = creattime;
	}

 
}