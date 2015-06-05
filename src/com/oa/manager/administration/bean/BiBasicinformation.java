package com.oa.manager.administration.bean;

import java.sql.Timestamp;

/**
 * 物品信息
 */

public class BiBasicinformation implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 物品名称
	 */
	private String usingname;
	/**
	 * 类型
	 */
	private String typeId;
	/**
	 * 单位
	 */
	private String danwei;
	/**
	 * 库存
	 */
	private Integer currentinventory;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人
	 */
	private String userid;
	/**
	 * 创建时间
	 */
	private Timestamp createdate;
	/**
	 * 规格型号
	 */
	private String specificationstype;
	

	// Constructors

	/** default constructor */
	public BiBasicinformation() {
	}

	/** minimal constructor */
	public BiBasicinformation(String userid, Timestamp createdate) {
		this.userid = userid;
		this.createdate = createdate;
	}

	/** full constructor */
	public BiBasicinformation(String usingname, String typeId, String danwei,
			Integer currentinventory, String remark, String userid,
			Timestamp createdate, String specificationstype) {
		this.usingname = usingname;
		this.typeId = typeId;
		this.danwei = danwei;
		this.currentinventory = currentinventory;
		this.remark = remark;
		this.userid = userid;
		this.createdate = createdate;
		this.specificationstype = specificationstype;
		
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsingname() {
		return this.usingname;
	}

	public void setUsingname(String usingname) {
		this.usingname = usingname;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDanwei() {
		return this.danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public Integer getCurrentinventory() {
		return this.currentinventory;
	}

	public void setCurrentinventory(Integer currentinventory) {
		this.currentinventory = currentinventory;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getSpecificationstype() {
		return this.specificationstype;
	}

	public void setSpecificationstype(String specificationstype) {
		this.specificationstype = specificationstype;
	}

	

}