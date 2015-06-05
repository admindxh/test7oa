/**  
 * @Title ProjectTaskService.java
 * @date 2013-12-11 上午9:37:29
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service;

import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.coordination.bean.ProjectTask;
import com.oa.manager.coordination.bean.ProjectTaskJournal;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public interface ProjectTaskService extends IBaseService{
	/**
	 * 条件分页查询项目任务
	 * @param param
	 * @param projectId
	 * @return
	 */
	public DataGrid selectProjectTask(PageParam param,String projectId);
	/**
	 * 查询我的任务
	 * @param param
	 * @return
	 */
	public DataGrid selectMyProjectTask(PageParam param);
	/**
	 * 添加项目任务
	 * @param t
	 * @param isSendMsg
	 * @param userIds
	 * @return
	 */
	public boolean addProjectTask(ProjectTask t,Integer isSendMsg,String[] userIds);
	/**
	 * 查询项目任务详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> findProjectTaskById(String id);
	/**
	 * 修改项目任务
	 * @param t
	 * @param isSendMsg
	 * @param userIds
	 * @return
	 */
	public String updateProjectTask(ProjectTask t,Integer isSendMsg,String[] userIds);
	/**
	 * 删除项目任务
	 * @param ids
	 * @return
	 */
	public boolean deleteProjectTask(String projectId,String[] ids);
	/**
	 * 条件分页查询 进度日志
	 * @param param
	 * @param taskId
	 * @return
	 */
	public DataGrid selectProjectTaskJournal(PageParam param,String taskId);
	/**
	 * 添加进度日志
	 * @param j
	 * @return
	 */
	public String addProjectTaskJournal(ProjectTaskJournal j,short speed);
	/**
	 * 判断当前用户是否对项目有添加 修改 删除 项目任务的权限
	 * @param projectId
	 * @param isAddTask 是添加操作
	 * @return 0:项目不存在 1:有 2:没有, 3:项目状态为结束
	 */
	public int hasPowerForTask(String projectId,boolean isAddTask);
	
	/**
	 * //判断当前用户是否是任务的执行人
	 * @param taskId
	 * @return
	 */
	public boolean myIsExecutorByTaskId(String taskId);
}
