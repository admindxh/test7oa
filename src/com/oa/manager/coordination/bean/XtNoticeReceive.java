package com.oa.manager.coordination.bean;

import java.sql.Timestamp;

/**
 * 通知接收人员表对应实体
 */

public class XtNoticeReceive implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 通知id
	 */
	private String noticeId;
	/**
	 * 接收人员id
	 */
	private String userId;
	/**
	 * 阅读通知时间
	 */
	private Timestamp readTime;

	// Constructors

	/** default constructor */
	public XtNoticeReceive() {
	}

	/** minimal constructor */
	public XtNoticeReceive(String noticeId, String userId) {
		this.noticeId = noticeId;
		this.userId = userId;
	}

	/** full constructor */
	public XtNoticeReceive(String noticeId, String userId, Timestamp readTime) {
		this.noticeId = noticeId;
		this.userId = userId;
		this.readTime = readTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

}