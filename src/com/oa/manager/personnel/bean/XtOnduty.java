package com.oa.manager.personnel.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtOnduty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_onduty")
public class XtOnduty implements java.io.Serializable {

	// Fields

	private String id;
	/**
	 * 值班人
	 */
	private String userid; 
	/**
	 * 值班类型
	 */
	private String type;
	private String title;
	private String content;
	/**
	 * 值班开始时间
	 */
	private Timestamp starttime;
	/**
	 * 值班结束时间
	 */
	private Timestamp endtime;
	private Timestamp createtime;
	private String createuser;

	// Constructors

	/** default constructor */
	public XtOnduty() {
	}

	/** full constructor */
	public XtOnduty(String userid, String type, String title, String content,
			Timestamp starttime, Timestamp endtime, Timestamp createtime,
			String createuser) {
		this.userid = userid;
		this.type = type;
		this.title = title;
		this.content = content;
		this.starttime = starttime;
		this.endtime = endtime;
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

	@Column(name = "userid", length = 32)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "type", length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "starttime", length = 19)
	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	@Column(name = "endtime", length = 19)
	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
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