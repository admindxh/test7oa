package com.oa.manager.coordination.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 项目任务 执行人 关联表
 */
@Entity
@Table(name = "project_task_user")
public class ProjectTaskUser implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String projectId;
	private String projectTaskId;
	private String userId;

	// Constructors

	/** default constructor */
	public ProjectTaskUser() {
	}

	/** full constructor */
	public ProjectTaskUser(String projectId, String projectTaskId, String userId) {
		this.projectId = projectId;
		this.projectTaskId = projectTaskId;
		this.userId = userId;
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

	@Column(name = "_project_id", length = 32)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "_project_task_id", length = 32)
	public String getProjectTaskId() {
		return this.projectTaskId;
	}

	public void setProjectTaskId(String projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	@Column(name = "_user_id", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}