/**  
 * @Project: jxoa
 * @Title: IWorkFlowService.java
 * @Package com.oa.manager.workFlow.service
 * @date 2013-7-2 下午3:50:29
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfWorkflow;

/**
 * 
 * 类名：IWorkFlowService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-2 下午3:50:29
 *
 */
public interface IWorkFlowService extends IBaseService{

	/**
	 * 条件分页查询 流程定义
	 * @param param
	 * @param wf
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectWorkflow(PageParam param,WfWorkflow wf,Date startDate,Date endDate);
	
	//添加流程
	public String addWfWorkflow(WfWorkflow wf,String[] userIds,String[] deptIds);
	
	
	/**
	 * 保存流程定义
	 * @param wf
	 * @return
	 */
	public String saveFlow(WfWorkflow wf);
	
	/**
	 * 部署流程定义(发布流程)
	 * @param id
	 * @return
	 */
	public String updateDeployFlow(String id);
	
	
	/**
	 * 批量删除流程，会级联删除所有流程部署，历史流程
	 * @param ids
	 * @return
	 */
	public boolean deleteFlows(String[] ids);

	/**
	 * 查询出所有流程的id，name
	 * @return List<Map>  
	 */
	public List selectAllWorkflow();
	

	/**
	 * 根据流程设计id，查询权限
	 * @param id
	 * @return
	 */
	public Map selectforUpdate(String id);
	/**
	 * 更新流程使用权限
	 * @param id
	 * @param userIds
	 * @param deptIds
	 * @return
	 */
	public String  updateWfWorkflow(WfWorkflow wf,String[] userIds,String[] deptIds);
	
	/**
	 * 根据权限查询当前用户可以使用的流程
	 * @param param
	 * @param wf
	 * @return
	 */
	public List selectWorkflowByPower(PageParam param,WfWorkflow wf);
	
	
}
