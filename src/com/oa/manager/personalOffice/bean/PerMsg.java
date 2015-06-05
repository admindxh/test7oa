package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;

/**
 * 系统消息提醒表对应实体
 */

public class PerMsg implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 提醒用户id
	 */
	private String userId;
	/**
	 * 消息类型
	 */
	private Short msgType;
	/**
	 * 提醒模块内容表id
	 */
	private String tableId;
	/**
	 * 发送时间
	 */
	private Timestamp msgTime;
	/**
	 * 消息读取状态，1：已读，0：未读
	 */
	private Short readState;

	// Constructors

	/** default constructor */
	public PerMsg() {
	}

	/** minimal constructor */
	public PerMsg(String userId, Short msgType, String tableId) {
		this.userId = userId;
		this.msgType = msgType;
		this.tableId = tableId;
	}

	/** full constructor */
	public PerMsg(String userId, Short msgType, String tableId,
			Timestamp msgTime, Short readState) {
		this.userId = userId;
		this.msgType = msgType;
		this.tableId = tableId;
		this.msgTime = msgTime;
		this.readState = readState;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Short getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public Timestamp getMsgTime() {
		return this.msgTime;
	}

	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}

	public Short getReadState() {
		return this.readState;
	}

	public void setReadState(Short readState) {
		this.readState = readState;
	}

}