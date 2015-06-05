package com.oa.manager.coordination.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目文档
 */
@Entity
@Table(name = "project_files")
public class ProjectFiles implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 关联的项目id
	 */
	private String projectId;
	@NotBlank(message="文档标题不能为空")
	@Length(min=1, max=100, message="文档标题长度限制在1-100")
	private String title;
	/**
	 * 文档内容
	 */
	private String content;
	/**
	 * 创建人
	 */
	private String createUserId;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 附件信息 json格式
	 */
	private String files;

	// Constructors

	/** default constructor */
	public ProjectFiles() {
	}

	/** minimal constructor */
	public ProjectFiles(String projectId) {
		this.projectId = projectId;
	}

	/** full constructor */
	public ProjectFiles(String projectId, String title, String content,
			String createUserId, Timestamp createTime, String files) {
		this.projectId = projectId;
		this.title = title;
		this.content = content;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.files = files;
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

	@Column(name = "_project_id", nullable = false, length = 32)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "_title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "_content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "_create_user_id", length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "_create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "_files", length = 65535)
	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

}