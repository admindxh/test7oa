package com.oa.manager.coordination.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 工作计划关联人
 */
@Entity
@Table(name = "xt_work_plan_user")
public class XtWorkPlanUser implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String workPlanId;
	private Short type;
	private String tableId;

	// Constructors

	/** default constructor */
	public XtWorkPlanUser() {
	}

	/** full constructor */
	public XtWorkPlanUser(String workPlanId, Short type, String tableId) {
		this.workPlanId = workPlanId;
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

	@Column(name = "_work_plan_id", length = 32)
	public String getWorkPlanId() {
		return this.workPlanId;
	}

	public void setWorkPlanId(String workPlanId) {
		this.workPlanId = workPlanId;
	}

	@Column(name = "_type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "_table_id", length = 32)
	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

}