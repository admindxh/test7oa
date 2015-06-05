/**  
 * @Title WorkPlanService.java
 * @date 2013-11-28 上午10:23:20
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service;

import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.coordination.bean.XtWorkPlan;
import com.oa.manager.coordination.bean.XtWorkPlanComment;
import com.oa.manager.coordination.bean.XtWorkPlanTask;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public interface WorkPlanService extends IBaseService{

	/**
	 * 工作计划查询
	 * @param param
	 * @return
	 */
	public DataGrid selectWorkPlan(PageParam param);
	/**
	 * 添加工作计划
	 * @param wp
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param headIds
	 * @param deptIds
	 * @param userIds
	 * @param savePath
	 * @param files
	 * @return
	 */
	public boolean addWorkPlan(XtWorkPlan wp,Integer isSendMsg,String[] fzIds,String[] cyIds,String[] headIds,String[] deptIds,String[] userIds,String savePath,FileList files);
	/**
	 * 查询工作详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> findWorkPlanById(String id);
	/**
	 * 修改工作计划
	 * @param wp
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param headIds
	 * @param deptIds
	 * @param userIds
	 * @param uuids
	 * @param savePath
	 * @param files
	 * @return
	 */
	public String updateWorkPlan(XtWorkPlan wp,Integer isSendMsg,String[] fzIds,String[] cyIds,String[] headIds,String[] deptIds,String[] userIds,String[] uuids,String savePath,FileList files);
	/**
	 * 删除工作计划
	 * @param ids
	 * @param savePath
	 * @return
	 */
	public boolean deleteWorkPlan(String[] ids,String savePath);
	/**
	 * 个人工作计划查询
	 * @param param
	 * @return
	 */
	public DataGrid selectMyWorkPlan(PageParam param);
	/**
	 * 查询计划任务
	 * @param param
	 * @return
	 */
	public DataGrid selectWorkPlanTask(PageParam param,String id,String userId);
	/**
	 * 添加计划任务
	 * @param t
	 * @param savePath
	 * @param files
	 * @return
	 */
	public boolean addWorkPlanTask(XtWorkPlanTask t,String savePath,FileList files);
	/**
	 * 修改计划任务
	 * @param t
	 * @param savePath
	 * @param uuids
	 * @param files
	 * @return
	 */
	public String updateWorkPlanTask(XtWorkPlanTask t,String savePath,String[] uuids,FileList files);
	/**
	 * 删除工作计划
	 * @param id
	 * @param savePath
	 * @return
	 */
	public String deleteWorkPlanTask(String id,String savePath);
	/**
	 * 查询计划批注
	 * @param param
	 * @param id
	 * @return
	 */
	public DataGrid selectWorkPlanComment(PageParam param,String id);
	/**
	 * 修改批注
	 * @param c
	 * @return
	 */
	public String updateWorkPlanComment(XtWorkPlanComment c);
	/**
	 * 删除批注
	 * @param id
	 * @return
	 */
	public String deleteWorkPlanComment(String id);
		
	
}
