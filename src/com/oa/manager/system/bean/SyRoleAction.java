package com.oa.manager.system.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色--操作关联
 */
@Entity
@Table(name = "sy_role_action")
public class SyRoleAction implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 操作id
	 */
	private String actionId;

	// Constructors

	/** default constructor */
	public SyRoleAction() {
	}

	/** full constructor */
	public SyRoleAction(String roleId, String actionId) {
		this.roleId = roleId;
		this.actionId = actionId;
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
	@Column(name = "role_id", nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "action_id", nullable = false, length = 32)
	public String getActionId() {
		return this.actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

}