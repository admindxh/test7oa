package com.oa.manager.personnel.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtAttendanceClose entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_attendance_close")
public class XtAttendanceClose implements java.io.Serializable {

	// Fields

	private String id;
	/**
	 *考勤登记时间设置表id 
	 */
	private String xtatimeid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 开始时间
	 */
	private Timestamp startdate;
	/**
	 * 结束时间
	 */ 
	private Timestamp enddate;
	private Timestamp createtime;
	private String createuser;

	// Constructors

	/** default constructor */
	public XtAttendanceClose() {
	}

	/** full constructor */
	public XtAttendanceClose(String xtatimeid, String name,
			Timestamp startdate, Timestamp enddate, Timestamp createtime,
			String createuser) {
		this.xtatimeid = xtatimeid;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
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

	@Column(name = "xtatimeid", length = 500)
	public String getXtatimeid() {
		return this.xtatimeid;
	}

	public void setXtatimeid(String xtatimeid) {
		this.xtatimeid = xtatimeid;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "startdate", length = 19)
	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	@Column(name = "enddate", length = 19)
	public Timestamp getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
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