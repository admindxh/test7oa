/**  
 * @Project: jxoa
 * @Title: ProcessDefinitionServiceImpl.java
 * @Package com.oa.manager.workFlow.service.impl
 * @date 2013-7-19 下午10:23:09
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.action.TaskAction;
import com.oa.manager.workFlow.action.WorkAction;
import com.oa.manager.workFlow.bean.WfForm;
import com.oa.manager.workFlow.bean.WfTaskFiles;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.bean.WfWorkFiles;
import com.oa.manager.workFlow.bean.WfWorkflow;
import com.oa.manager.workFlow.service.IProcessDefinitionService;

/**
 * 
 * 类名：ProcessDefinitionServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-19 下午10:23:09
 *
 */
@Service
public class ProcessDefinitionServiceImpl extends BaseServiceImpl implements IProcessDefinitionService{

	@Autowired
	protected RepositoryService repositoryService; //部署流程服务
	
	@Autowired
	protected RuntimeService runtimeService; //流程执行服务 
	
	@Autowired
	protected FormService formService; //表单服务
	
	//查询所有版本流程
	public DataGrid selectAllProcessDefinitionByKey(PageParam param,String processDefinitionKey){
		DataGrid data=new DataGrid();
	
		ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey);
		
		data.setTotal(query.count());
		
