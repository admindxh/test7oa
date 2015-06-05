package com.oa.manager.personalOffice.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Personal entity. @author MyEclipse Persistence Tools
 */

public class PersonalAddressBook implements java.io.Serializable {

	// Fields

	private String id;
	@NotEmpty 
	@Length(min=1,max=50,message="姓名长度在1-50之间。")
	private String peName;
	private String peSex;
	private String peEmail;
	private String pePhone;
	private String peOther;
	private String peGrouping;
	private String peCompany;
	private String peOfficPhone;
	private String peOfficFax;
	private String peCompanyAddress;
	private String peZip;
	private String pePost;
	private String peHomeAddress;
	private String peHomePhone;
	private String peRemark;
	private String peUid;
	// Constructors

	/** default constructor */
	public PersonalAddressBook() {
	}

	/** full constructor */
	public PersonalAddressBook(String peName, String peSex, String peEmail,
			String pePhone, String peOther, String peGrouping,
			String peCompany, String peOfficPhone, String peOfficFax,
			String peCompanyAddress, String peZip, String pePost,
			String peHomeAddress, String peHomePhone, String peRemark,String peUid) {
		this.peName = peName;
		this.peSex = peSex;
		this.peEmail = peEmail;
		this.pePhone = pePhone;
		this.peOther = peOther;
		this.peGrouping = peGrouping;
		this.peCompany = peCompany;
		this.peOfficPhone = peOfficPhone;
		this.peOfficFax = peOfficFax;
		this.peCompanyAddress = peCompanyAddress;
		this.peZip = peZip;
		this.pePost = pePost;
		this.peHomeAddress = peHomeAddress;
		this.peHomePhone = peHomePhone;
		this.peRemark = peRemark;
		this.peUid=peUid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeName() {
		return this.peName;
	}

	public void setPeName(String peName) {
		this.peName = peName;
	}

	public String getPeSex() {
		return this.peSex;
	}

	public void setPeSex(String peSex) {
		this.peSex = peSex;
	}

	public String getPeEmail() {
		return this.peEmail;
	}

	public void setPeEmail(String peEmail) {
		this.peEmail = peEmail;
	}

	public String getPePhone() {
		return this.pePhone;
	}

	public void setPePhone(String pePhone) {
		this.pePhone = pePhone;
	}

	public String getPeOther() {
		return this.peOther;
	}

	public void setPeOther(String peOther) {
		this.peOther = peOther;
	}

	public String getPeGrouping() {
		return this.peGrouping;
	}

	public void setPeGrouping(String peGrouping) {
		this.peGrouping = peGrouping;
	}

	public String getPeCompany() {
		return this.peCompany;
	}

	public void setPeCompany(String peCompany) {
		this.peCompany = peCompany;
	}

	public String getPeOfficPhone() {
		return this.peOfficPhone;
	}

	public void setPeOfficPhone(String peOfficPhone) {
		this.peOfficPhone = peOfficPhone;
	}

	public String getPeOfficFax() {
		return this.peOfficFax;
	}

	public void setPeOfficFax(String peOfficFax) {
		this.peOfficFax = peOfficFax;
	}

	public String getPeCompanyAddress() {
		return this.peCompanyAddress;
	}

	public void setPeCompanyAddress(String peCompanyAddress) {
		this.peCompanyAddress = peCompanyAddress;
	}

	public String getPeZip() {
		return this.peZip;
	}

	public void setPeZip(String peZip) {
		this.peZip = peZip;
	}

	public String getPePost() {
		return this.pePost;
	}

	public void setPePost(String pePost) {
		this.pePost = pePost;
	}

	public String getPeHomeAddress() {
		return this.peHomeAddress;
	}

	public void setPeHomeAddress(String peHomeAddress) {
		this.peHomeAddress = peHomeAddress;
	}

	public String getPeHomePhone() {
		return this.peHomePhone;
	}

	public void setPeHomePhone(String peHomePhone) {
		this.peHomePhone = peHomePhone;
	}

	public String getPeRemark() {
		return this.peRemark;
	}

	public void setPeRemark(String peRemark) {
		this.peRemark = peRemark;
	}

	public String getPeUid() {
		return peUid;
	}

	public void setPeUid(String peUid) {
		this.peUid = peUid;
	}

}