package com.oa.manager.system.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

/**
 * 数据权限
 */
@Entity
@Table(name = "sy_data_permissions")
public class SyDataPermissions implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 模块名称
	 */
	private String name;
	/**
	 * 模块类型
	 */
	private String type;
	/**
	 * 规则
	 */
	private String rules;
	/**
	 * 说明
	 */
	@Length(min=0, max=255, message="说明长度不能大于255。")
	private String desc;

	// Constructors

	/** default constructor */
	public SyDataPermissions() {
	}

	/** minimal constructor */
	public SyDataPermissions(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/** full constructor */
	public SyDataPermissions(String name, String type, String rules, String desc) {
		this.name = name;
		this.type = type;
		this.rules = rules;
		this.desc = desc;
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

	@Column(name = "_name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "_type", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "_rules", length = 1000)
	public String getRules() {
		return this.rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	@Column(name = "_desc")
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}