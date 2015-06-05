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
 * 工作计划
 */
@Entity
@Table(name = "xt_work_plan")
public class XtWorkPlan implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	@NotBlank(message="计划名称不能为空")
	@Length(min=1, max=100, message="计划名称长度限制在1-100")
	private String name;
	private String type;
	private Short status;
	@NotNull(message="开始时间不能为空")
	private Timestamp startTime;
	@NotNull(message="结束时间不能为空")
	private Timestamp endTime;
	private Timestamp createTime;
	private String createUserId;
	private String remark;
	private String content;
	private String files;

	// Constructors

	/** default constructor */
	public XtWorkPlan() {
	}

	/** full constructor */
	public XtWorkPlan(String name, String type, Short status,
			Timestamp startTime, Timestamp endTime, Timestamp createTime,
			String createUserId, String remark, String content, String files) {
		this.name = name;
		this.type = type;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.remark = remark;
		this.content = content;
		this.files = files;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
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

	@Column(name = "_status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	@Column(name = "_create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "_create_user_id", length = 32)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "_remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "_content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "_files", length = 65535)
	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

}