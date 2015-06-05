/**
 * 
 */
package com.oa.manager.personnel.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personnel.bean.Job;

/**
 * 
 * 类名：IJobService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年10月9日 13:58:44
 *
 */
public interface IJobService  extends IBaseService{

	/**
	 * 加载简历信息
	 * @return
	 */
	public DataGrid selectJob(PageParam param, Job j );
	
	public Job selectID(String id);
	/**
	 * 添加简历信息
	 * @param s
	 * @return
	 */
	public  String add(Job j);

	/**
	 *  修改简历信息
	 * @param s
	 * @return
	 */
	public String update(Job s);
	/**
	 * 删除简历信息
	 * @param ids
	 * @return
	 */
	public boolean delete(String[] ids);
	
}
