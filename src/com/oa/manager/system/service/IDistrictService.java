/**  
 * @Project: jxoa
 * @Title: IDistrictService.java
 * @Package com.oa.manager.system.service
 * @date 2013-6-19 下午2:03:01
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import java.util.List;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDistrict;

/**
 * 
 * 类名：IDistrictService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-19 下午2:03:01
 *
 */
public interface IDistrictService extends IBaseService{
	
	/**
	 * 条件分页查询 地区
	 * @param param
	 * @param dis
	 * @return
	 */
	public DataGrid selectDistrict(PageParam param,SyDistrict dis);
	
	/**
	 * 添加地区
	 * @param dis
	 * @return
	 */
	public String addDistrict(SyDistrict dis);
	/**
	 * 修改
	 * @param dis
	 * @return
	 */
	public String updateDistrict(SyDistrict dis);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean deleteDistrict(String[] ids);
	/**
	 * 查询出所有地区
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List selectAllDistrict();
	/**
	 * 根据id查询出下级地区
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List selectDistrictBySuperId(int id);
	
	
}
