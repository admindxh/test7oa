/**  
 * @Title ProjectAction.java
 * @date 2013-12-10 上午9:28:36
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
import com.oa.commons.util.FileUtils;
import com.oa.manager.coordination.bean.Project;
import com.oa.manager.coordination.bean.ProjectFiles;
import com.oa.manager.coordination.service.ProjectService;

/**
 * 项目管理
 * @author LiuJincheng
 * @version 1.0
 */
@Controller 
@RequestMapping("/project")
public class ProjectAction extends BaseAction{
	@Autowired
	private ProjectService service;
	
	/**
	 * 文件保存的目录
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"projectFiles";
	
	/**
	 * 跳转到项目管理 页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("load")
	public String load(){
		
		return "coordination/project/load";
	}
	/**
	 * 项目查询
	 * @param param
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param){
	
		return ajaxJsonEscape(service.selectProject(param));
	}
	/**
	 * 跳转到项目管理 页面
	 * @return
	 */
	@RequiresPermissions("project:myRead")
	@RequestMapping("my/load")
	public String loadMy(){
		
		return "coordination/project/load_my";
	}
	/**
	 * 项目查询
	 * @param param
	 * @return
	 */
	@RequiresPermissions("project:myRead")
	@RequestMapping("my/query")
	public ModelAndView queryMy(PageParam param){
	
		return ajaxJsonEscape(service.selectMyProject(param));
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("project:add")
	@RequestMapping("addPage")
	public String addPage(){
		return "coordination/project/add";
	}
	/**
	 * 添加项目
	 * @param p
	 * @param errors
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param deptIds
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("project:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Project p,Errors errors,Integer isSendMsg,String[] fzIds,String[] deptIds,String[] userIds){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }

		return ajaxDone(service.addProject(p, isSendMsg, fzIds,  deptIds, userIds));
		
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		Map<String,Object> m=service.findProjectById(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		return "coordination/project/update";
		
	}
	/**
	 * 修改项目
	 * @param p
	 * @param errors
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param deptIds
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("project:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Project p,Errors errors,Integer isSendMsg,String[] fzIds,String[] deptIds,String[] userIds){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateProject(p, isSendMsg, fzIds,deptIds, userIds));
		
	}
	/**
	 * 查询项目详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		Map<String,Object> m=service.findProjectById(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		
		return "coordination/project/show";
		
	}
	/**
	 * 删除项目
	 * @param id
	 * @return
	 */
	@RequiresPermissions("project:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteProject(ids));
		
	}
	
	
	/**
	 * 跳转到项目的文档管理
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/load")
	public String loadFiles(String projectId,ModelMap map){
		
		//判断是否有添加 删除 修改 文档的权限  项目创建人，负责人，任务的执行人 都有添加 修改 删除的权限
		int power=service.hasPowerForFile(projectId);
		if(power==0){
			return NODATA;
		}
		if(power==1){
			map.addAttribute("fzr", true);
		}
		return "coordination/project/file/load";

	}
	/**
	 * 项目的文档管理 查询页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/query")
	public ModelAndView queryFiles(PageParam param,String id){
		
		return ajaxJsonEscape(service.selectProjectFiles(param, id));
	}
	/**
	 * 跳转到添加文件页面
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/addPage")
	public String addFilePage(){
		
		return "coordination/project/file/add";
	
	}
	/**
	 * 添加文件
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/add")
	public ModelAndView addFile(@Valid ProjectFiles f,Errors errors,FileList files){
		//判断是否有添加操作权限
		int  power=service.hasPowerForFile(f.getProjectId());
		if(power==0){
			return ajaxDoneTextError("项目不存在，可能已被删除！");
		}
		if(power==2){
			return ajaxDoneTextError("没有添加权限，只有项目创建者,负责人,任务执行人才有添加权限！");
		}
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
		return ajaxDone(service.addProjectFiles(f, SAVEPATH, files));
	}
	/**
	 * 跳转到文档编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/show")
	public String showFiles(String id,ModelMap map){
		ProjectFiles file=service.get(ProjectFiles.class, id);
		if(file==null){
			return NODATA;
		}
		map.addAttribute("f",file);
		map.put("files", JSON.parseArray(file.getFiles()));
		return "coordination/project/file/show";
		
	}
	/**
	 * 跳转到文档编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/updatePage")
	public String updateFilePage(String id,ModelMap map){
		ProjectFiles file=service.get(ProjectFiles.class, id);
		if(file==null){
			return NODATA;
		}
		map.addAttribute("f",file);
		map.put("files", JSON.parseArray(file.getFiles()));
		return "coordination/project/file/update";
		
	}
	/**
	 * 修改文档
	 * @param f
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/update")
	public ModelAndView updateFile(@Valid ProjectFiles f,Errors errors,String[] uuids,FileList files){
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
		return ajaxDone(service.updateProjectFiles(f, uuids, SAVEPATH, files));
	}
	/**
	 * 文件下载
	 * @param name		文件原名
	 * @param uuid		保存时的UUID + 后缀
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("project:read")
    @RequestMapping(value = "file/download")   
    public String down(String name,String uuid,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, uuid,ext,request,response);  
    }
	/**
	 * 删除文档
	 * @param projectId 关联的项目id
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("project:read")
	@RequestMapping("file/del")
	public ModelAndView deleteFiles(String projectId,String[] ids){
		//判断是否有删除操作权限
		int  power=service.hasPowerForFile(projectId);
		if(power==0){
			return ajaxDoneTextError("项目不存在，可能已被删除！");
		}
		if(power==2){
			return ajaxDoneTextError("没有删除权限，只有项目创建者,负责人,任务执行人才有删除权限！");
		}
		
		return ajaxDone(service.deleteProjectFiles(projectId,ids, SAVEPATH));
		
	}
	
	
	
}
