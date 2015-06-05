/**  
 * @Project: jxoa
 * @Title: ProcessEndListener.java
 * @Package com.oa.manager.workFlow.listener.execution
 * @date 2013-8-16 下午2:04:12
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.listener.execution;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;

import com.oa.commons.base.IBaseDao;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.PerMsg;
import com.oa.manager.workFlow.bean.WfWork;



/**
 * 
 * 类名：ProcessEndListener
 * 功能：流程实例结束监听器
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-8-16 下午2:04:12
 *
 */
public class ProcessEndListener implements ExecutionListener{

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		String applyUserId=(String)execution.getVariable("applyUserId");//获取流程发起人id
		
		System.out.println("==流程结束=="+applyUserId);
		
		IBaseDao dao=ServletUtil.getApplicationContext().getBean(IBaseDao.class);
		//发送流程完成消息提醒
		PerMsg msg=new PerMsg();				
		msg.setMsgTime(DateUtil.currentTimestamp());
		msg.setMsgType((short)60);
		msg.setReadState((short)0);
		msg.setTableId(execution.getProcessInstanceId());
		msg.setUserId(applyUserId);
		dao.save(msg);
		//更新工作完成时间
		WfWork wf=dao.get(WfWork.class, execution.getProcessInstanceId());
		if(wf!=null){
			wf.setEndTime(DateUtil.currentTimestamp());
			wf.setWorkStatus((short)1);
			dao.update(wf);
		}
		//清空流程实例所有历史流程变量,任务变量
		HistoryService historyService=ServletUtil.getApplicationContext().getBean(HistoryService.class);
		
		List<HistoricVariableInstance> hvis=historyService.createHistoricVariableInstanceQuery().processInstanceId(execution.getProcessInstanceId()).list();
		
		for(HistoricVariableInstance h:hvis){
			((HistoricVariableInstanceEntity)h).delete();
		}
		
	}

}
