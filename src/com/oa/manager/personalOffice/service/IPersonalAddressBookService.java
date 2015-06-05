/**  
 * @Project: jxoa
 * @Title: IPersonal.java
 * @Package com.oa.manager.personalOffice.addressBook.Personal.service
 * @date 2013-5-21 下午02:24:10
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.PersonalAddressBook;

/**
 * 
 * 类名：IPersonalAddressBookService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-21 下午02:24:10
 *
 */
public interface IPersonalAddressBookService extends IBaseService{
	/**
	 * 个人通讯录条件查询
	 * @param param
	 * @return
	 */
	public DataGrid selectPersonal(PageParam param,PersonalAddressBook p);
	public PersonalAddressBook selectPersonal(String id);
	/**
	 * 添加个人通讯录
	 * @return
	 */
	public String addPersonal(PersonalAddressBook p);
	/**
	 * 修改个人通讯录
	 * @return
	 */
	public String updatePersonal(PersonalAddressBook p);
	/**
	 * 批量删除个人通讯录
	 * @param ids
	 * @return
	 */
	public boolean deletePersonal(String[] ids);
	
	/**
	 * 查询个人通讯录的分组
	 * 
	 * */
	public List selectGroup();
	
//***********************************安卓********************************************
	/**
	 * 根据分组id查询该组所有通讯录成员
	 * @param groupId  分组id
	 * @return
	 */
	public List<Map<String,Object>> queryPersonByGroup(String groupId,PageParam page);
	/**
	 * 根据分组id查询该组成员总数
	 * @param groupId 分组id
	 * @return
	 */
	public Long totlePersonByGroup(String groupId);
	/**
	 * 条件搜索个人通讯录
	 * @param phone 手机
	 * @param name  姓名
	 * @return
	 */
	public List<Map<String,Object>> selectPersonByConditions(String phone,String name,PageParam param);
	
	public Long totlePersonBookByConditions(String phone,String name);
}
