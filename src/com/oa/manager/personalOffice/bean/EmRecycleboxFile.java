package com.oa.manager.personalOffice.bean;

/**
 * EmRecycleboxFile entity. @author MyEclipse Persistence Tools
 */

public class EmRecycleboxFile implements java.io.Serializable {

	// Fields

	private String id;
	private EmFiles emFiles;
	private EmRecyclebox emRecyclebox;

	// Constructors

	/** default constructor */
	public EmRecycleboxFile() {
	}

	/** full constructor */
	public EmRecycleboxFile(EmFiles emFiles, EmRecyclebox emRecyclebox) {
		this.emFiles = emFiles;
		this.emRecyclebox = emRecyclebox;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmFiles getEmFiles() {
		return this.emFiles;
	}

	public void setEmFiles(EmFiles emFiles) {
		this.emFiles = emFiles;
	}

	public EmRecyclebox getEmRecyclebox() {
		return this.emRecyclebox;
	}

	public void setEmRecyclebox(EmRecyclebox emRecyclebox) {
		this.emRecyclebox = emRecyclebox;
	}

}