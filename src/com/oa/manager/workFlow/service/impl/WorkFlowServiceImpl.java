/**  
 * @Project: jxoa
 * @Title: WorkFlowServiceImpl.java
 * @Package com.oa.manager.workFlow.service.impl
 * @date 2013-7-2 下午3:50:03
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.workFlow.action.TaskAction;
import com.oa.manager.workFlow.action.WorkAction;
import com.oa.manager.workFlow.bean.WfTaskFiles;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.bean.WfWorkFiles;
import com.oa.manager.workFlow.bean.WfWorkflow;
import com.oa.manager.workFlow.bean.WfWorkflowPowers;
import com.oa.manager.workFlow.service.IWorkFlowService;

/**
 * 
 * 类名：WorkFlowServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-2 下午3:50:03
 *
 */
@Service
public class WorkFlowServiceImpl extends BaseServiceImpl implements IWorkFlowService{
	
	
	@Autowired
	protected RepositoryService repositoryService; //部署流程服务
	@Autowired
	protected RuntimeService runtimeService; //流程执行服务 
	@Autowired
	protected TaskService taskService ; //任务服务
	@Autowired
	protected HistoryService historyService; //历史服务
	@Autowired
	protected ManagementService managermentService; //流程管理服务
	@Autowired
	protected IdentityService identityService; //身份认证服务
	@Autowired
	protected FormService formService; //表单服务
	
	
	//查询流程设计
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectWorkflow(PageParam param,WfWorkflow wf,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WfWorkflow wf where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(wf.getFlowType())){
			sb.append(" and wf.flowType=? ");
			list.add(wf.getFlowType());
		}
		if(StringUtils.isNotBlank(wf.getFlowName())){
			sb.append(" and wf.flowName like ? ");
			list.add("%"+wf.getFlowName()+"%");	
		}
		if(StringUtils.isNotBlank(wf.getId())){
			sb.append(" and wf.flowKey = ? ");
			list.add(wf.getId());	
		}
		if(wf.getIsEnable()!=null){
			sb.append(" and wf.isEnable = ? ");
			list.add(wf.getIsEnable());	
		}
		if(wf.getReleaseState()!=null){
			sb.append(" and wf.releaseState = ? ");
			list.add(wf.getReleaseState());	
		}
		if(startDate!=null){
			sb.append(" and wf.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and wf.createTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by wf.createTime ");
		}
		List<WfWorkflow> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(WfWorkflow r:rows){
			r.setFlowXml("");
		}
		data.setRows(rows);
		return data;

	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long selectWorkflowCount(WfWorkflow wf,Date startDate,Date endDate){
		
		StringBuffer sb=new StringBuffer("select count(*)from WfWorkflow wf where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(wf.getFlowType())){
			sb.append(" and wf.flowType=? ");
			list.add(wf.getFlowType());
		}
		if(StringUtils.isNotBlank(wf.getFlowName())){
			sb.append(" and wf.flowName like ? ");
			list.add("%"+wf.getFlowName()+"%");	
		}
		if(StringUtils.isNotBlank(wf.getId())){
			sb.append(" and wf.flowKey = ? ");
			list.add(wf.getId());	
		}
		if(wf.getIsEnable()!=null){
			sb.append(" and wf.isEnable = ? ");
			list.add(wf.getIsEnable());	
		}
		if(wf.getReleaseState()!=null){
			sb.append(" and wf.releaseState = ? ");
			list.add(wf.getReleaseState());	
		}
		if(startDate!=null){
			sb.append(" and wf.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and wf.createTime <=? ");
			list.add(endDate);	
		}	
		return (Long)dao.findUniqueOne(sb.toString(),list);
		
	}
	//添加流程
	public String addWfWorkflow(WfWorkflow wf,String[] userIds,String[] deptIds){
		
		//新增
		Object obj=dao.findOne("from WfWorkflow where flowName=? ",wf.getFlowName());
		if(obj==null){
			Object obj2=dao.findOne("from WfWorkflow where flowId=? ",wf.getFlowId());
			if(obj2==null){
				wf.setCreateTime(DateUtil.currentTimestamp());
				wf.setCreateUserId(ServletUtil.getMember().getId());
				
				//发布状态，新增加：0, 已发布：1,已发布过，有更新2
				wf.setReleaseState((short)0);
				wf.setVersionNum(0);
				String workId=(String)dao.saveReturnId(wf);
				
				List<WfWorkflowPowers> c=new ArrayList<WfWorkflowPowers>();
				//权限为所有人都可以用
				if(userIds.length==0&&deptIds.length==0){
					WfWorkflowPowers power=new WfWorkflowPowers();
					power.setPowerId("");
					power.setPowerType((short)0);
					power.setWorkId(workId);
					c.add(power);
				}else{
					for(String id:deptIds){
						WfWorkflowPowers power=new WfWorkflowPowers();
						power.setPowerId(id);
						power.setPowerType((short)1);
						power.setWorkId(workId);
						c.add(power);
					}
					for(String id:userIds){
						WfWorkflowPowers power=new WfWorkflowPowers();
						power.setPowerId(id);
						power.setPowerType((short)2);
						power.setWorkId(workId);
						c.add(power);
					}
				}
				return dao.saveOrUpdateAll(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
			}else{
				return "msg.workflow.key.unique";//流程key重复！
			}
		}else{
			
			return "msg.workflow.name.unique";//流程名称重复！
		}
			
			
		
		
	}
	
	
	
	//保存流程设计
	public String saveFlow(WfWorkflow wf){
		
		Object obj=dao.findOne("from WfWorkflow where flowName=? and id!=? ",wf.getFlowName(),wf.getId());
		if(obj==null){
			
			WfWorkflow oldwf=dao.get(WfWorkflow.class, wf.getId());//查询出原流程
			//更新流程
			
			oldwf.setFlowName(wf.getFlowName());//更新最新流程名称
			oldwf.setFlowXml(wf.getFlowXml());//更新流程设计
			oldwf.setFlowDesc(wf.getFlowDesc());//更新流程描述
			oldwf.setUpdateDesc(wf.getUpdateDesc());
			oldwf.setUpdateTime(DateUtil.currentTimestamp());
			oldwf.setUpdateUserId(ServletUtil.getMember().getId());
			
			if(oldwf.getReleaseState()==1){
				oldwf.setReleaseState((short)2);
			}
			
			return MsgConfig.MSG_KEY_SUCCESS;
				
		}else{
			return "msg.workflow.name.unique";//流程id重复，此流程已存在！
		}
			
		
		
	}

	//部署流程
	public String updateDeployFlow(String id){
		
		WfWorkflow wf=dao.get(WfWorkflow.class, id);
		
		if(wf==null){
			
			return MsgConfig.MSG_KEY_NODATA;
		}
		if(StringUtils.isEmpty(wf.getFlowXml())){
			
			return "msg.workflow.noxml";
		}
	
		try {
			
			ByteArrayInputStream stream = new ByteArrayInputStream(wf.getFlowXml().getBytes("utf-8"));
			
		    String deploymentId=repositoryService.createDeployment().addInputStream(wf.getFlowId()+".bpmn", stream).category(wf.getFlowType()).name(wf.getFlowName()).deploy().getId();
			
		    ProcessDefinition processDefinition= repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
		
		    wf.setReleaseState((short)1);
		    wf.setVersionNum(wf.getVersionNum()+1);
			return MsgConfig.MSG_KEY_SUCCESS;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new RuntimeException();
			
		}
		
	}
	
	
	//删除流程定义
	public boolean deleteFlows(String[] ids){
		
		for(String id:ids){
			WfWorkflow workflow=dao.get(WfWorkflow.class, id);
			if(workflow!=null){
				
				//级联删除所有数据，包括所有版本流程定义，流程实例，流程历史
				List<ProcessDefinition> pdLst=repositoryService.createProcessDefinitionQuery().processDefinitionKey(workflow.getFlowId()).list();
				
				for(ProcessDefinition pd:pdLst){
					
					repositoryService.deleteDeployment(pd.getDeploymentId(), true);//删除流程部署,并级联删除历史记录和流程实例
					
					//级联删除自定义表和附件
					List<WfWork> works=dao.find("from WfWork where processDefinitionId=?",pd.getId());
					
					for(WfWork w:works){
						//工作附件
						List<WfWorkFiles> workfiles=dao.find("from WfWorkFiles where workId=?",w.getId());
						
						for(WfWorkFiles f:workfiles){
							if(f!=null){
								File file = new File(WorkAction.SAVEPATH+"/"+f.getFileNewName()+"."+f.getFileExt()); 
								file.delete();
							}
							dao.delete(f);
							
						}
						
						
						//删除全部任务
						dao.delete("delete WfTask where workId=?",w.getId());
						//查询任务附件
						List<WfTaskFiles> tks=dao.find("from WfTaskFiles where workId=?",w.getId());
						
						for(WfTaskFiles f:tks){
							if(f!=null){
								File file = new File(TaskAction.SAVEPATH+"/"+f.getFileNewName()+"."+f.getFileExt()); 
								file.delete();
							}
							dao.delete(f);
							
						}
						dao.delete(w);
					}
					
						
				}
				dao.delete(workflow);
			}
		}
		return true;
	}
	
	//查询出所有流程的id，name,type,key
	public List selectAllWorkflow(){
		
		return dao.find("select new Map(f.id as id, f.flowName as name,f.flowType as type,f.flowId as key)from WfWorkflow f");
	
	}
	
	//根据流程设计id，查询权限
	public Map selectforUpdate(String id){
		WfWorkflow wf=dao.get(WfWorkflow.class,id);
		if(wf==null){
			return null;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("wf", wf);
		List<WfWorkflowPowers> ps=dao.find("from WfWorkflowPowers where workId=?",id);
		
		List<String> userIds=new ArrayList<String>();
		List<String> deptIds=new ArrayList<String>();
		for(WfWorkflowPowers p:ps){
			if(StringUtils.isNotEmpty(p.getPowerId())){
				if(p.getPowerType()==1){
					deptIds.add(p.getPowerId());
				}else if(p.getPowerType()==2){
					userIds.add(p.getPowerId());
				}
			}
		}
		
		if(userIds.isEmpty()){
			map.put("userIds", "");
		}else{
			map.put("userIds", StringUtils.join(userIds,","));
		}
		if(deptIds.isEmpty()){
			map.put("deptIds", "");
		}else{
			map.put("deptIds", StringUtils.join(deptIds,","));
		}
	
		return map;
		
	}
	
	public String  updateWfWorkflow(WfWorkflow wf,String[] userIds,String[] deptIds){
		WfWorkflow old=dao.get(WfWorkflow.class,wf.getId());
		if(old==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		old.setIsEnable(wf.getIsEnable());
		//等待更新的对象集合
		List<Object> c=new ArrayList<Object>();
		if(userIds.length==0&&deptIds.length==0){
			dao.delete("delete WfWorkflowPowers where workId=? ",wf.getId());
			//默认权限为所有人都可以用
			WfWorkflowPowers power=new WfWorkflowPowers();
			power.setPowerId("");
			power.setPowerType((short)0);
			power.setWorkId(wf.getId());
			c.add(power);
			
		}else{
			dao.delete("delete WfWorkflowPowers where workId=? and powerType=0",wf.getId());
		}
		
		List<WfWorkflowPowers> ps=dao.find("from WfWorkflowPowers where workId=?",wf.getId());
		List<String> oldUserIds=new ArrayList<String>();
		List<String> oldDeptIds=new ArrayList<String>();
		for(WfWorkflowPowers p:ps){
			if(StringUtils.isNotEmpty(p.getPowerId())){
				if(p.getPowerType()==1){
					oldDeptIds.add(p.getPowerId());
				}else if(p.getPowerType()==2){
					oldUserIds.add(p.getPowerId());
				}
			}
		}
		//用户id处理
		List<String> addUserIds=new ArrayList<String>();//需要添加的uid
		for(String uid:userIds){
			addUserIds.add(uid);
		}
	
		
		//原先已有的-现在需要添加的==需要删除的
		List<String> delUserIds=new ArrayList<String>(oldUserIds);//需要删除的
		delUserIds.remove(addUserIds);
		for(String uid:delUserIds){
			dao.delete("delete WfWorkflowPowers where workId=? and powerId=? ",wf.getId(),uid);
		}
		//现在需要添加的-原先已有的=需要真正添加的
		addUserIds.remove(oldUserIds);
		for(String uid:addUserIds){
			WfWorkflowPowers power=new WfWorkflowPowers();
			power.setPowerId(uid);
			power.setPowerType((short)2);
			power.setWorkId(wf.getId());
			c.add(power);
		}
		
		//部门id处理
		List<String> addDeptIds=new ArrayList<String>();//需要添加的did
		for(String did:deptIds){
			addDeptIds.add(did);
		}
	
		
		//原先已有的-现在需要添加的==需要删除的
		List<String> delDeptIds=new ArrayList<String>(oldDeptIds);//需要删除的
		delDeptIds.remove(addDeptIds);
		for(String did:delDeptIds){
			dao.delete("delete WfWorkflowPowers where workId=? and powerId=? ",wf.getId(),did);
		}
		//现在需要添加的-原先已有的=需要真正添加的
		addDeptIds.remove(oldDeptIds);
		for(String did:addDeptIds){
			WfWorkflowPowers power=new WfWorkflowPowers();
			power.setPowerId(did);
			power.setPowerType((short)1);
			power.setWorkId(wf.getId());
			c.add(power);
		}
		
		return dao.saveOrUpdateAll(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
	}
	
	
	
	//查询流程设计 根据权限
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectWorkflowByPower(PageParam param,WfWorkflow wf){
		Member user=ServletUtil.getMember();
		
		StringBuffer sb=new StringBuffer("select distinct wf from WfWorkflow wf,WfWorkflowPowers wp where (wp.powerType=0 or wp.powerId=? or wp.powerId=?) and wp.workId=wf.id and wf.versionNum>0 and wf.isEnable=1 ");
		List list=new ArrayList();
		list.add(user.getId());
		list.add(user.getDeptId());
		//查询条件
		if(StringUtils.isNotBlank(wf.getFlowType())){
			sb.append(" and wf.flowType=? ");
			list.add(wf.getFlowType());
		}
		if(StringUtils.isNotBlank(wf.getFlowName())){
			sb.append(" and wf.flowName like ? ");
			list.add("%"+wf.getFlowName()+"%");	
		}
		if(StringUtils.isNotBlank(wf.getId())){
			sb.append(" and wf.flowKey = ? ");
			list.add(wf.getId());	
		}
		
		
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			sb.append(" order by wf."+param.getSort()+" "+param.getOrder());
			
		}else{
			sb.append(" order by wf.createTime");
		}
		return dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		
	
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	

	

	
}
