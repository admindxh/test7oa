/**  
 * @Project: jtoa
 * @Title: IAttendanceService.java
 * @Package com.oa.manager.personnel.service
 * @date 2013-11-6 上午11:07:25
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.service;
 

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.PerSchedule;
import com.oa.manager.personnel.bean.XtAttendanceClose;
import com.oa.manager.personnel.bean.XtAttendanceSign;
import com.oa.manager.personnel.bean.XtAttendanceTime;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：IAttendanceService
 * 功能：人事管理-考勤管理
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-11-6 上午11:07:25
 *
 */
public interface IAttendanceService extends IBaseService {
	
	/**
	 * 查询考勤登记时间设置页面
	 * @param param
	 * @param xttime
	 * @return
	 */
	public DataGrid selectAttendanceTimeLoad(PageParam param,XtAttendanceTime xttime );

	/**
	 * 查询考勤时间设置
	 * @param param
	 * @param attendanceTime
	 * @return
	 */ 
	public XtAttendanceTime selectAttendanceTime(String id  );

	/***
	 * 添加考勤时间
	 * @param attendanceTime
	 * @return
	 */
	public String addAttendanceTime(XtAttendanceTime attendanceTime);
	
	/***
	 * 修改考勤时间
	 * @param attendanceTime
	 * @return
	 */
	public String updateAttendanceTime(XtAttendanceTime attendanceTime);
	
	/***
	 * 删除考勤时间
	 * @param ids
	 * @return
	 */
	public boolean deleteAttendanceTimes(String[] ids);
	
	/**
	 * 保存考勤登记时间
	 * @param type 考勤登记类型
	 * @return
	 */
	public String saveSignAttendance(String type);
	
	
	/**
	 * 查询用户当天登记情况
	 * @return
	 */
	public Map<String, Object> selectSignAttendance(ModelMap map );
	
	/**
	 * 保存补录考勤
	 * @param attendanceSign
	 * @return
	 */
	public String saveRepairSign(XtAttendanceSign  attendanceSign );
	
 
	/***
	 * 查询考勤登记记录
	 * @param param
	 * @param attendanceSign
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectSign(PageParam param,XtAttendanceSign attendanceSign, Date startDate,Date endDate);
	
	/***
	 * 根据id获取考勤登记记录
	 * @param id
	 * @return
	 */
	public XtAttendanceSign selectAttendanceSignById(String id);
	
	/**
	 * 根据id修改考勤登记记录
	 * @param sign
	 * @return
	 */
	public String updateSign(XtAttendanceSign sign);
	
	/**
	 * 删除考勤登记记录
	 * @param ids
	 * @return
	 */
	public boolean deleteSign(String[] ids);
	
	/**
	 * 考勤统计查询
	 * @param param
	 * @param attendanceSign
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectSignStatistics(PageParam param, String userIds,String deptId, Date startDate,Date endDate);

	/**
	 * 查询times时间段内user考勤登记记录
	 * @param userid
	 * @param times
	 * @return
	 */
	public DataGrid selectSigns(PageParam param,String userid,String times);
	
	/**
	 * 考勤登记时间设置
	 * @param param
	 * @param attendanceId
	 * @param user
	 * @return
	 */
	public DataGrid selectUsers(PageParam param,String attendanceId,SyUsers user);
	
	/**
	 * 将角色批量分配给用户
	 * @param roleId
	 * @param ids
	 * @return
	 */
	public boolean addUserTime(String attendanceId,String[] ids);
	
	/**
	 * 查询时间设置分配用户
	 * @param param
	 * @param attendanceId
	 * @param user
	 * @return
	 */
	public DataGrid selectTimeUsers(PageParam param,String attendanceId,SyUsers user);

	/**
	 * 根据id删除登记时间设置关联
	 * @param ids
	 * @return
	 */
	public boolean delUserTime(String[] ids);
	
	/**
	 * 删除考勤登记时间设置
	 */
	public boolean deleteAttendanceTime(String[] ids);
	
	/**
	 * 根据用户id获取考勤登记时间设置id
	 * @param userId
	 * @return
	 */
	public List<String> selectTimeByUserId(String userId);
	
	/**
	 * 保存休息日
	 * @param attendanceClose
	 * @return
	 */
	public String addCloseTimeSet(XtAttendanceClose attendanceClose,String attimeids);
	/**
	 * 修改休息日
	 * @param attendanceClose
	 * @param attimeids
	 * @return
	 */
	public String updateCloseTimeSet(XtAttendanceClose attendanceClose,String attimeids);
	
	/**
	 * 根据休息日查询对应的考勤设置
	 * @param cid
	 * @param map
	 * @return
	 */
	public ModelMap selectTimeSetByTimeId(String tid,ModelMap map);
	
	/**
	 * 查询考勤登记时间设置
	 * @param param
	 * @param attendanceTime
	 * @return
	 */
	public DataGrid selectLookUp(PageParam param,XtAttendanceTime attendanceTime);
	/**
	 * 显示休息日
	 * @param start
	 * @param end
	 * @return
	 */
	public List selectCloseTime(Date start,Date end);
	
	/**
	 * 休息日查询
	 * @param param
	 * @param c
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectCloseTime(PageParam param,XtAttendanceClose c,Date startDate,Date endDate);

	/**
	 * 删除休息日
	 * @param ids
	 * @return
	 */
	public boolean delCloseTime(String[] ids);
}
