/**  
 * @Project: jxoa
 * @Title: vehiclemanagementAction.java
 * @Package com.oa.manager.administration.action
 * @date 2013-6-4 下午5:52:20
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
import com.oa.manager.administration.bean.VmVehiclemanagement;
import com.oa.manager.administration.service.IVehiclemanagementService;


/**
 * 
 * 类名：vehiclemanagementAction
 * 功能：行政办公--车辆管理--车辆管理
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-4 下午5:52:20
 *
 */
@Controller 
@RequestMapping("vehiclemanagement")
public class VehiclemanagementAction extends BaseAction {
   @Autowired
   private IVehiclemanagementService ivehiclemanagementService;
   /**
    * 跳转到---车辆管理界面
    * @return
    */
   @RequiresPermissions("vehicle:read")
   @RequestMapping("load")
   public String load(){
	   return "administration/vehiclemanagement/vehiclemanagement/load";
   }
   /**
    * 分页查询车辆
    * @return
    */
   @RequiresPermissions("vehicle:read")
   @RequestMapping("laodall")
   public ModelAndView laodall(PageParam param,VmVehiclemanagement vm){
	   
	   return ajaxJsonEscape(ivehiclemanagementService.load(param, vm));
   }
   
   
   
   /**
    * 跳转到添加页面
    * 
    */
   @RequiresPermissions("vehicle:add")
   @RequestMapping("addPage")
   public String addPage(){
	   return "administration/vehiclemanagement/vehiclemanagement/add";
   }
   /**
    * 增加车辆
    * @param vm
    * @param errors
    * @return
    */
   @RequiresPermissions("vehicle:add")
   @RequestMapping("add")
   public ModelAndView add(@Valid VmVehiclemanagement vm,Errors errors ){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		vm.setCreatedate(DateUtil.currentTimestamp());
		vm.setModydate(DateUtil.currentTimestamp());
		Member me=ServletUtil.getMember();
		vm.setPromulgator(me.getId());
		return ajaxDone(ivehiclemanagementService.save(vm));
   }
   /**
    * 批量删除车辆管理
    * @param ids
    * @return
    */
   @RequiresPermissions("vehicle:delete")
   @RequestMapping("delete")
   public ModelAndView delete(String[] ids){
	   return ajaxDone(ivehiclemanagementService.deletes(ids));
   }
   /**
    * 跳转到修改页面
    * @param id
    * @param map
    * @return
    */
   @RequiresPermissions("vehicle:update")
   @RequestMapping("updatePage")
   public String updatePage(String id,ModelMap map){
	   VmVehiclemanagement vg= ivehiclemanagementService.get(VmVehiclemanagement.class, id);
	   if(vg==null){
		  return NODATA; 
	   }
	   map.addAttribute("vm",ivehiclemanagementService.selectcar( id));
	   return "administration/vehiclemanagement/vehiclemanagement/update";
   }
   /**
    * 修改车辆管理信息
    * @param vm
    * @param errors
    * @return
    */
   @RequiresPermissions("vehicle:update")
   @RequestMapping("update")
   public ModelAndView update(@Valid VmVehiclemanagement vm,Errors errors){
	   if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
       }
	   Member me=ServletUtil.getMember();
	   vm.setBypromulgator(me.getId());
	   vm.setModydate(DateUtil.currentTimestamp());
	   return ajaxDone(ivehiclemanagementService.updateVmVehiclemanagement(vm));
   }
   /**
    * 车辆查找带回 
    * @param vm
    * @param param
    * @param map
    * @return
    */
  @RequestMapping("lookUpPage")
   public String lookUpPage(){
	  
	   return "administration/vehiclemanagement/vehiclemanagement/lookup";
  }
  @RequestMapping("lookUp")
  public ModelAndView lookUp(PageParam param,VmVehiclemanagement vm){
	  
	  return ajaxJsonEscape(ivehiclemanagementService.load(param, vm));
  }
  
  /**
   * 查看车辆管理的详情
   * @param id
   * @param map
   * @return
   */
  @RequestMapping("lookvehiclemanagement")
  public String   lookvehiclemanagement(String id,ModelMap map){
	  VmVehiclemanagement vg=ivehiclemanagementService.get(VmVehiclemanagement.class, id);
	  if(vg==null){
		  return NODATA;
	  }
	   map.addAttribute("vm",ivehiclemanagementService.selectcar( id));
	   return "administration/vehiclemanagement/vehiclemanagement/lookvehiclemanagermer";
  }
  /**
   * 跳到高级查询
   * @return
   */
  
  @RequestMapping("searchPage")
  public String searchPage(){
	  return "administration/vehiclemanagement/vehiclemanagement/search";
  }
}
