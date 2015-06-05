/**  
 * @Project: jxoa
 * @Title: MeetingAction.java
 * @Package com.oa.manager.common_platform.action
 * @date 2013-5-9 上午10:14:29
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.action;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Meeting;
import com.oa.manager.common_platform.service.IMeetingService;

/**
 * 
 * 类名：MeetingAction 
 * 功能：公共平台-会议管理 
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0 日期：2013-5-9
 * 上午10:14:29
 * 
 */
@Controller
@RequestMapping("/meeting")
public class MeetingAction extends BaseAction {

	@Autowired
	private IMeetingService service;

	/**
	 * 查询会议
	 * 
	 * @return
	 */
	@RequiresPermissions("meeting:read")
	@RequestMapping("load")
	public String load(ModelMap map) {
		map.addAttribute("list", service.selectRoom());
		return "common_platform/meeting/load";

	}
	@RequiresPermissions("meeting:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,Meeting lv){
		return ajaxJsonEscape(service.selectMeeting(param, lv));
		
	}
	/**
	 * 查询个人会议
	 * 
	 * @return
	 */
	@RequiresPermissions("meeting:readTome")
	@RequestMapping("loadtome")
	public String loadTome( Meeting lv, ModelMap map) {
		map.addAttribute("list", service.selectRoom());
		return "common_platform/meeting/loadtome";

	}
	@RequiresPermissions("meeting:readTome")
	@RequestMapping("queryTome")
	public ModelAndView queryTome(PageParam param,Meeting lv){
		return ajaxJsonEscape(service.selectMeetingTome(param, lv));
		
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("meeting:add")
	@RequestMapping("addPage")
	public String addPage( ModelMap map) {
		map.addAttribute("list", service.selectRoom());
		return "common_platform/meeting/add";

	}

	/**
	 * 添加会议
	 * 
	 * @return
	 */
	@RequiresPermissions("meeting:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Meeting m, Errors errors) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		m.setMPublished(formatter.format(date));
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		Member me = ServletUtil.getMember();
		m.setMUid(me.getId());

		return ajaxDone(service.addMeeting(m));

	}

	/**
	 * 跳转到会议编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("meeting:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
		map.addAttribute("list", service.selectRoom());
		map.addAllAttributes(service.selectMapping( id));
		if(map.get("meeting")==null){
			return NODATA;
		}
		return "common_platform/meeting/update";
	}

	/**
	 * 修改会议
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("meeting:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Meeting m, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}

		return ajaxDone(service.updateMeeting(m));

	}
	
	/**
	 * 删除会议
	 */
	@RequiresPermissions("meeting:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deleteMeeting(ids));
	
	}
	/**
	 * 跳转到会议查看页面
	 * 
	 * @return
	 */
	@RequiresPermissions("meeting:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {
		map.addAllAttributes(service.selectMapping( id));
		if(map.get("meeting")==null){
			return NODATA;
		}
		return "common_platform/meeting/find";
	}

	
}
