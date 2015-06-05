/**  
 * @Title isSuperAdminTag.java
 * @date 2013-12-9 下午5:25:45
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.oa.commons.util.ServletUtil;

/**
 * 判断是否是系统超级管理员
 * 如果是超级管理员 则输出标签内包括的内容
 * @author LiuJincheng
 * @version 1.0
 */
public class IsSuperAdminTag extends TagSupport{
	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException { //对标签内容的处理
		
		if(ServletUtil.isSuperAdmin()){
			return EVAL_BODY_INCLUDE;//标签内包含的内容被正常执行
		}
		return SKIP_BODY ; //不显示该标签之间的内容
	}
}
