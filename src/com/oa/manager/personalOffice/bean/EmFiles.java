package com.oa.manager.personalOffice.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * EmFiles entity. @author MyEclipse Persistence Tools
 */

public class EmFiles implements java.io.Serializable {

	// Fields

	private String id;
	private String efName;
	private String efNewName;
	private String efExt;
	private Integer efSize;
	private Integer efNum;
	private Set emOutboxFiles = new HashSet(0);
	private Set emInboxFiles = new HashSet(0);

	// Constructors

	/** default constructor */
	public EmFiles() {
	}

	/** full constructor */
	public EmFiles(String efName, String efNewName, String efExt,
			Integer efSize, Integer efNum, Set emOutboxFiles, Set emInboxFiles) {
		this.efName = efName;
		this.efNewName = efNewName;
		this.efExt = efExt;
		this.efSize = efSize;
		this.efNum = efNum;
		this.emOutboxFiles = emOutboxFiles;
		this.emInboxFiles = emInboxFiles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEfName() {
		return this.efName;
	}

	public void setEfName(String efName) {
		this.efName = efName;
	}

	public String getEfNewName() {
		return this.efNewName;
	}

	public void setEfNewName(String efNewName) {
		this.efNewName = efNewName;
	}

	public String getEfExt() {
		return this.efExt;
	}

	public void setEfExt(String efExt) {
		this.efExt = efExt;
	}

	public Integer getEfSize() {
		return this.efSize;
	}

	public void setEfSize(Integer efSize) {
		this.efSize = efSize;
	}

	public Integer getEfNum() {
		return this.efNum;
	}

	public void setEfNum(Integer efNum) {
		this.efNum = efNum;
	}

	public Set getEmOutboxFiles() {
		return this.emOutboxFiles;
	}

	public void setEmOutboxFiles(Set emOutboxFiles) {
		this.emOutboxFiles = emOutboxFiles;
	}

	public Set getEmInboxFiles() {
		return this.emInboxFiles;
	}

	public void setEmInboxFiles(Set emInboxFiles) {
		this.emInboxFiles = emInboxFiles;
	}

}