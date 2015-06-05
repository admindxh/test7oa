/**  
 * @Project: jxoa
 * @Title: ITaskService.java
 * @Package com.oa.manager.workFlow.service
 * @date 2013-7-22 下午3:00:08
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.history.HistoricTaskInstance;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfTask;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.entity.TaskQueryModel;

/**
 * 
 * 类名：ITaskService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-22 下午3:00:08
 *
 */
public interface ITaskService extends IBaseService{

	
	/**
	 * 查询候选任务
	 * @param param
	 * @param qm
	 * @param userId
	 * @param groupIds
	 * @return
	 */
	public DataGrid selectNotHaveDoTask(PageParam param,TaskQueryModel qm,String userId,List<String> groupIds);
	
	
	/**
	 * 查询待办任务
	 * @param param
	 * @param qm
	 * @return
	 */
	public DataGrid selectTask(PageParam param,TaskQueryModel qm);
	
	/**
	 * 查询用户流程任务总数， 未收任务和待办任务
	 * @param userId
	 * @param groupIds
	 * @return
	 */
	public long selectTaskCountByUser(String userId,List<String> groupIds);
	
	/**
	 * 添加候选人
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public boolean  addCandidate(String taskId,String[] userIds);
	
	/**
	 * 委托办理人
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public boolean updateDelegateTask(String taskId,String userId);
	/**
	 * 收回委托的任务
	 * @param taskId
	 * @return
	 */
	public boolean updateRevokeTask(String taskId);
	
	/**
	 * 根据任务id获取任务信息
	 * @param taskId
	 * @return
	 */
	public HistoricTaskInstance selectOneTask(String taskId);
	
	/**
	 * 领取任务
	 * @param taskId
	 * @return
	 */
	public String updateReceiveTask(String taskId);
	
	/**
	 * 完成任务查询任务信息，流程表单，任务表单等
	 * @param taskId
	 * @return
	 */
	public Map<String,Object> selectTaskforComplete(String taskId);
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param toName
	 * @param taskResult
	 * @return
	 */
	public String updateCompleteTask(String taskId,String flowFormHtml,String taskFormHtml,String[] processVariablesKeys,HttpServletRequest request,String savePath,FileList files);
	
	/**
	 * 查询已完成任务，分页条件查询
	 * @param param
	 * @param qm
	 * @return
	 */
	public DataGrid selectHistoricTask(PageParam param,WfTask t,WfWork wf,Date startDate,Date endDate);
	
	
	/** 
     * 根据当前任务ID，查询可以驳回的任务节点 
     *  
     * @param taskId 
     *            当前任务ID 
     */  
    public  List<Map<String, String>> selectBackAvtivity(String taskId);
   
}
