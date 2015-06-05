/**  
 * @Title ProjectTaskServiceImpl.java
 * @date 2013-12-11 上午9:37:44
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.SearchFilterUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.coordination.bean.Project;
import com.oa.manager.coordination.bean.ProjectTask;
import com.oa.manager.coordination.bean.ProjectTaskJournal;
import com.oa.manager.coordination.bean.ProjectTaskUser;
import com.oa.manager.coordination.service.ProjectTaskService;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
@Service
public class ProjectTaskServiceImpl extends BaseServiceImpl implements ProjectTaskService{
	
	public DataGrid selectProjectTask(PageParam param,String projectId){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from ProjectTask p where projectId=:projectId ");
		Map map=new HashMap();
		map.put("projectId",projectId);
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by p.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(p.id as id,p.name as name,p.speed as speed,p.startTime as startTime,p.endTime as endTime,p.createTime as createTime,p.createUserId as createUserId) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			
			r.put("createUserName", MyCache.getInstance().getTrueName((String)r.get("createUserId")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public DataGrid selectMyProjectTask(PageParam param){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from ProjectTask p,ProjectTaskUser u,Project pro  where u.projectTaskId=p.id and u.userId=:userId and pro.id=p.projectId");
		Map map=new HashMap();
		map.put("userId",ServletUtil.getMember().getId());
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(DISTINCT p.id)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by p.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select DISTINCT new Map(p.id as id,p.name as name,p.speed as speed,p.startTime as startTime,p.endTime as endTime,p.createTime as createTime,p.createUserId as createUserId,p.projectId as projectId,pro.name as projectName) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			
			r.put("createUserName", MyCache.getInstance().getTrueName((String)r.get("createUserId")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public boolean addProjectTask(ProjectTask t,Integer isSendMsg,String[] userIds){
		
		t.setCreateUserId(ServletUtil.getMember().getId());
		t.setCreateTime(DateUtil.currentTimestamp());
		t.setSpeed((short)0);//初始化进度
		dao.save(t);
		
		//等待保存的对象集合
		List<Object> c=new ArrayList<Object>();
	
		for(String id:userIds){
			ProjectTaskUser pt=new ProjectTaskUser();
			pt.setProjectId(t.getProjectId());
			pt.setProjectTaskId(t.getId());
			pt.setUserId(id);
			c.add(pt);
		}
		if(isSendMsg!=null&&isSendMsg==1){
			saveMsgWarn(32,t.getId(),userIds,null);//发送消息提醒
		}
		return dao.saveOrUpdateAll(c);
			
	}
	public Map<String,Object> findProjectTaskById(String id){
		
		ProjectTask t=dao.get(ProjectTask.class, id);
		if(t!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("t", t);
			
			List<String> userIds=dao.find("select userId from ProjectTaskUser where projectTaskId=?",id);//用户
			
			map.put("userIds",  StringUtils.join(userIds,","));
		
			return map;
		}
		return null;
	}
	public String updateProjectTask(ProjectTask t,Integer isSendMsg,String[] userIds){
		
		ProjectTask old=dao.get(ProjectTask.class, t.getId());
		
		if(old==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		//判断是否有修改操作权限
		int power=hasPowerForTask(old.getProjectId(),false);
		if(power==0){
			throw new MyRuntimeException("任务关联的项目不存在，可能已被删除！请刷新再试!");
		}
		if(power==2){
			throw new MyRuntimeException("没有权限，只有项目创建者或者项目负责人才有修改权限！");
		}
		
		old.setName(t.getName());
		old.setContent(t.getContent());
		old.setStartTime(t.getStartTime());
		old.setEndTime(t.getEndTime());
	
		
		List<ProjectTaskUser> list=dao.find("from ProjectTaskUser where projectTaskId=?",old.getId());
		for(ProjectTaskUser tu:list){
			boolean has=false;
			for(int i=0,k=userIds.length;i<k;i++){
				if(tu.getUserId().equals(userIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				//删除
				dao.delete(tu);
			}
			
		}
		//新增加
		//等待保存的对象集合
		List<Object> c=new ArrayList<Object>();
		
		for(int i=0,k=userIds.length;i<k;i++){//用户
			boolean has=false;
			for(ProjectTaskUser id:list){
				if(id.getUserId().equals(userIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				ProjectTaskUser tu=new ProjectTaskUser();
				tu.setProjectTaskId(old.getId());
				tu.setUserId(userIds[i]);
				c.add(tu);
			}
		}
		if(isSendMsg!=null&&isSendMsg==1){
			//发送消息提醒
			saveMsgWarn(32,t.getId() , userIds,null);
		}
		return dao.saveOrUpdateAll(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
	
	}
	
	public boolean deleteProjectTask(String projectId,String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			ProjectTask p=dao.get(ProjectTask.class, id);
			if(p!=null&&p.getProjectId().equals(projectId)){
				c.add(p);
				
			}
		}
		return dao.deleteAll(c);
	}
	
	public DataGrid selectProjectTaskJournal(PageParam param,String taskId){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from ProjectTaskJournal j where projectTaskId=:projectTaskId ");
		Map map=new HashMap();
		map.put("projectTaskId",taskId);
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by j.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(j.id as id,j.content as content,j.journal as journal,j.startTime as startTime,j.endTime as endTime,j.createTime as createTime,j.createUserId as createUserId) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			r.put("createUserName", MyCache.getInstance().getTrueName((String)r.get("createUserId")));
			
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public String addProjectTaskJournal(ProjectTaskJournal j,short speed){
		ProjectTask t=dao.get(ProjectTask.class, j.getProjectTaskId());
		if(t==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		//判断当前项目状态
		Project p=dao.get(Project.class, t.getProjectId());
		if(p==null){
			throw new MyRuntimeException("任务关联的项目不存在，可能已被删除！");
		}
		if(p.getStatus()!=1){
			throw new MyRuntimeException("项目处于未开始，暂停或结束状态时无法添加进度日志!");
		}
		//当前任务的进度执行人才有添加进度日志的权限
		if(!myIsExecutorByTaskId(t.getId())){
			throw new MyRuntimeException("没有添加权限，只有任务的执行人才有添加进度日志的权限。");
		}
		j.setJournal("修改进度:"+t.getSpeed()+"% --> "+speed+"%");
		j.setCreateTime(DateUtil.currentTimestamp());
		j.setCreateUserId(ServletUtil.getMember().getId());
	
		t.setSpeed(speed);
		
		return dao.save(j)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
	}
	public int hasPowerForTask(String projectId,boolean isAddTask){
		Project p=dao.get(Project.class, projectId);
		if(p==null){
			return 0;
		}
		if(isAddTask&&p.getStatus()==3){
			return 3;//项目状态为结束状态
		}else if(p.getCreateUserId().equals(ServletUtil.getMember().getId())){//项目创建者
			return 1;
		}else if(dao.findOne("from ProjectUser where projectId =? and type=1 and tableId=?",projectId, ServletUtil.getMember().getId())!=null){///项目负责人
			return 1;
		}
		return 2;
	}
	
	public boolean myIsExecutorByTaskId(String taskId){
		
		//判断当前用户是否是任务的执行人
		ProjectTaskUser pu=(ProjectTaskUser)dao.findOne("from ProjectTaskUser where projectTaskId =?  and  userId=?",taskId, ServletUtil.getMember().getId());
		if(pu==null){
			return false;
		}else{
			return true;
		}
		
	}
	
	
}
