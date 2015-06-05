/**  
 * @Project: jxoa
 * @Title: WorkFlowAction.java
 * @Package com.oa.manager.workFlow.action
 * @date 2013-7-2 下午3:48:51
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfWorkflow;
import com.oa.manager.workFlow.service.IWorkFlowService;

/**
 * 
 * 类名：WorkFlowAction
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-2 下午3:48:51
 *
 */
@Controller
@RequestMapping("/workflow")
public class WorkFlowAction extends BaseAction{
	
	@Autowired
	private IWorkFlowService service; 
	
	/**
	 * 以zip文件部署流程时文件保存路径
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"workflowZip";
	
	
	/**
	 * 查询流程定义 加载页面
	 * @return
	 */
	@RequiresPermissions("workflow:read")
	@RequestMapping("load")
	public String loadFlow(){
		
		return "workflow/flow/load";
	
	}
	/**
	 * 查询流程定义
	 * @param param
	 * @param wf
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workflow:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WfWorkflow wf,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectWorkflow(param, wf, startDate, endDate));
	}
	
	/**
	 * 跳转到流程添加页面，
	 * @return
	 */
	@RequiresPermissions("workflow:add")
	@RequestMapping("addPage")
	public String addPage(ModelMap map){
		
	
		return "workflow/flow/add";
	}
	/**
	 * 添加流程
	 * @param wf
	 * @param errors
	 * @param userIds
	 * @param deptIds
	 * @return
	 */
	@RequiresPermissions("workflow:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid WfWorkflow wf,Errors errors,String[] userIds,String[] deptIds){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.addWfWorkflow(wf, userIds, deptIds));
	
	}
	
	/**
	 * 跳转到流程复制页面
	 * @return
	 */
	@RequiresPermissions("workflow:add")
	@RequestMapping("copyPage")
	public String copyPage(ModelMap map){
		
	
		return "workflow/flow/copy";
	}
	/**
	 * 复制流程
	 * @param wf
	 * @param errors
	 * @param fromId
	 * @return
	 */
	@RequiresPermissions("workflow:add")
	@RequestMapping("copy")
	public ModelAndView copy(@Valid WfWorkflow wf,Errors errors,String fromId,String[] userIds,String[] deptIds){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		WfWorkflow oldwf=service.get(WfWorkflow.class, fromId);
		if(oldwf==null){
			return ajaxDoneError(MsgConfig.MSG_KEY_NODATA);
		}
		String xml=oldwf.getFlowXml();
		if(StringUtils.isNotEmpty(xml)){
			//替换xml一些信息，id,name,描述，类型
			xml=xml.replaceFirst("targetNamespace=\""+oldwf.getFlowType()+"\"", "targetNamespace=\""+wf.getFlowType()+"\"").replaceFirst("(<process)(.)*(>)", "<process id=\""+wf.getFlowId()+"\" name=\""+wf.getFlowName()+"\" >").replaceFirst("<documentation>"+oldwf.getFlowDesc()+"</documentation>","<documentation>"+ wf.getFlowDesc()+"</documentation>");
		}
		
		wf.setFlowXml(xml);
		return ajaxDone(service.addWfWorkflow(wf, userIds, deptIds));
		
	}
	
	
	
	
	/**
	 * 部署流程
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workflow:deploy")
	@RequestMapping("flow/deploy")
	public ModelAndView deploy(String id){
		
		
		return ajaxDone(service.updateDeployFlow(id));
	
	}
	
	
	
	
	/**
	 * 查看流程定义信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workflow:read")
	@RequestMapping("flow/show")
	public String showFlow(String id,ModelMap map){
		WfWorkflow wf=service.get(WfWorkflow.class, id);
		if(wf==null){
			return NODATA;
		}
		map.addAttribute("wf",wf);
		return "workflow/flow/show";
	}
	
	/**
	 * 流程定义设置页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workflow:update")
	@RequestMapping("flow/setPage")
	public String flowSetPage(String id,ModelMap map){
		WfWorkflow wf=service.get(WfWorkflow.class, id);
		if(wf==null){
			return NODATA;
		}
		map.addAttribute("wf",wf);
		return "workflow/flow/set/set";
	}
	
    
   /**
    * 批量删除流程定义,并级联删除所有流程版本，历史流程记录
    * @param ids
    * @return
    */
	@RequiresPermissions("workflow:delete")
	@RequestMapping("del")
	public ModelAndView deleteFlow(String[] ids){
		
		return ajaxDone(service.deleteFlows(ids));
		
	}

