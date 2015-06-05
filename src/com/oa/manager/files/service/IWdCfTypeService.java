/**  
 * @Title IWdCfTypeService.java
 * @date 2013-10-28 下午4:32:52
 * @Copyright: 2013 
 */
package com.oa.manager.files.service;

import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.files.bean.TypePowersList;
import com.oa.manager.files.bean.WdCfType;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public interface IWdCfTypeService extends IBaseService{
	/**
	 * 查询所有的文档类型
	 * @return
	 */
	public List selectAllTypes();
	/**
	 * 查询当前用户有访问权限的文档类型
	 * @return
	 */
	public List selectMyTypes();
	/**
	 * 分页条件查询 文档类型
	 * @param param
	 * @param t
	 * @return
	 */
	public DataGrid selectWdCfTypes(PageParam param,WdCfType t);
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	public String addWdCfType(WdCfType t,TypePowersList powers);
	/**
	 * 文件类型查询，同时查询出关联的权限 用于修改页面
	 * @param id
	 * @return
	 */
	public Map selectOneType(String id);
	/**
	 * 根据id删除一条权限  权限--用户关联表 主键关联 数据库级联删除
	 * @param id
	 * @return
	 */
	public boolean deletePower(String id);
	/**
	 * 修改
	 * @param t
	 * @param powers
	 * @return
	 */
	public String updateWdCfType(WdCfType t,TypePowersList powers);
	/**
	 * 批量删除文档分类
	 * @param ids
	 * @return
	 */
	public boolean deleteWdCfTypes(String[] ids);
}
