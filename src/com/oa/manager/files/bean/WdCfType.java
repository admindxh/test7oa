package com.oa.manager.files.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 公司文档类型
 */
@Entity
@Table(name = "wd_cf_type")
public class WdCfType implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 上级id
	 */
	private String superId;
	/**
	 * 顺序号
	 */
	@NotNull(message="顺序号不能为空")
	@Range(min=1,max=999,message="顺序号必须为1-999的整数")
	private Short sortNumber;
	/**
	 * 类别名称
	 */
	@NotBlank(message="类别名称不能为空")
	@Length(min=1,max=50,message="类别名称长度必须在1-50之间")
	private String name;

	// Constructors

	/** default constructor */
	public WdCfType() {
	}

	/** full constructor */
	public WdCfType(String superId, Short sortNumber, String name) {
		this.superId = superId;
		this.sortNumber = sortNumber;
		this.name = name;
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

	@Column(name = "_super_id", nullable = false, length = 32)
	public String getSuperId() {
		return this.superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	@Column(name = "_sort_number", nullable = false)
	public Short getSortNumber() {
		return this.sortNumber;
	}

	public void setSortNumber(Short sortNumber) {
		this.sortNumber = sortNumber;
	}

	@Column(name = "_name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}