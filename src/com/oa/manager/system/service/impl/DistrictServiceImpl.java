/**  
 * @Project: jxoa
 * @Title: DistrictServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-6-19 下午2:03:19
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDistrict;
import com.oa.manager.system.service.IDistrictService;

/**
 * 
 * 类名：DistrictServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-19 下午2:03:19
 *
 */
@Service
public class DistrictServiceImpl extends BaseServiceImpl implements IDistrictService{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectDistrict(PageParam param,SyDistrict dis){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyDistrict d where 1=1 ");
		List list=new ArrayList();
		//查询条件
		sb.append(" and d.superId= ? ");
		if(StringUtils.isNotBlank(dis.getSuperId())){
			list.add(dis.getSuperId());
		}else{
			list.add("0");
		}
		if(StringUtils.isNotBlank(dis.getDisName())){
			sb.append(" and d.disName like ? ");
			list.add(dis.getDisName()+"%");
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by d.disSort asc");
		}
		data.setRows(dao.findPage(sb.toString(),param.getPage(),param.getRows(),list));
		
		return data;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long selectDistrictCount(SyDistrict dis){
		
		StringBuffer sb=new StringBuffer("select count(*)from SyDistrict d where 1=1 ");
		List list=new ArrayList();
		if(StringUtils.isNotBlank(dis.getSuperId())){
			sb.append(" and d.superId= ? ");
			list.add(dis.getSuperId());
		}
		if(StringUtils.isNotBlank(dis.getDisName())){
			sb.append(" and d.disName like ? ");
			list.add(dis.getDisName()+"%");
		}
		
		
		return (Long)dao.findUniqueOne(sb.toString(),list);
		
	}
	
	
	public String addDistrict(SyDistrict dis){
		
		Object obj=dao.findOne("from SyDistrict where superId=? and  disName=? ",dis.getSuperId(),dis.getDisName());
		if(obj==null){
			return dao.save(dis)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		}else{
			return "msg.district.unique";//名称重复
		}
		
		
	}
	public String updateDistrict(SyDistrict dis){
		Object obj=dao.findOne("from SyDistrict where superId=? and  disName=? and id!=?",dis.getSuperId(),dis.getDisName(),dis.getId());
		if(obj==null){
			return dao.update(dis)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		}else{
			return "msg.district.unique";//名称重复
		}
	}
	
	public boolean deleteDistrict(String[] ids){
	
		for(String id:ids){
			dao.delete("delete SyDistrict where id=?",id);
		}
	
		return true;
	}
	@SuppressWarnings("rawtypes")
	public List selectAllDistrict(){
		
		return dao.find("select new Map(d.id as id,d.disName as name,d.superId as superId)from SyDistrict d order by disSort asc");
	}
	
	@SuppressWarnings("rawtypes")
	public List selectDistrictBySuperId(int id){
		
		return dao.find("from SyDistrict where superId=? order by disSort asc",id);
	}
}
