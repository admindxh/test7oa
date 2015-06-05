/**  
 * @Project: jxoa
 * @Title: FileSizeTag.java
 * @Package com.oa.commons.tag
 * @date 2013-6-5 下午4:36:40
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.oa.commons.util.FileUtils;

/**
 * 
 * 类名：FileSizeFormatTag
 * 功能：文件大小格式化
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-5 下午4:36:40
 *
 */
public class FileSizeFormatTag extends SimpleTagSupport{
	/**
	 * 文件大小,单位b
	 */
	private Long value;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 out.print(FileUtils.getHumanReadableFileSize(value));
		 
	 }

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
