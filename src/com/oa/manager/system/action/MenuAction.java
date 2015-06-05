/**  
 * @Project: jxoa
 * @Title: MenuAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-4-24 上午8:41:21
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
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
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.Member;
import com.oa.commons.model.Menu;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyAction;
import com.oa.manager.system.bean.SyMenu;
import com.oa.manager.system.bean.SyMenuMy;
import com.oa.manager.system.service.IMenuService;

/**
 * 
 * 类名：MenuAction
 * 功能：系统管理--菜单管理
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-24 上午8:41:21
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuAction extends BaseAction{
	
	@Autowired
	private IMenuService service; 
	
	/**
	 * 跳转到菜单管理页面 查询出所有菜单
	 * @param map
	 * @return
	 */
	@RequiresPermissions("menu:read")
	@RequestMapping("load")
	public String load(){
		
		
		return "system/menu/load";
	}
	/**
	 * 跳转到菜单管理页面 查询出所有菜单
	 * @param map
	 * @return
	 */
	@RequestMapping("query")
	public ModelAndView query(){
		
		return ajaxJsonEscape(service.queryMenus());
	}
	
	/**
	 * 跳转到菜单添加页面
	 * @return
	 */
	@RequestMapping("addPage")
	public String addPage(){
		if(!ServletUtil.isDeveloper()){
			return NOPOWER;
		}
		return "system/menu/add";
	
	}
	/**
	 * 添加菜单
	 * @param menu
	 * @param errors
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add(@Valid SyMenu menu,Errors errors){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.addMenu(menu));
		
	}
	/**
	 * 查询出所有可用菜单图标
	 * @return
	 */
	@RequestMapping("icons")
	public String showMenuIcons(ModelMap map){
		
		map.addAttribute("icons",service.selectMenusIcons());
		
		return "system/menu/icon_lookup";
	}
	/**
	 * 菜单查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUp(){
		
		
		return "system/menu/lookup";
	}
	/**
	 * 查找带回 查询我能访问的所有菜单
	 * @param map
	 * @return
	 */
	@RequestMapping("queryMyAll")
	public ModelAndView queryMyAll(){
		
		return ajaxJsonEscape(service.selectMyMenus());
	}
	
	
	
	/**
	 * 菜单修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("menu:read")
	@SuppressWarnings("unchecked")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		map.addAllAttributes(service.findMenuById(id));
		if(map.get("m")==null){
			return NODATA;
		}
		return "system/menu/update";
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("menu:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid SyMenu menu,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateMenu(menu));
		
	}
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@RequestMapping("del")
	public ModelAndView delete(String id){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		return ajaxDone(service.deleteMenu(id));
	
	}
	/**
	 * action条件查询
	 * @param param
	 * @param action
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("action/query")
	public ModelAndView selectActions(PageParam param,SyAction action) {
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		return ajaxJsonEscape(service.selectActions(param, action));
	}
	/**
	 * 跳转到action添加页面
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("action/addPage")
	public String addActionPage() {
		if(!ServletUtil.isDeveloper()){
			return NOPOWER;
		}
		return "system/menu/action/add";
	
	}
	/**
	 * 添加action
	 * @param action
	 * @param errors
	 * @return
	 */
	@RequestMapping("action/add")
	public ModelAndView addAction(@Valid SyAction action,Errors errors){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.save(action));
		
	}

	/**
	 * 跳转到action编辑页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("action/updatePage")
	public String editAction(String id,ModelMap map){
		if(!ServletUtil.isDeveloper()){
			return NOPOWER;
		}
		SyAction a=service.get(SyAction.class,id);
		if(a==null){
			return NODATA;
		}
		map.addAttribute("act",a);
		
		return "system/menu/action/update";
	}
	/**
	 * 更新action
	 * @param action
	 * @param errors
	 * @return
	 */
	@RequestMapping("action/update")
	public ModelAndView updateAction(@Valid SyAction action,Errors errors){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.update(action));
		
	}
	/**
	 * 删除action
	 * @param id
	 * @return
	 */
	@RequestMapping("action/del")
	public ModelAndView deleteActions(String[] ids){
		if(!ServletUtil.isDeveloper()){
			return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
		}
		return ajaxDone(service.deleteActions(ids));
	
	}
	/**
	 * 获取用户可以使用的菜单和一些用户基本信息
	 * @return
	 */
	@RequestMapping("mymenus")
	public ModelAndView selectMyMenus(){
		Member me=ServletUtil.getMember();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		map.put("menus", service.selectMenusByUserId(me.getId()));
		map.put("userId", me.getId());
		map.put("userName", MyCache.getInstance().getTrueName(me.getId()));
		map.put("deptId", me.getDeptId());
		map.put("deptName", MyCache.getInstance().getDeptName(me.getDeptId()));
		
		if(ServletUtil.getSessionAttribute("fromLogin")!=null){
			//登陆完成之后第一次查询菜单
			ServletUtil.getSession().removeAttribute("fromLogin");
		}else{
			//非登陆完整之后的刷新菜单 需要清空用户授权信息
			ServletUtil.removeAuthorizationCache(me.getId());
		}
		return ajaxJsonEscape(map);
	}
	
	/**
	 * 获取用户可以使用的菜单和一些用户基本信息
	 * @return
	 */
	@RequestMapping("mymenus/by")
	public String selectMyMenusById(String id,ModelMap map){
		
		SyMenu m=service.get(SyMenu.class, id);
		if(m!=null){
			map.addAttribute("name",m.getMenuName());
		}
		Menu menu=new Menu();
		
		service.getChildrenMenusBySuperId(service.selectMyMenus(), menu, id);
		
		List<Menu> list=menu.getChildrenMenus();
		StringBuffer sb=new StringBuffer();
		for(Menu l:list){
			sb.append("<li><a ");
			if(l.getUrl()!=null&&!"".equals(l.getUrl())){
				sb.append("href=\"");
				sb.append(l.getUrl());
				sb.append("\" target=\"").append(l.getTarget()).append("\" rel=\"").append(l.getRel()).append("\" external=\"");
				sb.append(l.getExternal()).append("\" fresh=\"false\" title=\"").append(l.getName()).append("\" ");
			}else{
				sb.append(" style=\"color: black;\" ");
			}
			sb.append(" ><img src=\"").append(l.getIcon()).append("\" />&nbsp;&nbsp;");
			sb.append(l.getName());
			sb.append("</a>");
			List<Menu> cs=l.getChildrenMenus();
			if(cs!=null&&!cs.isEmpty()){
				sb.append("<ul class=\"ul-submenu\">");
				appendMenus(cs, sb);
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
		map.addAttribute("menus",sb.toString());
		
		return "main/middle";
	}
	//递归拼接子菜单
	private void appendMenus(List<Menu> list,StringBuffer sb){
		for(Menu l:list){
			sb.append("<li><a ");
			
			if(l.getUrl()!=null&&!"".equals(l.getUrl())){
				sb.append("href=\"");
				sb.append(l.getUrl());
				sb.append("\" target=\"").append(l.getTarget()).append("\" rel=\"").append(l.getRel()).append("\" external=\"");
				sb.append(l.getExternal()).append("\" fresh=\"false\" title=\"").append(l.getName()).append("\" ");
			}else{
				sb.append(" style=\"color: black;\" ");
			}
			sb.append(" ><img src=\"").append(l.getIcon()).append("\" />&nbsp;&nbsp;");
			sb.append(l.getName());
			sb.append("</a>");
			List<Menu> cs=l.getChildrenMenus();
			if(cs!=null&&!cs.isEmpty()){
				sb.append("<ul class=\"ul-submenu\">");
				appendMenus(cs, sb);
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
	}
	/**
	 * 跳转到快捷菜单的管理页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("my/load")
	public String loadMy(){
		
		return "system/menu/my/load";
	}
	
	@RequestMapping("my/query")
	public ModelAndView queryMy(PageParam param,SyMenu menu){
		
		return ajaxJsonEscape(service.selectMyMenus(param,menu));
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("my/addPage")
	public String addPageMy(){
		
		return "system/menu/my/add";
	
	}
	
	@RequestMapping("my/add")
	public ModelAndView addMy(@Valid SyMenuMy m,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.addSyMenuMy(m));
		
	}
	
	@RequestMapping("my/updatePage")
	public String updatePageMy(String id,ModelMap map){
		SyMenuMy my=service.get(SyMenuMy.class, id);
		if(my==null){
			return NODATA;
		}
		SyMenu menu=service.get(SyMenu.class, my.getMenuId());
		if(menu==null){
			return NODATA;
		}
		map.addAttribute("my",my);
		map.addAttribute("name",menu.getMenuName());
		
		return "system/menu/my/update";
	}
	
	@RequestMapping("my/update")
	public ModelAndView update(@Valid SyMenuMy m,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateSyMenuMy(m));
		
	}
	
	@RequestMapping("my/del")
	public ModelAndView deleteMy(String id){
		
		return ajaxDone(service.deleteSyMenuMy(id));
	
	}
	
	
}
