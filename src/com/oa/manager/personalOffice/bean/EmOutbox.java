package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * EmOutbox entity. @author MyEclipse Persistence Tools
 */

public class EmOutbox implements java.io.Serializable {

	// Fields

	private String id;
	private String eoName;
	private String eoDetail;
	private String eoUid;
	private Timestamp eoDate;
	private Set emOutboxFiles = new HashSet(0);
	private Set emInboxOutboxUsers = new HashSet(0);
	
	public String detail;

	// Constructors

	/** default constructor */
	public EmOutbox() {
	}

	/** full constructor */
	public EmOutbox(String eoName, String eoDetail, String eoUid,
			Timestamp eoDate, Set emOutboxFiles, Set emInboxOutboxUsers) {
		this.eoName = eoName;
		this.eoDetail = eoDetail;
		this.eoUid = eoUid;
		this.eoDate = eoDate;
		this.emOutboxFiles = emOutboxFiles;
		this.emInboxOutboxUsers = emInboxOutboxUsers;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEoName() {
		return this.eoName;
	}

	public void setEoName(String eoName) {
		this.eoName = eoName;
	}

	public String getEoDetail() {
		return this.eoDetail;
	}

	public void setEoDetail(String eoDetail) {
		this.eoDetail = eoDetail;
	}

	public String getEoUid() {
		return this.eoUid;
	}

	public void setEoUid(String eoUid) {
		this.eoUid = eoUid;
	}

	public Timestamp getEoDate() {
		return this.eoDate;
	}

	public void setEoDate(Timestamp eoDate) {
		this.eoDate = eoDate;
	}

	public Set getEmOutboxFiles() {
		return this.emOutboxFiles;
	}

	public void setEmOutboxFiles(Set emOutboxFiles) {
		this.emOutboxFiles = emOutboxFiles;
	}

	public Set getEmInboxOutboxUsers() {
		return this.emInboxOutboxUsers;
	}

	public void setEmInboxOutboxUsers(Set emInboxOutboxUsers) {
		this.emInboxOutboxUsers = emInboxOutboxUsers;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}