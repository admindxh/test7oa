/**  
 * @Project: jxoa
 * @Title: IPublic.java
 * @Package com.oa.manager.PublicOffice.addressBook.Public.service
 * @date 2013-5-21 下午02:24:10
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service;

import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.GroupAddressBook;

/**
 * 
 * 类名：IPublicAddressBookService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-23 下午02:24:10
 *
 */
public interface IGroupAddressBookService extends IBaseService{
	/**
	 * 通讯录分组条件查询
	 * @param param
	 * @return
	 */
	public DataGrid selectGroup(PageParam param,GroupAddressBook g);
	public GroupAddressBook  selectGroup(String id);
	/**
	 * 添加通讯录分组
	 * @return
	 */
	public String addGroup(GroupAddressBook g);
	/**
	 * 修改通讯录分组
	 * @return
	 */
	public String updateGroup(GroupAddressBook g);
	/**
	 * 批量删除通讯录分组
	 * @param ids
	 * @return
	 */
	public String deleteGroup(String[] ids);
	
//*************************************安卓*****************************************
	/**
	 * 分页查询个人通讯录分组
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectAllPersonGroup(PageParam param);
	/**
	 * 查询个人通讯录总数
	 * @return
	 */
	public Long totlePersonGroup();
}
