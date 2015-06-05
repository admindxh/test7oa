/**  
 * @Project: jxoa
 * @Title: SystemLogServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-4-11 下午3:48:17
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyLog;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.service.ISystemLogService;

/**
 * 
 * 类名：SystemLogServiceImpl
 * 功能：系统登录日志 系统重要操作日志 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-11 下午3:48:17
 *
 */
@Service
public class SystemLogServiceImpl extends BaseServiceImpl implements ISystemLogService{
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectSyLoginLog(PageParam param,SyLoginLog log,Date startDate,Date endDate,boolean isUserName){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyLoginLog log where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(log.getUserId())){
			sb.append(" and log.userId = ? ");
			list.add(log.getUserId());
		}
		if(log.getLoginType()!=null){
			sb.append(" and log.loginType = ? ");
			list.add(log.getLoginType());	
		}
		if(StringUtils.isNotBlank(log.getLoginIp())){
			sb.append(" and log.loginIp like ? ");
			list.add("%"+log.getLoginIp()+"%");	
		}
		if(startDate!=null){
			sb.append(" and log.loginTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and log.loginTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by log.loginTime desc");
		}
		List<SyLoginLog> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		if(isUserName){
			for(SyLoginLog l:rows){
				l.setUserId(MyCache.getInstance().getTrueName(l.getUserId()));
			}	
		}
		data.setRows(rows);
		
		return data;
		
	
	}
	public List<SyLoginLog> selectSyLoginLogs(String[] ids){
		List<SyLoginLog> list=new ArrayList<SyLoginLog>();
		for(String id : ids){
			list.add(dao.get(SyLoginLog.class, id));
		}
		return list;
	}
	public boolean deleteLoginLog(String[] ids){
		for(String id:ids){
			dao.delete("delete SyLoginLog where id=?", id);
		}
		saveLog("删除登录日志", "删除"+ids.length+"条");
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectSyLog(PageParam param,SyLog log,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyLog log where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(log.getUserId())){
			sb.append(" and log.userId = ? ");
			list.add(log.getUserId());
		}
		if(StringUtils.isNotBlank(log.getActionIp())){
			sb.append(" and log.actionIp like ? ");
			list.add("%"+log.getActionIp()+"%");	
		}
		if(startDate!=null){
			sb.append(" and log.actionTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and log.actionTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by log.actionTime desc");
		}
		List<SyLog> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		
		for(SyLog l:rows){
			l.setUserId(MyCache.getInstance().getTrueName(l.getUserId()));
		}	
		
		data.setRows(rows);
		
		return data;
	}
	
	public boolean deleteLog(String[] ids){
		for(String id:ids){
			dao.delete("delete SyLog where id=?", id);
		}
		saveLog("删除操作日志", "删除"+ids.length+"条");
		return true;
	}
}
