/**  
 * @Project: jxoa
 * @Title: IEmailService.java
 * @Package com.oa.manager.personalOffice.service
 * @date 2013-5-28 下午03:46:45
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.EmOutbox;
import com.oa.manager.personalOffice.bean.Email;

/**
 * 
 * 类名：IEmailService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-28 下午03:46:45
 *
 */
public interface IEmailService  extends IBaseService{
	
	/**
	 * 发送邮件
	 * @param eo
	 * @param userIds
	 * @param savePath
	 * @param request
	 * @return
	 */
	public boolean addEmail(EmOutbox eo,String userIds ,String type,String  id,String savePath,HttpServletRequest request,FileList files,String[] oldIds);
	/**
	 * 加载收件箱
	 * @param param
	 * @param e
	 * @return
	 */
	public DataGrid selectEmInbox(PageParam param,Email e,String status);
	
	/**
	 * 根据ID查询一条记录
	 * @param id
	 * @return
	 */
	public Map selectEmail(String id);
	/**
	 * 根据ID查询一条收件箱记录
	 * @param id
	 * @return
	 */
	public Map findIn(String id);
	/**
	 * 修改邮件状态
	 * @param id
	 * @return
	 */
	public boolean updateStatus(String id);
	/**
	 * 加载发件箱
	 * @param param
	 * @param e
	 * @return
	 */
	public DataGrid selectEmOutbox(PageParam param,Email e);
	
	/**
	 * 查看邮件
	 * @param id
	 * @return
	 */
	public Map selectOutbox(String id);
	
	/**
	 * 保存草稿箱
	 * @param eo
	 * @param userIds
	 * @param savePath
	 * @param request
	 * @return
	 */
	public boolean saveEmail(EmOutbox eo,String userIds,String savePath,HttpServletRequest request,FileList files,String[] oldIds,String type,String getId);
	/**
	 * 修改草稿箱
	 * @param eo
	 * @param userIds
	 * @param savePath
	 * @param request
	 * @return
	 */
	public boolean update(EmOutbox eo,String userIds,String savePath,HttpServletRequest request,FileList files,String[] oldIds);
	
	/**
	 * 加载草稿箱
	 * @param param
	 * @param e
	 * @return
	 */
	public DataGrid selectEmDraftbox(PageParam param,Email e);
	
	/**
	 * 根据ID查询草稿箱邮件
	 * @param id
	 * @return
	 */
	public Map findDraftOne(String id);
	
	/**
	 * 从草稿箱发送邮件
	 * @param eo
	 * @param userIds
	 * @param savePath
	 * @param request
	 * @return
	 */
	public boolean addDraft(EmOutbox eo,String userIds,String savePath,HttpServletRequest request,FileList files,String edId,String eoId,String[] oldIds);
	/**
	 * 批量删除收件箱
	 * @param ids
	 * @return
	 */
	public boolean deleteEmail(String[] ids,int type,String savePath);
	
	/**
	 * 根据ID删除附件
	 * @param id
	 * @return
	 */
	public boolean deleteFile(String id,String savePath);
	/**
	 * 未读邮件数量
	 */
	public Long selectNumber();
}
