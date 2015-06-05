/**  
 * @Project: jxoa
 * @Title: MenuServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-4-24 上午8:41:50
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Menu;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyAction;
import com.oa.manager.system.bean.SyMenu;
import com.oa.manager.system.bean.SyMenuMy;
import com.oa.manager.system.service.IMenuService;

/**
 * 
 * 类名：MenuServiceImpl
 * 功能：菜单管理 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-24 上午8:41:50
 *
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService{
	
	
	@SuppressWarnings("unchecked")
	public List<SyMenu> selectMenus(){
		if(ServletUtil.isDeveloper()){
			return dao.find("from SyMenu order by menuSort asc");
		}else{
			return dao.find("from SyMenu where menuStatus=1 order by menuSort asc");
		}
	}
	//获取用户可以访问的菜单
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,Object>> selectMenusByUserId(String userId){
		
		List<String> roleIds=ServletUtil.getRoleIdsByUserId(userId);
		StringBuffer hql=new StringBuffer(" select distinct new Map(" +
				"m.id as id,m.menuName as name,m.menuSuperId as pid,m.menuUrl as url," +
				"m.menuExternal as external,m.menuFresh as fresh,m.menuIcon as icon,m.menuOpen as open," +
				"m.menuRel as rel,m.menuTarget as target )from ");
		Map map=new HashMap();
		if(ServletUtil.isDeveloper()){
			hql.append(" SyMenu m ");
		}else{
			hql.append(" SyRoleMenu rm, SyMenu m where rm.menuId=m.id and rm.roleId in(:roleIds)  and  m.menuStatus=1 ");
			map.put("roleIds", roleIds);
		}
		return dao.find(hql+" order by m.menuSort asc ",map);

	}
	
	/*//获取用户可以访问的菜单
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,Object>> selectMyMenusfor(){
		
		List<String> roleIds=ServletUtil.getRoleIdsByUserId(userId);
		StringBuffer hql=new StringBuffer(" select distinct new Map(" +
				"m.id as id,m.menuName as name,m.menuSuperId as pid,m.menuUrl as url," +
				"m.menuExternal as external,m.menuFresh as fresh,m.menuIcon as icon,m.menuOpen as open," +
				"m.menuRel as rel,m.menuTarget as target )from ");
		Map map=new HashMap();
		if(ServletUtil.isDeveloper()){
			hql.append(" SyMenu m ");
		}else{
			hql.append(" SyRoleMenu rm, SyMenu m where rm.menuId=m.id and rm.roleId in(:roleIds)  and  m.menuStatus=1 ");
			map.put("roleIds", roleIds);
		}
		return dao.find(hql+" order by m.menuSort asc ",map);

	}
	*/
		
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryMenus(){
		if(ServletUtil.isDeveloper()){
			return dao.find("select new Map(m.id as id,m.menuName as menuName,m.menuSuperId as menuSuperId,m.menuIcon as menuIcon,m.menuOpen as menuOpen)from SyMenu m order by menuSort asc");
		}else{
			return dao.find("select new Map(m.id as id,m.menuName as menuName,m.menuSuperId as menuSuperId,m.menuIcon as menuIcon,m.menuOpen as menuOpen) from SyMenu m where menuStatus=1 order by menuSort asc");
		}
	}
	public String[] selectMenusIcons(){
		
		File node=new File(BaseConfig.webPath+"/resource/images/menu/");
		String[] names=node.list();
		for(int i=0,k=names.length; i<k;i++){
			names[i]="resource/images/menu/"+names[i];
		}
		return names;
	}
	
	public String addMenu(SyMenu m){
		Object obj=dao.findOne("from SyMenu where menuName=? and menuSuperId=?",m.getMenuName(),m.getMenuSuperId());
		if(obj==null){
			if(dao.save(m)){
				saveLog("添加菜单", "菜单名:"+m.getMenuName());
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.menuname.unique";//菜单名已被占用
		}
		
	}
	
	public String updateMenu(SyMenu m){
		
		Object obj=dao.findOne("from SyMenu where menuName=? and menuSuperId=? and id!=?",m.getMenuName(),m.getMenuSuperId(),m.getId());
		if(obj==null){
			
			if(ServletUtil.isDeveloper()){
				
				//开发人员可修改全部字段
				if(dao.update(m)){
					saveLog("修改菜单", "菜单名:"+m.getMenuName());
					return MsgConfig.MSG_KEY_SUCCESS;
				}else{
					return MsgConfig.MSG_KEY_FAIL;
				}
			}else{
				//非开发人员只可修改部分字段
				
				SyMenu old=dao.get(SyMenu.class, m.getId());
				old.setMenuIcon(m.getMenuIcon());
				old.setMenuName(m.getMenuName());
				old.setMenuOpen(m.getMenuOpen());
				old.setMenuSort(m.getMenuSort());
				old.setMenuSuperId(m.getMenuSuperId());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}
			
			
		}else{
			return "msg.menuname.unique";//菜单名已被占用
		}
	}
	public String deleteMenu(String id){
		
		Object o=dao.findOne("from SyMenu where menuSuperId=? ",id);
		if(o!=null){
			return "msg.menu.haschild";//菜单下属还有子菜单，无法删除
		}else{
			SyMenu menu=dao.get(SyMenu.class, id);
			if(menu!=null){
				if(dao.delete(menu)){
					saveLog("删除菜单", "菜单名称："+menu.getMenuName());
					return MsgConfig.MSG_KEY_SUCCESS;
				}else{
					return MsgConfig.MSG_KEY_FAIL;
				}
			}else{
				return MsgConfig.MSG_KEY_NODATA;
			}
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public Map findMenuById(String id){
		return (Map)dao.findOne("select new Map(m as m,(select menuName from SyMenu where id=m.menuSuperId)as superName)from SyMenu m where m.id=?",id);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectActions(PageParam param,SyAction action){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyAction a where 1=1 ");
		List list=new ArrayList();
		if(StringUtils.isNotBlank(action.getActionName())){
			sb.append(" and a.actionName like ? ");
			list.add("%"+action.getActionName()+"%");
		}
		if(StringUtils.isNotBlank(action.getActionUrl())){
			sb.append(" and a.actionUrl like ? ");
			list.add("%"+action.getActionUrl()+"%");	
		}
		if(StringUtils.isNotBlank(action.getMenuId())){
			sb.append(" and a.menuId = ? ");
			list.add(action.getMenuId());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by a.actionSort");
		}
		data.setRows(dao.findPage(sb.toString(),param.getPage(),param.getRows(),list));
		return data;
	}
	
	public boolean deleteActions(String[] ids){
		
		for(String id:ids){
			dao.delete("delete SyAction where id=?", id);
		}
		return true;
	}
	//获取用户可以访问的所有菜单  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SyMenu> selectMyMenus(){
		
		List<String> roleIds=ServletUtil.getRoleIdsByUserId(ServletUtil.getMember().getId());
		StringBuffer hql=new StringBuffer(" select distinct  m from ");
		Map map=new HashMap();
		if(ServletUtil.isDeveloper()){
			hql.append(" SyMenu m ");
		}else{
			hql.append(" SyRoleMenu rm, SyMenu m where  rm.menuId=m.id and rm.roleId in(:roleIds)  and  m.menuStatus=1 ");
			map.put("roleIds", roleIds);
			
		}
		return dao.find(hql+" order by m.menuSort asc ",map);

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectMyMenus(PageParam param,SyMenu menu){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyMenu m ,SyMenuMy my where m.id=my.menuId and my.userId=? ");
		List list=new ArrayList();
		list.add(ServletUtil.getMember().getId());
		if(StringUtils.isNotBlank(menu.getMenuName())){
			sb.append(" and m.menuName like ? ");
			list.add("%"+menu.getMenuName()+"%");
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by my.sort asc ");
		}
		
		data.setRows(dao.find("select new Map(my.id as id,m.menuName as name,my.sort as sort) "+sb,list));
		
		return data;
	}
	
	public String addSyMenuMy(SyMenuMy m){
		
		Object obj=dao.findOne("from SyMenuMy  where userId=? and menuId=? ",ServletUtil.getMember().getId(),m.getMenuId());
		if(obj==null){
			m.setUserId(ServletUtil.getMember().getId());
			dao.save(m);
			if(StringUtils.isNotBlank(m.getId())){
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			throw new MyRuntimeException("菜单重复！此菜单已添加到快捷菜单了！请重新选择其他菜单!");
		}
	}
	public String updateSyMenuMy(SyMenuMy m){
		
		Object obj=dao.findOne("from SyMenuMy where userId=? and menuId=? and id!=? ",ServletUtil.getMember().getId(),m.getMenuId(),m.getId());
		if(obj==null){
			SyMenuMy old=dao.get(SyMenuMy.class, m.getId());
			old.setMenuId(m.getMenuId());
			old.setSort(m.getSort());
			if(StringUtils.isNotBlank(m.getId())){
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			throw new MyRuntimeException("菜单重复！此菜单已添加到快捷菜单了！请重新选择其他菜单!");
		}
	}
	public boolean deleteSyMenuMy(String id){
		//等待删除的对象集合
	
		return dao.delete("delete SyMenuMy where id=?",id);
	}
	
	//查询出用户可以访问的自定义快捷菜单 
	public List selectMySyMenuMy(){
		
		
		return dao.find(" select new Map(m.menuName as name,m.menuExternal as external,m.menuIcon as icon,m.menuRel as rel,m.menuTarget as target,m.menuUrl as url) from  SyMenu m ,SyMenuMy my where  my.menuId=m.id and my.userId=?  and  m.menuStatus=1  order by my.sort asc ",ServletUtil.getMember().getId());
	}
	
	
	
	public void getChildrenMenusBySuperId(List<SyMenu> list, Menu menu,String superId){
		
		List<Menu> childrenMenus=new ArrayList<Menu>();
		
		for(SyMenu s:list){
			if(s.getMenuSuperId().equals(superId)){
				Menu m=new Menu();
				m.setExternal(s.getMenuExternal());
				m.setFresh(s.getMenuFresh());
				m.setIcon(s.getMenuIcon());
				m.setId(s.getId());
				m.setName(s.getMenuName());
				m.setOpen(s.getMenuOpen());
				m.setRel(s.getMenuRel());
				m.setSuperId(s.getMenuSuperId());
				m.setTarget(s.getMenuTarget());
				m.setUrl(s.getMenuUrl());
				
				//递归获取下级菜单
				getChildrenMenusBySuperId(list, m, m.getId());
				
				childrenMenus.add(m);
			}
		}
		menu.setChildrenMenus(childrenMenus);
		
	}
	
	
}
