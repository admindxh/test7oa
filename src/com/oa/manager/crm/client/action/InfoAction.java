/**  
 * @Project: jtoa
 * @Title: InfoAction.java
 * @Package com.oa.manager.crm.client.action
 * @date 2013-11-29 下午02:45:02
 * @Copyright: 2013 
 */
package com.oa.manager.crm.client.action;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.crm.client.bean.Info;
import com.oa.manager.crm.client.service.IInfoService;
import com.oa.manager.personalOffice.bean.GroupAddressBook;
import com.oa.manager.personalOffice.bean.PersonalAddressBook;
import com.oa.manager.personalOffice.service.IPersonalAddressBookService;
import com.oa.manager.personnel.bean.Staff;

/**
 * 
 * 类名：InfoAction
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-11-29 下午02:45:02
 *
 */
@Controller
@RequestMapping("/info")
public class InfoAction extends BaseAction {
	@Autowired
	private IInfoService service;
	
	/**
	 * 跳转到客户信息主页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:read")
	@RequestMapping("load")
	public String load() {
		
		
		return "crm/client/info/load";

	}
	/**
	 * 查询当前登录人员所负责的客户信息
	 */
	@RequiresPermissions("info:read")
	@RequestMapping("query")
	public ModelAndView query( PageParam param,Info o){
		return ajaxJsonEscape(service.selectInfo(param,o));
	}
	/**
	 * 跳转到客户信息添加页面
	 * @return
	 */
	@RequiresPermissions("info:add")
	@RequestMapping("addPage")
	public String addPage() {
		return "crm/client/info/add";

	}
	/**
	 * 添加客户信息
	 * @param p
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("info:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Info o, Errors errors) {
	
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		
		return ajaxDone(service.addInfo(o));

	}
	
	
	
	/**
	 * 跳转到客户信息编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
		map.addAttribute("info",service.selectID( id));
		if(map.get("info")==null){
			return NODATA;
		}
		return "crm/client/info/update";
	}

	/**
	 * 修改客户信息
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("info:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Info o, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateInfo(o));

	}
	
	/**
	 * 删除客户信息
	 */
	@RequiresPermissions("info:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deleteInfo(ids));
	
	}
	/**
	 * 跳转到客户信息查看页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {
		Info o=service.selectID( id);
		map.addAttribute("info",o);
		
		if(map.get("info")==null){
			return NODATA;
		}
		return "crm/client/info/find";
	}
	
	/**
	 * 跳转到客户转移页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:shift")
	@RequestMapping("shiftPage")
	public String shiftPage( String id, ModelMap map) {
		map.addAttribute("info",service.selectID( id));
		if(map.get("info")==null){
			return NODATA;
		}
		return "crm/client/info/shift";
	}
	/**
	 *客户转移
	 * 
	 * @param o
	 * @return
	 */
	@RequiresPermissions("info:shift")
	@RequestMapping("shift")
	public ModelAndView shift(@Valid Info o, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateShift(o.getId(),o.getClStaff()));

	}
	/**
	 * 跳转到客户共享页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:share")
	@RequestMapping("sharePage")
	public String sharePage( String id, ModelMap map) {
		map.addAttribute("info",service.selectID( id));
		if(map.get("info")==null){
			return NODATA;
		}
		return "crm/client/info/share";
	}
	/**
	 *客户共享
	 * 
	 * @param 
	 * @return
	 */
	@RequiresPermissions("info:share")
	public ModelAndView share( String id,String deptid,String userid) {
		return ajaxDone(service.addShare(id,deptid,userid));
	}
	/**
	 * 跳转到高级检索
	 * @return
	 */
	@RequiresPermissions("info:read")
	public String searchTag(String search,ModelMap map) {
		return "crm/client/info/search";

	}
	
	/**
	 * 跳转到共享客户信息主页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:readShare")
	@RequestMapping("loadShare")
	public String loadShare() {
		
		
		return "crm/client/info/loadShare";

	}
	/**
	 * 查询共享的客户信息
	 */
	@RequiresPermissions("info:readShare")
	@RequestMapping("queryShare")
	public ModelAndView queryShare( PageParam param,Info o){
		return ajaxJsonEscape(service.selectShare(param,o));
	}
	
	/**
	 * 跳转到客户共享页面修改页面
	 * 
	 * @return
	 */
	@RequiresPermissions("info:shareUpdate")
	@RequestMapping("shareUpdatePage")
	public String shareUpdatePage( String id, ModelMap map) {
		map.addAllAttributes(service.selectShare( id));
		if(map.get("info")==null){
			return NODATA;
		}
		
		return "crm/client/info/shareUpdate";
	}
	/**
	 *修改客户共享
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("info:shareUpdate")
	@RequestMapping("shareUpdate")
	public ModelAndView shareUpdate( String id,String deptid,String userid) {
		return ajaxDone(service.updateShare(id,deptid,userid));
	}

	/**
	 * 跳转到共享客户的高级检索
	 * @return
	 */
	@RequiresPermissions("info:readShare")
	@RequestMapping("searchTagShare")
	public String searchTagShare(String search,ModelMap map) {
		return "crm/client/info/searchShare";

	}

	/**
	 * 跳转到客户名称用于查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		
		return "crm/client/info/lookup_customer";
	}
	/**
	 * 查询客户名称
	 * @param param
	 * @param o
	 * @return
	 */
	@RequestMapping("lookUp")
	public ModelAndView lookUp(PageParam param,Info o){
		
		return ajaxJsonEscape(service.selectInfo(param,o));
	}

}
