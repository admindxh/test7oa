package com.oa.manager.coordination.bean;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 工作日志表对应实体
 */

public class XtJournal implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 类型，字典值id
	 */
	@NotBlank(message="日志类型不能为空")
	private String journalType;
	/**
	 * 标题
	 */
	@NotBlank(message="标题不能为空")
	@Length(min=1, max=100, message="标题长度限制在1-100")
	private String journalTitle;
	/**
	 * 内容
	 */
	@NotBlank(message="内容不能为空")
	@Length(min=1, max=65530, message="内容不能为空，限制65530字节")
	private String journalContent;
	/**
	 * 所属用户
	 */
	private String userId;
	/**
	 * 日期
	 */
	private Timestamp journalTime;

	// Constructors

	/** default constructor */
	public XtJournal() {
	}

	/** minimal constructor */
	public XtJournal(String journalType, String journalTitle, String userId,
			Timestamp journalTime) {
		this.journalType = journalType;
		this.journalTitle = journalTitle;
		this.userId = userId;
		this.journalTime = journalTime;
	}

	/** full constructor */
	public XtJournal(String journalType, String journalTitle,
			String journalContent, String userId, Timestamp journalTime) {
		this.journalType = journalType;
		this.journalTitle = journalTitle;
		this.journalContent = journalContent;
		this.userId = userId;
		this.journalTime = journalTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJournalType() {
		return this.journalType;
	}

	public void setJournalType(String journalType) {
		this.journalType = journalType;
	}

	public String getJournalTitle() {
		return this.journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}

	public String getJournalContent() {
		return this.journalContent;
	}

	public void setJournalContent(String journalContent) {
		this.journalContent = journalContent;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getJournalTime() {
		return this.journalTime;
	}

	public void setJournalTime(Timestamp journalTime) {
		this.journalTime = journalTime;
	}

}