package com.oa.manager.administration.bean;

import java.sql.Timestamp;

/**
 * TpCount entity. @author MyEclipse Persistence Tools
 */

public class TpCount implements java.io.Serializable {

	// Fields

	private String id;
	private String userid;//用户id
	private String titleid;//题目id
	private String voteid;//投票id
	private String votedetailid;//选项id
	private Timestamp votetime;//投票时间
	private String textarea;//文本输入存入次字段 默认为空

	// Constructors

	/** default constructor */
	public TpCount() {
	}

	/** full constructor */
	public TpCount(String userid, String titleid, String voteid,
			String votedetailid, Timestamp votetime, String textarea) {
		this.userid = userid;
		this.titleid = titleid;
		this.voteid = voteid;
		this.votedetailid = votedetailid;
		this.votetime = votetime;
		this.textarea = textarea;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitleid() {
		return this.titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}

	public String getVoteid() {
		return this.voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getVotedetailid() {
		return this.votedetailid;
	}

	public void setVotedetailid(String votedetailid) {
		this.votedetailid = votedetailid;
	}

	public Timestamp getVotetime() {
		return this.votetime;
	}

	public void setVotetime(Timestamp votetime) {
		this.votetime = votetime;
	}

	public String getTextarea() {
		return this.textarea;
	}

	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}

}