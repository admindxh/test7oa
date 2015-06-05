/**  
 * @Project: jxoa
 * @Title: IFormService.java
 * @Package com.oa.manager.workFlow.service
 * @date 2013-7-7 下午2:27:15
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfForm;

/**
 * 
 * 类名：IFormService
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-7 下午2:27:15
 *
 */
public interface IFormService extends IBaseService{

	/**
	 * 条件分页查询表单
	 * @return
	 */
	public DataGrid selectWfForm(PageParam param,WfForm wf);
	
	
	/**
	 * 添加
	 * @param wf
	 * @return
	 */
	public String addWfForm(WfForm wf);
	
	/**
	 * 修改
	 * @param wf
	 * @return
	 */
	public String updateWfForm(WfForm wf);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean deleteWfForm(String[] ids);
	
	
	
	
}
