package com.oa.manager.files.bean;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

/**
 * 共享文档
 */

public class WdShareFiles implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 上级id
	 */
	private String wdSuperId;
	/**
	 * 文档名称
	 */
	@Length(min=1,max=50,message="名称在1-50之间。")
	private String wdName;
	/**
	 * 所属用户id
	 */
	private String wdUserId;
	/**
	 * 文档创建时间
	 */
	private Timestamp wdCreateTime;
	/**
	 * 文档类型，0：目录，1：文件
	 */
	private Short wdType;
	/**
	 * 文档说明
	 */
	@Length(min=0,max=50,message="备注长度不能大于50。")
	private String wdDesc;
	/**
	 * 文件保存时uuid
	 */
	private String wdNewName;
	/**
	 * 文件后缀
	 */
	private String wdExt;
	/**
	 * 文件大小，单位b
	 */
	private Integer wdSize;

	// Constructors

	/** default constructor */
	public WdShareFiles() {
	}

	/** minimal constructor */
	public WdShareFiles(String wdSuperId, String wdName, String wdUserId,
			Timestamp wdCreateTime, Short wdType) {
		this.wdSuperId = wdSuperId;
		this.wdName = wdName;
		this.wdUserId = wdUserId;
		this.wdCreateTime = wdCreateTime;
		this.wdType = wdType;
	}

	/** full constructor */
	public WdShareFiles(String wdSuperId, String wdName, String wdUserId,
			Timestamp wdCreateTime, Short wdType, String wdDesc,
			String wdNewName, String wdExt, Integer wdSize) {
		this.wdSuperId = wdSuperId;
		this.wdName = wdName;
		this.wdUserId = wdUserId;
		this.wdCreateTime = wdCreateTime;
		this.wdType = wdType;
		this.wdDesc = wdDesc;
		this.wdNewName = wdNewName;
		this.wdExt = wdExt;
		this.wdSize = wdSize;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWdSuperId() {
		return this.wdSuperId;
	}

	public void setWdSuperId(String wdSuperId) {
		this.wdSuperId = wdSuperId;
	}

	public String getWdName() {
		return this.wdName;
	}

	public void setWdName(String wdName) {
		this.wdName = wdName;
	}

	public String getWdUserId() {
		return this.wdUserId;
	}

	public void setWdUserId(String wdUserId) {
		this.wdUserId = wdUserId;
	}

	public Timestamp getWdCreateTime() {
		return this.wdCreateTime;
	}

	public void setWdCreateTime(Timestamp wdCreateTime) {
		this.wdCreateTime = wdCreateTime;
	}

	public Short getWdType() {
		return this.wdType;
	}

	public void setWdType(Short wdType) {
		this.wdType = wdType;
	}

	public String getWdDesc() {
		return this.wdDesc;
	}

	public void setWdDesc(String wdDesc) {
		this.wdDesc = wdDesc;
	}

	public String getWdNewName() {
		return this.wdNewName;
	}

	public void setWdNewName(String wdNewName) {
		this.wdNewName = wdNewName;
	}

	public String getWdExt() {
		return this.wdExt;
	}

	public void setWdExt(String wdExt) {
		this.wdExt = wdExt;
	}

	public Integer getWdSize() {
		return this.wdSize;
	}

	public void setWdSize(Integer wdSize) {
		this.wdSize = wdSize;
	}

}