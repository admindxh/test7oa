package com.oa.manager.system.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 用户表对应实体
 */
@Entity
@Table(name = "sy_users")
public class SyUsers implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 用户登录名称
	 */
	@NotBlank(message="账号不能为空")
	@Pattern(regexp="^\\w{3,15}$",message="账号只能由数字、字母或者下划线组成，长度在3-15之间。")
	private String userName;
	/**
	 * 用户密码
	 */
	@NotBlank(message="密码不能为空")
	private String userPassword;
	/**
	 * 注册时间
	 */
	private Timestamp registerTime;
	/**
	 * 最后登陆时间
	 */
	private Timestamp lastLoginTime;
	/**
	 * 最后登陆ip
	 */
	private String lastLoginIp;
	/**
	 * 登陆时密码输入错误时间(最后一次)
	 */
	private Timestamp errorTime;
	/**
	 * 密码输入错误次数，当输入正确一次时清零
	 */
	private Short errorCount;
	/**
	 * 用户状态，1：可用，0：不可用，禁止登陆系统
	 */
	@NotNull(message="状态不能为空")
	@Range(min=0,max=1,message="状态格式错误")
	private Short userStatus;
	/**
	 * 用户所属部门id
	 */
	@Pattern(regexp="^[A-Za-z0-9]{32}$",message="部门格式错误")
	private String deptId;
	/**
	 * 用户真实姓名
	 */
	@NotBlank(message="用户姓名不能为空")
	@Length(min=2,max=15,message="真实姓名只能由汉字、数字、字母或者下划线组成，长度在2-10之间。")
	private String trueName;
	/**
	 * 备注
	 */
	@Length(min=0, max=50, message="用户备注长度不能大于50。")
	private String userDesc;
	/**
	 * 性别,1:男，0：女
	 */
	@NotNull(message="性别不能为空")
	@Range(min=0,max=1,message="性别格式错误")
	private Short userSex;
	/**
	 * 注册用户人id
	 */
	private String registerUid;
	/**
	 * 手机号
	 */
	@Pattern(regexp="^|\\d{11}$",message="手机号格式错误")
	private String mobilePhoneNumber;

	// Constructors

	/** default constructor */
	public SyUsers() {
	}

	/** minimal constructor */
	public SyUsers(String userName, String userPassword, Short userStatus,
			String deptId, String trueName, Short userSex, String registerUid) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.deptId = deptId;
		this.trueName = trueName;
		this.userSex = userSex;
		this.registerUid = registerUid;
	}

	/** full constructor */
	public SyUsers(String userName, String userPassword,
			Timestamp registerTime, Timestamp lastLoginTime,
			String lastLoginIp, Timestamp errorTime, Short errorCount,
			Short userStatus, String deptId, String trueName, String userDesc,
			Short userSex, String registerUid,String mobilePhoneNumber) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.registerTime = registerTime;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.errorTime = errorTime;
		this.errorCount = errorCount;
		this.userStatus = userStatus;
		this.deptId = deptId;
		this.trueName = trueName;
		this.userDesc = userDesc;
		this.userSex = userSex;
		this.registerUid = registerUid;
		this.mobilePhoneNumber=mobilePhoneNumber;
		
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
	@Column(name = "user_name", nullable = false, length = 15)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "user_password", nullable = false, length = 32)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Column(name = "register_time", length = 19)
	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	@Column(name = "last_login_time", length = 19)
	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Column(name = "last_login_ip",	length = 50)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Column(name = "error_time",	length = 19)
	public Timestamp getErrorTime() {
		return this.errorTime;
	}

	public void setErrorTime(Timestamp errorTime) {
		this.errorTime = errorTime;
	}
	@Column(name = "error_count")
	public Short getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Short errorCount) {
		this.errorCount = errorCount;
	}
	@Column(name = "user_status", nullable = false)
	public Short getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Short userStatus) {
		this.userStatus = userStatus;
	}
	@Column(name = "dept_id", nullable = false, length = 32)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name = "true_name", nullable = false, length = 15)
	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	@Column(name = "user_desc", length = 50)
	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	@Column(name = "user_sex", nullable = false)
	public Short getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}
	@Column(name = "register_uid", nullable = false, length = 32)
	public String getRegisterUid() {
		return this.registerUid;
	}

	public void setRegisterUid(String registerUid) {
		this.registerUid = registerUid;
	}
	@Column(name = "mobile_phone_number", length = 11)
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}




}