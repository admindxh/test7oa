/**  
 * @Project: jxoa
 * @Title: JournalServiceImpl.java
 * @Package com.oa.manager.coordination.service.impl
 * @date 2013-5-23 上午10:14:22
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service.impl;

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
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.coordination.bean.XtJournal;
import com.oa.manager.coordination.bean.XtJournalUser;
import com.oa.manager.coordination.service.IJournalService;
import com.oa.manager.system.bean.ListValues;

/**
 * 
 * 类名：JournalServiceImpl
 * 功能：日志管理 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-23 上午10:14:22
 *
 */
@Service
public class JournalServiceImpl extends BaseServiceImpl implements IJournalService{
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectJournal(PageParam param,XtJournal xj,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtJournal j where 1=1 ");
		List list=new ArrayList();
		
		//查询条件
		if(StringUtils.isNotBlank(xj.getUserId())){
			sb.append(" and j.userId = ? ");
			list.add(xj.getUserId());
		}
		if(StringUtils.isNotBlank(xj.getJournalTitle())){
			sb.append(" and j.journalTitle like ? ");
			list.add("%"+xj.getJournalTitle()+"%");
		}
		if(StringUtils.isNotBlank(xj.getJournalType())){
			sb.append(" and j.journalType = ? ");
			list.add(xj.getJournalType());	
		}
		if(startDate!=null){
			sb.append(" and j.journalTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and j.journalTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by j.journalTime desc");
		}
		
		List<XtJournal> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtJournal j:rows){
			j.setJournalType(MyCache.getInstance().getSelectValue(j.getJournalType()));
			j.setJournalContent("");
		}	
		data.setRows(rows);
		return data;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectJournalShare(PageParam param,XtJournal xj,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtJournal j,XtJournalUser u where u.userId=? and u.journalId=j.id ");
		List list=new ArrayList();
		list.add(ServletUtil.getMember().getId());
		//查询条件
		if(StringUtils.isNotBlank(xj.getUserId())){
			sb.append(" and j.userId = ? ");
			list.add(xj.getUserId());
		}
		if(StringUtils.isNotBlank(xj.getJournalTitle())){
			sb.append(" and j.journalTitle like ? ");
			list.add("%"+xj.getJournalTitle()+"%");
		}
		if(StringUtils.isNotBlank(xj.getJournalType())){
			sb.append(" and j.journalType = ? ");
			list.add(xj.getJournalType());	
		}
		if(startDate!=null){
			sb.append(" and j.journalTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and j.journalTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by j.journalTime desc");
		}
		
		List<XtJournal> rows=dao.findPage("select j "+sb.toString(),param.getPage(),param.getRows(),list);
		for(XtJournal j:rows){
			j.setJournalType(MyCache.getInstance().getSelectValue(j.getJournalType()));
			j.setUserId(MyCache.getInstance().getTrueName(j.getUserId()));
		}	
		data.setRows(rows);
		return data;
		
		
	
	}
	
	
	
	
	public boolean saveJournal(XtJournal xj,String[] userIds,Integer isSendMsg){
		
		String id=(String)dao.saveReturnId(xj);
		List<XtJournalUser> list=new ArrayList<XtJournalUser>();
		for(String uid:userIds){
			XtJournalUser ju=new XtJournalUser();
			ju.setJournalId(id);
			ju.setUserId(uid);
			list.add(ju);
		}
		//发送消息提醒
		if(isSendMsg!=null&&isSendMsg==1){
			saveMsgWarn(4, id, userIds,null);
		}
		return dao.saveOrUpdateAll(list);
	}
	public String updateJournal(XtJournal xj,String[] userIds,Integer isSendMsg){
		
		XtJournal oldxj=dao.get(XtJournal.class, xj.getId());
		if(oldxj==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		if(!ServletUtil.getMember().getId().equals(oldxj.getUserId())){
			return MsgConfig.MSG_KEY_FAIL;
		}
		oldxj.setJournalContent(xj.getJournalContent());
		oldxj.setJournalTime(xj.getJournalTime());
		oldxj.setJournalTitle(xj.getJournalTitle());
		oldxj.setJournalType(xj.getJournalType());
		
		List<String> addUserIds=new ArrayList<String>();
		for(String id:userIds){
			addUserIds.add(id);
		}
		List<String> oldUserIds=dao.find("select userId from XtJournalUser where journalId=?",oldxj.getId());
		
		//原先已有的-现在需要添加的==需要删除的
		List<String> delUserIds=new ArrayList<String>(oldUserIds);//需要删除的
		delUserIds.remove(addUserIds);
		for(String id:delUserIds){
			dao.delete("delete XtJournalUser where journalId=? and userId=? ",oldxj.getId(),id);
		}
		//现在需要添加的-原先已有的=需要真正添加的
		addUserIds.remove(oldUserIds);
		
		//等待更新的对象集合
		List<Object> c=new ArrayList<Object>();
		
		for(String id:addUserIds){
			XtJournalUser ju=new XtJournalUser();
			ju.setJournalId(oldxj.getId());
			ju.setUserId(id);
			c.add(ju);
		}
		//发送消息提醒
		if(isSendMsg!=null&&isSendMsg==1){
			saveMsgWarn(4, oldxj.getId(), addUserIds,null);
		}
		return dao.saveOrUpdateAll(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		
	}
	
	public boolean deleteJournal(String[] ids){
		String userId=ServletUtil.getMember().getId();
		for(String id:ids){
			
			dao.delete("delete XtJournal where id=? and userId=?", id,userId);
		}
		return true;
	}
	
	public String selectJournalUserIds(String id){
		
		return StringUtils.join(dao.find("select userId from XtJournalUser where journalId=?",id),",");
		
	}
	/**
	 * 客户端上传工作日志
	 */
	@Override
	public boolean addJournByClient(String title, String journType,String userIds, String content) {
			
		//先保存日志对象
		XtJournal j = new XtJournal();
		j.setUserId(ServletUtil.getMember().getId());
		j.setJournalTitle(title);
		j.setJournalType(journType);
		j.setJournalTime(DateUtil.currentTimestamp());
		j.setJournalContent(content);
		String id = (String) dao.saveReturnId(j);
		//再保存共享对象
		List<XtJournalUser> list = new ArrayList<XtJournalUser>();
		if(StringUtils.isNotBlank(userIds)){
			String [] arr = userIds.split(",");
			for(String uid:arr){
				XtJournalUser ju=new XtJournalUser();
				ju.setJournalId(id);
				ju.setUserId(uid);
				list.add(ju);
			}
		}
		return dao.saveOrUpdateAll(list);
	}
	/**
	 * 分页查询个人工作日志---用于客户端
	 * id 主键
	 * tpye 类别
	 * title 标题
	 */
	@Override
	public List<Map<String, Object>> query_journList(PageParam param) {
				
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = dao.findPage(" select new Map(x.id as id,x.journalType as journalType,x.journalTitle as journalTitle) from XtJournal x  where x.userId=? order by x.journalTime ",param.getPage(),param.getRows(),ServletUtil.getMember().getId());
		for(Map<String,Object> m:list){
			m.put("title", StringUtils.abbreviate((String)(m.get("journalTitle")),12));//标题默认显示前12字符
			m.put("type",MyCache.getInstance().getSelectValue((String)m.get("journalType")));//日志类别
			m.remove("journalType");
			m.remove("journalTitle");
		}
		
		return list;
	}
	/**
	 * 根据日志id查询日志详情
	 * content
	 * title
	 * type
	 * date
	 */
	@Override
	public Map<String, Object> queryJournalById(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		XtJournal j = dao.get(XtJournal.class, id);
		if(j!=null){
			map.put("content", j.getJournalContent());
			map.put("title",j.getJournalTitle());
			map.put("type",MyCache.getInstance().getSelectValue(j.getJournalType()));
			map.put("date",j.getJournalTime());
		}
		return map;
	}
	/**
	 * 查询日志类型
	 */
	@Override
	public List<ListValues> queryJourn_type() {
		 
		@SuppressWarnings("unchecked")
		List<ListValues> list = dao.find(" from ListValues where listType =?",5);
		return list;
	}
}
