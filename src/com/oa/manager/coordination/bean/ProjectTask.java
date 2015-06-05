package com.oa.manager.coordination.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 项目任务
 */
@Entity
@Table(name = "project_task")
public class ProjectTask implements java.io.Serializable {

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
	/**
	 * 任务名称
	 */
	private String name;
	/**
	 * 开始时间
	 */
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	private Timestamp endTime;
	/**
	 * 任务内容
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
	 * 进度 0--100 百分数
	 */
	private Short speed;

	// Constructors

	/** default constructor */
	public ProjectTask() {
	}

	/** minimal constructor */
	public ProjectTask(String name, Timestamp startTime, Timestamp endTime,
			String createUserId, Timestamp createTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createUserId = createUserId;
		this.createTime = createTime;
	}

	/** full constructor */
	public ProjectTask(String projectId, String name, Timestamp startTime,
			Timestamp endTime, String content, String createUserId,
			Timestamp createTime, Short speed) {
		this.projectId = projectId;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.speed = speed;
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

	@Column(name = "_name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "_start_time", nullable = false, length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "_end_time", nullable = false, length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "_content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Column(name = "_speed")
	public Short getSpeed() {
		return this.speed;
	}

	public void setSpeed(Short speed) {
		this.speed = speed;
	}

}