/**  
 * * @Project: jxoa
 * @Title: AccidentServiceImpl.java
 * @Package com.oa.manager.administration.service.impl
 * @date 2013-6-6 下午3:03:46
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.VmAccident;
import com.oa.manager.administration.service.IAccidentService;


/**
 * 
 * 类名：AccidentServiceImpl
 * 功能：行政办公-车辆管理-事故管理
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-6 下午3:03:46
 *
 */
@Service
public class AccidentServiceImpl extends BaseServiceImpl implements  IAccidentService {
	/**
	 * 查询事故
	 */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid loadaccident(VmAccident vd,PageParam param){
		 DataGrid data=new DataGrid();
		 StringBuffer sb=new StringBuffer(" from VmAccident vd,VmDrivertable vt,VmVehiclemanagement vg where vd.drivingno=vt.id and vg.id=vd.licenseno ");
		 List list=new ArrayList();
		
		 if(StringUtils.isNotBlank(vd.getLicenseno())){
				sb.append(" and vg.licenseno = ? ");
				list.add(vd.getLicenseno());
				
		 }
		 if(StringUtils.isNotBlank(vd.getDrivingno())){
				sb.append(" and vt.drivingname = ?");
				list.add(vd.getDrivingno());
		 }
		 if(vd.getStartDate()!=null){
				sb.append(" and vd.createdate>=?");
				list.add(vd.getStartDate());
			}
			if(vd.getEndDate()!=null){
				sb.append(" and vd.createdate<=?");
				list.add(vd.getEndDate());
			}
			data.setTotal((Long)dao.findOne("select count(*)"+sb.toString(),list));
			if(StringUtils.isNotBlank(param.getSort())){
				param.appendOrderBy(sb);//排序
			}else{
				
				sb.append(" order by vd.createdate desc ");
			}
			
			List<Map<String,Object>> rows = dao.findPage("select new Map( vd.id as id,vd.happenplace as happenplace, vd.accidentconfirm as accidentconfirm,vd.summary as summary,vd.createdate as createdate,vt.drivingname as drivingname,vg.licenseno as licenseno,vg.id as licensenoId,vd.drivingno as drivingno) "+sb.toString(),param.getPage(),param.getRows(),list);
			
			data.setRows(rows);
			
			
		 return data;
	 }
	 /**
	  * 批量删除事故
	  */
	 public boolean deletes(String[] ids){
		 List<Object> list=new ArrayList<Object>();
		 for(String id:ids){
			 VmAccident vd=dao.get(VmAccident.class, id);
			 if(vd!=null){
			 list.add(vd);
			 }
		 }
		 return dao.deleteAll(list);
	 }
	 /**
	  * 更新查找
	  */
	 public List  updateselect(String id){
		 
		 return   dao.find("select new Map( vd as vd,vt.drivingname as jsy,vg.licenseno as cph,vg.id as ids) from VmAccident vd,VmDrivertable vt,VmVehiclemanagement vg where vd.drivingno=vt.id and vg.id=vd.licenseno and vd.id=?", id);
	 }
	 /**
	  * 更新事故
	  */
	 public String updateVd(VmAccident vd){
		 VmAccident vc=dao.get(VmAccident.class, vd.getId());
		 if(vc!=null){
			 vc.setHappendate(vd.getHappendate());
			 vc.setHappenplace(vd.getHappenplace());
			 vc.setAccidentconfirm(vd.getAccidentconfirm());
			 vc.setInsurancecompensation(vd.getInsurancecompensation());
			 vc.setByname(vd.getByname());
			 vc.setByaddress(vd.getByaddress());
			 vc.setByphone(vd.getByphone());
			 vc.setCgmoney(vd.getCgmoney());
			 vc.setSelfgmomey(vd.getSelfgmomey());
			 vc.setBylicensenumberId(vd.getBylicensenumberId());
			 vc.setSummary(vd.getSummary());
			 vc.setContentreconciliation(vd.getContentreconciliation());
			 vc.setRemark(vd.getRemark());
			 
				return   MsgConfig.MSG_KEY_SUCCESS;
		   }
		   else{
			   return "msg.update.content";
		   }
	 }
}
