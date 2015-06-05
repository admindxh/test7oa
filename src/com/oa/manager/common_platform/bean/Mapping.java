package com.oa.manager.common_platform.bean;

/**
 * Mapping entity. @author MyEclipse Persistence Tools
 */

public class Mapping implements java.io.Serializable {

	// Fields

	private String id;
	private String mpMtid;
	private String mpFkid;
	private String mpType;

	// Constructors

	/** default constructor */
	public Mapping() {
	}

	/** full constructor */
	public Mapping(String mpMtid, String mpFkid, String mpType) {
		this.mpMtid = mpMtid;
		this.mpFkid = mpFkid;
		this.mpType = mpType;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMpMtid() {
		return this.mpMtid;
	}

	public void setMpMtid(String mpMtid) {
		this.mpMtid = mpMtid;
	}

	public String getMpFkid() {
		return this.mpFkid;
	}

	public void setMpFkid(String mpFkid) {
		this.mpFkid = mpFkid;
	}

	public String getMpType() {
		return this.mpType;
	}

	public void setMpType(String mpType) {
		this.mpType = mpType;
	}

}