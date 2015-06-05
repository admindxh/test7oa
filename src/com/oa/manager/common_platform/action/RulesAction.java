/**  
 * @Project: jxoa
 * @Title: RulesAction.java
 * @Package com.oa.manager.common_platform.action
 * @date 2013-6-3 上午10:18:11
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
import com.oa.manager.common_platform.bean.RmRulesmanager;
import com.oa.manager.common_platform.service.IRulesService;

/**
 * 
 * 类名：RulesAction 功能： 
 * 详细： 法规
 * 作者：QinXiaohua 
 * 版本：1.0 
 * 日期：2013-6-3 上午10:18:11
 * 
 */
@Controller
@RequestMapping("regulation")
public class RulesAction extends BaseAction {
	@Autowired
	private IRulesService irulesservice;

	/**
	 * 查询法规
	 * 
	 * @return
	 */
	@RequiresPermissions("regulation:read")
	@RequestMapping("loadrule")
	public String loadrule() {

		return "common_platform/informationregulations/rules/load";
	}
	@RequiresPermissions("regulation:read")
	@RequestMapping("loadallrule")
	public ModelAndView loadallrule(PageParam param,RmRulesmanager rm){
		
		return ajaxJsonEscape(irulesservice.load(rm, param));
	}
	/**
	 * 跳转到增加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("regulation:add")
	@RequestMapping("addPage")
	public String addPage() {
		return "common_platform/informationregulations/rules/add";
	}

	/**
	 * 添加法规
	 * 
	 * @param rm
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("regulation:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid RmRulesmanager rm, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		rm.setCreatedate(DateUtil.currentTimestamp());
		rm.setModydate(DateUtil.currentTimestamp());
		Member me = ServletUtil.getMember();
		rm.setAuthor(me.getId());
		return ajaxDone(irulesservice.save(rm));
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("regulation:update")
	@RequestMapping("updatePage")
	public String updatePage(String id, ModelMap map) {
		RmRulesmanager rm= irulesservice.get(RmRulesmanager.class, id);
		if(rm==null){
			return NODATA;
		}
		map.addAttribute("update", rm);
		return "common_platform/informationregulations/rules/update";
	}
	@RequiresPermissions("regulation:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid RmRulesmanager rm, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		Member me = ServletUtil.getMember();
		rm.setModyauthor(me.getId());
		rm.setModydate(DateUtil.currentTimestamp());
		return ajaxDone(irulesservice.updaterule(rm));

	}

	/**
	 * 批量删除法规
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("regulation:delete")
	@RequestMapping("delete")
	public ModelAndView delete(String[] ids) {
		return ajaxDone(irulesservice.deletes(ids));
	}

	/**
	 * 查看法规内容
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("lookrule")
	public String lookrule(String id, ModelMap map) {
		RmRulesmanager rm= irulesservice.get(RmRulesmanager.class, id);
		if(rm==null){
			return NODATA;
		}
		map.addAttribute("rules", rm);
		return "common_platform/informationregulations/rules/lookrule";
	}
/**
 * 查看只读法规页面
 * @param rm
 * @param param
 * @param map
 * @return
 */
	@RequiresPermissions("regulation:read")
	@RequestMapping("rules")
	public String rules() {
		
		return "common_platform/informationregulations/rules/rules";
	}

	
}
