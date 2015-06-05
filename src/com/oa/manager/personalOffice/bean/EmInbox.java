package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * EmInbox entity. @author MyEclipse Persistence Tools
 */

public class EmInbox implements java.io.Serializable {

	// Fields

	private String id;
	private String eiName;
	private String eiDetail;
	private String eiUid;
	private Timestamp eiDate;
	private Set emInboxFiles = new HashSet(0);
	private Set emInboxOutboxUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public EmInbox() {
	}

	/** full constructor */
	public EmInbox(String eiName, String eiDetail, String eiUid,
			Timestamp eiDate, Set emInboxFiles, Set emInboxOutboxUsers) {
		this.eiName = eiName;
		this.eiDetail = eiDetail;
		this.eiUid = eiUid;
		this.eiDate = eiDate;
		this.emInboxFiles = emInboxFiles;
		this.emInboxOutboxUsers = emInboxOutboxUsers;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEiName() {
		return this.eiName;
	}

	public void setEiName(String eiName) {
		this.eiName = eiName;
	}

	public String getEiDetail() {
		return this.eiDetail;
	}

	public void setEiDetail(String eiDetail) {
		this.eiDetail = eiDetail;
	}

	public String getEiUid() {
		return this.eiUid;
	}

	public void setEiUid(String eiUid) {
		this.eiUid = eiUid;
	}

	public Timestamp getEiDate() {
		return this.eiDate;
	}

	public void setEiDate(Timestamp eiDate) {
		this.eiDate = eiDate;
	}

	public Set getEmInboxFiles() {
		return this.emInboxFiles;
	}

	public void setEmInboxFiles(Set emInboxFiles) {
		this.emInboxFiles = emInboxFiles;
	}

	public Set getEmInboxOutboxUsers() {
		return this.emInboxOutboxUsers;
	}

	public void setEmInboxOutboxUsers(Set emInboxOutboxUsers) {
		this.emInboxOutboxUsers = emInboxOutboxUsers;
	}

}