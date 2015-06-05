/**  
 * @Project: jxoa
 * @Title: FlowRunUtil.java
 * @Package com.oa.manager.workFlow.util
 * @date 2013-8-10 下午4:28:40
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.util;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.oa.commons.base.IBaseDao;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Meeting;
import com.oa.manager.common_platform.service.IMeetingService;

/**
 * 
 * 类名：FlowRunUtil
 * 功能：流程实例运行中辅助类
 * 详细：此类暴露给流程，可在流程定义使用表达式来使用此类的方法，必须由Spring创建才有效
 * 
 * 对外暴露的名称 flowUtil
 * 
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-8-10 下午4:28:40
 *
 */
public class FlowRunUtil {
	
	@Autowired
	private IBaseDao dao;
	
	/**
	 * 将字符串用，分割为数组，再转换成集合，
	 * @param str
	 * @return
	 */
	public List<String> stringToList(String str){
		
		return Arrays.asList(str.split(","));
		
	}
	/**
	 * 根据用户id，获取用户所属部门经理id
	 * @param userId
	 * @return
	 */
	public String getDeptHeadId(String userId){
		
		//IBaseDao dao=ServletUtil.getApplicationContext().getBean(IBaseDao.class);
	
		String leadUid=(String)dao.findOne("select d.leadUid from SyUsers u,SyDept d where u.id=? and  u.deptId=d.id ",userId);
		
		System.out.println("部门经理id===="+leadUid);
		
		return leadUid;
	}
	/**
	 * 将时间字符串转换成时间格式
	 * @param time
	 * @return
	 */
	public Timestamp string2Timestamp(String time){
		
		
		
		return DateUtil.string2Timestamp(time);
	}
	public void saveMeeting(DelegateExecution e){
		System.out.println("*********保存会议**********");
		IMeetingService service=ServletUtil.getApplicationContext().getBean(IMeetingService.class);
		Meeting m=new Meeting();
		
		m.setMType((String)e.getVariable("mType"));
		m.setMName((String)e.getVariable("mName"));
		m.setMHost((String)e.getVariable("mHost"));
		m.setMDate(DateUtil.string2Date((String)e.getVariable("mDate")));
		m.setMStatime((String)e.getVariable("mStartTime"));
		m.setMEndtime((String)e.getVariable("mEndTime"));
		m.setMRid((String)e.getVariable("mRid"));
		m.setMSummary((String)e.getVariable("mSummary"));
		m.setMRemark((String)e.getVariable("mRemark"));
		m.setMPublished(DateUtil.currentDateTimeToString());
		m.setMUid((String)e.getVariable("applyUserId"));//流程创建人作为会议发布人
		
		m.setDeptid((String)e.getVariable("deptIds"));
		m.setUserid((String)e.getVariable("userIds"));
		
		service.saveMeetingForFlow(m);
	}
	
	
	public void test(){
		System.out.println("执行提醒任务====");
	}
	
	
	
}
