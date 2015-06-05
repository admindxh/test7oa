/**  
 * @Title: IWorkService.java
 * @date 2013-8-20 上午10:51:32
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.repository.ProcessDefinition;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfWork;

/**
 * 
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public interface IWorkService extends IBaseService{
	/**
	 * 根据key获取最新版本流程定义信息
	 * @param key
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String key);
	
	
	//新创建工作 发起流程
	public Map saveWork(String id,String key,String workTitle,String savePath,FileList files);
	


	//查询流程明细
	@SuppressWarnings({  "rawtypes" })
	public Map selectProcessDetails(String processInstanceId);
	
	/**
	 * 查询正在运行中的流程实例
	 * @param qm
	 * @return
	 */
	public DataGrid queryRunProcessInstance(PageParam param,WfWork wf,Date startedAfter,Date startedBefore);

	/**
	 * 条件分页查询工作
	 * @param param
	 * @param wf
	 * @param startedAfter
	 * @param startedBefore
	 * @param finishedAfter
	 * @param finishedBefore
	 * @return
	 */
	public DataGrid selectWork(PageParam param,WfWork wf,Date startedAfter,Date startedBefore,Date finishedAfter,Date finishedBefore);
	
	
	
	/**
	 * 删除流程实例
	 * @param id	流程实例id
	 * @param deleteReason	删除原因
	 * @return
	 */
	public boolean deleteProcessInstance(String id,String deleteReason);
	
	/**
	 * 批量删除历史流程记录，只能删除已经完成流程
	 * @param ids
	 * @return
	 */
	public boolean deleteHistoricProcessInstance(String[] ids);
	

	
}
