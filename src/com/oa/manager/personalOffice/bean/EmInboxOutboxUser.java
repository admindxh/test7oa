package com.oa.manager.personalOffice.bean;

import com.oa.manager.system.bean.SyUsers;

/**
 * EmInboxOutboxUser entity. @author MyEclipse Persistence Tools
 */

public class EmInboxOutboxUser implements java.io.Serializable {

	// Fields

	private String id;
	private SyUsers syUsers;
	private EmInbox emInbox;
	private EmOutbox emOutbox;
	private String ioStatus;

	// Constructors

	/** default constructor */
	public EmInboxOutboxUser() {
	}

	/** full constructor */
	public EmInboxOutboxUser(SyUsers syUsers, EmInbox emInbox,
			EmOutbox emOutbox, String ioStatus) {
		this.syUsers = syUsers;
		this.emInbox = emInbox;
		this.emOutbox = emOutbox;
		this.ioStatus = ioStatus;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SyUsers getSyUsers() {
		return this.syUsers;
	}

	public void setSyUsers(SyUsers syUsers) {
		this.syUsers = syUsers;
	}

	public EmInbox getEmInbox() {
		return this.emInbox;
	}

	public void setEmInbox(EmInbox emInbox) {
		this.emInbox = emInbox;
	}

	public EmOutbox getEmOutbox() {
		return this.emOutbox;
	}

	public void setEmOutbox(EmOutbox emOutbox) {
		this.emOutbox = emOutbox;
	}

	public String getIoStatus() {
		return this.ioStatus;
	}

	public void setIoStatus(String ioStatus) {
		this.ioStatus = ioStatus;
	}


}