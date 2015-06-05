/**  
 * @Project: jxoa
 * @Title: ISystemLogService.java
 * @Package com.oa.manager.system.service
 * @date 2013-4-11 下午3:48:00
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import java.util.Date;
import java.util.List;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyLog;
import com.oa.manager.system.bean.SyLoginLog;

/**
 * 
 * 类名：ISystemLogService
 * 功能：系统登录日志 系统重要操作日志 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-11 下午3:48:00
 *
 */
public interface ISystemLogService extends IBaseService{
	
	/**
	 * 条件分页查询登录日志
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectSyLoginLog(PageParam param,SyLoginLog log,Date startDate,Date endDate,boolean isUserName);
	/**
	 * 根据id 获取登陆日志
	 * @param ids
	 * @return
	 */
	public List<SyLoginLog> selectSyLoginLogs(String[] ids);
	/**
	 * 批量删除登录日志
	 * @param ids 
	 * @return
	 */
	public boolean deleteLoginLog(String[] ids);
	
	/**
	 * 条件分页查询操作日志
	 * @param param
	 * @param log
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectSyLog(PageParam param,SyLog log,Date startDate,Date endDate);
	
	/**
	 * 批量删除操作日志
	 * @param ids
	 * @return
	 */
	public boolean deleteLog(String[] ids);
	
	
}
