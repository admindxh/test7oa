package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * EmDraftbox entity. @author MyEclipse Persistence Tools
 */

public class EmDraftbox implements java.io.Serializable {

	// Fields

	private String id;
	private String edInUid;
	private String edName;
	private String edDetail;
	private String edOutUid;
	private Timestamp edDate;
	private Set emDraftboxFiles = new HashSet(0);

	// Constructors

	/** default constructor */
	public EmDraftbox() {
	}

	/** full constructor */
	public EmDraftbox(String edInUid, String edName, String edDetail,
			String edOutUid, Timestamp edDate, Set emDraftboxFiles) {
		this.edInUid = edInUid;
		this.edName = edName;
		this.edDetail = edDetail;
		this.edOutUid = edOutUid;
		this.edDate = edDate;
		this.emDraftboxFiles = emDraftboxFiles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEdInUid() {
		return this.edInUid;
	}

	public void setEdInUid(String edInUid) {
		this.edInUid = edInUid;
	}

	public String getEdName() {
		return this.edName;
	}

	public void setEdName(String edName) {
		this.edName = edName;
	}

	public String getEdDetail() {
		return this.edDetail;
	}

	public void setEdDetail(String edDetail) {
		this.edDetail = edDetail;
	}

	public String getEdOutUid() {
		return this.edOutUid;
	}

	public void setEdOutUid(String edOutUid) {
		this.edOutUid = edOutUid;
	}

	public Timestamp getEdDate() {
		return this.edDate;
	}

	public void setEdDate(Timestamp edDate) {
		this.edDate = edDate;
	}

	public Set getEmDraftboxFiles() {
		return this.emDraftboxFiles;
	}

	public void setEmDraftboxFiles(Set emDraftboxFiles) {
		this.emDraftboxFiles = emDraftboxFiles;
	}

}