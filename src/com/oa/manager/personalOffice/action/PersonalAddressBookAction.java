/**  
 * @Project: jxoa
 * @Title: PersonalAction.java
 * @Package com.oa.manager.personalOffice.addressBook.Personal.action
 * @date 2013-5-21 上午09:38:12
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
import com.oa.manager.personalOffice.bean.PersonalAddressBook;
import com.oa.manager.personalOffice.service.IPersonalAddressBookService;

/**
 * 
 * 类名：PersonalAction
 * 功能：个人办公-通讯录-个人通讯录
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-21 上午09:38:12
 *
 */

@Controller
@RequestMapping("/personal")
public class PersonalAddressBookAction extends BaseAction {
	@Autowired
	private IPersonalAddressBookService service;

	/**
	 * 查询个人通讯录
	 * 
	 * @return
	 */
	@RequiresPermissions("personal:read")
	@RequestMapping("load")
	public String load() {
		
		
		return "personalOffice/addressBook/personal/load";

	}
	/**
	 * 根据分组查询
	 */
	@RequiresPermissions("public:read")
	@RequestMapping("queryGroup")
	public String queryGroup( ModelMap map){
		
		return "personalOffice/addressBook/personal/query";
	}
	
	@RequestMapping("loadGroup")
	public ModelAndView loadGroup(){
		
		
		return ajaxJsonEscape(service.selectGroup());
		
	}
	@RequiresPermissions("personal:read")
	@RequestMapping("queryGr")
	public ModelAndView queryGrp(PageParam param,PersonalAddressBook p){
		return ajaxJsonEscape(service.selectPersonal(param, p));
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("personal:add")
	@RequestMapping("addPage")
	public String addPage( ModelMap map) {
		map.addAttribute("group",service.selectGroup());
		return "personalOffice/addressBook/personal/add";

	}
	

	/**
	 * 添加个人通讯录
	 * 
	 * @return
	 */
	@RequiresPermissions("personal:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid PersonalAddressBook p, Errors errors) {
		
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.addPersonal(p));

	}

	/**
	 * 跳转到个人通讯录编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("personal:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
		map.addAttribute("group",service.selectGroup());
		map.addAttribute("per",service.selectPersonal( id));
		if(map.get("per")==null){
			return NODATA;
		}
		return "personalOffice/addressBook/personal/update";
	}

	/**
	 * 修改个人通讯录
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("personal:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid PersonalAddressBook m, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updatePersonal(m));

	}
	
	/**
	 * 删除个人通讯录
	 */
	@RequiresPermissions("personal:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deletePersonal(ids));
	
	}
	/**
	 * 跳转到个人通讯录查看页面
	 * 
	 * @return
	 */
	@RequiresPermissions("personal:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {
		PersonalAddressBook p=service.selectPersonal( id);
		p.setPeGrouping(service.get(GroupAddressBook.class, p.getPeGrouping()).getGrName());
		map.addAttribute("per",p);
		
		if(map.get("per")==null){
			return NODATA;
		}
		return "personalOffice/addressBook/personal/find";
	}

}
