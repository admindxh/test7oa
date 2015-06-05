/**  
 * @Project: jxoa
 * @Title: EncodeUtils.java
 * @Package com.oa.commons.util
 * @date 2013-4-8 下午5:04:22
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类名：EncodeUtils
 * 功能：编码处理
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-8 下午5:04:22
 *
 */
public class EncodeUtils {
	
	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	
	/**
	 * 将ISO-8859-1字符串转换为UTF-8格式字符串
	 * @param str
	 * @return
	 */
	public static String strISO2UTF(String str){
		 
		return strEncode(str,"ISO-8859-1",DEFAULT_URL_ENCODING);
		
	}
	/**
	 * 字符串编码转换
	 * @param str
	 * @param oldCode 原编码
	 * @param toCode  要转换成的编码
	 * @return
	 */
	public static String strEncode(String str,String oldType,String toType){
		 if(StringUtils.isBlank(str)){
			 return "";
		 }else{
			try {
				return new String(str.getBytes(oldType),toType);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		 }
	}
	
	/**
	 * Hex编码.
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String base64Encode(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 编码.
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码.
	 */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * Html 转码.
	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}
	/**
	 * Html 解码.
	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String xtmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
	
	
	
	public static void main(String[] args){
		
		
		
		
	}
}
