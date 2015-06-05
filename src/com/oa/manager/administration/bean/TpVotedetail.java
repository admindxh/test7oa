package com.oa.manager.administration.bean;

/**
 * TpVotedetail entity. @author MyEclipse Persistence Tools
 */

public class TpVotedetail implements java.io.Serializable {

	// Fields

	private String id;
	private String optiondesc;//选项
	private String titleid;//题目id

	// Constructors

	/** default constructor */
	public TpVotedetail() {
	}

	/** full constructor */
	public TpVotedetail(String optiondesc, String titleid) {
		this.optiondesc = optiondesc;
		this.titleid = titleid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOptiondesc() {
		return this.optiondesc;
	}

	public void setOptiondesc(String optiondesc) {
		this.optiondesc = optiondesc;
	}

	public String getTitleid() {
		return this.titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}

}