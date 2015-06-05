package com.oa.manager.system.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 角色表对应实体
 * 
 */
@Entity
@Table(name = "sy_role")
public class SyRole implements java.io.Serializable{

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	/**
	 * 角色名称
	 */
	@NotBlank(message="角色名称不能为空")
	@Length(min=1,max=20,message="角色名称长度必须在1-20之间")
	private String roleName;
	/**
	 * 角色说明
	 */
	private String roleDesc;
	

	// Constructors

	/** default constructor */
	public SyRole() {
	}

	/** minimal constructor */
	public SyRole(String roleName) {
		this.roleName = roleName;
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
	@Column(name = "role_name", nullable = false, length = 20)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "role_desc",	length = 50)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	

}