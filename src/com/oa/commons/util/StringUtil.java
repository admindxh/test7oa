/**  
 * @Project: jxoa
 * @Title: StringUtil.java
 * @Package com.oa.commons.util
 * @date 2013-6-6 下午03:40:21
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;
 

/**
 * 
 * 类名：StringUtil
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-6-6 下午03:40:21
 *
 */
public class StringUtil {

	
	

	/** 在字符串左侧补齐空格 */
	public final static int LEFT_SPACE = 0;
	/** 在字符串右侧补齐空格 */
	public final static int RIGHT_SPACE = 1;
	/** 如果字符串实际长度超出指定长度，将左侧截断 */
	public final static int TRUNC_LEFT = 0;
	/** 如果字符串实际长度超出指定长度，将右侧截断 */
	public final static int TRUNC_RIGHT = 1;

	/**
	 * 该方法去掉字符串的左边空格
	 * @param str   需要去掉左边空格的字符串
	 * @return String  已经去掉左边空格的字符串
	 */
	public static String leftTrim(String str) {
		if (str == null)
			return "";

		byte[] bytes = str.getBytes();
		int index = 0;
		byte ch;
		do {
			ch = bytes[index];
			if (ch != ' ')
				break;
			index++;
		} while (true);
		return str.substring(index);
	}

	/**
	 * 改方法去掉字符串的右边空格
	 * @param str   需要去掉右边空格的字符串
	 * @return String  已经去掉右边空格的字符串
	 */
	// duan_wliang update 2004-05-17
	public static String rightTrim(String str) {
		if (str == null)
			return "";

		byte[] bytes = str.getBytes();
		int index = StringUtil.length(str);

		String tmpStr = str.trim();
		if (index == 0)
			return "";

		index = index - 1;
		byte ch;
		do {
			ch = bytes[index];
			if (ch != ' ')
				break;
			index--;
		} while (index >= 0);

		return new String(str.getBytes(), 0, index + 1);
		//return str.substring(0, index + 1);

	}

	/**
	 *
	 * 将字符串中所有的空格删除，包括左边、右边、中间。
	 * @param str
	 */
	public static String allTrim(String str) {
		if (str == null) return "";
		String tmp = str.trim();
		if(tmp.equals("")) return "";
		int idx = 0;
		int len = 0;
		len = tmp.length();
		idx = tmp.indexOf(" ");
		while(idx > 0) {
			tmp = tmp.substring(0, idx) + tmp.substring(idx+1, len);
			idx = tmp.indexOf(" ");
			len = tmp.length();
		}

		return tmp;
	}

	/**
	 * 字符串去除前后空格　解决 String tirm()方法　对全角空格无效的问题
	 * @param orin 需要进行处理的字符串
	 * @return String 处理完成的结果字符串
	 */
	public static String trim(String str) {
		if(str!=null){
			str=str.trim();//去除前后半角空格
			//去除前后全角半角空格
			while(str.startsWith("　")){
				str = str.substring(1,str.length()).trim();   
			}   
		    while(str.endsWith("　")){   
		    	str = str.substring(0,str.length()-1).trim();   
			}
			 
		}
		return str;
	}

	/**
	 * 该方法将传入的字符串扩充为指定长度的字符串,长度不足的话,补充空格
	 * 如果字符串长度大于指定的长度,就截断超出的那部分字符.
	 * @param str 需要处理的字符串
	 * @param len 指定的字符串长度
	 * @param direct    StringUtil.LEFT_SPACE    从左侧补充空格
	 *                   StringUtil.RIGHT_SPACE   从右侧补充空格
	 * @param truncWay  StringUtil.TRUNC_LEFT    从左侧截断空格
	 *                   StringUtil.TRUNC_RIGHT   从右侧截断空格
	 * @return 返回指定格式的字符串
	 */
	public static String alignStr(
		String str,
		int len,
		int direct,
		int truncWay) {

		return alignStr(str, len, direct, truncWay, ' ');

	}

	/**
	 * 该方法将传入的字符串扩充为指定长度的字符串,长度不足的话,以 fixStr 补充
	 * 如果字符串长度大于指定的长度,就截断超出的那部分字符.
	 * @param str 需要处理的字符串
	 * @param len 指定的字符串长度
	 * @param direct    StringUtil.LEFT_SPACE    从左侧补充空格
	 *                   StringUtil.RIGHT_SPACE   从右侧补充空格
	 * @param truncWay  StringUtil.TRUNC_LEFT    从左侧截断空格
	 *                   StringUtil.TRUNC_RIGHT   从右侧截断空格
	 * @param fixStr 用来填充的的字符
	 * @return 返回指定格式的字符串
	 */
	public static String alignStr(
		String str,
		int len,
		int direct,
		int truncWay,
		char fixStr) {

		/*
				if (str == null)
					return "";

				int l = length(str);
				if (l >= len) {
					if (truncWay == StringUtil.TRUNC_LEFT)
						return str.substring(l - len, l);
					else
						return str.substring(0, len);
				}

				StringBuffer sb = new StringBuffer(str);
				for (int i = l; i < len; i++) {
					if (direct == StringUtil.RIGHT_SPACE)
						sb = sb.insert(0, fixStr);
					else
						sb = sb.append(fixStr);
				}
				return sb.substring(0);
		*/
		if (str == null)
			return "";

		byte[] b = StringUtil.getBytes(str);
		int l = StringUtil.length(str);
		if (l >= len) {
			if (truncWay == StringUtil.TRUNC_LEFT)
				return new String(b, l - len, len);
			else
				return new String(b, 0, len);
		}

		StringBuffer sb = new StringBuffer(str);
		for (int i = l; i < len; i++) {
			if (direct == StringUtil.LEFT_SPACE)
				sb = sb.insert(0, fixStr);
			else
				sb = sb.append(fixStr);
		}
		return sb.substring(0);
	}

