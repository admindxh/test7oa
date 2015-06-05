/**  
 * @Project: jxoa
 * @Title: IAfficheService.java
 * @Package com.oa.manager.common_platform.service
 * @date 2013-5-31 上午9:59:27
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.XtAffiche;
import com.oa.manager.common_platform.bean.XtAfficheDept;

/**
 * 
 * 类名：IAfficheService
 * 功能：公共平台--公告管理业务层接口
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-31 上午9:59:27
 *
 */
public interface IAfficheService extends IBaseService{
	
	/**
	 * 条件分页查询 系统公告
	 * @param param
	 * @param a
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectAffiche(PageParam param,XtAffiche a,Date startDate,Date endDate);

	/**
	 * 更新系统公告
	 * @param xa
	 * @return
	 */
	public String updateAffiche(XtAffiche xa);
	/**
	 * 删除系统公告
	 * @param ids
	 * @return
	 */
	public boolean deleteAffiche(String[] ids);
	

	/**
	 * 条件分页查询 部门公告
	 * @param param
	 * @param a
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectAfficheDept(PageParam param,XtAfficheDept a,Date startDate,Date endDate);
	/**
	 * 查询 当前用户能看到的部门公告 非管理模块
	 * @param param
	 * @param a
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectMyAfficheDept(PageParam param,XtAfficheDept a,Date startDate,Date endDate);

	/**
	 * 更新部门公告
	 * @param xa
	 * @return
	 */
	public boolean updateAfficheDept(XtAfficheDept xa);
	/**
	 * 删除部门公告
	 * @param ids
	 * @return
	 */
	public boolean deleteAfficheDept(String[] ids);
	
	//****************************安卓***********************************
	/**
	 * 分页查询系统公告
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> querySystemNotic(PageParam param);
	/**
	 * 查询系统公告总数
	 * @return
	 */
	public Long totleSystemNotic();
	/**
	 * 根据id查询系统公告详情
	 * @param id 系统公告id
	 * @return
	 */
	public Map<String,Object> updateSystemNoticById(String id);
	/**
	 * 分页查询部门公告
	 * @return
	 */
	public List<Map<String,Object>> selectDepartmentNotic(PageParam param);
	/**
	 * 查询部门公告总数
	 * @return
	 */
	public Long totleDepartmentNotic();
	/**
	 * 根据部门id查询部门公告详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> selectDepartmentById(String id);
	
}
