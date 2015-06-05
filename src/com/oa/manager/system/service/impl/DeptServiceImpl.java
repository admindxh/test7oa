/**  
 * @Project: jxoa
 * @Title: DeptServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-4-3 下午5:11:01
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDept;
import com.oa.manager.system.service.IDeptService;

/**
 * 
 * 类名：DeptServiceImpl
 * 功能：部门管理 业务操作
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-3 下午5:11:01
 *
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl implements IDeptService{
	
	public String addDept(SyDept d){
		Object obj=dao.findOne("from SyDept where deptName=? and superId=?",d.getDeptName(),d.getSuperId());
		if(obj==null){
			dao.save(d);
			if(StringUtils.isNotBlank(d.getId())){
				
				saveLog("添加部门", "部门名称:"+d.getDeptName());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.deptname.unique";//部门名称已被占用
		}
		
	}
	
	/**
	 * 查询出所有部门
	 */
	@SuppressWarnings("unchecked")
	public List<SyDept> selectAllDepts(){
		
		return dao.find("from SyDept  order by deptSort asc");
	}
	/**
	 * 查询出所有部门
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectAllDeptsMap(){
		
		return dao.find("select new Map(id as id,deptName as deptName,superId as superId)from SyDept  order by deptSort asc");
	}
	
	/**
	 * 递归查询子节点 
	 * @param superMap
	 * @param depts
	 */
	/*@SuppressWarnings("unchecked")
	private void queryChildDepts(List<SyDept> depts){
		
		for(SyDept d:depts){
			List<SyDept> zdepts=(List<SyDept>)dao.find("from SyDept where superId=? order by deptSort asc",d.getId());
			if(!zdepts.isEmpty()){
				d.setDepts(zdepts);
				queryChildDepts(zdepts);	
			}
		}
	}*/
	
	public String updateDept(SyDept d){
		
		Object obj=dao.findOne("from SyDept where deptName=? and superId=? and id!=?",d.getDeptName(),d.getSuperId(),d.getId());
		if(obj==null){
			
			if(dao.update(d)){
				saveLog("修改部门", "部门名称："+d.getDeptName());
				//删除缓存
				MyCache.getInstance().removeCache(MyCache.DEPTID2NAME,d.getId());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.deptname.unique";//菜单名已被占用
		}
	}
	
	public String deleteDept(String id){
		
		Object o=dao.findOne("from SyDept where superId=? ",id);
		if(o!=null){
			return "msg.dept.haschild";//部门下属还有子部门，无法删除
		}else{
			Object userObj=dao.findOne("from SyUsers where deptId=?",id);
			if(userObj!=null){
				return "msg.dept.hasuser";//有用户属于此部门，无法删除
			}
			SyDept dept=dao.get(SyDept.class, id);
			if(dept!=null){
				if(dao.delete(dept)){
					saveLog("删除部门", "部门名称："+dept.getDeptName());
					//删除缓存
					MyCache.getInstance().removeCache(MyCache.DEPTID2NAME,id);
					
					return MsgConfig.MSG_KEY_SUCCESS;
				}else{
					return MsgConfig.MSG_KEY_FAIL;
				}
			}else{
				return MsgConfig.MSG_KEY_NODATA;
			}
		}
		
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectDepts(PageParam param,SyDept dept){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyDept d where 1=1 ");
		List list=new ArrayList();
		if(StringUtils.isNotBlank(dept.getDeptName())){
			sb.append(" and d.deptName like ? ");
			list.add("%"+dept.getDeptName()+"%");
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		List<Map<String,Object>> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		 
		
		data.setRows(rows);
		
		return data;
	
	}
	
	
}
