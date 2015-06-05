/**  
 * @Project: jxoa
 * @Title: IPurchasegoodsService.java
 * @Package com.oa.manager.administration.service
 * @date 2013-6-18 下午2:41:23
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service;

import java.util.Date;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.BiShopping;

/**
 * 
 * 类名：IPurchasegoodsService
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-18 下午2:41:23
 *
 */
public interface IPurchasegoodsService extends IBaseService {
	/**
	 * 查看物品购买清单
	 * @param bs
	 * @param param
	 * @return
	 */
	
	public DataGrid load(BiShopping bs,PageParam param,Date startDate,Date endDate);
	
	/**
	 * 增加物品购买
	 * @param bs
	 * @return
	 */
    public String saveshopping(BiShopping bs);
   
    /**
     * 批量删除物品购买
     * @param ids
     * @return
     */
    public boolean deletes(String[] ids);
    /**
     * 修改物品购买
     * @param bs
     * @return
     */
    public String updateshop(BiShopping bs);
   

}
