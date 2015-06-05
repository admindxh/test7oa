package com.oa.manager.coordination.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 项目 负责人关联表
 */
@Entity
@Table(name = "project_user")
public class ProjectUser implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	// Fields

	private String id;
	private String projectId;
	private Short type;
	private String tableId;

	// Constructors

	/** default constructor */
	public ProjectUser() {
	}

	/** full constructor */
	public ProjectUser(String projectId, Short type, String tableId) {
		this.projectId = projectId;
		this.type = type;
		this.tableId = tableId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "_project_id", nullable = false, length = 32)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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