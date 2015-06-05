/**  
 * @Project: jxoa
 * @Title: ScriptEscapeTag.java
 * @Package com.oa.commons.tag
 * @date 2013-5-22 下午2:53:44
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
 * 类名：ScriptEscapeTag
 * 功能：script标签转换
 * 详细：将script 转义，防止执行js代码
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-22 下午2:53:44
 *
 */
public class ScriptEscapeTag extends SimpleTagSupport{
	/**
	 * 需要转换的字符串
	 */
	private String value;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 if(StringUtils.isBlank(value)){
			out.print(""); 
		 }else{
			
			out.print(value.replaceAll("<script", "&lt;script").replaceAll("</script", "&lt;/script"));
		 }
	 }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
