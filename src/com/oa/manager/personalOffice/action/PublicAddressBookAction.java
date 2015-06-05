/**  
 * @Project: jxoa
 * @Title: PublicAction.java
 * @Package com.oa.manager.PublicOffice.addressBook.Public.action
 * @date 2013-5-21 上午09:38:12
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ExcelUtils;
import com.oa.manager.personalOffice.bean.PublicAddressBook;
import com.oa.manager.personalOffice.service.IPublicAddressBookService;
import com.oa.manager.system.service.IListValuesService;

/**
 * 
 * 类名：PublicAddressBookAction
 * 功能：个人办公-通讯录-公共通讯录
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-23 上午09:38:12
 *
 */

@Controller
@RequestMapping("/public")
public class PublicAddressBookAction extends BaseAction {
	@Autowired
	private IPublicAddressBookService service;
	@Autowired
	private IListValuesService LVservice; 
	/**
	 * 查询公共通讯录
	 * 
	 * @return
	 */
	@RequiresPermissions("public:read")
	@RequestMapping("load")
	public String load( PublicAddressBook p, ModelMap map) {
		return "personalOffice/addressBook/public/load";

	}
	
	@RequestMapping("loadGroup")
	public ModelAndView loadGroup(){
		
		
		return ajaxJsonEscape(service.selectGroup());
		
	}
	/**
	 * 根据分组查询
	 */
	@RequiresPermissions("public:read")
	@RequestMapping("queryGroup")
	public String queryGroup(){
//		p.setPuGrouping(peGrouping);
//		map.addAttribute("group",service.selectGroup());
//		map.addAttribute("gro",peGrouping);
//		param.setAllCount(service.selectPublicCount(p));
//		map.addAttribute("values",service.selectPublic(param, p));
//		map.addAttribute("fromParam", param);
		return "personalOffice/addressBook/public/query";
	}
	@RequiresPermissions("public:read")
	@RequestMapping("queryGrp")
	public ModelAndView queryGrp(PageParam param,PublicAddressBook p){
		return ajaxJsonEscape(service.selectPublic(param, p));
	}
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("public:add")
	@RequestMapping("addPage")
	public String addPage( ModelMap map) {
		map.addAttribute("group",service.selectGroup());
		return "personalOffice/addressBook/public/add";

	}
	

	/**
	 * 添加公共通讯录
	 * 
	 * @return
	 */
	@RequiresPermissions("public:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid PublicAddressBook p, Errors errors) {
		
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.addPublic(p));

	}

	/**
	 * 跳转到公共通讯录编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("public:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
//		map.addAttribute("list", service.selectRoom());
		map.addAttribute("group",service.selectGroup());
		map.addAttribute("per",service.selectPublic( id));
		if(map.get("per")==null){
			return NODATA;
		}
		return "personalOffice/addressBook/public/update";
	}

	/**
	 * 修改公共通讯录
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("public:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid PublicAddressBook m, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updatePublic(m));
	}
	
	/**
	 * 删除公共通讯录
	 */
	@RequiresPermissions("public:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deletePublic(ids));
	
	}
	/**
	 * 跳转到公共通讯录查看页面
	 * 
	 * @return
	 */
	@RequiresPermissions("public:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {
		map.addAttribute("per",service.selectPublic( id));
		map.addAttribute("group",service.selectGroup());
		if(map.get("per")==null){
			return NODATA;
		}
		return "personalOffice/addressBook/public/find";
	}
	
	/**
	 * 跳转到excel导入页面
	 * 
	 * @return 
	 */
	@RequiresPermissions("public:excel")
	@RequestMapping("excelPage")
	public String excelPage( ModelMap map) {
		map.addAttribute("group",service.selectGroup());
		return "personalOffice/addressBook/public/excel";

	}
	/***
	 * excel导入
	 * @return
	 */
	@RequiresPermissions("public:excel")
	@RequestMapping("excel")
	public ModelAndView  excel(@Valid String group,@RequestParam MultipartFile file ){
		 List<PublicAddressBook> list=new ArrayList<PublicAddressBook>();
		 if(!file.isEmpty()){
				try {
					list = ExcelUtils.staffExcel(file.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		

			return ajaxDone(service.addExcelPublic(list,group));
	}
	
}
  