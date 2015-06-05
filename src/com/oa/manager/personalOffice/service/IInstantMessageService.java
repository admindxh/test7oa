/**  
 * @Project: jxoa
 * @Title: IInstantMessageService.java
 * @Package com.oa.manager.personalOffice.service
 * @date 2013-5-24 下午5:05:58
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.PerInstantMessage;
import com.oa.manager.personalOffice.bean.PerMsg;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：IInstantMessageService
 * 功能：即时消息 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-24 下午5:05:58
 *
 */
public interface IInstantMessageService extends IBaseService{
	
	/**
	 * 查询用户信息
	 * @param param
	 * @param user
	 * @return
	 */
	public DataGrid selectUsers(PageParam param,SyUsers user);
	
	/**
	 * 发送消息
	 * @param messageContent
	 * @param userIds
	 * @return
	 */
	public boolean addInstantMessage(String messageContent,String[] userIds);
	/**
	 * 条件分页查询消息
	 * @param param
	 * @param im
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectInstantMessage(PageParam param,PerInstantMessage im,Date startDate,Date endDate);
	
	/**
	 * 查询和某个用户的历史聊天记录
	 * @param param
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<PerMsg> selectHistoryMessage(PageParam param,String userId,Date startDate,Date endDate);
	/**
	 * 查询和某个用户的历史聊天记录 计算总数
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Long selectHistoryMessageCount(String userId,Date startDate,Date endDate);
	/**
	 * 查看单个信息 并更新状态
	 * @param id
	 * @return
	 */
	public PerInstantMessage updatefindInstantMessageById(String id);
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteInstantMessage(String[] ids);
	/**
	 * 当前登录用户未读消息数量查询
	 * @return
	 */
	public Long selectMyNotReadCount();
	
//************************安卓应用*********************************************	
	
	
	/**
	 * 分页查询已发消息
	 * @param param  分页模型
	 * @return  mfh
	 */
	public List<Map<String,Object>> selectSendMessage(PageParam param);
	/**
	 * 查询已发消息总数
	 * @return mfh
	 */
	public Long totleSendMessage();
	/**
	 * 分页查询已接收消息
	 * @param param 分页模型
	 * @return mfh
	 */
	public List<Map<String,Object>> selectReceiveMessage(PageParam param);
	/**
	 * 查询已接收消息总数
	 * @return mfh
	 */
	public Long totleReceiveMessage();
	
	/**
	 * 分页查询和某一个的历史聊天记录
	 * @param param 
	 * @param userId  某人id
	 * @return
	 */
	public List<Map<String,Object>> querHistoryMessage(PageParam param,String userId);
	/**
	 * 根据用户id查询和该用户的历史聊天记录总数
	 * @param userId 用户id
	 * @return
	 */
	public Long totleHistoryMessage(String userId);
	
}	