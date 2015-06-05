/**  
 * @Project: jtoa
 * @Title: AttendanceServiceImpl.java
 * @Package com.oa.manager.personnel.service.impl
 * @date 2013-11-6 上午11:09:46
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.service.impl;

 

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.PerSchedule;
import com.oa.manager.personnel.bean.XtAttendanceClose;
import com.oa.manager.personnel.bean.XtAttendanceSign;
import com.oa.manager.personnel.bean.XtAttendanceTime;
import com.oa.manager.personnel.bean.XtAttendanceTimeUser;
import com.oa.manager.personnel.service.IAttendanceService;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.bean.SyUserRole;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：AttendanceServiceImpl
 * 功能：人事管理-考勤管理
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-11-6 上午11:09:46
 *
 */
@SuppressWarnings("rawtypes") 
@Service
public class AttendanceServiceImpl extends BaseServiceImpl implements
		IAttendanceService {
 
	/**
	 * 查询考勤登记时间设置页面
	 * @param param
	 * @param xttime
	 * @return
	 */
	public DataGrid selectAttendanceTimeLoad(PageParam param,XtAttendanceTime xttime ){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtAttendanceTime xttime  where 1=1 ");
		List<Object> list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(xttime.getName())){
			sb.append(" and xttime.name like ? ");
			list.add("%"+xttime.getName()+"%");
		}
		 
		data.setTotal((Long)dao.findUniqueOne("select count(*) "+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by xttime.createtime desc");
		}
		List<XtAttendanceTime> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtAttendanceTime l:rows){
			l.setCreateuser(MyCache.getInstance().getTrueName(l.getCreateuser()));
		}
		data.setRows(rows);
		
		return data;
		
		
	}

	/**
	 * 查询考勤设置信息
	 */
	@Override
	public XtAttendanceTime selectAttendanceTime(String id) {
		XtAttendanceTime attendanceTime = (XtAttendanceTime)dao.findOne("from XtAttendanceTime where id =? ",id);
		attendanceTime.setCreateuser(MyCache.getInstance().getTrueName(attendanceTime.getCreateuser()));
		
		return attendanceTime;
		 
	}

	/**
	 * 增加考勤时间设置
	 */
	@Override
	public String addAttendanceTime(XtAttendanceTime attendanceTime) {
		Member me=ServletUtil.getMember();
		attendanceTime.setCreateuser(me.getId());
		attendanceTime.setCreatetime(new Timestamp(new Date().getTime()));
		//判断修改还是添加
	//	if(attendanceTime.getId()==null||"".equals(attendanceTime.getId())){
			if(dao.save(attendanceTime)){
				saveLog("增加考勤时间设置", "设置名称："+attendanceTime.getName());
				return  MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		/*}else{
			if(dao.update(attendanceTime)){
				saveLog("增加考勤时间设置", "设置名称："+attendanceTime.getName());
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		} */
	}

	/**
	 * 修改考勤设置
	 */
	@Override
	public String updateAttendanceTime(XtAttendanceTime attendanceTime) {
		 
		Member me=ServletUtil.getMember();
		attendanceTime.setCreateuser(me.getId());
		attendanceTime.setCreatetime(new Timestamp(new Date().getTime()));
		//判断修改还是添加
		/*if(attendanceTime.getId()==null||"".equals(attendanceTime.getId())){
			if(dao.save(attendanceTime)){
				saveLog("修改上班时间设置", "设置名称："+attendanceTime.getName());
				return  MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{*/
			if(dao.update(attendanceTime)){
				saveLog("修改上班时间设置", "设置名称："+attendanceTime.getName());
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		//} 
	}
	
	/**
	 * 保存用户考勤登记信息
	 */
	public String saveSignAttendance(String type){
		
		Member me=ServletUtil.getMember();
		//获取该用户考勤时间设置
		Object obj = dao.findOne("select attimeId from XtAttendanceTimeUser where userId =? ", me.getId());
		if(obj==null){
			return MsgConfig.MSG_KEY_FAIL;
		}else{
			XtAttendanceTime att = (XtAttendanceTime)dao.findOne(" from XtAttendanceTime t where t.id =? ", obj.toString());
		
			//List list = dao.find(" from XtAttendanceTime ");
			if(att!=null){
				//XtAttendanceTime att = (XtAttendanceTime)list.get(0);
				 
				XtAttendanceSign sign = new XtAttendanceSign();
				Date nowDate = new Date();
				//设置签到信息
				sign.setAttType(type);
				sign.setSigndate(DateUtil.date2String(nowDate, DateUtil.PATTERN_DATE)); 
				String nowtime = DateUtil.date2String(nowDate, DateUtil.PATTERN_TIME);
				sign.setSigntime(nowtime);
				sign.setSignuser(me.getId());
				//判断签到类型 第几次签到
				//判断是否迟到早退
				if(type.equals(MsgConfig.ATTENDANCE_TYPE_FS)){
					if(DateUtil.subtractTime( nowtime,att.getFirstup())>0){
						sign.setIslate(MsgConfig.ATTENDANCE_SIGN_LATE);
					} 
				}else if(type.equals(MsgConfig.ATTENDANCE_TYPE_FE)){
					if(DateUtil.subtractTime( att.getFirstdown(),nowtime)>0){
						sign.setIslate(MsgConfig.ATTENDANCE_SIGN_LEAVEEARLY);
					}
				}else if(type.equals(MsgConfig.ATTENDANCE_TYPE_SS)){
					if(DateUtil.subtractTime( nowtime,att.getSecondup())>0){
						sign.setIslate(MsgConfig.ATTENDANCE_SIGN_LATE);
					}
				}else if(type.equals(MsgConfig.ATTENDANCE_TYPE_SE)){
					if(DateUtil.subtractTime( att.getSeconddown(),nowtime)>0){
						sign.setIslate(MsgConfig.ATTENDANCE_SIGN_LEAVEEARLY);
					}
				}else if(type.equals(MsgConfig.ATTENDANCE_TYPE_TS)){
					if(DateUtil.subtractTime( nowtime,att.getThirdup())>0){
						sign.setIslate(MsgConfig.ATTENDANCE_SIGN_LATE);
					}
				}else if(type.equals(MsgConfig.ATTENDANCE_TYPE_TE)){
					if(DateUtil.subtractTime( att.getThirddown(),nowtime)>0){
						sign.setIslate(MsgConfig.ATTENDANCE_SIGN_LEAVEEARLY);
					}
				}  
				sign.setCreattime(DateUtil.currentTimestamp());
				if(dao.save(sign)){
					return MsgConfig.MSG_KEY_SUCCESS;
				}else{
					return MsgConfig.MSG_KEY_FAIL;
				}
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			} 
		}
	}
	
	/**
	 * 查询用户当天登记情况
	 * @return
	 */
	public Map<String, Object> selectSignAttendance(ModelMap map ){
		Member me=ServletUtil.getMember();
	 
		//获取该用户考勤时间设置
		Object obj = dao.findOne("select attimeId from XtAttendanceTimeUser where userId =? ", me.getId());
		if(obj==null){
			return map;
		}else{
			//查询今天是否是登录用户的休息日
			Timestamp nowTime = DateUtil.currentTimestamp();
			List l = dao.find("from XtAttendanceClose c where c.startdate<=? and c.enddate>=? and c.xtatimeid like ? ",nowTime,nowTime,"%"+obj.toString()+"%" );
			//现在是休息日
			if(l.size()>0){
				return map;
			}else{
				//考勤登记时间设置
				XtAttendanceTime attendanceTime = (XtAttendanceTime)dao.findOne(" from XtAttendanceTime t where t.id =? ", obj.toString());
				if(attendanceTime!=null){
					 //已经设置了考勤时间
					//XtAttendanceTime attendanceTime = (XtAttendanceTime)atttimes.get(0);
					map.put("att", attendanceTime);
					//获取可以签到的操作
					String nowtime = DateUtil.date2String(new Date(), DateUtil.PATTERN_TIME);
					long nowlong = DateUtil.string2long(nowtime,DateUtil.PATTERN_TIME);
					//获取考勤上下班允许的打卡时间
					long upStart = 1000*60*attendanceTime.getUpstart();
					long upEnd = 1000*60*attendanceTime.getUpend();
					long downStart = 1000*60*attendanceTime.getDownstart();
					long downEnd = 1000*60*attendanceTime.getDownend();
					//获取考勤登记时间点
					long fu = DateUtil.string2long(attendanceTime.getFirstup(),DateUtil.PATTERN_TIME);
					long fd = DateUtil.string2long(attendanceTime.getFirstdown(),DateUtil.PATTERN_TIME);
					long su = DateUtil.string2long(attendanceTime.getSecondup(),DateUtil.PATTERN_TIME);
					long sd = DateUtil.string2long(attendanceTime.getSeconddown(),DateUtil.PATTERN_TIME);
					long tu = DateUtil.string2long(attendanceTime.getThirdup(),DateUtil.PATTERN_TIME);
					long td = DateUtil.string2long(attendanceTime.getThirddown(),DateUtil.PATTERN_TIME);
					//第一次上班登记时间
					if((fu-upStart)<nowlong &&nowlong<(fu+upEnd)){
						map.put("fu", MsgConfig.ATTENDANCE_TYPE_FS);
					}
					//第一次下班登记时间
					if((fd-downStart)<nowlong &&nowlong<(fd+downEnd)){
						map.put("fd", MsgConfig.ATTENDANCE_TYPE_FE);
					}
					//第二次上班登记时间
					if((su-upStart)<nowlong &&nowlong<(su+upEnd)){
						map.put("su", MsgConfig.ATTENDANCE_TYPE_SS);
					}
					//第二次下班登记时间
					if((sd-downStart)<nowlong &&nowlong<(sd+downEnd)){
						map.put("sd", MsgConfig.ATTENDANCE_TYPE_SE);
					}
					//第三次上班登记时间
					if((tu-upStart)<nowlong &&nowlong<(tu+upEnd)){
						map.put("tu", MsgConfig.ATTENDANCE_TYPE_TS);
					}
					//第三次下班登记时间
					if((td-downStart)<nowlong &&nowlong<(td+downEnd)){
						map.put("td", MsgConfig.ATTENDANCE_TYPE_TE);
					} 
					//获取登录用户今天所有考勤登记
					List<XtAttendanceSign> list = dao.find(" from XtAttendanceSign where signuser=? and signdate=? ", me.getId(),DateUtil.date2String(new Date(), DateUtil.PATTERN_DATE));
					if(list.size()>0){
						//保存考勤记录
						for (XtAttendanceSign xtAttendanceSign : list) {
							if(xtAttendanceSign.getAttType().equals(MsgConfig.ATTENDANCE_TYPE_FS)){
								map.put("sign1", xtAttendanceSign);
							}else  if(xtAttendanceSign.getAttType().equals(MsgConfig.ATTENDANCE_TYPE_FE )){
								map.put("sign2", xtAttendanceSign);
							}else if(xtAttendanceSign.getAttType().equals(MsgConfig.ATTENDANCE_TYPE_SS )){
								map.put("sign3", xtAttendanceSign);
							}else if(xtAttendanceSign.getAttType().equals(MsgConfig.ATTENDANCE_TYPE_SE )){
								map.put("sign4", xtAttendanceSign);
							}else if(xtAttendanceSign.getAttType().equals(MsgConfig.ATTENDANCE_TYPE_TS )){
								map.put("sign5", xtAttendanceSign);
							}else if(xtAttendanceSign.getAttType().equals(MsgConfig.ATTENDANCE_TYPE_TE )){
								map.put("sign6", xtAttendanceSign);
							}
						}
					} 
				}  
			}
			
		}
		return map;
	}

	/**
	 * 保存补录考勤
	 * @param attendanceSign
	 * @return
	 */
	public String saveRepairSign(XtAttendanceSign  attendanceSign ){
		
		List list = dao.find(" from XtAttendanceSign where signuser=? and signdate =? and attType = ? ",attendanceSign.getSignuser(),attendanceSign.getSigndate(),attendanceSign.getAttType());
		//该用户该次考勤是否已经登记
		if(list.size()>0){
			return "msg.personnel.sign.have";
		}else{
			Member me=ServletUtil.getMember();
			attendanceSign.setRepairuser(me.getId());
			attendanceSign.setRepairtime(DateUtil.currentTimestamp());
			attendanceSign.setCreattime(DateUtil.currentTimestamp());
			if(dao.save(attendanceSign)){
				return MsgConfig.MSG_KEY_SUCCESS;
			}
			return MsgConfig.MSG_KEY_FAIL;
		} 
	}
	 
	/**
	 * 查询考勤记录
	 */
	public DataGrid selectSign(PageParam param,XtAttendanceSign attendanceSign , Date startDate,Date endDate){
		
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtAttendanceSign attendanceSign  where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(attendanceSign.getSignuser())){
			sb.append(" and attendanceSign.signuser = ? ");
			list.add(attendanceSign.getSignuser());
		}
		if(StringUtils.isNotBlank(attendanceSign.getAttType())){
			sb.append(" and attendanceSign.attType = ? ");
			list.add(attendanceSign.getAttType());	
		}
		if(StringUtils.isNotBlank(attendanceSign.getIslate())){
			sb.append(" and attendanceSign.islate = ? ");
			list.add(attendanceSign.getIslate());	
		}
		//SELECT id,Concat(s.signdate," ",s.signtime ) as stime from xt_attendance_sign s   where  Concat(s.signdate," ",s.signtime )<"2013-11-20 08:47:47"
		if(startDate!=null){
			sb.append(" and Concat(attendanceSign.signdate,' ',attendanceSign.signtime) >=? ");
			list.add(DateUtil.date2String(startDate));	
		}
		if(endDate!=null){
			sb.append(" and Concat(attendanceSign.signdate,' ',attendanceSign.signtime) <=? ");
			list.add(DateUtil.date2String(endDate)); 
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*) "+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by attendanceSign.creattime desc");
		}
		List<XtAttendanceSign> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtAttendanceSign l:rows){
			l.setSignuser(MyCache.getInstance().getTrueName(l.getSignuser()));
		}
		data.setRows(rows);
		
		return data;
	}

	/**
	 * 根据id获取签到记录
	 */
	public XtAttendanceSign selectAttendanceSignById(String id){
		
		XtAttendanceSign sign = (XtAttendanceSign)dao.findOne(" from XtAttendanceSign where id = ? " , id);
		return sign;
	}
	
	/**
	 * 根据id修改考勤登记记录
	 * @param sign
	 * @return
	 */
	public String updateSign(XtAttendanceSign sign){
		Member me=ServletUtil.getMember();
		sign.setRepairuser(me.getId());
		sign.setRepairtime(DateUtil.currentTimestamp());
		sign.setCreattime(DateUtil.currentTimestamp());
		//修改考勤记录
		if(dao.update(sign)){
			saveLog("修改考勤登记记录", "修改"+sign.getId()+"考勤登记记录");
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		} 
	}
	
	@Override
	public boolean deleteAttendanceTimes(String[] ids) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 删除考勤记录
	 */
	public boolean deleteSign(String[] ids){
		
		for(String id:ids){
			dao.delete("delete XtAttendanceSign where id=?", id);
		}
		saveLog("删除考勤登记记录", "删除"+ids.length+"条");
		return true;
	}
	
	
	/**
	 * 考勤统计查询
	 * @param param
	 * @param attendanceSign
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectSignStatistics(PageParam param,String userIds,String deptId, Date startDate,Date endDate){
		
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" ");
		List<Object> list =new ArrayList<Object>();
		StringBuffer Month = new StringBuffer("");
		//查询条件
		
		//登记人
		if(StringUtils.isNotBlank(userIds)){
			String[] ids = userIds.split(",");
			sb.append(" and s.signuser in (");
			for (int i = 0; i < ids.length; i++) {
				if(i<(ids.length-1)){
					sb.append("'").append(ids[i]).append("',");
				}else{
					sb.append("'").append(ids[i]).append("'");
				} 
			}
			sb.append(" )"); 
		}
		//部门
		if(StringUtils.isNotBlank(deptId)){
			//获取部门人员id
			List<String> uid=dao.find("select id from SyUsers where deptId=?",deptId);
			sb.append(" and s.signuser in (");
			for (int i = 0; i < uid.size(); i++) {
				if(i<(uid.size()-1)){
					sb.append("'").append(uid.get(i)).append("',");
				}else{
					sb.append("'").append(uid.get(i)).append("'");
				} 
			}
			sb.append(" )"); 
		}
		 
 		if(startDate!=null){
			sb.append(" and Concat(s.signdate,' ',s.signtime) >=? ");
			String s = DateUtil.date2String(startDate);
			list.add(s);	
			Month.append(s);
		}else{
			sb.append(" and  Concat(s.signdate,' ',s.signtime) >=? ");
			String m = DateUtil.currentMonthDateToString();
			list.add(m);
			Month.append(m);
		}
		Month.append("--");
		if(endDate!=null){
			sb.append(" and Concat(s.signdate,' ',s.signtime) <=? ");
			String e =DateUtil.date2String(endDate);
			list.add(e); 
			Month.append(e);
		}else{
			sb.append(" and Concat(s.signdate,' ',s.signtime) <=? ");
			String m =DateUtil.currentDateTimeToString();
			list.add(m); 
			Month.append(m);
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(distinct s.signuser )  from XtAttendanceSign s where 1=1 "+sb,list));
		StringBuffer sb1=new StringBuffer("");
		sb1.append(" GROUP BY  s.signuser ");
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb1.append(" order by s.creattime desc");
		}
		//统计考勤登记
		List<Map<String,Object>> rows=dao.findPage(" select new Map(s.signuser as signuser , count(id) as signCount ) from XtAttendanceSign s where 1=1 "+sb.toString()+sb1.toString(),param.getPage(),param.getRows(),list);
		
		for(Map<String,Object> map:rows){
			String userid = (String)map.get("signuser");
			map.put("userid",userid);
			map.put("signuser", MyCache.getInstance().getTrueName(userid));
			//获取正常考勤登记数量 
			map.put("normalCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='0' "+sb,list));
			//迟到登记数量 
			map.put("lateCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='1' "+sb,list));
			//早退登记数量
			map.put("earlyCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='2' "+sb,list));
			//请假登记数量
			map.put("leaveCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='3' "+sb,list));
			//旷工登记数量
			map.put("absenteeismCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='4' "+sb,list));
			
			//外出登记数量
			map.put("outCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='5' "+sb,list));
			//其他
			map.put("otherCount",dao.findUniqueOne("select count(islate)   from XtAttendanceSign s where signuser = '"+userid+"' and islate='6' "+sb,list));
			//查询时间段
			map.put("times", Month);
			//未登记数量  计算两个日期之间的天数 -
		}
		data.setRows(rows);
		
		return data;
	}
	/**
	 * 查询times时间段内user考勤登记记录
	 * @param userid
	 * @param times
	 * @return
	 */
	public DataGrid selectSigns(PageParam param,String userid,String times){
		
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtAttendanceSign attendanceSign  where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		//查询条件
		if(StringUtils.isNotBlank(userid)){
			sb.append(" and attendanceSign.signuser = ? ");
			list.add(userid);
		} 
		if(StringUtils.isNotBlank(times)){
			String[] time = times.split("--");
			 
			sb.append(" and Concat(attendanceSign.signdate,' ',attendanceSign.signtime) >=? ");
			sb.append(" and Concat(attendanceSign.signdate,' ',attendanceSign.signtime) <=? ");
			list.add(time[0]);
			list.add(time[1]);
		} 
 		/*if(startDate!=null){
			sb.append(" and Concat(attendanceSign.signdate,'',attendanceSign.signtime) >=? ");
			list.add(DateUtil.date2String(startDate));	
		}
		if(endDate!=null){
			sb.append(" and Concat(attendanceSign.signdate,'',attendanceSign.signtime) <=? ");
			list.add(DateUtil.date2String(endDate)); 
		}*/
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by attendanceSign.signdate desc");
		}
		List<XtAttendanceSign> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtAttendanceSign l:rows){
			l.setSignuser(MyCache.getInstance().getTrueName(l.getSignuser()));
		}
		data.setRows(rows);
		
		return data;
	}
	
	/**
	 * 查询用户列表
	 */
	public DataGrid selectUsers(PageParam param,String attendanceId,SyUsers user){
		
		DataGrid data=new DataGrid();
		//先查询出已具有考勤时间设置的用户id
		List<String> ids=dao.find("select ur.userId from XtAttendanceTimeUser ur ");
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuffer sb=new StringBuffer("from SyUsers u where ");
		if(ids.isEmpty()){
			sb.append(" 1=1 ");
		}else{
			sb.append(" u.id not in(:ids) ");
			map.put("ids",ids);
		}
		//查询条件
		if(StringUtils.isNotBlank(user.getUserName())){
			sb.append(" and u.userName like :userName ");
			map.put("userName", "%"+user.getUserName()+"%");
		}
		if(StringUtils.isNotBlank(user.getTrueName())){
			sb.append(" and u.trueName like :trueName ");
			map.put("trueName", "%"+user.getTrueName()+"%");
		}
		if(StringUtils.isNotBlank(user.getDeptId())&&!"0".equals(user.getDeptId())){
			sb.append(" and u.deptId = :deptId ");
			map.put("deptId",user.getDeptId());
		}
		if(user.getUserSex()!=null){
			sb.append(" and u.userSex = :userSex ");
			map.put("userSex", user.getUserSex());
		}
		if(user.getUserStatus()!=null){
			sb.append(" and u.userStatus = :userStatus ");
			map.put("userStatus", user.getUserStatus());
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,map));
		param.appendOrderBy(sb);//排序
		List<Map<String,Object>> rows=dao.findPage("select new Map(u.id as id,u.userName as userName, " +
				"u.userStatus as userStatus,u.trueName as trueName,u.userSex as userSex,u.deptId as deptId)"
				+sb.toString(),param.getPage(),param.getRows(),map);
		 
		for(Map<String,Object> m:rows){
			m.put("deptName",MyCache.getInstance().getDeptName((String)m.get("deptId")));
		 
		}		
		data.setRows(rows);
		return data;
		
	}
	
	/**
	 * 将登记时间批量分配给用户
	 * @param roleId
	 * @param ids
	 * @return
	 */
	public boolean addUserTime(String attendanceId,String[] ids){
		//等待添加的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			if(StringUtils.isNotBlank(id)){
				
				XtAttendanceTimeUser ur=new XtAttendanceTimeUser();
				ur.setAttimeId(attendanceId);
				ur.setUserId(id);
				c.add(ur);
			}
		}
		return dao.saveOrUpdateAll(c);
	}
	/**
	 * 查询时间设置分配用户
	 * @param param
	 * @param attendanceId
	 * @param user
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectTimeUsers(PageParam param,String attendanceId,SyUsers user){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyUsers u ,XtAttendanceTimeUser ur where u.id=ur.userId  and ur.attimeId=? ");
		List list=new ArrayList();
		list.add(attendanceId);
		//查询条件
		if(StringUtils.isNotBlank(user.getUserName())){
			sb.append(" and u.userName like ? ");
			list.add("%"+user.getUserName()+"%");
		}
		if(StringUtils.isNotBlank(user.getTrueName())){
			sb.append(" and u.trueName like ? ");
			list.add("%"+user.getTrueName()+"%");
		}
		if(StringUtils.isNotBlank(user.getDeptId())&&!"0".equals(user.getDeptId())){
			sb.append(" and u.deptId = ? ");
			list.add(user.getDeptId());	
		}
		if(user.getUserSex()!=null){
			sb.append(" and u.userSex = ? ");
			list.add(user.getUserSex());	
		}
		if(user.getUserStatus()!=null){
			sb.append(" and u.userStatus = ? ");
			list.add(user.getUserStatus());	
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(ur.id as id," +
				"u.userName as userName,u.userStatus as userStatus,u.trueName as trueName,u.userSex as userSex,u.deptId as deptId)"
				+sb.toString(),param.getPage(),param.getRows(),list);
		 
		for(Map<String,Object> map:rows){
			map.put("deptName",MyCache.getInstance().getDeptName((String)map.get("deptId")));
		 
		}		
		
		data.setRows(rows);
		
		return data;
		
	}
	
	/**
	 * 根据id删除登记时间设置关联
	 */
	public boolean delUserTime(String[] ids){
		for(String id:ids){
			dao.delete(" delete XtAttendanceTimeUser where id=? ", id);
		}
		return true;
	}
	
	/**
	 * 根据用户id获取考勤登记时间设置id
	 * @param userId
	 * @return
	 */
	public List<String> selectTimeByUserId(String userId){
		
		return dao.find("select attimeId from XtAttendanceTimeUser where userId=? ",userId);
	}
	

	/**
	 * 删除考勤登记时间设置
	 */
	public boolean deleteAttendanceTime(String[] ids){
		
		for(String id:ids){
			dao.delete("delete XtAttendanceTime where id=?", id);
			dao.delete("delete XtAttendanceTimeUser where attimeId=?", id);
		}
		saveLog("删除考勤登记时间设置记录", "删除"+ids.length+"条");
		return true;
	}
	/**
	 * 保存休息日
	 * @param attendanceClose
	 * @return
	 */
	public String addCloseTimeSet(XtAttendanceClose attendanceClose,String attimeids){
		Member me=ServletUtil.getMember();
		attendanceClose.setCreateuser(me.getId());
		attendanceClose.setCreatetime(DateUtil.currentTimestamp());
	
		attendanceClose.setXtatimeid(attimeids); 
		dao.save(attendanceClose);
		saveLog("增加休息日", "增加休息日:"+attendanceClose.getName());
	  
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	/**
	 * 修改休息日
	 * @param attendanceClose
	 * @return
	 */
	public String updateCloseTimeSet(XtAttendanceClose attendanceClose,String attimeids){
		Member me=ServletUtil.getMember();
		attendanceClose.setCreateuser(me.getId());
		attendanceClose.setCreatetime(DateUtil.currentTimestamp());
	
		attendanceClose.setXtatimeid(attimeids); 
		dao.update(attendanceClose);
		saveLog("修改休息日", "修改休息日:"+attendanceClose.getName());
	 
		
		return MsgConfig.MSG_KEY_SUCCESS;
		 
		
	}
	
	/**
	 * 查询考勤登记时间设置
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectLookUp(PageParam param,XtAttendanceTime attendanceTime ){
		DataGrid data=new DataGrid();
		
		StringBuffer sb=new StringBuffer("from XtAttendanceTime t where 1=1 ");
		List list=new ArrayList();
		
		if(StringUtils.isNotBlank(attendanceTime.getName())){
			sb.append(" and t.name like ? ");
			list.add("%"+attendanceTime.getName()+"%");
		}
		if(StringUtils.isNotBlank(attendanceTime.getCreateuser())){
			sb.append(" and t.createuser = ? ");
			list.add(attendanceTime.getCreateuser());	
		}
		 
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(t.id as id," +
				"t.name as name,t.createuser as createuser) "
				+sb.toString(),param.getPage(),param.getRows(),list);
		 
		for(Map<String,Object> map:rows){
			map.put("createuser",MyCache.getInstance().getTrueName((String)map.get("createuser")));
		 
		}		
		
		data.setRows(rows);
		
		return data;
		
	}
	
	/**
	 * 显示休息日
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public List selectCloseTime(Date startDate,Date endDate){
		
		return dao.find("select new Map(s.id as id,s.name as name,s.startdate as start,s.enddate as end ) from XtAttendanceClose s " +
				" where s.startdate >=? and s.startdate <=?",startDate,endDate);
	}
	
	/**
	 * 根据休息日查询登记时间设置 
	 */
	public ModelMap selectTimeSetByTimeId(String tid,ModelMap map){
		 
		StringBuffer names =new StringBuffer(""); 
		String[] timeids = tid.split(","); 
		
		StringBuffer sb=new StringBuffer("select new Map(t.id as id,t.name as name) from XtAttendanceTime t where ");
		if(timeids==null){
			sb.append(" 1=1 ");
		}else{
			sb.append(" t.id  in(:ids) ");
			map.put("ids",timeids);
		}
		 //排序
		List<Map<String,Object>> rows=dao.find(sb.toString(),map);
		 
		for (int i = 0; i < rows.size(); i++) {
			if(i<(rows.size()-1)){
				names.append(rows.get(i).get("name")).append(",");
			}else{
				names.append(rows.get(i).get("name"));
			} 
		}	 
		map.put("tids", tid);
		map.put("tnames",names);
		 
		return map;
	}

	/**
	 * 查询休息日
	 */
	public DataGrid selectCloseTime(PageParam param,XtAttendanceClose c,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtAttendanceClose c where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(c.getCreateuser())){
			sb.append(" and c.createuser = ? ");
			list.add(c.getCreateuser());	
		}
		if(StringUtils.isNotBlank(c.getName())){
			sb.append(" and c.name like ? ");
			list.add("%"+c.getName()+"%");	
		}
		 
		if(startDate!=null){
			sb.append(" and c.startdate >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and c.startdate <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by c.startdate ");
		}
		
		
		List<XtAttendanceClose> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtAttendanceClose ac:rows){
			ac.setCreateuser(MyCache.getInstance().getTrueName(ac.getCreateuser()));
			 
		}
		data.setRows(rows);
	
		return data;
		
	}
	
	/**
	 * 删除休息日
	 */
	public boolean delCloseTime(String[] ids){
		for(String id:ids){
			dao.delete(" delete XtAttendanceClose where id=? ", id);
		}
		return true;
	}
}
