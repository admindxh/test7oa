package com.oa.commons.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.oa.manager.system.bean.SyTableCustom;

/**
 * @author LiuJincheng
 * 
 * @desc	基本服务层接口
 *
 */

public interface IBaseService {
	/**
	 * 添加	保存对象
	 * @param 	obj		需要添加的对象
	 * @return boolean	返回保存是否成功
	 */
	public boolean save(Object obj);
	
	/**
	 * 添加或更新
	 * 
	 * @param	obj		需要添加或更新的对象
	 * @return	boolean 添加成功或失败
	 */
	
	public boolean saveOrUpdate(Object o);
	/**
	 * 批量添加或更新
	 * 
	 * @param	obj		需要添加或更新的对象集合
	 * @return	boolean 添加成功或失败
	 */
	public boolean saveOrUpdateAll(Collection<?> c);
	/**
	 * 更新对象 根据对象id 
	 * 
	 * @param obj	对象
	 * @return boolean 	更新成功或失败
	 */
	public boolean update(Object o);
	/**
	 * 删除一个对象
	 * @param obj	对象
	 * @return	boolean	删除成功或失败
	 */
	public boolean delete(Object o);
	/**
	 * 删除一个集合里面的全部对象
	 * 
	 * @param c 对象集合
	 * @return boolean 删除成功或失败
	 */
	public boolean deleteAll(Collection<?> c);
	/**
	 * 单个 查询	根据类型 id	 get方法
	 * @param <T>
	 * 
	 * @param 	c 	对象Class
	 * @param 	id 	对象ID
	 * @return	Object	返回数据对象
	 */
	public <T> T get(Class<T> c, Serializable id);

	
	/**
	 * 根据类型查询出可导出的自定义属性
	 * @param type
	 * @return
	 */
	public List<SyTableCustom> findTableCustomExport(short type);
	/**
	 * 根据类型查询出可打印的自定义属性
	 * @param type
	 * @return
	 */
	public List<SyTableCustom> findTableCustomPrint(short type);
	/**
	 * 根据类型查询出可显示的自定义属性
	 * @param type
	 * @return
	 */
	public List<SyTableCustom> findTableCustomShow(short type);
	
	
}
