/**  
 * @Project: jtoa
 * @Title: OnDutyAction.java
 * @Package com.oa.manager.personnel.action
 * @date 2013-12-4 下午9:50:36
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.PerSchedule;
import com.oa.manager.personnel.bean.XtAttendanceClose;
import com.oa.manager.personnel.bean.XtOnduty;
import com.oa.manager.personnel.service.IAttendanceService;
import com.oa.manager.personnel.service.IOnDutyService;

/**
 * 
 * 类名：OnDutyAction
 * 功能：值班日志管理
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-12-4 下午9:50:36
 *
 */
@Controller
@RequestMapping("onDuty")
public class OnDutyAction extends BaseAction {
	@Autowired
	private IOnDutyService service;
	/**
	 * 通知上传的保存路径
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"onDuty";
	
	@RequiresPermissions("onDuty:add")
	@RequestMapping("addOnDutyPage")
	public String addOnDutyPage(){
		return "personnel/onDuty/add";
	}
	/**
	 * 保存值班记录	
	 * @param onduty
	 * @return
	 */
	@RequiresPermissions("onDuty:add")
	@RequestMapping("addOnDuty")
	public ModelAndView addOnDuty(XtOnduty onduty,FileList files){
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
		return ajaxDone( service.saveOnDuty(onduty,SAVEPATH,files));
	}
	
	/**
	 * 值班记录页面
	 * @return
	 */
	@RequiresPermissions("onDuty:load")
	@RequestMapping("onDutyLoad")
	public String onDutyLoad(){
		
		return "personnel/onDuty/load";
	}
	/**
	 * 查询
	 * @param param
	 * @param o
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("onDuty:load")
	@RequestMapping("selectOnDuty")
	public ModelAndView selectOnDuty(PageParam param,XtOnduty o,Date startDate,Date endDate){
		 
		return ajaxJsonEscape(service.selectOnDuty(param, o, startDate, endDate));
	}
	/**
	 * 修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("onDuty:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		map = service.selectOnDutyById(id, map);
		if(map.get("o")==null){
			return NODATA;
		}else{
			return "personnel/onDuty/update";
		}
	}
	
	/**
	 * 修改
	 * @param onduty
	 * @param files
	 * @return
	 */
	@RequiresPermissions("onDuty:update")
	@RequestMapping("update")
	public ModelAndView update(XtOnduty onduty,FileList files){
		
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
		return ajaxDone( service.saveOnDuty(onduty,SAVEPATH,files));
	}
	/**
	 * 单独删除值班附件
	 * @param id 附件id 
	 * @return
	 */
	@RequiresPermissions("onDuty:update")
	@RequestMapping("delFile")
	public ModelAndView deleteFile(String id){
		
		return ajaxDone(service.deleteOnDutyFiles(id,SAVEPATH));
		
	}
	
	/**
	 * 查看值班记录详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("onDuty:show")
	@RequestMapping("showOnDuty")
	public String showOnDuty(String id,ModelMap map){
		map = service.selectOnDutyById(id, map);
		if(map.get("o")==null){
			return NODATA;
		}else{
			return "personnel/onDuty/show";
		}
	}

	/**
	 * 文件下载
	 * @param newName 文件保存时名称
	 * @param name 文件原名
	 * @param ext  文件后缀
	 * @param request  
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("onDuty:show")
    @RequestMapping("download")   
    public String down(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, newName, ext,request,response);
    }  
	
	/**
	 * 删除值班记录及附件
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("onDuty:delOnDuty")
	@RequestMapping("delOnDuty")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteXtOnduty(ids,SAVEPATH));
		
	}
}
