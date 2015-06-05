/**  
 * @Project: jxoa
 * @Title: MyException.java
 * @Package com.oa.commons.exception
 * @date 2013-6-30 下午5:43:15
 * @Copyright: 2013 
 */
package com.oa.commons.exception;

/**
 * 
 * 类名：MyException
 * 功能：自定义异常
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-30 下午5:43:15
 *
 */
public class MyRuntimeException extends RuntimeException{

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 创建一个新的实例 MyException.
	 *
	 * @param message 异常信息
	 */
	public MyRuntimeException(String message) {
		super(message);
	}
	
	
}
