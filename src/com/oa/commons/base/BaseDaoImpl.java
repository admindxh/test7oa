package com.oa.commons.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/** 
 * @author LiuJincheng
 * 数据库操作封装基类 
 * 封装了HibernateTemplate 一些繁琐操作
 * 快速开发可只需要这一个dao service直接调用此dao传sql语句
 * 
 */
@Repository
public class BaseDaoImpl implements IBaseDao{
	
	private static Logger logger = Logger.getLogger(BaseDaoImpl.class);

	@Autowired 
	private SessionFactory sessionFactory; 
	 
	public Session getSession(){
	
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 添加	保存对象
	 * @param 	obj		需要添加的对象
	 * @return boolean	返回保存是否成功
	 */
	public boolean save(Object o){
		try{
			
			Serializable id=getSession().save(o);
			
			if(id!=null&&id.toString().length()!=0&&!id.toString().equals("0")){
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return false;
		}
	}
	/**
	 * 添加  方法已过时
	 * 说明，可直接使用save方法，会自动的将生成的数据set到对象里面，不需要单独的使用
	 * @param 	obj		需要添加的对象
	 * @return 	Object 	返回保存的对象生成的主键
	 */
	@Deprecated
	public Serializable saveReturnId(Object o) {
		return  getSession().save(o);
	}
	
	/**
	 * 添加或更新
	 * 
	 * @param	obj		需要添加或更新的对象
	 * @return	boolean 添加成功或失败
	 */
	
	public boolean saveOrUpdate(Object o) {
		try{
			getSession().saveOrUpdate(o);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return false;
		}
	}
	/**
	 * 批量添加或更新
	 * 批量操作 需要设置 hibernate.jdbc.batch_size=20
	 * 如果对象采用"identity"标识符生成器，则Hibernate无法在JDBC层进行批量插入操作。
	 * 进行批量操作时，建议关闭Hibernate的第二级缓存 hibernate.cache.use_second_level_cache=false
	 * @param	obj		需要添加或更新的对象集合
	 * @return	boolean 添加成功或失败
	 */
	public boolean saveOrUpdateAll(Collection<?> c) {
		try{
			Session session=getSession();
			int i=0;
			for(Object o:c){
				session.saveOrUpdate(o);
				if ( i % 20 == 0 ) { //单次批量操作的数目为20
					session.flush(); //清理缓存，执行批量插入20条记录的SQL insert语句
					session.clear(); //清空缓存中的对象
				}
				i++;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return false;
		}
	}
	/**
	 * 更新对象 根据对象id 
	 * 
	 * @param obj	对象
	 * @return boolean 	更新成功或失败
	 */
	public boolean update(Object o) {
		try{
			getSession().update(o);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return false;
		}
	}
	/**
	 * 更新 传入 更新语句	例：	update Test t set t.name=?,t.password=? where t.id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(可变参)
	 * @return boolean 	更新成功或失败
	 */
	public boolean update( String hql,Object... values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i, values[i]);
			}
		}
		return q.executeUpdate()!=0 ? true: false;
	}
	/**
	 * 更新 	自定义HQL语句 	例：	update Test t set t.name=?,t.password=? where t.id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(List参数  list 索引对应 ？占位符位置)
	 * @return boolean 	更新成功或失败
	 */
	public boolean update(String hql,List<?> values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i, values.get(i));
			}
		}
		return q.executeUpdate()!=0 ? true: false;
	}

	/**
	 * 删除一个对象
	 * @param obj	对象
	 * @return	boolean	删除成功或失败
	 */
	public boolean delete(Object o){
		try{
			getSession().delete(o);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			return false;
		}
	}
	/**
	 * 删除	自定义HQL语句	例：	delete Test where id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(可变参)
	 * @return boolean 	更新成功或失败
	 */
	public boolean delete(String hql,Object... values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i, values[i]);
			}
		}
		return q.executeUpdate()!=0?true:false;
	}
	/**
	 * 删除	自定义HQL语句	例：	delete Test where id=? 		
	 * 
	 * @param hql  语句
	 * @param values ?占位符对应的参数(List参数  list 索引对应 ？占位符位置)
	 * @return boolean 	更新成功或失败
	 */
	public boolean delete(String hql,List<Object> values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i, values.get(i));
			}
		}
		
		return q.executeUpdate()!=0?true:false;
	}
	/**
	 * 删除一个集合里面的全部对象
	 * 
	 * @param c 对象集合
	 * @return boolean 删除成功或失败
	 */
	public boolean deleteAll(Collection<?> c) {
		try{
			Session session=getSession();
			
			for(Object o:c){
				session.delete(o);
			}
			
			return true;
		}catch(Exception e){
			logger.error(e);
			return false;
		}
	}
	/**
	 * 单个 查询	根据类型 id	 get方法
	 * @param <T>
	 * 
	 * @param 	c 	对象Class
	 * @param 	id 	对象ID
	 * @return	Object	返回数据对象
	 */
	public <T> T get(Class<T> c, Serializable id) {
		
		return (T)getSession().get(c, id);
	}
	/**
	 * 单个查询		根据类型 id	post方法
	 * 
	 * @param 	c 	对象Class
	 * @param 	id 	对象ID
	 * @return	Object	返回数据对象
	 */
	public <T>T load(Class<T> c, Serializable id) {
		return (T)getSession().load(c, id);
	}
	/**
	 * 果可以有多条，但只查询第一条，使用此方法，   例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(可变参)
	 * @return Object  无查询结果返回null
	 */
	@SuppressWarnings("rawtypes")
	public Object findOne(String hql, Object... values) {
		List list=findPage(hql, 1, 1, values);
		return list.isEmpty()?null:list.get(0);
	}
	/**
	 * 结果可以有多条，但只查询第一条，使用此方法，   例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(List参数)
	 * @return Object  查询内容仅返回第一条
	 */
	@SuppressWarnings("rawtypes")
	public Object findOne(String hql, List<Object> values) {
		List list=findPage(hql, 1, 1, values);
		return list.isEmpty()?null:list.get(0);
	}
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法  	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(Map 类型参数 )
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findOne(String hql,Map<String,Object> values) {
		List list=findPage(hql, 1, 1, values);
		return list.isEmpty()?null:list.get(0);
		
	}
	
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法，	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(可变参)
	 * @return Object  无查询结果返回null
	 */
	public Object findUniqueOne(String hql,Object... values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i, values[i]);
			}
		}
		return q.uniqueResult();
			
		
	}
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法  	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(List参数)
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findUniqueOne(String hql, List<Object> values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i, values.get(i));
			}
		}
		return q.uniqueResult();
		
	}
	/**
	 * 单个查询	 当确定只返回一条，或没有信息时，使用此方法  	自定义HQL语句   	例：select name from Test where id=?
	 * 
	 * @param hql	HQL查询语句
	 * @param values  参数(Map 类型参数 )
	 * @return Object  查询内容仅返回第一条
	 */
	public Object findUniqueOne(String hql,Map<String,Object> values) {
		
		Query q = getSession().createQuery(hql);
		
		for(String key: values.keySet()){ 
		    Object obj=values.get(key);
		    if(obj instanceof Collection ){
		    	q.setParameterList(key,(Collection) obj);
		    }else if(obj instanceof Object[]){
		    	q.setParameterList(key, (Object[]) obj);
		    }else{
		    	
		    	q.setParameter(key, obj);
		    }
		} 
		return q.uniqueResult();
		
	}
	
	
	/**
	 * 多条查询	 无参的
	 * 
	 * @param hql 查询语句
	 * @return List	查询结果集
	 */
	
	@SuppressWarnings("rawtypes")
	public List find(String hql) {
		
		return getSession().createQuery(hql).list();
		
	}
	
	/**
	 * 多条查询	 可变参
	 * 
	 * @param hql 查询语句
	 * @param values 参数
	 * @return List	查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... values){
		Query q=getSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i, values[i]);
			}
		}
		return q.list();
	}
	
	/**
	 * 多条查询	 list 参数
	 * 
	 * @param hql 查询语句
	 * @param values 参数
	 * @return List	查询结果集
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql,List<Object> values){
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i, values.get(i));
			}
		}
		return q.list();
			
	}
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
	public List find(String hql,Map<String,Object> values) {
		
		Query q = getSession().createQuery(hql);
		
		for(String key: values.keySet()){ 
		    Object obj=values.get(key);
		    if(obj instanceof Collection ){
		    	q.setParameterList(key,(Collection) obj);
		    }else if(obj instanceof Object[]){
		    	q.setParameterList(key, (Object[]) obj);
		    }else{
		    	
		    	q.setParameter(key, obj);
		    }
		} 
		
		return q.list();
		
	}
	
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
	public List findPage(String hql, int page, int rows,List<Object> values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i, values.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
			
	}
	
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
	public List findPage(String hql,int page,int rows,Object... values) {
		
		Query q = getSession().createQuery(hql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i, values[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
	}
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
	public List findPage(String hql, int page,int rows,Map<String,Object> values) {
		
		Query q = getSession().createQuery(hql);
		
		for(String key: values.keySet()){ 
		    Object obj=values.get(key);
		    if(obj instanceof Collection ){
		    	q.setParameterList(key,(Collection) obj);
		    }else if(obj instanceof Object[]){
		    	q.setParameterList(key, (Object[]) obj);
		    }else{
		    	
		    	q.setParameter(key, obj);
		    }
		} 
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
			
	}
}
