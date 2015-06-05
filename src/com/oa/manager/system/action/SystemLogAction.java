/**  
 * @Project: jxoa
 * @Title: SystemLogAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-4-11 下午3:45:10
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ExcelUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyLog;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.service.ISystemLogService;

/**
 * 
 * 类名：SystemLog
 * 功能：日志功能
 * 详细：系统登录日志 系统重要操作日志
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-11 下午3:45:10
 *
 */
@Controller
@RequestMapping("/log")
public class SystemLogAction extends BaseAction{
	
	@Autowired
	private ISystemLogService service; 
	
	
	/**
	 * 查询出登录日志，跳转到登录日志界面
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("log:readLogin")
	@RequestMapping("login")
	public String loginLoad(){
		
		return "system/logs/login_log_load";
	
	}
	/**
	 * 查询出登录日志，跳转到登录日志界面
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("log:readLogin")
	@RequestMapping("login/query")
	public ModelAndView loginQuery(PageParam param,SyLoginLog log,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectSyLoginLog(param, log, startDate, endDate,true));
	
	}
	
	/**
	 * 导出登陆日志
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("log:exportLogin")
	@RequestMapping("export")
	public String loginExport(String[] ids,HttpServletRequest request,HttpServletResponse response){
	
		
		return ExcelUtils.export(service.selectSyLoginLogs(ids), service.findTableCustomExport((short)1), "登陆日志导出.xls", request, response);

	}
	/**
	 * 打印登陆日志
	 * @param ids
	 * @param map
	 * @return
	 */
	@RequiresPermissions("log:printLogin")
	@RequestMapping("print")
	public String loginExport(String[] ids,ModelMap map){
	
		map.addAttribute("data",service.selectSyLoginLogs(ids));
		
		map.addAttribute("tc",service.findTableCustomPrint((short)1));
		
		map.addAttribute("tableTitle", "登陆日志");

		return "print";
	
	}
	/**
	 * 查询出个人登录日志，跳转到登录日志界面
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("log:readMy")
	@RequestMapping("login/my")
	public String loginLoadMy(){
		
		return "system/logs/login_log_load_my";
	
	}
	/**
	 * 查询出登录日志，跳转到登录日志界面
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("log:readMy")
	@RequestMapping("login/my/query")
	public ModelAndView loginMyQuery(PageParam param,SyLoginLog log,Date startDate,Date endDate){
		log.setUserId(ServletUtil.getMember().getId());
		return ajaxJsonEscape(service.selectSyLoginLog(param, log, startDate, endDate,false));
	
	}
	/**
	 * 删除登录日志
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("log:deleteLogin")
	@RequestMapping("login/del")
	public ModelAndView deleteLoginLog(String[] ids){
		
		return ajaxDone(service.deleteLoginLog(ids));
	
	}
	
	
	/**
	 * 查询出系统操作日志，跳转到系统操作日志界面
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("log:readAction")
	@RequestMapping("action")
	public String actionLoad(){
		
		return "system/logs/action_log_load";
	}
	/**
	 * 查询出登录日志，跳转到登录日志界面
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequiresPermissions("log:readAction")
	@RequestMapping("action/query")
	public ModelAndView actionQuery(PageParam param,SyLog log,Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectSyLog(param, log, startDate, endDate));
	}
	
	
	/**
	 * 批量删除操作日志
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("log:deleteAction")
	@RequestMapping("action/del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteLog(ids));
	
	}
	
	
}
