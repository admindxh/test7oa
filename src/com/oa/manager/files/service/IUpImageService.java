/**  
 * @Project: jtoa
 * @Title: IUpImageService.java
 * @Package com.oa.manager.files.service
 * @date 2014-1-20 上午9:03:31
 * @Copyright: 2014 
 */
package com.oa.manager.files.service;

import java.util.Date;
import java.util.List;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.files.bean.XtUpImage;
import com.oa.manager.files.bean.XtUpImageFiles;

/**
 * 
 * 类名：IUpImageService
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2014-1-20 上午9:03:31
 *
 */
public interface IUpImageService extends IBaseService {
	
	
	/**
	 * 查询上传图片
	 * @param param
	 * @param m
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectImg(PageParam param,XtUpImage m,Date startDate,Date endDate);
	/**
	 *删除图片
	 *@param ids 
	 *@param SAVEPATH  图片保存路径文件夹
	 */
	public boolean delete_img(String [] ids,String SAVEPATH);
	/**
	 * 根据图片对象查询图片详情
	 * @param id
	 * @return
	 */
	public List<XtUpImageFiles> get_imgfilesList(String id);
	
	
	
	/**
	 * 上传图片----用于客户端
	 * @param title 标题
	 * @param content 内容
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param address 地址
	 * @param files 图片
	 * @return
	 */
	public boolean addClicentImg(String SAVEPATH,String fileExt,String title,String content, Double longitude,
			Double latitude, String address,String... files);
	}
