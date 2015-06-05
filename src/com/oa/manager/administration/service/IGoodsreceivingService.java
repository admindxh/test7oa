/**  
 * @Project: jxoa
 * @Title: IGoodsreceiving.java
 * @Package com.oa.manager.administration.service
 * @date 2013-6-18 下午2:43:59
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service;

import java.util.Date;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.BiGoodsreceiving;

/**
 * 
 * 类名：IGoodsreceiving
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-18 下午2:43:59
 *
 */
public interface IGoodsreceivingService  extends IBaseService {
	/**
	 * 增加物品领用
	 * @param bv
	 * @return
	 */
	public String savereceving(BiGoodsreceiving bv);
	
	/**
	 * 查询物品领用
	 * @param bv
	 * @param param
	 * @return
	 */
	public DataGrid load(BiGoodsreceiving bv,PageParam param,Date startDate,Date endDate);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public String deletes(String[] ids);
	
	/**
	 * 修改物品领用
	 * @param bv
	 * @param num
	 * @return
	 */
	public String updatereceving(BiGoodsreceiving bv);
}
