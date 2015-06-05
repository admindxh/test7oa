/**  
 * @Project: jxoa
 * @Title: ProcessDefinitionAction.java
 * @Package com.oa.manager.workFlow.action
 * @date 2013-7-19 下午10:21:51
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.service.IProcessDefinitionService;

/**
 * 
 * 类名：ProcessDefinitionAction
 * 功能：流程定义操作
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-19 下午10:21:51
 *
 */
@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionAction extends BaseAction{

	@Autowired
	private IProcessDefinitionService service; 
	
	/**
	 * 流程管理--版本监控--根据流程定义key查询出所有版本
	 * @return
	 */
	@RequiresPermissions("processDefinition:read")
	@RequestMapping("queryAllVersion")
	public String loadAllVersion(){
		
		return "workflow/flow/query_all_version";
	}
	/**
	 * 流程管理--版本监控--根据流程定义key查询出所有版本
	 * @param processDefinitionKey
	 * @param map
	 * @return
	 */
	@RequiresPermissions("processDefinition:read")
	@RequestMapping("queryAllVersion/query")
	public ModelAndView queryAllVersion(PageParam param,String processDefinitionKey){
	
		return ajaxJsonEscape(service.selectAllProcessDefinitionByKey(param,processDefinitionKey));
	}
	
	/**
	 * 跳转到流程图显示页面
	 * @return
	 */
	@RequestMapping("showFlowImgPage")
	public String showFlowImgPage(){
		
		return "workflow/process/flow_img";
	
	}
	
	
	/**
	 * 根据流程定义id获取流程图
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping("showFlowImg")
	public String showFlowImg(String processDefinitionId,HttpServletResponse response){
		
		return service.getFlowImg(processDefinitionId, response);
		
	
	}
	
	/**
	 * 批量删除流程定义 级联删除流程实例和历史流程记录
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("processDefinition:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] deploymentIds){
		
		return ajaxDone(service.deleteProcessDefinition(deploymentIds));
		
	}

	
	/**
	 * 查询流程定义所有节点信息，如果同时传入了流程实例id，则同时查询出当前活动节点信息,返回json格式
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping("activityCoordinates")
	public ModelAndView getProcessInstanceActivityCoordinates(String processDefinitionId,String processInstanceId){
		
		return ajaxJsonEscape(service.getProcessDefinitionActivity(processDefinitionId, processInstanceId));
	
	}
	
	
	/**
	 * 流程定义查询
	 * @param processDefinitionId
	 * @param map
	 * @return
	 */
	@RequestMapping("show")
	public String showProcessDefinition(String processDefinitionId,ModelMap map){
		
		
		map.addAttribute("pd",service.getProcessDefinition(processDefinitionId));
		
	
		return "workflow/flow/show_processDefinition";
		
	}
	/**
	 * 流程定义 开始 表单预览
	 * @param processDefinitionId
	 * @param map
	 * @return
	 */
	@RequestMapping("startForm/show")
	public String showStartForm(String processDefinitionId,ModelMap map){
		
		
		map.addAttribute("formHtml",service.getStartFormHtml(processDefinitionId));
		
	
		return "workflow/flow/show_form";
		
	}
	
	/**
	 * 人员设置页面，查询出所有用户任务节点，和人员分配
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("userTask/assigneePage")
	public String selectTaskNode(String processDefinitionId, ModelMap map){
		
		List list=service.getProcessDefinitionUserTask(processDefinitionId);
		
		if(list==null){
			return NODATA;
		}else{
			map.addAttribute("ut",list);
		}
		return "workflow/flow/set/users";
	}
	
	
	
	
	
	
}
