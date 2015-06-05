/**  
 * @Project: jxoa
 * @Title: SelectListValueTag.java
 * @Package com.oa.commons.tag
 * @date 2013-6-7 下午3:56:41
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

import com.oa.commons.cache.MyCache;

/**
 * 
 * 类名：SelectListValueTag
 * 功能：根据字典值id，输出字典值
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-7 下午3:56:41
 *
 */
public class SelectListValueTag extends SimpleTagSupport{

	private String id;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 if(StringUtils.isBlank(id)){
			out.print(""); 
		 }else{
			out.print(MyCache.getInstance().getSelectValue(id));
		 }
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
