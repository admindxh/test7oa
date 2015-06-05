/**  
 * @Project: jtoa
 * @Title: OnDutyServiceImpl.java
 * @Package com.oa.manager.personnel.service.impl
 * @date 2013-12-4 下午9:48:52
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.coordination.bean.XtNotice;
import com.oa.manager.coordination.bean.XtNoticeFiles;
import com.oa.manager.personnel.bean.XtAttendanceSign;
import com.oa.manager.personnel.bean.XtOnduty;
import com.oa.manager.personnel.bean.XtOndutyFiles;
import com.oa.manager.personnel.service.IOnDutyService;

/**
 * 
 * 类名：OnDutyServiceImpl
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-12-4 下午2:48:52
 *
 */
@Service
public class OnDutyServiceImpl extends BaseServiceImpl implements
		IOnDutyService {

	/**
	 * 保存值班日志
	 */
	public String saveOnDuty(XtOnduty  onduty,String savePath ,FileList files){
		try { 
			Member me=ServletUtil.getMember();
			 
			//等待更新的对象集合
			List<Object> c=new ArrayList<Object>();
			onduty.setCreateuser(me.getId()); 
		    onduty.setCreatetime(DateUtil.currentTimestamp());
			 
			c.add(onduty);
			//上传文件
			List<MultipartFile> fileList=files.getFile();
			if(fileList!=null){
				for(MultipartFile f:fileList){
					if(f!=null&&!f.isEmpty()){
						String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
						String ext=FileUtils.getFileExt(f.getOriginalFilename());//获取文件后缀
						//保存文件
						File newFile = new File(savePath+"/"+uuid+"."+ext); 
						f.transferTo(newFile);
						XtOndutyFiles nf=new XtOndutyFiles();
						nf.setFileExt(ext);
						nf.setFileName(f.getOriginalFilename());
						nf.setFileNewName(uuid);
						nf.setFileSize((int)f.getSize());
						nf.setDutyid(onduty.getId());
						c.add(nf);
					}
				}
			}
			if(dao.saveOrUpdateAll(c)){
				return MsgConfig.MSG_KEY_SUCCESS;
			}
			return MsgConfig.MSG_KEY_FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	 
	}
	
	/**
	 * 查询值班日记
	 * @param param
	 * @param onduty
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectOnDuty(PageParam param,XtOnduty onduty, Date startDate,Date endDate){
		
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("  from XtOnduty o  where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(onduty.getUserid())){
			sb.append(" and o.userid = ? ");
			list.add(onduty.getUserid());
		}
		if(StringUtils.isNotBlank(onduty.getType())){
			sb.append(" and o.type = ? ");
			list.add(onduty.getType());	
		}
		if(StringUtils.isNotBlank(onduty.getTitle())){
			sb.append(" and o.title like ? ");
			list.add("%"+onduty.getTitle()+"%");	
		}
		if(startDate!=null){
			sb.append(" and o.starttime >=? ");
			list.add(DateUtil.date2String(startDate));	
		}
		if(endDate!=null){
			sb.append(" and o.starttime <=? ");
			list.add(DateUtil.date2String(endDate)); 
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*) "+sb,list));
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by o.createtime desc");
		}
		List<XtOnduty> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtOnduty l:rows){
			l.setUserid(MyCache.getInstance().getTrueName(l.getUserid()));
			l.setCreateuser(MyCache.getInstance().getTrueName(l.getCreateuser()));
			l.setType(MyCache.getInstance().getSelectValue(l.getType()));
			l.setContent("");
		}
		data.setRows(rows);
		
		return data;
	}

	/**
	 * 根据id获取值班记录
	 * @param id
	 * @param map
	 * @return
	 */
	public ModelMap  selectOnDutyById(String id,ModelMap map){
		XtOnduty onduty =dao.get(XtOnduty.class, id);
		if(onduty!=null){
			map.put("username", MyCache.getInstance().getTrueName(onduty.getUserid()));
			onduty.setCreateuser(MyCache.getInstance().getTrueName(onduty.getCreateuser()));
			//onduty.setType(MyCache.getInstance().getSelectValue(onduty.getType()));
			map.put("o", onduty);
			List<XtOndutyFiles> fs=dao.find("from XtOndutyFiles f where f.dutyid = ? ",id);
			map.put("fs", fs);
			
		}
		return map;
	}
	 
	/**
	 * 修改值班日记
	 * @param onduty
	 * @return
	 */
	public String updateOnDuty(XtOnduty  onduty,String savePath ,FileList files ){
		try { 
			Member me=ServletUtil.getMember();
			//onduty.setCreateuser(me.getId()); 
			//onduty.setCreatetime(DateUtil.currentTimestamp());
			//保存值班记录
			String oId=(String)dao.saveReturnId(onduty);
			//等待保存的对象集合
			List<Object> c=new ArrayList<Object>();
			c.add(onduty);
			//上传文件
			List<MultipartFile> fileList=files.getFile();
			if(fileList!=null){
				for(MultipartFile f:fileList){
					if(f!=null&&!f.isEmpty()){
						String uuid=FileUtils.getUUID();//uuid作为保存时的文件名
						String ext=FileUtils.getFileExt(f.getOriginalFilename());//获取文件后缀
						//保存文件
						File newFile = new File(savePath+"/"+uuid+"."+ext); 
						f.transferTo(newFile);
						XtOndutyFiles nf=new XtOndutyFiles();
						nf.setFileExt(ext);
						nf.setFileName(f.getOriginalFilename());
						nf.setFileNewName(uuid);
						nf.setFileSize((int)f.getSize());
						nf.setDutyid(oId);
						c.add(nf);
					}
				}
			}
			if(dao.saveOrUpdateAll(c)){
				return MsgConfig.MSG_KEY_SUCCESS;
			}
			return MsgConfig.MSG_KEY_FAIL;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * 删除考勤记录
	 */
	public boolean deleteOnDuty(String[] ids){
		
		for(String id:ids){
			dao.delete("delete XtOnduty where id=?", id);
		} 
		return true;
	}
	/**
	 * 删除附件
	 */
	public String  deleteOnDutyFiles(String id,String savePath){
		
		XtOndutyFiles of=dao.get(XtOndutyFiles.class, id);
		if(of==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		File file = new File(savePath+"/"+of.getFileNewName()+"."+of.getFileExt()); 
		file.delete();
		
		return dao.delete(of)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
	} 
	
	/**
	 * 删除值班记录及附件
	 */
	public boolean deleteXtOnduty(String[] ids,String savePath){
		
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			XtOnduty d=dao.get(XtOnduty.class, id);
			if(d!=null){
				c.add(d);
				List<XtOndutyFiles> fs=dao.find("from XtOndutyFiles where dutyid=?",id);
				for(XtOndutyFiles f:fs){
					if(f!=null){
						File file = new File(savePath+"/"+f.getFileNewName()+"."+f.getFileExt()); 
						file.delete();
					}
					//c.add(f);
				}
			}
		}
		
		return dao.deleteAll(c);
	}
}
