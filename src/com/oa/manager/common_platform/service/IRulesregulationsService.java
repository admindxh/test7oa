/**  
 * @Project: jxoa
 * @Title: Irulesregulations.java
 * @Package com.oa.manager.common_platform.service
 * @date 2013-5-31 上午11:34:59
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.RgRulesregulations;

/**
 * 
 * 类名：Irulesregulations
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-5-31 上午11:34:59
 *
 */
public interface IRulesregulationsService extends IBaseService {
	/**
	 * 查找规章制度
	 * @param rg
	 * @param param
	 * @return
	 */
     public DataGrid selectrules(RgRulesregulations rg,PageParam param);
    
     /**
      * 批量删除
      * @param ids
      * @return
      */
     public boolean delete(String[] ids);
     /**
      * 修南规章
      * @param rg
      * @return
      */
     public String updaterg(RgRulesregulations rg);
}