	/**
	 * 该方法计算字符串(包括中文)的实际长度.
	 * @param str 需要计算长度的字符串
	 * @return int 字符串的实际长度
	 */
	public static int length(String str) {

		if (str == null)
			return 0;
		try {
			return new String(str.getBytes("GBK"), "8859_1").length();
		} catch (UnsupportedEncodingException e) {
			return -1;
		}
	}
	/**
	 * 去除html标签
	 * @param inputString   用于查询公告模块
	 * @return
	 */
	public static String Html2Text(String inputString) { 
        String htmlStr = inputString; //含html标签的字符串 
        String textStr =""; 
      java.util.regex.Pattern p_script; 
      java.util.regex.Matcher m_script; 
      
      java.util.regex.Pattern p_style; 
      java.util.regex.Matcher m_style; 
      
      java.util.regex.Pattern p_html; 
      java.util.regex.Matcher m_html;
      
      java.util.regex.Pattern p_space; 
      java.util.regex.Matcher m_space;
      try {  
       String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
       String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
       String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
       String regEx_space = "[\\s]";//去空格
     
       //过滤script标签
          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
          m_script = p_script.matcher(htmlStr); 
          htmlStr = m_script.replaceAll("");  
        //过滤style标签
          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
          m_style = p_style.matcher(htmlStr); 
          htmlStr = m_style.replaceAll("");  
        //过滤html标签
          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
          m_html = p_html.matcher(htmlStr); 
          htmlStr = m_html.replaceAll("");  
        //去除所有空格 
          p_space = Pattern.compile(regEx_space,Pattern.CASE_INSENSITIVE);
          m_space = p_space.matcher(htmlStr);
          htmlStr = m_space.replaceAll("");
         textStr = htmlStr.replaceAll("&nbsp;",""); 
      
      }catch(Exception e) { 
               System.err.println("Html2Text: " + e.getMessage()); 
      } 
   
      	return textStr.trim();//返回文本字符串 
       }   
	/**
	 *
	 */
	public static byte[] getBytes(String str) {
		try {
			return str.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "".getBytes();
		}
	}

	public static void main(String[] args) {
		  
		String s ="&nbsp;e";
		//System.out.println(s.length());
		System.out.println(Html2Text(s));
		//System.out.println(Html2Text(s).length());
//
//		String str = "aaa        ";
//		String str1 = null;
//
//		System.out.println("str=[" + str + "]");
//		System.out.println("str.left=[" + StringUtil.leftTrim(str) + "]");
//		System.out.println("str.right=[" + StringUtil.rightTrim(str) + "]");
//
//		System.out.println("null.trim=[" + StringUtil.trim(str1) + "]");
//		str1 = "   ";
//		System.out.println("null.trim=[" + StringUtil.trim(str1) + "]");
//		str1 = "   aaa   ";
//		System.out.println("null.trim=[" + StringUtil.trim(str1) + "]");
//
//		str1 = "Hello交行";
//		System.out.println("\"" + str1 + "\"的长度是:" + StringUtil.length(str1));
//
//		str1 = "cvic";
//		System.out.println(
//			"10左 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.LEFT_SPACE,
//					StringUtil.TRUNC_RIGHT)
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT)
//				+ "]");
//
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					2,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_LEFT)
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					2,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT)
//				+ "]");
//		str1 = "中创";
//		System.out.println(
//			"10左 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.LEFT_SPACE,
//					StringUtil.TRUNC_RIGHT)
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT)
//				+ "]");
//
//		str1 = "cvic";
//		System.out.println(
//			"10左 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.LEFT_SPACE,
//					StringUtil.TRUNC_RIGHT,
//					'0')
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT,
//					'0')
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					2,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_LEFT,
//					'0')
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					2,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT,
//					'0')
//				+ "]");
//		str1 = "中创";
//		System.out.println(
//			"10左 is ["
//				+ StringUtil.alignStr(
//					str1,
//					4,
//					StringUtil.LEFT_SPACE,
//					StringUtil.TRUNC_RIGHT,
//					'0')
//				+ "]");
//		System.out.println(
//			"10右 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT,
//					'0')
//				+ "]");
//
//		System.out.println("[中创软件公司]进行rightTrim is:[" + StringUtil.rightTrim("中创软件公司") + "]");
//
//		str1 = "中创软件1234567890";
//
//		System.out.println(
//			str1
//				+ " 左截断 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_LEFT,
//					'0')
//				+ "]");
//
//		System.out.println(
//			str1
//				+ " 右截断 is ["
//				+ StringUtil.alignStr(
//					str1,
//					8,
//					StringUtil.RIGHT_SPACE,
//					StringUtil.TRUNC_RIGHT,
//					'0')
//				+ "]");
//
//		str1 = " a 中 创 b ";
//		System.out.println("[" + str1 + "] 删除空格后：" + "[" + StringUtil.allTrim(str1) + "]");
//		System.out.println(StringUtil.length("leng7leng7leng7leng7leng7leng7leng7leng7多个手机号之间用，隔11111"));
	}
	
	
	
	
	
	
	
	 

}
