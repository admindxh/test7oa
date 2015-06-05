package com.oa.manager.system.bean;

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
 * 地区表对应实体
 */
@Entity
@Table(name = "sy_district")
public class SyDistrict implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 上级id
	 */
	@NotBlank(message="上级地区不能为空")
	private String superId;
	/**
	 * 排序号
	 */
	@NotNull(message="序号不能为空")
	@Range(min=1,max=9999,message="顺序号必须为1-9999的整数")
	private Short disSort;
	/**
	 * 地区名称
	 */
	@NotBlank(message="地区名称不能为空")
	@Length(min=1,max=50,message="地区名称长度必须在1-50之间")
	private String disName;
	/**
	 * 地区说明
	 */
	private String disDesc;

	// Constructors

	/** default constructor */
	public SyDistrict() {
	}

	/** minimal constructor */
	public SyDistrict(Short disSort, String disName) {
		this.disSort = disSort;
		this.disName = disName;
	}

	/** full constructor */
	public SyDistrict(String superId, Short disSort, String disName,
			String disDesc) {
		this.superId = superId;
		this.disSort = disSort;
		this.disName = disName;
		
		this.disDesc = disDesc;
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
	@Column(name = "super_id", length = 32)
	public String getSuperId() {
		return this.superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}
	@Column(name = "dis_sort", nullable = false)
	public Short getDisSort() {
		return this.disSort;
	}

	public void setDisSort(Short disSort) {
		this.disSort = disSort;
	}
	@Column(name = "dis_name", nullable = false, length = 100)
	public String getDisName() {
		return this.disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}
	@Column(name = "dis_desc", length = 100)
	public String getDisDesc() {
		return this.disDesc;
	}

	public void setDisDesc(String disDesc) {
		this.disDesc = disDesc;
	}

}