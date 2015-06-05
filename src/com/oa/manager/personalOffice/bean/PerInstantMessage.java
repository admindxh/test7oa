package com.oa.manager.personalOffice.bean;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 即时消息表对应实体
 */

public class PerInstantMessage implements java.io.Serializable {

	// Fields

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 发送人id
	 */
	private String sendUid;
	/**
	 * 发送人姓名
	 */
	private String sendName;
	/**
	 * 接收人id
	 */
	@NotBlank(message="接收人不能为空")
	private String receiveUid;
	/**
	 * 接收人姓名
	 */
	private String receiveName;
	/**
	 * 发送时间
	 */
	private Timestamp createTime;
	/**
	 * 消息内容
	 */
	@NotBlank(message="消息内容不能为空")
	@Length(min=1, max=65530, message="消息内容内容长度限制65530字节")
	private String messageContent;
	/**
	 * 消息读取状态，1：已读，0：未读
	 */
	private Short readState;

	// Constructors

	/** default constructor */
	public PerInstantMessage() {
	}

	/** minimal constructor */
	public PerInstantMessage(String sendUid, String receiveUid,
			Timestamp createTime, Short readState) {
		this.sendUid = sendUid;
		this.receiveUid = receiveUid;
		this.createTime = createTime;
		this.readState = readState;
	}

	/** full constructor */
	public PerInstantMessage(String sendUid, String receiveUid,
			Timestamp createTime, String messageContent, Short readState) {
		this.sendUid = sendUid;
		this.receiveUid = receiveUid;
		this.createTime = createTime;
		this.messageContent = messageContent;
		this.readState = readState;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendUid() {
		return this.sendUid;
	}

	public void setSendUid(String sendUid) {
		this.sendUid = sendUid;
	}

	public String getReceiveUid() {
		return this.receiveUid;
	}

	public void setReceiveUid(String receiveUid) {
		this.receiveUid = receiveUid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Short getReadState() {
		return this.readState;
	}

	public void setReadState(Short readState) {
		this.readState = readState;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}