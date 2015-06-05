package com.oa.manager.system.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 自定义打印导出设置表实体
 */
@Entity
@Table(name = "sy_table_custom")
public class SyTableCustom implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 类型，不同类型对应不同表
	 */
	@NotNull(message="类型不能为空")
	private Short tbType;
	/**
	 * 顺序号
	 */
	@NotNull(message="顺序号不能为空")
	@Range(min=1,max=999,message="顺序号必须为1-999的整数")
	private Short fieldSort;
	/**
	 * 表字段对应的javaBean属性名称
	 */
	@Length(min=1,max=30,message="属性名长度在1-30之间。")
	private String fieldName;
	/**
	 * 属性类型
	 */
	@NotNull(message="属性类型不能为空")
	private Short fieldType;
	/**
	 * 默认的显示名
	 */
	@Length(min=1,max=20,message="显示名长度在1-20之间。")
	private String fieldTitle;
	/**
	 * 显示的别名，如果没有则显示默认名称
	 */
	@Length(min=0,max=20,message="别名长度在0-20之间。")
	private String fieldAnotherTitle;
	/**
	 * 是否导出，1：导出，0：不导出
	 */
	private Short isExport;
	/**
	 * 是否打印,1:打印，0,不打印
	 */
	private Short isPrint;
	/**
	 * 是否显示，1：显示，0：不显示
	 */
	private Short isShow;

	// Constructors

	/** default constructor */
	public SyTableCustom() {
	}

	/** minimal constructor */
	public SyTableCustom(Short tbType, Short fieldSort, String fieldName,
			Short fieldType, String fieldTitle, Short isExport, Short isPrint,
			Short isShow) {
		this.tbType = tbType;
		this.fieldSort = fieldSort;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldTitle = fieldTitle;
		this.isExport = isExport;
		this.isPrint = isPrint;
		this.isShow = isShow;
	}

	/** full constructor */
	public SyTableCustom(Short tbType, Short fieldSort, String fieldName,
			Short fieldType, String fieldTitle, String fieldAnotherTitle,
			Short isExport, Short isPrint, Short isShow) {
		this.tbType = tbType;
		this.fieldSort = fieldSort;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldTitle = fieldTitle;
		this.fieldAnotherTitle = fieldAnotherTitle;
		this.isExport = isExport;
		this.isPrint = isPrint;
		this.isShow = isShow;
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
	@Column(name = "tb_type", nullable = false)
	public Short getTbType() {
		return this.tbType;
	}

	public void setTbType(Short tbType) {
		this.tbType = tbType;
	}
	@Column(name = "field_sort", nullable = false)
	public Short getFieldSort() {
		return this.fieldSort;
	}

	public void setFieldSort(Short fieldSort) {
		this.fieldSort = fieldSort;
	}
	@Column(name = "field_name", nullable = false, length = 30)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	@Column(name = "field_type", nullable = false)
	public Short getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(Short fieldType) {
		this.fieldType = fieldType;
	}
	@Column(name = "field_title", nullable = false, length = 20)
	public String getFieldTitle() {
		return this.fieldTitle;
	}

	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}
	@Column(name = "field_another_title", length = 20)
	public String getFieldAnotherTitle() {
		return this.fieldAnotherTitle;
	}

	public void setFieldAnotherTitle(String fieldAnotherTitle) {
		this.fieldAnotherTitle = fieldAnotherTitle;
	}
	@Column(name = "is_export", nullable = false)
	public Short getIsExport() {
		return this.isExport;
	}

	public void setIsExport(Short isExport) {
		this.isExport = isExport;
	}
	@Column(name = "is_print", nullable = false)
	public Short getIsPrint() {
		return this.isPrint;
	}

	public void setIsPrint(Short isPrint) {
		this.isPrint = isPrint;
	}
	@Column(name = "is_show", nullable = false)
	public Short getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}

}