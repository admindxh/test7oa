/**  
 * @Project: jxoa
 * @Title: MyCache.java
 * @Package com.oa.commons.cache
 * @date 2013-4-7 上午11:37:58
 * @Copyright: 2013 
 */
package com.oa.commons.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.oa.commons.model.UserInfo;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.bean.SyDept;
import com.oa.manager.system.bean.SyRole;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.IDataPermissionsService;
import com.oa.manager.system.service.IListValuesService;
import com.oa.manager.system.service.IUserService;

/**
 * 
 * 类名：MyCache
 * 功能：自定义全局缓存
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-7 上午11:37:58
 *
 */

public class MyCache {
	
	@Autowired
	private IUserService userService; 
	@Autowired
	private IListValuesService listService; 
	@Autowired
	private IDataPermissionsService dataPermissionsService;
	
	/**
	 * 用户id-->用户信息对象  UserInfo
	 */
	public static final String USERID2INFO="userId2Info";
	
	/**
	 * 部门id-->名称
	 */
	public static final String DEPTID2NAME="deptId2Name";
	
	/**
	 * 角色id-->名称
	 */
	public static final String ROLE2NAME="roleId2Name";
	
	/**
	 * 字典值类型 type-->类型下字典值
	 */
	public static final String TYPE2LISTVALUES="type2ListValues";
	
	/**
	 * 字典值id-->字典值
	 */
	public static final String LISTID2NAME="listId2Name";
	/**
	 * 数据权限  type : json格式查询规则
	 */
	public static final String DATAPERMISSIONS="dataPermissions";
	
	@Autowired
	private CacheManager ehcacheManager;
	
	//私有的默认构造子
	private MyCache(){} 
	
	//获取spring创建的bean对象
    public synchronized static MyCache getInstance() {
    	
    	return  ServletUtil.getApplicationContext().getBean(MyCache.class);
       
    }
    /**
	 * 删除缓存信息
	 * @param cacheName 
	 * @param key
	 */
	public  void removeCache(String cacheName,Object key){
		ehcacheManager.getCache(cacheName).remove(key);
	}
	/**
	 * 根据用户id，获取用户名
	 * @param ids 用户id,多个用户id以,隔开
	 * @return
	 */
	public String getUserName(String ids){
		if(StringUtils.isBlank(ids)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.USERID2INFO);
		String[] sz_ids=ids.split(",");
		StringBuffer names=new StringBuffer();
		for(String id:sz_ids){
			UserInfo userInfo=getUserInfo(cache, id);
			if(userInfo!=null){
				names.append(userInfo.getUserName()+",");
			}
		
		}
		if("".equals(names.toString()))return "";
		return names.substring(0,names.lastIndexOf(","));
	}
	
