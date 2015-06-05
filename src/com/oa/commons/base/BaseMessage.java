/**  
 * @Project: jxoa
 * @Title: BaseMessage.java
 * @Package com.oa.commons.base
 * @date 2013-7-16 下午4:16:10
 * @Copyright: 2013 
 */
package com.oa.commons.base;

/**
 * 
 * 类名：BaseMessage
 * 功能：消息状态
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-16 下午4:16:10
 *
 */
public class BaseMessage {
	
	/**
	 * statusCode:消息状态码  300：操作失败，301：登录超时
	 */
	private Integer statusCode;

	/**
	 * message:提醒信息
	 */
	private String message;
	
	/**
	 * 返回的信息对象
	 */
	private Object obj;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
