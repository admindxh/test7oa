/**  
 * @Project: jxoa
 * @Title: TaskAction.java
 * @Package com.oa.manager.workFlow.action
 * @date 2013-7-22 下午2:59:12
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.model.FileList;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.workFlow.bean.WfTask;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.entity.TaskQueryModel;
import com.oa.manager.workFlow.service.ITaskService;

/**
 * 
 * 类名：TaskAction
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-22 下午2:59:12
 *
 */
@Controller
@RequestMapping("/task")
public class TaskAction extends BaseAction{

	@Autowired
	private ITaskService service; 
	/**
	 * 任务上传附件保存路径
	 */
	public static final String SAVEPATH=BaseConfig.uploadPath+"flow_task";
	
	/**
	 * 待办任务 加载页面
	 * @return
	 */
	@RequiresPermissions("task:read")
	@RequestMapping("todo/load")
	public String todoNotHaveTask(){
		
		return "workflow/task/load";
	
	}
	/**
	 * 待办任务 查询我未领取的任务
	 * @param param
	 * @param qm
	 * @return
	 */
	@RequiresPermissions("task:read")
	@RequestMapping("todo/notHave/query")
	public ModelAndView queryTodoNotHaveTask(PageParam param,TaskQueryModel qm){
		Member me=ServletUtil.getMember();
		return ajaxJsonEscape(service.selectNotHaveDoTask(param, qm, me.getId(), ServletUtil.getRoleIdsByUserId(me.getId())));
	}
	
	/**
	 * 待办任务 跳转到未领任务 添加候选人页面
	 * @return
	 */
	@RequiresPermissions("task:addCandidate")
	@RequestMapping("todo/notHave/entrustPage")
	public String  notHaveTaskEntrustPage(){
		
		return "workflow/task/entrustPage_notHave";
	}
	
	/**
	 * 待办任务 未领任务	添加候选人
	 * @param taskId
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("task:addCandidate")
	@RequestMapping("todo/notHave/entrust")
	public ModelAndView notHaveTaskEntrust(String taskId,String[] userIds){
		
		return ajaxDone(service.addCandidate(taskId, userIds));
		
	}

	/**
	 * 待办任务 我的待办任务查询
	 * @return
	 */
	@RequiresPermissions("task:read")
	@RequestMapping("todo/doing")
	public String todoTask(){
		
		return "workflow/task/todo";
	}
	/**
	 * 待办任务 我的待办任务查询
	 * @param param
	 * @param qm
	 * @return
	 */
	@RequiresPermissions("task:read")
	@RequestMapping("todo/doing/query")
	public ModelAndView queryTodoTask(PageParam param,TaskQueryModel qm){
		
		qm.setAssignee(ServletUtil.getMember().getId());
		qm.setOwner(null);

		return ajaxJsonEscape(service.selectTask(param, qm));
	
	}
	/**
	 * 待办任务 跳转到待办任务，委托代理人页面
	 * @return
	 */
	@RequiresPermissions("task:addAssignee")
	@RequestMapping("todo/doing/entrustPage")
	public String  doingTaskEntrustPage(){
		
		
		return "workflow/task/entrustPage_todo";
	}
	
