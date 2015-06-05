/**  
 * @Project: jxoa
 * @Title: IProcessDefinitionService.java
 * @Package com.oa.manager.workFlow.service
 * @date 2013-7-19 下午10:22:51
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;

/**
 * 
 * 类名：IProcessDefinitionService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-19 下午10:22:51
 *
 */

public interface IProcessDefinitionService extends IBaseService{

	/**
	 * 根据key查询出所有流程定义
	 * @param processDefinitionKey
	 * @return
	 */
	public DataGrid selectAllProcessDefinitionByKey(PageParam param,String processDefinitionKey);
	
	/**
	 * 根据流程定义id 获取流程设计图
	 * @param processDefinitionId
	 * @param response
	 * @return
	 */
	public String getFlowImg(String processDefinitionId,HttpServletResponse response);
	
	/**
	 * 获取流程定义各个节点信息，如果传入流程id，查询当前活动节点信息,用于配合流程设计图展示节点详细信息
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @return
	 */
	public List getProcessDefinitionActivity(String processDefinitionId,String processInstanceId);
	
	
	/**
	 * 批量删除流程部署 级联删除流程实例，历史信息
	 * @param ids
	 * @return
	 */
	public boolean deleteProcessDefinition(String[] ids);
	/**
	 * 获取流程定义信息
	 * @param processDefinitionId
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);
	
	/**
	 * 获取流程定义的全局表单
	 * @param processDefinitionId
	 * @return
	 */
	public String getStartFormHtml(String processDefinitionId);
	
	/**
	 * 根据流程定义 查询用户任务节点信息，用于人员配置显示
	 * @param processDefinitionId
	 * @return
	 */
	public List getProcessDefinitionUserTask(String processDefinitionId);
	
	
	
}
