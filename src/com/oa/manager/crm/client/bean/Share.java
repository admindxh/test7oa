package com.oa.manager.crm.client.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Share entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "crm_client_share")
public class Share implements java.io.Serializable {

	// Fields

	private String id;
//	客户信息id
	private String csClid;
//	部门和用户id
	private String csFkid;
//	1：部门，2：用户
	private String csType;

	// Constructors

	/** default constructor */
	public Share() {
	}

	/** full constructor */
	public Share(String csClid, String csFkid, String csType) {
		this.csClid = csClid;
		this.csFkid = csFkid;
		this.csType = csType;
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

	@Column(name = "cs_clid", length = 32)
	public String getCsClid() {
		return this.csClid;
	}

	public void setCsClid(String csClid) {
		this.csClid = csClid;
	}

	@Column(name = "cs_fkid", length = 32)
	public String getCsFkid() {
		return this.csFkid;
	}

	public void setCsFkid(String csFkid) {
		this.csFkid = csFkid;
	}

	@Column(name = "cs_type", length = 1)
	public String getCsType() {
		return this.csType;
	}

	public void setCsType(String csType) {
		this.csType = csType;
	}

}