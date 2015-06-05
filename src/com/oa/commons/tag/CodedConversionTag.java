/**  
 * @Project: jxoa
 * @Title: CodedConversionTag.java
 * @Package com.oa.commons.tag
 * @date 2013-4-25 上午11:00:41
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类名：CodedConversionTag
 * 功能：编码转换标签
 * 详细：用于url传递字符串，编码转换，默认将ISO-8859-1编码转换为utf-8，也可自定义转换类型
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-25 上午11:00:41
 *
 */
public class CodedConversionTag extends SimpleTagSupport{
	/**
	 * 需要转换的字符串
	 */
	private String str;
	/**
	 * 字符串原编码
	 */
	private String oldType;
	/**
	 * 要转换成的编码
	 */
	private String toType;
	/**
	 * 如果需要转换的字符串为空，可输出此默认值，如果默认值也为空，则输出空字符串
	 */
	private String defaultValue;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		
		 if(StringUtils.isBlank(str)){
			 if(StringUtils.isBlank(defaultValue)){
				 out.print("");
			 }else{
				 out.print(defaultValue);
			 }
		 }else{
			 if(StringUtils.isBlank(oldType)){
				 oldType="ISO-8859-1";
			 }
			 if(StringUtils.isBlank(toType)){
				 toType="utf-8";
			 }
			out.print(new String(str.getBytes(oldType),toType));
		 }
	 }

	public void setStr(String str) {
		this.str = str;
	}

	public void setOldType(String oldType) {
		this.oldType = oldType;
	}

	public void setToType(String toType) {
		this.toType = toType;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}


	
}
