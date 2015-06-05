/**
 * 
 */
package com.oa.manager.personnel.service;

import java.util.Date;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personnel.bean.Staff;

/**
 * 
 * 类名：StaffServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年6月8日 15:23:23
 *
 */
public interface IStaffService  extends IBaseService{

	/**
	 * 加载在职员工信息
	 * @return
	 */
	public DataGrid selectStaff(PageParam param, Staff s,Date staDate,Date endDate,String status);
	

	/**
	 * 添加员工信息
	 * @param s
	 * @return
	 */
	public  String addStaff(Staff s);
	/**
	 * 加载修改信息
	 * @param id
	 * @return
	 */
	public Map updatePage(String id);
	/**
	 *  修改员工信息
	 * @param s
	 * @return
	 */
	public String update(Staff s);
	/**
	 * 删除员工信息
	 * @param ids
	 * @return
	 */
	public boolean deleteStaff(String[] ids);
	/**
	 *  修改员工状态
	 * @param s
	 * @return
	 */
	public String updateStatus(Staff s);
}
