/**  
 * @Project: jxoa
 * @Title: DistrictAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-6-19 下午2:01:55
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDistrict;
import com.oa.manager.system.service.IDistrictService;

/**
 * 
 * 类名：DistrictAction
 * 功能：地区管理
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-19 下午2:01:55
 *
 */
@Controller
@RequestMapping("/district")
public class DistrictAction extends BaseAction{
	
	@Autowired
	private IDistrictService service; 
	
	
	/**
	 * 地区管理 条件分页查询
	 * @return
	 */
	@RequiresPermissions("district:read")
	@RequestMapping("load")
	public String load(String superId,ModelMap map){
		
		if(StringUtils.isNotBlank(superId)){
			SyDistrict superDis=service.get(SyDistrict.class, superId);
			if(superDis!=null&&superDis.getSuperId()!=null){
				map.addAttribute("spId",superDis.getSuperId());
				map.addAttribute("spName",superDis.getDisName());
			}else{
				map.addAttribute("spName","地区管理");
			}
		}else{
			map.addAttribute("spName","地区管理");
		}
		return "system/district/load";
	
	}
	/**
	 * 地区管理 条件分页查询
	 * @param param
	 * @param dis
	 * @return
	 */
	@RequiresPermissions("district:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,SyDistrict dis){
		
		return ajaxJsonEscape(service.selectDistrict(param, dis));
		
	
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("district:add")
	@RequestMapping("addPage")
	public String addPage(){
		
		return "system/district/add";
	
	}
	/**
	 * 添加地区
	 * @param dis
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("district:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid SyDistrict dis,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.addDistrict(dis));
		
	}
	/**
	 * 跳转到自定义设置修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("district:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		SyDistrict dis=service.get(SyDistrict.class, id);
		if(dis==null){
			return NODATA;
		}
		map.addAttribute("d",dis);
		return "system/district/update";
	}
	/**
	 * 修改
	 * @param dis
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("district:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid SyDistrict dis,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateDistrict(dis));
		
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("district:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteDistrict(ids));
	}
	
	/**
	 * 地区查找带回
	 * @param map
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(ModelMap map){
		
		map.addAttribute("dis",service.selectAllDistrict());
		
		return "system/district/lookup";
	}
	/**
	 * 地区查找带回
	 * @param map
	 * @return
	 */
	@RequestMapping("lookUp")
	public ModelAndView lookUp(){
		
		return ajaxJsonEscape(service.selectAllDistrict());
	}
	
	
	
	
}
