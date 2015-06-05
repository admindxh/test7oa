/**  
 * @Title PersonalGalleryImpl.java
 * @date 2013-12-25 下午4:39:59
 * @Copyright: 2013 
 */
package com.oa.manager.files.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.ImageSpace;
import com.oa.manager.files.bean.ImageSpaceImg;
import com.oa.manager.files.service.ImageSpaceService;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
@Service
public class ImageSpaceImpl extends BaseServiceImpl implements ImageSpaceService{

	
	public DataGrid selectImg(PageParam param,ImageSpaceImg m,Date startDate,Date endDate){
		
		DataGrid data=new DataGrid();
		
		StringBuffer hql=new StringBuffer("from ImageSpaceImg m where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		//查询条件
		if(StringUtils.isNotBlank(m.getUserId())){
			hql.append(" and m.userId = ? ");
			list.add(m.getUserId());
		}
		if(StringUtils.isNotBlank(m.getType())){
			hql.append(" and m.type = ? ");
			list.add(m.getType());
		}
		if(StringUtils.isNotBlank(m.getName())){
			hql.append(" and m.name like ? ");
			list.add("%"+m.getName()+"%");
		}
		if(startDate!=null){
			hql.append(" and m.createTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			hql.append(" and m.createTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+hql,list));
		if(param.getSort()!=null){
			param.appendOrderBy(hql);//排序
		}else{
			hql.append(" order by createTime desc ");
		}
		
		
		data.setRows(dao.findPage(hql.toString(),param.getPage(),param.getRows(),list));
		
		return data;
	}
	
	public List<ImageSpaceImg> selectImg(PageParam param,ImageSpaceImg m){
		
		StringBuffer hql=new StringBuffer("from ImageSpaceImg m where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		//查询条件
		if(StringUtils.isNotBlank(m.getUserId())){
			hql.append(" and m.userId = ? ");
			list.add(m.getUserId());
		}
		if(StringUtils.isNotBlank(m.getType())){
			hql.append(" and m.type = ? ");
			list.add(m.getType());
		}
		if(StringUtils.isNotBlank(m.getName())){
			hql.append(" and m.name like ? ");
			list.add("%"+m.getName()+"%");
		}
		hql.append(" order by createTime desc ");
		return dao.findPage(hql.toString(),param.getPage(),param.getRows(),list);
		
		
	}
	public Long selectImgCount(ImageSpaceImg m){
		DataGrid data=new DataGrid();
		
		StringBuffer hql=new StringBuffer("select count(*)from ImageSpaceImg m where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		//查询条件
		if(StringUtils.isNotBlank(m.getUserId())){
			hql.append(" and m.userId = ? ");
			list.add(m.getUserId());
		}
		if(StringUtils.isNotBlank(m.getType())){
			hql.append(" and m.type = ? ");
			list.add(m.getType());
		}
		
		return (Long)dao.findUniqueOne(hql.toString(),list);
	}
	
	public boolean addImg(ImageSpaceImg m,String savePath,MultipartFile file){
		try {
			String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
			String ext=FileUtils.getFileExt(file.getOriginalFilename());//获取文件后缀
			if(StringUtils.isBlank(m.getName())){
			
				m.setName(file.getOriginalFilename());//如果用户没有输入文件名，则以上传的图片名为文件名
			}
			m.setCreateTime(DateUtil.currentTimestamp());
			m.setExt(ext);
			m.setSize((int)file.getSize());
			m.setUserId(ServletUtil.getMember().getId());
			m.setSavePath(savePath+"/"+uuid+"."+ext);
			//保存文件
			File newFile = new File(BaseConfig.webPath+savePath+"/"+uuid+"."+ext); 
			file.transferTo(newFile);
			
			return dao.save(m);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyRuntimeException("图片保存失败！");
		}
		
	}
	public String updateImg(ImageSpaceImg m){
		ImageSpaceImg old=dao.get(ImageSpaceImg.class, m.getId());
		if(old==null)return MsgConfig.MSG_KEY_NODATA;
		if(!ServletUtil.getMember().getId().equals(old.getUserId()))return MsgConfig.MSG_KEY_FAIL;
		
		old.setName(m.getName());
		old.setType(m.getType());
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	public boolean deleteImg(String[] ids){
		String userId=ServletUtil.getMember().getId();
		for(String id:ids){
			ImageSpaceImg m=dao.get(ImageSpaceImg.class, id);
			if(m!=null&&userId.equals(m.getUserId())){
				//如果是文件则删除文件 
				File file = new File(BaseConfig.webPath+m.getSavePath()); 
				file.delete();
				dao.delete(m);
			}
		}
		return true;
	}
	
	
	public DataGrid selectType(PageParam param,ImageSpace m){
		
		DataGrid data=new DataGrid();
		
		StringBuffer hql=new StringBuffer("from ImageSpace m where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		//查询条件
		if(StringUtils.isNotBlank(m.getUserId())){
			hql.append(" and m.userId = ? ");
			list.add(m.getUserId());
		}
		if(StringUtils.isNotBlank(m.getName())){
			hql.append(" and m.name like ? ");
			list.add("%"+m.getName()+"%");
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+hql,list));
		
		param.appendOrderBy(hql);//排序
		
		data.setRows(dao.findPage(hql.toString(),param.getPage(),param.getRows(),list));
		
		
		return data;
	}
	
	public boolean addType(ImageSpace m){
		
		Object obj=dao.findOne("from ImageSpace where name=? and userId=?",m.getName(),ServletUtil.getMember().getId());
		if(obj==null){
			m.setUserId(ServletUtil.getMember().getId());
			m.setCreateTime(DateUtil.currentTimestamp());
			m.setType((short)0);//用户创建
			return dao.save(m);
		}else{
			throw new MyRuntimeException("类型名称重复！请重新命名!");
		}
	}
	public String updateType(ImageSpace m){
		Object obj=dao.findOne("from ImageSpace where id!=? and name=? and userId=? ",m.getId(),m.getName(),ServletUtil.getMember().getId());
		if(obj==null){
			ImageSpace old=dao.get(ImageSpace.class, m.getId());
			if(old==null){
				return MsgConfig.MSG_KEY_NODATA;
			}
			if(!old.getUserId().equals(ServletUtil.getMember().getId())){
				throw new MyRuntimeException("修改失败！只能由创建人修改!");
			}
			old.setName(m.getName());
			old.setRemark(m.getRemark());
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			throw new MyRuntimeException("类型名称重复！请重新命名!");
		}
	}
	public boolean deleteTypes(String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			ImageSpace m=dao.get(ImageSpace.class,id);
			if(m!=null){
				c.add(m);
			}
		}
		return dao.deleteAll(c);
	}
	
	public List<Map<String,Object>> selectTypeList(String userId){
		
		return dao.find("select new Map(id as id,name as name)from ImageSpace  where userId=? order by createTime desc ",userId);
	}
	
	public String addDefaultType(String userId,short type,String name,String remark){
		ImageSpace s=(ImageSpace)dao.findOne("from ImageSpace where userId=? and type=? ",userId,type);
		if(s==null){
			//不存在则创建一个
			s=new ImageSpace();
			s.setCreateTime(DateUtil.currentTimestamp());
			s.setName(name);
			s.setRemark(remark);
			s.setType(type);
			s.setUserId(userId);
			dao.save(s);
		}
		return s.getId();
	}
	
}
