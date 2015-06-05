/**  
 * @Project: jxoa
 * @Title: driverServiceImpl.java
 * @Package com.oa.manager.administration.service.impl
 * @date 2013-6-5 下午4:38:47
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service.impl;

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
import com.oa.manager.administration.bean.VmDrivertable;
import com.oa.manager.administration.service.IDriverService;

/**
 * 
 * 类名：driverServiceImpl
 * 功能：行政办公--车辆管理--驾驶员
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-5 下午4:38:47
 *
 */
@Service
public class DriverServiceImpl extends BaseServiceImpl implements IDriverService {
	/**
	 * 查询驾驶员的信息
	 */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public DataGrid load(PageParam param,VmDrivertable vt){
	  
	  DataGrid data=new DataGrid();
	  StringBuffer sb =new StringBuffer("  from  VmDrivertable d where 1=1");
	  List list=new ArrayList();
	  if(StringUtils.isNotBlank(vt.getDrivingname())){
		  sb.append(" and d.drivingname like ?");
		  list.add("%"+vt.getDrivingname()+"%");
	  }
	  if(StringUtils.isNotBlank(vt.getGender())){
		  sb.append(" and d.gender=?");
		  list.add(vt.getGender());
	  }
	  if(StringUtils.isNotBlank(vt.getDrivingno())){
		  sb.append(" and d.drivingno=?");
		  list.add(vt.getDrivingno()); 
	  }
	  
	  data.setTotal((Long)dao.findOne("select count(*)"+sb.toString(),list));
	 if(StringUtils.isNotBlank(param.getSort())){
		 param.appendOrderBy(sb);//排序
	 }else{
		 sb.append(" order by d.createdate desc ");
	 }
	  
	  
	  
	  List<Map<String,Object>> rows = dao.findPage("select new Map(d.id as id,d.drivingno as drivingno,d.drivingname as drivingname,d.gender as gender,d.papervaliddate as papervaliddate,d.drivingage as drivingage,d.allowdriving as allowdriving,d.address as address,d.phone as phone,d.promulgator as promulgator,d.createdate as createdate)"+sb.toString(),param.getPage(),param.getRows(),list);
	  for(Map<String,Object> map:rows){
			map.put("userName",MyCache.getInstance().getTrueName((String)map.get("promulgator")));//创建人
			//map.put("allowdriving",MyCache.getInstance().getSelectValue((String) map.get("allowdriving")));//准驾车型
		}	
	 
	  data.setRows(rows);
	  return data;
  }
  /**
   * 批量删除
   */
  public boolean deletes(String[] ids){
	  List<Object> c=new ArrayList<Object>();
	  for(String id:ids){
		  VmDrivertable vt=dao.get(VmDrivertable.class, id);
		 if(vt!=null){
		  c.add(vt);
		  }
	  }
	  return dao.deleteAll(c);
  }
  /**
   * 增加驾驶员
   */
  public String adddriver(VmDrivertable vt){
	  Object drivername=dao.findOne(" from VmDrivertable where drivingno=?",vt.getDrivingno());
	  if(drivername==null){
		  dao.save(vt);
		  return MsgConfig.MSG_KEY_SUCCESS;
	  }
	  else{
		  return "msg.driver.drivingno";
	  }
  }
	  /**
	   * 更新数据时
	   */
	  public String updateVd(VmDrivertable vd){
		  VmDrivertable vs=dao.get(VmDrivertable.class, vd.getId());
		  if(vs!=null){
			  vs.setModydate(vd.getModydate());
			  vs.setDrivingname(vd.getDrivingname());
			  vs.setGender(vd.getGender());
			  vs.setDrivingno(vd.getDrivingno());
			  vs.setBirthday(vd.getBirthday());
			  vs.setRegisterdate(vd.getRegisterdate());
			  vs.setPapervaliddate(vd.getPapervaliddate());
			  vs.setDrivingage(vd.getDrivingage());
			  vs.setPhone(vd.getPhone());
			  vs.setAllowdriving(vd.getAllowdriving());
			  vs.setAddress(vd.getAddress());
			  vs.setCheckrecord(vd.getCheckrecord());
			  vs.setRemark(vd.getRemark());
			 	return   MsgConfig.MSG_KEY_SUCCESS;
		   }
		   else{
			   return "msg.update.content";
		   }
	  }

}
