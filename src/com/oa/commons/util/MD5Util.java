package com.oa.commons.util;

import java.security.MessageDigest;
/**
 * 
 * 
 * 类名：MD5Util
 * 功能：MD5加密
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-3-28 下午3:16:53
 *
 */
public class MD5Util {
	
	/**
	 * 加密
	 * @param s
	 * @return
	 */
	public  static String MD5(String s) {
		
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
	/**
	 * 比较加密是否一致
	 * @param str
	 * @param md5Str
	 * @return
	 */
	public static boolean MD5Validate(String str,String md5Str){
		
		if (md5Str==null||md5Str.length()==0){
			return false;
		}
		if(md5Str.equals(MD5(str))){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		String str = MD5("1");
		
	
		System.out.println(str+"==="+MD5Validate("1",str));
		
	}
}
