/**  
 * @Project: jxoa
 * @Title: NoticeServiceImpl.java
 * @Package com.oa.manager.administrativeoffice.service.impl
 * @date 2013-4-12 下午4:00:47
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.coordination.bean.XtNotice;
import com.oa.manager.coordination.bean.XtNoticeContent;
import com.oa.manager.coordination.bean.XtNoticeFiles;
import com.oa.manager.coordination.bean.XtNoticeReceive;
import com.oa.manager.coordination.service.INoticeService;

/**
 * 
 * 类名：NoticeServiceImpl
 * 功能：通知管理 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-12 下午4:00:47
 *
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl implements INoticeService{
	
	
	public boolean addNotice(XtNotice not,XtNoticeContent nc,Integer isSendMsg,String[] deptIds,String[] userIds,String savePath,FileList files){
		try {
			//先保存通知主表
			String noticeId=(String)dao.saveReturnId(not);
			nc.setNoticeId(noticeId);
			
			//等待保存的对象集合
			List<Object> c=new ArrayList<Object>();
			c.add(nc);
			
			Set<String> addUserIds=new HashSet<String>();//需要发送的人员集合，部门，和人员交集
			
			
			//查询出此部门所有的用户id
			for(String id:deptIds){
				List<String> uid=dao.find("select id from SyUsers where deptId=?",id);
				addUserIds.addAll(uid);
			}
			for(String id:userIds){
				addUserIds.add(id);
			}
			for(String id:addUserIds){
				XtNoticeReceive nr=new XtNoticeReceive();
				nr.setNoticeId(noticeId);
				nr.setUserId(id);
				nr.setReadTime(null);
				c.add(nr);
			}
			//发送消息提醒
			if(isSendMsg!=null&&isSendMsg==1){
				saveMsgWarn(1, noticeId, addUserIds,null);
			}
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
						XtNoticeFiles nf=new XtNoticeFiles();
						nf.setFileExt(ext);
						nf.setFileName(f.getOriginalFilename());
						nf.setFileNewName(uuid);
						nf.setFileSize((int)f.getSize());
						nf.setNoticeId(noticeId);
						c.add(nf);
					}
				}
			}
			return  dao.saveOrUpdateAll(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectMyNotice(PageParam param,XtNotice not,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtNotice n,XtNoticeReceive nr where n.id=nr.noticeId and  nr.userId=? ");
		List list=new ArrayList();
		list.add(ServletUtil.getMember().getId());
		//查询条件
		if(StringUtils.isNotBlank(not.getNoticeTitle())){
			sb.append(" and n.noticeTitle like ? ");
			list.add("%"+not.getNoticeTitle()+"%");
		}
		if(StringUtils.isNotBlank(not.getNoticeType())){
			sb.append(" and n.noticeType = ? ");
			list.add(not.getNoticeType());	
		}
		if(StringUtils.isNotBlank(not.getNoticeSendId())){
			sb.append(" and n.noticeSendId = ? ");
			list.add(not.getNoticeSendId());	
		}
		if(startDate!=null){
			sb.append(" and n.noticeCreateTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and n.noticeCreateTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by n.noticeCreateTime desc");
		}
		
		List<Map> rows=dao.findPage("select  new Map(n.id as id,n.noticeTitle as noticeTitle,n.noticeType as noticeType,n.noticeSendId as noticeSendId,n.noticeCreateTime as noticeCreateTime,nr.readTime as readTime) "+sb.toString(),param.getPage(),param.getRows(),list);
		for(Map r:rows){
			r.put("noticeSendId", MyCache.getInstance().getTrueName((String)r.get("noticeSendId")));
			r.put("noticeType", MyCache.getInstance().getSelectValue((String)r.get("noticeType")));
			
		}	
		data.setRows(rows);
		return data;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectNotice(PageParam param,XtNotice not,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from XtNotice n where 1=1 ");
		List list=new ArrayList();
		
		//查询条件
		if(StringUtils.isNotBlank(not.getNoticeSendId())){
			sb.append(" and n.noticeSendId = ? ");
			list.add(not.getNoticeSendId());
		}
		if(StringUtils.isNotBlank(not.getNoticeTitle())){
			sb.append(" and n.noticeTitle like ? ");
			list.add("%"+not.getNoticeTitle()+"%");
		}
		if(StringUtils.isNotBlank(not.getNoticeType())){
			sb.append(" and n.noticeType = ? ");
			list.add(not.getNoticeType());	
		}
		if(startDate!=null){
			sb.append(" and n.noticeCreateTime >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and n.noticeCreateTime <=? ");
			list.add(endDate);	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by n.noticeCreateTime desc");
		}
		
		List<XtNotice> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(XtNotice n:rows){
			n.setNoticeSendId(MyCache.getInstance().getTrueName(n.getNoticeSendId()));
			n.setNoticeType(MyCache.getInstance().getSelectValue(n.getNoticeType()));
		}	
		data.setRows(rows);
		return data;
	
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map findNoticeById(String id){
		
		XtNotice not=dao.get(XtNotice.class, id);
		Map map=new HashMap();
		map.put("n", not);
		if(not!=null){
			XtNoticeContent nc=dao.get(XtNoticeContent.class, id);
			List<XtNoticeFiles> nfs=dao.find("from XtNoticeFiles nf where nf.noticeId=? ",id);
			map.put("nc", nc);
			map.put("nfs", nfs);
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateShowOneById(String id){
		
		XtNotice not=dao.get(XtNotice.class, id);
		Map map=new HashMap();
		map.put("n", not);
		if(not!=null){
			XtNoticeContent nc=dao.get(XtNoticeContent.class, id);
			List<XtNoticeFiles> nfs=dao.find("from XtNoticeFiles nf where nf.noticeId=? ",id);
			List<XtNoticeReceive> nrs=dao.find("from XtNoticeReceive nr where nr.noticeId=?",id);
			int isRead=0;
			for(XtNoticeReceive n:nrs){
				if(n.getReadTime()!=null){
					isRead++;
				}
			}
			map.put("nc", nc);
			map.put("nfs", nfs);
			map.put("nrs", nrs);
			
			map.put("readNum", isRead);
			map.put("allNum", nrs.size());
			
			String userId=ServletUtil.getMember().getId();
			for(XtNoticeReceive nr:nrs){
				if(userId.equals(nr.getUserId())){
					if(nr.getReadTime()==null){
						nr.setReadTime(DateUtil.currentTimestamp());
						dao.update(nr);
					}
				}
			}
		}
		return map;
	}
	
	
	public String deleteXtNoticeFiles(String id,String savePath){
		
		XtNoticeFiles nf=dao.get(XtNoticeFiles.class, id);
		if(nf==null)return MsgConfig.MSG_KEY_NODATA;
		File file = new File(savePath+"/"+nf.getFileNewName()+"."+nf.getFileExt()); 
		file.delete();
		return dao.delete(nf)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
	}
	
	public String updateNotice(XtNotice not,XtNoticeContent nc,Integer isSendMsg,String[] deptIds,String[] userIds,String savePath,FileList files){
		
		try{
			XtNotice oldnot=dao.get(XtNotice.class, not.getId());
			if(oldnot==null){
				return MsgConfig.MSG_KEY_NODATA;
			}
			
			oldnot.setNoticeTitle(not.getNoticeTitle());
			oldnot.setNoticeType(not.getNoticeType());
			
			//等待更新的对象集合
			List<Object> c=new ArrayList<Object>();
			nc.setNoticeId(oldnot.getId());
			c.add(nc);
			
			List<String> oldUserIds=dao.find("select userId from XtNoticeReceive nr where nr.noticeId=?",oldnot.getId());
			Set<String> addUserIds=new HashSet<String>();//需要发送的人员集合，部门，和人员交集
			
			//查询出此部门所有的用户id
			for(String id:deptIds){
				List<String> uid=dao.find("select id from SyUsers where deptId=?",id);
				addUserIds.addAll(uid);
			}
			for(String id:userIds){
				addUserIds.add(id);
			}
			
			//原先已有的-现在需要添加的==需要删除的
			List<String> delUserIds=new ArrayList<String>(oldUserIds);//需要删除的
			delUserIds.remove(addUserIds);
			for(String id:delUserIds){
				dao.delete("delete XtNoticeReceive where noticeId=? and userId=? ",oldnot.getId(),id);
			}
			//现在需要添加的-原先已有的=需要真正添加的
			addUserIds.remove(oldUserIds);
			
			for(String id:addUserIds){
				XtNoticeReceive nr=new XtNoticeReceive();
				nr.setNoticeId(oldnot.getId());
				nr.setUserId(id);
				nr.setReadTime(null);
				c.add(nr);
			}
			if(isSendMsg!=null&&isSendMsg==1)
				saveMsgWarn(1, oldnot.getId(), addUserIds,null);
			
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
						
						XtNoticeFiles nf=new XtNoticeFiles();
						nf.setFileExt(ext);
						nf.setFileName(f.getOriginalFilename());
						nf.setFileNewName(uuid);
						nf.setFileSize((int)f.getSize());
						nf.setNoticeId(oldnot.getId());
						c.add(nf);
					}
				}
			}
			return  dao.saveOrUpdateAll(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public boolean deleteXtNotice(String[] ids,String savePath){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			XtNotice notice=dao.get(XtNotice.class, id);
			if(notice!=null){
				c.add(notice);
				List<XtNoticeFiles> nfs=dao.find("from XtNoticeFiles where noticeId=?",id);
				for(XtNoticeFiles nf:nfs){
					if(nf!=null){
						File file = new File(savePath+"/"+nf.getFileNewName()+"."+nf.getFileExt()); 
						file.delete();
					}
					
				}
			}
		}
		
		return dao.deleteAll(c);
	}
//*********************************************安卓**************************************************
	@Override
	public List<Map<String, Object>> queryNoticByPerson(PageParam param) {
		List<Map<String,Object>> list = dao.findPage("select new Map(n.id as id,n.noticeTitle as noctitle,n.noticeType as noticeType) from XtNotice n,XtNoticeReceive nr where n.id=nr.noticeId and  nr.userId=? order by n.noticeCreateTime ",param.getPage(),param.getRows(),ServletUtil.getMember().getId()) ;
		for(Map<String,Object> m:list){
			m.put("title", StringUtils.abbreviate((String)(m.get("noctitle")),12));
			m.put("type", MyCache.getInstance().getSelectValue((String)m.get("noticeType")));//通知类别
			m.remove("noticeType");
			m.remove("noctitle");
		}
		return list;
	}
	@Override
	public Long totleNoticByPerson() {
		
		return (Long) dao.findUniqueOne("select count(*) from XtNotice n,XtNoticeReceive nr where n.id=nr.noticeId and  nr.userId=? ",ServletUtil.getMember().getId());
	}
	@Override
	public Map<String, Object> updateNoticById(String id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		XtNotice not=dao.get(XtNotice.class, id);
		if(not!=null){
			map.put("title",not.getNoticeTitle());//标题
			map.put("sendName",MyCache.getInstance().getTrueName(not.getNoticeSendId()));//发送人
			map.put("createtime",not.getNoticeCreateTime());//发送时间
			XtNoticeContent nc=dao.get(XtNoticeContent.class, id);//通知详情对象
			map.put("content",nc.getNoticeContent());//通知内容  
			XtNoticeReceive receive= (XtNoticeReceive) dao.findOne("from XtNoticeReceive nr where nr.userId=? and nr.noticeId=?  ",ServletUtil.getMember().getId(),id);
			//更新阅读状态
			if(receive.getReadTime()==null){
				receive.setReadTime(DateUtil.currentTimestamp());
				dao.update(receive);
			}
		}
		return map;
	}
	
}
