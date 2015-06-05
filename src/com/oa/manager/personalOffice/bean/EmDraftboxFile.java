package com.oa.manager.personalOffice.bean;

/**
 * EmDraftboxFile entity. @author MyEclipse Persistence Tools
 */

public class EmDraftboxFile implements java.io.Serializable {

	// Fields

	private String id;
	private EmFiles emFiles;
	private EmDraftbox emDraftbox;

	// Constructors

	/** default constructor */
	public EmDraftboxFile() {
	}

	/** full constructor */
	public EmDraftboxFile(EmFiles emFiles, EmDraftbox emDraftbox) {
		this.emFiles = emFiles;
		this.emDraftbox = emDraftbox;
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

	public EmDraftbox getEmDraftbox() {
		return this.emDraftbox;
	}

	public void setEmDraftbox(EmDraftbox emDraftbox) {
		this.emDraftbox = emDraftbox;
	}

}