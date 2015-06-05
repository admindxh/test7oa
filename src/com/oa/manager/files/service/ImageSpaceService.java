/**  
 * @Title PersonalGalleryService.java
 * @date 2013-12-25 下午4:39:20
 * @Copyright: 2013 
 */
package com.oa.manager.files.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.files.bean.ImageSpace;
import com.oa.manager.files.bean.ImageSpaceImg;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public interface ImageSpaceService extends IBaseService{
	/**
	 * 分页条件查询图片
	 * @param param
	 * @param m
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectImg(PageParam param,ImageSpaceImg m,Date startDate,Date endDate);
	/**
	 * 分页条件查询图片
	 * @param param
	 * @param m
	 * @return
	 */
	public List<ImageSpaceImg> selectImg(PageParam param,ImageSpaceImg m);
	/**
	 * 查询所有图片总数
	 * @param m
	 * @return
	 */
	public Long selectImgCount(ImageSpaceImg m);
	/**
	 * 上传保存图片
	 * @param m
	 * @param savePath
	 * @param file
	 * @return
	 */
	public boolean addImg(ImageSpaceImg m,String savePath,MultipartFile file);
	/**
	 * 修改图片内容
	 * @param m
	 * @return
	 */
	public String updateImg(ImageSpaceImg m);
	/**
	 * 删除图片
	 * @param ids
	 * @return
	 */
	public boolean deleteImg(String[] ids);
	/**
	 * 分页查询类型
	 * @param param
	 * @param m
	 * @return
	 */
	public DataGrid selectType(PageParam param,ImageSpace m);
	/**
	 * 添加图片类型
	 * @param m
	 * @return
	 */
	public boolean addType(ImageSpace m);
	/**
	 * 修改图片类型
	 * @param m
	 * @return
	 */
	public String updateType(ImageSpace m);
	/**
	 * 删除图片类型
	 * @param ids
	 * @return
	 */
	public boolean deleteTypes(String[] ids);
	
	/**
	 * 根据用户id，查询出用户图片 类型
	 * 
	 * @param id 用户id
	 * @return	id,name(相册名称)
	 */
	public List<Map<String,Object>> selectTypeList(String id);
	/**
	 * 创建系统默认目录类型，如果存在则不需要重新创建
	 * @param userId 用户id
	 * @param type   类型：1：word图片转存
	 * @param name 
	 * @param remark
	 * @return
	 */
	public String addDefaultType(String userId,short type,String name,String remark);
	
}
