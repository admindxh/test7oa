/**  
 * @Title: LoginUserNameTag.java
 * @date 2013-10-10 下午4:19:56
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.oa.commons.cache.MyCache;
import com.oa.commons.model.Member;
import com.oa.commons.util.ServletUtil;

/**
 * 输出当前登陆用户 信息
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public class LoginUserInfoTag extends SimpleTagSupport{
	/**
	 * 获取信息类型
	 * userId 
	 * userName 登陆账号
	 * trueName 姓名
	 * deptId 	所属部门id
	 * deptName	所属部门名称
	 */
	private String type;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 Member me=ServletUtil.getMember();
		 if("userId".equals(type)){
			 out.print(me.getId());
		 }else  if("userName".equals(type)){
			 out.print(MyCache.getInstance().getUserName(me.getId()));
		 }else  if("trueName".equals(type)){
			 out.print(MyCache.getInstance().getTrueName(me.getId()));
		 }else  if("deptId".equals(type)){
			 out.print(me.getDeptId());
		 }else  if("deptName".equals(type)){
			 out.print(MyCache.getInstance().getDeptName(me.getDeptId()));
		 }
		 
	 }

	public void setType(String type) {
		this.type = type;
	}
}
