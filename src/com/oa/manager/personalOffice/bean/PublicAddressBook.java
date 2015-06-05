package com.oa.manager.personalOffice.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Public entity. @author MyEclipse Persistence Tools
 */

public class PublicAddressBook implements java.io.Serializable {

	// Fields

	private String id;
	@NotEmpty 
	@Length(min=1,max=50,message="姓名长度在1-50之间。")
	private String puName;
	private String puSex;
	private String puEmail;
	private String puPhone;
	private String puOther;
	private String puGrouping;
	private String puCompany;
	private String puOfficPhone;
	private String puOfficFax;
	private String puCompanyAddress;
	private String puZip;
	private String puPost;
	private String puHomeAddress;
	private String puHomePhone;
	private String puRemark;

	// Constructors

	/** default constructor */
	public PublicAddressBook() {
	}

	/** full constructor */
	public PublicAddressBook(String puName, String puSex, String puEmail, String puPhone,
			String puOther, String puGrouping, String puCompany,
			String puOfficPhone, String puOfficFax, String puCompanyAddress,
			String puZip, String puPost, String puHomeAddress,
			String puHomePhone, String puRemark) {
		this.puName = puName;
		this.puSex = puSex;
		this.puEmail = puEmail;
		this.puPhone = puPhone;
		this.puOther = puOther;
		this.puGrouping = puGrouping;
		this.puCompany = puCompany;
		this.puOfficPhone = puOfficPhone;
		this.puOfficFax = puOfficFax;
		this.puCompanyAddress = puCompanyAddress;
		this.puZip = puZip;
		this.puPost = puPost;
		this.puHomeAddress = puHomeAddress;
		this.puHomePhone = puHomePhone;
		this.puRemark = puRemark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPuName() {
		return this.puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}

	public String getPuSex() {
		return this.puSex;
	}

	public void setPuSex(String puSex) {
		this.puSex = puSex;
	}

	public String getPuEmail() {
		return this.puEmail;
	}

	public void setPuEmail(String puEmail) {
		this.puEmail = puEmail;
	}

	public String getPuPhone() {
		return this.puPhone;
	}

	public void setPuPhone(String puPhone) {
		this.puPhone = puPhone;
	}

	public String getPuOther() {
		return this.puOther;
	}

	public void setPuOther(String puOther) {
		this.puOther = puOther;
	}

	public String getPuGrouping() {
		return this.puGrouping;
	}

	public void setPuGrouping(String puGrouping) {
		this.puGrouping = puGrouping;
	}

	public String getPuCompany() {
		return this.puCompany;
	}

	public void setPuCompany(String puCompany) {
		this.puCompany = puCompany;
	}

	public String getPuOfficPhone() {
		return this.puOfficPhone;
	}

	public void setPuOfficPhone(String puOfficPhone) {
		this.puOfficPhone = puOfficPhone;
	}

	public String getPuOfficFax() {
		return this.puOfficFax;
	}

	public void setPuOfficFax(String puOfficFax) {
		this.puOfficFax = puOfficFax;
	}

	public String getPuCompanyAddress() {
		return this.puCompanyAddress;
	}

	public void setPuCompanyAddress(String puCompanyAddress) {
		this.puCompanyAddress = puCompanyAddress;
	}

	public String getPuZip() {
		return this.puZip;
	}

	public void setPuZip(String puZip) {
		this.puZip = puZip;
	}

	public String getPuPost() {
		return this.puPost;
	}

	public void setPuPost(String puPost) {
		this.puPost = puPost;
	}

	public String getPuHomeAddress() {
		return this.puHomeAddress;
	}

	public void setPuHomeAddress(String puHomeAddress) {
		this.puHomeAddress = puHomeAddress;
	}

	public String getPuHomePhone() {
		return this.puHomePhone;
	}

	public void setPuHomePhone(String puHomePhone) {
		this.puHomePhone = puHomePhone;
	}

	public String getPuRemark() {
		return this.puRemark;
	}

	public void setPuRemark(String puRemark) {
		this.puRemark = puRemark;
	}

}