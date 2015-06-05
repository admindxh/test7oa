package com.oa.manager.personnel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtOndutyFiles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_onduty_files")
public class XtOndutyFiles implements java.io.Serializable {

	// Fields

	private String id;
	private String dutyid;
	private String fileName;
	private String fileNewName;
	private String fileExt;
	private Integer fileSize;

	// Constructors

	/** default constructor */
	public XtOndutyFiles() {
	}

	 
	/** full constructor */
	public XtOndutyFiles( String dutyid, String fileName,
			String fileNewName, String fileExt, Integer fileSize) {
		this.dutyid = dutyid;
		this.fileName = fileName;
		this.fileNewName = fileNewName;
		this.fileExt = fileExt;
		this.fileSize = fileSize;
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
 
	
	 

	 

	@Column(name = "file_name")
	public String getFileName() {
		return this.fileName;
	}
	@Column(name = "dutyid", nullable = false)
	public String getDutyid() {
		return dutyid;
	}


	public void setDutyid(String dutyid) {
		this.dutyid = dutyid;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_new_name", length = 32)
	public String getFileNewName() {
		return this.fileNewName;
	}

	public void setFileNewName(String fileNewName) {
		this.fileNewName = fileNewName;
	}

	@Column(name = "file_ext", length = 50)
	public String getFileExt() {
		return this.fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	@Column(name = "file_size")
	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

}