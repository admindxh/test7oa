/**  
 * @Project: jxoa
 * @Title: UserServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-3-29 下午2:20:27
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.MD5Util;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyDept;
import com.oa.manager.system.bean.SyUserRole;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.IUserService;

/**
 * 
 * 类名：UserServiceImpl
 * 功能：用户管理 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-3-29 下午2:20:27
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectUsers(PageParam param,SyUsers user){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyUsers u where 1=1 ");
		List list=new ArrayList();
		if(StringUtils.isNotBlank(user.getUserName())){
			sb.append(" and u.userName like ? ");
			list.add("%"+user.getUserName()+"%");
		}
		if(StringUtils.isNotBlank(user.getTrueName())){
			sb.append(" and u.trueName like ? ");
			list.add("%"+user.getTrueName()+"%");
		}
		if(StringUtils.isNotBlank(user.getDeptId())&&!"0".equals(user.getDeptId())){
			sb.append(" and u.deptId = ? ");
			list.add(user.getDeptId());	
		}
		if(user.getUserSex()!=null){
			sb.append(" and u.userSex = ? ");
			list.add(user.getUserSex());	
		}
		if(user.getUserStatus()!=null){
			sb.append(" and u.userStatus = ? ");
			list.add(user.getUserStatus());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(u.id as id,u.userName as userName,u.registerTime as registerTime,u.userStatus as userStatus," +
				"u.deptId as deptId,u.trueName as trueName,u.userSex as userSex) "
				+sb.toString(),param.getPage(),param.getRows(),list);
		 
		for(Map<String,Object> map:rows){
			map.put("deptName",MyCache.getInstance().getDeptName((String)map.get("deptId")));
		 
		}		
		
		data.setRows(rows);
		
		return data;
		
	}
	
	
	public String addUser(SyUsers user){
		Object obj=dao.findOne("from SyUsers where userName=?",user.getUserName());
		if(obj==null){
			
			Member me=ServletUtil.getMember();
			user.setRegisterUid(me.getId());
			
			user.setUserPassword(MD5Util.MD5(user.getUserPassword()));
			user.setErrorCount((short)0);
			user.setLastLoginIp("x.x.x.x");//设置用户最后登录ip，可以根据此ip判断用户是否为第一次登录系统
			user.setRegisterTime(new Timestamp(new Date().getTime()));
			dao.save(user);
			if(StringUtils.isNotBlank(user.getId())){
				
				saveLog("添加用户", "账号:"+user.getUserName());
				
				ServletUtil.getSession().removeAttribute("jmpw");//清除加密密码
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.username.unique";//用户名已被占用
		}
	}
	
	public String addUsers(List<Map> excel){
		List<SyUsers> lu  = new ArrayList<SyUsers>();
		for (Map<Integer ,String> map : excel) {
			SyUsers user = new SyUsers();
			user.setUserName(map.get(0));
			user.setTrueName(map.get(1));
			String did = (String)dao.findOne("select id from SyDept where deptName=?  ", map.get(2));
			if(did!=null && !"".equals(did)){
				user.setDeptId(did);
			}
			if("女".equals(map.get(3))){
				user.setUserSex((short)0);
			}else{
				user.setUserSex((short)1);
			}
			user.setMobilePhoneNumber(map.get(4));
			if("是".equals(map.get(5))){
				user.setUserStatus((short)1);
			}else{
				//禁止登陆
				user.setUserStatus((short)0);
			}
			
			
			user.setUserPassword("H1AF2G39C90F59F00H5DHA574BA4EE3H");//默认密码123456
			Member me=ServletUtil.getMember();
			user.setRegisterUid(me.getId());
			user.setErrorCount((short)0);
			user.setLastLoginIp("x.x.x.x");//设置用户最后登录ip，可以根据此ip判断用户是否为第一次登录系统
			user.setRegisterTime(new Timestamp(new Date().getTime()));
			lu.add(user);
			
		}
		
		if(dao.saveOrUpdateAll(lu)){
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
					
		}
		
		
			
	}
	
	
	public String updateUser(SyUsers u){
		SyUsers old=dao.get(SyUsers.class, u.getId());
		if(old==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		Object obj=dao.findOne("from SyUsers where userName=? and id!=?",u.getUserName(),u.getId());
		if(obj!=null){
			return "msg.username.unique";//用户名已被占用
		}
		if(StringUtils.isNotBlank(u.getUserPassword())){
			old.setUserPassword(MD5Util.MD5(u.getUserPassword()));
		}
		old.setUserName(u.getUserName());
		old.setTrueName(u.getTrueName());
		old.setUserSex(u.getUserSex());
		old.setDeptId(u.getDeptId());
		old.setUserDesc(u.getUserDesc());
		
		
		old.setUserStatus(u.getUserStatus());
		
		old.setMobilePhoneNumber(u.getMobilePhoneNumber());
		
		saveLog("修改用户", "账号:"+old.getUserName());
		
		//删除缓存
		MyCache.getInstance().removeCache(MyCache.USERID2INFO,old.getId());
		
		return MsgConfig.MSG_KEY_SUCCESS;
		
	}
	
	
	public boolean deleteUser(String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			SyUsers user=dao.get(SyUsers.class, id);
			if(user!=null){
				//开发人员账号，超级管理员账号不可删除
				if(!user.getUserName().equals(BaseConfig.getInstance().getDevName())&&!user.getUserName().equals(BaseConfig.getInstance().getSaName())){
					
					saveLog("删除用户", "账号："+user.getUserName()+" 姓名："+user.getTrueName());
					c.add(user);
					
					//删除缓存
					MyCache.getInstance().removeCache(MyCache.USERID2INFO,id);
					
				}
				
			}
		}
		

		return dao.deleteAll(c);
	}
	

	@SuppressWarnings("rawtypes")
	public List selectUserRoles(String userId){
		
		return dao.find("select r from SyRole r,SyUserRole ur where ur.userId=? and r.id=ur.roleId ",userId);
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map selectUserRolesAndIds(String userId){
		
		List<Map> allRoles=dao.find("select new Map(r.id as id,r.roleName as roleName)from SyRole r");
		List<String> oldRoles=dao.find("select roleId from SyUserRole where userId=? ",userId);
		Map map=new HashMap();
		map.put("roles", allRoles);
		map.put("hasRoles", oldRoles);
		
		return map;
		
	}
	public boolean updateUserRoles(String userId,String[] addRoleIds,String[] delRoleIds){
		//等待修改的对象集合
		List<Object> c=new ArrayList<Object>();
		//添加用户角色关联
		
		for(String id:addRoleIds){
			SyUserRole ur=new SyUserRole();
			ur.setRoleId(id);
			ur.setUserId(userId);
			c.add(ur);
		}
		//删除用户角色关联
		for(String id:delRoleIds){
			dao.delete(" delete SyUserRole where roleId=? and userId=? ",id,userId);
		}
		
		return dao.saveOrUpdateAll(c);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> selectUserPowers(String userId){
		List<String> ids=dao.find("select r.id from SyRole r, SyUserRole ur where ur.roleId=r.id and ur.userId=? ",userId);
		Map<String,Object> map=new HashMap<String,Object>();
		if(!ids.isEmpty()){
			Map<String,Object> queryValues=new HashMap<String,Object>();
			queryValues.put("roleIds", ids);
			List menus=dao.find("select distinct new Map(m.id as id,m.menuName as menuName,m.menuSuperId as menuSuperId,m.menuIcon as menuIcon) from SyRoleMenu rm,SyMenu m where rm.menuId=m.id and rm.roleId in(:roleIds) order by m.menuSort asc",queryValues);
			List actions=dao.find("select distinct new Map(a.id as id,a.menuId as menuId, a.actionName as actionName) from SyRoleAction ra,SyAction a where ra.actionId=a.id and ra.roleId in(:roleIds) order by a.actionSort asc",queryValues);
			
			map.put("menus", menus);
			map.put("actions", actions);
			
		}else{
			map.put("noRole", true);
		}
		return map;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SyUsers> selectAllUsers(){
		
		return  dao.find("from SyUsers");
		
	}
	
	public boolean updatePassword(String id,String userPassword){
		
	
		return dao.update("update SyUsers set userPassword=? where id=?",MD5Util.MD5(userPassword),id);
			
	}
	
	public boolean updateMyPassword(String oldPassword,String userPassword){
		
		SyUsers user=dao.get(SyUsers.class, ServletUtil.getMember().getId());
		if(MD5Util.MD5Validate(oldPassword, user.getUserPassword())){
			user.setUserPassword(MD5Util.MD5(userPassword));
			saveLog("修改密码", "");
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectUsersLookUp(PageParam param,SyUsers user){
		DataGrid data=new DataGrid();
		
		StringBuffer sb=new StringBuffer("from SyUsers u where 1=1 ");
		List list=new ArrayList();
		
		if(StringUtils.isNotBlank(user.getTrueName())){
			sb.append(" and u.trueName like ? ");
			list.add("%"+user.getTrueName()+"%");
		}
		if(StringUtils.isNotBlank(user.getDeptId())){
			sb.append(" and u.deptId = ? ");
			list.add(user.getDeptId());	
		}
		if(user.getUserSex()!=null){
			sb.append(" and u.userSex = ? ");
			list.add(user.getUserSex());	
		}
		if(user.getUserStatus()!=null){
			sb.append(" and u.userStatus = ? ");
			list.add(user.getUserStatus());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(u.id as id," +
				"u.deptId as deptId,u.trueName as trueName,u.userSex as userSex) "
				+sb.toString(),param.getPage(),param.getRows(),list);
		 
		for(Map<String,Object> map:rows){
			map.put("deptName",MyCache.getInstance().getDeptName((String)map.get("deptId")));
		 
		}		
		
		data.setRows(rows);
		
		return data;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectUsersLookUpNumber(PageParam param,SyUsers user){
		DataGrid data=new DataGrid();
		
		StringBuffer sb=new StringBuffer(" from SyUsers u where 1=1 ");
		List list=new ArrayList();
		
		if(StringUtils.isNotBlank(user.getTrueName())){
			sb.append(" and u.trueName like ? ");
			list.add("%"+user.getTrueName()+"%");
		}
		if(StringUtils.isNotBlank(user.getDeptId())){
			sb.append(" and u.deptId = ? ");
			list.add(user.getDeptId());	
		}
		if(user.getUserSex()!=null){
			sb.append(" and u.userSex = ? ");
			list.add(user.getUserSex());	
		}
		if(user.getUserStatus()!=null){
			sb.append(" and u.userStatus = ? ");
			list.add(user.getUserStatus());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(u.id as id,u.mobilePhoneNumber as mobilePhoneNumber, " +
				"u.deptId as deptId,u.trueName as trueName,u.userSex as userSex) "
				+sb.toString(),param.getPage(),param.getRows(),list);
		 
		for(Map<String,Object> map:rows){
			map.put("deptName",MyCache.getInstance().getDeptName((String)map.get("deptId")));
		 
		}		
		
		data.setRows(rows);
		
		return data;
		
	}
	public SyUsers findUserByLoginName(String name){
		
		return (SyUsers)dao.findOne("from SyUsers where userName = ?",name);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Collection<String>> selectRolesPowers(String userId){
		Map<String,Collection<String>> map=new HashMap<String,Collection<String>>();
		List<String> roleIds=dao.find("select roleId from SyUserRole where userId=? ",userId);
		//用户可以访问的url集合
		Set<String> urls=new HashSet<String>();
		for(String id:roleIds){
			List<String> menuUrls=dao.find("select distinct m.menuUrl from SyRoleMenu rm,SyMenu m where rm.menuId=m.id and rm.roleId=? ",id);
			for(String url:menuUrls){
				if(StringUtils.isNotBlank(url)){
					urls.add(url.split("\\?")[0]);
				}
			}
		}
		//获取操作url
		for(String id:roleIds){
			List<String> actUrl=dao.find("select distinct a.actionUrl from SyAction a,SyRoleAction ra where a.id=ra.actionId and ra.roleId=? ",id);
			for(String url:actUrl){
				if(StringUtils.isNotBlank(url)){
					urls.addAll(Arrays.asList(url.split(",")));
				}
			}
		}
		map.put("roleIds", roleIds);
		map.put("powers", urls);
		return map;
	}
//********************************安卓********************************************************
	/**
	 * id 用户id
	 * trueName  用户姓名
	 * sex 用户性别  1 男 0 女
	 * deptName部门名称
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryAllUsers(PageParam param) {
		
		return dao.findPage(" select new Map(u.id as id,u.trueName as trueName,u.userSex as sex,d.deptName as deptName) from SyUsers u,SyDept d where u.userStatus='1' and u.deptId=d.id  ", param.getPage(),param.getRows());
	}
	@Override
	public Long totleUsers() {
		
		return (Long) dao.findUniqueOne("select count(*) from SyUsers u where u.userStatus='1' " );
	}
	/**
	 * id 用户id
	 * trueName  用户姓名
	 * sex 用户性别  1 男 0 女
	 * deptName部门名称
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryUserConditions(String deptId,String name,PageParam param) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" from SyUsers u,SyDept d where u.userStatus='1' and u.deptId=d.id  ");
		if(StringUtils.isNotBlank(deptId)){
			sb.append(" and u.deptId=? ");
			list.add(deptId);
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and u.trueName like ? ");
			list.add("%"+name+"%");
		}
		return dao.findPage("select new Map(u.id as id,u.trueName as trueName,u.userSex as sex,d.deptName as deptName)"+sb.toString(),param.getPage(), param.getRows(),list); 
	}
	@Override
	public Long totleUserConditions(String deptId,String name) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" from SyUsers u,SyDept d where u.userStatus='1' and u.deptId=d.id  ");
		if(StringUtils.isNotBlank(deptId)){
			sb.append(" and u.deptId=? ");
			list.add(deptId);
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and u.trueName like ? ");
			list.add("%"+name+"%");
		}
		return (Long) dao.findUniqueOne("select count(*)"+sb.toString(),list);
	}
}
