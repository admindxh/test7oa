/**  
 * @Project: jxoa
 * @Title: MsgConfig.java
 * @Package com.oa.commons.base
 * @date 2013-4-1 上午9:46:54
 * @Copyright: 2013 
 */
package com.oa.commons.config;

/**
 * 
 * 类名：MsgConfig
 * 功能：消息配置文件
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-1 上午9:46:54
 *
 */
public interface MsgConfig {
	
	/**
	 * dwz 状态
	 * 值：statusCode
	 */
	public static final String STATUSCODE="statusCode";
	/**
	 * dwz信息
	 * 值：message
	 */
	public static final String MESSAGE="message";
	/**
	 * dwz message 占位符赋值  {1}{2}   多个值用，隔开
	 * 值：messageValues
	 */
	public static final String MESSAGEVALUES="messageValues";	
	
	/**
	 * 操作成功 响应码
	 * 值：200
	 */
	public static final Integer CODE_SUCCESS=200;
	/**
	 * 操作失败 响应码
	 * 值：300
	 */
	public static final Integer CODE_FAIL=300;
	/**
	 * 登录超时 响应码
	 * 值：301
	 */
	public static final Integer CODE_OVERTIME=301;
	
	/**
	 * ajaxJson 页面取值key
	 * 值：msginfo
	 */
	public static final String MSGINFO="msginfo";
	
	/**
	 * messages_zh.properties 配置文件key  操作成功
	 * 值：msg.operation.success
	 */
	public static final String MSG_KEY_SUCCESS="msg.operation.success";
	/**
	 * messages_zh.properties 配置文件key  操作失败
	 * 值：msg.operation.failure
	 */
	public static final String MSG_KEY_FAIL="msg.operation.failure";
	/**
	 * messages_zh.properties 配置文件key  数据不存在，已被删除
	 * 值：msg.data.hasdelete
	 */
	public static final String MSG_KEY_NODATA="msg.data.hasdelete";
	/**
	 * messages_zh.properties 配置文件key  无操作权限
	 * 值：msg.request.nopower
	 */
	public static final String MSG_KEY_NOPOWER="msg.request.nopower";
	
	/**
	 * ajaxDone 页面路径
	 * 值：ajaxDone
	 */
	public static final String PAGE_AJAXDONE="ajaxDone";
	/**
	 * ajaxMessage 页面路径
	 * 值：ajaxMessage
	 */
	public static final String PAGE_AJAXMESSAGE="ajaxMessage";
	/**
	 * ajaxJson 页面路径
	 * 值：ajaxJson
	 */
	public static final String PAGE_AJAXJSON="ajaxJson";

	/**
	 * 查询列表总数
	 */
	public static final String TOTLE="totle";
	

	/**
	 * 考勤类型：第一次上班
	 */
	public static final String ATTENDANCE_TYPE_FS="1";
	/**
	 * 考勤类型：第一次下班
	 */
	public static final String ATTENDANCE_TYPE_FE="2";
	/**
	 * 考勤类型：第二次上班
	 */
	public static final String ATTENDANCE_TYPE_SS="3";
	/**
	 * 考勤类型：第二次下班
	 */
	public static final String ATTENDANCE_TYPE_SE="4";
	/**
	 * 考勤类型：第三次上班
	 */
	public static final String ATTENDANCE_TYPE_TS="5";
	/**
	 * 考勤类型：第三次下班
	 */
	public static final String ATTENDANCE_TYPE_TE="6";
	
	/**
	 * 考勤签到：正常
	 */
	public static final String ATTENDANCE_SIGN_NOLATE="0";
	/**
	 * 考勤签到：迟到
	 */
	public static final String ATTENDANCE_SIGN_LATE="1";
	/**
	 * 考勤签到：早退
	 */
	public static final String ATTENDANCE_SIGN_LEAVEEARLY="2";

	/**
	 * 考勤签到：请假
	 */
	public static final String ATTENDANCE_SIGN_LEAVE="3";
	/**
	 * 考勤签到：旷工
	 */
	public static final String ATTENDANCE_SIGN_ABSENTEEISM="4";
	/**
	 * 考勤签到：外出
	 */
	public static final String ATTENDANCE_SIGN_OUT="5"; 
	/**
	 * 考勤签到：其他
	 */
	public static final String ATTENDANCE_SIGN_OTHER="6"; 
	
	
}