		List<ProcessDefinition> pds=query.orderByProcessDefinitionVersion().desc().listPage((param.getPage()-1)*param.getRows(), param.getRows());
		
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		for(ProcessDefinition pd:pds){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", pd.getId());
			map.put("name", pd.getName());
			map.put("version", pd.getVersion());
			map.put("deploymentId", pd.getDeploymentId());
			
			rows.add(map);
			
		}
		data.setRows(rows);
		return data;
	}


	//根据流程部署id获取流程设计图
	public String getFlowImg(String processDefinitionId,HttpServletResponse response){
		
		
		 ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
		 
		 if(processDefinition==null){
			 return "";
		 }
		
		// InputStream inputStream=ProcessDiagramGenerator.generateDiagram(repositoryService.getBpmnModel(processDefinitionId), "png",ids);
		 //InputStream inputStream =ProcessDiagramGenerator.generatePngDiagram(((RepositoryServiceImpl)repositoryService).getBpmnModel(processDefinitionId));
		 InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
		
		 ServletOutputStream out=null;
		 try{ 
			 response.setDateHeader("Expires", 0);     
	         response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");    
	         response.addHeader("Cache-Control", "post-check=0, pre-check=0");    
	         response.setHeader("Pragma", "no-cache");    
	         response.setContentType("image/png");    
	         
	         out = response.getOutputStream();
	 
	         byte[] buffer = new byte[inputStream.available()];
	         inputStream.read(buffer);  
	         inputStream.close();  
	         out.write(buffer);
	         out.flush(); 
	        
	     }catch (Exception e) {
			e.printStackTrace();
		 }
		 finally{    
	         if(out!=null){
	        	 try {
					out.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	         }
	    
	     }
		 return null;
	
	}
	
	
	//获取流程定义各个节点信息，如果传入流程id，查询当前活动节点信息
	public List getProcessDefinitionActivity(String processDefinitionId,String processInstanceId){
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		ProcessDefinitionEntity  pde=(ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		
		//获取流程定义全部节点
		List<ActivityImpl> activitiList =pde.getActivities();
		
		//因图片生成时对图片进行了剪切，去掉了X，Y轴空白，所以需要计算出最小x，y
		ActivityImpl ai=activitiList.get(0);
		int minX=ai.getX();
		int minY=ai.getY();
		
		for(ActivityImpl a:activitiList){
			int x=a.getX();
			int y=a.getY();
			if(x<minX){
				minX=x;
			}
			if(y<minY){
				minY=y;
			}
		}
		//minX-=5;
		//minY-=5;
		List<String> activityIds=null;
		if(StringUtils.isNotEmpty(processInstanceId)){
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if(processInstance!=null){
				//获取当前活动节点id集合
				activityIds =runtimeService.getActiveActivityIds(processInstanceId);
			}
		}
				
		for(ActivityImpl a:activitiList){
			
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("id", a.getId());
			m.put("name", a.getProperty("name"));
			m.put("documentation", a.getProperty("documentation"));
			TaskDefinition td=(TaskDefinition)a.getProperty("taskDefinition");
			if(td!=null){
				Expression assigneeExpression=td.getAssigneeExpression();
				
				if(assigneeExpression!=null){
					
					String assignee=assigneeExpression.getExpressionText();
					if("${applyUserId}".equals(assignee)){
						m.put("assignee","流程发起人");
					}
					else if("${flowUtil.getDeptHeadId(applyUserId)}".equals(assignee)){
						m.put("assignee","流程发起人的部门经理");
					}
					else if("${assigneeUserId}".equals(assignee)){
						m.put("assignee","自动分配");
					}
					else{
						m.put("assignee",MyCache.getInstance().getTrueName(assignee));
					}
					
				}else{
					Set<Expression> candidateUserIdExpressions=td.getCandidateUserIdExpressions();
					Set<Expression> candidateGroupIdExpressions=td.getCandidateGroupIdExpressions();
					
					if(!candidateUserIdExpressions.isEmpty()){
						if(candidateUserIdExpressions.size()==1&&candidateUserIdExpressions.toString().startsWith("[${")){
							
							m.put("userNames","自动分配");
							
						}else{
							
							m.put("userNames", MyCache.getInstance().getTrueName(StringUtils.join(candidateUserIdExpressions,",")));
						}
					}
					if(!candidateGroupIdExpressions.isEmpty()){
						if(candidateGroupIdExpressions.size()==1&&candidateGroupIdExpressions.toString().startsWith("[${")){
							
							m.put("roleNames","自动分配");
							
						}else{
							
							m.put("roleNames", MyCache.getInstance().getRoleName(StringUtils.join(candidateGroupIdExpressions,",")));
						}
					}
				
					
				}
			}
			m.put("x",a.getX()-minX);
			m.put("y",a.getY()-minY);
			m.put("width",a.getWidth()+10);
			m.put("height", a.getHeight()+10);
			
			if(activityIds!=null&&activityIds.contains(a.getId())){
				m.put("current",true);
			}else{
				m.put("current",false);
			}
			list.add(m);
			
		}
		
		return list;
		
	}

	
	//删除流程部署
	public boolean deleteProcessDefinition(String[] ids){
		
		for(String id:ids){
			ProcessDefinition pd=repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult();
			if(pd!=null){
				WfWorkflow workflow=(WfWorkflow)dao.findOne("from WfWorkflow where flowId=?", pd.getKey());
				workflow.setVersionNum(workflow.getVersionNum()-1);
				if(workflow.getVersionNum()<=0){
					workflow.setVersionNum(0);
					workflow.setReleaseState((short)0);
				}
				dao.update(workflow);
				repositoryService.deleteDeployment(id, true);//删除流程部署,级联删除历史记录和流程实例
				
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
		}
		return true;
	}
	
	
	//查看流程定义信息，根据流程定义id
	public ProcessDefinition getProcessDefinition(String processDefinitionId){
		
		return repositoryService.getProcessDefinition(processDefinitionId);

	}
	
	//获取流程开始的表单
	public String getStartFormHtml(String processDefinitionId){
		
		String id =formService.getStartFormKey(processDefinitionId);
		if(id==null){
			return null;
		}
		WfForm f=dao.get(WfForm.class, id);
		if(f==null){
			return null;
			
		}
		return f.getFormHtml();
		
	}
	
	
	
	//获取流程定义各个节点信息，
	public List getProcessDefinitionUserTask(String processDefinitionId){
		
		ProcessDefinitionEntity  pde=(ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		
		if(pde==null){
			
			return null;
		}
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		//获取流程定义全部节点
		List<ActivityImpl> activitiList =pde.getActivities();
		
		for(ActivityImpl a:activitiList){
			
			if("userTask".equals(a.getProperty("type"))){
				//用户任务
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("id", a.getId());
				m.put("name", a.getProperty("name"));
				m.put("documentation", a.getProperty("documentation"));
				
				TaskDefinition td=(TaskDefinition)a.getProperty("taskDefinition");
				if(td!=null){
					
					m.put("formKey",td.getTaskFormHandler().getFormKey());
					
					Expression assigneeExpression=td.getAssigneeExpression();
					
					if(assigneeExpression!=null){
						
						String assignee=assigneeExpression.getExpressionText();
						
						if("${applyUserId}".equals(assignee)){
							m.put("assignee","流程发起人");
						}
						else if("${flowUtil.getDeptHeadId(applyUserId)}".equals(assignee)){
							m.put("assignee","流程发起人的部门经理");
						}
						else if("${assigneeUserId}".equals(assignee)){
							m.put("assignee","自动分配");
						}
						else{
							m.put("assignee",MyCache.getInstance().getTrueName(assignee));
						}
						
					}else{
						Set<Expression> candidateUserIdExpressions=td.getCandidateUserIdExpressions();
						Set<Expression> candidateGroupIdExpressions=td.getCandidateGroupIdExpressions();
						
						List<String> roleIds=new ArrayList<String>();
						
						if(candidateUserIdExpressions.isEmpty()&&candidateGroupIdExpressions.isEmpty()){
							m.put("no", "true");
						}else{
							if(!candidateUserIdExpressions.isEmpty()){
								if(candidateUserIdExpressions.size()==1&&candidateUserIdExpressions.toString().startsWith("[${")){
									
									m.put("userNames","自动分配");
									
								}else{
									
									m.put("userIds", StringUtils.join(candidateUserIdExpressions,","));
								}
							}
							if(!candidateGroupIdExpressions.isEmpty()){
								if(candidateGroupIdExpressions.size()==1&&candidateGroupIdExpressions.toString().startsWith("[${")){
									
									m.put("roleNames","自动分配");
									
								}else{
									for(Expression e:candidateGroupIdExpressions){
										roleIds.add(e.getExpressionText());
									}
									m.put("roleIdsLst",roleIds);
									
								}
							}
						}
						
					}
				}
				
				list.add(m);
			}
			
		}
		
		return list;
		
	}

	
	
	
	
	
	
	
	
	
	
}
