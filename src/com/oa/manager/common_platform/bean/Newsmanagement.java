package com.oa.manager.common_platform.bean;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 新闻表对应的实体
 * 
 * 
 */

public class Newsmanagement implements java.io.Serializable {

	// Fields

	private String id;
	private String typeid;
	@NotBlank(message="标题不能为空")
	@Length(min=1,max=100, message="{标题必须小于100个字符}")
	private String newstitle;
	private String plotsummary;
	@NotBlank(message="新闻内容不能为空")
	@Length(min=1,message="{标题必须小于100个字符}")
	private String newsplot;
	private Integer status;
	private String promulgator;
	private Timestamp createdtime;
	private Timestamp modifiedate;
	private Integer hits;
	private Integer contents;
	private Integer stick;
	private Integer allowcomment;
	private String modifyByname;

	public String getModifyByname() {
		return modifyByname;
	}

	public void setModifyByname(String modifyByname) {
		this.modifyByname = modifyByname;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;

	// Constructors

	/** default constructor */
	public Newsmanagement() {
	}

	/** minimal constructor */
	public Newsmanagement(String typeid, String newstitle,
			Timestamp createdtime, Timestamp modifiedate) {
		this.typeid = typeid;
		this.newstitle = newstitle;
		this.createdtime = createdtime;
		this.modifiedate = modifiedate;
	}

	/** full constructor */
	public Newsmanagement(String typeid, String newstitle, String plotsummary,
			String newsplot, Integer status, String promulgator,
			Timestamp createdtime, Timestamp modifiedate, Integer hits,
			Integer contents,  Integer stick,
			Integer allowcomment,String modifyByname) {
		this.typeid = typeid;
		this.newstitle = newstitle;
		this.plotsummary = plotsummary;
		this.newsplot = newsplot;
		this.status = status;
		this.promulgator = promulgator;
		this.createdtime = createdtime;
		this.modifiedate = modifiedate;
		this.hits = hits;
		this.contents = contents;
		this.stick = stick;
		this.allowcomment = allowcomment;
		this.modifyByname= modifyByname;
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

	public String getNewstitle() {
		return this.newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getPlotsummary() {
		return this.plotsummary;
	}

	public void setPlotsummary(String plotsummary) {
		this.plotsummary = plotsummary;
	}

	public String getNewsplot() {
		return this.newsplot;
	}

	public void setNewsplot(String newsplot) {
		this.newsplot = newsplot;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPromulgator() {
		return this.promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public Timestamp getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	public Timestamp getModifiedate() {
		return this.modifiedate;
	}

	public void setModifiedate(Timestamp modifiedate) {
		this.modifiedate = modifiedate;
	}

	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getContents() {
		return this.contents;
	}

	public void setContents(Integer contents) {
		this.contents = contents;
	}

	public Integer getStick() {
		return this.stick;
	}

	public void setStick(Integer stick) {
		this.stick = stick;
	}

	public Integer getAllowcomment() {
		return this.allowcomment;
	}

	public void setAllowcomment(Integer allowcomment) {
		this.allowcomment = allowcomment;
	}

}