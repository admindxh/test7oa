/**  
 * @Project: jxoa
 * @Title: ScheduleServiceImpl.java
 * @Package com.oa.manager.personalOffice.service.impl
 * @date 2013-5-29 上午9:12:24
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.PerSchedule;
import com.oa.manager.personalOffice.service.IScheduleService;

/**
 * 
 * 类名：ScheduleServiceImpl
 * 功能：日程安排 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-29 上午9:12:24
 *
 */
@Service
public class ScheduleServiceImpl extends BaseServiceImpl implements IScheduleService{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectSchedule(PageParam param,PerSchedule s,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from PerSchedule s where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(s.getUserId())){
			sb.append(" and s.userId = ? ");
			list.add(s.getUserId());	
		}
		if(StringUtils.isNotBlank(s.getSchType())){
			sb.append(" and s.schType = ? ");
			list.add(s.getSchType());	
		}
		if(StringUtils.isNotBlank(s.getSchTitle())){
			sb.append(" and s.schTitle like ? ");
			list.add("%"+s.getSchTitle()+"%");
		}
		if(startDate!=null){
			sb.append(" and s.startTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and s.startTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by s.startTime ");
		}
		
		
		List<PerSchedule> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(PerSchedule r:rows){
			r.setSchType(MyCache.getInstance().getSelectValue(r.getSchType()));
			r.setSchContent("");
		}
		data.setRows(rows);
	
		return data;
		
	}
	@SuppressWarnings({ "rawtypes"})
	public List selectMySchedule(Date startDate,Date endDate){
		
		return dao.find("select new Map(s.id as id,s.schTitle as title,s.startTime as start,s.endTime as end)from PerSchedule s where s.userId = ? and s.startTime >=? and s.startTime <=?",ServletUtil.getMember().getId(),startDate,endDate);
	}
	
	
	public String updateSchedule(PerSchedule s){
		PerSchedule old=dao.get(PerSchedule.class, s.getId());
		if(old==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		if(!ServletUtil.getMember().getId().equals(old.getUserId())){
			return MsgConfig.MSG_KEY_FAIL;
		}
		old.setEndTime(s.getEndTime());
		old.setSchContent(s.getSchContent());
		old.setSchTitle(s.getSchTitle());
		old.setSchType(s.getSchType());
		old.setStartTime(s.getStartTime());
		old.setEndTime(s.getEndTime());
		old.setWarnTime(s.getWarnTime());
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	
	public boolean deleteSchedule(String[] ids){
		String userId=ServletUtil.getMember().getId();
		for(String id:ids){
			dao.delete("delete PerSchedule where id=? and userId=? ", id,userId);
		}
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> selectAfterNowTodaySchedule(){
		
		Date now=new Date();
		
		Timestamp todayEndTime=DateUtil.string2Timestamp(DateUtil.date2String(now)+" 23:59:59");
	
		return dao.find("select new Map(s.id as id,s.schTitle as title,s.warnTime as time)from PerSchedule s where s.userId=? and warnTime>=? and warnTime <=?",ServletUtil.getMember().getId(),now,todayEndTime);
		
	}
	
	
	public Long selectTodayScheduleCount(){
		
		Date now=new Date();
		Timestamp todayStartTime=DateUtil.string2Timestamp(DateUtil.date2String(now)+" 00:00:00");
		Timestamp todayEndTime=DateUtil.string2Timestamp(DateUtil.date2String(now)+" 23:59:59");
	
		return (Long)dao.findUniqueOne("select count(*) from PerSchedule s where s.userId=? and s.startTime>=? and s.startTime <=?",ServletUtil.getMember().getId(),todayStartTime,todayEndTime);
		
	}
	
	public List selectTodaySchedule(){
		
		Date now=new Date();
		Timestamp todayStartTime=DateUtil.string2Timestamp(DateUtil.date2String(now)+" 00:00:00");
		Timestamp todayEndTime=DateUtil.string2Timestamp(DateUtil.date2String(now)+" 23:59:59");
		
		return dao.find("from PerSchedule s where s.userId=? and s.startTime>=? and s.startTime <=? order by s.startTime",ServletUtil.getMember().getId(),todayStartTime,todayEndTime);
		
	
	}
	
}
