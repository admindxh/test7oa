/**
 * 
 */
package com.oa.manager.personnel.action;

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
import com.oa.manager.personnel.bean.Job;
import com.oa.manager.personnel.service.IJobService;

/**
 * 
 * 类名：JobAction
 * 功能：人事管理--招聘管理
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年6月7日 10:06:46
 *
 */
@Controller
@RequestMapping("/job")
public class JobAction  extends BaseAction{
	
	@Autowired
	private IJobService service;
	
	
	/**
	 * 加载
	 * @return
	 */
	@RequiresPermissions("job:read")
	@RequestMapping("load")
	public String load() {
		
		return "personnel/job/query";
	}
	@RequiresPermissions("job:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param, Job j) {
		return ajaxJsonEscape(service.selectJob(param,j));

	}
	
	
	
	/**
	 * 跳转到招聘添加页面
	 * @return
	 */
	@RequiresPermissions("job:add")
	@RequestMapping("addPage")
	public String addPage() {
		return "personnel/job/add";

	}
	/**
	 * 添加招聘信息
	 * @param p
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("job:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Job j, Errors errors) {
	
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		
		return ajaxDone(service.add (j));

	}
	/**
	 * 跳转到查看页面
	 * @param param
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("job:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {

		map.addAttribute("job", service.selectID(id));
		if(map.get("job")==null){
			return NODATA;
		}
		return "personnel/job/find";
	}
	/**
	 * 跳转到修改页面
	 * @param param
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("job:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
		
		map.addAttribute("job", service.selectID(id));
		if(map.get("job")==null){
			return NODATA;
		}
		return "personnel/job/update";
	}
	/**
	 * 修改招聘信息
	 * @param s
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("job:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Job p, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.update(p));
	}
	
	/**
	 * 删除招聘
	 */
	@RequiresPermissions("job:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.delete(ids));
	
	}
	
	
	
}
