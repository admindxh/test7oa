package com.oa.manager.crm.sell.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * CrmSellActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "crm_sell_activity")
public class CrmSellActivity implements java.io.Serializable {

	// Fields

	private String id;
	//活动主题
	@NotEmpty 
	@Length(min=1,max=50,message="主题长度在1-50之间。")
	private String acTitle;
	//活动方式
	private String acType;
	//活动时间
	private Timestamp acTime;
	//单位（客户）
	@NotBlank
	private String acDanwei;
	//联系人(存名字，可选可填)
	@NotBlank
	private String acContacts;
	//活动详情
	private String acDetail;
	//我方联系人（存名字可选可填）
	private String acOurStaff;
	//活动地点
	private String acPlace;
	//持续时间
	private String acDuration;
	//花费
	private Float acSpend;
	//赠送礼品
	private String acGiveGift;
	//礼品数量
	private Integer acGiftNum;
	//重要性(0一般1重要2非常重要)
	private String acImportance;
	//销售机会ID
	private String chanceid;
	//创建人
	private String acCreateMan;
	//创建时间
	private Timestamp acCreateTime;

	// Constructors

	/** default constructor */
	public CrmSellActivity() {
	}

	/** minimal constructor */
	public CrmSellActivity(String acTitle, String acDanwei, String acContacts,
			String acOurStaff, String acCreateMan, Timestamp acCreateTime) {
		this.acTitle = acTitle;
		this.acDanwei = acDanwei;
		this.acContacts = acContacts;
		this.acOurStaff = acOurStaff;
		this.acCreateMan = acCreateMan;
		this.acCreateTime = acCreateTime;
	}

	/** full constructor */
	public CrmSellActivity(String acTitle, String acType, Timestamp acTime,
			String acDanwei, String acContacts, String acDetail,
			String acOurStaff, String acPlace, String acDuration,
			Float acSpend, String acGiveGift, Integer acGiftNum,
			String acImportance, String chanceid, String acCreateMan,
			Timestamp acCreateTime) {
		this.acTitle = acTitle;
		this.acType = acType;
		this.acTime = acTime;
		this.acDanwei = acDanwei;
		this.acContacts = acContacts;
		this.acDetail = acDetail;
		this.acOurStaff = acOurStaff;
		this.acPlace = acPlace;
		this.acDuration = acDuration;
		this.acSpend = acSpend;
		this.acGiveGift = acGiveGift;
		this.acGiftNum = acGiftNum;
		this.acImportance = acImportance;
		this.chanceid = chanceid;
		this.acCreateMan = acCreateMan;
		this.acCreateTime = acCreateTime;
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

	@Column(name = "ac_title", nullable = false, length = 50)
	public String getAcTitle() {
		return this.acTitle;
	}

	public void setAcTitle(String acTitle) {
		this.acTitle = acTitle;
	}

	@Column(name = "ac_type", length = 32)
	public String getAcType() {
		return this.acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	@Column(name = "ac_time", length = 19)
	public Timestamp getAcTime() {
		return this.acTime;
	}

	public void setAcTime(Timestamp acTime) {
		this.acTime = acTime;
	}

	@Column(name = "ac_danwei", nullable = false, length = 32)
	public String getAcDanwei() {
		return this.acDanwei;
	}

	public void setAcDanwei(String acDanwei) {
		this.acDanwei = acDanwei;
	}

	@Column(name = "ac_contacts", nullable = false, length = 30)
	public String getAcContacts() {
		return this.acContacts;
	}

	public void setAcContacts(String acContacts) {
		this.acContacts = acContacts;
	}

	@Column(name = "ac_detail", length = 500)
	public String getAcDetail() {
		return this.acDetail;
	}

	public void setAcDetail(String acDetail) {
		this.acDetail = acDetail;
	}

	@Column(name = "ac_our_staff", nullable = false, length = 30)
	public String getAcOurStaff() {
		return this.acOurStaff;
	}

	public void setAcOurStaff(String acOurStaff) {
		this.acOurStaff = acOurStaff;
	}

	@Column(name = "ac_place", length = 50)
	public String getAcPlace() {
		return this.acPlace;
	}

	public void setAcPlace(String acPlace) {
		this.acPlace = acPlace;
	}

	@Column(name = "ac_duration", length = 5)
	public String getAcDuration() {
		return this.acDuration;
	}

	public void setAcDuration(String acDuration) {
		this.acDuration = acDuration;
	}

	@Column(name = "ac_spend", precision = 10)
	public Float getAcSpend() {
		return this.acSpend;
	}

	public void setAcSpend(Float acSpend) {
		this.acSpend = acSpend;
	}

	@Column(name = "ac_give_gift", length = 30)
	public String getAcGiveGift() {
		return this.acGiveGift;
	}

	public void setAcGiveGift(String acGiveGift) {
		this.acGiveGift = acGiveGift;
	}

	@Column(name = "ac_gift_num")
	public Integer getAcGiftNum() {
		return this.acGiftNum;
	}

	public void setAcGiftNum(Integer acGiftNum) {
		this.acGiftNum = acGiftNum;
	}

	@Column(name = "ac_importance", length = 1)
	public String getAcImportance() {
		return this.acImportance;
	}

	public void setAcImportance(String acImportance) {
		this.acImportance = acImportance;
	}

	@Column(name = "chanceid", length = 32)
	public String getChanceid() {
		return this.chanceid;
	}

	public void setChanceid(String chanceid) {
		this.chanceid = chanceid;
	}

	@Column(name = "ac_create_man", nullable = false, length = 32)
	public String getAcCreateMan() {
		return this.acCreateMan;
	}

	public void setAcCreateMan(String acCreateMan) {
		this.acCreateMan = acCreateMan;
	}

	@Column(name = "ac_create_time", nullable = false, length = 19)
	public Timestamp getAcCreateTime() {
		return this.acCreateTime;
	}

	public void setAcCreateTime(Timestamp acCreateTime) {
		this.acCreateTime = acCreateTime;
	}

}