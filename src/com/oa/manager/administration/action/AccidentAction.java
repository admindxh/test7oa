/**  
 * @Project: jxoa
 * @Title: AccidentAction.java
 * @Package com.oa.manager.administration.action
 * @date 2013-6-6 下午3:02:59
 * @Copyright: 2013 
 */
package com.oa.manager.administration.action;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.bean.VmAccident;
import com.oa.manager.administration.service.IAccidentService;

/**
 * 
 * 类名：AccidentAction
 * 功能： 行政办公--车辆管理--事故管理
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-6 下午3:02:59
 *
 */
@Controller
@RequestMapping("accident")
public class AccidentAction extends BaseAction {
	@Autowired
	private IAccidentService iaccidentservice;
	/**
	 * 跳转到查询事故管理页面
	 * @return
	 */
	@RequiresPermissions("accident:read")
	@RequestMapping("loadaccident")
    public String loadaccident(){
		
   	 return "administration/vehiclemanagement/accident/load";
    }
	@RequiresPermissions("accident:read")
	@RequestMapping("loadallaccident")
	public ModelAndView loadallaccident(PageParam param,VmAccident vd){
		
		return ajaxJsonEscape(iaccidentservice.loadaccident(vd, param));
	}
	
	/**
	 * 跳转到增加页面
	 * @return
	 */
	@RequiresPermissions("accident:add")
	@RequestMapping("addPage")
	public String addPage(){
		 return "administration/vehiclemanagement/accident/add";
	}
	/**
	 * 增加事故管理
	 * @param vd
	 * @return
	 */
	@RequiresPermissions("accident:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid VmAccident vd,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		vd.setCreatedate(DateUtil.currentTimestamp());
		
		
		Member me=ServletUtil.getMember();
		vd.setPromulgator(me.getId());
		return ajaxDone(iaccidentservice.save(vd));
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("accident:delete")
	@RequestMapping("delete")
	public ModelAndView delete(String[] ids){
		return ajaxDone(iaccidentservice.deletes(ids));
	}
	/**
	 * 跳转到事故修改页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("accident:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		VmAccident vd=iaccidentservice.get(VmAccident.class, id);
		if(vd==null){
			return NODATA;
		}
		map.addAttribute("vd",iaccidentservice.updateselect(id));
		
		return "administration/vehiclemanagement/accident/update";
	}
	/**
	 *修改事故
	 * @param 
	 * @return
	 */
	@RequiresPermissions("accident:update")
	@RequestMapping("update")
	public ModelAndView update( @Valid VmAccident vd,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(iaccidentservice.updateVd(vd));
	}
	/**
	 * 查看详情
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("lookaccident")
	public String lookaccident(String id,ModelMap map){
		map.addAttribute("vd",iaccidentservice.updateselect(id));
		if(map.get("vd")==null){
			return NODATA;
		}
		return "administration/vehiclemanagement/accident/lookaccident";
	}

}
