package com.oa.manager.personnel.bean;

/**
 * PeFile entity. @author MyEclipse Persistence Tools
 */

public class PeFile implements java.io.Serializable {

	// Fields

	private String id;
	private String peName;
	private String peNewName;
	private String peExt;
	private Integer peSize;
	private String peDid;

	// Constructors

	/** default constructor */
	public PeFile() {
	}

	/** full constructor */
	public PeFile(String peName, String peNewName, String peExt,
			Integer peSize, String peDid) {
		this.peName = peName;
		this.peNewName = peNewName;
		this.peExt = peExt;
		this.peSize = peSize;
		this.peDid = peDid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeName() {
		return this.peName;
	}

	public void setPeName(String peName) {
		this.peName = peName;
	}

	public String getPeNewName() {
		return this.peNewName;
	}

	public void setPeNewName(String peNewName) {
		this.peNewName = peNewName;
	}

	public String getPeExt() {
		return this.peExt;
	}

	public void setPeExt(String peExt) {
		this.peExt = peExt;
	}

	public Integer getPeSize() {
		return this.peSize;
	}

	public void setPeSize(Integer peSize) {
		this.peSize = peSize;
	}

	public String getPeDid() {
		return this.peDid;
	}

	public void setPeDid(String peDid) {
		this.peDid = peDid;
	}

}