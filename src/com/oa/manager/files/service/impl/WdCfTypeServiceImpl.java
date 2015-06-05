/**  
 * @Title WdCfTypeServiceImpl.java
 * @date 2013-10-28 下午4:33:11
 * @Copyright: 2013 
 */
package com.oa.manager.files.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.TypePowersList;
import com.oa.manager.files.bean.WdCfType;
import com.oa.manager.files.bean.WdCfTypePowers;
import com.oa.manager.files.bean.WdCfTypeUsers;
import com.oa.manager.files.bean.WdCompanyFiles;
import com.oa.manager.files.service.IWdCfTypeService;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
@Service
public class WdCfTypeServiceImpl extends BaseServiceImpl implements IWdCfTypeService{
	
	public List selectAllTypes(){
		
		return dao.find("select new Map(t.id as id,t.name as name,t.superId as superId)from WdCfType t order by t.sortNumber");
	}
	public List selectMyTypes(){
		Member me=ServletUtil.getMember();
		return dao.find("select  distinct new Map(t.id as id,t.name as name,t.superId as superId)from WdCfType t,WdCfTypeUsers u "+
				" where  t.id=u.cfTypeId and (u.tableId=? or u.tableId=? )  order by t.sortNumber",me.getId(),me.getDeptId());
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectWdCfTypes(PageParam param,WdCfType t){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WdCfType t where 1=1 ");
		List list=new ArrayList();
		//查询条件
		sb.append(" and t.superId = ? ");
		if(StringUtils.isNotBlank(t.getSuperId())){
			list.add(t.getSuperId());
		}else{
			list.add("0");//默认查询根目录下 
		}
		if(StringUtils.isNotBlank(t.getName())){
			sb.append(" and t.name like ? ");
			list.add("%"+t.getName()+"%");
		}
		
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by t.sortNumber");
		}
		data.setRows(dao.findPage("select new Map(t.id as id,t.name as name )"+sb,param.getPage(),param.getRows(),list));
		return data;
	}
	public String addWdCfType(WdCfType t,TypePowersList powers){
		
		Object obj=dao.findOne("from WdCfType where superId=? and  name=? ",t.getSuperId(),t.getName());
		if(obj==null){
			//等待保存的对象集合
			List<Object> c=new ArrayList<Object>();
			dao.save(t);
			List<WdCfTypePowers> deptPowers=powers.getDeptPowers();
			List<WdCfTypePowers> userPowers=powers.getUserPowers();
			if(powers!=null&&powers.getDeptPowers()!=null){
				for(WdCfTypePowers d:deptPowers){
					if(d!=null&&StringUtils.isNotBlank(d.getTableIds())){
						d.setCfTypeId(t.getId());
						d.setType((short)1);
						dao.save(d);
						String[] ids= d.getTableIds().split(",");
						for(String id:ids){
							WdCfTypeUsers u=new WdCfTypeUsers();
							u.setCfTypeId(t.getId());
							u.setCfTypePowerId(d.getId());
							u.setTableId(id);
							u.setType((short)1);
							c.add(u);
						}
						
					}
				}
			}
			if(powers!=null&&powers.getUserPowers()!=null){
				for(WdCfTypePowers d:userPowers){
					if(d!=null&&StringUtils.isNotBlank(d.getTableIds())){
						d.setCfTypeId(t.getId());
						d.setType((short)2);
						dao.save(d);
						String[] ids= d.getTableIds().split(",");
						for(String id:ids){
							WdCfTypeUsers u=new WdCfTypeUsers();
							u.setCfTypeId(t.getId());
							u.setCfTypePowerId(d.getId());
							u.setTableId(id);
							u.setType((short)2);
							c.add(u);
						}
					}
				}
			}
			dao.saveOrUpdateAll(c);
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return "msg.file.type.unique";//文档类型名称重复
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map selectOneType(String id){
		WdCfType t=dao.get(WdCfType.class, id);
		if(t==null){
			return null;
		}
		List<WdCfTypePowers> list=dao.find("from WdCfTypePowers p where p.cfTypeId=?",id);
		List<WdCfTypePowers> deptPowers=new ArrayList<WdCfTypePowers>();
		List<WdCfTypePowers> userPowers=new ArrayList<WdCfTypePowers>();
		for(WdCfTypePowers p:list){
			if(p.getType()==1){
				deptPowers.add(p);
			}else{
				userPowers.add(p);
			}
		}
		Map map=new HashMap();
		map.put("t", t);
		map.put("deptPowers", deptPowers);
		map.put("userPowers", userPowers);
		WdCfType supType=dao.get(WdCfType.class, t.getSuperId());
		if(supType!=null){
			map.put("supName",supType.getName());
		}
		return map;
	}
	
	public boolean deletePower(String id){
		
		return dao.delete("delete WdCfTypePowers where id=?",id);
	}
	public String updateWdCfType(WdCfType t,TypePowersList powers){
		
		Object obj=dao.findOne("from WdCfType where superId=? and  name=? and id!=?",t.getSuperId(),t.getName(),t.getId());
		if(obj==null){
			//等待保存的对象集合
			List<Object> c=new ArrayList<Object>();
			WdCfType old=dao.get(WdCfType.class, t.getId());
			old.setName(t.getName());
			old.setSortNumber(t.getSortNumber());
			if(!t.getId().equals(t.getSuperId())){
				old.setSuperId(t.getSuperId());
			}
			List<WdCfTypePowers> deptPowers=powers.getDeptPowers();
			List<WdCfTypePowers> userPowers=powers.getUserPowers();
			if(powers!=null&&powers.getDeptPowers()!=null){
				for(WdCfTypePowers d:deptPowers){
					if(d!=null&&StringUtils.isNotBlank(d.getTableIds())){
						if(StringUtils.isBlank(d.getId())){
							//新增
							d.setCfTypeId(t.getId());
							d.setType((short)1);
							dao.save(d);
							String[] ids= d.getTableIds().split(",");
							for(String id:ids){
								WdCfTypeUsers u=new WdCfTypeUsers();
								u.setCfTypeId(t.getId());
								u.setCfTypePowerId(d.getId());
								u.setTableId(id);
								u.setType((short)1);
								c.add(u);
							}
						}else{
							//修改
							d.setCfTypeId(t.getId());
							d.setType((short)1);
							dao.update(d);
							//先删除此权限关联的所有 WdCfTypeUsers
							dao.delete("delete WdCfTypeUsers where cfTypePowerId=?",d.getId());
							String[] ids= d.getTableIds().split(",");
							for(String id:ids){
								WdCfTypeUsers u=new WdCfTypeUsers();
								u.setCfTypeId(t.getId());
								u.setCfTypePowerId(d.getId());
								u.setTableId(id);
								u.setType((short)1);
								c.add(u);
							}
						}
						
						
						
					}
				}
			}
			if(powers!=null&&powers.getUserPowers()!=null){
				for(WdCfTypePowers d:userPowers){
					if(d!=null&&StringUtils.isNotBlank(d.getTableIds())){
						if(StringUtils.isBlank(d.getId())){
							//新增
							d.setCfTypeId(t.getId());
							d.setType((short)2);
							dao.save(d);
							String[] ids= d.getTableIds().split(",");
							for(String id:ids){
								WdCfTypeUsers u=new WdCfTypeUsers();
								u.setCfTypeId(t.getId());
								u.setCfTypePowerId(d.getId());
								u.setTableId(id);
								u.setType((short)2);
								c.add(u);
							}
						}else{
							//修改
							d.setCfTypeId(t.getId());
							d.setType((short)2);
							dao.update(d);
							//先删除此权限关联的所有 WdCfTypeUsers
							dao.delete("delete WdCfTypeUsers where cfTypePowerId=?",d.getId());
							String[] ids= d.getTableIds().split(",");
							for(String id:ids){
								WdCfTypeUsers u=new WdCfTypeUsers();
								u.setCfTypeId(t.getId());
								u.setCfTypePowerId(d.getId());
								u.setTableId(id);
								u.setType((short)2);
								c.add(u);
							}
						}
					}
				}
			}
			dao.saveOrUpdateAll(c);
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return "msg.file.type.unique";//文档类型名称重复
		}
	}
	
	public boolean deleteWdCfTypes(String[] ids){
		for(String id:ids){
			WdCfType sub=(WdCfType)dao.findOne("from WdCfType where superId=?",id);
			//判断是否有下级
			if(sub==null){
				WdCompanyFiles subFile=(WdCompanyFiles)dao.findOne("from WdCompanyFiles where wdSuperId=?",id);
				//判断是否有文件
				if(subFile==null){
					dao.delete("delete WdCfType where id=?",id);
				}
			}
		}
		return true;
	}
	
}
