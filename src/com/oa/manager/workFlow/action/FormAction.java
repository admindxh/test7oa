/**  
 * @Project: jxoa
 * @Title: FormAction.java
 * @Package com.oa.manager.workFlow.action
 * @date 2013-7-6 上午9:45:39
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.action;

import java.util.HashMap;
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
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.workFlow.bean.WfForm;
import com.oa.manager.workFlow.service.IFormService;

/**
 * 
 * 类名：FormAction
 * 功能：表单设计
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-6 上午9:45:39
 *
 */
@Controller
@RequestMapping("/form")
public class FormAction extends BaseAction{

	@Autowired
	private IFormService service; 
	
	
	/**
	 * 表单管理
	 * @return
	 */
	@RequiresPermissions("form:read")
	@RequestMapping("load")
	public String load(){
		
		return "workflow/form/load";
	
	}
	/**
	 * 表单查询
	 * @param param
	 * @param wf
	 * @param map
	 * @return
	 */
	@RequiresPermissions("form:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WfForm wf,ModelMap map){
		
		return ajaxJsonEscape(service.selectWfForm(param, wf));
	
	}
	/**
	 * 跳转到测试页面
	 * @return
	 */
	@RequestMapping("testPage")
	public String testPage(){
		
		return "workflow/form/test";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequiresPermissions("form:add")
	@RequestMapping("addPage")
	public String addPage(){
		
		return "workflow/form/add";
	}
	/**
	 * 添加表单设计
	 * @param wf
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("form:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid WfForm wf,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		wf.setUpdateUserId(ServletUtil.getMember().getId());
		wf.setUpdateTime(DateUtil.currentTimestamp());
		return ajaxDone(service.addWfForm(wf));
	
	}
	
	/**
	 * 跳转到表单修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("form:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		WfForm wf=service.get(WfForm.class, id);
		if(wf==null){
			return NODATA;
		}
		map.addAttribute("f",wf);
		
		return "workflow/form/update";
	}
	/**
	 * 更新表单
	 * @param wf
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("form:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid WfForm wf,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		wf.setUpdateUserId(ServletUtil.getMember().getId());
		wf.setUpdateTime(DateUtil.currentTimestamp());
		return ajaxDone(service.updateWfForm(wf));
		
	}
	/**
	 * 删除表单设计
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("form:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteWfForm(ids));
	
	}
	/**
	 * 预览表单
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		WfForm wf=service.get(WfForm.class, id);
		if(wf==null){
			return NODATA;
		}
		map.addAttribute("f",wf);
		
		return "workflow/form/show";
	}
	
	
	/**
	 * 跳转到 表单查询带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage_easyui(){
		
		return "workflow/form/lookup";
		
	}
	/**
	 * 跟id查询form名称
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getFormNameById")
	public ModelAndView getFormNameById(String id){
		
		WfForm wf=service.get(WfForm.class, id);
		Map map=new HashMap();
		if(wf==null){
			map.put(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL );
			map.put(MsgConfig.MESSAGE,"id:"+id+"表单不存在");
		}else{
			map.put(MsgConfig.STATUSCODE,MsgConfig.CODE_SUCCESS );
			map.put("name",wf.getFormName());
		}
		return ajaxJsonEscape(map);
	
	}
	
	
}
