/**  
 * @Title ProjectTaskAction.java
 * @date 2013-12-11 上午9:37:16
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.action;

import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.PageParam;
import com.oa.manager.coordination.bean.Project;
import com.oa.manager.coordination.bean.ProjectTask;
import com.oa.manager.coordination.bean.ProjectTaskJournal;
import com.oa.manager.coordination.service.ProjectTaskService;

/**
 * 项目任务
 * @author LiuJincheng
 * @version 1.0
 */
@Controller 
@RequestMapping("/projectTask")
public class ProjectTaskAction extends BaseAction{
	@Autowired
	private ProjectTaskService service;
	/**
	 * 跳转到项目任务管理 页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("load")
	public String load(String projectId,ModelMap map){
		//判断是否有添加 删除 修改 任务的权限
		int power=service.hasPowerForTask(projectId,false);
		if(power==0){
			return NODATA;
		}
		if(power==1){
			map.addAttribute("fzr", true);
		}
		return "coordination/project/task/load";
	}
	/**
	 * 项目任务查询
	 * @param param
	 * @param projectId
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,String projectId){
	
		return ajaxJsonEscape(service.selectProjectTask(param, projectId));
	}
	/**
	 * 跳转到任务一览 页面
	 * @return
	 */
	@RequiresPermissions("projectTask:myRead")
	@RequestMapping("my/load")
	public String loadMy(){
		
		return "coordination/project/task/load_my";
	}
	/**
	 * 任务一览查询 查询任务执行人是我的任务
	 * @param param
	 * @return
	 */
	@RequiresPermissions("projectTask:myRead")
	@RequestMapping("my/query")
	public ModelAndView queryMy(PageParam param){
	
		return ajaxJsonEscape(service.selectMyProjectTask(param));
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("addPage")
	public String addPage(){
		
		return "coordination/project/task/add";
	}
	/**
	 * 添加项目任务
	 * @param t
	 * @param errors
	 * @param isSendMsg
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("add")
	public ModelAndView add(@Valid ProjectTask t,Errors errors,Integer isSendMsg,String[] userIds){
		//判断是否有添加操作权限, 当项目处于停止状态 不可添加项目任务
		int power=service.hasPowerForTask(t.getProjectId(),true);
		if(power==3){
			return ajaxDoneTextError("项目已结束,无法添加项目任务!");
		}
		if(power==0){
			return ajaxDoneTextError("任务关联的项目不存在，可能已被删除！请刷新再试!");
		}
		if(power==2){
			return ajaxDoneTextError("没有添加权限，只有项目创建者或者项目负责人才有添加权限！");
		}
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.addProjectTask(t, isSendMsg, userIds));
		
	}
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		Map<String,Object> m=service.findProjectTaskById(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		return "coordination/project/task/update";
		
	}
	/**
	 * 修改项目任务
	 * @param p
	 * @param errors
	 * @param isSendMsg
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("update")
	public ModelAndView update(@Valid ProjectTask t,Errors errors,Integer isSendMsg,String[] userIds){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateProjectTask(t, isSendMsg, userIds));
		
	}
	/**
	 * 查询项目任务详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		Map<String,Object> m=service.findProjectTaskById(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		return "coordination/project/task/show";
		
	}
	/**
	 * 删除项目任务
	 * @param id
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("del")
	public ModelAndView delete(String projectId,String[] ids){
		//判断是否有删除操作权限
		int power=service.hasPowerForTask(projectId,false);
		if(power==0){
			return ajaxDoneTextError("任务关联的项目不存在，可能已被删除！请刷新再试!");
		}
		if(power==2){
			return ajaxDoneTextError("没有删除权限，只有项目创建者或者项目负责人才有删除权限！");
		}
		return ajaxDone(service.deleteProjectTask(projectId,ids));
		
	}
	
	/** 
	 * 跳转到任务进度日志 查询 页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("journal/load")
	public String loadJournal(String taskId,ModelMap map){
		
		ProjectTask t=service.get(ProjectTask.class, taskId);
		if(t!=null){
			map.addAttribute("name",t.getName());
		}else{
			map.addAttribute("name","");
		}
		return "coordination/project/journal/load";
	}
	/**
	 * 查询任务进度日志
	 * @param param
	 * @param taskId
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("journal/query")
	public ModelAndView queryJournal(PageParam param,String taskId){
	
		return ajaxJsonEscape(service.selectProjectTaskJournal(param, taskId));
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("journal/addPage")
	public String addJournalPage(String id,ModelMap map){
		ProjectTask t=service.get(ProjectTask.class, id);
		if(t==null){
			return NODATA;
		}
		Project p=service.get(Project.class, t.getProjectId());
		if(p==null){
			throw new MyRuntimeException("任务关联的项目不存在，可能已被删除！");
		}
		if(p.getStatus()!=1){
			throw new MyRuntimeException("项目处于未开始，暂停或结束状态时无法添加进度日志!");
		}
		if(!service.myIsExecutorByTaskId(id)){
			throw new MyRuntimeException("没有添加权限，只有任务的执行人才有添加进度日志的权限。");
		}
		map.addAttribute("t",t);
		return "coordination/project/journal/add";
	}
	/**
	 * 添加任务进度日志
	 * @param p
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("journal/add")
	public ModelAndView addJournal(@Valid ProjectTaskJournal j,Errors errors,short speed){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }

		return ajaxDone(service.addProjectTaskJournal(j,speed));
	}
	/**
	 * 查看进度日志详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("journal/show")
	public String showJournal(String id,ModelMap map){
		ProjectTaskJournal j=service.get(ProjectTaskJournal.class, id);
		if(j==null){
			return NODATA;
		}
		map.addAttribute("j",j);
		return "coordination/project/journal/show";
		
	}
	
	
}
