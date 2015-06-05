package com.oa.manager.personnel.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

/**
 * XtAttendanceTime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_attendance_time")
public class XtAttendanceTime implements java.io.Serializable {

	// Fields

	// Fields

	private String id;
	/**
	 * 名称
	 */
	@Length(min=0, max=50, message="说明长度不能大于50。")
	private String name;
	/***
	 * 第一次上班
	 */
	private String firstup;
	/***
	 * 第一次下班
	 */
	private String firstdown;
	/***
	 * 第二次上班
	 */
	private String secondup;
	/***
	 * 第二次下班
	 */
	private String seconddown;
	/***
	 * 第三次上班
	 */
	private String thirdup;
	/***
	 * 第三次下班
	 */
	private String thirddown;
	/***
	 * 创建时间
	 */
	private Timestamp createtime;
	/***
	 * 创建人
	 */
	private String createuser;

	/***
	 * 上班开始打卡时间(分钟)
	 */
	private Integer upstart;
	/***
	 * 上班结束打卡时间(分钟)
	 */
	private Integer upend;
	/***
	 * 下班开始打卡时间(分钟)
	 */
	private Integer downstart;
	/***
	 * 下班结束打卡时间(分钟)
	 */
	private Integer downend; 

	/**
	 * 保存工作时间,如周一周二上班 则存1:1,2,3;2:1,2,3
	 */
	private String worktime; 
	// Constructors

	/** default constructor */
	public XtAttendanceTime() {
	}

	/** full constructor */
	public XtAttendanceTime(String name, String firstup, String firstdown,
			String secondup, String seconddown, String thirdup,
			String thirddown, Integer upstart, Integer upend,
			Integer downstart, Integer downend, String worktime,
			Timestamp createtime, String createuser) {
		this.name = name;
		this.firstup = firstup;
		this.firstdown = firstdown;
		this.secondup = secondup;
		this.seconddown = seconddown;
		this.thirdup = thirdup;
		this.thirddown = thirddown;
		this.upstart = upstart;
		this.upend = upend;
		this.downstart = downstart;
		this.downend = downend;
		this.worktime = worktime;
		this.createtime = createtime;
		this.createuser = createuser;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "firstup", length = 10)
	public String getFirstup() {
		return this.firstup;
	}

	public void setFirstup(String firstup) {
		this.firstup = firstup;
	}

	@Column(name = "firstdown", length = 10)
	public String getFirstdown() {
		return this.firstdown;
	}

	public void setFirstdown(String firstdown) {
		this.firstdown = firstdown;
	}

	@Column(name = "secondup", length = 10)
	public String getSecondup() {
		return this.secondup;
	}

	public void setSecondup(String secondup) {
		this.secondup = secondup;
	}

	@Column(name = "seconddown", length = 10)
	public String getSeconddown() {
		return this.seconddown;
	}

	public void setSeconddown(String seconddown) {
		this.seconddown = seconddown;
	}

	@Column(name = "thirdup", length = 10)
	public String getThirdup() {
		return this.thirdup;
	}

	public void setThirdup(String thirdup) {
		this.thirdup = thirdup;
	}

	@Column(name = "thirddown", length = 10)
	public String getThirddown() {
		return this.thirddown;
	}

	public void setThirddown(String thirddown) {
		this.thirddown = thirddown;
	}

	@Column(name = "upstart")
	public Integer getUpstart() {
		return this.upstart;
	}

	public void setUpstart(Integer upstart) {
		this.upstart = upstart;
	}

	@Column(name = "upend")
	public Integer getUpend() {
		return this.upend;
	}

	public void setUpend(Integer upend) {
		this.upend = upend;
	}

	@Column(name = "downstart")
	public Integer getDownstart() {
		return this.downstart;
	}

	public void setDownstart(Integer downstart) {
		this.downstart = downstart;
	}

	@Column(name = "downend")
	public Integer getDownend() {
		return this.downend;
	}

	public void setDownend(Integer downend) {
		this.downend = downend;
	}

	@Column(name = "worktime", length = 50)
	public String getWorktime() {
		return this.worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	@Column(name = "createtime", length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "createuser", length = 32)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

}