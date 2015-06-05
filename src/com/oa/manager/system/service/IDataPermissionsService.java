/**  
 * @Title: IDataPermissions.java
 * @date 2013-10-18 下午4:06:08
 * @Copyright: 2013 
 */
package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.manager.system.bean.SyDataPermissions;

/**
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public interface IDataPermissionsService extends IBaseService{
	

	/**
	 * 根据类型编码查询，如果不存在则创建一个并返回,存在则直接返回
	 * @param per
	 * @return
	 */
	public SyDataPermissions updateSelectOne(SyDataPermissions per);
	
	/**
	 * 修改
	 * @param per
	 * @return
	 */
	public String updateSyDataPermissions(SyDataPermissions per);
	
	/**
	 * 根据类型获取查询规则 如果没有查到此类型查询规则 则返回Null
	 * @param type
	 * @return
	 */
	public String selectRules(String type);
	
}
