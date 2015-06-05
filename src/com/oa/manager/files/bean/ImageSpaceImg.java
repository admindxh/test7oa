package com.oa.manager.files.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 图片空间 图片
 */
@Entity
@Table(name = "image_space_img")
public class ImageSpaceImg implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	/**
	 * 关联用户
	 */
	private String userId;
	/**
	 * 关联类型
	 */
	private String type;
	/**
	 * 图片名称
	 */
	private String name;
	/**
	 * 后缀
	 */
	private String ext;
	/**
	 * 大小
	 */
	private Integer size;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 保存位置
	 */
	private String savePath;

	// Constructors

	/** default constructor */
	public ImageSpaceImg() {
	}

	/** minimal constructor */
	public ImageSpaceImg(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public ImageSpaceImg(String userId, String type, String name, String ext,
			Integer size, Timestamp createTime, String savePath) {
		this.userId = userId;
		this.type = type;
		this.name = name;
		this.ext = ext;
		this.size = size;
		this.createTime = createTime;
		this.savePath = savePath;
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

	@Column(name = "_type", length = 32)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "_name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "_ext", length = 50)
	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Column(name = "_size")
	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Column(name = "_create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "_save_path")
	public String getSavePath() {
		return this.savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}