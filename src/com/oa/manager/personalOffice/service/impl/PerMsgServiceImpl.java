/**  
 * @Project: jxoa
 * @Title: PerMsgServiceImpl.java
 * @Package com.oa.manager.personalOffice.service.impl
 * @date 2013-5-21 下午4:24:05
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.service.IVoteService;
import com.oa.manager.common_platform.bean.Newsmanagement;
import com.oa.manager.common_platform.service.IMeetingService;
import com.oa.manager.common_platform.service.INewsService;
import com.oa.manager.coordination.bean.XtJournal;
import com.oa.manager.coordination.service.IJournalService;
import com.oa.manager.coordination.service.INoticeService;
import com.oa.manager.personalOffice.bean.PerMsg;
import com.oa.manager.personalOffice.service.IPerMsgService;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.service.IWorkService;

/**
 * 
 * 类名：PerMsgServiceImpl
 * 功能：消息提醒 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-21 下午4:24:05
 *
 */
@Service
public class PerMsgServiceImpl extends BaseServiceImpl implements IPerMsgService {
	
	/**
	 * 通知service
	 */
	@Autowired
	private INoticeService noticservice;
	/**
	 * 会议service
	 */
	@Autowired
	private IMeetingService meetservice;
	/**
	 * 新闻service
	 */
	@Autowired
	private INewsService newsService;
	/**
	 * 投票service
	 */
	@Autowired
	private IVoteService voteservice;
	/**
	 * 日志service
	 */
	@Autowired
	private IJournalService journalservice;
	/**
	 * 工作流程 工作操作service
	 */
	@Autowired
	private IWorkService workservice; 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectPerMsg(PageParam param,PerMsg msg,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from PerMsg m where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(msg.getReadState()!=null){
			sb.append(" and readState=? ");
			list.add(msg.getReadState());
		}
		if(StringUtils.isNotBlank(msg.getUserId())){
			sb.append(" and userId=? ");
			list.add(msg.getUserId());
		}
		if(msg.getMsgType()!=null){
			sb.append(" and m.msgType = ? ");
			list.add(msg.getMsgType());	
		}
		if(startDate!=null){
			sb.append(" and m.msgTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and m.msgTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by m.msgTime desc");
		}
		
		
		data.setRows(dao.findPage(sb.toString(),param.getPage(),param.getRows(),list));
		return data;
		
	}
	
	
	public PerMsg updatefindPerMsgById(String id){
		PerMsg msg=dao.get(PerMsg.class, id);
		if(msg!=null&&msg.getReadState()==0){
			msg.setReadState((short)1);
		}
		return msg;
	}
	
	public boolean deletePerMsg(String[] ids){
	
		for(String id:ids){
			dao.delete(" delete PerMsg where id=? ", id);
		}
		return true;
	}
	
