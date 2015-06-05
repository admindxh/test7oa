package com.oa.manager.coordination.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 领导批准
 */
@Entity
@Table(name = "xt_work_plan_comment")
public class XtWorkPlanComment implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String workPlanId;
	private String content;
	private Timestamp createTime;
	private String userId;

	// Constructors

	/** default constructor */
	public XtWorkPlanComment() {
	}

	/** full constructor */
	public XtWorkPlanComment(String workPlanId, String content,
			Timestamp createTime, String userId) {
		this.workPlanId = workPlanId;
		this.content = content;
		this.createTime = createTime;
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

	@Column(name = "_work_plan_id", length = 32)
	public String getWorkPlanId() {
		return this.workPlanId;
	}

	public void setWorkPlanId(String workPlanId) {
		this.workPlanId = workPlanId;
	}

	@Column(name = "_content", length = 255)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "_create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "_user_id", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}