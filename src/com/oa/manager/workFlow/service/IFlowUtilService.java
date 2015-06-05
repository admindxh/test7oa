/**  
 * @Title: IFlowUtilService.java
 * @date 2013-8-20 上午11:20:19
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public interface IFlowUtilService {
	
	/**
	 * 根据key获取已发布的最新版本流程定义
	 * @param key	流程定义key
	 * @return
	 */
	public ProcessDefinition queryProcessDefinitionLatestVersion(String key);
	
	/**
	 * 根据流程定义id获取流程定义
	 * @param processDefinitionId
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);
	
	/**
	 * 发起流程实例
	 * @param userId	发起人
	 * @param key		流程定义key
	 * @param variables	流程变量
	 * @return
	 */
	public ProcessInstance startProcessInstance(String userId,String key,Map<String,Object> variables);
	
	/**
	 * 根据流程实例id 查询当前所有待办里的任务
	 * @param processInstanceId
	 * @return
	 */
	public List<Task> queryTasksByProcessInstanceId(String processInstanceId);
	
	
	
	
	
	
	/**
	 * 获取流程开启表单key
	 * @param processDefinitionId	流程定义id
	 * @return
	 */
	public String getStartFormKey(String processDefinitionId);
	
	
	
	

}
