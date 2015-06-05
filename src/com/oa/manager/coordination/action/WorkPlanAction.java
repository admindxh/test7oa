/**  
 * @Title WorkPlanAction.java
 * @date 2013-11-28 上午10:22:22
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.oa.commons.base.BaseAction;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.coordination.bean.XtWorkPlan;
import com.oa.manager.coordination.bean.XtWorkPlanComment;
import com.oa.manager.coordination.bean.XtWorkPlanTask;
import com.oa.manager.coordination.service.WorkPlanService;

/**
 * 工作计划 管理
 * @author LiuJincheng
 * @version 1.0
 */
@Controller
@RequestMapping("/workPlan")
public class WorkPlanAction extends BaseAction{
	@Autowired
	private WorkPlanService service;
	
	/**
	 * 工作计划上传的保存路径
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"work_plan";
	
	/**
	 * 跳转到工作计划管理 页面
	 * @return
	 */
	@RequiresPermissions("workPlan:read")
	@RequestMapping("load")
	public String load(){
		
		return "coordination/work_plan/load";
	}
	/**
	 * 工作计划查询
	 * @param param
	 * @return
	 */
	@RequiresPermissions("workPlan:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param){
	
		return ajaxJsonEscape(service.selectWorkPlan(param));
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("workPlan:add")
	@RequestMapping("addPage")
	public String addPage(){
		return "coordination/work_plan/add";
	}
	/**
	 * 添加计划
	 * @param wp
	 * @param errors
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param headIds
	 * @param deptIds
	 * @param userIds
	 * @param files
	 * @return
	 */
	@RequiresPermissions("workPlan:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid XtWorkPlan wp,Errors errors,Integer isSendMsg,String[] fzIds,String[] cyIds,String[] headIds,String[] deptIds,String[] userIds,FileList files){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
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
		
		return ajaxDone(service.addWorkPlan(wp, isSendMsg, fzIds, cyIds, headIds, deptIds, userIds, SAVEPATH, files));
		
	}
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workPlan:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		Map<String,Object> m=service.findWorkPlanById(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		return "coordination/work_plan/update";
		
	}
	/**
	 * 修改工作计划
	 * @param wp
	 * @param errors
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param headIds
	 * @param deptIds
	 * @param userIds
	 * @param uuids
	 * @param files
	 * @return
	 */
	@RequiresPermissions("workPlan:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid XtWorkPlan wp,Errors errors,Integer isSendMsg,String[] fzIds,String[] cyIds,String[] headIds,String[] deptIds,String[] userIds,String[] uuids,FileList files){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
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
		return ajaxDone(service.updateWorkPlan(wp, isSendMsg, fzIds, cyIds, headIds, deptIds, userIds, uuids, SAVEPATH, files));
		
	}
	/**
	 * 工作计划详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		Map<String,Object> m=service.findWorkPlanById(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		return "coordination/work_plan/show";
		
	}
	/**
	 * 文件下载
	 * @param name		文件原名
	 * @param uuid		保存时的UUID 包含后缀
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
    @RequestMapping(value = "/download")   
    public String down(String name,String uuid,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, uuid,ext,request,response);  
    }
	/**
	 * 删除工作计划
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workPlan:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteWorkPlan(ids,SAVEPATH));
		
	}
	/**
	 * 跳转到工作计划查询 页面 查询个人工作过计划
	 * @return
	 */
	@RequiresPermissions("workPlan:myRead")
	@RequestMapping("my/load")
	public String myLoad(){
		
		return "coordination/work_plan/load_my";
	}
	/**
	 * 个人工作计划查询
	 * @param param
	 * @return
	 */
	@RequiresPermissions("workPlan:myRead")
	@RequestMapping("my/query")
	public ModelAndView myQuery(PageParam param){
	
		return ajaxJsonEscape(service.selectMyWorkPlan(param));
	}
	/**
	 * 计划任务查询
	 * @param param
	 * @return
	 */
	@RequiresPermissions("workPlan:myRead")
	@RequestMapping("task/query")
	public ModelAndView queryTask(PageParam param,String id,String userId){
	
		return ajaxJsonEscape(service.selectWorkPlanTask(param,id,userId));
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("task/addPage")
	public String addTaskPage(){
		return "coordination/work_plan/task/add";
	}
	/**
	 * 添加计划
	 * @param wp
	 * @param errors
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param headIds
	 * @param deptIds
	 * @param userIds
	 * @param files
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("task/add")
	public ModelAndView addTask(@Valid XtWorkPlanTask t,Errors errors,FileList files){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
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
		return ajaxDone(service.addWorkPlanTask(t, SAVEPATH, files));
		
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("task/updatePage")
	public String updateTaskPage(String id,ModelMap map){
		
		XtWorkPlanTask t=service.get(XtWorkPlanTask.class, id);
		if(t==null){
			return NODATA;
		}
		map.put("t",t);
		map.put("files", JSON.parseArray(t.getFiles()));
		return "coordination/work_plan/task/update";
	}
	/**
	 * 修改计划任务
	 * @param wp
	 * @param errors
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param headIds
	 * @param deptIds
	 * @param userIds
	 * @param files
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	public ModelAndView updateTask(@Valid XtWorkPlanTask t,Errors errors,String[] uuids,FileList files){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
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
		return ajaxDone(service.updateWorkPlanTask(t, SAVEPATH, uuids,files));
		
	}
	/**
	 * 删除工作计划任务
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	public ModelAndView deleteTask(String id){
		
		return ajaxDone(service.deleteWorkPlanTask(id,SAVEPATH));
		
	}
	/**
	 * 跳转到批注查询页面
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/load")
	public String loadComment(){
		
		return "coordination/work_plan/comment/load";
	}
	/**
	 * 计划批注查询
	 * @param param
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/query")
	public ModelAndView queryComment(PageParam param,String id){
	
		return ajaxJsonEscape(service.selectWorkPlanComment(param, id));
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/addPage")
	public String addCommentPage(){
		return "coordination/work_plan/comment/add";
	}
	/**
	 * 添加计划批注
	 * @param c
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/add")
	public ModelAndView addComment(@Valid XtWorkPlanComment c,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		c.setUserId(ServletUtil.getMember().getId());
		c.setCreateTime(DateUtil.currentTimestamp());
		return ajaxDone(service.save(c));
		
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/updatePage")
	public String updateCommentPage(String id,ModelMap map){
		
		XtWorkPlanComment c=service.get(XtWorkPlanComment.class, id);
		if(c==null){
			return NODATA;
		}
		map.put("t",c);
		
		return "coordination/work_plan/comment/update";
	}
	/**
	 * 修改批注
	 * @param c
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/update")
	public ModelAndView updateComment(@Valid XtWorkPlanComment c,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
	
		return ajaxDone(service.updateWorkPlanComment(c));
		
	}
	/**
	 * 删除批注
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workPlan:show")
	@RequestMapping("comment/del")
	public ModelAndView deleteComment(String id){
		
		return ajaxDone(service.deleteWorkPlanComment(id));
		
	}
	
	
	
	
	
}
