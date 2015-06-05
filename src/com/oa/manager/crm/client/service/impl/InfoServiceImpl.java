/**  
 * @Project: jxoa
 * @Title: PublicServiceImpl.java
 * @Package com.oa.manager.PublicOffice.addressBook.Public.service.impl
 * @date 2013-5-21 下午02:42:07
 * @Copyright: 2013 
 */
package com.oa.manager.crm.client.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.SearchFilterUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Mapping;
import com.oa.manager.common_platform.bean.Meeting;
import com.oa.manager.common_platform.bean.Room;
import com.oa.manager.crm.client.bean.Info;
import com.oa.manager.crm.client.bean.Share;
import com.oa.manager.crm.client.service.IInfoService;
import com.oa.manager.personalOffice.bean.PersonalAddressBook;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personnel.bean.Deal;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：InfoServiceImpl 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0
 * 日期：2013年11月30日 11:47:20
 * 
 */
@Service
public class InfoServiceImpl extends BaseServiceImpl implements
		IInfoService {

	@Override
	public DataGrid selectInfo(PageParam param, Info o) {
		DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer("from Info  where clStaff=:clStaff ");
		Map<String, Object> map=new HashMap<String, Object>();	
		Member me = ServletUtil.getMember();
		map.put("clStaff", me.getId());
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb, map));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by clCreateTime desc");
		}
		
		List<Info> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), map);
		
		for ( Info r : rows) {
				
			r.setClType(MyCache.getInstance().getSelectValue(r.getClType()));
			r.setClSource(MyCache.getInstance().getSelectValue(r.getClSource()));
			r.setClGrade(MyCache.getInstance().getSelectValue(r.getClGrade()));
			r.setClStatus(MyCache.getInstance().getSelectValue(r.getClStatus()));
			r.setClStaje(MyCache.getInstance().getSelectValue(r.getClStaje()));
			r.setClRelation(MyCache.getInstance().getSelectValue(r.getClRelation()));
			r.setClIndustry(MyCache.getInstance().getSelectValue(r.getClIndustry()));
			r.setClStaff(MyCache.getInstance().getTrueName(r.getClStaff()));
		}
		
		data.setRows(rows);

		return data;
		
	}

	@Override
	public String addInfo(Info o) {
		
		Object obj=dao.findOne("from Info  where clName=? ",o.getClName());
			if(obj==null){
				Member me = ServletUtil.getMember();
				Timestamp ts = new Timestamp(new Date().getTime());
				o.setClCreate(me.getId());
				o.setClCreateTime(ts);
				o.setClUptade(me.getId());
				o.setClUpdateTime(ts);
				
				if (dao.save(o)) {
					return MsgConfig.MSG_KEY_SUCCESS;
				} else {
					return MsgConfig.MSG_KEY_FAIL;
				}
			}else{
				return "msg.personal.unique";//数据库已有值
			}
		
	}

	@Override
	public String updateInfo(Info o) {
		Member me = ServletUtil.getMember();
		Timestamp ts = new Timestamp(new Date().getTime());
		o.setClUptade(me.getId());
		o.setClUpdateTime(ts);
		if (dao.update(o)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public boolean deleteInfo(String[] ids) {
		for (String id : ids) {
			Info p = dao.get(Info.class, id);
			dao.delete(p);
		}
		return true;
	}

	@Override
	public Info selectID(String id) {
		return dao.get(Info.class, id);
	}
	@Override
	public String updateShift(String id,String clStaff) {
		Info o=dao.get(Info.class, id);
		Member me = ServletUtil.getMember();
		Timestamp ts = new Timestamp(new Date().getTime());
		o.setClUptade(me.getId());
		o.setClUpdateTime(ts);
		o.setClStaff(clStaff);
		if (dao.update(o)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public String addShare(String id, String deptid, String userid) {
		Collection<Share> c = new ArrayList<Share>();
		String[] dept=null;
		if (deptid != null && !"".equals(deptid)) {
			dept = deptid.split(",");

			for (int i = 0; i < dept.length; i++) {
				if (dept[i] != null && !"".equals(dept[i])) {
					Share s = new Share();
					s.setCsClid(id);
					s.setCsFkid(dept[i]);
					s.setCsType("1");
					c.add(s);

				}

			}
			
			
		}
		String[] user=null;
		if (userid != null && !"".equals(userid)) {

			user = userid.split(",");
		}
		if (user != null && !"".equals(user)) {
			for (int i = 0; i < user.length; i++) {
				if (user[i] != null && !"".equals(user[i])) {
					Share s = new Share();
					s.setCsClid(id);
					s.setCsFkid(user[i]);
					s.setCsType("2");
					c.add(s);
				}

			}
		}
		
		
		if (dao.saveOrUpdateAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}
	
	
	@Override
	public DataGrid selectShare(PageParam param, Info o) {
		DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer("from Info  o ,Share s  where  o.id=s.csClid and ( s.csFkid =:deptid  or s.csFkid=:userid )");
		Map<String, Object> map=new HashMap<String, Object>();	
		Member me = ServletUtil.getMember();
		map.put("deptid",me.getDeptId());
		map.put("userid", me.getId());
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(distinct o.id)" + sb, map));
		
		if (StringUtils.isNotBlank(param.getSort())) {

//			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by o.clCreateTime desc");
		}
		
		List<Info> rows = dao.findPage("select distinct o "+sb.toString(),
				param.getPage(), param.getRows(), map);
//		类型转换异常
		for (Info r : rows) {

			r.setClType(MyCache.getInstance().getSelectValue(r.getClType()));
			r.setClSource(MyCache.getInstance().getSelectValue(r.getClSource()));
			r.setClGrade(MyCache.getInstance().getSelectValue(r.getClGrade()));
			r.setClStatus(MyCache.getInstance().getSelectValue(r.getClStatus()));
			r.setClStaje(MyCache.getInstance().getSelectValue(r.getClStaje()));
			r.setClRelation(MyCache.getInstance().getSelectValue(r.getClRelation()));
			r.setClIndustry(MyCache.getInstance().getSelectValue(r.getClIndustry()));
			r.setClStaff(MyCache.getInstance().getTrueName(r.getClStaff()));
		}
		
		data.setRows(rows);
		return data;
		
	}

	@Override
	public Map<String, Serializable> selectShare(String id) {
		Info o = dao.get(Info.class, id);
		List<Share> li = dao.find("from Share where csClid=? ", id);
		List<String> deptIds = new ArrayList<String>();		
		List<String> userIds = new ArrayList<String>();
		for (Share m : li) {
			if (m.getCsType().equals("1")) {
				deptIds.add(m.getCsFkid());
			} else {
				userIds.add(m.getCsFkid());
			}
		}
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		
		map.put("info", o);
		map.put("deptIds", StringUtils.join(deptIds, ","));
		map.put("userIds", StringUtils.join(userIds, ","));
		return map;
	}

	@Override
	public String updateShare(String id, String deptid, String userid) {
		Collection<Object> c = new ArrayList<Object>();
		List<String> deptList = new ArrayList<String>();
		if(deptid!=null&&!"".equals(deptid)){
			deptList = new ArrayList<String>(Arrays.asList(deptid.split(",")));
		}
		List<Share> dtList = dao
				.find("select csFkid from Share where csClid=? and csType=?",
						id, "1");
		List<String> userList = new ArrayList<String>();
		if(userid!=null&&!"".equals(userid)){
			userList = new ArrayList<String>(Arrays.asList(userid.split(",")));
		}
		List<Share> uiList = dao
				.find("select csFkid from Share where csClid=? and csType=?",
						id, "2");
		
		List<Share> dList = dao
				.find("select csFkid from Share where csClid=? and csType=?",
						id, "1");
		List<Share> uList = dao
				.find("select csFkid from Share where csClid=? and csType=?",
						id, "2");
		//原来的部门减去现在的部门
		dtList.removeAll(deptList);
		//现在的部门减去原来的部门
		deptList.removeAll(dList);
		//原来的人员减去现在的人员
		uiList.removeAll(userList);
		//现在的人员减去原来的人员
		userList.removeAll(uList);
		for (int i = 0, l = dtList.size(); i < l; i++) {
			dao.delete("delete Share where csClid=? and csFkid=? ",
					id, dtList.get(i));
		}
		for (int i = 0; i < deptList.size(); i++) {
			String fkid = deptList.get(i).toString();
			if (fkid != null && !"".equals(fkid)) {
				Share s = new Share();
				s.setCsClid(id);
				s.setCsFkid(fkid);
				s.setCsType("1");
				c.add(s);
		
			}
		
		}
		
		for (int i = 0, l = uiList.size(); i < l; i++) {
			dao.delete(
					"delete Share where csClid=? and csFkid=?  and csType=? ",
					id, uList.get(i), "2");
		}
		for (int i = 0; i < userList.size(); i++) {
			String fkid = userList.get(i).toString();
			if (fkid != null && !"".equals(fkid)) {
				Share s = new Share();
				s.setCsClid(id);
				s.setCsFkid(fkid);
				s.setCsType("2");
				c.add(s);
			}
		
		}
		
		if (dao.saveOrUpdateAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}
	@Override
	public DataGrid lookUpInfo(PageParam param, Info o) {
DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer("from Info  where 1=1 ");
		Map<String, Object> map=new HashMap<String, Object>();	
		
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb, map));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by clCreateTime desc");
		}
		
		List<Info> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), map);
		
		for ( Info r : rows) {
				
			r.setClType(MyCache.getInstance().getSelectValue(r.getClType()));
			r.setClSource(MyCache.getInstance().getSelectValue(r.getClSource()));
			r.setClGrade(MyCache.getInstance().getSelectValue(r.getClGrade()));
			r.setClStatus(MyCache.getInstance().getSelectValue(r.getClStatus()));
			r.setClStaje(MyCache.getInstance().getSelectValue(r.getClStaje()));
			r.setClRelation(MyCache.getInstance().getSelectValue(r.getClRelation()));
			r.setClIndustry(MyCache.getInstance().getSelectValue(r.getClIndustry()));
			r.setClStaff(MyCache.getInstance().getTrueName(r.getClStaff()));
		}
		
		data.setRows(rows);

		return data;
	}
	
}
