/**  
 * @Project: jxoa
 * @Title: PersonalFilesAction.java
 * @Package com.oa.manager.files.action
 * @date 2013-4-18 下午5:14:10
 * @Copyright: 2013 
 */
package com.oa.manager.files.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.WdPersonalFiles;
import com.oa.manager.files.service.IPersonalFilesService;

/**
 * 
 * 类名：PersonalFilesAction
 * 功能：文档管理--个人文档
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-18 下午5:14:10
 *
 */
@Controller
@RequestMapping("/personalFiles")
public class PersonalFilesAction extends BaseAction{
	
	@Autowired
	private IPersonalFilesService service; 
	/**
	 * 文件保存的目录
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"personalFiles";
	
	/**
	 * 跳转到文件加载页面
	 * @return
	 */
	@RequiresPermissions("personalFiles:read")
	@RequestMapping("load")
	public String load(){
		
		return "files/personal/load";

	}
	/**
	 * 跳转到文件加载页面
	 * @return
	 */
	@RequiresPermissions("personalFiles:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WdPersonalFiles f,Date startDate,Date endDate){
		

		f.setWdUserId(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.selectFiles(param, f, startDate, endDate));

	}
	/**
	 * 跳转到添加文件页面
	 * @return
	 */
	@RequiresPermissions("personalFiles:add")
	@RequestMapping("addFilePage")
	public String addFilePage(){
		
		return "files/personal/add_file";
	
	}
	/**
	 * 添加文件
	 * @return
	 */
	@RequiresPermissions("personalFiles:add")
	@RequestMapping("addFile")
	public ModelAndView addFile(@Valid WdPersonalFiles f,Errors errors,@RequestParam MultipartFile file){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		if(file.isEmpty()){
			return ajaxDoneError();
		}
		ModelAndView view=FileUtils.validateFile(file, 1024L*1024*500, null, new String[]{"exe"});
		if(view!=null)return view;
		return ajaxDone(service.addFile(f,SAVEPATH,file));
	}
	/**
	 * 跳转到添加目录页面
	 * @return
	 */
	@RequiresPermissions("personalFiles:add")
	@RequestMapping("addNodePage")
	public String addNodePage(){
		
		return "files/personal/add_node";
	
	}
	/**
	 * 添加目录
	 * @return
	 */
	@RequiresPermissions("personalFiles:add")
	@RequestMapping("addNode")
	public ModelAndView addNode(@Valid WdPersonalFiles f,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        } 
		
		return ajaxDone(service.addNode(f));
	}
	
	/**
	 * 查看文件信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("personalFiles:read")
	@RequestMapping("show")
	public String showFile(String id,ModelMap map){
		WdPersonalFiles wf=service.get(WdPersonalFiles.class, id);
		if(wf==null){
			return NODATA;
		}
		if(!ServletUtil.getMember().getId().equals(wf.getWdUserId())){
			return NOPOWER;
		}
		map.addAttribute("f",wf);
		
		return "files/personal/show";
	}
	/**
	 * 跳转到文件编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("personalFiles:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		WdPersonalFiles file=service.get(WdPersonalFiles.class, id);
		if(file==null){
			return NODATA;
		}
		if(!ServletUtil.getMember().getId().equals(file.getWdUserId())){
			return NOPOWER;
		}
		map.addAttribute("f",file);
		String sid=file.getWdSuperId();
		if(sid!=null&&sid.length()!=0&&!sid.equals("0")){
			WdPersonalFiles supFile=service.get(WdPersonalFiles.class, sid);
			if(supFile!=null){
				map.addAttribute("superName",supFile.getWdName());
			}
		}else{
			map.addAttribute("superName","个人文档");
		}
		
		if(file.getWdType()==0){
			//目录
			return "files/personal/update_node";
		}else{
			return "files/personal/update_file";
		}
	}
	/**
	 * 修改文件
	 * @param f
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("personalFiles:update")
	@RequestMapping("updateNode")
	public ModelAndView updateNode(@Valid WdPersonalFiles f,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateNode(f));
		
	}
	/**
	 * 修改文件
	 * @param f
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("personalFiles:update")
	@RequestMapping("updateFile")
	public ModelAndView updateFile(@Valid WdPersonalFiles f,Errors errors,@RequestParam MultipartFile file){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		if(!file.isEmpty()){
			ModelAndView view=FileUtils.validateFile(file, 1024L*1024*500, null, new String[]{"exe"});
			if(view!=null)return view;
		}
		return ajaxDone(service.updateFile(f, SAVEPATH,file));
		
	}
	
	
	/**
	 * 批量删除文件
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("personalFiles:delete")
	@RequestMapping("delFile")
	public ModelAndView deleteFile(String[] ids){
		
		return ajaxDone(service.deleteFile(ids, SAVEPATH));
	}
	/**
	 * 删除文件夹 只能删除单个文件夹，而且文件夹必须为空才能删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("personalFiles:delete")
	@RequestMapping("delNode")
	public ModelAndView deleteNode(String id){
		
		return ajaxDone(service.deleteNode(id));
	
	}
	/**
	 * 文件预览
	 * @param id 文件保存时名称
	 * @param name 文件原名
	 * @param ext  文件后缀
	 * @param request  
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("personalFiles:read")
    @RequestMapping(value = "/inline")   
    public String inline(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.inline(SAVEPATH, name, newName, ext,request,response);
        
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
	@RequiresPermissions("personalFiles:read")
    @RequestMapping(value = "/download")   
    public String down(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, newName, ext,request,response);
        
    }  
    /**
	 * 跳转到文件加载页面 树状
	 * @return
	 */
	@RequestMapping("tree/load")
	public String loadTree(ModelMap map){
		
		return "files/personal/tree/load";

	}
    
    
	/**
	 * 跳转到文件加载页面 树状
	 * @return
	 */
	@RequestMapping("tree/allNode")
	public ModelAndView allNode(){
		
		return ajaxJsonEscape(service.selectAllNodesByUserId(ServletUtil.getMember().getId()));

	}
	/**
	 * 目录，查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		
		return "files/personal/lookup";
	}
	

	
	
}
