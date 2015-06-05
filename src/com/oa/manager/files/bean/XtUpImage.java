package com.oa.manager.files.bean;

import java.sql.Timestamp; 
import java.util.Set; 
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * XtUpImage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xt_up_image")
public class XtUpImage implements java.io.Serializable {

	// Fields

	private String id;
	//标题
	private String title;
	//内容
	private String content;
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	//地址
	private String address;
	//上传时间
	private String uptime;
	//修改时间
	private Timestamp modifytime;
	//修改人
	private String modifyuser;
	//创建人
	private String createuser;
	//创建时间
	private Timestamp createtime;
 

	// Constructors

	/** default constructor */
	public XtUpImage() {
	}

	/** full constructor */
	public XtUpImage(String title, String content, Double longitude,
			Double latitude, String address, String uptime,
			Timestamp modifytime, String modifyuser, String createuser,
			Timestamp createtime) {
		this.title = title;
		this.content = content;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.uptime = uptime;
		this.modifytime = modifytime;
		this.modifyuser = modifyuser;
		this.createuser = createuser;
		this.createtime = createtime; 
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

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "longitude", precision = 10, scale = 6)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", precision = 10, scale = 6)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "uptime", length = 20)
	public String getUptime() {
		return this.uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	@Column(name = "modifytime", length = 19)
	public Timestamp getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Column(name = "modifyuser", length = 32)
	public String getModifyuser() {
		return this.modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}

	@Column(name = "createuser", length = 32)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Column(name = "createtime", length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	 

}