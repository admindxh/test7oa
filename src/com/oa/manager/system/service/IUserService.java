/**  
 * @Project: jxoa
 * @Title: IUserService.java
 * @Package com.oa.manager.system.service
 * @date 2013-3-29 下午2:20:05
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyUsers;


/**
 * 
 * 类名：IUserService
 * 功能：用户管理 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-3-29 下午2:20:05
 *
 */
public interface IUserService extends IBaseService{
	
	/**
	 * 用户查询
	 * @param param
	 * @param user
	 * @return
	 */
	public DataGrid selectUsers(PageParam param,SyUsers user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public String addUser(SyUsers user);
	
	/**
	 * 导入用户
	 * @param users
	 * @return
	 */
	public String addUsers(List<Map> excel);


	/**
	 * 更新用户信息
	 * @param u
	 * @return
	 */
	public String updateUser(SyUsers u);
	/**
	 * 批量删除用户
	 * @param id
	 * @return
	 */
	public boolean deleteUser(String[] id);
	
	/**
	 * 根据用户id查询用户所有角色
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List selectUserRoles(String userId);
	
	/**
	 * 查询出所有角色，和用户已有角色，用于用户角色更新
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map selectUserRolesAndIds(String userId);
	/**
	 * 用户角色分配
	 * @param userId
	 * @param addRoleIds
	 * @param delRoleIds
	 * @return
	 */
	public boolean updateUserRoles(String userId,String[] addRoleIds,String[] delRoleIds);
	/**
	 * 查询某用户拥有的所有权限
	 * @param userId
	 * @return
	 */
	public Map<String,Object> selectUserPowers(String userId);
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	public List<SyUsers> selectAllUsers();
	/**
	 * 重置用户密码
	 * @param id
	 * @param pw
	 * @return
	 */
	public boolean updatePassword(String id,String userPassword);
	/**
	 * 当前用户修改密码
	 * @param oldPassword
	 * @param userPassword
	 * @param pw
	 * @return
	 */
	public boolean updateMyPassword(String oldPassword,String userPassword);
	/**
	 * 查找带回 用户查询
	 * @param param
	 * @param user
	 * @return
	 */
	public DataGrid selectUsersLookUp(PageParam param,SyUsers user);
	/**
	 * 查找带回 查询手机号码
	 * @param param
	 * @param user
	 * @return
	 */
	public DataGrid selectUsersLookUpNumber(PageParam param,SyUsers user);
	/**
	 * 根据用户 登陆名称 查找用户
	 * @param name
	 * @return
	 */
	public SyUsers findUserByLoginName(String name);
	/**
	 * 查询用户id ,查询拥有的角色id，和可以访问的urls
	 * @param id 用户id
	 * @return   key: roleIds:角色id集合,powers：访问权限url集合
	 */
	public Map<String,Collection<String>> selectRolesPowers(String id);
//************************************安卓****************************************	
	/**
	 * 查询全部可用用户
	 * @param param
	 * @return mfh
	 */
	public List<Map<String,Object>> queryAllUsers(PageParam param);
	/**
	 * 查询可用用户的总数
	 * @return mfh
	 */
	public Long totleUsers();
	
	
	/**
	 * 条件搜索可用用户
	 * @param deptId  部门ID  
	 * @param page  当前页 
	 * @param rows  每页显示的行数
	 * @param name  姓名
	 * @return  mfh
	 */
	public List<Map<String,Object>> queryUserConditions(String deptId,String name,PageParam param);
	/**
	 * 条件搜索可用用户总数
	 * @param deptId  部门id
	 * @param name  姓名
	 * @return mfh
	 */
	public Long totleUserConditions(String deptId,String name);
	
}
