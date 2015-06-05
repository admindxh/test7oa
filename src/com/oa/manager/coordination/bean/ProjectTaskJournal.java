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
 * 项目任务进度日志
 */
@Entity
@Table(name = "project_task_journal")
public class ProjectTaskJournal implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 关联的项目任务
	 */
	private String projectTaskId;
	/**
	 * 开始时间
	 */
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	private Timestamp endTime;
	/**
	 * 进度说明
	 */
	@NotBlank(message="进度说明不能为空")
	@Length(min=1, max=100, message="进度说明长度限制在1-100")
	private String content;
	/**
	 * 进度日志
	 */
	private String journal;
	/**
	 * 创建人
	 */
	private String createUserId;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public ProjectTaskJournal() {
	}

	/** minimal constructor */
	public ProjectTaskJournal(String projectTaskId, String content,
			String journal, String createUserId, Timestamp createTime) {
		this.projectTaskId = projectTaskId;
		this.content = content;
		this.journal = journal;
		this.createUserId = createUserId;
		this.createTime = createTime;
	}

	/** full constructor */
	public ProjectTaskJournal(String projectTaskId, Timestamp startTime,
			Timestamp endTime, String content, String journal,
			String createUserId, Timestamp createTime) {
		this.projectTaskId = projectTaskId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.journal = journal;
		this.createUserId = createUserId;
		this.createTime = createTime;
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

	@Column(name = "_project_task_id", nullable = false, length = 32)
	public String getProjectTaskId() {
		return this.projectTaskId;
	}

	public void setProjectTaskId(String projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	@Column(name = "_start_time", length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "_end_time", length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "_content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "_journal", nullable = false, length = 100)
	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	@Column(name = "_create_user_id", nullable = false, length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "_create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}