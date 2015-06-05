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
 * 部门表对应实体
 */
@Entity
@Table(name = "sy_dept")
public class SyDept implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 部门序号
	 */
	@NotNull(message="部门序号不能为空")
	@Range(min=1,max=999,message="部门序号必须为1-999的整数")
	private Short deptSort;
	/**
	 * 部门名称
	 */
	@NotBlank(message="部门名称不能为空")
	@Length(min=1,max=50,message="部门名称长度必须在1-50之间")
	private String deptName;
	/**
	 * 部门电话
	 */
	@Length(min=0,max=255,message="电话长度必须在1-255之间")
	private String deptPhone;
	/**
	 * 部门传真
	 */
	@Length(min=0,max=255,message="传真长度必须在1-255之间")
	private String deptFax;
	/**
	 * 部门地址
	 */
	@Length(min=0,max=100,message="地址长度不能超过100。")
	private String deptAddress;
	/**
	 * 上级部门
	 */
	@NotBlank(message="上级部门不能为空")
	private String superId;
	/**
	 * 部门主管
	 */
	private String leadUid;
	/**
	 * 部门描述
	 */
	@Length(min=0,max=200,message="备注长度不能超过200。")
	private String deptDesc;
	

	// Constructors

	/** default constructor */
	public SyDept() {
	}

	/** minimal constructor */
	public SyDept(Short deptSort, String deptName, String superId) {
		this.deptSort = deptSort;
		this.deptName = deptName;
		this.superId = superId;
	}

	/** full constructor */
	public SyDept(Short deptSort, String deptName, String deptPhone,
			String deptFax, String deptAddress, String superId, String leadUid,
			String deptDesc) {
		this.deptSort = deptSort;
		this.deptName = deptName;
		this.deptPhone = deptPhone;
		this.deptFax = deptFax;
		this.deptAddress = deptAddress;
		this.superId = superId;
		this.leadUid = leadUid;
		this.deptDesc = deptDesc;
		
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
	@Column(name = "dept_sort", nullable = false)
	public Short getDeptSort() {
		return this.deptSort;
	}

	public void setDeptSort(Short deptSort) {
		this.deptSort = deptSort;
	}
	@Column(name = "dept_name", nullable = false,length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name = "dept_phone", length = 20)
	public String getDeptPhone() {
		return this.deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}
	@Column(name = "dept_fax", length = 20)
	public String getDeptFax() {
		return this.deptFax;
	}

	public void setDeptFax(String deptFax) {
		this.deptFax = deptFax;
	}
	@Column(name = "dept_address", length = 100)
	public String getDeptAddress() {
		return this.deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}
	@Column(name = "super_id", nullable = false, length = 32)
	public String getSuperId() {
		return this.superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}
	@Column(name = "lead_uid", length = 32)
	public String getLeadUid() {
		return this.leadUid;
	}

	public void setLeadUid(String leadUid) {
		this.leadUid = leadUid;
	}
	@Column(name = "dept_desc", length = 200)
	public String getDeptDesc() {
		return this.deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}



}