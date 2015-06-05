package com.oa.manager.coordination.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目管理
 */
@Entity
@Table(name = "project")
public class Project implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 项目名称
	 */
	@NotBlank(message="项目名称不能为空")
	@Length(min=1, max=100, message="项目名称长度限制在1-100")
	private String name;
	/**
	 * 项目类型
	 */
	@NotBlank(message="项目类型不能为空")
	private String type;
	/**
	 * 项目重要程度
	 */
	@NotBlank(message="项目重要程度不能为空")
	private String importance;
	/**
	 * 状态,0:未开始,1:运行中,2:暂停,3:结束
	 */
	private Short status;
	/**
	 * 开始时间
	 */
	@NotNull(message="开始时间不能为空")
	private Timestamp startTime;
	/**
	 * 结束时间
	 */
	@NotNull(message="结束时间不能为空")
	private Timestamp endTime;
	/**
	 * 创建人
	 */
	private String createUserId;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 项目内容
	 */
	private String content;

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(Short status, Timestamp startTime, Timestamp endTime,
			Timestamp createTime) {
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public Project(String name, String type, String importance, Short status,
			Timestamp startTime, Timestamp endTime, String createUserId,
			Timestamp createTime, String content) {
		this.name = name;
		this.type = type;
		this.importance = importance;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.content = content;
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

	@Column(name = "_name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "_type", length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "_importance", length = 32)
	public String getImportance() {
		return this.importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	@Column(name = "_status", nullable = false)
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	@Column(name = "_create_user_id", length = 32)
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

	@Column(name = "_content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}