package com.oa.manager.coordination.bean;

import org.hibernate.validator.constraints.Length;

/**
 * 通知内容表对应实体
 */

public class XtNoticeContent implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 通知id,此字段为此表主键，手动指定，assigned
	 */
	private String noticeId;
	/**
	 * 通知内容
	 */
	@Length(min=1, max=65530, message="内容不能为空，限制65530字节")
	private String noticeContent;
	/**
	 * 接收人员id，多个用,隔开
	 */
	@Length(min=0, max=65530, message="接收人员量太大，请分批发送")
	private String userIds;
	/**
	 * 接收部门id，多个用,隔开
	 */
	@Length(min=0, max=65530, message="接收部门量太大，请分批发送")
	private String deptIds;

	// Constructors

	/** default constructor */
	public XtNoticeContent() {
	}

	/** full constructor */
	public XtNoticeContent(String noticeContent, String userIds, String deptIds) {
		this.noticeContent = noticeContent;
		this.userIds = userIds;
		this.deptIds = deptIds;
	}

	// Property accessors

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getUserIds() {
		return this.userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getDeptIds() {
		return this.deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

}