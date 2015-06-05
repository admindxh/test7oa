package com.oa.manager.crm.client.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Info entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "crm_client_info")
public class Info implements java.io.Serializable {

	// Fields

	private String id;
//	客户名称
	private String clName;
//	客户编号
	private String clNo;
//	客户类别
	private String clCategory;
//	客户类型
	private String clType;
//	客户来源
	private String clSource;
//	客户等级
	private String clGrade;
//	客户状态
	private String clStatus;
//	客户阶段
	private String clStaje;
//	客户关系
	private String clRelation;
//	负责人
	private String clStaff;
//	备注
	private String clRemark;
//	所属行业
	private String clIndustry;
//	行业描述
	private String clDescribe;
//	公司地址
	private String clAddress;
//	emali
	private String clEmail;
//	电话
	private String clPhone;
//	手机号码
	private String clPhoneNumeber;
//	传真
	private String clFax;
//	邮编
	private String clZip;
//	旺旺
	private String clWangwang;
//	QQ/MSN
	private String clQq;
//	网址
	private String clWebsite;
//	收货地址
	private String clReceipt;
//	开户银行
	private String clBank;
//	开户人
	private String clPeople;
//	银行账号
	private String clNumber;
//	创建人
	private String clCreate;
//	创建时间
	private Timestamp clCreateTime;
//	最后修改人
	private String clUptade;
//	最后修改时间
	private Timestamp clUpdateTime;
	

	// Constructors

	/** default constructor */
	public Info() {
	}

	/** full constructor */
	public Info(String clName, String clNo,String clCategory, String clType, String clSource,
			String clGrade, String clStatus, String clStaje, String clRelation,
			String clStaff, String clRemark, String clIndustry,
			String clDescribe, String clAddress, String clEmail,
			String clPhone, String clPhoneNumeber, String clFax, String clZip,
			String clWangwang, String clQq, String clWebsite, String clReceipt,
			String clBank, String clPeople, String clNumber, String clCreate,
			Timestamp clCreateTime, String clUptade, Timestamp clUpdateTime) {
		this.clName = clName;
		this.clNo = clNo;
		this.clCategory=clCategory;
		this.clType = clType;
		this.clSource = clSource;
		this.clGrade = clGrade;
		this.clStatus = clStatus;
		this.clStaje = clStaje;
		this.clRelation = clRelation;
		this.clStaff = clStaff;
		this.clRemark = clRemark;
		this.clIndustry = clIndustry;
		this.clDescribe = clDescribe;
		this.clAddress = clAddress;
		this.clEmail = clEmail;
		this.clPhone = clPhone;
		this.clPhoneNumeber = clPhoneNumeber;
		this.clFax = clFax;
		this.clZip = clZip;
		this.clWangwang = clWangwang;
		this.clQq = clQq;
		this.clWebsite = clWebsite;
		this.clReceipt = clReceipt;
		this.clBank = clBank;
		this.clPeople = clPeople;
		this.clNumber = clNumber;
		this.clCreate = clCreate;
		this.clCreateTime = clCreateTime;
		this.clUptade = clUptade;
		this.clUpdateTime = clUpdateTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "cl_name", length = 50)
	public String getClName() {
		return this.clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}

	@Column(name = "cl_no", length = 50)
	public String getClNo() {
		return this.clNo;
	}

	public void setClNo(String clNo) {
		this.clNo = clNo;
	}
	
	@Column(name = "cl_category", length = 32)
	public String getClCategory() {
		return this.clCategory;
	}

	public void setClCategory(String clCategory) {
		this.clCategory = clCategory;
	}

	@Column(name = "cl_type", length = 32)
	public String getClType() {
		return this.clType;
	}

	public void setClType(String clType) {
		this.clType = clType;
	}

	@Column(name = "cl_source", length = 32)
	public String getClSource() {
		return this.clSource;
	}

	public void setClSource(String clSource) {
		this.clSource = clSource;
	}

	@Column(name = "cl_grade", length = 32)
	public String getClGrade() {
		return this.clGrade;
	}

	public void setClGrade(String clGrade) {
		this.clGrade = clGrade;
	}

	@Column(name = "cl_status", length = 32)
	public String getClStatus() {
		return this.clStatus;
	}

	public void setClStatus(String clStatus) {
		this.clStatus = clStatus;
	}

	@Column(name = "cl_staje", length = 32)
	public String getClStaje() {
		return this.clStaje;
	}

	public void setClStaje(String clStaje) {
		this.clStaje = clStaje;
	}

