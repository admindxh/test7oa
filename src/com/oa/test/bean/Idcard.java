package com.oa.test.bean;

/**
 * Idcard entity. @author MyEclipse Persistence Tools
 */

public class Idcard implements java.io.Serializable {

	// Fields

	private String uid;
	private User user;
	private String address;

	// Constructors

	/** default constructor */
	public Idcard() {
	}

	/** minimal constructor */
	public Idcard(User user) {
		this.user = user;
	}

	/** full constructor */
	public Idcard(User user, String address) {
		this.user = user;
		this.address = address;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}