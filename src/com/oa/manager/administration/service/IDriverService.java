/**  
 * @Project: jxoa
 * @Title: driverService.java
 * @Package com.oa.manager.administration.service
 * @date 2013-6-5 下午4:38:27
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.VmDrivertable;

/**
 * 
 * 类名：driverService
 * 功能：行政办公--车辆管理--驾驶员
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-5 下午4:38:27
 *
 */
public interface IDriverService extends IBaseService {
	/**
	 * 查询驾驶员的信息
	 * @param param
	 * @param vt
	 * @return
	 */
     public DataGrid load(PageParam param,VmDrivertable vt);
     
     /**
      * 批量删除
      * @param ids
      * @return
      */
     public boolean deletes(String[] ids);
     /**
      * 增加驾驶员 
      * @param vt
      * @return
      */
     public String adddriver(VmDrivertable vt);
     /**
      * 更新驾驶员
      * @param vt
      * @return
      */
     public String updateVd(VmDrivertable vt);
}
