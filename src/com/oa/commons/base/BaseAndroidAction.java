/**  
 * @Title: BaseAndroidAction.java
 * @date 2013-10-11 上午9:56:31
 * @Copyright: 2013 
 */
package com.oa.commons.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.oa.commons.config.MsgConfig;

/**
 * 安卓Action基类
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public class BaseAndroidAction {
	
	/**
	 * 将信息格式化成json输出
	 * @param message
	 * @return
	 */
	protected ModelAndView ajaxJson(Object message) {
		ModelAndView mav = new ModelAndView(MsgConfig.PAGE_AJAXJSON);
		mav.addObject(MsgConfig.MSGINFO, JSON.toJSONString(message));
		
		return mav;
	}
	/**
	 * 自定义状态码 和自定义提示信息，
	 * @param statusCode
	 * @param message
	 * @return
	 */
	protected ModelAndView ajaxDone(int statusCode, String message,Object data) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(MsgConfig.STATUSCODE, statusCode);
		map.put(MsgConfig.MESSAGE,message);
		map.put("data", data);
		return ajaxJson(map);
	}
	/**
	 * 自定义状态码 和自定义提示信息，
	 * @param statusCode
	 * @param message
	 * @param data
	 * @param totle 查询总数
	 * @return  mfh
	 */
	protected ModelAndView ajaxDone(int statusCode, String message,Object data,Long totle) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(MsgConfig.STATUSCODE, statusCode);
		map.put(MsgConfig.MESSAGE,message);
		map.put("data", data);
		map.put(MsgConfig.TOTLE,totle);
		return ajaxJson(map);
	}
	/**
	 * 操作成功
	 * @param data
	 * @return
	 */
	protected ModelAndView ajaxSuccess(Object data) {
		
		return ajaxDone(MsgConfig.CODE_SUCCESS, "", data);
	}
	/**
	 * 操作成功
	 * @param message	提示信息
	 * @param data		其他数据
	 * @return
	 */
	protected ModelAndView ajaxSuccess(String message,Object data) {
		
		return ajaxDone(MsgConfig.CODE_SUCCESS,message, data);
	}
	/**
	 * 操作成功
	 * @param message 提示信息
	 * @param data 其他数据
	 * @param totle 查询总数
	 * @return mfh
	 */
	protected ModelAndView ajaxSuccess(Object data,Long totle) {
		
		return ajaxDone(MsgConfig.CODE_SUCCESS,"", data,totle);
	}
	/**
	 * 操作失败
	 * @param data
	 * @return
	 */
	protected ModelAndView ajaxError(Object data) {
		
		return ajaxDone(MsgConfig.CODE_FAIL, "操作失败", data);
	}
	/**
	 * 操作失败
	 * @param message	提示信息
	 * @param data		其他数据
	 * @return
	 */
	protected ModelAndView ajaxError(String message,Object data) {
		
		return ajaxDone(MsgConfig.CODE_FAIL,message, data);
	}
	/**
	 * 传入boolean 返回操作成功 或者失败  使用默认提示信息
	 * @param f  
	 * @return mfh
	 */
	protected ModelAndView ajaxDone(boolean f){
		if(f){
			return ajaxSuccess("操作成功");
		}else{
			return ajaxError("");
		}
	}
	
}
