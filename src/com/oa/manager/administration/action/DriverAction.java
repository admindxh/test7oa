/**  
 * @Project: jxoa
 * @Title: driverAction.java
 * @Package com.oa.manager.administration.action
 * @date 2013-6-5 下午4:38:02
 * @Copyright: 2013 
 */
package com.oa.manager.administration.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.bean.VmDrivertable;
import com.oa.manager.administration.service.IDriverService;

/**
 * 
 * 类名：driverAction
 * 功能：行政办公--车辆管理--驾驶员
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-5 下午4:38:02
 *
 */
@Controller
@RequestMapping("driver")
public class DriverAction extends BaseAction {
	@Autowired
	private IDriverService idriverservice;
	/**
	 * 跳转到驾驶员信息界面 
	 * @return
	 */
   @RequiresPermissions("driver:read")
   @RequestMapping("load")
   public String load(){
	
	return "administration/vehiclemanagement/driver/load";
    }
   @RequiresPermissions("driver:read")
   @RequestMapping("loadall")
    public ModelAndView loadall(PageParam param,VmDrivertable vt){
    	
    	return ajaxJsonEscape(idriverservice.load(param, vt));
    }
   
   /**
    * 跳转到增加页面
    * @return
    */
   @RequiresPermissions("driver:add")
   @RequestMapping("addPage")
   public String addPage(){
	 return  "administration/vehiclemanagement/driver/add";
   }
   /**
    * 增加驾驶员
    * @param vt
    * @return
    */
   @RequiresPermissions("driver:add")
   @RequestMapping("add")
   public ModelAndView add(VmDrivertable vt){
	   Member me=ServletUtil.getMember();
	   vt.setPromulgator(me.getId());
	   vt.setCreatedate(DateUtil.currentTimestamp());
	   return ajaxDone(idriverservice.adddriver(vt));
   }
   /**
    * 跳转到改页面
    * @param id
    * @param map
    * @return
    */
   @RequiresPermissions("driver:update")
   @RequestMapping("updatePage")
   public String updatePage(String id,ModelMap map){
	     VmDrivertable vt= idriverservice.get(VmDrivertable.class, id);
	     if(vt==null){
	    	 return NODATA;
	     }
	     map.addAttribute("vt",vt);
		 return  "administration/vehiclemanagement/driver/update";
   }
   /**
    * 修 驾驶员信息
    */
   @RequiresPermissions("driver:update")
   @RequestMapping("update")
   public ModelAndView update( VmDrivertable vt){
	   vt.setModydate(DateUtil.currentTimestamp());
	   return ajaxDone(idriverservice.updateVd(vt));
   }
   /**
    * 批量删除
    * @param ids
    * @return
    */
   @RequiresPermissions("driver:delete")
   @RequestMapping("delete")
   public ModelAndView delete(String[] ids){
	   return ajaxDone(idriverservice.deletes(ids));
   }
   /**
    * 跳转到--查找驾驶员
    * @param vt
    * @param param
    * @param map
    * @return
    */
   @RequestMapping("lookupdriver")
   public String lookupdriver(){
		
		return "administration/vehiclemanagement/accident/loopup_drivingname";
   }
   /**
    * 分页查找驾驶员
    * @param param
    * @param vt
    * @return
    */
   @RequestMapping("lookupalldriver")
   public ModelAndView lookupalldriver(PageParam param,VmDrivertable vt){
	   
	   return ajaxJsonEscape(idriverservice.load(param, vt));
   }
   /**
    * 查看详情
    * @param id
    * @param map
    * @return
    */
   @RequestMapping("lookdriver")
   public String lookdriver(String id,ModelMap map){
	   VmDrivertable vt=idriverservice.get(VmDrivertable.class, id);
	   if(vt==null){
		   return NODATA;
	   }
	   map.addAttribute("vt",vt );
		 return  "administration/vehiclemanagement/driver/lookldriver";
   }
}
