/**  
 * @Project: jxoa
 * @Title: vehiclemanagementServiceImpl.java
 * @Package com.oa.manager.administration.service.impl
 * @date 2013-6-4 下午5:54:29
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.SearchFilterUtil;
import com.oa.manager.administration.bean.VmVehiclemanagement;
import com.oa.manager.administration.service.IVehiclemanagementService;

/**
 * 
 * 类名：vehiclemanagementServiceImpl
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-4 下午5:54:29
 *
 */
@Service
public class VehiclemanagementServiceImpl extends BaseServiceImpl implements IVehiclemanagementService{
	/**
	 * 查询车辆管理
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid load(PageParam param,VmVehiclemanagement vm){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from VmVehiclemanagement vg,VmDrivertable vt where vg.drivingno=vt.id ");
		//List list=new ArrayList();
		Map map=new HashMap();
		
		/*if(StringUtils.isNotBlank(vm.getLicenseno())){
			sb.append("  and licenseno =?");
			list.add(vm.getLicenseno());
		}
		if(StringUtils.isNotBlank(vm.getVehicletypeId())){
			sb.append(" and vg.vehicletypeId=?");
			list.add(vm.getVehicletypeId());
		}
		if(StringUtils.isNotBlank(vm.getStatus())){
			sb.append(" and vg.status=?");
			list.add(vm.getStatus());
		}
		if(StringUtils.isNotBlank(vm.getUsingunit())){
			sb.append(" and usingunit like ?");
			list.add(vm.getUsingunit());
		}
		if(StringUtils.isNotBlank(vm.getDrivingno())){
			sb.append(" and vt.drivingname like ?");
			list.add("%"+vm.getDrivingno()+"%");
		}
		if(StringUtils.isNotBlank(vm.getEnginenumber())){
			sb.append(" and vg.enginenumber like ?");
			list.add("%"+vm.getEnginenumber()+"%");
		}
		if(StringUtils.isNotBlank(vm.getPono())){
			sb.append(" and vg.pono like ?");
			list.add("%"+vm.getPono()+"%");
		}
		if(vm.getCinsurancetime()!=null){
			sb.append(" and vg.cinsurancetime <=?");
			list.add(vm.getCinsurancetime());
		}
		if(vm.getInsurancetime()!=null){
			sb.append(" and vg.insurancetime  <=?");
			list.add(vm.getInsurancetime());
		}
		if(vm.getBuydate()!=null){
			sb.append(" and buydate <?");
			list.add(vm.getBuydate());
		}*/
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by vg.createdate desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(vg.id as id,vg.licenseno as licenseno,vg.vehicletypeId as vehicletypeId,vg.buydate as buydate,vg.weight as weight,vg.seat as seat,vg.status as status,vg.usingunit as usingunit,vg.promulgator as promulgator,vg.createdate as createdate,vg.drivingno as drivingno,vt.drivingname as xm) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			r.put("userName",MyCache.getInstance().getTrueName((String)r.get("promulgator")));//getDeptName((String)map.get("promulgator")));//创建人
			r.put("vehicletypeId",MyCache.getInstance().getSelectValue((String) r.get("vehicletypeId")));//车辆类型
		}	
		
		data.setRows(rows);
		return data ;
	}
	 /**
	  * 批量删除
	  */
	 public boolean deletes(String[] ids){
		 List<Object> c=new ArrayList<Object>();
		 for(String id:ids){
			 VmVehiclemanagement vm=dao.get(VmVehiclemanagement.class, id);
			 if(vm!=null){
			 c.add(vm);
			 }
			 }
          return dao.deleteAll(c);
	 }
	 /**
	  * 修改车辆管理信息
	  */
	 public List selectcar(String id){
		 
		 return dao.find("select new Map(vm as vm,vt.drivingname as xm)  from VmVehiclemanagement vm,VmDrivertable vt where vm.drivingno=vt.id and vm.id=?",id);
	 }
	 /**
	  * 修改车辆管理
	  */
	 public String updateVmVehiclemanagement(VmVehiclemanagement vm){
		  VmVehiclemanagement  vg=dao.get(VmVehiclemanagement.class, vm.getId());
		  System.out.println(vg+"null");
		   if(vg!=null){
			   vg.setLicenseno(vm.getLicenseno());
			   vg.setDrivingno(vm.getDrivingno());
			   vg.setVehicletypeId(vm.getVehicletypeId());
			   vg.setBuydate(vm.getBuydate());
			   vg.setPrice(vm.getPrice());
			   vg.setEnginenumber(vm.getEnginenumber());
			   vg.setPono(vm.getPono());
			   vg.setWeight(vm.getWeight());
			   vg.setSeat(vm.getSeat());
			   vg.setUsingunit(vm.getUsingunit());
			   vg.setInsurancetime(vm.getInsurancetime());
			   vg.setCinsurancetime(vm.getCinsurancetime());
			   vg.setStatus(vm.getStatus());
			   vg.setUsingpersonal(vm.getUsingpersonal());
			   vg.setRemark(vm.getRemark());
			   vg.setBypromulgator(vm.getBypromulgator());
			   vg.setModydate(vm.getModydate());

			return   MsgConfig.MSG_KEY_SUCCESS;
		   }
		   else{
			   return "msg.update.content";
		   }
	 }
}
