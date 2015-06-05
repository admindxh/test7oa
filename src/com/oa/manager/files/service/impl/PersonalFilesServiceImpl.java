/**  
 * @Project: jxoa
 * @Title: PersonalFilesServiceImpl.java
 * @Package com.oa.manager.files.service.impl
 * @date 2013-4-18 下午5:15:47
 * @Copyright: 2013 
 */
package com.oa.manager.files.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.WdPersonalFiles;
import com.oa.manager.files.service.IPersonalFilesService;

/**
 * 
 * 类名：PersonalFilesServiceImpl
 * 功能：个人文档 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-18 下午5:15:47
 *
 */
@Service
public class PersonalFilesServiceImpl extends BaseServiceImpl implements IPersonalFilesService{
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectFiles(PageParam param,WdPersonalFiles f,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WdPersonalFiles f where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(f.getWdName())){
			sb.append(" and f.wdName like ? ");
			list.add("%"+f.getWdName()+"%");
		}
		if(StringUtils.isNotBlank(f.getWdUserId())){
			sb.append(" and f.wdUserId = ? ");
			list.add(f.getWdUserId());	
		}
		if(StringUtils.isNotBlank(f.getWdSuperId())){
			sb.append(" and f.wdSuperId = ? ");
			list.add(f.getWdSuperId());	
		}
		if(f.getWdType()!=null&&f.getWdType()!=0){
			sb.append(" and f.wdType = ? ");
			list.add(f.getWdType());	
		}
		if(startDate!=null){
			sb.append(" and f.wdCreateTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and f.wdCreateTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by f.wdCreateTime desc");
		}
		data.setRows(dao.findPage(sb.toString(),param.getPage(),param.getRows(),list));
		return data;
	}
	
	
	public String addNode(WdPersonalFiles f){
		
		Object obj=dao.findOne("from WdPersonalFiles where wdUserId=? and wdSuperId=? and wdName=? and wdType=?",ServletUtil.getMember().getId(),f.getWdSuperId(),f.getWdName(),(short)0);
		
		if(obj==null){
			//赋初始值
			Member me=ServletUtil.getMember();
			f.setWdCreateTime(DateUtil.currentTimestamp());
			f.setWdType((short)0);//目录
			f.setWdUserId(me.getId());
			return dao.save(f)? MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
		}else{
			return "msg.filenode.unique";//文件夹名称重复
		}
	}
	
	public String addFile(WdPersonalFiles f,String savePath, MultipartFile file){
		try {
			String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
			String ext=FileUtils.getFileExt(file.getOriginalFilename());//获取文件后缀
			//保存文件
			File newFile = new File(savePath+"/"+uuid+"."+ext); 
			file.transferTo(newFile);
			f.setWdNewName(uuid);
			f.setWdSize((int)file.getSize());
			f.setWdExt(ext);
			//赋初始值
			Member me=ServletUtil.getMember();
			f.setWdUserId(me.getId());
			f.setWdCreateTime(DateUtil.currentTimestamp());
			f.setWdType((short)1);
			return dao.save(f)? MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsgConfig.MSG_KEY_FAIL;
		
		
	}
	public String updateNode(WdPersonalFiles f){
		WdPersonalFiles old=dao.get(WdPersonalFiles.class, f.getId());
		if(old==null)return MsgConfig.MSG_KEY_NODATA;
		if(!ServletUtil.getMember().getId().equals(old.getWdUserId()))return MsgConfig.MSG_KEY_FAIL;
		if(old.getWdType()==0){
			//修改目录
			Object obj=dao.findOne("from WdPersonalFiles where wdUserId=? and wdSuperId=? and wdName=? and wdType=? and id!=?",ServletUtil.getMember().getId(),f.getWdSuperId(),f.getWdName(),(short)0,f.getId());
			if(obj!=null){
				
				return "msg.filenode.unique";//文件夹名称重复
			}
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		old.setWdName(f.getWdName());
		old.setWdDesc(f.getWdDesc());
		if(!f.getId().equals(f.getWdSuperId())){
			old.setWdSuperId(f.getWdSuperId());
		}
		return MsgConfig.MSG_KEY_SUCCESS;
		
		
	}
	
	public String updateFile(WdPersonalFiles f,String savePath,MultipartFile file){
		WdPersonalFiles old=dao.get(WdPersonalFiles.class, f.getId());
		if(old==null)return MsgConfig.MSG_KEY_NODATA;
		if(!ServletUtil.getMember().getId().equals(old.getWdUserId()))return MsgConfig.MSG_KEY_FAIL;
		if(old.getWdType()==1){
			
			if(!file.isEmpty()){
				try {
					//用户上传了文件，覆盖原文件
					String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
					String ext=FileUtils.getFileExt(file.getOriginalFilename());//获取文件后缀
					//保存文件
					File newFile = new File(savePath+"/"+uuid+"."+ext); 
					file.transferTo(newFile);
					//删除旧文件
					File oldfile = new File(savePath+"/"+old.getWdNewName()+"."+old.getWdExt()); 
					oldfile.delete();

					old.setWdNewName(uuid);
					old.setWdSize((int)file.getSize());
					old.setWdExt(ext);
					
				} catch (Exception e) {
					e.printStackTrace();
					return MsgConfig.MSG_KEY_FAIL;
				}
			}
			old.setWdName(f.getWdName());
			old.setWdDesc(f.getWdDesc());
			if(!f.getId().equals(f.getWdSuperId())){
				old.setWdSuperId(f.getWdSuperId());
			}
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		
		
		
	}
	
	public boolean deleteFile(String[] ids,String savePath){
		String userId=ServletUtil.getMember().getId();
		for(String id:ids){
			WdPersonalFiles f=dao.get(WdPersonalFiles.class, id);
			if(f!=null&&userId.equals(f.getWdUserId())&&f.getWdType()==1){
				//如果是文件则删除文件 
				File file = new File(savePath+"/"+f.getWdNewName()+"."+f.getWdExt()); 
				file.delete();
				dao.delete(f);
			}
		}
		return true;
	}
	public String deleteNode(String id){
		String userId=ServletUtil.getMember().getId();
		Object obj=dao.findOne("from WdPersonalFiles where wdSuperId=? ",id);
		if(obj==null){
			return dao.delete("delete WdPersonalFiles where id=? and wdUserId=? ", id,userId)? MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		}else{
			return "msg.fielnode.notempty";//文件夹不是空，无法删除
		}
	}
	
	
	//查询某用户所有目录
	public List selectAllNodesByUserId(String userId){
		
		return dao.find("select new Map(f.id as id,f.wdName as name, f.wdSuperId as pId) from WdPersonalFiles f where wdUserId=? and  wdType=0 ",userId);
	
	}

	
	
}
