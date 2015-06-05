/**  
 * @Project: jxoa
 * @Title: INoticeService.java
 * @Package com.oa.manager.administrativeoffice.service
 * @date 2013-4-12 下午4:00:21
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.coordination.bean.XtNotice;
import com.oa.manager.coordination.bean.XtNoticeContent;

/**
 * 
 * 类名：INoticeService
 * 功能：通知管理 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-12 下午4:00:21
 *
 */
public interface INoticeService extends IBaseService{
	
	/**
	 * 添加通知
	 * @param not
	 * @param nc
	 * @param deptIds
	 * @param roleIds
	 * @param userIds
	 * @param request
	 * @param savePath
	 * @return
	 */
	public boolean addNotice(XtNotice not,XtNoticeContent nc,Integer isSendMsg,String[] deptIds,String[] userIds,String savePath,FileList files);
	
	/**
	 * 查询我能查看的通知
	 * @param param
	 * @param not
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectMyNotice(PageParam param,XtNotice not,Date startDate,Date endDate);
	
	
	
	/**
	 * 通知管理查询，查询我发布的通知
	 * @param param
	 * @param not
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectNotice(PageParam param,XtNotice not,Date startDate,Date endDate);
	
	/**
	 * 根据id查询通知详情 用于修改页面
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map findNoticeById(String id);
	/**
	 * 根据id查询通知信息 用于详情页面
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map updateShowOneById(String id);
	
	/**
	 * 删除通知附件
	 * @param id
	 * @param savePath
	 * @return
	 */
	public String deleteXtNoticeFiles(String id,String savePath);
	/**
	 * 修改通知
	 * @param not
	 * @param nc
	 * @param deptIds
	 * @param userIds
	 * @param savePath
	 * @param request
	 * @return
	 */
	public String updateNotice(XtNotice not,XtNoticeContent nc,Integer isSendMsg,String[] deptIds,String[] userIds,String savePath,FileList files);
	/**
	 * 删除通知，其他附表数据库自动删除
	 * @param ids
	 * @param savePath
	 * @return
	 */
	public boolean deleteXtNotice(String[] ids,String savePath);
	
//***********************************************安卓**********************************************
	/**
	 * 分页查询个人通知
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> queryNoticByPerson(PageParam param);
	/**
	 * 查询个人通知总数
	 * @return
	 */
	public Long totleNoticByPerson();
	/**
	 * 根据通知id查询通知详情
	 * @param id 通知id
	 * @return
	 */
	public Map<String,Object> updateNoticById(String id);
}
