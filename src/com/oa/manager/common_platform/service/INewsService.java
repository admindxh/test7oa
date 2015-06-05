/**  
 * @Project: jxoa
 * @Title: INewsService.java
 * @Package com.oa.manager.common_platform.service
 * @date 2013-5-19 上午11:14:11
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service;



import java.util.List;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.Newscomment;
import com.oa.manager.common_platform.bean.Newsmanagement;

/**
 * 
 * 类名：INewsService
 * 功能：新闻管理
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-5-19 上午11:14:11
 *
 */
public interface INewsService extends IBaseService
{
	@SuppressWarnings("rawtypes")
	/**
	 * 查询新闻管理里的新闻 
	 * @param param
	 * @param ng
	 * @return
	 */
	public DataGrid newsload(PageParam param,Newsmanagement ng);
	
	/**
	 * 删除新闻
	 * @param ids
	 * @return
	 */
	public boolean deletenews(String[] ids);
	@SuppressWarnings("rawtypes")
	/**
	 * 查看评论论内容
	 * @param id
	 * @param param
	 * @return
	 */
	public List selectNewscomment(String id,PageParam param);
	
	/**
	 *  查看评论总数
	 * @param id
	 * @return
	 */
	public Long selectNewscommentCount(String id);
	/**
	 * 删除评论
	 * @param id
	 * @param newsid
	 * @return
	 */
	public String deletecontent(String id,String newsid);
	/**
	 * 评论总数
	 * @param id
	 * @return
	 */
	public Long contentCount(String id);
	/**
	 * 修改新闻状态成终止
	 * @param ids
	 * @return
	 */
	public boolean updatestop(String[] ids);
	/**
	 * 修改成生成
	 * @param ids
	 * @return
	 */
	public boolean updategenerate(String[] ids);
	/**
	 * 修改点击数
	 * @param id
	 * @return
	 */
	public Newsmanagement updateSelectOne(String id);
	/**
	 * 保存评论和修改评论数
	 * @param nc
	 * @return
	 */
	public String updatecontent(Newscomment nc);
	/**
	 * 修改新闻
	 * @param ng
	 * @return
	 */
	public String updatenews(Newsmanagement ng);
	
}