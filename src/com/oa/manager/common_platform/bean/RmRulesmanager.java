package com.oa.manager.common_platform.bean;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * RmRulesmanager entity. @author MyEclipse Persistence Tools
 */

public class RmRulesmanager implements java.io.Serializable {

	// Fields

	private String id;
	@NotBlank(message="新闻标题不能为空")
	@Length(max=200,message="标题不能大于200个字符")
	private String rulesTitle;
	private String rulesTypeid;
	private String author;//作者
	private String modyauthor;//修改人
	private Timestamp createdate;
	private Timestamp modydate;
	@NotBlank(message="内容不允许为空")
	private String contents;

	// Constructors

	/** default constructor */
	public RmRulesmanager() {
	}

	/** minimal constructor */
	public RmRulesmanager(String rulesTitle, String rulesTypeid, String author,
			Timestamp createdate, Timestamp modydate) {
		this.rulesTitle = rulesTitle;
		this.rulesTypeid = rulesTypeid;
		this.author = author;
		this.createdate = createdate;
		this.modydate = modydate;
	}

	/** full constructor */
	public RmRulesmanager(String rulesTitle, String rulesTypeid, String author,
			String modyauthor, Timestamp createdate, Timestamp modydate,
			String contents) {
		this.rulesTitle = rulesTitle;
		this.rulesTypeid = rulesTypeid;
		this.author = author;
		this.modyauthor = modyauthor;
		this.createdate = createdate;
		this.modydate = modydate;
		this.contents = contents;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRulesTitle() {
		return this.rulesTitle;
	}

	public void setRulesTitle(String rulesTitle) {
		this.rulesTitle = rulesTitle;
	}

	public String getRulesTypeid() {
		return this.rulesTypeid;
	}

	public void setRulesTypeid(String rulesTypeid) {
		this.rulesTypeid = rulesTypeid;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getModyauthor() {
		return this.modyauthor;
	}

	public void setModyauthor(String modyauthor) {
		this.modyauthor = modyauthor;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getModydate() {
		return this.modydate;
	}

	public void setModydate(Timestamp modydate) {
		this.modydate = modydate;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}