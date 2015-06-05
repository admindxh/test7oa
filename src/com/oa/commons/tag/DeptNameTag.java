/**  
 * @Project: jxoa
 * @Title: DeptNameTag.java
 * @Package com.oa.commons.tag
 * @date 2013-6-6 下午9:57:06
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.oa.commons.cache.MyCache;

/**
 * 
 * 类名：DeptNameTag
 * 功能：根据部门id，输出部门名称
 * 详细：从缓存中获取
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-6 下午9:57:06
 *
 */
public class DeptNameTag extends SimpleTagSupport{
	/**
	 * id
	 */
	private String id;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 
		 out.print(MyCache.getInstance().getDeptName(id));
			
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
