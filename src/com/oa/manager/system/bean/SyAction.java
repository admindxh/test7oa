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
 * 操作表对应实体
 */
@Entity
@Table(name = "sy_action")
public class SyAction implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	// Fields
	
	private String id;
	/**
	 * 关联菜单id
	 */
	private String menuId;
	/**
	 * 排序号
	 */
	@NotNull(message="操作序号不能为空")
	@Range(min=1,max=999,message="操作序号必须为1-999的整数")
	private Short actionSort;
	/**
	 * 操作名称
	 */
	@NotBlank(message="操作名称不能为空")
	@Length(min=1, max=50, message="操作名称长度不能大于50。")
	private String actionName;
	/**
	 * 操作url，多个用，连接
	 */
	@Length(min=0, max=500, message="url长度不能大于500.")
	private String actionUrl;

	// Constructors

	/** default constructor */
	public SyAction() {
	}

	/** minimal constructor */
	public SyAction(String menuId, Short actionSort, String actionName) {
		this.menuId = menuId;
		this.actionSort = actionSort;
		this.actionName = actionName;
	}

	/** full constructor */
	public SyAction(String menuId, Short actionSort, String actionName,
			String actionUrl) {
		this.menuId = menuId;
		this.actionSort = actionSort;
		this.actionName = actionName;
		this.actionUrl = actionUrl;
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
	@Column(name = "menu_id", nullable = false,length = 32)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name = "action_sort", nullable = false)
	public Short getActionSort() {
		return this.actionSort;
	}

	public void setActionSort(Short actionSort) {
		this.actionSort = actionSort;
	}
	@Column(name = "action_name", nullable = false,length = 50)
	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	@Column(name = "action_url", length = 500)
	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

}