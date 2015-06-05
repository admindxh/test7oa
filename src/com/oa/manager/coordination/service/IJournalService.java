/**  
 * @Project: jxoa
 * @Title: IJournalService.java
 * @Package com.oa.manager.coordination.service
 * @date 2013-5-23 上午10:14:08
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.coordination.bean.XtJournal;
import com.oa.manager.system.bean.ListValues;

/**
 * 
 * 类名：IJournalService
 * 功能：日志管理 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-23 上午10:14:08
 *
 */
public interface IJournalService extends IBaseService{

	/**
	 * 日志条件分页查询
	 * @param param
	 * @param xj
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectJournal(PageParam param,XtJournal xj,Date startDate,Date endDate);
	
	
	/**
	 * 查询我能查看的分享日志
	 * @param param
	 * @param xj
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectJournalShare(PageParam param,XtJournal xj,Date startDate,Date endDate);
	
	
	/**
	 * 添加日志
	 * @param xj	
	 * @param userIds	共享人id
	 * @param isSendMsg	是否发送消息提醒 1:发送
	 * @return
	 */
	public boolean saveJournal(XtJournal xj,String[] userIds,Integer isSendMsg);
	/**
	 * 客户端上传工作日志
	 * @param title
	 * @param journType
	 * @param userIds
	 * @param content
	 * @return
	 */
	public boolean  addJournByClient(String title,String journType,String userIds,String content);
	/**
	 * 分页查询个人工作日志---客户端
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> query_journList(PageParam param);
	/**
	 * 更新日志
	 * @param xj
	 * @return
	 */
	public String updateJournal(XtJournal xj,String[] userIds,Integer isSendMsg);
	
	/**
	 * 批了删除日志
	 * @param ids
	 * @return
	 */
	public boolean deleteJournal(String[] ids);
	/**
	 * 根据日志id查询出共享用户的id用，拼接
	 * @param id
	 * @return
	 */
	public String selectJournalUserIds(String id);
	/**
	 * 根据日志id查询日志详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> queryJournalById(String id);
	/**
	 * 查询日志类型
	 * @return
	 */
	public List<ListValues> queryJourn_type();
}
