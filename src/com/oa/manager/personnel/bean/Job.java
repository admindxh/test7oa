package com.oa.manager.personnel.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pe_job")
public class Job implements java.io.Serializable {

	// Fields

	private String id;
	@Length(min=1,max=50,message="姓名长度在1-50之间。")
	private String pjName;
	private String pjSex;
	private String pjBirthdate;
	private String pjIdNo;
	private String pjEthnic;
	private String pjMarital;
	private String pjPhone;
	private String pjHeight;
	private String pjQq;
	private String pjEducation;
	private String pjDegree;
	private String pjExperience;
	private String pjAddress;
	private String pjHometown;
	private String pjPost;
	private String pjSalary;
	private String pjSkill;
	private String pjJobStatus;
	private String pjEduTime1;
	private String pjSchool1;
	private String pjSpecialty1;
	private String pjEducation1;
	private String pjEduTime2;
	private String pjSchool2;
	private String pjSpecialty2;
	private String pjEducation2;
	private String pjEduTime3;
	private String pjSchool3;
	private String pjSpecialty3;
	private String pjEducation3;
	private String pjWorkTime1;
	private String pjCompany1;
	private String pjPosition1;
	private String pjSalary1;
	private String pjLeave1;
	private String pjWorkTime2;
	private String pjCompany2;
	private String pjPosition2;
	private String pjSalary2;
	private String pjLeave2;
	private String pjWorkTime3;
	private String pjCompany3;
	private String pjPosition3;
	private String pjSalary3;
	private String pjLeave3;
	private String pjWorkTime4;
	private String pjCompany4;
	private String pjPosition4;
	private String pjSalary4;
	private String pjLeave4;
	private String pjWorkTime5;
	private String pjCompany5;
	private String pjPosition5;
	private String pjSalary5;
	private String pjLeave5;
	private String pjCall1;
	private String pjName1;
	private String pjUnit1;
	private String pjResidence1;
	private String pjPhone1;
	private String pjCall2;
	private String pjName2;
	private String pjUnit2;
	private String pjResidence2;
	private String pjPhone2;
	private String pjCall3;
	private String pjName3;
	private String pjUnit3;
	private String pjResidence3;
	private String pjPhone3;
	private String pjProject;
	private String pjPlan;
	private String pjEvaluate;
	private String pjResult;
	private Date pjDate;

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** full constructor */
	public Job(String pjName, String pjSex, String pjBirthdate, String pjIdNo,
			String pjEthnic, String pjMarital, String pjPhone, String pjHeight,
			String pjQq, String pjEducation, String pjDegree,
			String pjExperience, String pjAddress, String pjHometown,
			String pjPost, String pjSalary, String pjSkill, String pjJobStatus,
			String pjEduTime1, String pjSchool1, String pjSpecialty1,
			String pjEducation1, String pjEduTime2, String pjSchool2,
			String pjSpecialty2, String pjEducation2, String pjEduTime3,
			String pjSchool3, String pjSpecialty3, String pjEducation3,
			String pjWorkTime1, String pjCompany1, String pjPosition1,
			String pjSalary1, String pjLeave1, String pjWorkTime2,
			String pjCompany2, String pjPosition2, String pjSalary2,
			String pjLeave2, String pjWorkTime3, String pjCompany3,
			String pjPosition3, String pjSalary3, String pjLeave3,
			String pjWorkTime4, String pjCompany4, String pjPosition4,
			String pjSalary4, String pjLeave4, String pjWorkTime5,
			String pjCompany5, String pjPosition5, String pjSalary5,
			String pjLeave5, String pjCall1, String pjName1, String pjUnit1,
			String pjResidence1, String pjPhone1, String pjCall2,
			String pjName2, String pjUnit2, String pjResidence2,
			String pjPhone2, String pjCall3, String pjName3, String pjUnit3,
			String pjResidence3, String pjPhone3, String pjProject,
			String pjPlan, String pjEvaluate, String pjResult, Date pjDate) {
		this.pjName = pjName;
		this.pjSex = pjSex;
		this.pjBirthdate = pjBirthdate;
		this.pjIdNo = pjIdNo;
		this.pjEthnic = pjEthnic;
		this.pjMarital = pjMarital;
		this.pjPhone = pjPhone;
		this.pjHeight = pjHeight;
		this.pjQq = pjQq;
		this.pjEducation = pjEducation;
		this.pjDegree = pjDegree;
		this.pjExperience = pjExperience;
		this.pjAddress = pjAddress;
		this.pjHometown = pjHometown;
		this.pjPost = pjPost;
		this.pjSalary = pjSalary;
		this.pjSkill = pjSkill;
		this.pjJobStatus = pjJobStatus;
		this.pjEduTime1 = pjEduTime1;
		this.pjSchool1 = pjSchool1;
		this.pjSpecialty1 = pjSpecialty1;
		this.pjEducation1 = pjEducation1;
		this.pjEduTime2 = pjEduTime2;
		this.pjSchool2 = pjSchool2;
		this.pjSpecialty2 = pjSpecialty2;
		this.pjEducation2 = pjEducation2;
		this.pjEduTime3 = pjEduTime3;
		this.pjSchool3 = pjSchool3;
		this.pjSpecialty3 = pjSpecialty3;
		this.pjEducation3 = pjEducation3;
		this.pjWorkTime1 = pjWorkTime1;
		this.pjCompany1 = pjCompany1;
		this.pjPosition1 = pjPosition1;
		this.pjSalary1 = pjSalary1;
		this.pjLeave1 = pjLeave1;
		this.pjWorkTime2 = pjWorkTime2;
		this.pjCompany2 = pjCompany2;
		this.pjPosition2 = pjPosition2;
		this.pjSalary2 = pjSalary2;
		this.pjLeave2 = pjLeave2;
		this.pjWorkTime3 = pjWorkTime3;
		this.pjCompany3 = pjCompany3;
		this.pjPosition3 = pjPosition3;
		this.pjSalary3 = pjSalary3;
		this.pjLeave3 = pjLeave3;
		this.pjWorkTime4 = pjWorkTime4;
		this.pjCompany4 = pjCompany4;
		this.pjPosition4 = pjPosition4;
		this.pjSalary4 = pjSalary4;
		this.pjLeave4 = pjLeave4;
		this.pjWorkTime5 = pjWorkTime5;
		this.pjCompany5 = pjCompany5;
		this.pjPosition5 = pjPosition5;
		this.pjSalary5 = pjSalary5;
		this.pjLeave5 = pjLeave5;
		this.pjCall1 = pjCall1;
		this.pjName1 = pjName1;
		this.pjUnit1 = pjUnit1;
		this.pjResidence1 = pjResidence1;
		this.pjPhone1 = pjPhone1;
		this.pjCall2 = pjCall2;
		this.pjName2 = pjName2;
		this.pjUnit2 = pjUnit2;
		this.pjResidence2 = pjResidence2;
		this.pjPhone2 = pjPhone2;
		this.pjCall3 = pjCall3;
		this.pjName3 = pjName3;
		this.pjUnit3 = pjUnit3;
		this.pjResidence3 = pjResidence3;
		this.pjPhone3 = pjPhone3;
		this.pjProject = pjProject;
		this.pjPlan = pjPlan;
		this.pjEvaluate = pjEvaluate;
		this.pjResult = pjResult;
		this.pjDate = pjDate;
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

	@Column(name = "pj_name", length = 50)
	public String getPjName() {
		return this.pjName;
	}

	public void setPjName(String pjName) {
		this.pjName = pjName;
	}

	@Column(name = "pj_sex", length = 50)
	public String getPjSex() {
		return this.pjSex;
	}

	public void setPjSex(String pjSex) {
		this.pjSex = pjSex;
	}

	@Column(name = "pj_birthdate", length = 50)
	public String getPjBirthdate() {
		return this.pjBirthdate;
	}

	public void setPjBirthdate(String pjBirthdate) {
		this.pjBirthdate = pjBirthdate;
	}

	@Column(name = "pj_idNo", length = 18)
	public String getPjIdNo() {
		return this.pjIdNo;
	}

	public void setPjIdNo(String pjIdNo) {
		this.pjIdNo = pjIdNo;
	}

	@Column(name = "pj_ethnic", length = 50)
	public String getPjEthnic() {
		return this.pjEthnic;
	}

	public void setPjEthnic(String pjEthnic) {
		this.pjEthnic = pjEthnic;
	}

	@Column(name = "pj_marital", length = 50)
	public String getPjMarital() {
		return this.pjMarital;
	}

	public void setPjMarital(String pjMarital) {
		this.pjMarital = pjMarital;
	}

	@Column(name = "pj_phone", length = 50)
	public String getPjPhone() {
		return this.pjPhone;
	}

	public void setPjPhone(String pjPhone) {
		this.pjPhone = pjPhone;
	}

	@Column(name = "pj_height", length = 50)
	public String getPjHeight() {
		return this.pjHeight;
	}

	public void setPjHeight(String pjHeight) {
		this.pjHeight = pjHeight;
	}

	@Column(name = "pj_qq", length = 50)
	public String getPjQq() {
		return this.pjQq;
	}

	public void setPjQq(String pjQq) {
		this.pjQq = pjQq;
	}

	@Column(name = "pj_education", length = 50)
	public String getPjEducation() {
		return this.pjEducation;
	}

	public void setPjEducation(String pjEducation) {
		this.pjEducation = pjEducation;
	}

	@Column(name = "pj_degree", length = 50)
	public String getPjDegree() {
		return this.pjDegree;
	}

	public void setPjDegree(String pjDegree) {
		this.pjDegree = pjDegree;
	}

	@Column(name = "pj_experience", length = 50)
	public String getPjExperience() {
		return this.pjExperience;
	}

	public void setPjExperience(String pjExperience) {
		this.pjExperience = pjExperience;
	}

	@Column(name = "pj_address", length = 500)
	public String getPjAddress() {
		return this.pjAddress;
	}

	public void setPjAddress(String pjAddress) {
		this.pjAddress = pjAddress;
	}

	@Column(name = "pj_hometown", length = 500)
	public String getPjHometown() {
		return this.pjHometown;
	}

	public void setPjHometown(String pjHometown) {
		this.pjHometown = pjHometown;
	}

	@Column(name = "pj_post", length = 50)
	public String getPjPost() {
		return this.pjPost;
	}

	public void setPjPost(String pjPost) {
		this.pjPost = pjPost;
	}

	@Column(name = "pj_salary", length = 50)
	public String getPjSalary() {
		return this.pjSalary;
	}

	public void setPjSalary(String pjSalary) {
		this.pjSalary = pjSalary;
	}

	@Column(name = "pj_skill", length = 500)
	public String getPjSkill() {
		return this.pjSkill;
	}

	public void setPjSkill(String pjSkill) {
		this.pjSkill = pjSkill;
	}

	@Column(name = "pj_jobStatus", length = 50)
	public String getPjJobStatus() {
		return this.pjJobStatus;
	}

	public void setPjJobStatus(String pjJobStatus) {
		this.pjJobStatus = pjJobStatus;
	}

	@Column(name = "pj_eduTime1", length = 50)
	public String getPjEduTime1() {
		return this.pjEduTime1;
	}

	public void setPjEduTime1(String pjEduTime1) {
		this.pjEduTime1 = pjEduTime1;
	}

	@Column(name = "pj_school1", length = 50)
	public String getPjSchool1() {
		return this.pjSchool1;
	}

	public void setPjSchool1(String pjSchool1) {
		this.pjSchool1 = pjSchool1;
	}

	@Column(name = "pj_specialty1", length = 50)
	public String getPjSpecialty1() {
		return this.pjSpecialty1;
	}

	public void setPjSpecialty1(String pjSpecialty1) {
		this.pjSpecialty1 = pjSpecialty1;
	}

	@Column(name = "pj_education1", length = 50)
	public String getPjEducation1() {
		return this.pjEducation1;
	}

	public void setPjEducation1(String pjEducation1) {
		this.pjEducation1 = pjEducation1;
	}

	@Column(name = "pj_eduTime2", length = 50)
	public String getPjEduTime2() {
		return this.pjEduTime2;
	}

	public void setPjEduTime2(String pjEduTime2) {
		this.pjEduTime2 = pjEduTime2;
	}

	@Column(name = "pj_school2", length = 50)
	public String getPjSchool2() {
		return this.pjSchool2;
	}

	public void setPjSchool2(String pjSchool2) {
		this.pjSchool2 = pjSchool2;
	}

	@Column(name = "pj_specialty2", length = 50)
	public String getPjSpecialty2() {
		return this.pjSpecialty2;
	}

	public void setPjSpecialty2(String pjSpecialty2) {
		this.pjSpecialty2 = pjSpecialty2;
	}

	@Column(name = "pj_education2", length = 50)
	public String getPjEducation2() {
		return this.pjEducation2;
	}

	public void setPjEducation2(String pjEducation2) {
		this.pjEducation2 = pjEducation2;
	}

	@Column(name = "pj_eduTime3", length = 50)
	public String getPjEduTime3() {
		return this.pjEduTime3;
	}

	public void setPjEduTime3(String pjEduTime3) {
		this.pjEduTime3 = pjEduTime3;
	}

	@Column(name = "pj_school3", length = 50)
	public String getPjSchool3() {
		return this.pjSchool3;
	}

	public void setPjSchool3(String pjSchool3) {
		this.pjSchool3 = pjSchool3;
	}

	@Column(name = "pj_specialty3", length = 50)
	public String getPjSpecialty3() {
		return this.pjSpecialty3;
	}

	public void setPjSpecialty3(String pjSpecialty3) {
		this.pjSpecialty3 = pjSpecialty3;
	}

	@Column(name = "pj_education3", length = 50)
	public String getPjEducation3() {
		return this.pjEducation3;
	}

	public void setPjEducation3(String pjEducation3) {
		this.pjEducation3 = pjEducation3;
	}

	@Column(name = "pj_workTime1", length = 50)
	public String getPjWorkTime1() {
		return this.pjWorkTime1;
	}

	public void setPjWorkTime1(String pjWorkTime1) {
		this.pjWorkTime1 = pjWorkTime1;
	}

	@Column(name = "pj_company1", length = 50)
	public String getPjCompany1() {
		return this.pjCompany1;
	}

	public void setPjCompany1(String pjCompany1) {
		this.pjCompany1 = pjCompany1;
	}

	@Column(name = "pj_position1", length = 50)
	public String getPjPosition1() {
		return this.pjPosition1;
	}

	public void setPjPosition1(String pjPosition1) {
		this.pjPosition1 = pjPosition1;
	}

	@Column(name = "pj_salary1", length = 50)
	public String getPjSalary1() {
		return this.pjSalary1;
	}

	public void setPjSalary1(String pjSalary1) {
		this.pjSalary1 = pjSalary1;
	}

	@Column(name = "pj_leave1", length = 500)
	public String getPjLeave1() {
		return this.pjLeave1;
	}

	public void setPjLeave1(String pjLeave1) {
		this.pjLeave1 = pjLeave1;
	}

	@Column(name = "pj_workTime2", length = 50)
	public String getPjWorkTime2() {
		return this.pjWorkTime2;
	}

	public void setPjWorkTime2(String pjWorkTime2) {
		this.pjWorkTime2 = pjWorkTime2;
	}

	@Column(name = "pj_company2", length = 50)
	public String getPjCompany2() {
		return this.pjCompany2;
	}

	public void setPjCompany2(String pjCompany2) {
		this.pjCompany2 = pjCompany2;
	}

	@Column(name = "pj_position2", length = 50)
	public String getPjPosition2() {
		return this.pjPosition2;
	}

	public void setPjPosition2(String pjPosition2) {
		this.pjPosition2 = pjPosition2;
	}

	@Column(name = "pj_salary2", length = 50)
	public String getPjSalary2() {
		return this.pjSalary2;
	}

	public void setPjSalary2(String pjSalary2) {
		this.pjSalary2 = pjSalary2;
	}

	@Column(name = "pj_leave2", length = 500)
	public String getPjLeave2() {
		return this.pjLeave2;
	}

	public void setPjLeave2(String pjLeave2) {
		this.pjLeave2 = pjLeave2;
	}

	@Column(name = "pj_workTime3", length = 50)
	public String getPjWorkTime3() {
		return this.pjWorkTime3;
	}

	public void setPjWorkTime3(String pjWorkTime3) {
		this.pjWorkTime3 = pjWorkTime3;
	}

	@Column(name = "pj_company3", length = 50)
	public String getPjCompany3() {
		return this.pjCompany3;
	}

	public void setPjCompany3(String pjCompany3) {
		this.pjCompany3 = pjCompany3;
	}

	@Column(name = "pj_position3", length = 50)
	public String getPjPosition3() {
		return this.pjPosition3;
	}

	public void setPjPosition3(String pjPosition3) {
		this.pjPosition3 = pjPosition3;
	}

	@Column(name = "pj_salary3", length = 50)
	public String getPjSalary3() {
		return this.pjSalary3;
	}

	public void setPjSalary3(String pjSalary3) {
		this.pjSalary3 = pjSalary3;
	}

	@Column(name = "pj_leave3", length = 500)
	public String getPjLeave3() {
		return this.pjLeave3;
	}

	public void setPjLeave3(String pjLeave3) {
		this.pjLeave3 = pjLeave3;
	}

	@Column(name = "pj_workTime4", length = 50)
	public String getPjWorkTime4() {
		return this.pjWorkTime4;
	}

	public void setPjWorkTime4(String pjWorkTime4) {
		this.pjWorkTime4 = pjWorkTime4;
	}

	@Column(name = "pj_company4", length = 50)
	public String getPjCompany4() {
		return this.pjCompany4;
	}

	public void setPjCompany4(String pjCompany4) {
		this.pjCompany4 = pjCompany4;
	}

	@Column(name = "pj_position4", length = 50)
	public String getPjPosition4() {
		return this.pjPosition4;
	}

	public void setPjPosition4(String pjPosition4) {
		this.pjPosition4 = pjPosition4;
	}

	@Column(name = "pj_salary4", length = 50)
	public String getPjSalary4() {
		return this.pjSalary4;
	}

	public void setPjSalary4(String pjSalary4) {
		this.pjSalary4 = pjSalary4;
	}

	@Column(name = "pj_leave4", length = 500)
	public String getPjLeave4() {
		return this.pjLeave4;
	}

	public void setPjLeave4(String pjLeave4) {
		this.pjLeave4 = pjLeave4;
	}

	@Column(name = "pj_workTime5", length = 50)
	public String getPjWorkTime5() {
		return this.pjWorkTime5;
	}

	public void setPjWorkTime5(String pjWorkTime5) {
		this.pjWorkTime5 = pjWorkTime5;
	}

	@Column(name = "pj_company5", length = 50)
	public String getPjCompany5() {
		return this.pjCompany5;
	}

	public void setPjCompany5(String pjCompany5) {
		this.pjCompany5 = pjCompany5;
	}

	@Column(name = "pj_position5", length = 50)
	public String getPjPosition5() {
		return this.pjPosition5;
	}

	public void setPjPosition5(String pjPosition5) {
		this.pjPosition5 = pjPosition5;
	}

	@Column(name = "pj_salary5", length = 50)
	public String getPjSalary5() {
		return this.pjSalary5;
	}

	public void setPjSalary5(String pjSalary5) {
		this.pjSalary5 = pjSalary5;
	}

	@Column(name = "pj_leave5", length = 500)
	public String getPjLeave5() {
		return this.pjLeave5;
	}

	public void setPjLeave5(String pjLeave5) {
		this.pjLeave5 = pjLeave5;
	}

	@Column(name = "pj_call1", length = 50)
	public String getPjCall1() {
		return this.pjCall1;
	}

	public void setPjCall1(String pjCall1) {
		this.pjCall1 = pjCall1;
	}

	@Column(name = "pj_name1", length = 50)
	public String getPjName1() {
		return this.pjName1;
	}

	public void setPjName1(String pjName1) {
		this.pjName1 = pjName1;
	}

	@Column(name = "pj_unit1", length = 50)
	public String getPjUnit1() {
		return this.pjUnit1;
	}

	public void setPjUnit1(String pjUnit1) {
		this.pjUnit1 = pjUnit1;
	}

	@Column(name = "pj_residence1", length = 50)
	public String getPjResidence1() {
		return this.pjResidence1;
	}

	public void setPjResidence1(String pjResidence1) {
		this.pjResidence1 = pjResidence1;
	}

	@Column(name = "pj_phone1", length = 50)
	public String getPjPhone1() {
		return this.pjPhone1;
	}

	public void setPjPhone1(String pjPhone1) {
		this.pjPhone1 = pjPhone1;
	}

	@Column(name = "pj_call2", length = 50)
	public String getPjCall2() {
		return this.pjCall2;
	}

	public void setPjCall2(String pjCall2) {
		this.pjCall2 = pjCall2;
	}

	@Column(name = "pj_name2", length = 50)
	public String getPjName2() {
		return this.pjName2;
	}

	public void setPjName2(String pjName2) {
		this.pjName2 = pjName2;
	}

	@Column(name = "pj_unit2", length = 50)
	public String getPjUnit2() {
		return this.pjUnit2;
	}

	public void setPjUnit2(String pjUnit2) {
		this.pjUnit2 = pjUnit2;
	}

	@Column(name = "pj_residence2", length = 50)
	public String getPjResidence2() {
		return this.pjResidence2;
	}

	public void setPjResidence2(String pjResidence2) {
		this.pjResidence2 = pjResidence2;
	}

	@Column(name = "pj_phone2", length = 50)
	public String getPjPhone2() {
		return this.pjPhone2;
	}

	public void setPjPhone2(String pjPhone2) {
		this.pjPhone2 = pjPhone2;
	}

	@Column(name = "pj_call3", length = 50)
	public String getPjCall3() {
		return this.pjCall3;
	}

	public void setPjCall3(String pjCall3) {
		this.pjCall3 = pjCall3;
	}

	@Column(name = "pj_name3", length = 50)
	public String getPjName3() {
		return this.pjName3;
	}

	public void setPjName3(String pjName3) {
		this.pjName3 = pjName3;
	}

	@Column(name = "pj_unit3", length = 50)
	public String getPjUnit3() {
		return this.pjUnit3;
	}

	public void setPjUnit3(String pjUnit3) {
		this.pjUnit3 = pjUnit3;
	}

	@Column(name = "pj_residence3", length = 50)
	public String getPjResidence3() {
		return this.pjResidence3;
	}

	public void setPjResidence3(String pjResidence3) {
		this.pjResidence3 = pjResidence3;
	}

	@Column(name = "pj_phone3", length = 50)
	public String getPjPhone3() {
		return this.pjPhone3;
	}

	public void setPjPhone3(String pjPhone3) {
		this.pjPhone3 = pjPhone3;
	}

	@Column(name = "pj_project", length = 999)
	public String getPjProject() {
		return this.pjProject;
	}

	public void setPjProject(String pjProject) {
		this.pjProject = pjProject;
	}

	@Column(name = "pj_plan", length = 999)
	public String getPjPlan() {
		return this.pjPlan;
	}

	public void setPjPlan(String pjPlan) {
		this.pjPlan = pjPlan;
	}

	@Column(name = "pj_evaluate", length = 999)
	public String getPjEvaluate() {
		return this.pjEvaluate;
	}

	public void setPjEvaluate(String pjEvaluate) {
		this.pjEvaluate = pjEvaluate;
	}

	@Column(name = "pj_result", length = 999)
	public String getPjResult() {
		return this.pjResult;
	}

	public void setPjResult(String pjResult) {
		this.pjResult = pjResult;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pj_date", length = 10)
	public Date getPjDate() {
		return this.pjDate;
	}

	public void setPjDate(Date pjDate) {
		this.pjDate = pjDate;
	}

}