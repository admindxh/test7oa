package com.oa.manager.coordination.bean;

/**
 * 工作日志共享人关联表对应实体
 */

public class XtJournalUser implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 日志id
	 */
	private String journalId;
	/**
	 * 共享人id
	 */
	private String userId;

	// Constructors

	/** default constructor */
	public XtJournalUser() {
	}

	/** full constructor */
	public XtJournalUser(String journalId, String userId) {
		this.journalId = journalId;
		this.userId = userId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJournalId() {
		return this.journalId;
	}

	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}