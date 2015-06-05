/**  
 * @Project: jxoa
 * @Title: MeetingServiceImpl.java
 * @Package com.oa.manager.common_platform.service.impl
 * @date 2013-5-9 上午10:29:47
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Mapping;
import com.oa.manager.common_platform.bean.Meeting;
import com.oa.manager.common_platform.bean.Room;
import com.oa.manager.common_platform.service.IMeetingService;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：MeetingServiceImpl 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2013-5-9
 * 上午10:29:47
 * 
 */
@Service
public class MeetingServiceImpl extends BaseServiceImpl implements
		IMeetingService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectMeeting(PageParam param, Meeting lv) {
		
		DataGrid data=new DataGrid();
		StringBuffer sb = new StringBuffer("from Meeting  where 1=1 ");
		List list = new ArrayList();
		// 查询条件

		if (lv.getMName() != null && !"".equals(lv.getMName())) {
			sb.append(" and MName like ? ");
			list.add("%"+lv.getMName()+"%");

		}
		if (lv.getMType() != null && !"".equals(lv.getMType())) {
			
			sb.append(" and MType = ? ");
			list.add(lv.getMType());
		
	}
		if (lv.getMRid() != null && !"".equals(lv.getMRid())) {
			
				sb.append(" and MRid = ? ");
				list.add(lv.getMRid());
			
		}
		if (lv.getMDate() != null && !"".equals(lv.getMDate())) {
			sb.append(" and MDate >= ? ");
			list.add(lv.getMDate());
		}
		if (lv.getMDate2() != null) {
			sb.append(" and MDate <= ? ");
			list.add(lv.getMDate2());
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		
		
		
		if (StringUtils.isNotBlank(param.getSort())) {
			
			param.appendOrderBy(sb);//排序

		} else {
			sb.append(" order by MDate desc");
		}
		
		
		List<Meeting> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		
		for(Meeting m:rows){
			m.setMRid(dao.get(Room.class, m.getMRid()).getMrName());
			m.setMType(dao.get(ListValues.class, m.getMType()).getListValue());
			m.setMUid(MyCache.getInstance().getTrueName(m.getMUid()));
			}
		
		
		data.setRows(rows);
		
		return data;

	}

	public DataGrid selectMeetingTome(PageParam param, Meeting lv) {
		
		DataGrid data=new DataGrid();
		StringBuffer sb = new StringBuffer("from Meeting me,Mapping mp where me.id=mp.mpMtid and (mp.mpFkid=? or mp.mpFkid=? )");
		List list = new ArrayList();
		Member me = ServletUtil.getMember();
		list.add(me.getDeptId());
		list.add(me.getId());
		// 查询条件

		if (lv.getMName() != null && !"".equals(lv.getMName())) {
			sb.append(" and me.MName like ? ");
			list.add("%" + lv.getMName() + "%");

		}
		if (lv.getMRid() != null && !"".equals(lv.getMRid())) {
			
				sb.append(" and  me.MRid = ? ");
				list.add(lv.getMRid());
			
		}
		if (lv.getMDate() != null && !"".equals(lv.getMDate())) {
			sb.append(" and me.MDate >= ? ");
			list.add(lv.getMDate());
		}
		if (lv.getMDate2() != null) {
			sb.append(" and me.MDate <= ? ");
			list.add(lv.getMDate2());
		}
		data.setTotal((Long)dao.findUniqueOne("select count(distinct me.id) "+sb,list));
		
		
		
		
		if (StringUtils.isNotBlank(param.getSort())) {
//			param.appendOrderBy(sb);//排序

		} else {
			sb.append(" order by me.MDate desc");
		}
		
		
		List<Meeting> rows=dao.findPage("select distinct me "+sb.toString(),param.getPage(),param.getRows(),list);
		
		for(Meeting m:rows){
			m.setMRid(dao.get(Room.class, m.getMRid()).getMrName());
			m.setMType(dao.get(ListValues.class, m.getMType()).getListValue());
			m.setMUid(MyCache.getInstance().getTrueName(m.getMUid()));
			}
		
		
		data.setRows(rows);
		
		return data;

	}

	@Override
	public List selectRoom() {
		return dao.find("from Room");

	}

	public String addMeeting(Meeting m) {
		long sta = Long.valueOf(m.getMStatime().replaceAll("[:]", ""));
		long end = Long.valueOf(m.getMEndtime().replaceAll("[:]", ""));
		long result = end - sta;

		if (result > 0) {
			List<Meeting> me = dao
					.find("from Meeting where MDate=? and MRid=? and MStatime<=? and MEndtime>=?",
							m.getMDate(), m.getMRid(),
							m.getMEndtime(), m.getMStatime());

//			select meeting0_.id as id1_24_, meeting0_.m_type as m_type2_24_, meeting0_.m_name as m_name3_24_, meeting0_.m_host as m_host4_24_, meeting0_.m_date as m_date5_24_, meeting0_.m_statime as m_statim6_24_, meeting0_.m_endtime as m_endtim7_24_, meeting0_.m_rid as m_rid8_24_, meeting0_.m_summary as m_summar9_24_, meeting0_.m_remark as m_remar10_24_, meeting0_.m_published as m_publi11_24_, meeting0_.m_uid as m_uid12_24_ from mt_meeting meeting0_ 
//			where meeting0_.m_date='2013-10-17' and meeting0_.m_rid='402881e63f3b23a2013f3b26e0540002' and meeting0_.m_statime<='09:06:04'<=meeting0_.m_endtime and meeting0_.m_endtime>='10:06:11'>=meeting0_.m_statime
			
			if (me.size() == 0) {
				String[] dept=null;
				Collection c = new ArrayList();
				String id = (String) dao.saveReturnId(m);
				String deptid = m.getDeptid();
				String userid = m.getUserid();
				if (deptid != null && !"".equals(deptid)) {
					dept = deptid.split(",");

					for (int i = 0; i < dept.length; i++) {
						if (dept[i] != null && !"".equals(dept[i])) {
							Mapping mp = new Mapping();
							mp.setMpMtid(id);
							mp.setMpFkid(dept[i]);
							mp.setMpType("1");
							c.add(mp);

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

							Mapping mp = new Mapping();
							mp.setMpMtid(id);
							mp.setMpFkid(user[i]);
							mp.setMpType("2");
							c.add(mp);
						}

					}
				}
				
				saveMsgWarn(2, id, user, dept);
				if (dao.saveOrUpdateAll(c)) {
					return MsgConfig.MSG_KEY_SUCCESS;
				} else {
					return MsgConfig.MSG_KEY_FAIL;
				}
			} else {

				return "msg.room.status";
			}
		} else {
			return "msg.meeting.date";
		}
	}
	@Override
	public String updateMeeting(Meeting m) {
		long sta = Long.valueOf(m.getMStatime().replaceAll("[:]", ""));
		long end = Long.valueOf(m.getMEndtime().replaceAll("[:]", ""));
		long result = end - sta;

		if (result > 0) {
			List<Meeting> mee = dao
			.find("from Meeting where MDate=? and MRid=? and MStatime<=? and MEndtime>=? and id<>?" ,
					m.getMDate(), m.getMRid(),
					m.getMEndtime(), m.getMStatime(),m.getId());
			if (mee.size() == 0) {
				Collection<Object> c = new ArrayList<Object>();
				String userid = null;
				String deptid = null;
				if( m.getUserid()!=null&&!"".equals( m.getUserid())){
					userid = m.getUserid();
				}
				if( m.getDeptid()!=null&&!"".equals( m.getDeptid())){
					deptid = m.getDeptid();
				}
				
				
				Meeting me = dao.get(Meeting.class, m.getId());
				me.setMDate(m.getMDate());
				me.setMRid(m.getMRid());
				me.setMType(m.getMType());
				me.setMName(m.getMName());
				me.setMHost(m.getMHost());
				me.setMStatime(m.getMStatime());
				me.setMEndtime(m.getMEndtime());
				me.setMSummary(m.getMSummary());
				me.setMRemark(m.getMRemark());
				c.add(me);
				List deptList = new ArrayList();
				if(deptid!=null&&!"".equals(deptid)){
					deptList = new ArrayList(Arrays.asList(deptid.split(",")));
				}
				
				List dtList = dao
						.find("select mpFkid from Mapping where mpMtid=? and mpType=?",
								m.getId(), "1");
				List userList = new ArrayList();
				if(userid!=null&&!"".equals(userid)){
					userList = new ArrayList(Arrays.asList(userid.split(",")));
				}
				List uiList = dao
						.find("select mpFkid from Mapping where mpMtid=? and mpType=?",
								m.getId(), "2");

				List dList = dao
						.find("select mpFkid from Mapping where mpMtid=? and mpType=?",
								m.getId(), "1");
				List uList = dao
						.find("select mpFkid from Mapping where mpMtid=? and mpType=?",
								m.getId(), "2");
				//原来的部门减去现在的部门
				dtList.removeAll(deptList);
				//现在的部门减去原来的部门
				deptList.removeAll(dList);
				//原来的人员减去现在的人员
				uiList.removeAll(userList);
				//现在的人员减去原来的人员
				userList.removeAll(uList);
				
				
				
				// 发送信息
				saveMsgWarn(2, m.getId(), userList, deptList);

				for (int i = 0, l = dtList.size(); i < l; i++) {
					dao.delete("delete Mapping where mpMtid=? and mpFkid=? ",
							m.getId(), dtList.get(i));
				}
				for (int i = 0; i < deptList.size(); i++) {
					String fkid = deptList.get(i).toString();
					if (fkid != null && !"".equals(fkid)) {
						Mapping mp = new Mapping();

						mp.setMpMtid(m.getId());
						mp.setMpFkid(fkid);
						mp.setMpType("1");
						c.add(mp);

					}

				}

				for (int i = 0, l = uiList.size(); i < l; i++) {
					dao.delete(
							"delete Mapping where mpMtid=? and mpFkid=?  and mpType=? ",
							m.getId(), uList.get(i), "2");
				}
				for (int i = 0; i < userList.size(); i++) {
					String fkid = userList.get(i).toString();
					if (fkid != null && !"".equals(fkid)) {
						Mapping mp = new Mapping();

						mp.setMpMtid(m.getId());
						mp.setMpFkid(fkid);
						mp.setMpType("2");
						c.add(mp);
					}

				}

				if (dao.saveOrUpdateAll(c)) {
					return MsgConfig.MSG_KEY_SUCCESS;
				} else {
					return MsgConfig.MSG_KEY_FAIL;
				}
			} else {

				return "msg.room.status";
			}
		} else {
			return "msg.meeting.date";
		}
	}

	@Override
	public boolean deleteMeeting(String[] ids) {
		for (String id : ids) {
			dao.delete("delete Mapping where mpMtid=?", id);
			Meeting m = dao.get(Meeting.class, id);
			dao.delete(m);
		}

		return true;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map selectMapping(String id) {

		Meeting meeting = dao.get(Meeting.class, id);
		List<Mapping> li = dao.find("from Mapping where mpMtid=? ", id);
		List deptIds = new ArrayList();		
		List userIds = new ArrayList();
		for (Mapping m : li) {
			if (m.getMpType().equals("1")) {
				deptIds.add(m.getMpFkid());
			} else {
				userIds.add(m.getMpFkid());
			}
		}
		Map map = new HashMap();
		if(meeting!=null){
			map.put("rName", dao.get(Room.class, meeting.getMRid()).getMrName());
		}else{
			map.put("rName", "");
		}
		map.put("meeting", meeting);
		map.put("deptIds", StringUtils.join(deptIds, ","));
		map.put("userIds", StringUtils.join(userIds, ","));
		return map;

	}
	
	public void saveMeetingForFlow(Meeting m) {
		
		String[] dept=null;
		Collection c = new ArrayList();
		String id = (String) dao.saveReturnId(m);
		String deptid = m.getDeptid();
		String userid = m.getUserid();
		if (deptid != null && !"".equals(deptid)) {
			dept = deptid.split(",");

			for (int i = 0; i < dept.length; i++) {
				if (dept[i] != null && !"".equals(dept[i])) {
					Mapping mp = new Mapping();
					mp.setMpMtid(id);
					mp.setMpFkid(dept[i]);
					mp.setMpType("1");
					c.add(mp);

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

					Mapping mp = new Mapping();
					mp.setMpMtid(id);
					mp.setMpFkid(user[i]);
					mp.setMpType("2");
					c.add(mp);
				}

			}
		}
		
		saveMsgWarn(2, id, user, dept);
		dao.saveOrUpdateAll(c);
			
	}
	
	
	
//***********************************************安卓************************************************
	@Override
	public Map<String, Object> selectMeetingById(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Meeting meeting = dao.get(Meeting.class, id);
		if(meeting==null){
			return map;
		}
		map.put("type",MyCache.getInstance().getSelectValue(meeting.getMType()));//会议类型
		map.put("title",meeting.getMName());//会议主题
		map.put("host",meeting.getMHost());//主持人
		map.put("date",meeting.getMDate());//会议日期 xxxx-xx-xx
		map.put("start",meeting.getMStatime());//开始时间
		map.put("end",meeting.getMEndtime());//结束时间
		map.put("meetroom", dao.get(Room.class, meeting.getMRid()).getMrName());//会议室
		@SuppressWarnings("unchecked")
		List<Mapping> li = dao.find("from Mapping where mpMtid=? ", id);
		if(li.size()>0){
			List<String> deptIds = new ArrayList<String>();//部门ids		
			List<String> userIds = new ArrayList<String>();//用户ids
			for (Mapping m : li) {
				if (m.getMpType().equals("1")) {
					deptIds.add(m.getMpFkid());
				}else if(m.getMpType().equals("2")) {
					userIds.add(m.getMpFkid());
				}
			}
			String d = "";
			if(deptIds.size()>0){
				for(String s:deptIds){
					d+=MyCache.getInstance().getDeptName(s)+",";
				}
				map.put("deptNames",d.substring(0,d.length()-1));//参与部门
			}else{
				map.put("deptNames",d);//参与部门
			}
			String u = "";
			if(userIds.size()>0){
				for(String s:userIds){
				   u+=MyCache.getInstance().getTrueName(s)+",";
				}
				map.put("userNames",u.substring(0,u.length()-1));//参与人
			}else{
				map.put("userNames",u);//参与人
			}
		}else{
			map.put("deptNames","");//参与部门
			map.put("userNames","");//参与人
		}
		return map;
	}
	
	
	
	

	
	
	
	
	
}
