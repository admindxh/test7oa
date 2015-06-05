package com.oa.manager.files.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

/**
 * 图片空间 图片类型
 */
@Entity
@Table(name = "image_space")
public class ImageSpace implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	/**
	 * 关联用户id
	 */
	private String userId;
	/**
	 * 类型名称
	 */
	@Length(min=1,max=50,message="类型名称长度必须在1-50之间")
	private String name;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 说明
	 */
	@Length(min=0,max=255,message="说明长度不能大于255个字符。")
	private String remark;
	/**
	 * 标识：0：用户创建，1：word图片转存(系统创建)
	 */
	private Short type;

	// Constructors

	/** default constructor */
	public ImageSpace() {
	}

	/** minimal constructor */
	public ImageSpace(String userId, String name, Timestamp createTime,
			Short type) {
		this.userId = userId;
		this.name = name;
		this.createTime = createTime;
		this.type = type;
	}

	/** full constructor */
	public ImageSpace(String userId, String name, Timestamp createTime,
			String remark, Short type) {
		this.userId = userId;
		this.name = name;
		this.createTime = createTime;
		this.remark = remark;
		this.type = type;
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

	@Column(name = "_user_id", nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "_name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "_create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "_remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "_type", nullable = false)
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}