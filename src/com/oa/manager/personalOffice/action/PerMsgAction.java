/**  
 * @Project: jxoa
 * @Title: PerMsgAction.java
 * @Package com.oa.manager.personalOffice.action
 * @date 2013-5-21 下午4:21:54
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.PerMsg;
import com.oa.manager.personalOffice.service.IPerMsgService;

/**
 * 
 * 类名：PerMsgAction
 * 功能：个人办公--消息提醒
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-21 下午4:21:54
 *
 */
@Controller
@RequestMapping("/msgwarn")
public class PerMsgAction extends BaseAction{
	
	@Autowired
	private IPerMsgService service; 
	
	/**
	 * 跳转到消息提醒，查询出未读消息提醒
	 */
	@RequestMapping("load")
	public String load(){
	
		return "personalOffice/msgwarn/load";
	}
	/**
	 * 出未读消息页面
	 * @return
	 */
	@RequestMapping("load/notread")
	public String load_notRead(){
		
		return "personalOffice/msgwarn/query_notread";
	}
	/**
	 * 查询出未读消息  查询
	 * @param param
	 * @param msg
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("query")
	public ModelAndView query_notRead(PageParam param,PerMsg msg,Date startDate,Date endDate){
		msg.setUserId(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.selectPerMsg(param, msg, startDate, endDate));
	}
	/**
	 * 已读消息页面
	 * @return
	 */
	@RequestMapping("load/isread")
	public String load_isRead(){
		
		return "personalOffice/msgwarn/query_isread";
	}
	
	/**
	 * 查看单个消息提醒
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		PerMsg m=service.updatefindPerMsgById(id);
		if(m==null){
			return NODATA;
		}
		map.addAttribute("msg",m);
		return "personalOffice/msgwarn/show";
	}
	
	/**
	 * 删除消息提醒
	 * @param ids
	 * @return
	 */
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deletePerMsg(ids));
	}
	

	
	
}
