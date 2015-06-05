/**  
 * @Project: jxoa
 * @Title: InstantMessageServiceImpl.java
 * @Package com.oa.manager.personalOffice.service.impl
 * @date 2013-5-24 下午5:06:14
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.commons.util.StringUtil;
import com.oa.manager.personalOffice.bean.PerInstantMessage;
import com.oa.manager.personalOffice.bean.PerMsg;
import com.oa.manager.personalOffice.service.IInstantMessageService;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：InstantMessageServiceImpl
 * 功能：即时消息 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-24 下午5:06:14
 *
 */
@Service
public class InstantMessageServiceImpl extends BaseServiceImpl implements IInstantMessageService{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectUsers(PageParam param,SyUsers user){
		DataGrid data=new DataGrid();
		
		StringBuffer sb=new StringBuffer("from SyUsers u where 1=1 ");
		List list=new ArrayList();
		
		if(StringUtils.isNotBlank(user.getTrueName())){
			sb.append(" and u.trueName like ? ");
			list.add("%"+user.getTrueName()+"%");
		}
		if(StringUtils.isNotBlank(user.getDeptId())){
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
		
		List<Map<String,Object>> rows=dao.findPage("select new Map(u.id as id," +
				"u.trueName as trueName,u.userSex as userSex,u.lastLoginTime as lastLoginTime) "
				+sb.toString(),param.getPage(),param.getRows(),list);

		data.setRows(rows);
		
		return data;
		
	}
	public boolean addInstantMessage(String messageContent,String[] userIds){
		
		if(userIds.length==0)return false;
		String sendUid=ServletUtil.getMember().getId();
		Timestamp time= DateUtil.currentTimestamp();
		//等待保存的对象集合
		List<PerInstantMessage> c=new ArrayList<PerInstantMessage>();
		for(String id:userIds){
			PerInstantMessage im=new PerInstantMessage();
			im.setCreateTime(time);
			im.setMessageContent(messageContent);
			im.setReceiveUid(id);
			im.setSendUid(sendUid);
			im.setReadState((short)0);
			c.add(im);
		}
		return  dao.saveOrUpdateAll(c);
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectInstantMessage(PageParam param,PerInstantMessage im,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from PerInstantMessage m where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(im.getReadState()!=null){
			sb.append(" and m.readState =? ");
			list.add(im.getReadState());
		}
		if(StringUtils.isNotBlank(im.getSendUid())){
			sb.append(" and m.sendUid =? ");
			list.add(im.getSendUid());
		}
		if(StringUtils.isNotBlank(im.getReceiveUid())){
			sb.append(" and m.receiveUid =? ");
			list.add(im.getReceiveUid());
		}
		if(startDate!=null){
			sb.append(" and m.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and m.createTime <=? ");
			list.add(endDate);	
		}
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by m.createTime desc ");
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		List<PerInstantMessage> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(PerInstantMessage r:rows){
			r.setReceiveName(MyCache.getInstance().getTrueName(r.getReceiveUid()));
			r.setSendName(MyCache.getInstance().getTrueName(r.getSendUid()));
		}
		data.setRows(rows);
		return data;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long selectInstantMessageCount(PerInstantMessage im,Date startDate,Date endDate){
		
		StringBuffer sb=new StringBuffer("select count(*) from PerInstantMessage m where 1=1  ");
		List list=new ArrayList();
		//查询条件
		if(im.getReadState()!=null){
			sb.append(" and m.readState =? ");
			list.add(im.getReadState());
		}
		if(StringUtils.isNotBlank(im.getSendUid())){
			sb.append(" and m.sendUid =? ");
			list.add(im.getSendUid());
		}
		if(StringUtils.isNotBlank(im.getReceiveUid())){
			sb.append(" and m.receiveUid =? ");
			list.add(im.getReceiveUid());
		}
		if(startDate!=null){
			sb.append(" and m.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and m.createTime <=? ");
			list.add(endDate);	
		}
		
		return (Long)dao.findUniqueOne(sb.toString(),list);
		
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PerMsg> selectHistoryMessage(PageParam param,String userId,Date startDate,Date endDate){
		
		StringBuffer sb=new StringBuffer("from PerInstantMessage m where (sendUid=? and receiveUid=?) or (sendUid=? and receiveUid=?) ");
		List list=new ArrayList();
		String myId=ServletUtil.getMember().getId();
		list.add(myId);
		list.add(userId);
		list.add(userId);
		list.add(myId);
		//查询条件
		if(startDate!=null){
			sb.append(" and m.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and m.createTime <=? ");
			list.add(endDate);	
		}
		
		//排序规则
		
		sb.append(" order by m.createTime desc");
		List data=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		Collections.reverse(data);//转正顺序，查看方便
		return data;
		
	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long selectHistoryMessageCount(String userId,Date startDate,Date endDate){
		
		StringBuffer sb=new StringBuffer("select count(*) from PerInstantMessage m where (sendUid=? and receiveUid=?) or (sendUid=? and receiveUid=?) ");
		List list=new ArrayList();
		String myId=ServletUtil.getMember().getId();
		list.add(myId);
		list.add(userId);
		list.add(userId);
		list.add(myId);
		//查询条件
		if(startDate!=null){
			sb.append(" and m.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and m.createTime <=? ");
			list.add(endDate);	
		}
		
		return (Long)dao.findUniqueOne(sb.toString(),list);
		
	}
	
	public PerInstantMessage updatefindInstantMessageById(String id){
		PerInstantMessage msg=dao.get(PerInstantMessage.class, id);
		if(msg!=null&&msg.getReadState()==0){
			if(msg.getReceiveUid().equals(ServletUtil.getMember().getId()))
			msg.setReadState((short)1);
		}
		return msg;
	}
	
	public boolean deleteInstantMessage(String[] ids){
		
		for(String id:ids){
			dao.delete(" delete PerInstantMessage where id=? ", id);
		}
		return true;
	}
	
	public Long selectMyNotReadCount(){
		
		return (Long)dao.findUniqueOne("select count(*) from PerInstantMessage m where readState=? and receiveUid=?  ",(short)0,ServletUtil.getMember().getId());
		
	}
	
	
	
	
//**********************************安卓应用******************************************	
	/**
	 * id  主键ID
	 * readState  阅读状态 1：已读，0：未读
	 * createTime 发送时间
	 * trueName   接收人姓名
	 * receiveUid  接收人id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectSendMessage(PageParam param) {
		List<Map<String,Object>> list = dao.findPage("select new Map(m.id as id,m.readState as readState,m.createTime as createTime,m.receiveUid as receiveUid,m.messageContent as cont) from PerInstantMessage m  where  m.sendUid= ? order by m.createTime desc ", param.getPage(), param.getRows(),ServletUtil.getMember().getId());
		for(Map<String,Object> m :list){
			m.put("trueName",MyCache.getInstance().getTrueName((String)(m.get("receiveUid"))));
			//去掉html标签样式正则
			m.put("content",StringUtils.abbreviate(StringUtil.Html2Text((String)(m.get("cont"))),16));
			m.remove("cont");
		}
		return list;
	}
	@Override
	public Long totleSendMessage() {
		
		return (Long)dao.findUniqueOne("select count(*)  from PerInstantMessage m   where  m.sendUid= ? ",ServletUtil.getMember().getId());
	}
	/**
	 * id  主键ID
	 * readState  阅读状态 1：已读，0：未读
	 * createTime 发送时间
	 * trueName   发送姓名
	 * sendUid    发送人id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectReceiveMessage(PageParam param) {
		List<Map<String,Object>> list = dao.findPage("select new Map(m.id as id,m.readState as readState,m.createTime as createTime,m.messageContent as cont,m.sendUid as sendUid) from PerInstantMessage m where  m.receiveUid= ? order by m.createTime desc ", param.getPage(), param.getRows(),ServletUtil.getMember().getId());
		for(Map<String,Object> m :list){
			//去掉html标签样式正则
			m.put("trueName",MyCache.getInstance().getTrueName((String) m.get("sendUid")));
			m.put("content",StringUtils.abbreviate(StringUtil.Html2Text((String)(m.get("cont"))),16));
			m.remove("cont");
			
		}
		return list;
	}
	@Override
	public Long totleReceiveMessage() {
		
		return (Long)dao.findUniqueOne("select count(*)  from PerInstantMessage m ,SyUsers u  where m.sendUid=u.id and m.receiveUid=? ",ServletUtil.getMember().getId());
	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> querHistoryMessage(PageParam param,String userId) {
		List<Object> al=new ArrayList<Object>();
		String myId=ServletUtil.getMember().getId();
		al.add(myId);
		al.add(userId);
		al.add(userId);
		al.add(myId);
		List<Map<String,Object>> list = dao.findPage(" select new Map( m.sendUid as sendUid,m.receiveUid as receiveUid,m.messageContent as content,m.createTime as createTime) from PerInstantMessage m where (m.sendUid=? and m.receiveUid=?) or (m.sendUid=? and m.receiveUid=?) order by m.createTime desc ", param.getPage(),param.getRows(), al);
		for(Map<String,Object> map:list){
			map.put("sendName", MyCache.getInstance().getTrueName((String)map.get("sendUid")));//发送人
			map.put("receiveName",MyCache.getInstance().getTrueName((String)map.get("receiveUid")));//接收人
		}
		return list;
	}
	@Override
	public Long totleHistoryMessage(String userId) {
		List<Object> list=new ArrayList<Object>();
		String myId=ServletUtil.getMember().getId();
		list.add(myId);
		list.add(userId);
		list.add(userId);
		list.add(myId);
		return (Long) dao.findUniqueOne("select count(*) from PerInstantMessage where (sendUid=? and receiveUid=?) or (sendUid=? and receiveUid=?)",list);
	}
}
