/**  
 * @Project: jxoa
 * @Title: DateUtil.java
 * @Package com.oa.commons.util
 * @date 2013-4-12 下午1:55:18
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.oa.commons.exception.MyRuntimeException;

/**
 * 
 * 类名：DateUtil
 * 功能：时间格式化
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-12 下午1:55:18
 *
 */
public class DateUtil {
	/**
	 * 默认 日期时间 格式  yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 默认 日期格式  yyyy-MM-dd
	 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * 默认 时间格式
	 */
	public static final String PATTERN_TIME = "HH:mm:ss";
	/**
	 * 每月1日
	 */
	public static final String PATTERN_MONTH = "yyyy-MM-01";
	/**
	 * 自动匹配字符串格式
	 */
	public  static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };
	
	/**
	 * Timestamp 格式化成字符串，使用默认格式  yyyy-MM-dd HH:mm:ss
	 * @param timestamp
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return DateFormatUtils.format(timestamp, PATTERN_STANDARD);
	}
	/**
	 * Timestamp 格式化 自定义格式
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			return null;
		}
		return DateFormatUtils.format(timestamp, pattern);
	}
	/**
	 * Date 格式化成字符串，使用默认格式  yyyy-MM-dd 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, PATTERN_DATE);
	}
	/**
	 * Date 格式化 自定义格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.format(date, pattern);
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 获取当前日期yyyy-MM-dd String
	 * @return
	 */
	public static String currentDateToString( ) {
		
		return date2String(new Date());
	}
	
	/**
	 * 获取当前时间 HH:mm:ss String类型
	 * @return 
	 */
	public static String currentTimeToString( ) {
		 
		return date2String(new Date(),PATTERN_TIME);
		 
	}
	/**
	 * 获取当前日期yyyy-MM-dd  HH:mm:ss String
	 * @return
	 */
	public static String currentDateTimeToString( ) {
		 
		return date2String(new Date(),PATTERN_STANDARD);
	}
	/**
	 * 两个时间相减
	 * @param firsttime
	 * @param secondtime
	 * @return
	 */
	public static long subtractTime(String firsttime ,String secondtime ) {
		 
		return string2Date(firsttime,PATTERN_TIME).getTime() -string2Date(secondtime,PATTERN_TIME).getTime();
		 
	}
	/**
	 * 字符串转换为 Timestamp  自动匹配格式
	 * @param strDateTime
	 * @return 如果传入字符串为null，或者空字符串，则返回null
	 */
	public static Timestamp string2Timestamp(String strDateTime) {
		
		return new Timestamp(string2Date(strDateTime).getTime());
	}
	/**
	 * 字符串 转换为 Timestamp  传入字符串格式 
	 * @param strDateTime
	 * @param pattern
	 * @return
	 */
	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		
		return new Timestamp(string2Date(strDateTime, pattern).getTime());
	}
	/**
	 * 字符串转换为 Date  自动匹配格式
	 * @param strDate
	 * @return 如果传入字符串为null，或者空字符串，则返回null
	 */
	public static Date string2Date(String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return DateUtils.parseDate(strDate.trim(),parsePatterns);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new MyRuntimeException("日期类型转换错误");
		}
	}
	/**
	 * 字符串转换为 Date  传入字符串格式
	 * @param strDate 时间
	 * @param pattern 字符串 格式
	 * @return 如果传入字符串为null，或者空字符串，则返回null 
	 */
	public static Date string2Date(String strDate, String pattern) {
		
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return DateUtils.parseDate(strDate.trim(),new String[]{pattern});
		} catch (ParseException e) {
			e.printStackTrace();
			throw new MyRuntimeException("日期类型转换错误");
		}
	}
	
	/**
	 * 前后移动日期
	 * @param date
	 * @param move -1:向前移动一天，1:向后移动一天
	 * @return
	 */
	public static Date moveDate(Date date,int move){
		
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,move);//把日期往后增加一天.整数往后推,负数往前移动
		 return calendar.getTime(); //这个时间就是日期移动之后的时间
		
	}
	/***
	 * 时间字符串转换成long
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static long string2long(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			return 0l;
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		Date d=string2Date(strDate,pattern);
		if(d==null){
			return 0L;
		}
		return d.getTime();
	}
	
	/**
	 * 获得当前月初日期 yyyy-MM-01
	 * @return
	 */
	public static String currentMonthDateToString( ) {
	
		return date2String(new Date(), PATTERN_MONTH);
	}
	/**
	 * 获取当天开始时间
	 * @return
	 */
	public static String currentDayDateStartToString(String stime ) {
		//(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		Date d= string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		
		return date2String(d, "yyyy-MM-dd 00:00:01");
	}
	/**
	 * 获取当天结束时间
	 * @return
	 */
	public static String currentDayEndDateToString(String stime ) {
		//(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		Date d= string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);
		
		return date2String(d, "yyyy-MM-dd 23:59:59");
	}
	
	public static void main(String[] args){
		
		//System.out.println(moveDate(new Date(),1).toLocaleString());
		
		/*String t=date2String(new Date(), null);
		
		System.out.println(t);
		
		Timestamp end=string2Timestamp(t+" 23:59:59", null);
		
		System.out.println(end);
		
		System.out.println(string2Timestamp("2013-10-20 00:00:00", null).getTime());
	    
	
		System.out.println("=========="+Pattern.matches("^192.168.1.*$", "192.168.2.1"));
	
	
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
		System.out.println(sdf.format(0l));*/
	
		System.out.println(currentDayEndDateToString("2013-10-20 12:01:24"));
		
	}
	
	
}
