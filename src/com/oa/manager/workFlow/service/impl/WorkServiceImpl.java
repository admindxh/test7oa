/**  
 * @Title: WorkServiceImpl.java
 * @date 2013-8-20 上午10:51:54
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.AppUtil;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.workFlow.action.TaskAction;
import com.oa.manager.workFlow.action.WorkAction;
import com.oa.manager.workFlow.bean.WfForm;
import com.oa.manager.workFlow.bean.WfTask;
import com.oa.manager.workFlow.bean.WfTaskFiles;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.bean.WfWorkFiles;
import com.oa.manager.workFlow.service.IFlowUtilService;
import com.oa.manager.workFlow.service.IWorkService;

/**
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
@Service
public class WorkServiceImpl extends BaseServiceImpl implements IWorkService{
	
	@Autowired
	protected IFlowUtilService flowUtilService; //流程工具服务
	@Autowired
	private RuntimeService runtimeService; //流程执行服务 
	
	@Autowired
	private HistoryService historyService; //历史服务
	
	//查看流程定义信息，根据key获取最新版本
	public ProcessDefinition getProcessDefinition(String key){
		
		return flowUtilService.queryProcessDefinitionLatestVersion(key);
		
	}
	
	//新创建工作 发起流程
	public Map saveWork(String id,String key,String workTitle,String savePath,FileList files){
		
		Map<String,Object> msg=new HashMap<String,Object>();
		
		ProcessDefinition pd=flowUtilService.queryProcessDefinitionLatestVersion(key);
		if(pd==null){
			
			msg.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
			msg.put(MsgConfig.MESSAGE,"流程定义不存在！");
			
			return msg;
		}
		Timestamp nowTime=DateUtil.currentTimestamp();
		String userId=ServletUtil.getMember().getId();
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("_flowtype", "work");//标识为工作流程的工作，区别其他单独使用的流程
		map.put("flowName", pd.getName());//流程名称
		map.put("workTitle", workTitle);//工作标题
		map.put("startTime",nowTime );//将开始时间放入流程变量，用于运行时流程查看
		
		ProcessInstance processInstance=flowUtilService.startProcessInstance(userId, key, map);
		
		AppUtil.println("流程实例ID",processInstance.getId());//qjlc.50001
		
		WfWork work=new WfWork();
		work.setProcessDefinitionId(processInstance.getProcessDefinitionId());
		work.setFlowName(pd.getName());
		work.setId(processInstance.getProcessInstanceId());
		work.setStartTime(nowTime);
		work.setUserId(userId);
		work.setWorkTitle(workTitle);
		work.setWorkflowId(id);
		work.setWorkStatus((short)0);
		String formId =flowUtilService.getStartFormKey(pd.getId());
		
		if(formId!=null){
			WfForm f=dao.get(WfForm.class, formId);
			if(f!=null){
				work.setFormHtml(f.getFormHtml());
			}
		}
		//等待保存的对象集合
		List<Object> c=new ArrayList<Object>();
		c.add(work);
		
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
						WfWorkFiles wwf=new WfWorkFiles();
						
						wwf.setFileExt(ext);
						wwf.setFileName(f.getOriginalFilename());
						wwf.setFileNewName(uuid);
						wwf.setFileSize((int)f.getSize());
						wwf.setWorkId(work.getId());
						c.add(wwf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		dao.saveOrUpdateAll(c);
		
		//获取当前发起的流程的所有需要办理的任务
		List<Task> tasks=flowUtilService.queryTasksByProcessInstanceId(processInstance.getId());
		
		for(Task t:tasks){
			if(t!=null&&userId.equals(t.getAssignee())){
				
				msg.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
				msg.put(MsgConfig.MESSAGE,"");
			
				msg.put("currentCallback","task/completePage.do?taskId="+t.getId());
				
				return msg;
			}
		}
		msg.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		msg.put(MsgConfig.MESSAGE,"添加成功");
		msg.put("currentCallback","close");
		return msg;
			
	}
	//查询某人发起的工作，正在执行中的
	public DataGrid queryRunProcessInstance(PageParam param,WfWork wf,Date startedAfter,Date startedBefore){
		DataGrid data=new DataGrid();
		ProcessInstanceQuery query=  runtimeService.createProcessInstanceQuery();
		query.variableValueEquals("_flowtype", "work");
		if(wf.getUserId()!=null){
			query.variableValueEquals("applyUserId", wf.getUserId());
		}
		if(StringUtils.isNotEmpty(wf.getFlowName())){
			query.variableValueLike("flowName", "%"+wf.getFlowName()+"%");
		}
		if(StringUtils.isNotEmpty(wf.getWorkTitle())){
			query.variableValueLike("workTitle", "%"+wf.getWorkTitle()+"%");
		}
		if(startedAfter!=null){
			query.variableValueGreaterThanOrEqual("startTime", startedAfter);
		}
		if(startedBefore!=null){
			query.variableValueLessThanOrEqual("startTime", startedBefore);
		}
		data.setTotal(query.count());
		
		List<ProcessInstance> pis=query.listPage((param.getPage()-1)*param.getRows(), param.getRows());
	
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		for(ProcessInstance p:pis){
			//单独调用流程变量查询，解决序列化问题
			Map<String,Object> variables=runtimeService.getVariables(p.getProcessInstanceId());
			
			Map<String,Object> m=new HashMap<String,Object>();
			
			m.put("processDefinitionId",p.getProcessDefinitionId());
			m.put("processInstanceId",p.getProcessInstanceId());
			m.put("flowName",variables.get("flowName"));
			m.put("workTitle",variables.get("workTitle") );
			m.put("startTime",variables.get("startTime") );
			
			//获取当前活动节点
			List<String> activityIds =runtimeService.getActiveActivityIds(p.getProcessInstanceId());
			if(activityIds!=null){
				
				ProcessDefinitionEntity  pde=(ProcessDefinitionEntity)flowUtilService.getProcessDefinition(p.getProcessDefinitionId());
				
				List<ActivityImpl> activitiList =pde.getActivities();//获取流程定义全部节点
				
				List<String> currentActivityNames=new ArrayList<String>();
				
				for(ActivityImpl a:activitiList){
					
					if(activityIds.contains(a.getId())){
						currentActivityNames.add((String)a.getProperty("name"));
					}
				
				}
				m.put("currentActivityName",StringUtils.join(currentActivityNames,","));
				
			}else{
				m.put("currentActivityName","");
			}
			list.add(m);
		}
		data.setRows(list);
		return data;
		
	}
	//工作
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectWork(PageParam param,WfWork wf,Date startedAfter,Date startedBefore,Date finishedAfter,Date finishedBefore){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WfWork w where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(wf.getUserId())){
			sb.append(" and w.userId = ? ");
			list.add(wf.getUserId());	
		}
		if(StringUtils.isNotBlank(wf.getProcessDefinitionId())){
			sb.append(" and w.processDefinitionId = ? ");
			list.add(wf.getProcessDefinitionId());	
		}
		if(StringUtils.isNotBlank(wf.getWorkflowId())){
			sb.append(" and w.workflowId = ? ");
			list.add(wf.getWorkflowId());	
		}
		if(wf.getWorkStatus()!=null){
			sb.append(" and w.workStatus = ? ");
			list.add(wf.getWorkStatus());
		}
		if(StringUtils.isNotBlank(wf.getFlowName())){
			sb.append(" and w.flowName like ? ");
			list.add("%"+wf.getFlowName()+"%");	
		}
		if(StringUtils.isNotBlank(wf.getWorkTitle())){
			sb.append("and w.workTitle like ? ");
			list.add("%"+wf.getWorkTitle()+"%");	
		}
		
		if(startedAfter!=null){
			sb.append(" and w.startTime >=? ");
			list.add(startedAfter);	
		}
		if(startedBefore!=null){
			sb.append(" and w.startTime <=? ");
			list.add(startedBefore);	
		}
		
		if(finishedAfter!=null){
			sb.append(" and w.endTime >=? ");
			list.add(finishedAfter);	
		}
		if(finishedBefore!=null){
			sb.append(" and w.endTime <=? ");
			list.add(finishedBefore);	
		}
		
		if(StringUtils.isNotBlank(wf.getFormHtml())){
			sb.append("and w.formHtml like ? ");
			list.add("%"+wf.getFormHtml()+"%");	
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by w.startTime desc ");
		}
		List<WfWork> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(WfWork r:rows){
			r.setFormHtml("");
		}
		data.setRows(rows);
		return data;
		
		
	
	}

	//查询工作明细
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map selectProcessDetails(String processInstanceId){
		
		//工作表
		WfWork work=dao.get(WfWork.class, processInstanceId);
		if(work==null){
			return null;
		}
		Map map=new HashMap();
		//流程实例
		HistoricProcessInstance hpi=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	
		//流程定义
		ProcessDefinition pd=flowUtilService.getProcessDefinition(hpi.getProcessDefinitionId());
		//任务
		List<HistoricTaskInstance> hti=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricTaskInstanceStartTime().asc().list();
		
		map.put("work", work);
		map.put("hpi", hpi);
		map.put("pd", pd);
		map.put("hti", hti);
		
		//全局表单
		
		map.put("flowFormHtml", work.getFormHtml());
		//全局附件
		List<WfWorkFiles> workFiles=dao.find("from WfWorkFiles where workId=? ",processInstanceId);
		map.put("workFiles", workFiles);
		
		//任务表单
		List<WfTask> wts=dao.find("from WfTask t where t.workId=? ",work.getId());
		Map<String,String> taskFormHtml=new HashMap<String,String>();
		for(WfTask t:wts){
			taskFormHtml.put(t.getId(), t.getFormHtml());
		}
		map.put("taskFormHtml", taskFormHtml);
		//任务附件
		Map<String,List<WfTaskFiles>> taskFiles=new HashMap<String,List<WfTaskFiles>>();
		List<WfTaskFiles> taskFilesLst=dao.find("from WfTaskFiles where workId=? ",processInstanceId);
		
		for(WfTaskFiles f:taskFilesLst){
			if(!taskFiles.containsKey(f.getTaskId())){
				List<WfTaskFiles> fs=new ArrayList<WfTaskFiles>();
				for(WfTaskFiles tf:taskFilesLst){
					if(f.getTaskId().equals(tf.getTaskId())){
						fs.add(tf);
					}
				}
				taskFiles.put(f.getTaskId(), fs);
			}
			
			
		}
		map.put("taskFiles", taskFiles);
		return map;
	}
	
	
	//删除运行中的流程实例
	public boolean deleteProcessInstance(String id,String deleteReason){
		
		runtimeService.deleteProcessInstance(id, deleteReason);//删除流程实例，deleteReason:删除原因
		
		return true;
	}
	
	//删除历史流程实例
	public boolean deleteHistoricProcessInstance(String[] ids){
		for(String id:ids){
			WfWork work=dao.get(WfWork.class, id);
			if(work!=null&&work.getWorkStatus()!=0){//只能删除已完成的流程
				historyService.deleteHistoricProcessInstance(id);
				//级联删除自定义表
				dao.delete(work);
				dao.delete("delete WfTask where workId=?",id);
				
				//工作附件
				List<WfWorkFiles> workfiles=dao.find("from WfWorkFiles where workId=?",id);
				
				for(WfWorkFiles f:workfiles){
					if(f!=null){
						File file = new File(WorkAction.SAVEPATH+"/"+f.getFileNewName()+"."+f.getFileExt()); 
						file.delete();
					}
					dao.delete(f);
					
				}
				//查询任务附件
				List<WfTaskFiles> tks=dao.find("from WfTaskFiles where workId=?",id);
				
				for(WfTaskFiles f:tks){
					if(f!=null){
						File file = new File(TaskAction.SAVEPATH+"/"+f.getFileNewName()+"."+f.getFileExt()); 
						file.delete();
					}
					dao.delete(f);
					
				}
				
			}
			
			
		}
		return true;
	}
	

	
	
}
