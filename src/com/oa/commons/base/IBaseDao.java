package com.oa.commons.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author LiuJincheng
 * 数据库操作封装 接口
 * 
 */
public interface IBaseDao {
	
	/**
	 * 添加	保存对象
	 * @param 	obj		需要添加的对象
	 * @return boolean	返回保存是否成功
	 */
	public boolean save(Object obj);
	
	/**
	 * 添加  需要返回添加之后的id
	 * @param 	obj		需要添加的对象
	 * @return  Object 	返回保存的对象生成的主键
	 */
	@Deprecated
	public Serializable saveReturnId(Object obj);
	
	/**
	 * 添加或更新
	 * 
	 * @param	obj		需要添加或更新的对象
	 * @return	boolean 添加成功或失败
	 */
	public boolean saveOrUpdate(Object obj);

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
	public boolean update(Object obj);
	/**
	 * 更新 传入 更新语句	例：	update Test t set t.name=?,t.password=? where t.id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(可变参)
	 * @return boolean 	更新成功或失败
	 */
	public boolean update(String hql, Object... values);
	/**
	 * 更新 	自定义HQL语句	例：	update Test t set t.name=?,t.password=? where t.id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(List参数  list 索引对应 ？占位符位置)
	 * @return boolean 	更新成功或失败
	 */
	public boolean update(String hql, List<?> values);
	
	
	/**
	 * 删除一个对象
	 * @param obj	对象
	 * @return	boolean	删除成功或失败
	 */
	public boolean delete(Object obj);

	/**
	 * 删除	自定义HQL语句	例：	delete Test where id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(可变参)
	 * @return boolean 	更新成功或失败
	 */
	public boolean delete(String hql, Object... values);
	/**
	 * 删除	自定义HQL语句	例：	delete Test where id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(List参数  list 索引对应 ？占位符位置)
	 * @return boolean 	更新成功或失败
	 */
	public boolean delete(String hql, List<Object> values);
	/**
	 * 删除一个集合里面的全部对象
	 * 
	 * @param c 对象集合
	 * @return boolean 删除成功或失败
	 */
	public boolean deleteAll(Collection<?> c);
	
	/**
	 * 单个查询		根据类型 id	get方法
	 * @param <T>
	 * 
	 * @param 	c 	对象Class
	 * @param 	id 	对象ID
	 * @return	Object	返回数据对象
	 */
	public <T> T get(Class<T> c, Serializable id);
	

	/**
	 * 单个查询		根据类型 id	post方法
	 * @param <T>
	 * 
	 * @param 	c 	对象Class
	 * @param 	id 	对象ID
	 * @return	Object	返回数据对象
	 */
	public <T> T load(Class<T> c, Serializable id);
	/**
	 * 结果可以有多条，但只查询第一条，使用此方法，	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(可变参)
	 * @return Object  无查询结果返回null
	 */
	public Object findOne(String hql,  Object... values);
	/**
	 * 结果可以有多条，但只查询第一条，使用此方法，   例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(List参数)
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findOne(String hql, List<Object> values);
	/**
	 * 结果可以有多条，但只查询第一条，使用此方法，   例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(Map 类型参数 )
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findOne(String hql,Map<String,Object> values);
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法		自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(可变参)
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findUniqueOne(String hql,Object... values);
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法  	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(List参数)
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findUniqueOne(String hql, List<Object> values);
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法  	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(Map 类型参数 )
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findUniqueOne(String hql,Map<String,Object> values);
	
	/**
	 * 多条查询	 无参的
	 * 
	 * @param hql 查询语句
	 * @return List	查询结果集
	 */
	
	@SuppressWarnings("rawtypes")
	public List find(String hql);
	
	/**
	 * 多条查询	 可变参
	 * 
	 * @param hql 查询语句
	 * @param values 参数
	 * @return List	查询结果集
	 */
	
	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... values);
	
	/**
	 * 多条查询	 list 参数
	 * 
	 * @param hql 查询语句
	 * @param values 参数
	 * @return List	查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, List<Object> values);
	/**
	 * 查询 	map 参数  别名占位，如果参数是集合，数组类型，使用此方法
	 * 
	 * 例：from user where age in(:age);		map: key:别名, value: 集合或数组
	 * 
	 * @param hql 	查询语句
	 * @param page 	当前页
	 * @param rows	每页显示记录数
	 * @param values 参数
	 * @return List 查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, Map<String,Object> values);
	/**
	 * 分页查询 	list参数
	 * 
	 * @param hql 	查询语句
	 * @param page 	当前页
	 * @param rows	每页显示记录数
	 * @param values 参数
	 * @return List 查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List findPage(String hql, int page, int rows, List<Object> values);


	/**
	 * 分页查询 	可变参
	 * 
	 * @param hql 	查询语句
	 * @param page 	当前页
	 * @param rows	每页显示记录数
	 * @param values 参数
	 * @return List 查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List findPage(String hql, int page, int rows, Object... values);

	
	/**
	 * 分页查询 	map 参数  别名占位，如果参数是集合，数组类型，使用此方法
	 * 
	 * 例：from user where age in(:age);		map: key:别名, value: 集合或数组
	 * 
	 * @param hql 	查询语句
	 * @param page 	当前页
	 * @param rows	每页显示记录数
	 * @param values 参数
	 * @return List 查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List findPage(String hql, int page, int rows, Map<String,Object> values);

}
