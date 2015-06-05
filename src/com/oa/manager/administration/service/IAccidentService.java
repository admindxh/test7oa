package com.oa.manager.administration.service;

import java.util.List;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.VmAccident;

/**
 * 
 * 类名：AccidentAction
 * 功能：行政办公---车辆管理 --事故管理
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-6 下午3:02:59
 *
 */
public interface IAccidentService extends IBaseService  {
	/**
	 * 查看事故
	 * @param vd
	 * @param param
	 * @return
	 */
     public DataGrid loadaccident(VmAccident vd,PageParam param);
     
     /**
      * 批量删除事故
      * @param ids
      * @return
      */
     public boolean deletes(String[] ids);
     /**
      * 更新时查找
      * @param id
      * @return
      */
     public List  updateselect(String id);
     /**
      * 更新事故
      * @param vd
      * @return
      */
     public String updateVd(VmAccident vd);
}
