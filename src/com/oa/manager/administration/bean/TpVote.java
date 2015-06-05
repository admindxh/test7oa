package com.oa.manager.administration.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TpVote entity. @author MyEclipse Persistence Tools
 */

public class TpVote implements java.io.Serializable {

	// Fields

	private String id;
	@NotEmpty 
	@Length(min=1,max=50,message="标题长度在1-50之间。")
	private String tpTitle;//标题
	public String getTpTitle() {
		return tpTitle;
	}

	public void setTpTitle(String tpTitle) {
		this.tpTitle = tpTitle;
	}

	private String tpDescribe;//描述
	private Short tpSeeresult;//是否可见       查看投票结果 0 投票后可看      1投票前可看        2不允许查看
	private Short tpAnonymous;//是否匿名               是否匿名投票  0不允许 1允许
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tpValidity;//生效日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tpExpiry;//终止日期
	private Short tpStick;//置顶                                             是否置顶  0 不置顶  1置顶
	private String userid;//发布人   
	private Timestamp tpCreatetime;//创建日期
	private Timestamp tpPublishtime;//发布日期
	private Short stuts;//状态    0 未发布 1生效   2终止         新建投票默认0未发布
	private Date endDate;
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	//@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
	// Constructors

	/** default constructor */
	public TpVote() {
	}

	/** minimal constructor */
	public TpVote(String tpTitle, Date tpValidity, Short tpStick,
			Timestamp tpCreatetime) {
		this.tpTitle = tpTitle;
		this.tpValidity = tpValidity;
		this.tpStick = tpStick;
		this.tpCreatetime = tpCreatetime;
	}

	/** full constructor */
	public TpVote(String tpTitle, String tpDescribe, Short tpSeeresult,
			Short tpAnonymous, Date tpValidity, Date tpExpiry, Short tpStick,
			String userid, Timestamp tpCreatetime, Timestamp tpPublishtime,
			Short stuts) {
		this.tpTitle = tpTitle;
		this.tpDescribe = tpDescribe;
		this.tpSeeresult = tpSeeresult;
		this.tpAnonymous = tpAnonymous;
		this.tpValidity = tpValidity;
		this.tpExpiry = tpExpiry;
		this.tpStick = tpStick;
		this.userid = userid;
		this.tpCreatetime = tpCreatetime;
		this.tpPublishtime = tpPublishtime;
		this.stuts = stuts;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTpDescribe() {
		return this.tpDescribe;
	}

	public void setTpDescribe(String tpDescribe) {
		this.tpDescribe = tpDescribe;
	}

	public Short getTpSeeresult() {
		return this.tpSeeresult;
	}

	public void setTpSeeresult(Short tpSeeresult) {
		this.tpSeeresult = tpSeeresult;
	}

	public Short getTpAnonymous() {
		return this.tpAnonymous;
	}

	public void setTpAnonymous(Short tpAnonymous) {
		this.tpAnonymous = tpAnonymous;
	}

	public Date getTpValidity() {
		return this.tpValidity;
	}

	public void setTpValidity(Date tpValidity) {
		this.tpValidity = tpValidity;
	}

	public Date getTpExpiry() {
		return this.tpExpiry;
	}

	public void setTpExpiry(Date tpExpiry) {
		this.tpExpiry = tpExpiry;
	}

	public Short getTpStick() {
		return this.tpStick;
	}

	public void setTpStick(Short tpStick) {
		this.tpStick = tpStick;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Timestamp getTpCreatetime() {
		return this.tpCreatetime;
	}

	public void setTpCreatetime(Timestamp tpCreatetime) {
		this.tpCreatetime = tpCreatetime;
	}

	public Timestamp getTpPublishtime() {
		return this.tpPublishtime;
	}

	public void setTpPublishtime(Timestamp tpPublishtime) {
		this.tpPublishtime = tpPublishtime;
	}

	public Short getStuts() {
		return this.stuts;
	}

	public void setStuts(Short stuts) {
		this.stuts = stuts;
	}


}