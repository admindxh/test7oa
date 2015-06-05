/**  
 * @Project: jxoa
 * @Title: CompanyFilesServiceImpl.java
 * @Package com.oa.manager.files.service.impl
 * @date 2013-4-23 上午10:42:05
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
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.WdCfTypePowers;
import com.oa.manager.files.bean.WdCompanyFiles;
import com.oa.manager.files.service.ICompanyFilesService;

/**
 * 
 * 类名：CompanyFilesServiceImpl
 * 功能：公司文档 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-23 上午10:42:05
 *
 */
@Service
public class CompanyFilesServiceImpl extends BaseServiceImpl implements ICompanyFilesService{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectFiles(PageParam param,WdCompanyFiles f,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WdCompanyFiles f where 1=1 ");
		List list=new ArrayList();
		//查询条件
		sb.append(" and f.wdSuperId = ? ");
		if(StringUtils.isNotBlank(f.getWdSuperId())){
			list.add(f.getWdSuperId());	
		}else{
			list.add("0");
		}
		if(StringUtils.isNotBlank(f.getWdName())){
			sb.append(" and f.wdName like ? ");
			list.add("%"+f.getWdName()+"%");
		}
		if(StringUtils.isNotBlank(f.getWdUserId())){
			sb.append(" and f.wdUserId = ? ");
			list.add(f.getWdUserId());	
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
		List<WdCompanyFiles> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(WdCompanyFiles r:rows){
			r.setWdUserId(MyCache.getInstance().getTrueName(r.getWdUserId()));
		}
		
		data.setRows(rows);
		return data;
	}
	
	public String addFile(WdCompanyFiles f,String savePath,MultipartFile file){
		
		try {
			String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
			String ext=FileUtils.getFileExt(file.getOriginalFilename());//获取文件后缀
			//保存文件
			File newFile = new File(savePath+"/"+uuid+"."+ext); 
			file.transferTo(newFile);
			//赋初始值
			Member me=ServletUtil.getMember();
			f.setWdUserId(me.getId());
			f.setWdCreateTime(DateUtil.currentTimestamp());
			f.setWdSize((int)file.getSize());
			f.setWdNewName(uuid);
			f.setWdExt(ext);
			
			return dao.save(f)? MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		return MsgConfig.MSG_KEY_FAIL;
		
		
	}
	
	public String updateFile(WdCompanyFiles f,String savePath,MultipartFile file){
		WdCompanyFiles old=dao.get(WdCompanyFiles.class, f.getId());
		if(old==null)return MsgConfig.MSG_KEY_NODATA;
		if(!checkPower(old.getWdSuperId(), 2)){
			return MsgConfig.MSG_KEY_NOPOWER;//无操作权限
		}
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
		old.setWdSuperId(f.getWdSuperId());
		return MsgConfig.MSG_KEY_SUCCESS;
	
	}

	public String deleteFile(String id,String savePath){
		
		WdCompanyFiles f=dao.get(WdCompanyFiles.class, id);
		if(f==null)return MsgConfig.MSG_KEY_NODATA;
		if(!checkPower(f.getWdSuperId(), 3)){
			return MsgConfig.MSG_KEY_NOPOWER;//无操作权限
		}
		//如果是文件则删除文件 
		File file = new File(savePath+"/"+f.getWdNewName()+"."+f.getWdExt()); 
		file.delete();
		dao.delete(f);
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	
	public boolean checkPower(String typeId,int power){
		Member me=ServletUtil.getMember();
		List<WdCfTypePowers> list=dao.find("select distinct p from WdCfTypeUsers u,WdCfTypePowers p where u.cfTypePowerId=p.id and  u.cfTypeId=?  and (u.tableId=? or u.tableId=?)",typeId,me.getId(),me.getDeptId());
		if(power==1){
			//判断是否有添加的权限
			for(WdCfTypePowers p:list){
				if(p.getPowerAdd()!=null)return true;	
			}
		}else if(power==2){
			//判断是否有修改的权限
			for(WdCfTypePowers p:list){
				if(p.getPowerUpdate()!=null)return true;	
			}
		}else if(power==3){
			//判断是否有删除的权限
			for(WdCfTypePowers p:list){
				if(p.getPowerDelete()!=null)return true;	
			}
		}
		return false;
	}
	public String move(String[] ids,String wdSuperId){
		
		
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	
	
	
}
