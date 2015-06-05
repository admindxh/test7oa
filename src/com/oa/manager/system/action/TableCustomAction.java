/**  
 * @Project: jxoa
 * @Title: TableCustomAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-6-17 上午11:56:07
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyTableCustom;
import com.oa.manager.system.service.ITableCustomService;

/**
 * 
 * 类名：TableCustomAction
 * 功能：导入导出自定义设置管理
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-17 上午11:56:07
 *
 */
@Controller
@RequestMapping("/tableCustom")
public class TableCustomAction extends BaseAction{
	@Autowired
	private ITableCustomService service; 
	
	
	/**
	 * 开发模式--自定义设置
	 * @return
	 */
	@RequestMapping("dev/load")
	public String devLoad(){
		return "system/table_custom/dev/load";
	}
	/**
	 * 开发模式--自定义设置查询 管理
	 * @param param
	 * @param t
	 * @return
	 */
	@RequestMapping("dev/query")
	public ModelAndView query(PageParam param,SyTableCustom t){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		
		return ajaxJsonEscape(service.selectTableCustoms(param, t));
	}
	
	/**
	 * 开发模式--跳转到添加页面
	 * @return
	 */
	@RequestMapping("dev/addPage")
	public String addPage(){
		
		return "system/table_custom/dev/add";
	
	}
	/**
	 * 开发模式--添加自定义设置
	 * @param tc
	 * @param errors
	 * @return
	 */
	@RequestMapping("dev/add")
	public ModelAndView add(@Valid SyTableCustom tc,Errors errors){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.addTableCustom(tc));
		
	}
	/**
	 * 开发模式--跳转到自定义设置修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("dev/updatePage")
	public String devUpdatePage(String id,ModelMap map){
		if(!ServletUtil.isDeveloper()){
			return NOPOWER;
		}
		SyTableCustom tc=service.get(SyTableCustom.class, id);
		if(tc==null){
			return NODATA;
		}
		map.addAttribute("tc",tc);
		
		return "system/table_custom/dev/update";
	}
	/**
	 * 开发模式--修改
	 * @param tc
	 * @param errors
	 * @return
	 */
	@RequestMapping("dev/update")
	public ModelAndView devUpdate(@Valid SyTableCustom tc,Errors errors){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateDevTableCustom(tc));
		
	}
	/**
	 * 开发模式--删除自定义设置
	 * @param ids
	 * @return
	 */
	@RequestMapping("dev/del")
	public ModelAndView delete(String[] ids){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		return ajaxDone(service.deleteTableCustom(ids));
	
	}
	/**
	 * 用户模式--自定义设置查询，根据类型
	 * @param tbType
	 * @param map
	 * @return
	 */
	@RequestMapping("load")
	public String load(short type,ModelMap map){
		
		map.addAttribute("ts",service.selectTableCustom(type));
		
		return "system/table_custom/load";
	}
	/**
	 * 用户模式--跳转到自定义设置修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("tableCustom:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		SyTableCustom tc=service.get(SyTableCustom.class, id);
		if(tc==null){
			return NODATA;
		}
		map.addAttribute("tc",tc);
		
		return "system/table_custom/update";
	}
	/**
	 *用户模式--修改
	 * @param tc
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("tableCustom:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid SyTableCustom tc,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors,"fieldType","tbType");
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateTableCustom(tc));
		
	}
	
}