	/**
	 * 根据用户id，获取用户姓名
	 * @param ids  用户id,多个用户id以,隔开
	 * @return
	 */
	public String getTrueName(String ids){
		if(StringUtils.isBlank(ids)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.USERID2INFO);
		String[] sz_ids=ids.split(",");
		StringBuffer names=new StringBuffer();
		for(String id:sz_ids){
			UserInfo userInfo=getUserInfo(cache, id);
			if(userInfo!=null){
				names.append(userInfo.getTrueName()+",");
			}
		
		}
		if("".equals(names.toString()))return "";
		return names.substring(0,names.lastIndexOf(","));
	}
	/**
	 * 根据用户id，获取部门名称
	 * @param id  
	 * @return
	 */
	public String getDeptNameByUserId(String id){
		if(StringUtils.isBlank(id)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.USERID2INFO);
		
		UserInfo userInfo=getUserInfo(cache, id);
		
		if(userInfo==null){
			return "";
		}else{
			return getDeptName(userInfo.getDeptId());
		}
	}
	/**
	 * 获取用户缓存对象
	 * @param cache
	 * @param id
	 * @return
	 */
	private UserInfo getUserInfo(Cache cache,String id){
		UserInfo info=null;
		Element element = cache.get(id);
		if(element==null){
			SyUsers user=userService.get(SyUsers.class, id);
			if(user!=null){
				info=new UserInfo();
				info.setUserName(user.getUserName());
				info.setTrueName(user.getTrueName());
				info.setDeptId(user.getDeptId());
				cache.put(new Element(id,info ));
			}
		}else{
			info=(UserInfo)element.getObjectValue();
		}
		return info;
	}
	/**
	 * 根据部门id，获取部门名称
	 * @param ids  部门id,多个部门id以,隔开
	 * @return
	 */
	public String getDeptName(String ids){
		if(StringUtils.isBlank(ids)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.DEPTID2NAME);
		String[] sz_ids=ids.split(",");
		StringBuffer names=new StringBuffer();
		for(String id:sz_ids){
			Element element = cache.get(id);
			if(element==null){
				SyDept dept=userService.get(SyDept.class, id);
				if(dept!=null){	
					cache.put(new Element(id,dept.getDeptName()));
					names.append(dept.getDeptName()+",");
				}
			}else{
				names.append(element.getObjectValue()+",");
			}
		}
		if("".equals(names.toString()))return "";
		return names.substring(0,names.lastIndexOf(","));
	}
	/**
	 * 根据角色id，获取角色名称
	 * @param ids	角色id,多个角色id以,隔开
	 * @return
	 */
	public String getRoleName(String ids){
		if(StringUtils.isBlank(ids)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.ROLE2NAME);
		String[] sz_ids=ids.split(",");
		StringBuffer names=new StringBuffer();
		for(String id:sz_ids){
			Element element = cache.get(id);
			if(element==null){
				SyRole role=userService.get(SyRole.class, id);
				if(role!=null){	
					cache.put(new Element(id,role.getRoleName()));
					names.append(role.getRoleName()+",");
				}
			}else{
				names.append(element.getObjectValue()+",");
			}
		}
		if("".equals(names.toString()))return "";
		return names.substring(0,names.lastIndexOf(","));
	}
	/**
	 * 根据字典值id，获取字典值
	 * @param id
	 * @return
	 */
	public String getSelectValue(String id){
		if(StringUtils.isBlank(id)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.LISTID2NAME);
		Element element=cache.get(id);
		if(element==null){
			ListValues v=listService.get(ListValues.class, id);
			if(v!=null){
				cache.put(new Element(id,v.getListValue()));
				return v.getListValue();
			}
		}else{
			return element.getObjectValue()+"";
		}
		return "";
	}
	/**
	 * 根据类型获取对应的数据字典集合
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getListValues(Integer type){
		
		if(type==null){
			return null;
		}
		Cache cache = ehcacheManager.getCache(MyCache.TYPE2LISTVALUES);
		Element element=cache.get(type);
		if(element==null){
			List<ListValues> values=listService.selectListByType(type);
			if(!values.isEmpty()){
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				for(ListValues v:values){
					Map<String,String> m=new HashMap<String,String>();
					m.put("value", v.getId());
					m.put("name", v.getListValue());
					list.add(m);
				}
				cache.put(new Element(type,list));
				return list;
			}
			
		}else{
			return (List<Map<String,String>>)element.getObjectValue();
		}
		return null;
	}
	/**
	 * 根据 数据查询规则类型 获得对应的json格式查询规则
	 * @param type
	 * @return
	 */
	public String getDataPermissions(String type){
		if(StringUtils.isBlank(type)){
			return "";
		}
		Cache cache = ehcacheManager.getCache(MyCache.DATAPERMISSIONS);
		Element element=cache.get(type);
		if(element==null){
			String rules=dataPermissionsService.selectRules(type);
			
			if(rules!=null){
				cache.put(new Element(type,rules));
				return rules;
			}
		}else{
			return (String)element.getObjectValue();
		}
		return null;
	}
	
	
	

}
