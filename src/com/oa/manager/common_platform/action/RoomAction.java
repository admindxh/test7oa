/**  
 * @Project: jxoa
 * @Title: RoomAction.java
 * @Package com.oa.manager.common_platform.action
 * @date 2013-5-9 下午03:36:32
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.Room;
import com.oa.manager.common_platform.service.IRoomService;

/**
 * 
 * 类名：RoomAction 功能：会议室 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2013-5-9 下午03:36:32
 * 
 */

@Controller
@RequestMapping("/room")
public class RoomAction extends BaseAction {

	@Resource(name = "roomServiceImpl")
	private IRoomService service;

	/**
	 * 查询会议室
	 * 
	 * @return
	 */
	@RequiresPermissions("room:read")
	@RequestMapping("load")
	public String load() {

//		map.addAttribute("values", service.selectRoom(param, lv));
//		param.setAllCount(service.selectRoomCount(lv));
//		map.addAttribute("fromParam", param);
		return "common_platform/meeting/room/set";

	}
	@RequiresPermissions("room:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,Room r){
		return ajaxJsonEscape(service.selectRoom(param, r));
		
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequiresPermissions("room:add")
	@RequestMapping("addPage")
	public String addPage() {

		return "common_platform/meeting/room/setAdd";

	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequiresPermissions("room:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Room lv, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.addRoom(lv));

	}
	
	/**
	 * 跳转到会议室编辑页面
	 * 
	 * @return
	 */
	@RequiresPermissions("room:update")
	@RequestMapping("updatePage")
	public String updatePage(String id, ModelMap map) {

		map.addAttribute("room", service.get(Room.class, id));
	
		return "common_platform/meeting/room/setUpdate";
	}
	/**
	 * 修改会议室
	 * 
	 * @param lv
	 * @return
	 */
	@RequiresPermissions("room:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Room r, Errors errors, HttpSession session) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.updateRoom(r));

	}
	/**
	 * 删除会议室
	 */
	@RequiresPermissions("room:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		return ajaxDone(service.deleteRoom(ids));
	
	}
	/**
	 * 会议室 选择 下拉列表查询 
	 * @return
	 */
	@RequestMapping("getSelectOptions")
	public ModelAndView getSelectList(){
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		
		map.put("list", service.selectAllRoom());
		
		return ajaxJsonEscape(map);
	}
}
