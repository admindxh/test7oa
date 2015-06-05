package com.oa.manager.crm.sell.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.crm.sell.bean.Chance;

/**
 * 
 * 类名：IChanceService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年12月11日 09:04:54
 *
 */
public interface IChanceService extends IBaseService{
	/**
	 * 销售机会条件查询
	 * @param param
	 * @return
	 */
	public DataGrid selectChance(PageParam param,Chance c);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Chance selectID(String id);
	/**
	 * 添加销售机会
	 * @return
	 */
	public String addChance(Chance c);
	/**
	 * 修改销售机会
	 * @return
	 */
	public String updateChance(Chance c);
	/**
	 * 批量删除销售机会
	 * @param ids
	 * @return
	 */
	public boolean deleteChance(String[] ids);
	/**
	 * 分配销售机会
	 * @return
	 */
	public String updateAllocation(String id,String scStaff,String scBranch,String scPicture);
	

	/**
	 * 查询销售机会用于查找带回
	 * @param param
	 * @param o
	 * @return
	 */
	public DataGrid lookUpChance(PageParam param,Chance c);
	
}
