package com.oa.manager.common_platform.bean;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * RgRulesregulations entity. @author MyEclipse Persistence Tools
 */

public class RgRulesregulations implements java.io.Serializable {

	// Fields

	private String id;
	private String typeid;
	@NotBlank(message="内容不能为空")
	private String contents;
	private String promulgator;
	private Timestamp createdate;
	private Timestamp modifiedate;
	@NotBlank(message="标题不能为空")
	@Length(min=1,max=50,message="标题不能超过50个字符")
	private String rulestitle;
	private String modifyByname;

	// Constructors

	/** default constructor */
	public RgRulesregulations() {
	}

	/** minimal constructor */
	public RgRulesregulations(String typeid, String promulgator,
			Timestamp createdate, Timestamp modifiedate, String rulestitle) {
		this.typeid = typeid;
		this.promulgator = promulgator;
		this.createdate = createdate;
		this.modifiedate = modifiedate;
		this.rulestitle = rulestitle;
	}

	/** full constructor */
	public RgRulesregulations(String typeid, String contents,
			String promulgator, Timestamp createdate, Timestamp modifiedate,
			String rulestitle, String modifyByname) {
		this.typeid = typeid;
		this.contents = contents;
		this.promulgator = promulgator;
		this.createdate = createdate;
		this.modifiedate = modifiedate;
		this.rulestitle = rulestitle;
		this.modifyByname = modifyByname;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return this.typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPromulgator() {
		return this.promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getModifiedate() {
		return this.modifiedate;
	}

	public void setModifiedate(Timestamp modifiedate) {
		this.modifiedate = modifiedate;
	}

	public String getRulestitle() {
		return this.rulestitle;
	}

	public void setRulestitle(String rulestitle) {
		this.rulestitle = rulestitle;
	}

	public String getModifyByname() {
		return this.modifyByname;
	}

	public void setModifyByname(String modifyByname) {
		this.modifyByname = modifyByname;
	}

}