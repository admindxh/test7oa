/**  
 * @Project: jxoa
 * @Title: GroupAddressBook.java
 * @Package com.oa.manager.GroupOffice.action
 * @date 2013-5-24 下午03:57:57
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.action;

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
import com.oa.manager.personalOffice.bean.GroupAddressBook;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personalOffice.service.IPublicGroupService;

/**
 * 
 * 类名：GroupAddressBook
 * 功能：公共办公-通讯录-公共通讯录-分组
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-8-13 下午03:57:57
 *
 */
@Controller
@RequestMapping("/publicGroup")
public class PublicGroupAction extends BaseAction {
	@Autowired
	private IPublicGroupService service;
	/**
	 * 查询公共通讯录分组
	 * 
	 * @return
	 */
	@RequiresPermissions("publicGroup:read")
	@RequestMapping("load")
	public String load() {
		return "personalOffice/addressBook/public/group/load";

	}
	@RequiresPermissions("publicGroup:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param, PublicGroup p) {
	
		return ajaxJsonEscape(service.selectGroup(param, p));

	}
	
	
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("publicGroup:add")
	@RequestMapping("addPage")
	public String addPage( ModelMap map) {
		return "personalOffice/addressBook/public/group/add";

	}
	

	/**
	 * 添加公共通讯录分组
	 * 
	 * @return
	 */
	@RequiresPermissions("publicGroup:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid PublicGroup p, Errors errors) {
		
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.addGroup(p));

	}

	/**
	 * 跳转到公共通讯录分组编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("publicGroup:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
//		map.addAttribute("list", service.selectRoom());
		PublicGroup gro=service.selectGroup( id);
		if(gro==null){
			return NODATA;
		}
		map.addAttribute("gro",gro);
		
		return "personalOffice/addressBook/public/group/update";
	}

	/**
	 * 修改公共通讯录分组
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("publicGroup:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid PublicGroup p, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateGroup(p));

	}
	
	/**
	 * 删除公共通讯录分组
	 */
	@RequiresPermissions("publicGroup:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deleteGroup(ids));
	
	}
}
