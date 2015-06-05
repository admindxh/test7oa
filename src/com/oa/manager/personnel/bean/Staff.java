package com.oa.manager.personnel.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff  implements java.io.Serializable {


    // Fields    

     private String id;
     @NotEmpty 
 	@Length(min=1,max=50,message="姓名长度在1-50之间。")
     private String psName;
     private String psSex;
     private Date psBirthday;
     private Integer psAge;
     private String psPresentAddress;
     private String psHomePhone;
     private String psPhone;
     private String psOther;
     private String psAccountAddress;
     private String psEthnic;
     private String psCard;
     private String psHomeTown;
     private String psUserId;
     private String psStaffId;
     private String psWage;
     private String psSocial;
     private String psEdu;
     private String psSpecialty;
     private String psSchool;
     private Date psGraduation;
     private String psStatus;
     private String psType;
     private String psPost;
     private Date psEntryDate;
     private String psResReason;
     private Date psResDate;
     private String psResRemark;
     private String psRetReason;
     private String psRetRemark;
     private Date psRetDate;
     private String psUid;
     private Date psInDate;

     public Date staDate;//开始日期    页面使用
     public Date endDate;//结束日期
    // Constructors

    /** default constructor */
    public Staff() {
    }

    
    /** full constructor */
    public Staff(String psName, String psSex, Date psBirthday, Integer psAge, String psPresentAddress, String psHomePhone, String psPhone, String psOther, String psAccountAddress, String psEthnic, String psCard, String psHomeTown, String psUserId, String psStaffId, String psWage, String psSocial, String psEdu, String psSpecialty, String psSchool, Date psGraduation, String psStatus, String psType, String psPost, Date psEntryDate, String psResReason, Date psResDate, String psResRemark, String psRetReason, String psRetRemark, Date psRetDate, String psUid, Date psInDate) {
        this.psName = psName;
        this.psSex = psSex;
        this.psBirthday = psBirthday;
        this.psAge = psAge;
        this.psPresentAddress = psPresentAddress;
        this.psHomePhone = psHomePhone;
        this.psPhone = psPhone;
        this.psOther = psOther;
        this.psAccountAddress = psAccountAddress;
        this.psEthnic = psEthnic;
        this.psCard = psCard;
        this.psHomeTown = psHomeTown;
        this.psUserId = psUserId;
        this.psStaffId = psStaffId;
        this.psWage = psWage;
        this.psSocial = psSocial;
        this.psEdu = psEdu;
        this.psSpecialty = psSpecialty;
        this.psSchool = psSchool;
        this.psGraduation = psGraduation;
        this.psStatus = psStatus;
        this.psType = psType;
        this.psPost = psPost;
        this.psEntryDate = psEntryDate;
        this.psResReason = psResReason;
        this.psResDate = psResDate;
        this.psResRemark = psResRemark;
        this.psRetReason = psRetReason;
        this.psRetRemark = psRetRemark;
        this.psRetDate = psRetDate;
        this.psUid = psUid;
        this.psInDate = psInDate;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getPsName() {
        return this.psName;
    }
    
    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getPsSex() {
        return this.psSex;
    }
    
    public void setPsSex(String psSex) {
        this.psSex = psSex;
    }

    public Date getPsBirthday() {
        return this.psBirthday;
    }
    
    public void setPsBirthday(Date psBirthday) {
        this.psBirthday = psBirthday;
    }

    public Integer getPsAge() {
        return this.psAge;
    }
    
    public void setPsAge(Integer psAge) {
        this.psAge = psAge;
    }

    public String getPsPresentAddress() {
        return this.psPresentAddress;
    }
    
    public void setPsPresentAddress(String psPresentAddress) {
        this.psPresentAddress = psPresentAddress;
    }

    public String getPsHomePhone() {
        return this.psHomePhone;
    }
    
    public void setPsHomePhone(String psHomePhone) {
        this.psHomePhone = psHomePhone;
    }

    public String getPsPhone() {
        return this.psPhone;
    }
    
    public void setPsPhone(String psPhone) {
        this.psPhone = psPhone;
    }

    public String getPsOther() {
        return this.psOther;
    }
    
    public void setPsOther(String psOther) {
        this.psOther = psOther;
    }

    public String getPsAccountAddress() {
        return this.psAccountAddress;
    }
    
    public void setPsAccountAddress(String psAccountAddress) {
        this.psAccountAddress = psAccountAddress;
    }

    public String getPsEthnic() {
        return this.psEthnic;
    }
    
    public void setPsEthnic(String psEthnic) {
        this.psEthnic = psEthnic;
    }

    public String getPsCard() {
        return this.psCard;
    }
    
    public void setPsCard(String psCard) {
        this.psCard = psCard;
    }

    public String getPsHomeTown() {
        return this.psHomeTown;
    }
    
    public void setPsHomeTown(String psHomeTown) {
        this.psHomeTown = psHomeTown;
    }

    public String getPsUserId() {
        return this.psUserId;
    }
    
    public void setPsUserId(String psUserId) {
        this.psUserId = psUserId;
    }

    public String getPsStaffId() {
        return this.psStaffId;
    }
    
    public void setPsStaffId(String psStaffId) {
        this.psStaffId = psStaffId;
    }

    public String getPsWage() {
        return this.psWage;
    }
    
    public void setPsWage(String psWage) {
        this.psWage = psWage;
    }

    public String getPsSocial() {
        return this.psSocial;
    }
    
    public void setPsSocial(String psSocial) {
        this.psSocial = psSocial;
    }

    public String getPsEdu() {
        return this.psEdu;
    }
    
    public void setPsEdu(String psEdu) {
        this.psEdu = psEdu;
    }

    public String getPsSpecialty() {
        return this.psSpecialty;
    }
    
    public void setPsSpecialty(String psSpecialty) {
        this.psSpecialty = psSpecialty;
    }

    public String getPsSchool() {
        return this.psSchool;
    }
    
    public void setPsSchool(String psSchool) {
        this.psSchool = psSchool;
    }

    public Date getPsGraduation() {
        return this.psGraduation;
    }
    
    public void setPsGraduation(Date psGraduation) {
        this.psGraduation = psGraduation;
    }

    public String getPsStatus() {
        return this.psStatus;
    }
    
    public void setPsStatus(String psStatus) {
        this.psStatus = psStatus;
    }

    public String getPsType() {
        return this.psType;
    }
    
    public void setPsType(String psType) {
        this.psType = psType;
    }

    public String getPsPost() {
        return this.psPost;
    }
    
    public void setPsPost(String psPost) {
        this.psPost = psPost;
    }

    public Date getPsEntryDate() {
        return this.psEntryDate;
    }
    
    public void setPsEntryDate(Date psEntryDate) {
        this.psEntryDate = psEntryDate;
    }

    public String getPsResReason() {
        return this.psResReason;
    }
    
    public void setPsResReason(String psResReason) {
        this.psResReason = psResReason;
    }

    public Date getPsResDate() {
        return this.psResDate;
    }
    
    public void setPsResDate(Date psResDate) {
        this.psResDate = psResDate;
    }

    public String getPsResRemark() {
        return this.psResRemark;
    }
    
    public void setPsResRemark(String psResRemark) {
        this.psResRemark = psResRemark;
    }

    public String getPsRetReason() {
        return this.psRetReason;
    }
    
    public void setPsRetReason(String psRetReason) {
        this.psRetReason = psRetReason;
    }

    public String getPsRetRemark() {
        return this.psRetRemark;
    }
    
    public void setPsRetRemark(String psRetRemark) {
        this.psRetRemark = psRetRemark;
    }

    public Date getPsRetDate() {
        return this.psRetDate;
    }
    
    public void setPsRetDate(Date psRetDate) {
        this.psRetDate = psRetDate;
    }

    public String getPsUid() {
        return this.psUid;
    }
    
    public void setPsUid(String psUid) {
        this.psUid = psUid;
    }

    public Date getPsInDate() {
        return this.psInDate;
    }
    
    public void setPsInDate(Date psInDate) {
        this.psInDate = psInDate;
    }


	public Date getStaDate() {
		return staDate;
	}


	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
   








}