/**  
 * @Project: jtoa
 * @Title: SerialNumberUtil.java
 * @Package com.oa.commons.util
 * @date 2013-10-15 下午2:37:03
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.security.MessageDigest;
import java.util.Date;

/**
 * 
 * 类名：SerialNumberUtil
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-10-15 下午2:37:03
 *
 */
public class SerialNumberUtil {
	 
	/**
	 * 加密
	 * @param s
	 * @return
	 */
	private  static String MD5(String s) {
		
		if (s==null||s.length()==0){
			return null;
		}
		char hexDigits[] = { 'A', '1', 'B', '3', 'C', '5', 'D', '7', 'E','9', 'F', '0', 'G', '2', 'H', '4' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	private  static String MD5(long l) {
		
		String s = DateUtil.date2String(new Date(l));
		if (s==null||s.length()==0){
			return null;
		}
		char hexDigits[] = { 'A', '1', 'B', '3', 'C', '5', 'D', '7', 'E','9', 'F', '0', 'G', '2', 'H', '4' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
 
	/***
	 * 获取key:MD5方式 
	 * @param clientname:客户名称
	 * @param clientcode:客户代码
	 * @param validstart:有效期起
	 * @param validend:有效期止
	 * @param validday:有效时间
	 * @return
	 */
	private static String makeKey(String clientname,String clientcode ,String validstart,String validend,String validday ){
		StringBuffer sb = new StringBuffer("");
		sb.append(MD5(clientname)).append("-");
		sb.append(MD5(clientcode)).append("-");
		sb.append(MD5(validstart));
		sb.append(MD5(validend)).append("-");
		sb.append(MD5(validday));
		return sb.toString();
	}
	/***
	 * 验证信息是否与key信息一致:MD5方式
	 * @param clientname:客户名称
	 * @param clientcode:客户代码
	 * @param validstart:有效期起
	 * @param validend:有效期止
	 * @param validday:有效时间
	 * @param key:注册信息
	 * @return
	 */
	public static boolean verificationkey(String clientname,String clientcode ,long validstart,long validend,String validday,String key){
		StringBuffer sb = new StringBuffer("");
		sb.append(MD5(clientname)).append("-");
		sb.append(MD5(clientcode)).append("-");
		sb.append(MD5(validstart));
		sb.append(MD5(validend)).append("-");
		sb.append(MD5(validday));
		//比较加密后信息与key是否一致
		boolean iskey = key.equals(sb.toString());
		if(iskey){
			/*验证有效期
			 * */
			//获取本地系统时间 
			Date localdate =new Date();
			//String localdate = dateFormat.format(new Date());
			//System.out.println(localdate);
			// 
			//判断当前时间是否在注册有效期内
			if((validstart<localdate.getTime())&&(validend>localdate.getTime())){
				//获取有效使用时间
			//	long useday =  ((validendDate.getTime()-validstartDate.getTime())/(24*60*60*1000));
				
			}else{
				//系统时间不在有效期内
				return false;
			}
		 
			return true;
		}else{
			return false;
		} 
	}
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		String key = makeKey("dalian", "code201403", "2014-02-27", "2014-05-01", "61");
		System.out.println("key:"+key);
		long start = DateUtil.string2Date("2014-02-27").getTime();
		
		long end = DateUtil.string2Date("2014-05-01").getTime();
		Boolean flag = verificationkey("dalian", "code201403", start, end, "61", key);
		System.out.println(flag);
		 
	}

}
