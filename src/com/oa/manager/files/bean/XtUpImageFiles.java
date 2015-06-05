package com.oa.manager.files.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtUpImageFiles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_up_image_files")
public class XtUpImageFiles implements java.io.Serializable {

	// Fields

	private String id;
	//图片id
	private String imageId;
	//原始文件名
	private String fileName;
	//保存文件名uuid
	private String fileNewName;
	//文件后缀
	private String fileExt;
	//文件大小
	private String fileSize;

	// Constructors

	/** default constructor */
	public XtUpImageFiles() {
	}

	/** full constructor */
	public XtUpImageFiles(String imageId, String fileName, String fileNewName,
			String fileExt, String fileSize) {
		this.imageId = imageId;
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

	@Column(name = "image_id", length = 32)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return this.fileName;
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

	@Column(name = "file_size",length = 30)
	public String getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}