/**  
 * @Project: jxoa
 * @Title: IMeetingService.java
 * @Package com.oa.manager.common_platform.service
 * @date 2013-5-9 上午10:25:16
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service;

import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.Meeting;


/**
 * 
 * 类名：IMeetingService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-9 上午10:25:16
 *
 */
public interface IMeetingService extends IBaseService{
	/**
	 * 会议值条件查询
	 * @param param
	 * @param lv
	 * @return
	 */
	public DataGrid selectMeeting(PageParam param,Meeting lv);
	/**
	 * 添加会议值
	 * @param lv
	 * @return
	 */
	public String addMeeting(Meeting lv);
	/**
	 * 修改会议值
	 * @param lv
	 * @return
	 */
	public String updateMeeting(Meeting lv);
	/**
	 * 批量删除会议值
	 * @param ids
	 * @return
	 */
	public boolean deleteMeeting(String[] ids);
	
	
	
	/***
	 * 查询会议室,可以根据会议室ID查询 
	 * 
	 * */
	public List selectRoom();
	
	/**
	 * 根据会议id查找mapping表的数据
	 * 
	 * **/
	public Map selectMapping(String id);
	
	/**
	 * 查找登录人员的会议信息
	 * */
	public DataGrid selectMeetingTome(PageParam param, Meeting lv);
	/**
	 * 根据会议Id查询会议详情
	 * @param id  会议id
	 * @return
	 */
	public Map<String,Object> selectMeetingById(String id);
	/**
	 * 保存会议 会议申请流程自动保存调用此方法
	 * @param m
	 */
	public void saveMeetingForFlow(Meeting m);
	
}
