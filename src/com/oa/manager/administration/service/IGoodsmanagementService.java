/**  
 * @Project: jxoa
 * @Title: IGoodsmanagementService.java
 * @Package com.oa.manager.administration.service
 * @date 2013-6-17 下午4:54:38
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.BiBasicinformation;

/**
 * 
 * 类名：IGoodsmanagementService
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-17 下午4:54:38
 *
 */
public interface IGoodsmanagementService  extends IBaseService  {
	/**
	 * 查询物品管理
	 * @param bi
	 * @param param
	 * @return
	 */
	public DataGrid load(BiBasicinformation bi,PageParam param);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean deletes(String[] ids);
	/**
	 * 更新物品管理
	 * @param bi
	 * @return
	 */
	public String updateBi(BiBasicinformation bi);
}
