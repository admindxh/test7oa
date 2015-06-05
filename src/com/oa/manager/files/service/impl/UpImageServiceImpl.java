/**  
 * @Project: jtoa
 * @Title: UpImageServiceImpl.java
 * @Package com.oa.manager.files.service.impl
 * @date 2014-1-20 上午10:50:42
 * @Copyright: 2014 
 */
package com.oa.manager.files.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.BASE64;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.XtUpImage;
import com.oa.manager.files.bean.XtUpImageFiles;
import com.oa.manager.files.service.IUpImageService;

/**
 * 
 * 类名：UpImageServiceImpl
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2014-1-20 上午10:50:42
 *
 */
@Service
public class UpImageServiceImpl extends BaseServiceImpl implements
		IUpImageService {

	/**
	 * 查询上传图片
	 */
	public DataGrid selectImg(PageParam param,XtUpImage m,Date startDate,Date endDate){
		
		DataGrid data=new DataGrid();
		
		StringBuffer hql=new StringBuffer(" from XtUpImage m where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		//查询条件
				if(StringUtils.isNotBlank(m.getCreateuser())){
					hql.append(" and m.createuser = ? ");
					list.add(m.getCreateuser());
				}
			 
				if(StringUtils.isNotBlank(m.getTitle())){
					hql.append(" and m.title like ? ");
					list.add("%"+m.getTitle()+"%");
				}
				if(startDate!=null){
					hql.append(" and m.createtime >=? ");
					list.add(startDate);	
				}
				if(endDate!=null){
					hql.append(" and m.createtime <=? ");
					list.add(endDate);	
				}
				data.setTotal((Long)dao.findUniqueOne("select count(*)"+hql,list));
				if(param.getSort()!=null){
					param.appendOrderBy(hql);//排序
				}else{
					hql.append(" order by createtime desc ");
				}
				
				
				data.setRows(dao.findPage(hql.toString(),param.getPage(),param.getRows(),list));
				
				return data;
		 
	}
	/**
	 * 删除图片
	 */
	@Override
	public boolean delete_img(String[] ids,String SAVEPATH) {
		
		for(String id: ids){
			XtUpImage img = dao.get(XtUpImage.class, id);
			if(img!=null){
				@SuppressWarnings("unchecked")
				List<XtUpImageFiles> img_List = dao.find("from XtUpImageFiles where imageId = ? ",id);
				if(img_List.size()>0){
					for(XtUpImageFiles imginfo: img_List){
						//先删除服务器图片
						File file = new File(BaseConfig.webPath+SAVEPATH+"/"+imginfo.getFileNewName()+imginfo.getFileExt());
						file.delete();
					}
				}
				//再删除图片对象级联删除图片详情记录
				 dao.delete(img);
			}
		}
		return true;
	}
	
	/**
	 * 图片上传---用于客户端
	 */
	@Override
	public boolean addClicentImg(String SAVEPATH,String fileExt,String title, String content,
			Double longitude, Double latitude, String address, String... files) {
		
		//保存图片对象
	     XtUpImage img = new XtUpImage();
	     img.setAddress(address);
	     img.setContent(content);
	     img.setCreatetime(DateUtil.currentTimestamp());
	     img.setCreateuser(ServletUtil.getMember().getId());
	     img.setLatitude(latitude);
	     img.setLongitude(longitude);
	     img.setModifytime(DateUtil.currentTimestamp());
	     img.setModifyuser(ServletUtil.getMember().getId());
	     img.setTitle(title);
	     img.setUptime(DateUtil.currentDateTimeToString());
	     //图片对象ID
	     @SuppressWarnings("deprecation")
		String id = (String) dao.saveReturnId(img);
		//保存图片
		for (String fileString : files) {
			
			 String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
			 String path = BaseConfig.webPath+SAVEPATH+"/"+uuid+fileExt;
			 File f = new File(path);
		     try {
		    	byte[] s = BASE64.decode(new String(fileString));
				FileOutputStream out = new FileOutputStream(f);
				out.write(s);
				out.close();
				//图片对象详情对象
			     XtUpImageFiles img_info = new XtUpImageFiles();
			     img_info.setFileExt(fileExt);
			     img_info.setFileNewName(uuid);
			     img_info.setImageId(id);  
			     img_info.setFileSize(FileUtils.getHumanReadableFileSize(s.length));
			     dao.save(img_info);
			 } catch (Exception e) { 
				 
				 throw new MyRuntimeException("图片保存失败！");
			 }	
		}
		
		return true;
	}
	/**
	 * 根据图片对象id查询图片详情
	 */
	@Override
	public List<XtUpImageFiles> get_imgfilesList(String id) {
			
		@SuppressWarnings("unchecked")
		List<XtUpImageFiles> list = dao.find("from XtUpImageFiles where imageId = ?", id);
		
		return list;
	}
	

}