	@Column(name = "cl_relation", length = 32)
	public String getClRelation() {
		return this.clRelation;
	}

	public void setClRelation(String clRelation) {
		this.clRelation = clRelation;
	}

	@Column(name = "cl_staff", length = 32)
	public String getClStaff() {
		return this.clStaff;
	}

	public void setClStaff(String clStaff) {
		this.clStaff = clStaff;
	}

	@Column(name = "cl_remark", length = 5000)
	public String getClRemark() {
		return this.clRemark;
	}

	public void setClRemark(String clRemark) {
		this.clRemark = clRemark;
	}

	@Column(name = "cl_industry", length = 32)
	public String getClIndustry() {
		return this.clIndustry;
	}

	public void setClIndustry(String clIndustry) {
		this.clIndustry = clIndustry;
	}

	@Column(name = "cl_describe", length = 5000)
	public String getClDescribe() {
		return this.clDescribe;
	}

	public void setClDescribe(String clDescribe) {
		this.clDescribe = clDescribe;
	}

	@Column(name = "cl_address", length = 100)
	public String getClAddress() {
		return this.clAddress;
	}

	public void setClAddress(String clAddress) {
		this.clAddress = clAddress;
	}

	@Column(name = "cl_email", length = 50)
	public String getClEmail() {
		return this.clEmail;
	}

	public void setClEmail(String clEmail) {
		this.clEmail = clEmail;
	}

	@Column(name = "cl_phone", length = 50)
	public String getClPhone() {
		return this.clPhone;
	}

	public void setClPhone(String clPhone) {
		this.clPhone = clPhone;
	}

	@Column(name = "cl_phoneNumeber", length = 50)
	public String getClPhoneNumeber() {
		return this.clPhoneNumeber;
	}

	public void setClPhoneNumeber(String clPhoneNumeber) {
		this.clPhoneNumeber = clPhoneNumeber;
	}

	@Column(name = "cl_fax", length = 50)
	public String getClFax() {
		return this.clFax;
	}

	public void setClFax(String clFax) {
		this.clFax = clFax;
	}

	@Column(name = "cl_zip", length = 50)
	public String getClZip() {
		return this.clZip;
	}

	public void setClZip(String clZip) {
		this.clZip = clZip;
	}

	@Column(name = "cl_wangwang", length = 50)
	public String getClWangwang() {
		return this.clWangwang;
	}

	public void setClWangwang(String clWangwang) {
		this.clWangwang = clWangwang;
	}

	@Column(name = "cl_qq", length = 50)
	public String getClQq() {
		return this.clQq;
	}

	public void setClQq(String clQq) {
		this.clQq = clQq;
	}

	@Column(name = "cl_website", length = 50)
	public String getClWebsite() {
		return this.clWebsite;
	}

	public void setClWebsite(String clWebsite) {
		this.clWebsite = clWebsite;
	}

	@Column(name = "cl_receipt", length = 100)
	public String getClReceipt() {
		return this.clReceipt;
	}

	public void setClReceipt(String clReceipt) {
		this.clReceipt = clReceipt;
	}

	@Column(name = "cl_bank", length = 100)
	public String getClBank() {
		return this.clBank;
	}

	public void setClBank(String clBank) {
		this.clBank = clBank;
	}

	@Column(name = "cl_people", length = 50)
	public String getClPeople() {
		return this.clPeople;
	}

	public void setClPeople(String clPeople) {
		this.clPeople = clPeople;
	}

	@Column(name = "cl_number", length = 50)
	public String getClNumber() {
		return this.clNumber;
	}

	public void setClNumber(String clNumber) {
		this.clNumber = clNumber;
	}

	@Column(name = "cl_create", length = 32)
	public String getClCreate() {
		return this.clCreate;
	}

	public void setClCreate(String clCreate) {
		this.clCreate = clCreate;
	}

	@Column(name = "cl_createTime", length = 19)
	public Timestamp getClCreateTime() {
		return this.clCreateTime;
	}

	public void setClCreateTime(Timestamp clCreateTime) {
		this.clCreateTime = clCreateTime;
	}

	@Column(name = "cl_uptade", length = 32)
	public String getClUptade() {
		return this.clUptade;
	}

	public void setClUptade(String clUptade) {
		this.clUptade = clUptade;
	}

	@Column(name = "cl_updateTime", length = 19)
	public Timestamp getClUpdateTime() {
		return this.clUpdateTime;
	}

	public void setClUpdateTime(Timestamp clUpdateTime) {
		this.clUpdateTime = clUpdateTime;
	}

}