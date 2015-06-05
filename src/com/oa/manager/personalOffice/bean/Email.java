/**  
 * @Project: jxoa
 * @Title: Email.java
 * @Package com.oa.manager.personalOffice.bean
 * @date 2013-5-29 下午05:57:21
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.bean;

import java.util.Date;

/**
 * 
 * 类名：Email
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-29 下午05:57:21
 *
 */
public class Email {
//	public String id;			//eif.getEmInbox().getId()
//	public String status;		//eif.getStatus()
//	public String eiName;		//eif.getEmInbox().getEiName()
//	public String efName;		//eif.getEmFiles().getEfName()
//	public String eiDate;		//eif.getEmInbox().getEiDate()
//	public String efSize;		//eif.getEmFiles().getEfSize()
	
	public EmInbox emInbox;
	public EmOutbox emOutbox;
	public EmDraftbox emDraftbox;
	public EmFiles emFiles;
	public EmInboxOutboxUser emInboxOutboxUser; 
	
	public String name;
	public Date  date1;
	public Date  date2;
	
	public EmInbox getEmInbox() {
		return emInbox;
	}
	public void setEmInbox(EmInbox emInbox) {
		this.emInbox = emInbox;
	}
	public EmFiles getEmFiles() {
		return emFiles;
	}
	public void setEmFiles(EmFiles emFiles) {
		this.emFiles = emFiles;
	}
	public EmInboxOutboxUser getEmInboxOutboxUser() {
		return emInboxOutboxUser;
	}
	public void setEmInboxOutboxUser(EmInboxOutboxUser emInboxOutboxUser) {
		this.emInboxOutboxUser = emInboxOutboxUser;
	}
	public EmOutbox getEmOutbox() {
		return emOutbox;
	}
	public void setEmOutbox(EmOutbox emOutbox) {
		this.emOutbox = emOutbox;
	}
	public EmDraftbox getEmDraftbox() {
		return emDraftbox;
	}
	public void setEmDraftbox(EmDraftbox emDraftbox) {
		this.emDraftbox = emDraftbox;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	
	
}
