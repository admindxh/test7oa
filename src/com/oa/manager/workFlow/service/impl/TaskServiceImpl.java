/**  
 * @Project: jxoa
 * @Title: TaskServiceImpl.java
 * @Package com.oa.manager.workFlow.service.impl
 * @date 2013-7-22 下午3:00:25
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.workFlow.bean.WfForm;
import com.oa.manager.workFlow.bean.WfTask;
import com.oa.manager.workFlow.bean.WfTaskFiles;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.entity.TaskQueryModel;
import com.oa.manager.workFlow.service.ITaskService;

/**
 * 
 * 类名：TaskServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-22 下午3:00:25
 *
 */
@Service
public class TaskServiceImpl extends TaskBaseServiceImpl implements ITaskService{

	
	//查询,未领取的任务, 个人待选和组待选
	public DataGrid selectNotHaveDoTask(PageParam param,TaskQueryModel qm,String userId,List<String> groupIds){
		DataGrid data=new DataGrid();
		List<Task> tasks=new ArrayList<Task>();
		TaskQuery tq1=taskService.createTaskQuery();
		TaskQuery tq2=taskService.createTaskQuery();
		tq1.processVariableValueEquals("_flowtype", "work");
		tq2.processVariableValueEquals("_flowtype", "work");
		if(StringUtils.isNotEmpty(qm.getProcessDefinitionKey())){
			tq1.processDefinitionKey(qm.getProcessDefinitionKey());
			tq2.processDefinitionKey(qm.getProcessDefinitionKey());
		}
		if(StringUtils.isNotEmpty(qm.getFlowName())){
			
			tq1.processVariableValueEquals("flowName", qm.getFlowName());
			tq2.processVariableValueEquals("flowName", qm.getFlowName());
		}
		if(StringUtils.isNotEmpty(qm.getWorkTitle())){
			
			tq1.processVariableValueEquals("workTitle", qm.getWorkTitle());
			tq2.processVariableValueEquals("workTitle", qm.getWorkTitle());
		}
		if(StringUtils.isNotEmpty(qm.getTaskName())){
			tq1.taskNameLike(qm.getTaskName());
			tq2.taskNameLike(qm.getTaskName());
		}
		if(qm.getCreatedAfter()!=null){
			tq1.taskCreatedAfter(qm.getCreatedAfter());
			tq2.taskCreatedAfter(qm.getCreatedAfter());
		}
		if(qm.getCreatedBefore()!=null){
			tq1.taskCreatedBefore(qm.getCreatedBefore());
			tq2.taskCreatedBefore(qm.getCreatedBefore());
		}
		if(qm.getDueAfter()!=null){
			tq1.dueAfter(qm.getDueAfter());
			tq2.dueAfter(qm.getDueAfter());
		}
		if(qm.getDueBefore()!=null){
			tq1.dueBefore(qm.getDueBefore());
			tq2.dueBefore(qm.getDueBefore());
		}
		
		tasks.addAll(tq1.taskCandidateUser(userId).list());
		
		tasks.addAll(tq2.taskCandidateGroupIn(groupIds).list());
		
		
		String orderField=param.getSort();
		if(StringUtils.isNotEmpty(orderField)){
			
			if("createTime".equals(orderField)){
				if("asc".equals(param.getOrder())){
					Collections.sort(tasks, new Comparator<Task>() {
						@Override
						public int compare(Task t1, Task t2) {
							if(t1.getCreateTime().getTime() > t2.getCreateTime().getTime()){	
								return 1;
							}else if(t1.getCreateTime().getTime()< t2.getCreateTime().getTime()){
								return -1;
							}
							return 0;
						}
					 
					 });
					
				}else{
					Collections.sort(tasks, new Comparator<Task>() {

						@Override
						public int compare(Task t1, Task t2) {
							if(t1.getCreateTime().getTime() > t2.getCreateTime().getTime()){
								return -1;
							}else if(t1.getCreateTime().getTime() < t2.getCreateTime().getTime()){
								return 1;
							}
							return 0;
						}
					 
					 });
					
				}
				
			}
		}else{
			//默认按创建时间降序
			Collections.sort(tasks, new Comparator<Task>() {

				@Override
				public int compare(Task t1, Task t2) {
					if(t1.getCreateTime().getTime() > t2.getCreateTime().getTime()){
						return 1;
					}else if(t1.getCreateTime().getTime() < t2.getCreateTime().getTime()){
						return -1;
					}
					return 0;
				}
			 
			 });
			
		}
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Task t:tasks){
			Map<String,Object> variables =taskService.getVariables(t.getId());
			Map<String,Object> map=new HashMap<String,Object>();
			
			map.put("id",t.getId());
			map.put("name",t.getName());
			map.put("createTime",t.getCreateTime());
			map.put("processDefinitionId", t.getProcessDefinitionId());
			map.put("processInstanceId",t.getProcessInstanceId());
			map.put("flowName",variables.get("flowName"));
			map.put("workTitle",variables.get("workTitle"));
			map.put("applyUserId",MyCache.getInstance().getTrueName((String)variables.get("applyUserId")));
			
			String formkey=formService.getTaskFormKey(t.getProcessDefinitionId(), t.getTaskDefinitionKey());//任务表单
			if(formkey!=null){
				WfForm f=dao.get(WfForm.class, formkey);
				if(f!=null){
					map.put("completeUrl",f.getCompletePageUrl());
				}
			}
			
			list.add(map);
			
		}
		data.setRows(list);
		return data;
		
	}
	
	
	//查询待办任务
	public DataGrid selectTask(PageParam param,TaskQueryModel qm){
		DataGrid data=new DataGrid();
		TaskQuery tq=taskService.createTaskQuery();
		
		tq.processVariableValueEquals("_flowtype", "work");
		
		if(StringUtils.isNotEmpty(qm.getProcessDefinitionKey())){
			tq.processDefinitionKey(qm.getProcessDefinitionKey());
		}
		if(qm.getAssignee()!=null){
			tq.taskAssignee(qm.getAssignee());
		} 
		if(qm.getOwner()!=null){
			tq.taskOwner(qm.getOwner());
		}
		if(StringUtils.isNotEmpty(qm.getFlowName())){
			
			tq.processVariableValueEquals("flowName", qm.getFlowName());
			
		}
		if(StringUtils.isNotEmpty(qm.getWorkTitle())){
			
			tq.processVariableValueEquals("workTitle", qm.getWorkTitle());
			
		}
		if(StringUtils.isNotEmpty(qm.getTaskName())){
			tq.taskNameLike(qm.getTaskName());
			
		}
		if(qm.getCreatedAfter()!=null){
			tq.taskCreatedAfter(qm.getCreatedAfter());
			
		}
		if(qm.getCreatedBefore()!=null){
			tq.taskCreatedBefore(qm.getCreatedBefore());
			
		}
		if(qm.getDueAfter()!=null){
			tq.dueAfter(qm.getDueAfter());
		
		}
		if(qm.getDueBefore()!=null){
			tq.dueBefore(qm.getDueBefore());
		}
		data.setTotal(tq.count());
		
		String orderField=param.getSort();
		if(StringUtils.isNotEmpty(orderField)){
			
			if("createTime".equals(orderField)){
				tq.orderByTaskCreateTime();
			}
			if("asc".equals(param.getOrder())){
				tq.asc();
			}else{
				tq.desc();
			}
			
		}
		
		List<Task> tasks=tq.listPage((param.getPage()-1)*param.getRows(), param.getRows());
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Task t:tasks){
			Map<String,Object> variables =taskService.getVariables(t.getId());
			Map<String,Object> map=new HashMap<String,Object>();
			
			map.put("id",t.getId());
			map.put("name",t.getName());
			map.put("assignee",t.getAssignee());
			map.put("createTime",t.getCreateTime());
			map.put("processDefinitionId", t.getProcessDefinitionId());
			map.put("processInstanceId",t.getProcessInstanceId());
			map.put("flowName",variables.get("flowName"));
			map.put("workTitle",variables.get("workTitle"));
			map.put("applyUserId",MyCache.getInstance().getTrueName((String)variables.get("applyUserId")));
			
			String formkey=formService.getTaskFormKey(t.getProcessDefinitionId(), t.getTaskDefinitionKey());//任务表单
			if(formkey!=null){
				WfForm f=dao.get(WfForm.class, formkey);
				if(f!=null){
					map.put("completeUrl",f.getCompletePageUrl());
				}
			}
			
			list.add(map);
			
		}
		data.setRows(list);
		return data;
	}
	//查询待办任务总数量 包括未领任务数量
	public long selectTaskCountByUser(String userId,List<String> groupIds){
		
		long num1=taskService.createTaskQuery().processVariableValueEquals("_flowtype", "work").taskCandidateUser(userId).count();
		long num2=taskService.createTaskQuery().processVariableValueEquals("_flowtype", "work").taskCandidateGroupIn(groupIds).count();
		long num3=taskService.createTaskQuery().processVariableValueEquals("_flowtype", "work").taskAssignee(userId).count();
		
		return num1+num2+num3;
	}
	
	
	//领取任务
	public String updateReceiveTask(String taskId){
		
		Task task= taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task==null){
			
			return MsgConfig.MSG_KEY_NODATA;
			
		}
		
		String userId= ServletUtil.getMember().getId();
		
		taskService.claim(taskId, userId);
		
		return MsgConfig.MSG_KEY_SUCCESS;
		
	}
	//添加候选人
	public boolean  addCandidate(String taskId,String[] userIds){
		
		for(String id:userIds){
			taskService.addCandidateUser(taskId, id);
		}
		return true;
	}
	
	//委托办理人
	public boolean updateDelegateTask(String taskId,String userId){
		
		taskService.delegateTask(taskId, userId); 
		
		return true;
		
	}
	//收回委托任务
	public boolean updateRevokeTask(String taskId){
		
		taskService.setAssignee(taskId,ServletUtil.getMember().getId());
		taskService.setOwner(taskId, "");
		
		return true;
		
	}
	
	
	
	//根据任务id，获取单个任务信息
	public HistoricTaskInstance selectOneTask(String taskId){
		HistoricTaskInstance ht=historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		
		return ht;
	
	}
	
	//完成任务页面，获取任务信息
	public Map<String,Object> selectTaskforComplete(String taskId){
		
		
		Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task==null){
			return null;
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
	
		map.put("task",task);
		
		if(taskService.getVariablesLocal(taskId).get("canBack")!=null){
			//当前任务可以有驳回操作
			Map<String,Object> variables=taskService.getVariables(taskId);
			map.put("backActivityId", variables.get("backTaskIds"));//可驳回的任务
		}
		WfWork work=dao.get(WfWork.class, task.getProcessInstanceId());
		if(work!=null){
			map.put("flowFormHtml",work.getFormHtml());//流程全局表单
		}
		
		String formkey=formService.getTaskFormKey(task.getProcessDefinitionId(), task.getTaskDefinitionKey());//任务表单
		if(formkey!=null){
			WfForm f=dao.get(WfForm.class, formkey);
			if(f!=null){
				map.put("taskFormHtml",f.getFormHtml());
				map.put("completeUrl",f.getCompleteUrl());
			}else{
				throw new MyRuntimeException("错误：工作流程关联的表单不存在，已被删除！");
			}
		}
		
		return map;
		
	}
	
	
	
	//完成任务
	public String updateCompleteTask(String taskId,String flowFormHtml,String taskFormHtml,String[] processVariablesKeys,HttpServletRequest request,String savePath,FileList files){
		
		Task task= taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task==null){
			
			return MsgConfig.MSG_KEY_NODATA;
			
		}
		String userId=ServletUtil.getMember().getId();//当前登陆人
		
		String assignee=task.getAssignee();//任务执行人
		
		if(assignee==null){
			//未领取任务，先领取
			taskService.claim(taskId, userId);
			assignee=userId;
		}
		//当前登陆人是不是此任务执行人
		if(userId.equals(assignee)){
			
			//完成任务
			
			//需要保存的流程变量
			Map<String,Object> vm=new HashMap<String,Object>();
			for(String key:processVariablesKeys){
				 vm.put(key,request.getParameter(key));
			}
			if(StringUtils.isNotEmpty(flowFormHtml)){
		
				WfWork work=dao.get(WfWork.class, task.getProcessInstanceId());
				
				work.setFormHtml(flowFormHtml);//流程表单
				
			}
			String nextType=request.getParameter("nextType");//流程完成类型，2：驳回
			if("2".equals(nextType)){
				//驳回
				String backActivityId=request.getParameter("activityId");
				if(StringUtils.isBlank(backActivityId)){
					//没有驳回节点
					return "msg.workflow.nobackActivity";
				}
				Map<String,Object> variables=taskService.getVariables(taskId);
				List<Map<String,String>> backTaskIds=(List<Map<String,String>>)variables.get("backTaskIds");//获取可驳回任务列表
				List<Map<String,String>> newList=new ArrayList<Map<String,String>>();
				for(int i=0;i<backTaskIds.size();i++){
					Map<String,String> m=backTaskIds.get(i);
					String id=m.get("id");
					if(id.equals(backActivityId)){
						break;
					}
					newList.add(m);
				}
				taskService.setVariable(taskId, "backTaskIds", newList);//更新可驳回任务列表，清空这次驳回之后的任务
				//驳回操作
				backProcess(taskId, backActivityId, vm);
			
			}else{
				System.out.println("type=="+vm.get("charType"));
				//正常流转
				taskService.complete(taskId,vm);
			}
			
			WfTask wt=new WfTask();
			wt.setCompleteTime(DateUtil.currentTimestamp());
			wt.setId(taskId);
			wt.setTaskName(task.getName());
			wt.setTaskAssignee(userId);
			wt.setWorkId(task.getProcessInstanceId());
			wt.setTaskOwner(task.getOwner());//设置任务委托人
			if(StringUtils.isNotEmpty(taskFormHtml)){
				wt.setFormHtml(taskFormHtml);//任务表单保存到任务变量
			}
			
			//等待保存的对象集合
			List<Object> c=new ArrayList<Object>();
			c.add(wt);
			
			//上传附件
			try {
				List<MultipartFile> fileList=files.getFile();
				if(fileList!=null){
					for(MultipartFile f:fileList){
						if(f!=null&&!f.isEmpty()){
							String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
							String ext=FileUtils.getFileExt(f.getOriginalFilename());//获取文件后缀
							//保存文件
							File newFile = new File(savePath+"/"+uuid+"."+ext); 
							f.transferTo(newFile);
							WfTaskFiles tf=new WfTaskFiles();
							tf.setWorkId(task.getProcessInstanceId());
							tf.setTaskId(taskId);
							tf.setFileExt(ext);
							tf.setFileName(f.getOriginalFilename());
							tf.setFileNewName(uuid);
							tf.setFileSize((int)f.getSize());
							
							c.add(tf);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			
			dao.saveOrUpdateAll(c);
			
			
			return MsgConfig.MSG_KEY_SUCCESS;
		
		}else{

			return MsgConfig.MSG_KEY_FAIL;
		
		}
		
	}
	//条件分页查询已完成的任务
	public DataGrid selectHistoricTask(PageParam param,WfTask t,WfWork wf,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WfTask t,WfWork wf where t.workId=wf.id ");
		List<Object> list=new ArrayList<Object>();
		//查询条件
		if(StringUtils.isNotBlank(t.getTaskAssignee())){
			sb.append(" and t.taskAssignee = ? ");
			list.add(t.getTaskAssignee());	
		}
		if(StringUtils.isNotBlank(t.getTaskOwner())){
			sb.append(" and t.taskOwner = ? ");
			list.add(t.getTaskOwner());	
		}
		if(StringUtils.isNotBlank(wf.getUserId())){
			sb.append(" and wf.userId = ? ");
			list.add(wf.getUserId());	
		}
		
		if(StringUtils.isNotBlank(t.getTaskName())){
			sb.append(" and t.taskName like ? ");
			list.add("%"+t.getTaskName()+"%");
		}
		if(StringUtils.isNotBlank(wf.getWorkTitle())){
			sb.append(" and wf.workTitle like ? ");
			list.add("%"+wf.getWorkTitle()+"%");
		}
		if(StringUtils.isNotBlank(wf.getFlowName())){
			sb.append(" and wf.flowName like ? ");
			list.add("%"+wf.getFlowName()+"%");
		}
		if(startDate!=null){
			sb.append(" and t.completeTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and t.completeTime <=? ");
			list.add(endDate);	
		}
		if(StringUtils.isNotBlank(t.getFormHtml())){
			sb.append(" and t.formHtml like ? ");
			list.add("%"+t.getFormHtml()+"%");
		}
		data.setTotal((Long)dao.findUniqueOne("select count(t.id)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by t.completeTime desc ");
		}
		List<Map> rows=dao.findPage("select new Map(wf.id as workId,wf.flowName as flowName,wf.workTitle as workTitle,wf.processDefinitionId as processDefinitionId," +
				"wf.userId as userId,t.id as id,t.taskName as taskName,t.taskOwner as taskOwner,t.completeTime as completeTime) "+sb.toString(),param.getPage(),param.getRows(),list);
		for(Map r:rows){
			r.put("userName", MyCache.getInstance().getTrueName((String)r.get("userId")));
			r.put("ownerName", MyCache.getInstance().getTrueName((String)r.get("taskOwner")));
		}
		data.setRows(rows);
		return data;
		
		
	}
	

	/** 
     * 根据当前任务ID，查询可以驳回的任务节点 
     *  
     * @param taskId 
     *            当前任务ID 
     */  
    public  List<Map<String, String>> selectBackAvtivity(String taskId) {  
    	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
    	try {
			
    		List<ActivityImpl> actLst=findBackAvtivity(taskId);
			for(ActivityImpl act:actLst){
				Map<String, String> map=new HashMap<String,String>();
				map.put("id", act.getId());
				map.put("name",(String)act.getProperty("name") );
				list.add(map);
			}
    		
    		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	
    	return list;
    }
    
	
    //*************************Android 应用***************************************//
    
   
    
    
    
    
    
    
    
    
}
