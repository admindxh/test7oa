/**  
 * @Project: jxoa
 * @Title: IPerMsgService.java
 * @Package com.oa.manager.personalOffice.service
 * @date 2013-5-21 下午4:23:25
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.PerMsg;

/**
 * 
 * 类名：IPerMsgService
 * 功能：消息提醒 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-21 下午4:23:25
 *
 */
public interface IPerMsgService extends IBaseService {
	
	/**
	 * 分页条件查询消息提醒
	 * @param param
	 * @param msg
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectPerMsg(PageParam param,PerMsg msg,Date startDate,Date endDate);
	
	/**
	 * 查看单个消息提醒
	 * @param id
	 * @return
	 */
	public PerMsg updatefindPerMsgById(String id);
	/**
	 * 批量删除消息提醒
	 * @param ids
	 * @return
	 */
	public boolean deletePerMsg(String[] ids);
	/**
	 * 查询未读提醒数量
	 * @return
	 */
	public Long selectNotReadNumber();
//*****************************************安卓*******************************************************
	/**
	 * 分页查询当前用户的消息提醒信息
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectPersonMsgWarn(PageParam param);
	/**
	 * 查询当前用户消息提醒总数
	 * @return
	 */
	public Long totlePersonMsgWarn();
	/**
	 * 根据数据id和提醒类型查询对应记录
	 * @param tableId 数据id
	 * id 消息记录id
	 * @return
	 */
	public Map<String,Object> updateInformationById(String id,String tableId,short i);
	/**
	 * 查询消息提醒用于客户端通知栏
	 * @return
	 */
	public List<Map<String,Object>> selectNoticBar();
	/**
	 * 根据消息类别查询当前用户的未读消息提醒列表
	 * @param param
	 * @param i 消息类别  1通知2会议3新闻4共享日志5投票60工作流
	 * @return
	 */
	public List<Map<String,Object>> selectNoticBarByType(PageParam param,short i);
	/**
	 * 根据消息类别查询当前用户的未读消息提醒总数
	 * @param i 消息类别  1通知2会议3新闻4共享日志5投票60工作流
	 * @return
	 */
	public Long totleNoticBarByType(short i);
}