	/**
	 * 待办任务 待办任务，委托代理人
	 * @param taskId
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("task:addAssignee")
	@RequestMapping("todo/doing/entrust")
	public ModelAndView dongTaskEntrust(String taskId,String userId){
		
		return ajaxDone(service.updateDelegateTask(taskId, userId));
		
	}
	

	/**
	 * 待办任务 领取任务
	 * @param taskId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("task:complete")
	@RequestMapping("receive")
	public ModelAndView receiveTask(String taskId){
		
		return ajaxDone(service.updateReceiveTask(taskId));
		
	}
	
	
	/**
	 * 待办任务 跳转到任务完成界面
	 * @param taskId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("task:complete")
	@RequestMapping("completePage")
	public String completeTaskPage(String taskId,ModelMap map){
		Map m=service.selectTaskforComplete(taskId);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		
		return "workflow/task/complete";
	}
    
	/**
	 * 待办任务 任务节点详情
	 * @param taskId
	 * @param map
	 * @return
	 */
	@RequestMapping("show")
	public String show(String taskId,ModelMap map){
		
		HistoricTaskInstance task=service.selectOneTask(taskId);
		if(task==null){
			return NODATA;
		}
		map.addAttribute("task",task);
		
		return "workflow/task/show";
	}
	
    
	/**
	 * 待办任务	处理任务，完成任务
	 * @param taskId
	 * @param flowFormHtml
	 * @param taskFormHtml
	 * @param processVariablesKeys
	 * @param request
	 * @param files
	 * @return
	 */
	@RequiresPermissions("task:complete")
	@RequestMapping("complete")
	public ModelAndView completeTask(String taskId,String flowFormHtml,String taskFormHtml,String[] processVariablesKeys,HttpServletRequest request,FileList files){
		
		//验证上传文件大小，格式
		List<MultipartFile> fs=files.getFile();
		if(fs!=null){
			for(MultipartFile file:fs){
				if(file!=null&&!file.isEmpty()){
					ModelAndView view=FileUtils.validateFile(file, 1024L*1024*500, null, new String[]{"exe"});
					if(view!=null)return view;
				}
			}
		}
		
		return ajaxDone(service.updateCompleteTask(taskId, flowFormHtml, taskFormHtml, processVariablesKeys, request,SAVEPATH,files));
		
	}
	
	/**
	 * 我的委托 加载页面
	 * @return
	 */
	@RequiresPermissions("task:myEntrust")
	@RequestMapping("/entrust/load")
	public String myEntrustTaskLoad(){
		
		return "workflow/task/entrust/load";
	}
	
	/**
	 * 我的委托	查询我委托的任务 未完成的
	 * @param param
	 * @param qm
	 * @param map
	 * @return
	 */
	@RequiresPermissions("task:myEntrust")
	@RequestMapping("/entrust/run/query")
	public ModelAndView myEntrustTask(PageParam param,TaskQueryModel qm){
		
		qm.setAssignee(null);
		qm.setOwner(ServletUtil.getMember().getId());
	
		return ajaxJsonEscape(service.selectTask(param, qm));
	}
	/**
	 * 我的委托	查询我委托的任务 已完成的，历史任务
	 * @return
	 */
	@RequiresPermissions("task:myEntrust")
	@RequestMapping("/entrust/his/load")
	public String myEntrustHisTaskLoad(){
		
	
		return "workflow/task/entrust/query_his";
	
	}
	/**
	 * 我的委托	查询我委托的任务 已完成的，历史任务
	 * @param param
	 * @param qm
	 * @return
	 */
	@RequiresPermissions("task:myEntrust")
	@RequestMapping("/entrust/his/query")
	public ModelAndView myEntrustHisTask(PageParam param,WfTask t,WfWork wf,Date startDate,Date endDate){
		
		t.setTaskOwner(ServletUtil.getMember().getId());
	
		return ajaxJsonEscape(service.selectHistoricTask(param, t, wf, startDate, endDate));
	}
	/**
	 * 我的委托	委托任务回收
	 * @param taskId
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("task:revokeEntrust")
	@RequestMapping("todo/doing/entrust/revoke")
	public ModelAndView dongTaskEntrustRevoke(String taskId){
		
		return ajaxDone(service.updateRevokeTask(taskId));
		
	}
	
	
	/**
	 * 已办任务 查询已办任务
	 * @return
	 */
	@RequiresPermissions("task:havedo")
	@RequestMapping("havedo")
	public String havedoTask(){
		
		
		return "workflow/task/havedo";
	
	}
	/**
	 * 已办任务 查询已办任务
	 * @param param
	 * @param t
	 * @param wf
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("task:havedo")
	@RequestMapping("havedo/query")
	public ModelAndView queryHavedoTask(PageParam param,WfTask t,WfWork wf,Date startDate,Date endDate){
		
		t.setTaskAssignee(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.selectHistoricTask(param, t, wf, startDate, endDate));
	
	}

	/**
	 * 文件下载
	 * @param id 文件保存时名称
	 * @param name 文件原名
	 * @param ext  文件后缀
	 * @param request  
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/download")   
    public String down(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, newName, ext,request,response);
        
    }  
	
	
	
	
	
	
	
}
