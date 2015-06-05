/**  
 * @Project: jxoa
 * @Title: AfficheAction.java
 * @Package com.oa.manager.common_platform.action
 * @date 2013-5-31 上午9:58:28
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.action;

import java.util.Date;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.XtAffiche;
import com.oa.manager.common_platform.bean.XtAfficheDept;
import com.oa.manager.common_platform.service.IAfficheService;

/**
 * 
 * 类名：AfficheAction
 * 功能：公共平台--公告管理
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-31 上午9:58:28
 *
 */
@Controller
@RequestMapping("/affiche")
public class AfficheAction extends BaseAction{

	@Autowired
	private IAfficheService service;
	
	
	/**
	 * 系统公告管理 页面
	 * @return
	 */
	@RequiresPermissions("affiche:read")
	@RequestMapping("load")
	public String load(){
		
		
		return "common_platform/affiche/load";
	}
	/**
	 * 系统公告管理
	 * @param param
	 * @param a
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("affiche:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,XtAffiche a,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectAffiche(param, a, startDate, endDate));
	}
	
	/**
	 * 系统公告查看 
	 * @return
	 */
	@RequiresPermissions("affiche:myRead")
	@RequestMapping("load/my")
	public String loadmy(){
		
		return "common_platform/affiche/load_my";
	}
	
	/**
	 * 跳转到系统公告添加页面
	 * @return
	 */
	@RequiresPermissions("affiche:add")
	@RequestMapping("addPage")
	public String addPage(){
		
		return "common_platform/affiche/add";
	}
	/**
	 * 添加系统公告
	 * @param a
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("affiche:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid XtAffiche a, Errors errors){
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		if(a.getIsTop()==null||a.getIsTop()!=1){
			a.setIsTop((short)0);
		}
		a.setCreateTime(DateUtil.currentTimestamp());
		a.setUserId(ServletUtil.getMember().getId());
		return ajaxDone(service.save(a));
		
	}
	/**
	 * 查看单个系统公告
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("affiche:show")
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		XtAffiche xa=service.get(XtAffiche.class, id);
		if(xa==null){
			return NODATA;
		}
		
		map.addAttribute("xa",xa);
		return "common_platform/affiche/show";
		
		
	}
	/**
	 * 跳转到系统公告更新页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("affiche:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		
		XtAffiche xa=service.get(XtAffiche.class, id);
		if(xa==null){
			return NODATA;
		}
		
		map.addAttribute("xa",xa);
		return "common_platform/affiche/update";
		
		
	}
	/**
	 * 修改系统公告
	 * @param a
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("affiche:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid XtAffiche xa,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateAffiche(xa));
		
	}
	/**
	 * 删除系统公告
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("affiche:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteAffiche(ids));
		
	}
	
	/**
	 * 部门公告管理
	 * @return
	 */
	@RequiresPermissions("affiche:readDept")
	@RequestMapping("dept/load")
	public String loadXtAfficheDept(){
		
		return "common_platform/affiche/dept/load";
	}
	/**
	 * 部门公告管理
	 * @param param
	 * @param a
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("affiche:readDept")
	@RequestMapping("dept/query")
	public ModelAndView queryXtAfficheDept(PageParam param,XtAfficheDept a,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectAfficheDept(param, a, startDate, endDate));
	}
	/**
	 * 部门公告查看
	 */
	@RequiresPermissions("affiche:readMyDept")
	@RequestMapping("dept/load/my")
	public String loadmyXtAfficheDept(){
		
		return "common_platform/affiche/dept/load_my";
	}
	/**
	 * 部门公告管理
	 * @param param
	 * @param a
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("affiche:readMyDept")
	@RequestMapping("dept/my/query")
	public ModelAndView queryMyXtAfficheDept(PageParam param,XtAfficheDept a,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectMyAfficheDept(param, a, startDate, endDate));
	}
	/**
	 * 跳转到部门公告添加页面
	 * @return
	 */
	@RequiresPermissions("affiche:addDept")
	@RequestMapping("dept/addPage")
	public String addPageXtAfficheDept(){
		
		return "common_platform/affiche/dept/add";
	}
	/**
	 * 添加部门公告
	 * @param a
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("affiche:addDept")
	@RequestMapping("dept/add")
	public ModelAndView addXtAfficheDept(@Valid XtAfficheDept a, Errors errors){
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		if(a.getIsTop()==null||a.getIsTop()!=1){
			a.setIsTop((short)0);
		}
		a.setCreateTime(DateUtil.currentTimestamp());
		a.setUserId(ServletUtil.getMember().getId());
		return ajaxDone(service.save(a));
		
	}
	/**
	 * 查看单个部门公告
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("affiche:showDept")
	@RequestMapping("dept/show")
	public String showXtAfficheDept(String id,ModelMap map){
		XtAfficheDept xa=service.get(XtAfficheDept.class, id);
		if(xa==null){
			return NODATA;
		}
		
		map.addAttribute("xa",xa);
		return "common_platform/affiche/dept/show";
		
		
	}
	/**
	 * 跳转到部门公告更新页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("affiche:updateDept")
	@RequestMapping("dept/updatePage")
	public String updatePageXtAfficheDept(String id,ModelMap map){
		
		XtAfficheDept xa=service.get(XtAfficheDept.class, id);
		if(xa==null){
			return NODATA;
		}
		
		map.addAttribute("xa",xa);
		return "common_platform/affiche/dept/update";
		
		
	}
	/**
	 * 修改部门公告
	 * @param a
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("affiche:updateDept")
	@RequestMapping("dept/update")
	public ModelAndView updateXtAfficheDept(@Valid XtAfficheDept xa,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateAfficheDept(xa));
		
	}
	/**
	 * 删除部门公告
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("affiche:deleteDept")
	@RequestMapping("dept/del")
	public ModelAndView deleteXtAfficheDept(String[] ids){
		
		return ajaxDone(service.deleteAfficheDept(ids));
		
	}
	
	
	
	
	
	
	
}
