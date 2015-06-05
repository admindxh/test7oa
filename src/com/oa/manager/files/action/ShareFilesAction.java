/**  
 * @Project: jxoa
 * @Title: ShareFilesAction.java
 * @Package com.oa.manager.files.action
 * @date 2013-4-23 下午3:30:30
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
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.WdShareFiles;
import com.oa.manager.files.service.IShareFilesService;

/**
 * 
 * 类名：ShareFilesAction
 * 功能：文档管理--共享文档
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-23 下午3:30:30
 *
 */
@Controller
@RequestMapping("/shareFiles")
public class ShareFilesAction extends BaseAction{
	@Autowired
	private IShareFilesService service; 
	/**
	 * 文件保存的目录
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"shareFiles";
	
	
	/**
	 * 跳转到文件加载页面
	 * @return
	 */
	@RequiresPermissions("shareFiles:read")
	@RequestMapping("load")
	public String load(){
		
		return "files/share/load";

	}
	/**
	 * 跳转到文件加载页面
	 * @return
	 */
	@RequiresPermissions("shareFiles:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WdShareFiles f,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectFiles(param, f, startDate, endDate));

	}
	/**
	 * 跳转到添加文件页面
	 * @return
	 */
	@RequiresPermissions("shareFiles:addFile")
	@RequestMapping("addFilePage")
	public String addFilePage(){
		
		return "files/share/add_file";
	
	}
	/**
	 * 添加文件
	 * @return
	 */
	@RequiresPermissions("shareFiles:addFile")
	@RequestMapping("addFile")
	public ModelAndView addFile(@Valid WdShareFiles f,Errors errors,@RequestParam MultipartFile file){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		if(file.isEmpty()){
			return ajaxDoneError();
		}
		ModelAndView view=FileUtils.validateFile(file, 1024L*1024*500, null, new String[]{"exe"});
		if(view!=null)return view;
		Member me=ServletUtil.getMember();
		f.setWdUserId(me.getId());
		f.setWdCreateTime(DateUtil.currentTimestamp());
		f.setWdType((short)1);
		
		return ajaxDone(service.addFile(f,SAVEPATH,file));
	}
	/**
	 * 跳转到添加目录页面
	 * @return
	 */
	@RequiresPermissions("shareFiles:addNode")
	@RequestMapping("addNodePage")
	public String addNodePage(){
		
		return "files/share/add_node";
	
	}
	/**
	 * 添加目录
	 * @return
	 */
	@RequiresPermissions("shareFiles:addNode")
	@RequestMapping("addNode")
	public ModelAndView addNode(@Valid WdShareFiles f,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		Member me=ServletUtil.getMember();
		f.setWdCreateTime(DateUtil.currentTimestamp());
		f.setWdType((short)0);//目录
		f.setWdUserId(me.getId());
		return ajaxDone(service.addNode(f));
	}
	
	/**
	 * 查看文件信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("shareFiles:read")
	@RequestMapping("show")
	public String showFile(String id,ModelMap map){
		WdShareFiles wf=service.get(WdShareFiles.class, id);
		if(wf==null){
			return NODATA;
		}
		map.addAttribute("f",wf);
		
		return "files/share/show";
	}
	/**
	 * 跳转到文件编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		WdShareFiles file=service.get(WdShareFiles.class, id);
		if(file==null){
			return NODATA;
		}
		map.addAttribute("f",file);
		String sid=file.getWdSuperId();
		if(sid!=null&&sid.length()!=0&&!sid.equals("0")){
			WdShareFiles supFile=service.get(WdShareFiles.class, sid);
			if(supFile!=null){
				map.addAttribute("superName",supFile.getWdName());
			}
		}else{
			map.addAttribute("superName","共享文档");
		}
		if(file.getWdType()==0){
			//目录
			return "files/share/update_node";
		}else{
			return "files/share/update_file";
		}
	}
	/**
	 * 修改文件
	 * @param f
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("shareFiles:updateNode")
	@RequestMapping("updateNode")
	public ModelAndView updateNode(@Valid WdShareFiles f,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateNode(f));
		
	}
	/**
	 * 修改文件夹
	 * @param f
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("shareFiles:updateFile")
	@RequestMapping("updateFile")
	public ModelAndView updateFile(@Valid WdShareFiles f,Errors errors,@RequestParam MultipartFile file){
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
	@RequiresPermissions("shareFiles:deleteFile")
	@RequestMapping("delFile")
	public ModelAndView deleteFile(String[] ids){
		
		return ajaxDone(service.deleteFile(ids, SAVEPATH));
	}
	/**
	 * 删除文件夹 只能删除单个文件夹，而且文件夹必须为空才能删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("shareFiles:deleteNode")
	@RequestMapping("delNode")
	public ModelAndView deleteNode(String id){
		
		return ajaxDone(service.deleteNode(id));
	
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
	@RequiresPermissions("shareFiles:read")
    @RequestMapping(value = "/download")   
    public String down(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, newName, ext, request,response);
        
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
	@RequiresPermissions("shareFiles:read")
    @RequestMapping(value = "/inline")   
    public String inline(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.inline(SAVEPATH, name, newName, ext, request,response);
        
    }  
    
    
    
    
    
    
    /**
  	 * 跳转到文件加载页面 树状
  	 * @return
  	 */
  	@RequestMapping("tree/load")
  	public String loadTree(ModelMap map){
  		
  		return "files/share/tree/load";

  	}
      
      
  	/**
  	 * 跳转到文件加载页面 树状
  	 * @return
  	 */
  	@RequestMapping("tree/allNode")
  	public ModelAndView allNode(){
  		
  		return ajaxJsonEscape(service.selectAllNodes());

  	}
  	/**
	 * 目录，查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		
		return "files/share/lookup";
	}
  	
    
    
    
    
    
    
    
    
    
    
    
    
}
