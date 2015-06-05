/**  
 * @Project: jxoa
 * @Title: MainAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-4-1 下午3:18:28
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Meeting;
import com.oa.manager.common_platform.bean.Newsmanagement;
import com.oa.manager.common_platform.bean.XtAffiche;
import com.oa.manager.common_platform.bean.XtAfficheDept;
import com.oa.manager.common_platform.service.IAfficheService;
import com.oa.manager.common_platform.service.IMeetingService;
import com.oa.manager.common_platform.service.INewsService;
import com.oa.manager.personalOffice.bean.PerInstantMessage;
import com.oa.manager.personalOffice.service.IEmailService;
import com.oa.manager.personalOffice.service.IInstantMessageService;
import com.oa.manager.personalOffice.service.IPerMsgService;
import com.oa.manager.personalOffice.service.IScheduleService;
import com.oa.manager.system.service.IDeptService;
import com.oa.manager.system.service.IMainService;
import com.oa.manager.system.service.IMenuService;
import com.oa.manager.workFlow.service.ITaskService;

/**
 * 
 * 类名：MainAction
 * 功能：系统主界面
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-1 下午3:18:28
 *
 */
@Controller
@RequestMapping("/main")
public class MainAction extends BaseAction{
	
	@Autowired
	private IMainService service; 
	@Autowired
	private IMenuService menuService;//菜单 
	@Autowired
	private IDeptService deptService; //部门
	@Autowired
	private IPerMsgService perMsgService; //消息提醒
	@Autowired
	private IInstantMessageService instantMessageService;//即时沟通
	@Autowired
	private IScheduleService scheduleService;//日程安排
	@Autowired
	private IAfficheService afficheService;//公告
	@Autowired
	private IMeetingService meetingService;//会议
	@Autowired
	private INewsService newsService;//新闻
	@Autowired
	private IEmailService emailService;//邮件
	@Autowired
	private ITaskService taskService;//工作流程 任务服务 
	/**
	 * 跳转到系统主界面  刷新主界面将会执行此方法
	 * @return
	 */
	@RequestMapping("")
	public String to(ModelMap map){
	
		if(SecurityUtils.getSubject().isPermitted("schedule:read")){
			//有日程安排提醒权限
			map.addAttribute("scheduleWarn",true);
		}
		//消息提提 定时刷新间隔时间
		map.addAttribute("msgWarnTime",BaseConfig.webconfig.getMsgwarnTime());
			
		//查询出顶部一级菜单
		map.addAttribute("menus",service.selectMenusTop());
	    //查询出用户的自定义快捷菜单
		
		List<Map<String,Object>> menus=menuService.selectMySyMenuMy();
		for(Map<String,Object>  m:menus){
			m.put("url",((String)m.get("url")).replaceAll("rel=","rel=my_"));//统一追加前缀 防止和正常菜单rel冲突
		}
	
		map.addAttribute("mymenus",menus);
		
		//用户基本信息
		Member me=ServletUtil.getMember();
		map.addAttribute("userId",me.getId());
		map.addAttribute("trueName", MyCache.getInstance().getTrueName(me.getId()));
		map.addAttribute("deptId",me.getDeptId());
		map.addAttribute("deptName", MyCache.getInstance().getDeptName(me.getDeptId()));
		
		return "main/main";			
		
		
	}
	/**
	 * 跳转到主工作区 首页
	 * @return
	 */
	@RequestMapping("home")
	public String home(ModelMap map){
		Member me=ServletUtil.getMember();
		Subject currentUser =SecurityUtils.getSubject();

		PageParam param=new PageParam();
		param.setRows(5);
		if(currentUser.isPermitted("affiche:myRead")){
			//查询系统公告
			map.addAttribute("affiche",afficheService.selectAffiche(param, new XtAffiche(), null, null).getRows());
		}
		if(currentUser.isPermitted("affiche:readMydept")){
			//查询部门公告
			XtAfficheDept ad=new XtAfficheDept();
			ad.setDeptId(me.getDeptId());
			map.addAttribute("afficheDept",afficheService.selectAfficheDept(param, ad, null, null).getRows());
		}
		
		if(currentUser.isPermitted("meeting/loadtome.do")){
			//我的会议
			map.addAttribute("meetings", meetingService.selectMeetingTome(param, new Meeting()).getRows());			
		}
		
		if(currentUser.isPermitted("news/load_news.do")){
			//新闻
			Newsmanagement news=new Newsmanagement();
			news.setStatus(1);
			map.addAttribute("news",newsService.newsload(param,news).getRows());
		}
		
		
		return "main/home";
	}
	
	/**
	 * 查询在线人员列表，部门列表，在线人员数量
	 * @return
	 */
	@RequestMapping("online")
	public ModelAndView online(){
		Map<String,OnLineUser> onLineUsers=ServletUtil.getOnLineUsers();
		Set<Entry<String,OnLineUser>> entrys=onLineUsers.entrySet();
		List<Map<String,Object>> users=new ArrayList<Map<String,Object>>();
		for(Entry<String,OnLineUser> e:entrys){
			OnLineUser u=e.getValue();
			Map<String,Object> user=new HashMap<String,Object>();
			user.put("id", u.getId());
			user.put("trueName", u.getTrueName());
			user.put("deptId", u.getDeptId());
			user.put("sex", u.getSex());
			users.add(user);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		map.put("users", users);
		map.put("depts", deptService.selectAllDeptsMap());
		map.put("onlineNum",ServletUtil.getOnLineUsers().size() );
		
		return ajaxJsonEscape(map);
		
	}
	
	/**
	 * 查询未读信息数量，页面Ajax定时请求
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("warnmsg")
	public ModelAndView  queryNotReadNum(){
		Member me=ServletUtil.getMember();
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		
		Subject currentUser =SecurityUtils.getSubject();
		
		//消息提醒数量
		map.put("warnmsg_num",perMsgService.selectNotReadNumber());
		
		if(currentUser.isPermitted("instantMessage:read")){
			PerInstantMessage im=new PerInstantMessage();
			im.setReceiveUid(me.getId());
			im.setReadState((short)0);
			//即时沟通未读信息数量
			map.put("instantmsg_num",instantMessageService.selectMyNotReadCount());
		}
		if(currentUser.isPermitted("schedule:read")){
			//查询出当天的日程安排数量
			map.put("todaySchedule_num",scheduleService.selectTodayScheduleCount());
		}
		if(currentUser.isPermitted("email/loadIn.do")){
			map.put("email_num",emailService.selectNumber());
		}
		if(currentUser.isPermitted("task:read")){
			
			//工作流程--待办任务数量,未领任务数量 总和
			map.put("flow_toDo_num",taskService.selectTaskCountByUser(me.getId(), ServletUtil.getRoleIdsByUserId(me.getId())));
		}
		return ajaxJsonEscape(map);
	}
	
	
	
	
	
}
