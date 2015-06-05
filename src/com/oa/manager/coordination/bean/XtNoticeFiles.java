package com.oa.manager.coordination.bean;

import org.hibernate.validator.constraints.Length;

/**
 * 通知附件表对应实体
 */

public class XtNoticeFiles implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 通知id
	 */
	private String noticeId;
	/**
	 * 附件原名称
	 */
	@Length(min=1, max=255, message="文件名长度在1-255之间。")
	private String fileName;
	/**
	 * 附件保存时名称
	 */
	private String fileNewName;
	/**
	 * 附件后缀
	 */
	private String fileExt;
	/**
	 * 附件大小，单位b
	 */
	private Integer fileSize;

	// Constructors

	/** default constructor */
	public XtNoticeFiles() {
	}

	/** minimal constructor */
	public XtNoticeFiles(String noticeId) {
		this.noticeId = noticeId;
	}

	/** full constructor */
	public XtNoticeFiles(String noticeId,
			String fileName, String fileNewName, String fileExt,
			Integer fileSize) {
		this.noticeId = noticeId;
		
		this.fileName = fileName;
		this.fileNewName = fileNewName;
		this.fileExt = fileExt;
		this.fileSize = fileSize;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}


	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNewName() {
		return this.fileNewName;
	}

	public void setFileNewName(String fileNewName) {
		this.fileNewName = fileNewName;
	}

	public String getFileExt() {
		return this.fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

}