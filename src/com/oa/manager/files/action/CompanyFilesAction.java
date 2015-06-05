/**  
 * @Project: jxoa
 * @Title: CompanyFilesAction.java
 * @Package com.oa.manager.files.action
 * @date 2013-4-23 上午10:40:59
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
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.manager.files.bean.WdCfType;
import com.oa.manager.files.bean.WdCompanyFiles;
import com.oa.manager.files.service.ICompanyFilesService;

/**
 * 
 * 类名：CompanyFilesAction
 * 功能：文档管理--公司文档
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-23 上午10:40:59
 *
 */
@Controller
@RequestMapping("/companyFiles")
public class CompanyFilesAction extends BaseAction{
	@Autowired
	private ICompanyFilesService service; 
	/**
	 * 文件保存的目录
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"companyFiles";
	
	/**
	 * 跳转到文件加载页面
	 * @return
	 */
	@RequiresPermissions("companyFiles:read")
	@RequestMapping("load")
	public String load(){
		
		return "files/company/load";
	}
	/**
	 * 跳转到文件加载页面
	 * @return
	 */
	@RequiresPermissions("companyFiles:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WdCompanyFiles f,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectFiles(param, f, startDate, endDate));

	}
	
	/**
	 * 跳转到添加文件页面
	 * @return
	 */
	@RequiresPermissions("companyFiles:add")
	@RequestMapping("addPage")
	public String addFilePage(String wdSuperId){
		
		if(service.checkPower(wdSuperId, 1)){
			return "files/company/add";
		}else{
			return NOPOWER;
		}
	}
	/**
	 * 添加文件
	 * @return
	 */
	@RequiresPermissions("companyFiles:add")
	@RequestMapping("add")
	public ModelAndView addFile(@Valid WdCompanyFiles f,Errors errors,@RequestParam MultipartFile file){
		if(!service.checkPower(f.getWdSuperId(), 1)){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);//无操作权限
		}
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
	 * 查看文件信息
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("companyFiles:read")
	@RequestMapping("show")
	public String showFile(String id,ModelMap map){
		WdCompanyFiles wf=service.get(WdCompanyFiles.class, id);
		if(wf==null){
			return NODATA;
		}
		map.addAttribute("f",wf);
		return "files/company/show";
	}
	/**
	 * 跳转到文件编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("companyFiles:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,String wdSuperId,ModelMap map){
		if(!service.checkPower(wdSuperId, 2)){
			return NOPOWER;
		}
		WdCompanyFiles file=service.get(WdCompanyFiles.class, id);
		if(file==null){
			return NODATA;
		}
		WdCfType type=service.get(WdCfType.class, file.getWdSuperId());
		if(type!=null){
			map.addAttribute("type",type.getName());
		}
		map.addAttribute("f",file);
		
		return "files/company/update";
		
	}
	/**
	 * 修改文件夹
	 * @param f
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("companyFiles:update")
	@RequestMapping("update")
	public ModelAndView updateFile(@Valid WdCompanyFiles f,Errors errors,@RequestParam MultipartFile file){
	
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
	 * 删除文件
	 * @param id
	 * @return
	 */
	@RequiresPermissions("companyFiles:delete")
	@RequestMapping("del")
	public ModelAndView deleteFile(String id){
		
		return ajaxDone(service.deleteFile(id, SAVEPATH));
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
	@RequiresPermissions("companyFiles:read")
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
	@RequiresPermissions("companyFiles:read")
    @RequestMapping(value = "/inline")   
    public String inline(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.inline(SAVEPATH, name, newName, ext, request,response);
        
    } 
}