	public Long selectNotReadNumber(){
		
		return (Long)dao.findUniqueOne("select count(*) from PerMsg m where readState=0  and  userId=? ",ServletUtil.getMember().getId());
	}
//*********************************************安卓****************************************************
	/**
	 * type:提醒类型    1通知2会议3新闻4共享日志5投票60工作流
	 * tableId：内容表id
	 * msgTime:发送时间
	 * readState：读取状态 1已读 0未读
	 */
	@Override
	public List<Map<String, Object>> selectPersonMsgWarn(PageParam param) {
		return dao.findPage("select new Map(m.id as id,m.msgType as tpye,m.tableId as tableId,m.msgTime as msgTime,m.readState as readState) from PerMsg m where m.userId=? order by m.msgTime desc ",param.getPage(),param.getRows(),ServletUtil.getMember().getId());
	}
	@Override
	public Long totlePersonMsgWarn() {
		return (Long) dao.findUniqueOne("select count(*) from PerMsg m where m.userId=? ",ServletUtil.getMember().getId());
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateInformationById(String id,String tableId, short i){
		Map<String,Object> map = new HashMap<String,Object>();
		if(i==1){//通知
			PerMsg per  = dao.get(PerMsg.class,id);
			if(per!=null){
				//更新消息状态
				per.setReadState((short)1);
				map = noticservice.updateNoticById(tableId);
				
			}
			return map;
		}else if(i==2){//会议
			PerMsg per  = dao.get(PerMsg.class,id);
			if(per!=null){
				per.setReadState((short)1);
				map = meetservice.selectMeetingById(tableId);
			}
			return map;
		}else if(i==4){//共享日志
			PerMsg per  = dao.get(PerMsg.class,id);
			if(per!=null){
				per.setReadState((short)1);
				XtJournal xj = journalservice.get(XtJournal.class, tableId);
				if(xj!=null){
					map.put("type",MyCache.getInstance().getSelectValue(xj.getJournalType()));
					map.put("title",xj.getJournalTitle());
					map.put("content",xj.getJournalContent());
					map.put("createtime",xj.getJournalTime());
				}
			}
			return map;
		}else if(i==5){//投票
			PerMsg per  = dao.get(PerMsg.class,id);
			if(per!=null){
				per.setReadState((short)1);
				map = voteservice.queryVoteById(tableId);
			}
			return map;
		}else if(i==60){
			
			PerMsg per  = dao.get(PerMsg.class,id);
			if(per!=null){
				per.setReadState((short)1);
				Map<String,Object> m = workservice.selectProcessDetails(tableId);
				if(m!=null){
					WfWork work = (WfWork) m.get("work");
					//工作标题
					map.put("title",work.getWorkTitle());
					//流程实例
					HistoricProcessInstance hpi = (HistoricProcessInstance) m.get("hpi");
					//创建人
					map.put("userName",MyCache.getInstance().getTrueName(hpi.getStartUserId()));
					//部门
					map.put("deptName",MyCache.getInstance().getDeptNameByUserId(hpi.getStartUserId()));
					//创建时间
					map.put("createtime",hpi.getStartTime());
					//流程定义
					ProcessDefinition pd = (ProcessDefinition) m.get("pd");
					//流程名称
					map.put("flowName",pd.getName());
					//类型
					map.put("type",MyCache.getInstance().getSelectValue(pd.getCategory()));
					//版本
					map.put("version",pd.getVersion());
					//描述
					map.put("descript",pd.getDescription());
				}	
			}
			return  map;
		}
	   return map;
	}
	@Override
	public List<Map<String, Object>> selectNoticBar() {
		//返回客户端通知栏列表集合   
		List<Map<String,Object>> al = new ArrayList<Map<String,Object>>();
		//通知1
		int notic = 0;
		//会议2
		int meet = 0;
		//日志4
		int journal = 0;
		//投票5
		int vote = 0;
		//工作流60
		int workflow = 0;
		//查询当前用户全部未读消息提醒
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = dao.find("select new Map(m.msgType as type) from PerMsg m where m.readState='0'  and m.userId=? ",ServletUtil.getMember().getId());
		for(Map<String,Object> m:list){
				
			if((Short) m.get("type")==1){//通知
				notic++;
			}else if((Short) m.get("type")==2){//会议
				meet++;
			}else if((Short) m.get("type")==4){//日志
				journal++;
			}else if((Short) m.get("type")==5){//投票
				vote++;
			}else if((Short) m.get("type")==60){//工作流
				workflow++;
			}
		}
		if(notic!=0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type",1);
			map.put("totle",notic);
			al.add(map);//通知
		}
		if(meet!=0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type",2);
			map.put("totle",meet);
			al.add(map);//会议
		}
		if(journal!=0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type",4);
			map.put("totle",journal);
			al.add(map);//日志
		}
		if(vote!=0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type",5);
			map.put("totle",vote);
			al.add(map);//投票
		}
		if(workflow!=0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("type",60);
			map.put("totle",workflow);
			al.add(map);//工作流
		}
		return al;
	}
	/**
	 * type:提醒类型    1通知2会议3新闻4共享日志5投票60工作流
	 * tableId：内容表id
	 * msgTime:发送时间
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectNoticBarByType(PageParam param,short i) {
		return dao.findPage("select new Map(m.id as id,m.msgType as tpye,m.tableId as tableId,m.msgTime as msgTime) from PerMsg m where m.readState='0' and m.userId=? and m.msgType=? order by m.msgTime desc ",param.getPage(),param.getRows(),ServletUtil.getMember().getId(),i);
	}
	@Override
	public Long totleNoticBarByType(short i) {
		
		return (Long) dao.findUniqueOne("select count(*) from PerMsg m where m.readState='0' and m.userId=? and m.msgType=? ",ServletUtil.getMember().getId(),i);
	}
}
