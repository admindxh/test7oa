/**  
 * @Project: jxoa
 * @Title: rulesregulations.java
 * @Package com.oa.manager.common_platform.action
 * @date 2013-5-31 上午11:34:29
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.action;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.RgRulesregulations;
import com.oa.manager.common_platform.service.IRulesregulationsService;

/**
 * 
 * 类名：rulesregulations
 * 功能：规章制度
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-5-31 上午11:34:29
 *
 */
@Controller
@RequestMapping("rules")
public class RulesregulationsAction extends BaseAction {
	@Autowired
	private IRulesregulationsService  irulesregulationservice;
	/**
	 * 跳转到规章制度页面
	 * @return
	 */
	@RequiresPermissions("rules:read")
	@RequestMapping("loadrules")
	public String loadrules(){

		return "common_platform/informationregulations/rulesregulations/load";
	}
	@RequiresPermissions("rules:read")
	@RequestMapping("loadallrules")
	public ModelAndView loadallrules(PageParam param,RgRulesregulations rg){
		
		return ajaxJsonEscape(irulesregulationservice.selectrules(rg, param));
	}
	
	
	/**
	 * 跳转到增加页面
	 * @return
	 */
	@RequiresPermissions("rules:add")
	@RequestMapping("addPage")
	public String addPage(){
		return "common_platform/informationregulations/rulesregulations/add";
	}
	/**
	 * 增加规章制度
	 * @param rg
	 * @return
	 */
	@RequiresPermissions("rules:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid RgRulesregulations rg, Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		rg.setCreatedate(DateUtil.currentTimestamp());
		rg.setModifiedate(DateUtil.currentTimestamp());
		Member me=ServletUtil.getMember();
		rg.setPromulgator(me.getId());
		return ajaxDone(irulesregulationservice.save(rg));
	}
	/**
	 * 跳转到规章修改页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("rules:update")
	@RequestMapping("updatePage")
	public String  updatepage(String id,ModelMap map){
		RgRulesregulations rl=irulesregulationservice.get(RgRulesregulations.class, id);
		if(rl==null){
			
			return NODATA;
		}
		 map.addAttribute("rules",rl);
		 return "common_platform/informationregulations/rulesregulations/update";
	}
	/**
	 * 修改规章制度
	 * @param rg
	 * @return
	 */
	@RequiresPermissions("rules:update")
	@RequestMapping("update")
	public ModelAndView update(RgRulesregulations rg){
		rg.setModifiedate(DateUtil.currentTimestamp());
		Member me=ServletUtil.getMember();
		rg.setModifyByname(me.getId());
		return ajaxDone(irulesregulationservice.updaterg(rg));
	}
	/**
	 * 批量删除 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("rules:delete")
	@RequestMapping("delete")
	public ModelAndView delete(String[] ids){
		return ajaxDone(irulesregulationservice.delete(ids));
	}
	/**
	 * 跳到查看内容页面
	 * @param id
	 * @return
	 */
	@RequestMapping("lookrules")
	public String lookrules(String id,ModelMap map){
		RgRulesregulations rl=irulesregulationservice.get(RgRulesregulations.class, id);
		if(rl==null){
			return NODATA;
		}
		map.addAttribute("rules", rl);
		return "common_platform/informationregulations/rulesregulations/lookrules";
	}
	/**
	 * 不能进行增删改查的规章制度页面
	 * @param rg
	 * @param map
	 * @param param
	 * @return
	 */
	@RequiresPermissions("rules:read")
	@RequestMapping("lookrule")
	public String lookrule(){
	
		return "common_platform/informationregulations/rulesregulations/lookrule";
	}
	
}
