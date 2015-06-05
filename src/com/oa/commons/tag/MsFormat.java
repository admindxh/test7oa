/**  
 * @Project: jxoa
 * @Title: MsFormat.java
 * @Package com.oa.commons.tag
 * @date 2013-7-26 下午4:14:09
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
 * 类名：MsFormat
 * 功能：毫秒人性化格式
 * 详细：毫秒数格式化为具体的几天，几小时，几分钟，不是时间格式化
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-26 下午4:14:09
 *
 */
public class MsFormat extends SimpleTagSupport{
	
	private Long milliSecond;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 
		 out.print(formt(milliSecond));
			
	 }
	
	public String formt(Long mis) {
		if(mis==null){
			return "";
		}else if(mis==0){
			return "";
		}
		else if(mis<1000){
			return "1秒";
		}
		long second = 1000;//秒
		
		long minute = second*60;//分钟
		
		long hour = minute*60;//小时
		
		long day = hour*24;//天
		
		StringBuffer sb=new StringBuffer();
		if(mis>day){
			int d=(int) (mis/day);
			sb.append(d+"天");
			mis=mis-day*d;
		}
		if(mis>hour){
			int h=(int)(mis/hour);
			
			sb.append(h+"小时");
			
			mis=mis-hour*h;
		}
		if(mis>minute){
			int m=(int)(mis/minute);
			sb.append(m+"分钟");
			mis=mis-minute*m;
		}
		sb.append((int)(mis/1000)+"秒");
	
		return sb.toString();
	}

	public void setMilliSecond(Long milliSecond) {
		this.milliSecond = milliSecond;
	}
	
	
}
