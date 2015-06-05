/**  
 * @Project: oa
 * @Title: AfficheServiceImpl.java
 * @Package com.oa.manager.common_platform.service.impl
 * @date 2013-5-31 上午9:59:41
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.XtAffiche;
import com.oa.manager.common_platform.bean.XtAfficheDept;
import com.oa.manager.common_platform.service.IAfficheService;

/**
 * 
 * 类名：AfficheServiceImpl
 * 功能：公共平台--公告管理业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-31 上午9:59:41
 *
 */
@Service
public class AfficheServiceImpl extends BaseServiceImpl implements IAfficheService{

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectAffiche(PageParam param,XtAffiche a,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtAffiche a where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(a.getAfficheType())){
			sb.append(" and a.afficheType =? ");
			list.add(a.getAfficheType());
		}
		if(StringUtils.isNotBlank(a.getUserId())){
			sb.append(" and a.userId =? ");
			list.add(a.getUserId());
		}
		if(startDate!=null){
			sb.append(" and a.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and a.createTime <=? ");
			list.add(endDate);	
		}
		if(StringUtils.isNotBlank(a.getAfficheTitle())){
			sb.append(" and a.afficheTitle like ? ");
			list.add("%"+a.getAfficheTitle()+"%");
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by a.isTop desc,a.createTime desc");
		}
		
		List<XtAffiche> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtAffiche r:rows){
			r.setUserId(MyCache.getInstance().getTrueName(r.getUserId()));
			r.setAfficheType(MyCache.getInstance().getSelectValue(r.getAfficheType()));
			r.setAfficheContent("");
		}	
		data.setRows(rows);
		return data;
		
	}
	
	public String updateAffiche(XtAffiche xa){
		
		XtAffiche oldxa=dao.get(XtAffiche.class, xa.getId());
		if(oldxa==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		oldxa.setAfficheContent(xa.getAfficheContent());
		oldxa.setAfficheTitle(xa.getAfficheTitle());
		oldxa.setAfficheType(xa.getAfficheType());
		if(xa.getIsTop()==null||xa.getIsTop()!=1){
			oldxa.setIsTop((short)0);
		}else{
			oldxa.setIsTop((short)1);
		}
		
		return dao.update(oldxa)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
	}
	
	public boolean deleteAffiche(String[] ids){
		
		for(String id:ids){
			
			dao.delete("delete XtAffiche where id=? ", id);
		}
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectAfficheDept(PageParam param,XtAfficheDept a,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtAfficheDept a where 1=1 ");
		Map map=new HashMap();
		SearchFilterUtil.appendRules(sb, map, param,"10001");
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by a.isTop desc,a.createTime desc");
		}	
		List<XtAfficheDept> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),map);
		for(XtAfficheDept r:rows){
			r.setUserId(MyCache.getInstance().getTrueName(r.getUserId()));
			r.setAfficheType(MyCache.getInstance().getSelectValue(r.getAfficheType()));
			r.setDeptId(MyCache.getInstance().getDeptName(r.getDeptId()));
			r.setAfficheContent("");
		}	
		data.setRows(rows);
		return data;
		
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectMyAfficheDept(PageParam param,XtAfficheDept a,Date startDate,Date endDate){
		a.setDeptId(ServletUtil.getMember().getDeptId());
		
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtAfficheDept a where deptId=:deptId  ");
		Map map=new HashMap();
		
		map.put("deptId", a.getDeptId());
		
		SearchFilterUtil.appendRules(sb, map, param,null);
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by a.isTop desc,a.createTime desc");
		}	
		List<XtAfficheDept> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),map);
		for(XtAfficheDept r:rows){
			r.setUserId(MyCache.getInstance().getTrueName(r.getUserId()));
			r.setAfficheType(MyCache.getInstance().getSelectValue(r.getAfficheType()));
			r.setDeptId(MyCache.getInstance().getDeptName(r.getDeptId()));
			r.setAfficheContent("");
		}	
		data.setRows(rows);
		return data;
		
		
	}

	public boolean updateAfficheDept(XtAfficheDept xa){
		
		XtAfficheDept oldxa=dao.get(XtAfficheDept.class, xa.getId());
		oldxa.setAfficheContent(xa.getAfficheContent());
		oldxa.setAfficheTitle(xa.getAfficheTitle());
		oldxa.setAfficheType(xa.getAfficheType());
		oldxa.setDeptId(xa.getDeptId());
		if(xa.getIsTop()==null||xa.getIsTop()!=1){
			oldxa.setIsTop((short)0);
		}else{
			oldxa.setIsTop((short)1);
		}
		
		return dao.update(oldxa);
	}
	
	public boolean deleteAfficheDept(String[] ids){
		
		for(String id:ids){
			
			dao.delete("delete XtAfficheDept where id=? ", id);
		}
		return true;
	}
//*************************************安卓*************************************
	@Override
	public List<Map<String, Object>> querySystemNotic(PageParam param) {

	  List<Map<String, Object>> list =dao.findPage("select new Map(a.id as id,a.afficheType as afficheType,a.afficheTitle as title ) from XtAffiche a order by a.isTop,a.createTime ",param.getPage(),param.getRows());
	  for(Map<String,Object> m:list){
		  m.put("type",MyCache.getInstance().getSelectValue((String)m.get("afficheType")));//类型
		  m.remove("afficheType");//移除该值
	  }
		return list;
	}	
	@Override
	public Long totleSystemNotic() {
		
		return (Long) dao.findUniqueOne("select count(*) from XtAffiche ");
	}
	@Override
	public Map<String, Object> updateSystemNoticById(String id) {
		Map<String,Object> map= new HashMap<String,Object>();
		XtAffiche affiche = dao.get(XtAffiche.class, id);
		if(affiche==null){
			return map;
		}
		map.put("userName",MyCache.getInstance().getTrueName(affiche.getUserId()));//发布人
		map.put("createdate",affiche.getCreateTime());//发布时间
		map.put("content",affiche.getAfficheContent());//系统公告内容   
		return map;
	}
	@Override
	public List<Map<String, Object>> selectDepartmentNotic(PageParam param) {
		
		
		 List<Map<String, Object>> list= dao.findPage("select new Map(d.id as id,d.afficheType as affichetype,d.afficheTitle as title) from XtAfficheDept d where d.deptId=? ", param.getPage(),param.getRows(),ServletUtil.getMember().getDeptId());
		 for(Map<String,Object> m: list){
			m.put("type",MyCache.getInstance().getSelectValue((String)m.get("affichetype")));//部门公告类型
			m.remove("affichetype");
		 }
		return list;
	}
	@Override
	public Long totleDepartmentNotic() {
		
		return (Long) dao.findUniqueOne("select count(*)  from XtAfficheDept d where d.deptId=? ",ServletUtil.getMember().getDeptId());
	}
	
	@Override
	public Map<String, Object> selectDepartmentById(String id) {
		Map<String,Object> map= new HashMap<String,Object>();
		XtAfficheDept affichedept = dao.get(XtAfficheDept.class, id);
		if(affichedept==null){
			return map;
		}
		map.put("userName",MyCache.getInstance().getTrueName(affichedept.getUserId()));//发布人
		map.put("createdate",affichedept.getCreateTime());//发布时间
		map.put("content",affichedept.getAfficheContent());//发布内容
		return map;
	}
}
