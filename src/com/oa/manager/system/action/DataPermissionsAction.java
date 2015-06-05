/**  
 * @Title: DataPermissionsAction.java
 * @date 2013-10-18 下午4:05:33
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
import com.oa.manager.system.bean.SyDataPermissions;
import com.oa.manager.system.service.IDataPermissionsService;

/**
 * 数据权限管理
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
@Controller
@RequestMapping("/dataPer")
public class DataPermissionsAction extends BaseAction{

	@Autowired
	private IDataPermissionsService service; 
	
	/**
	 * 管理页面
	 * @return
	 */
	@RequiresPermissions("dataPer:read")
	@RequestMapping("load")
	public String load(){
		
		return "system/data_permissions/load";
	}
	/**
	 * 修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("dataPer:read")
	@RequestMapping("updatePage")
	public String updatePage(SyDataPermissions per,ModelMap map){
		
		map.addAttribute("per",service.updateSelectOne(per));
		
		return "system/data_permissions/update/"+per.getType();
	}
	/**
	 * 修改
	 * @param per
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("dataPer:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid SyDataPermissions per,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateSyDataPermissions(per));
		
	}
	
	
	
	
}
