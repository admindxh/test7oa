/**  
 * @Project: jxoa
 * @Title: ICompanyFilesService.java
 * @Package com.oa.manager.files.service
 * @date 2013-4-23 上午10:41:50
 * @Copyright: 2013 
 */
package com.oa.manager.files.service;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.files.bean.WdCompanyFiles;

/**
 * 
 * 类名：ICompanyFilesService
 * 功能：公司文档 业务层
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-23 上午10:41:50
 *
 */
public interface ICompanyFilesService extends IBaseService{
	
	/**
	 * 公司文档条件查询
	 * @param param
	 * @param f
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectFiles(PageParam param,WdCompanyFiles f,Date startDate,Date endDate);
	

	/**
	 * 新建文件
	 * @param f
	 * @param request
	 * @return
	 */
	public String addFile(WdCompanyFiles f,String savePath,MultipartFile file);

	/**
	 * 修改文件
	 * @param f
	 * @param savePath
	 * @param file
	 * @return
	 */
	public String updateFile(WdCompanyFiles f,String savePath,MultipartFile file);

	/**
	 * 删除文件
	 * @param id
	 * @param savePath
	 * @return
	 */
	public String deleteFile(String id,String savePath);
	/**
	 * 判断当前用户对某分类文档是否有添加 修改 删除 的操作权限
	 * @param typeId	分类id
	 * @param power	  1:添加,2:修改,3:删除
	 * @return
	 */
	public boolean checkPower(String typeId,int power);
	/**
	 * 批量移动文件
	 * @param ids
	 * @param wdSuperId
	 * @return
	 */
	public String move(String[] ids,String wdSuperId);
	
	
	
}
