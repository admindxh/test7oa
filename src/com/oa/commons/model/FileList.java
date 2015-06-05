/**  
 * @Project: jxoa
 * @Title: FileList.java
 * @Package com.oa.commons.model
 * @date 2013-5-31 下午4:05:37
 * @Copyright: 2013 
 */
package com.oa.commons.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 类名：FileList
 * 功能：多文件上传模型
 * 详细：单文件接收：@RequestParam MultipartFile file
 * 		 多文件接收:FileList files
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-31 下午4:05:37
 *
 */
public class FileList {
	/**
	 * 上传文件集合
	 */
	List<MultipartFile> file;
	
	
	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	
	
}
