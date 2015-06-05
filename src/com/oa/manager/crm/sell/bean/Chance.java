package com.oa.manager.crm.sell.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Chance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "crm_sell_chance")
public class Chance implements java.io.Serializable {

	// Fields

	private String id;
	private String scInfo;
	private String scLinkman;
	private String scName;
	private String scSource;
	private String scStatus;
	private String scPicture;
	private String scCreate;
	private Timestamp scCreateDate;
	private String scBranch;
	private String scStaff;
	private Timestamp scDate;
	
	private String info;//页面使用 客户名称
	private String linkman;//页面使用 联系人
	private String phone;//页面使用 联系人电话

	// Constructors

	/** default constructor */
	public Chance() {
	}

	/** full constructor */
	public Chance(String scInfo, String scLinkman, String scName,
			String scSource, String scStatus, String scPicture,
			String scCreate, Timestamp scCreateDate, String scBranch,
			String scStaff, Timestamp scDate,String  info,String  linkman,String  phone ) {
		this.scInfo = scInfo;
		this.scLinkman = scLinkman;
		this.scName = scName;
		this.scSource = scSource;
		this.scStatus = scStatus;
		this.scPicture = scPicture;
		this.scCreate = scCreate;
		this.scCreateDate = scCreateDate;
		this.scBranch = scBranch;
		this.scStaff = scStaff;
		this.scDate = scDate;
		this.info=info;
		this.linkman=linkman;
		this.phone=phone;
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

	@Column(name = "sc_info", length = 32)
	public String getScInfo() {
		return this.scInfo;
	}

	public void setScInfo(String scInfo) {
		this.scInfo = scInfo;
	}

	@Column(name = "sc_linkman", length = 32)
	public String getScLinkman() {
		return this.scLinkman;
	}

	public void setScLinkman(String scLinkman) {
		this.scLinkman = scLinkman;
	}

	@Column(name = "sc_name", length = 50)
	public String getScName() {
		return this.scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	@Column(name = "sc_source", length = 32)
	public String getScSource() {
		return this.scSource;
	}

	public void setScSource(String scSource) {
		this.scSource = scSource;
	}

	@Column(name = "sc_status", length = 32)
	public String getScStatus() {
		return this.scStatus;
	}

	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
	}

	@Column(name = "sc_picture", length = 999)
	public String getScPicture() {
		return this.scPicture;
	}

	public void setScPicture(String scPicture) {
		this.scPicture = scPicture;
	}

	@Column(name = "sc_create", length = 32)
	public String getScCreate() {
		return this.scCreate;
	}

	public void setScCreate(String scCreate) {
		this.scCreate = scCreate;
	}

	@Column(name = "sc_createDate", length = 19)
	public Timestamp getScCreateDate() {
		return this.scCreateDate;
	}

	public void setScCreateDate(Timestamp scCreateDate) {
		this.scCreateDate = scCreateDate;
	}

	@Column(name = "sc_branch", length = 3200)
	public String getScBranch() {
		return this.scBranch;
	}

	public void setScBranch(String scBranch) {
		this.scBranch = scBranch;
	}

	@Column(name = "sc_staff", length = 3200)
	public String getScStaff() {
		return this.scStaff;
	}

	public void setScStaff(String scStaff) {
		this.scStaff = scStaff;
	}

	@Column(name = "sc_date", length = 19)
	public Timestamp getScDate() {
		return this.scDate;
	}

	public void setScDate(Timestamp scDate) {
		this.scDate = scDate;
	}
	@Transient
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Transient
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	@Transient
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

}