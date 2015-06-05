package com.oa.manager.administration.bean;

import java.sql.Timestamp;

/**
 * 物品购买
 */

public class BiShopping implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 关联物品id
	 */
	private String usingnameId;
	/**
	 * 购买总数
	 */
	private Integer amount;
	/**
	 * 购买单价
	 */
	private Double unitprice;
	/**
	 * 购买总金额
	 */
	private Double totalmonety;
	/**
	 * 创建人
	 */
	private String userid;
	/**
	 * 创建时间
	 */
	private Timestamp createdate;
	/**
	 * 购买人id
	 */
	private String purchaser;
	/**
	 * 购买时间
	 */
	private Timestamp buyTime;
	

	// Constructors

	/** default constructor */
	public BiShopping() {
	}

	/** minimal constructor */
	public BiShopping(String usingnameId, String userid, Timestamp createdate) {
		this.usingnameId = usingnameId;
		this.userid = userid;
		this.createdate = createdate;
	}

	/** full constructor */
	public BiShopping(String usingnameId, Integer amount, Double unitprice,
			Double totalmonety, String userid, Timestamp createdate,
			String purchaser,Timestamp buyTime) {
		this.usingnameId = usingnameId;
		this.amount = amount;
		this.unitprice = unitprice;
		this.totalmonety = totalmonety;
		this.userid = userid;
		this.createdate = createdate;
		this.purchaser = purchaser;
		this.buyTime=buyTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsingnameId() {
		return this.usingnameId;
	}

	public void setUsingnameId(String usingnameId) {
		this.usingnameId = usingnameId;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public Double getTotalmonety() {
		return this.totalmonety;
	}

	public void setTotalmonety(Double totalmonety) {
		this.totalmonety = totalmonety;
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

	public String getPurchaser() {
		return this.purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}

}