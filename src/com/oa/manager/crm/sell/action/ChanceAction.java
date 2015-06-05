package com.oa.manager.crm.sell.action;

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
import com.oa.manager.crm.client.bean.CrmClientLinkman;
import com.oa.manager.crm.sell.bean.Chance;
import com.oa.manager.crm.sell.service.IChanceService;

/**
 * 
 * 类名：ChanceAction
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年12月11日 08:56:23
 *
 */
@Controller
@RequestMapping("/chance")
public class ChanceAction extends BaseAction {
	@Autowired
	private IChanceService service;
	
	/**
	 * 跳转到销售机会主页面
	 * 
	 * @return
	 */
	@RequiresPermissions("chance:read")
	@RequestMapping("load")
	public String load() {
		
		
		return "crm/sell/chance/load";

	}
	/**
	 * 查询当前登录人员所负责的销售机会
	 */
	@RequiresPermissions("chance:read")
	@RequestMapping("query")
	public ModelAndView query( PageParam param,Chance c){
		return ajaxJsonEscape(service.selectChance(param,c));
	}
	/**
	 * 跳转到销售机会添加页面
	 * @return
	 */
	@RequiresPermissions("chance:add")
	@RequestMapping("addPage")
	public String addPage() {
		return "crm/sell/chance/add";

	}
	/**
	 * 添加销售机会
	 * @param p
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("chance:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Chance c, Errors errors) {
	
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		
		return ajaxDone(service.addChance(c));

	}
	
	
	
	/**
	 * 跳转到销售机会编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("chance:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
		map.addAttribute("chance",service.selectID(id));
		if(map.get("chance")==null){
			return NODATA;
		}
		return "crm/sell/chance/update";
	}

	/**
	 * 修改销售机会
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("chance:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Chance c, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateChance(c));

	}
	
	/**
	 * 删除销售机会
	 */
	@RequiresPermissions("chance:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deleteChance(ids));
	
	}
	/**
	 * 跳转到销售机会查看页面
	 * 
	 * @return
	 */
	@RequiresPermissions("chance:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {
		Chance c=service.selectID( id);
		map.addAttribute("chance",c);
		
		if(map.get("chance")==null){
			return NODATA;
		}
		return "crm/sell/chance/find";
	}
	
	/**
	 * 跳转到销售机会分配
	 * 
	 * @return
	 */
	@RequiresPermissions("chance:allocation")
	@RequestMapping("allocationPage")
	public String allocationPage( String id, ModelMap map) {
		map.addAttribute("chance",service.selectID( id));
		if(map.get("chance")==null){
			return NODATA;
		}
		return "crm/sell/chance/allocationUpdate";
	}
	/**
	 *销售机会分配
	 * 
	 * @param o
	 * @return
	 */
	@RequiresPermissions("chance:allocation")
	@RequestMapping("allocation")
	public ModelAndView shift(@Valid Chance c, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateAllocation(c.getId(),c.getScStaff(),c.getScBranch(),c.getScPicture()));

	}
	
	/**
	 * 跳转到高级检索
	 * @return
	 */
	@RequiresPermissions("chance:read")
	public String searchTag(String search,ModelMap map) {
		return "crm/sell/chance/search";

	}
	
	/**
	 * 跳转到联系人查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		return "crm/sell/chance/lookup_customer";
	}
	/**
	 * 查询客户名称
	 * @param param
	 * @param o
	 * @return
	 */
	@RequestMapping("lookUp")
	public ModelAndView lookUp(Chance c,PageParam param){
		
		return ajaxJsonEscape(service.lookUpChance(param,c));
	}
	

}
