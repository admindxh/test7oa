package com.oa.manager.personnel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtAttendanceTimeUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_attendance_time_user")
public class XtAttendanceTimeUser implements java.io.Serializable {

	// Fields

	private String id;
	private String attimeId;
	private String userId;

	// Constructors

	/** default constructor */
	public XtAttendanceTimeUser() {
	}

	/** full constructor */
	public XtAttendanceTimeUser(String attimeId, String userId) {
		this.attimeId = attimeId;
		this.userId = userId;
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

	@Column(name = "attime_id", length = 32)
	public String getAttimeId() {
		return this.attimeId;
	}

	public void setAttimeId(String attimeId) {
		this.attimeId = attimeId;
	}

	@Column(name = "user_id", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}