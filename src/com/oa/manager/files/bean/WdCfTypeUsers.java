package com.oa.manager.files.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 公司文档类型关联的用户,部门
 */
@Entity
@Table(name = "wd_cf_type_users")
public class WdCfTypeUsers implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 文档类型id
	 */
	private String cfTypeId;
	/**
	 * 文档管理权限表id，
	 */
	private String cfTypePowerId;
	/**
	 * 类型:1:部门,2:用户
	 */
	private Short type;
	/**
	 * 关联的id，用户id，或部门id
	 */
	private String tableId;

	// Constructors

	/** default constructor */
	public WdCfTypeUsers() {
	}

	/** full constructor */
	public WdCfTypeUsers(String cfTypeId, String cfTypePowerId, Short type,
			String tableId) {
		this.cfTypeId = cfTypeId;
		this.cfTypePowerId = cfTypePowerId;
		this.type = type;
		this.tableId = tableId;
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

	@Column(name = "_cf_type_id", nullable = false, length = 32)
	public String getCfTypeId() {
		return this.cfTypeId;
	}

	public void setCfTypeId(String cfTypeId) {
		this.cfTypeId = cfTypeId;
	}

	@Column(name = "_cf_type_power_id", nullable = false, length = 32)
	public String getCfTypePowerId() {
		return this.cfTypePowerId;
	}

	public void setCfTypePowerId(String cfTypePowerId) {
		this.cfTypePowerId = cfTypePowerId;
	}

	@Column(name = "_type", nullable = false)
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "_table_id", nullable = false, length = 32)
	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

}