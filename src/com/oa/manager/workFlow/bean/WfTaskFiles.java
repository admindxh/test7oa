package com.oa.manager.workFlow.bean;

/**
 * WfTaskFiles entity. @author MyEclipse Persistence Tools
 */

public class WfTaskFiles implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 流程id
	 */
	private String workId;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 保存时的文件名称,UUID
	 */
	private String fileNewName;
	/**
	 * 文件后缀
	 */
	private String fileExt;
	/**
	 * 文件大小
	 */
	private Integer fileSize;

	// Constructors

	/** default constructor */
	public WfTaskFiles() {
	}

	/** full constructor */
	public WfTaskFiles(String workId, String taskId, String fileName,
			String fileNewName, String fileExt, Integer fileSize) {
		this.workId = workId;
		this.taskId = taskId;
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

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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