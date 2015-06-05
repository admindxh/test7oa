/**  
 * @Project: jxoa
 * @Title: ListValuesAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-4-15 下午2:32:55
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

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
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.service.IListValuesService;

/**
 * 
 * 类名：ListValuesAction
 * 功能：系统管理--字典值管理
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-15 下午2:32:55
 *
 */
@Controller
@RequestMapping("/list")
public class ListValuesAction extends BaseAction{
	
	@Autowired
	private IListValuesService service; 
	
	/**
	 * 字典值条件分页查询
	 * @param param
	 * @param lv
	 * @param map
	 * @return
	 */
	@RequiresPermissions("list:read")
	@RequestMapping("load")
	public String load(){
		
		return "system/list_values/load";
	
	}
	/**
	 * 字典值条件分页查询
	 * @param param
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("list:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,ListValues lv){
		
		return ajaxJsonEscape(service.selectListValues(param, lv));
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("list:add")
	@RequestMapping("addPage")
	public String addPage(){
		
		return "system/list_values/add";
	
	}
	/**
	 * 添加字典值
	 * @param lv
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("list:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid ListValues lv,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.addListValues(lv));
		
	}
	/**
	 * 跳转到字典值编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("list:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		ListValues lv=service.get(ListValues.class, id);
		if(lv==null){
			return NODATA;
		}
		map.addAttribute("lv",lv);
		
		return "system/list_values/update";
	}
	/**
	 * 修改字典值
	 * @param lv
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("list:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid ListValues lv,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateListValues(lv));
		
	}
	/**
	 * 删除字典值
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("list:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteListValues(ids));
	
	}
	
	
	/**
	 * select列表显示字典值，根据字典值类型查询
	 * @param type	字典值类型
	 * @return
	 */
	@RequestMapping("showSelectList")
	public ModelAndView showSelectList(Integer type){
		
		return ajaxJsonEscape(service.selectListByType(type));
	}
	
	/**
	 * select列表显示字典值，ajax 请求获取字典类型，从缓存中获取
	 * @param type	字典值类型
	 * @return
	 */
	@RequestMapping("getSelectOptions")
	public ModelAndView getSelectList(Integer type){
		
		return ajaxJsonEscape(MyCache.getInstance().getListValues(type));
	}
	
	
}
