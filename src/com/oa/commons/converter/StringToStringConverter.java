/**  
 * @Project: jxoa
 * @Title: StringToStringConverter.java
 * @Package com.oa.commons.converter
 * @date 2013-5-23 下午4:26:45
 * @Copyright: 2013 
 */
package com.oa.commons.converter;

import org.springframework.core.convert.converter.Converter;

import com.oa.commons.util.StringUtil;

/**
 * 
 * 类名：StringToStringConverter
 * 功能：Spring MVC 参数封装
 * 详细：去除前后空格
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-23 下午4:26:45
 *
 */
public class StringToStringConverter implements Converter<String,String>{
	
	public String convert(String source) {
		
		return StringUtil.trim(source);
	}
}
