/**  
 * @Project: jxoa
 * @Title: OutSelectOptionTag.java
 * @Package com.oa.commons.tag
 * @date 2013-6-7 下午3:06:58
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

import com.oa.commons.cache.MyCache;

/**
 * 
 * 类名：OutSelectOptionTag
 * 功能：根据字典值类型，以下拉列表方式输出字典值
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-7 下午3:06:58
 *
 */
public class OutSelectOptionTag extends SimpleTagSupport{
	/**
	 * 字典值类型
	 */
	private String type;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 if(StringUtils.isBlank(type)){
			out.print(""); 
		 }else{
			 List<Map<String,String>> list= MyCache.getInstance().getListValues(Integer.parseInt(type));
			 if(list!=null){
				 for(Map<String,String> m:list){
					 out.print("<option value=\""+m.get("value")+"\">"+m.get("name")+"</option>");
				 }
			 }
		 }
	 }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
