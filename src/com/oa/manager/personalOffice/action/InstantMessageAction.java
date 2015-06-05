/**  
 * @Project: jxoa
 * @Title: InstantMessageAction.java
 * @Package com.oa.manager.personalOffice.action
 * @date 2013-5-24 下午4:24:21
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.action;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.PerInstantMessage;
import com.oa.manager.personalOffice.service.IInstantMessageService;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：InstantMessageAction
 * 功能：个人办公--即时消息
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-24 下午4:24:21
 *
 */
@Controller
@RequestMapping("/instantMessage")
public class InstantMessageAction extends BaseAction{

	@Autowired
	private IInstantMessageService service;
	
	/**
	 * 跳转到即时消息页面, 查询出所有部门
	 * @param param
	 * @param map
	 * @return
	 */
	@RequestMapping("load")
	public String load(ModelMap map){
		
		return "personalOffice/instant_message/load";
	}
	/**
	 * 条件分页查询用户 可用用户
	 * @param param
	 * @param user
	 * @param map
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("queryUsers")
	public ModelAndView selectUsers(PageParam param,SyUsers user){
		user.setUserStatus((short)1);
		
		return ajaxJsonEscape(service.selectUsers(param, user));
	}
	/**
	 * 跳转到发送消息页面
	 * @return
	 */
	@RequestMapping("addPage")
	public String addPage(){
		
		return "personalOffice/instant_message/add";
	}
	/**
	 * 发送信息
	 * @param messageContent
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("add")
	public ModelAndView add(String messageContent,String[] userIds){
		
		if(messageContent.length()<1||messageContent.length()>65530){
			ModelAndView mav = new ModelAndView("ajaxMessage");
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"instantMessage.content.length");//您已{0}次密码输入错误，请{1}之后再试！
			mav.addObject(MsgConfig.MESSAGEVALUES, "1,65530字节");//占位符赋值
			return mav;
		}
		
		return ajaxDone(service.addInstantMessage(messageContent, userIds));
		
	}
	/**
	 * 已收消息页面
	 * @return
	 */
	@RequestMapping("selectReceiveMessage")
	public String selectReceiveMessage(){
		
		return "personalOffice/instant_message/receivey_message";
		
	}
	/**
	 * 已收消息 查询
	 * @param param
	 * @param im
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("selectReceiveMessage/query")
	public ModelAndView queryReceiveMessage(PageParam param,PerInstantMessage im ,Date startDate,Date endDate){
		im.setReceiveUid(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.selectInstantMessage(param, im, startDate, endDate));
	}
	/**
	 * 条件分页查询已发消息
	 * @return
	 */
	@RequestMapping("selectSendMessage")
	public String selectSendMessage(){
		
		return "personalOffice/instant_message/send_message";
	}
	/**
	 * 条件分页查询已发消息
	 * @param param
	 * @param im
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("selectSendMessage/query")
	public ModelAndView querySendMessage(PageParam param,PerInstantMessage im,Date startDate,Date endDate){
		im.setSendUid(ServletUtil.getMember().getId());
		return ajaxJsonEscape(service.selectInstantMessage(param, im, startDate, endDate));
	}
	/**
	 * 查询和某一用户的历史聊天记录
	 * @param param
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("selectHistoryMessage")
	public String loadHistoryMessage(PageParam param,String userId ,Date startDate,Date endDate,ModelMap map){
		
		map.addAttribute("msgs",service.selectHistoryMessage(param, userId, startDate, endDate));
		map.addAttribute("total",service.selectHistoryMessageCount(userId, startDate, endDate));
		map.addAttribute("pageSize",param.getRows());
		map.addAttribute("pageNumber",param.getPage());
		return "personalOffice/instant_message/history_message";
	
	}
	/**
	 * 查询和某一用户的历史聊天记录
	 * @param param
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("selectHistoryMessage/query")
	public String queryHistoryMessage(PageParam param,String userId ,Date startDate,Date endDate,ModelMap map){
		
		map.addAttribute("msgs",service.selectHistoryMessage(param, userId, startDate, endDate));
		
		return "personalOffice/instant_message/history_message_query";
	
	}
	
	/**
	 * 查询信息详情，并更新已读，未读状态
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		PerInstantMessage m=service.updatefindInstantMessageById(id);
		if(m==null){
			return NODATA;
		}
		map.addAttribute("msg",m);
		return "personalOffice/instant_message/show";
	}
	/**
	 * 删除消息提醒
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("instantMessage:read")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteInstantMessage(ids));
	}
	
}
