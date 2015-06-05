package com.oa.manager.workFlow.bean;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * WfForm entity. @author MyEclipse Persistence Tools
 */

public class WfForm implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 表单类型
	 * 1:工作表单，2：任务表单
	 */
	@NotNull(message="表单类型不能为空")
	@Range(min=1,max=2,message="表单类型格式错误")
	private Short type;
	/**
	 * 表单名称
	 */
	@NotBlank(message="表单名称不能为空")
	@Length(min=1,max=50,message="表单名称不能超过50个字符。")
	private String formName;
	/**
	 * 最后更新时间
	 */
	private Timestamp updateTime;
	/**
	 * 最后更新用户id
	 */
	private String updateUserId;
	/**
	 * 获取任务处理页面url
	 */
	@NotBlank(message="url不能为空")
	@Length(min=1,max=255,message="url不能超过255个字符。")
	private String completePageUrl;
	/**
	 * 提交 任务处理 url
	 * 
	 */
	@Length(min=0,max=255,message="url不能超过255个字符。")
	private String completeUrl;
	
	/**
	 * 表单内容
	 */
	private String formHtml;
	/**
	 * 表单描述
	 */
	private String formDesc;

	// Constructors

	/** default constructor */
	public WfForm() {
	}

	/** full constructor */
	public WfForm(Short type, String formName, Timestamp updateTime,
			String updateUserId, String completePageUrl,String completeUrl, String formHtml,
			String formDesc) {
		this.type = type;
		this.formName = formName;
		this.updateTime = updateTime;
		this.updateUserId = updateUserId;
		this.completePageUrl = completePageUrl;
		this.completeUrl=completeUrl;
		this.formHtml = formHtml;
		this.formDesc = formDesc;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	

	public String getFormHtml() {
		return this.formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}

	public String getFormDesc() {
		return this.formDesc;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	public String getCompletePageUrl() {
		return completePageUrl;
	}

	public void setCompletePageUrl(String completePageUrl) {
		this.completePageUrl = completePageUrl;
	}

	public String getCompleteUrl() {
		return completeUrl;
	}

	public void setCompleteUrl(String completeUrl) {
		this.completeUrl = completeUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}