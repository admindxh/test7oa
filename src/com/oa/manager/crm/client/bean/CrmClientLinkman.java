package com.oa.manager.crm.client.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * CrmClientLinkman entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "crm_client_linkman")
public class CrmClientLinkman implements java.io.Serializable {

	// Fields

	private String id;
	//联系人姓名
	@NotEmpty 
	@Length(min=1,max=30,message="姓名长度在1-30之间。")
	private String liName;
	//客户ID
	@NotEmpty 
	@Length(max=32,message="客户名称长度32位")
	private String customerId;
	//联系人性别
	private String liSex;
	//联系人种类
	private String liSpecies; 
	//联系人类型
	private String liType;
	//联系人负责业务
	private String liBusiness;
	//联系人部门
	private String liDept;
	//联系人职务
	private String liPost;
	//联系人称谓
	private String liAppellation;
	//联系人电话
	private String liTelephone;
	//联系人邮箱
	private String liEmail;
	//联系人传真
	private String liFax;
	//联系人其他联系方式
	private String liMobilePhone;
	//联系人msn/qq
	private String liMsnQq;
	//联系人邮编
	private String liZipCode;
	//联系人家庭住址
	private String liHomeaddress;
	//联系人家庭电话
	private String liHomephone;
	//联系人出生日期
	private Timestamp liBirthday;
	//备注
	private String remark;
	//创建人
	private String createMan;
	//创建时间
	private Timestamp createtime;
	//最后修改人
	private String updateMan;
	//最后修改时间
	private Timestamp updatetime;

	// Constructors

	/** default constructor */
	public CrmClientLinkman() {
	}

	/** full constructor */
	public CrmClientLinkman(String liName, String customerId, String liSex,
			String liSpecies, String liType, String liBusiness, String liDept,
			String liPost, String liAppellation, String liTelephone,
			String liEmail, String liFax, String liMobilePhone, String liMsnQq,
			String liZipCode, String liHomeaddress, String liHomephone,
			Timestamp liBirthday, String remark, String createMan,
			Timestamp createtime, String updateMan, Timestamp updatetime) {
		this.liName = liName;
		this.customerId = customerId;
		this.liSex = liSex;
		this.liSpecies = liSpecies;
		this.liType = liType;
		this.liBusiness = liBusiness;
		this.liDept = liDept;
		this.liPost = liPost;
		this.liAppellation = liAppellation;
		this.liTelephone = liTelephone;
		this.liEmail = liEmail;
		this.liFax = liFax;
		this.liMobilePhone = liMobilePhone;
		this.liMsnQq = liMsnQq;
		this.liZipCode = liZipCode;
		this.liHomeaddress = liHomeaddress;
		this.liHomephone = liHomephone;
		this.liBirthday = liBirthday;
		this.remark = remark;
		this.createMan = createMan;
		this.createtime = createtime;
		this.updateMan = updateMan;
		this.updatetime = updatetime;
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

	@Column(name = "li_name", length = 30)
	public String getLiName() {
		return this.liName;
	}

	public void setLiName(String liName) {
		this.liName = liName;
	}

	@Column(name = "customerId", length = 32)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "li_sex", length = 1)
	public String getLiSex() {
		return this.liSex;
	}

	public void setLiSex(String liSex) {
		this.liSex = liSex;
	}

	@Column(name = "li_species", length = 32)
	public String getLiSpecies() {
		return this.liSpecies;
	}

	public void setLiSpecies(String liSpecies) {
		this.liSpecies = liSpecies;
	}

	@Column(name = "li_type", length = 32)
	public String getLiType() {
		return this.liType;
	}

	public void setLiType(String liType) {
		this.liType = liType;
	}

	@Column(name = "li_business", length = 30)
	public String getLiBusiness() {
		return this.liBusiness;
	}

	public void setLiBusiness(String liBusiness) {
		this.liBusiness = liBusiness;
	}

	@Column(name = "li_dept", length = 50)
	public String getLiDept() {
		return this.liDept;
	}

	public void setLiDept(String liDept) {
		this.liDept = liDept;
	}

	@Column(name = "li_post", length = 20)
	public String getLiPost() {
		return this.liPost;
	}

	public void setLiPost(String liPost) {
		this.liPost = liPost;
	}

	@Column(name = "li_appellation", length = 20)
	public String getLiAppellation() {
		return this.liAppellation;
	}

	public void setLiAppellation(String liAppellation) {
		this.liAppellation = liAppellation;
	}

	@Column(name = "li_telephone", length = 15)
	public String getLiTelephone() {
		return this.liTelephone;
	}

	public void setLiTelephone(String liTelephone) {
		this.liTelephone = liTelephone;
	}

	@Column(name = "li_email", length = 35)
	public String getLiEmail() {
		return this.liEmail;
	}

	public void setLiEmail(String liEmail) {
		this.liEmail = liEmail;
	}

	@Column(name = "li_fax", length = 15)
	public String getLiFax() {
		return this.liFax;
	}

	public void setLiFax(String liFax) {
		this.liFax = liFax;
	}

	@Column(name = "li_mobile_phone", length = 11)
	public String getLiMobilePhone() {
		return this.liMobilePhone;
	}

	public void setLiMobilePhone(String liMobilePhone) {
		this.liMobilePhone = liMobilePhone;
	}

	@Column(name = "li_msn_qq", length = 15)
	public String getLiMsnQq() {
		return this.liMsnQq;
	}

	public void setLiMsnQq(String liMsnQq) {
		this.liMsnQq = liMsnQq;
	}

	@Column(name = "li_zip_code", length = 8)
	public String getLiZipCode() {
		return this.liZipCode;
	}

	public void setLiZipCode(String liZipCode) {
		this.liZipCode = liZipCode;
	}

	@Column(name = "li_homeaddress", length = 50)
	public String getLiHomeaddress() {
		return this.liHomeaddress;
	}

	public void setLiHomeaddress(String liHomeaddress) {
		this.liHomeaddress = liHomeaddress;
	}

	@Column(name = "li_homephone", length = 15)
	public String getLiHomephone() {
		return this.liHomephone;
	}

	public void setLiHomephone(String liHomephone) {
		this.liHomephone = liHomephone;
	}

	@Column(name = "li_birthday", length = 19)
	public Timestamp getLiBirthday() {
		return this.liBirthday;
	}

	public void setLiBirthday(Timestamp liBirthday) {
		this.liBirthday = liBirthday;
	}

	@Column(name = "remark", length = 30)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "create_man", length = 32)
	public String getCreateMan() {
		return this.createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	@Column(name = "createtime", length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "update_man", length = 32)
	public String getUpdateMan() {
		return this.updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	@Column(name = "updatetime", length = 19)
	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}