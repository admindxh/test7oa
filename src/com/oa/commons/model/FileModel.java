/**  
 * @Project: jxoa
 * @Title: FileModel.java
 * @Package com.oa.commons.model
 * @date 2013-4-22 上午8:55:17
 * @Copyright: 2013 
 */
package com.oa.commons.model;


/**
 * 
 * 类名：FileModel
 * 功能：文件保存模型
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-22 上午8:55:17
 *
 */
public class FileModel {
	/**
	 * 文件名称	包括后缀
	 */	
	private String name;
	/**
	 * 文件保存到服务器的名称，32位UUID 不包括后缀
	 */
	private String uuid;
	/**
	 * 后缀
	 */
	private String ext;
	/**
	 * 文件大小 
	 */
	private Long size;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	

	

}
