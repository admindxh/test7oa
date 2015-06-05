/**  
 * @Project: jxoa
 * @Title: Test.java
 * @Package com.oa.test
 * @date 2013-4-2 上午10:47:50
 * @Copyright: 2013 
 */
package com.oa.test;



import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

/**
 * 
 * 类名：Test
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-2 上午10:47:50
 *
 */
public class Test extends TestCase{
	
	public void test() {
			File file=new File("d:/s/a/c.text");
			System.out.println("=="+file.getPath());
			
			
	}
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
		long l =  new Date().getTime();
		Time time = new Time(l-(1000*60*60*5));
		Time time1 = new Time(l); 
		 
		Date nowTime=new Date();
		 
		/*System.out.println(sdf.format(nowTime));
		System.out.println(sdfd.format(nowTime));
		System.out.println(sdft.format(nowTime));
		System.out.println(time);
		System.out.println(time1);
		System.out.println(time.getTime());
		System.out.println(time1.getTime());
		long hsl =DateUtil.string2long("12:12:12","HH:mm:ss");
		System.out.println(hsl);
		Date s = new Date(hsl);
		System.out.println(s);*/
		/*
		long cha =DateUtil.subtractTime("16:12:12", "12:12:12");
		System.out.println(cha);
		
		String nowtime = DateUtil.date2String(new Date(), DateUtil.PATTERN_TIME);
		System.out.println(nowtime);
		long l1 = DateUtil.string2long(nowtime,DateUtil.PATTERN_TIME);
		long l2 = DateUtil.string2long("12:12:12",DateUtil.PATTERN_TIME);
		System.out.println(l1);
		System.out.println(l2); */
		 /*
        
        System.out.println("***MAC地址***");  
          
        String sn = getSerialNumber("C");   
        System.out.println("***硬盘编号***");  
        System.out.println(sn); 
        String sn2 = getSerialNumber("C");   
        System.out.println("***硬盘编号***");  
        System.out.println(sn2);   */
		
	   Date date=new Date();

	   SimpleDateFormat dateFm = new SimpleDateFormat("E");

	   String str = dateFm.format(date);
	   System.out.println(str);
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(date);
	   int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	   System.out.println(w);
	   //星期五 5
	   //星期六 6
	    //星期日 0
	  
	}
	 
	 public static String getSerialNumber(String drive) {
		  String result = "";
		    try {
		       
		    }
		    catch(Exception e){
		        e.printStackTrace();
		    }
		    return result.trim();
		  }
	 
	 
	
	
}
