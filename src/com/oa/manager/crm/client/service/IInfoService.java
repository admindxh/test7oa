/**  
 * @Project: jxoa
 * @Title: IPublic.java
 * @Package com.oa.manager.PublicOffice.addressBook.Public.service
 * @date 2013-5-21 下午02:24:10
 * @Copyright: 2013 
 */
package com.oa.manager.crm.client.service;

import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.crm.client.bean.Info;

/**
 * 
 * 类名：IInfoService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年11月30日 11:11:26
 *
 */
public interface IInfoService extends IBaseService{
	/**
	 * 客户信息条件查询
	 * @param param
	 * @return
	 */
	public DataGrid selectInfo(PageParam param,Info o);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Info selectID(String id);
	/**
	 * 添加客户信息
	 * @return
	 */
	public String addInfo(Info o);
	/**
	 * 修改客户信息
	 * @return
	 */
	public String updateInfo(Info o);
	/**
	 * 批量删除客户信息
	 * @param ids
	 * @return
	 */
	public boolean deleteInfo(String[] ids);
	/**
	 * 修改客户信息
	 * @return
	 */
	public String updateShift(String id,String clStaff);
	
	/**
	 * 客户共享
	 * @return
	 */
	public String addShare(String id,String deptid,String userid);
	/**
	 * 	共享客户查询显示
	 * @param param
	 * @param o
	 * @return
	 */
	public DataGrid selectShare(PageParam param, Info o) ;
	/**
	 * 根据id查询客户共享信息
	 * @param id
	 * @return
	 */
	public Map selectShare(String id);
	/**
	 * 修改客户共享
	 * @return
	 */
	public String updateShare(String id,String deptid,String userid);
	/**
	 * 查询客户信息用于查找带回
	 * @param param
	 * @param o
	 * @return
	 */
	public DataGrid lookUpInfo(PageParam param,Info o);
	
}
