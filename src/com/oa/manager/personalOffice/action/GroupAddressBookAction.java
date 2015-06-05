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
import com.oa.manager.personalOffice.service.IGroupAddressBookService;

/**
 * 
 * 类名：GroupAddressBook
 * 功能：个人办公-通讯录-个人通讯录-分组
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-24 下午03:57:57
 *
 */
@Controller
@RequestMapping("/group")
public class GroupAddressBookAction extends BaseAction {
	@Autowired
	private IGroupAddressBookService service;
	/**
	 * 查询个人通讯录分组
	 * 
	 * @return
	 */
	@RequiresPermissions("group:read")
	@RequestMapping("load")
	public String load() {
		
		return "personalOffice/addressBook/personal/set/load";

	}
	@RequiresPermissions("group:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param, GroupAddressBook p) {
	
		return ajaxJsonEscape(service.selectGroup(param, p));

	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("group:add")
	@RequestMapping("addPage")
	public String addPage(ModelMap map) {
		return "personalOffice/addressBook/personal/set/add";

	}
	

	/**
	 * 添加个人通讯录分组
	 * 
	 * @return
	 */
	@RequiresPermissions("group:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid GroupAddressBook p, Errors errors) {
		
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.addGroup(p));

	}

	/**
	 * 跳转到个人通讯录分组编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("group:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
//		map.addAttribute("list", service.selectRoom());
		GroupAddressBook gro=service.selectGroup( id);
		if(gro==null){
			return NODATA;
		}
		map.addAttribute("gro",gro);
		
		return "personalOffice/addressBook/personal/set/update";
	}

	/**
	 * 修改个人通讯录分组
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("group:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid GroupAddressBook m, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateGroup(m));

	}
	
	/**
	 * 删除个人通讯录分组
	 */
	@RequiresPermissions("group:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deleteGroup(ids));
	
	}
}
