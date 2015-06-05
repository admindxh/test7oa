/**  
 * @Project: jxoa
 * @Title: IPersonalFilesService.java
 * @Package com.oa.manager.files.service
 * @date 2013-4-18 下午5:15:16
 * @Copyright: 2013 
 */
package com.oa.manager.files.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.files.bean.WdPersonalFiles;

/**
 * 
 * 类名：IPersonalFilesService
 * 功能：个人文档 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-18 下午5:15:16
 *
 */
public interface IPersonalFilesService extends IBaseService{
	
	/**
	 * 个人文档条件查询
	 * @param param
	 * @param f
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectFiles(PageParam param,WdPersonalFiles f,Date startDate,Date endDate);

	/**
	 * 新建文档目录
	 * @param f
	 * @return
	 */
	public String addNode(WdPersonalFiles f);
	/**
	 * 新建文件
	 * @param f
	 * @param request
	 * @return
	 */
	public String addFile(WdPersonalFiles f,String savePath, MultipartFile file);
	/**
	 * 修改文件夹
	 * @param f
	 * @return
	 */
	public String updateNode(WdPersonalFiles f);
	/**
	 * 修改文件
	 * @param f
	 * @return
	 */
	public String updateFile(WdPersonalFiles f,String savePath, MultipartFile file);
	
	/**
	 * 删除文件
	 * @param ids
	 * @param savePath
	 * @return
	 */
	public boolean deleteFile(String[] ids,String savePath);
	/**
	 * 删除文件夹
	 * @param id
	 * @return
	 */
	public String deleteNode(String id);
	
	
	/**
	 * 查询某出所有目录信息，用于tree显示
	 * @param userId 用户id
	 * @return
	 */
	public List selectAllNodesByUserId(String userId);
	
	
	
}
