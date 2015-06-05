/**  
 * @Project: jxoa
 * @Title: 测试.java
 * @Package com.oa.test
 * @date 2013-7-9 下午11:11:06
 * @Copyright: 2013 
 */
package com.oa.test;

import java.util.Date;

import com.oa.commons.util.DateUtil;

import junit.framework.TestCase;

/**
 * 
 * 类名：测试 功能： 详细： 作者：LiuJincheng 版本：1.0 日期：2013-7-9 下午11:11:06
 * 
 */
public class 测试 extends TestCase {

	public void 运行测试() {

		中文方法();
		formt(1374825880019L);
	}

	private void 中文方法() {

		System.out.println("中文...");

		
			
		
		Date date = new Date();
		date.setTime(13821984000000L);
		
		
		
		System.out.println(DateUtil.date2String(date, null));

	}

	public static void formt(long mis) {
		
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
		
		System.out.println(sb);
	}
}
