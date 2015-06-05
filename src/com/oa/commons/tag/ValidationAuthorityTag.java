/**  
 * @Project: jxoa
 * @Title: ValidationAuthorityTag.java
 * @Package com.oa.commons.tag
 * @date 2013-6-7 上午11:00:23
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.shiro.SecurityUtils;

import com.oa.commons.util.ServletUtil;

/**
 * 
 * 类名：ValidationAuthorityTag
 * 功能：验证操作权限
 * 详细：判断当前用户是否拥有请求的url权限，如果有，则输出标签内容，相当于if条件判断
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-7 上午11:00:23
 *
 */
public class ValidationAuthorityTag extends TagSupport{
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * 标签内包含的操作的请求url
	 */
	private String url;
	
	@Override
	public int doStartTag() throws JspException { //对标签内容的处理
		// TODO 自动生成方法存根
		if(ServletUtil.isDeveloper()||SecurityUtils.getSubject().isPermitted(url)){
			return EVAL_BODY_INCLUDE;//标签内包含的内容被正常执行
		}
		return SKIP_BODY ; //不显示该标签之间的内容
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
