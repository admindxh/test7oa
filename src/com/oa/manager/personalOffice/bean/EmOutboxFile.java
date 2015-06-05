package com.oa.manager.personalOffice.bean;

/**
 * EmOutboxFile entity. @author MyEclipse Persistence Tools
 */

public class EmOutboxFile implements java.io.Serializable {

	// Fields

	private String id;
	private EmFiles emFiles;
	private EmOutbox emOutbox;

	// Constructors

	/** default constructor */
	public EmOutboxFile() {
	}

	/** full constructor */
	public EmOutboxFile(EmFiles emFiles, EmOutbox emOutbox) {
		this.emFiles = emFiles;
		this.emOutbox = emOutbox;
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

	public EmOutbox getEmOutbox() {
		return this.emOutbox;
	}

	public void setEmOutbox(EmOutbox emOutbox) {
		this.emOutbox = emOutbox;
	}

}