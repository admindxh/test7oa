package com.oa.manager.system.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

/**
 * 用户自定义快捷菜单
 */
@Entity
@Table(name = "sy_menu_my")
public class SyMenuMy implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 关联的用户id 
	 */
	private String userId;
	/**
	 * 关联的菜单id
	 */
	private String menuId;
	/**
	 * 排序号
	 */
	@NotNull(message="排序号不能为空")
	@Range(min=1,max=999,message="排序号必须为1-999的整数")
	private Short sort;

	// Constructors

	/** default constructor */
	public SyMenuMy() {
	}

	/** full constructor */
	public SyMenuMy(String userId, String menuId, Short sort) {
		this.userId = userId;
		this.menuId = menuId;
		this.sort = sort;
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

	@Column(name = "_menu_id", nullable = false, length = 32)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "_sort", nullable = false)
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

}