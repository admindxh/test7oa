/**  
 * @Title WdCfTypeAction.java
 * @date 2013-10-28 下午4:32:24
 * @Copyright: 2013 
 */
package com.oa.manager.files.action;

import java.util.Map;

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
import com.oa.manager.files.bean.TypePowersList;
import com.oa.manager.files.bean.WdCfType;
import com.oa.manager.files.service.IWdCfTypeService;

/**
 * 公司文档类型管理
 * @author LiuJincheng
 * @version 1.0
 */
@Controller
@RequestMapping("/companyFiles/type")
public class WdCfTypeAction extends BaseAction{
	@Autowired
	private IWdCfTypeService service; 
	/**
	 * 跳转到加载页面
	 * @return
	 */
	@RequiresPermissions("companyFilesType:read")
	@RequestMapping("load")
	public String load(){
		
		return "files/company/type/load";
	}
	/**
	 * 查询所有文档类型
	 * @return
	 */
	@RequiresPermissions("companyFilesType:read")
	@RequestMapping("allTypes")
	public ModelAndView queryAllTypes(){
		
		return ajaxJsonEscape(service.selectAllTypes());
	}
	/**
	 * 查询当前用户可以访问的文档类型
	 * @return
	 */
	@RequiresPermissions("companyFiles:read")
	@RequestMapping("myTypes")
	public ModelAndView queryMyTypes(){
		
		return ajaxJsonEscape(service.selectMyTypes());
	}
	
	/**
	 * 数据查询
	 * @return
	 */
	@RequiresPermissions("companyFilesType:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WdCfType t){
		
		return ajaxJsonEscape(service.selectWdCfTypes(param, t));
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("companyFilesType:add")
	@RequestMapping("addPage")
	public String addPage(String superId,ModelMap map){
		WdCfType t=service.get(WdCfType.class, superId);
		if(t!=null){
			map.addAttribute("superName",t.getName());
		}
		
		return "files/company/type/add";
	
	}
	/**
	 * 添加分类
	 * @return
	 */
	@RequiresPermissions("companyFilesType:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid WdCfType t,Errors errors,TypePowersList powers){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.addWdCfType(t,powers));
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequiresPermissions("companyFilesType:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		Map m=service.selectOneType(id);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
		return "files/company/type/update";
	
	}
	
	/**
	 * 删除一个权限
	 * @param id
	 * @return
	 */
	@RequiresPermissions("companyFilesType:update")
	@RequestMapping("delPower")
	public ModelAndView deletePower(String id){
		
		return ajaxDone(service.deletePower(id));
	}
	/**
	 * 修改分类
	 * @return
	 */
	@RequiresPermissions("companyFilesType:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid WdCfType t,Errors errors,TypePowersList powers){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateWdCfType(t, powers));
	}
	/**
	 * 批量删除类型
	 * @param id
	 * @return
	 */
	@RequiresPermissions("companyFilesType:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteWdCfTypes(ids));
	}
	/**
	 * 文档分类，查找带回
	 * @param type 1: 当前用户能够访问的分类，2:所有的分类
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		
		return "files/company/type/lookup";
		
	}
	
	
	
}
