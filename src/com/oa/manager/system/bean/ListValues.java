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


/**
 * 数据类型字典表对应实体
 *
 */
@Entity
@Table(name = "list_values")
public class ListValues implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 字典值类型
	 */
	@NotNull(message="类型不能为空")
	private Integer listType;
	/**
	 * 字典值
	 */
	@NotBlank(message="字典值不能为空")
	@Length(min=1,max=50,message="字典值长度在1-50之间。")
	private String listValue;
	/**
	 * 备注
	 */
	@Length(min=0, max=50, message="备注长度不能大于50。")
	private String listDesc;

	// Constructors

	/** default constructor */
	public ListValues() {
	}

	/** minimal constructor */
	public ListValues(Integer listType, String listValue) {
		this.listType = listType;
		this.listValue = listValue;
	}

	/** full constructor */
	public ListValues(Integer listType, String listValue, String listDesc) {
		this.listType = listType;
		this.listValue = listValue;
		this.listDesc = listDesc;
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
	@Column(name = "list_type", nullable = false)
	public Integer getListType() {
		return this.listType;
	}

	public void setListType(Integer listType) {
		this.listType = listType;
	}
	@Column(name = "list_value", nullable = false, length = 50)
	public String getListValue() {
		return this.listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}
	@Column(name = "list_desc", length = 50)
	public String getListDesc() {
		return this.listDesc;
	}

	public void setListDesc(String listDesc) {
		this.listDesc = listDesc;
	}

}