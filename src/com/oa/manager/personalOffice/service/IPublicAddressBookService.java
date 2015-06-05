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
import com.oa.manager.personalOffice.bean.PublicAddressBook;
import com.oa.manager.personalOffice.bean.PublicGroup;

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
public interface IPublicAddressBookService extends IBaseService{
	/**
	 * 公共通讯录条件查询
	 * @param param
	 * @return
	 */
	public DataGrid selectPublic(PageParam param,PublicAddressBook p);
	public PublicAddressBook selectPublic(String id);
	/**
	 * 添加公共通讯录
	 * @return
	 */
	public String addPublic(PublicAddressBook p);
	/**
	 * 修改公共通讯录
	 * @return
	 */
	public String updatePublic(PublicAddressBook p);
	/**
	 * 批量删除公共通讯录
	 * @param ids
	 * @return
	 */
	public boolean deletePublic(String[] ids);
	
	
	/**
	 * 导入公共通讯录
	 * @return
	 */
	public String addExcelPublic(List<PublicAddressBook> list,String group);
	/**
	 * 查询分组 
	 * @return
	 */
	public List<PublicGroup> selectGroup();
	
	//**********************************************安卓*************************************************
	/**
	 * 根据分组id查询该组所有通讯录成员
	 * @param groupId  公司通讯录分组id
	 * @return
	 */
	public List<Map<String,Object>> selectPublicBookByGroupId(String groupId,PageParam param);
	/**
	 * 根据分组id查询该组成员总数
	 * @param groupId 分组id
	 * @return
	 */
	public Long totlePublicBookByGroupId(String groupId);
	/**
	 * 条件搜索公司通讯录
	 * @param phone 手机
	 * @param name 姓名
	 * @return
	 */
	public List<Map<String,Object>> selectPublicBookByConditions(String phone,String name,PageParam param);
	/**
	 * 条件搜索公司通讯录总数
	 * @param phone 手机
	 * @param name   姓名
	 * @return
	 */
	public Long totlePublicBookByConditions(String phone,String name);
}
