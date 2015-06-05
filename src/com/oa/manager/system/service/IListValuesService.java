/**  
 * @Project: jxoa
 * @Title: IListValuesService.java
 * @Package com.oa.manager.system.service
 * @date 2013-4-15 下午2:36:33
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import java.util.List;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.ListValues;

/**
 * 
 * 类名：IListValuesService
 * 功能：字典值管理 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-15 下午2:36:33
 *
 */
public interface IListValuesService extends IBaseService{
	
	
	/**
	 * 字典值条件分页查询
	 * @param param
	 * @param lv
	 * @return
	 */
	public DataGrid selectListValues(PageParam param,ListValues lv);
	
	/**
	 * 添加字典值
	 * @param lv
	 * @return
	 */
	public String addListValues(ListValues lv);
	/**
	 * 修改字典值
	 * @param lv
	 * @return
	 */
	public String updateListValues(ListValues lv);
	/**
	 * 批量删除字典值
	 * @param ids
	 * @return
	 */
	public boolean deleteListValues(String[] ids);
	/**
	 *  查询出所有字典值
	 * @return
	 */
	public List<ListValues> selectAllListValues();
	
	/**
	 * 根据字典值类型查询字典值,用户表单的select列表取值
	 * @param type
	 * @return
	 */
	public List selectListByType(Integer type);
	
}
