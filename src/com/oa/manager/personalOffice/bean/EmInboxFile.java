package com.oa.manager.personalOffice.bean;

/**
 * EmInboxFile entity. @author MyEclipse Persistence Tools
 */

public class EmInboxFile implements java.io.Serializable {

	// Fields

	private String id;
	private EmInbox emInbox;
	private EmFiles emFiles;
	
	public String status;//状态  （已读未读）

	// Constructors

	/** default constructor */
	public EmInboxFile() {
	}

	/** full constructor */
	public EmInboxFile(EmInbox emInbox, EmFiles emFiles) {
		this.emInbox = emInbox;
		this.emFiles = emFiles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmInbox getEmInbox() {
		return this.emInbox;
	}

	public void setEmInbox(EmInbox emInbox) {
		this.emInbox = emInbox;
	}

	public EmFiles getEmFiles() {
		return this.emFiles;
	}

	public void setEmFiles(EmFiles emFiles) {
		this.emFiles = emFiles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}