	/**
	 * 跳转到流程权限修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workflow:update")
	@RequestMapping("set/updatePage")
	public String updatePage(String id, ModelMap map){
		Map m=service.selectforUpdate(id);
		if(m==null){
			return NODATA;
		}else{
			map.addAllAttributes(m);
		}
		return "workflow/flow/set/update";
	}
	/**
	 * 修改流程设置
	 * @param wf
	 * @param userIds
	 * @param deptIds
	 * @return
	 */
	@RequiresPermissions("workflow:update")
	@RequestMapping("set/update")
	public ModelAndView update(WfWorkflow wf,String[] userIds,String[] deptIds){
		
		
		return ajaxDone(service.updateWfWorkflow(wf, userIds, deptIds));
		
	}
	

	
	/**
	 * 新建工作，根据类型查询用户有权限的已有流程版本可以使用的流程
	 * @param param
	 * @param wf
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workflow:readMy")
	@RequestMapping("newWork/query")
	public String query(PageParam param,WfWorkflow wf,ModelMap map){
		
		map.addAttribute("flows",service.selectWorkflowByPower(param, wf));
		
		return "workflow/process/new/query";
	
	}
	
	
	
	
	

	/**
	 * 新建工作 查询所有流程类型，返回json格式数据
	 * @return
	 */
	@RequestMapping("work/flowType")
	public ModelAndView getFlowType(){
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("types", MyCache.getInstance().getListValues(17));//获取所有流程类型
		
		
		return ajaxJsonEscape(map);
		
	}
	
	
	
	
	/**
	 * 流程监控  查询所有流程类型，流程，返回json格式数据
	 * @return
	 */
	@RequestMapping("work/flowTree")
	public ModelAndView getFlowTypeAndFlow(){
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("types", MyCache.getInstance().getListValues(17));//获取所有流程类型
		
		map.put("flows", service.selectAllWorkflow());
		
		return ajaxJsonEscape(map);
		
	}
	
	
	/**
	 * 跳转到流程设计界面
	 * @return
	 */
	@RequiresPermissions("workflow:designer")
	@RequestMapping("designer/load")
	public String designerLoad(String id,ModelMap map){
		WfWorkflow wf=service.get(WfWorkflow.class, id);
		
		if(wf==null){
	
			return NODATA;
			
		}
		map.put("flow", wf);
		return "workflow/flow/designer/load";
	
	}

	/**
	 * 获取流程设计xml
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workflow:designer")
	@RequestMapping("designer/getProcessXml")
	public ModelAndView getProcessXml(String id){
		
		WfWorkflow wf=service.get(WfWorkflow.class, id);
		
		if(wf==null){
			
			return ajaxDoneNoData();
			
		}
		return ajaxText(wf.getFlowXml());
		
	}

	/**
	 * 保存流程设计xml，和流程名称,流程描述
	 * @param wf
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("workflow:designer")
	@RequestMapping("designer/save")
	public ModelAndView addWfWorkflow(@Valid WfWorkflow wf,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors,"flowType","flowId");
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.saveFlow(wf));
	
	}
		
	/**
	 * 跳转流程属性配置页面
	 * @param page
	 * @return
	 */
	@RequiresPermissions("workflow:designer")
	@RequestMapping("designer/getFlowPropertiesPage")
	public String getFlowPropertiesPage(String page){
		
		return "workflow/flow/designer/"+page;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
