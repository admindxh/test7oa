/**  
 * @Title: FlowUtilService.java
 * @date 2013-8-20 上午11:20:34
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.manager.workFlow.service.IFlowUtilService;

/**
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
@Service
public class FlowUtilService implements IFlowUtilService{
	@Autowired
	private RepositoryService repositoryService; //部署流程服务
	
	@Autowired
	private RuntimeService runtimeService; //流程执行服务 
	
	@Autowired
	private IdentityService identityService; //身份认证服务

	@Autowired
	private FormService formService; //表单服务
	
	@Autowired
	private HistoryService historyService; //历史服务
	
	@Autowired
	private TaskService taskService ; //任务服务
	@Autowired
	private ManagementService managermentService; //流程管理服务
	
	//根据key获取最新版本流程
	public ProcessDefinition queryProcessDefinitionLatestVersion(String key){
		
		return repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).latestVersion().singleResult();
		
	}
	//根据流程定义id获取流程定义
	public ProcessDefinition getProcessDefinition(String processDefinitionId){
		return repositoryService.getProcessDefinition(processDefinitionId);
	}
	
	
	//发起流程
	public ProcessInstance startProcessInstance(String userId,String key,Map<String,Object> variables){
		
		ProcessInstance processInstance=null;
		try{
			identityService.setAuthenticatedUserId(userId);//设置流程发起人,对应流程变量名称 applyUserId
			processInstance =  runtimeService.startProcessInstanceByKey(key,variables);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			identityService.setAuthenticatedUserId(null);//清空
		}
		return processInstance;
		
	}
	//根据流程实例id，获取当前任务集合
	public List<Task> queryTasksByProcessInstanceId(String processInstanceId){
		
		return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
	} 
	
	
	//获取开始表单key
	public String getStartFormKey(String processDefinitionId){
		
		return formService.getStartFormKey(processDefinitionId);
		
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
