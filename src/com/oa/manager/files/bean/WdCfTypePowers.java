package com.oa.manager.files.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 公司文档类型 授权权限
 */
@Entity
@Table(name = "wd_cf_type_powers")
public class WdCfTypePowers implements java.io.Serializable {

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
	 * 关联的ids,多个用，隔开
	 */
	private String tableIds;
	/**
	 * 类型:1:部门,2:用户
	 */
	private Short type;
	/**
	 * 新增权限,1：有
	 */
	private Short powerAdd;
	/**
	 * 编辑权限,1：有
	 */
	private Short powerUpdate;
	/**
	 * 删除权限,1：有
	 */
	private Short powerDelete;

	// Constructors

	/** default constructor */
	public WdCfTypePowers() {
	}

	/** minimal constructor */
	public WdCfTypePowers(String cfTypeId, String tableIds, Short type) {
		this.cfTypeId = cfTypeId;
		this.tableIds = tableIds;
		this.type = type;
	}

	/** full constructor */
	public WdCfTypePowers(String cfTypeId, String tableIds, Short type,
			Short powerAdd, Short powerUpdate, Short powerDelete) {
		this.cfTypeId = cfTypeId;
		this.tableIds = tableIds;
		this.type = type;
		this.powerAdd = powerAdd;
		this.powerUpdate = powerUpdate;
		this.powerDelete = powerDelete;
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

	@Column(name = "_table_ids", nullable = false, length = 65535)
	public String getTableIds() {
		return this.tableIds;
	}

	public void setTableIds(String tableIds) {
		this.tableIds = tableIds;
	}

	@Column(name = "_type", nullable = false)
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "_power_add")
	public Short getPowerAdd() {
		return this.powerAdd;
	}

	public void setPowerAdd(Short powerAdd) {
		this.powerAdd = powerAdd;
	}

	@Column(name = "_power_update")
	public Short getPowerUpdate() {
		return this.powerUpdate;
	}

	public void setPowerUpdate(Short powerUpdate) {
		this.powerUpdate = powerUpdate;
	}

	@Column(name = "_power_delete")
	public Short getPowerDelete() {
		return this.powerDelete;
	}

	public void setPowerDelete(Short powerDelete) {
		this.powerDelete = powerDelete;
	}

}