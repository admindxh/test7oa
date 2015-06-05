package com.oa.manager.common_platform.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Meeting entity. @author MyEclipse Persistence Tools
 */

public class Meeting implements java.io.Serializable {

	// Fields

	private String id;
	private String MType;
	@NotEmpty 
	@Length(min=1,max=50,message="标题长度在1-50之间。")
	private String MName;
	private String MHost;
	private Date MDate;
	private String MStatime;
	private String MEndtime;
	private String MRid;
	private String MSummary;
	private String MRemark;
	private String MPublished;
	private String MUid;

	private String deptid;
	private String dtid;//修改时用到，旧的部门ID
	private String userid;
	private String uid;//修改时用到，旧的人员ID
	private String rName;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date MDate2;//条件查询时使用 范围日期
	private String roomName;//条件查询时使用  会议室名称
	
	
	// Constructors

	/** default constructor */
	public Meeting() {
	}

	/** full constructor */
	public Meeting(String MType, String MName, String MHost, Date MDate,
			String MStatime, String MEndtime, String MRid, String MSummary,
			String MRemark, String MPublished, String MUid) {
		this.MType = MType;
		this.MName = MName;
		this.MHost = MHost;
		this.MDate = MDate;
		this.MStatime = MStatime;
		this.MEndtime = MEndtime;
		this.MRid = MRid;
		this.MSummary = MSummary;
		this.MRemark = MRemark;
		this.MPublished = MPublished;
		this.MUid = MUid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMType() {
		return this.MType;
	}

	public void setMType(String MType) {
		this.MType = MType;
	}

	public String getMName() {
		return this.MName;
	}

	public void setMName(String MName) {
		this.MName = MName;
	}

	public String getMHost() {
		return this.MHost;
	}

	public void setMHost(String MHost) {
		this.MHost = MHost;
	}

	public Date getMDate() {
		return this.MDate;
	}

	public void setMDate(Date MDate) {
		this.MDate = MDate;
	}

	public String getMStatime() {
		return this.MStatime;
	}

	public void setMStatime(String MStatime) {
		this.MStatime = MStatime;
	}

	public String getMEndtime() {
		return this.MEndtime;
	}

	public void setMEndtime(String MEndtime) {
		this.MEndtime = MEndtime;
	}

	public String getMRid() {
		return this.MRid;
	}

	public void setMRid(String MRid) {
		this.MRid = MRid;
	}

	public String getMSummary() {
		return this.MSummary;
	}

	public void setMSummary(String MSummary) {
		this.MSummary = MSummary;
	}

	public String getMRemark() {
		return this.MRemark;
	}

	public void setMRemark(String MRemark) {
		this.MRemark = MRemark;
	}

	public String getMPublished() {
		return this.MPublished;
	}

	public void setMPublished(String MPublished) {
		this.MPublished = MPublished;
	}

	public String getMUid() {
		return this.MUid;
	}

	public void setMUid(String MUid) {
		this.MUid = MUid;
	}

	

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDtid() {
		return dtid;
	}

	public void setDtid(String dtid) {
		this.dtid = dtid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public Date getMDate2() {
		return MDate2;
	}

	public void setMDate2(Date mDate2) {
		MDate2 = mDate2;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}