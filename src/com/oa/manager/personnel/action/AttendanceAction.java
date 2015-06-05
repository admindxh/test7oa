/**  
 * @Project: jtoa
 * @Title: AttendanceAction.java
 * @Package com.oa.manager.personnel.action
 * @date 2013-11-5 下午3:55:55
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ExcelUtils;
import com.oa.manager.personalOffice.bean.PerSchedule;
import com.oa.manager.personnel.bean.XtAttendanceClose;
import com.oa.manager.personnel.bean.XtAttendanceSign;
import com.oa.manager.personnel.bean.XtAttendanceTime;
import com.oa.manager.personnel.service.IAttendanceService;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：AttendanceAction
 * 功能：人事管理-考勤管理
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-11-5 下午3:55:55
 *
 */
@Controller
@RequestMapping("attendance")
public class AttendanceAction extends BaseAction  {
	
	@Autowired
	private IAttendanceService service;
	
	/**
	 * 考勤登记时间设置列表页面
	 * @return
	 */
	@RequiresPermissions("attendance:attendanceTimeLoad")
	@RequestMapping("attendanceTimeLoad")
	public String attendanceTimeLoad(){
		
		return "personnel/attendance/attendanceTimeLoad";
	}
	/**
	 * 查询考勤登记时间设置
	 * @return
	 */
	@RequiresPermissions("attendance:attendanceTimeLoad")
	@RequestMapping("attendanceTimeQuery")
	public ModelAndView attendanceTimeQuery(PageParam param,XtAttendanceTime attendanceTime){
		
		return ajaxJsonEscape(service.selectAttendanceTimeLoad(param, attendanceTime));
	}
	/***
	 * 考勤时间设置信息
	 * 上下班时间设置
	 * @return
	 */
	@RequiresPermissions("attendance:updateAttendanceTime")
	@RequestMapping("updateTimePage")
	public String attendancetimeUpdatePage(ModelMap map,String id){
		XtAttendanceTime atime = service.selectAttendanceTime( id);
	 
		//获取工作日
		if(atime.getWorktime()!=null & atime.getWorktime().length()>0){
			//获取工作日
			String[] days = atime.getWorktime().split(";");
			//判断工作日 1:1,2,3;2:1;3:2
			for (int i = 0; i < days.length; i++) {
				//获取周几
				String[] z = days[i].split(":"); 
				//获取上班时段
				String[] w = z[1].split(",");
				if("1".equals(z[0])){
					//周一
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w11",w[j] );
						}else if("2".equals(w[j])){
							map.put("w12",w[j] );
						}else if("3".equals(w[j])){
							map.put("w13",w[j] );
						}
					}
				}else if("2".equals(z[0])){
					//周二
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w21",w[j] );
						}else if("2".equals(w[j])){
							map.put("w22",w[j] );
						}else if("3".equals(w[j])){
							map.put("w23",w[j] );
						}
					}
				}else if("3".equals(z[0])){
					//周三
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w31",w[j] );
						}else if("2".equals(w[j])){
							map.put("w32",w[j] );
						}else if("3".equals(w[j])){
							map.put("w33",w[j] );
						}
					}
				}else if("4".equals(z[0])){
					//周四
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w41",w[j] );
						}else if("2".equals(w[j])){
							map.put("w42",w[j] );
						}else if("3".equals(w[j])){
							map.put("w43",w[j] );
						}
					}
				}else if("5".equals(z[0])){
					//周五
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w51",w[j] );
						}else if("2".equals(w[j])){
							map.put("w52",w[j] );
						}else if("3".equals(w[j])){
							map.put("w53",w[j] );
						}
					}
				}else if("6".equals(z[0])){
					//周六
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w61",w[j] );
						}else if("2".equals(w[j])){
							map.put("w62",w[j] );
						}else if("3".equals(w[j])){
							map.put("w63",w[j] );
						}
					}
				}else if("7".equals(z[0])){
					//周七
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w71",w[j] );
						}else if("2".equals(w[j])){
							map.put("w72",w[j] );
						}else if("3".equals(w[j])){
							map.put("w73",w[j] );
						}
					}
				}
			}
			
		}
		
		
		
		map.put("att", atime);
		
		return "personnel/attendance/attendanceTime";
	}
	

	/**
	 * 修改考勤登记设置
	 * @param attendanceTime
	 * @param work1
	 * @param work2
	 * @param work3
	 * @param work4
	 * @param work5
	 * @param work6
	 * @param work7
	 * @return
	 */
	@RequiresPermissions("attendance:updateAttendanceTime")
	 @RequestMapping("updateAttendanceTime")
	public ModelAndView updateAttendanceTime(XtAttendanceTime  attendanceTime,String[] work1,String[] work2,String[] work3
				,String[] work4,String[] work5,String[] work6,String[] work7){
			//获取工作日
			StringBuffer s = new StringBuffer("");
			if(work1!=null && work1.length>0){
				s.append("1:");
				for (int i = 0; i < work1.length; i++) {
					if(i<(work1.length-1)){
						s.append(work1[i]).append(",");
					}else{
						s.append(work1[i]);
					} 
				}
			}
			if(work2!=null && work2.length>0){
				if(s.toString().length()>0){
					s.append(";2:");	
				}else{
					s.append("2:");
				}
				
				for (int i = 0; i < work2.length; i++) {
					if(i<(work2.length-1)){
						s.append(work2[i]).append(",");
					}else{
						s.append(work2[i]);
					} 
				}
			}
			if(work3!=null && work3.length>0){
				if(s.toString().length()>0){
					s.append(";3:");	
				}else{
					s.append("3:");
				}
				
				for (int i = 0; i < work3.length; i++) {
					if(i<(work3.length-1)){
						s.append(work3[i]).append(",");
					}else{
						s.append(work3[i]);
					} 
				}
			}
			if(work4!=null && work4.length>0){
				if(s.toString().length()>0){
					s.append(";4:");	
				}else{
					s.append("4:");
				}
				
				for (int i = 0; i < work4.length; i++) {
					if(i<(work4.length-1)){
						s.append(work4[i]).append(",");
					}else{
						s.append(work4[i]);
					} 
				}
			}
			if(work5!=null && work5.length>0){
				if(s.toString().length()>0){
					s.append(";5:");	
				}else{
					s.append("5:");
				}
				
				for (int i = 0; i < work5.length; i++) {
					if(i<(work5.length-1)){
						s.append(work5[i]).append(",");
					}else{
						s.append(work5[i]);
					} 
				}
			}
			if(work6!=null && work6.length>0){
				if(s.toString().length()>0){
					s.append(";6:");	
				}else{
					s.append("6:");
				}
				
				for (int i = 0; i < work6.length; i++) {
					if(i<(work6.length-1)){
						s.append(work6[i]).append(",");
					}else{
						s.append(work6[i]);
					} 
				}
			}
			if(work7!=null && work7.length>0){
				if(s.toString().length()>0){
					s.append(";7:");	
				}else{
					s.append("7:");
				}
				
				for (int i = 0; i < work7.length; i++) {
					if(i<(work7.length-1)){
						s.append(work7[i]).append(",");
					}else{
						s.append(work7[i]);
					} 
				}
			}
		attendanceTime.setWorktime(s.toString());
		
		return  ajaxDone(service.updateAttendanceTime(attendanceTime)) ;
	} 
	
	 /**
	  * 显示登记时间设置详情
	  * @param map
	  * @param id
	  * @return
	  */
	@RequiresPermissions("attendance:showAttendanceTime")
	@RequestMapping("attendancetimeShowPage")
	public String attendancetimeShowPage(ModelMap map,String id){
		XtAttendanceTime atime = service.selectAttendanceTime( id);
	 
		//获取工作日
		if(atime.getWorktime()!=null & atime.getWorktime().length()>0){
			//获取工作日
			String[] days = atime.getWorktime().split(";");
			//判断工作日 1:1,2,3;2:1;3:2
			for (int i = 0; i < days.length; i++) {
				//获取周几
				String[] z = days[i].split(":"); 
				//获取上班时段
				String[] w = z[1].split(",");
				if("1".equals(z[0])){
					//周一
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w11",w[j] );
						}else if("2".equals(w[j])){
							map.put("w12",w[j] );
						}else if("3".equals(w[j])){
							map.put("w13",w[j] );
						}
					}
				}else if("2".equals(z[0])){
					//周二
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w21",w[j] );
						}else if("2".equals(w[j])){
							map.put("w22",w[j] );
						}else if("3".equals(w[j])){
							map.put("w23",w[j] );
						}
					}
				}else if("3".equals(z[0])){
					//周三
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w31",w[j] );
						}else if("2".equals(w[j])){
							map.put("w32",w[j] );
						}else if("3".equals(w[j])){
							map.put("w33",w[j] );
						}
					}
				}else if("4".equals(z[0])){
					//周四
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w41",w[j] );
						}else if("2".equals(w[j])){
							map.put("w42",w[j] );
						}else if("3".equals(w[j])){
							map.put("w43",w[j] );
						}
					}
				}else if("5".equals(z[0])){
					//周五
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w51",w[j] );
						}else if("2".equals(w[j])){
							map.put("w52",w[j] );
						}else if("3".equals(w[j])){
							map.put("w53",w[j] );
						}
					}
				}else if("6".equals(z[0])){
					//周六
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w61",w[j] );
						}else if("2".equals(w[j])){
							map.put("w62",w[j] );
						}else if("3".equals(w[j])){
							map.put("w63",w[j] );
						}
					}
				}else if("7".equals(z[0])){
					//周七
					for (int j = 0; j < w.length; j++) {
						if("1".equals(w[j])){
							map.put("w71",w[j] );
						}else if("2".equals(w[j])){
							map.put("w72",w[j] );
						}else if("3".equals(w[j])){
							map.put("w73",w[j] );
						}
					}
				}
			}
			
		} 
		map.put("att", atime);
		
		return "personnel/attendance/attendanceTimeShow";
	}
	
	/**
	 * 增加考勤时间设置页面
	 * @return
	 */
	@RequiresPermissions("attendance:addAttendanceTime")
	@RequestMapping("addAttendanceTimePage")
	public String addAttendanceTimePage(){
		
		return "personnel/attendance/attendanceTimeAdd";
	}
	/**
	 * 增加考勤时间设置
	 * @return
	 */
	@RequiresPermissions("attendance:addAttendanceTime")
	@RequestMapping("addAttendanceTime")
	public ModelAndView addAttendanceTime(XtAttendanceTime attendanceTime){
		  
		return ajaxDone(service.addAttendanceTime(attendanceTime));
	}
	 /**
	 * 上下班打卡页面
	 * @param map
	 * @return
	 */
	@RequiresPermissions("attendance:signAttendance")
	@RequestMapping("signAttendancePage")
	public String signAttendancePage(ModelMap map){
		//用户今天打卡记录
		map.put("signs", service.selectSignAttendance(map));
		
		return "personnel/attendance/signAttendance";
	}
	/**
	 * 上下班考勤登记保存
	 * @param type 考勤登记类型
	 * @return
	 */
	@RequiresPermissions("attendance:signAttendance")
	@RequestMapping("signAttendance")
	public ModelAndView signAttendance(String type){
		 
		return  ajaxDone(service.saveSignAttendance(type));
	}
	
	/**
	 * 考勤补录页面
	 * @param map
	 * @return
	 */
	@RequiresPermissions("attendance:repairSign")
	@RequestMapping("repairSignUpdatePage")
	public String repairSignUpdatePage(ModelMap map){
	   
		return "personnel/attendance/repairSign";
	}
	/**
	 * 考勤补录
	 * @param attendanceSign
	 * @return
	 */
	@RequiresPermissions("attendance:repairSign")
	@RequestMapping("repairSign")
	public ModelAndView repairSign(XtAttendanceSign  attendanceSign){
		
		return  ajaxDone(service.saveRepairSign(attendanceSign)) ;
	} 
	
	
	/**
	 * 修改考勤登记记录页面
	 * @param map
	 * @return
	 */
	@RequiresPermissions("attendance:updateSign")
	@RequestMapping("updateSignPage")
	public String updateSignPage(ModelMap map,String id){
		
		XtAttendanceSign sign = service.selectAttendanceSignById(id);
		if(sign!=null){
			map.put("sign", sign);
			return "personnel/attendance/updateSign";
		}else{
			return NODATA;
		}  
	}
	
	/***
	 * 修改考勤记录
	 * @param map
	 * @return
	 */
	@RequiresPermissions("attendance:updateSign")
	@RequestMapping("updateSign")
	public ModelAndView updateSign(ModelMap map,XtAttendanceSign sign){
	   
		return ajaxDone(service.updateSign(sign));
	}
	 /**
	  * 考勤记录页面
	  * @return
	  */
	@RequiresPermissions("attendance:attendanceLoad")
	@RequestMapping("attendanceLoad")
	public String attendanceLoad(){ 
		return "personnel/attendance/attendanceLoad";
	}
	/**
	 * 考勤记录查询
	 * @param param
	 * @param attendanceSign
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("attendance:attendanceLoad")
	@RequestMapping("attendanceQuery")
	public ModelAndView attendanceQuery(PageParam param,XtAttendanceSign attendanceSign,  Date startDate,Date endDate){
		  
		return ajaxJsonEscape(service.selectSign(param, attendanceSign, startDate, endDate));
	}

	/**
	 * 删除考勤记录
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("attendance:delSign")
	@RequestMapping("delSign")
	public ModelAndView deleteSign(String[] ids){
		
		return ajaxDone(service.deleteSign(ids));
	}
	/**
	 * 考勤统计
	 * @return
	 */
	@RequiresPermissions("attendance:signStatisticsLoad")
	@RequestMapping("signStatisticsLoad")
	public String signStatisticsLoad(){
		
		return "personnel/attendance/signStatisticsLoad";
	}
	/**
	 * 考勤统计查询
	 * @param param
	 * @param userIds
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("attendance:signStatisticsLoad")
	@RequestMapping("signStatisticsQuery")
	public ModelAndView signStatisticsQuery(PageParam param, String userIds,String deptId, Date startDate,Date endDate){
		
		return ajaxJsonEscape(service.selectSignStatistics(param,  userIds, deptId, startDate, endDate));
	}
	
	/**
	 * 考勤登记记录详情
	 * @param map
	 * @return
	 */
	@RequiresPermissions("attendance:showSign")
	@RequestMapping("showSign")
	public String showSign(ModelMap map,String id){
		
		XtAttendanceSign sign = service.selectAttendanceSignById(id);
		if(sign!=null){
			map.put("sign", sign);
			return "personnel/attendance/showSign";
		}else{
			return NODATA;
		}  
	}
	
	/**
	 * 查询登记时间设置已有用户 
	 * @return
	 */
	@RequiresPermissions("attendance:timeAddUsers")
	@RequestMapping("users/load")
	public String usersLoad(){
		 
		return "personnel/attendance/select_users";
	}
	/**
	 * 条件查询 用户，用于登记时间设置分配
	 * @param param
	 * @param attendanceId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("attendance:timeAddUsers")
	@RequestMapping("users/query")
	public ModelAndView queryUsers(PageParam param,String attendanceId,SyUsers user){
		
		return ajaxJsonEscape(service.selectUsers(param, attendanceId, user));
	
	}
	
	/**
	 * 将登记时间设置分配给用户
	 * @param attendanceId
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("attendance:timeAddUsers")
	@RequestMapping("timeAddUsers")
	public ModelAndView timeAddUsers(String attendanceId,String[] ids){
		
		return ajaxDone(service.addUserTime(attendanceId, ids));
		
	} 
	/**
	 * 查询考勤登记设置已有用户 
	 * @return
	 */
	@RequiresPermissions("attendance:timeUserLoad")
	@RequestMapping("timeUser/load")
	public String timeUsersLoad(){
		 
		return "personnel/attendance/select_time_users";
	
	}
	/**
	 * 查询考勤时间设置分配的用户
	 * @param param
	 * @param roleId
	 * @param user
	 * @return
	 */
	@RequiresPermissions("attendance:timeUserLoad")
	@RequestMapping("timeUser/query")
	public ModelAndView queryTimeUsers(PageParam param,String attendanceId,SyUsers user){
	
		
		return ajaxJsonEscape(service.selectTimeUsers(param, attendanceId, user));
	}
	/**
	 * 批量删除登记时间设置已分配的用户
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("attendance:delTimeUsers")
	@RequestMapping("delTimeUsers")
	public ModelAndView delTimeUsers(String[] ids){
		
		return ajaxDone(service.delUserTime( ids)); 
	}
	
	
	/**
	 * 删除考勤登记时间设置
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("attendance:delAttendanceTime")
	@RequestMapping("delAttendanceTime")
	public ModelAndView delAttendanceTime(String[] ids){
		
		return ajaxDone(service.deleteAttendanceTime(ids));
	}
	
	/**
	 * 显示休息日
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping("view/closeTimeQuery")
	public ModelAndView closeTimeQuery(Date start,Date end){
		
		return ajaxJsonEscape(service.selectCloseTime(start, end));
	}
	
	
	/***
	 * 休息日设置列表
	 * @return
	 */
	@RequiresPermissions("attendance:closeTimeLoad")
	@RequestMapping("closeTimeLoad")
	public String closeTimeLoad(){ 
		return "personnel/attendance/closeTimeLoad";
	}
	/**
	 * 休息日设置页面
	 * @return
	 */
	@RequiresPermissions("attendance:addcloseTime")
	@RequestMapping("closeTimeSetPage")
	public String closeTimeSetPage(){ 
	 
		return "personnel/attendance/closeTimeSetPage";
	}
	
	/**
	 * 保存休息日
	 * @param attendanceClose
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("attendance:addcloseTime")
	@RequestMapping("addCloseTimeSet")
	public ModelAndView addCloseTimeSet(@Valid XtAttendanceClose attendanceClose,Errors errors,String attimeids){ 
		
		
		return ajaxDone(service.addCloseTimeSet(attendanceClose,attimeids));
	}
	/**
	 * 修改休息日页面
	 * @return
	 */
	@RequiresPermissions("attendance:updateCloseTime")
	@RequestMapping("updateCloseTimePage")
	public String updateCloseTimePage(String id,ModelMap map){  
		XtAttendanceClose c = service.get(XtAttendanceClose.class, id);
		
		if(c==null){
			return NODATA;
		}
		map = service.selectTimeSetByTimeId(c.getXtatimeid(),map);
		map.addAttribute("c",c);
		return "personnel/attendance/closeTimeUpdate";
	}
 
	/**
	 * 修改休息日
	 * @param attendanceClose
	 * @param errors
	 * @param attimeids
	 * @return
	 */
	@RequiresPermissions("attendance:updateCloseTime")
	@RequestMapping("updateCloseTimeSet")
	public ModelAndView updateCloseTimeSet(@Valid XtAttendanceClose attendanceClose,Errors errors,String attimeids){
		
		return ajaxDone(service.updateCloseTimeSet(attendanceClose, attimeids));
	}
	/**
	 * 查询登记时间设置
	 * @param type 查到带回类型	1:单选，2:多选
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(Integer type){
		
		return "personnel/attendance/lookup";
		
	}
	
	/**
	 * 用户，查找带回
	 * @param param
	 * @param user
	 * @param type 查到带回类型	1:单选，2:多选,3:手机号选择
	 * @param map
	 * @return
	 */
	@RequestMapping("lookUp")
	public ModelAndView lookUp(PageParam param,XtAttendanceTime attendanceTime,int type){
		 
		return ajaxJsonEscape(service.selectLookUp(param, attendanceTime));
	}
	/**
	 * 休息日管理列表
	 * @return
	 */
	@RequiresPermissions("attendance:closeTimeLoad")
	@RequestMapping("manageCloseTime/load")
	public String closeTimeManageLoad(){
		
		return "personnel/attendance/closeTimeManageLoad";
	}
 
	/**
	 * 查询休息日
	 * @param param
	 * @param s
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("attendance:closeTimeLoad")
	@RequestMapping("manageCloseTime/query")
	public ModelAndView query(PageParam param,XtAttendanceClose c,Date startDate,Date endDate){
		
		
		return ajaxJsonEscape(service.selectCloseTime(param, c, startDate, endDate));
	}
	
	/**
	 * 休息日详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions("attendance:closeTimeshow")
	@RequestMapping("closeTimeshow")
	public String closeTimeshow( String id,ModelMap map){
		
		XtAttendanceClose c = service.get(XtAttendanceClose.class, id);
		
		if(c==null){
			return NODATA;
		}
		map = service.selectTimeSetByTimeId(c.getXtatimeid(),map);
		map.addAttribute("c",c);
		
		return "personnel/attendance/closeTimeShow";
	}
	/**
	 * 删除休息日
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("attendance:closeTimedel")
	@RequestMapping("closeTimedel")
	public ModelAndView closeTimedel(String[] ids){
		
		return ajaxDone(service.delCloseTime( ids));
		
	}
}
