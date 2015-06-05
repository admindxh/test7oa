/**  
 * @Project: jxoa
 * @Title: IRoleService.java
 * @Package com.oa.manager.system.service
 * @date 2013-4-28 下午2:57:40
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyRole;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：IRoleService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-28 下午2:57:40
 *
 */
public interface IRoleService extends IBaseService{
	/**
	 * 查询出所有角色
	 * @return
	 */
	public List selectAllRoles();
	/**
	 * 角色条件查询
	 * @param param
	 * @param role
	 * @return
	 */
	public DataGrid selectRoles(PageParam param,SyRole role);
	/**
	 * 角色条件查询  计算总数
	 * @param role
	 * @return
	 */
	public Long selectRolesCount(SyRole role);
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public String addRole(SyRole role);
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public String updateRole(SyRole role);
	/**
	 * 批量删除角色
	 * @param ids
	 * @return
	 */
	public boolean deleteRoles(String[] ids);
	/**
	 * 查询出角色已有权限，所有菜单，操作
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map selectPowers(String id);
	/**
	 * 更新角色的权限
	 * @param roleId
	 * @param addMenuIds
	 * @param delMenuIds
	 * @param addActionIds
	 * @param delActionIds
	 * @return
	 */
	public boolean updatePowers(String roleId,String addMenuIds,String delMenuIds,String addActionIds,String delActionIds);
	
	/**
	 * 权限分配 查询用户
	 * @param param
	 * @param roleId
	 * @param user
	 * @return
	 */
	public DataGrid selectUsers(PageParam param,String roleId,SyUsers user);

	/**
	 * 将角色批量分配给用户
	 * @param roleId
	 * @param ids
	 * @return
	 */
	public boolean addUserRole(String roleId,String[] ids);
	
	/**
	 * 角色分配管理 查询出角色已分配的用户
	 * @param param
	 * @param roleId
	 * @param user
	 * @return
	 */
	public DataGrid selectRoleUsers(PageParam param,String roleId,SyUsers user);
	/**
	 * 角色分配管理，删除用户
	 * @param ids
	 * @return
	 */
	public boolean delUserRole(String[] ids);
	/**
	 * 根据用户id 获取角色集合
	 * @param userId
	 * @return
	 */
	public List<String> selectRolesByUserId(String userId);
	
	
	
}
