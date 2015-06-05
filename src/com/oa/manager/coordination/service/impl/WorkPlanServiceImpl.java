/**  
 * @Title WorkPlanServiceImpl.java
 * @date 2013-11-28 上午10:23:48
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.FileModel;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.SearchFilterUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.coordination.bean.XtWorkPlan;
import com.oa.manager.coordination.bean.XtWorkPlanComment;
import com.oa.manager.coordination.bean.XtWorkPlanTask;
import com.oa.manager.coordination.bean.XtWorkPlanUser;
import com.oa.manager.coordination.service.WorkPlanService;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
@Service
public class WorkPlanServiceImpl extends BaseServiceImpl implements WorkPlanService{
	

	public DataGrid selectWorkPlan(PageParam param){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtWorkPlan w where createUserId=:createUserId ");
		Map map=new HashMap();
		map.put("createUserId",ServletUtil.getMember().getId());
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by w.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(w.id as id,w.name as name,w.type as type,w.status as status,w.startTime as startTime,w.endTime as endTime,w.createTime as createTime) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			r.put("type", MyCache.getInstance().getSelectValue((String)r.get("type")));
			
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	@Override
	public DataGrid selectMyWorkPlan(PageParam param){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtWorkPlan w,XtWorkPlanUser u where u.workPlanId=w.id and (u.tableId=:uid or u.tableId=:did) ");
		Map map=new HashMap();
		map.put("uid", ServletUtil.getMember().getId());
		map.put("did", ServletUtil.getMember().getDeptId());
		
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select  count(DISTINCT w.id)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by w.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select DISTINCT  new Map(w.id as id,w.name as name,w.type as type,w.status as status,w.startTime as startTime,w.endTime as endTime,w.createTime as createTime,w.createUserId as createUserId) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			r.put("type", MyCache.getInstance().getSelectValue((String)r.get("type")));
			r.put("createUser", MyCache.getInstance().getTrueName((String)r.get("createUserId")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public boolean addWorkPlan(XtWorkPlan wp,Integer isSendMsg,String[] fzIds,String[] cyIds,String[] headIds,String[] deptIds,String[] userIds,String savePath,FileList files){
		
		wp.setCreateUserId(ServletUtil.getMember().getId());
		wp.setCreateTime(DateUtil.currentTimestamp());
		dao.save(wp);
		
		//等待保存的对象集合
		List<Object> c=new ArrayList<Object>();
		Set<String> sendIds=new HashSet<String>();//需要发送消息提醒的用户
	
		//关联用户类型  1:负责人，2:参与人.3:批注领导,4:发布部门,5:发布人员
		// 负责人 参与人 批注领导 会发送消息提醒
		for(String id:fzIds){
			XtWorkPlanUser pu=new XtWorkPlanUser();
			pu.setType((short)1);
			pu.setTableId(id);
			pu.setWorkPlanId(wp.getId());
			c.add(pu);
			sendIds.add(id);
		}
		for(String id:cyIds){
			XtWorkPlanUser pu=new XtWorkPlanUser();
			pu.setType((short)2);
			pu.setTableId(id);
			pu.setWorkPlanId(wp.getId());
			c.add(pu);
			sendIds.add(id);
		}
		for(String id:headIds){
			XtWorkPlanUser pu=new XtWorkPlanUser();
			pu.setType((short)3);
			pu.setTableId(id);
			pu.setWorkPlanId(wp.getId());
			c.add(pu);
			sendIds.add(id);
		}
		for(String id:deptIds){
			XtWorkPlanUser pu=new XtWorkPlanUser();
			pu.setType((short)4);
			pu.setTableId(id);
			pu.setWorkPlanId(wp.getId());
			c.add(pu);
		}
		for(String id:userIds){
			XtWorkPlanUser pu=new XtWorkPlanUser();
			pu.setType((short)5);
			pu.setTableId(id);
			pu.setWorkPlanId(wp.getId());
			c.add(pu);
		}
		if(isSendMsg!=null&&isSendMsg==1){
			saveMsgWarn(20,wp.getId() , sendIds,null);//发送消息提醒
		}
		if(dao.saveOrUpdateAll(c)){
			//最后保存上传的文件
			String fileJson=FileUtils.uploadFilesToJson(savePath, files);
			if(fileJson==null){
				throw new MyRuntimeException("文件上传失败");
			}
			wp.setFiles(fileJson);
			return  true;
		}else{
			return false;
		}
	}
	
	public Map<String,Object> findWorkPlanById(String id){
	
		XtWorkPlan wp=dao.get(XtWorkPlan.class, id);
		if(wp!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("wp", wp);
			List<String> fzIds=new ArrayList<String>();//负责人
			List<String> cyIds=new ArrayList<String>();//参与人
			List<String> headIds=new ArrayList<String>();//领导批注
			List<String> deptIds=new ArrayList<String>();//部门
			List<String> userIds=new ArrayList<String>();//用户
			List<XtWorkPlanUser> list=dao.find("from XtWorkPlanUser where workPlanId=?",id);
			
			for(XtWorkPlanUser p:list){
				if(1==p.getType()){
					fzIds.add(p.getTableId());
				}else if(2==p.getType()){
					cyIds.add(p.getTableId());
				}else if(3==p.getType()){
					headIds.add(p.getTableId());
				}else if(4==p.getType()){
					deptIds.add(p.getTableId());
				}else{
					userIds.add(p.getTableId());
				}
			}
			// 判断当前用户 是不是 负责人 参与人 领导批准  
			String userId=ServletUtil.getMember().getId();
			if(fzIds.contains(userId)){
				map.put("fz", true);//负责人 有修改工作计划的权限
			}
			if(wp.getStatus()!=3&&(fzIds.contains(userId)||cyIds.contains(userId))){
				map.put("rw", true);//有添加计划任务的权限
			}
			if(headIds.contains(userId)){
				map.put("pz", true);//有些批注的权限
			}
			map.put("fzLst",fzIds);
			map.put("cyLst", cyIds);
			
			map.put("fzIds", StringUtils.join(fzIds,","));
			map.put("cyIds",  StringUtils.join(cyIds,","));
			map.put("headIds",  StringUtils.join(headIds,","));
			map.put("deptIds",  StringUtils.join(deptIds,","));
			map.put("userIds",  StringUtils.join(userIds,","));
			
			map.put("files", JSON.parseArray(wp.getFiles()));
			
			map.put("id",wp.getId());
			
			
			return map;
			
		}
		return null;
	}
	public String updateWorkPlan(XtWorkPlan wp,Integer isSendMsg,String[] fzIds,String[] cyIds,String[] headIds,String[] deptIds,String[] userIds,String[] uuids,String savePath,FileList files){
		
		XtWorkPlan oldWp=dao.get(XtWorkPlan.class, wp.getId());
		
		if(oldWp==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		oldWp.setName(wp.getName());
		oldWp.setContent(wp.getContent());
		oldWp.setStartTime(wp.getStartTime());
		oldWp.setEndTime(wp.getEndTime());
		oldWp.setRemark(wp.getRemark());
		oldWp.setType(wp.getType());
		oldWp.setStatus(wp.getStatus());
		Set<String> sendIds=new HashSet<String>();//需要发送消息提醒的用户
		
		
		//负责人处理
		List<String> fzIdsOld=new ArrayList<String>();//负责人
		List<String> cyIdsOld=new ArrayList<String>();//参与人
		List<String> headIdsOld=new ArrayList<String>();//领导批注
		List<String> deptIdsOld=new ArrayList<String>();//部门
		List<String> userIdsOld=new ArrayList<String>();//用户
		List<XtWorkPlanUser> list=dao.find("from XtWorkPlanUser where workPlanId=?",oldWp.getId());
		for(XtWorkPlanUser p:list){
			if(1==p.getType()){//负责人
				fzIdsOld.add(p.getTableId());
				boolean has=false;
				for(int i=0,k=fzIds.length;i<k;i++){
					if(p.getTableId().equals(fzIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(p);
				}else{
					sendIds.add(p.getTableId());
				}
				
			}else if(2==p.getType()){//参与人
				cyIdsOld.add(p.getTableId());
				boolean has=false;
				for(int i=0,k=cyIds.length;i<k;i++){
					if(p.getTableId().equals(cyIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(p);
				}else{
					sendIds.add(p.getTableId());
				}
			}else if(3==p.getType()){//领导
				headIdsOld.add(p.getTableId());
				boolean has=false;
				for(int i=0,k=headIds.length;i<k;i++){
					if(p.getTableId().equals(headIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(p);
				}else{
					sendIds.add(p.getTableId());
				}
			}else if(4==p.getType()){//部门
				deptIdsOld.add(p.getTableId());
				boolean has=false;
				for(int i=0,k=deptIds.length;i<k;i++){
					if(p.getTableId().equals(deptIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(p);
				}
			}else{//用户
				userIdsOld.add(p.getTableId());
				boolean has=false;
				for(int i=0,k=userIds.length;i<k;i++){
					if(p.getTableId().equals(userIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(p);
				}
			}
		}
		//新增加
		//等待保存的对象集合
		List<Object> c=new ArrayList<Object>();
		for(int i=0,k=fzIds.length;i<k;i++){//负责人
			boolean has=false;
			for(String id:fzIdsOld){
				if(id.equals(fzIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				XtWorkPlanUser pu=new XtWorkPlanUser();
				pu.setType((short)1);
				pu.setTableId(fzIds[i]);
				pu.setWorkPlanId(wp.getId());
				c.add(pu);
				sendIds.add(pu.getTableId());
			}
		}
		
		for(int i=0,k=cyIds.length;i<k;i++){//参与人
			boolean has=false;
			for(String id:cyIdsOld){
				if(id.equals(cyIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				XtWorkPlanUser pu=new XtWorkPlanUser();
				pu.setType((short)2);
				pu.setTableId(cyIds[i]);
				pu.setWorkPlanId(wp.getId());
				c.add(pu);
				sendIds.add(pu.getTableId());
			}
		}
		
		for(int i=0,k=headIds.length;i<k;i++){//领导
			boolean has=false;
			for(String id:headIdsOld){
				if(id.equals(headIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				XtWorkPlanUser pu=new XtWorkPlanUser();
				pu.setType((short)3);
				pu.setTableId(headIds[i]);
				pu.setWorkPlanId(wp.getId());
				c.add(pu);
				sendIds.add(pu.getTableId());
			}
		}
		
		for(int i=0,k=deptIds.length;i<k;i++){//部门
			boolean has=false;
			for(String id:deptIdsOld){
				if(id.equals(deptIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				XtWorkPlanUser pu=new XtWorkPlanUser();
				pu.setType((short)4);
				pu.setTableId(deptIds[i]);
				pu.setWorkPlanId(wp.getId());
				c.add(pu);
			}
		}
		
		for(int i=0,k=userIds.length;i<k;i++){//用户
			boolean has=false;
			for(String id:userIdsOld){
				if(id.equals(userIds[i])){
					has=true;
					break;
				}
			}
			if(!has){
				XtWorkPlanUser pu=new XtWorkPlanUser();
				pu.setType((short)5);
				pu.setTableId(userIds[i]);
				pu.setWorkPlanId(wp.getId());
				c.add(pu);
			}
		}
		if(isSendMsg!=null&&isSendMsg==1){
			//发送消息提醒
			saveMsgWarn(20,wp.getId() , sendIds,null);
		}
		
		if(dao.saveOrUpdateAll(c)){
			//处理附件
			List<FileModel> oldFiles=JSON.parseArray(oldWp.getFiles(),FileModel.class);
			List<FileModel> saveFiles=FileUtils.uploadFiles(savePath, files);//上传文件
			if(saveFiles==null){
				throw new MyRuntimeException("文件上传失败");
			}
			if(uuids!=null&&uuids.length>0){
				for(FileModel m:oldFiles){
					for(int i=0,k=uuids.length;i<k;i++){
						if(m.getUuid().equals(uuids[i])){
							saveFiles.add(m);
							break;
						}
						
					}
				}
			}
			oldWp.setFiles(JSON.toJSONString(saveFiles));
			if(oldFiles!=null){
				oldFiles.removeAll(saveFiles);//移除保留的文件
				for(FileModel f:oldFiles){
					new File(savePath+"/"+f.getUuid()).delete();//删除附件
				}
			}
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
		
		
	}
	
	public boolean deleteWorkPlan(String[] ids,String savePath){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			XtWorkPlan wp=dao.get(XtWorkPlan.class, id);
			if(wp!=null){
				c.add(wp);
				List<FileModel> files=JSON.parseArray(wp.getFiles(),FileModel.class);
				for(FileModel f:files){
					new File(savePath+"/"+f.getUuid()).delete();//删除附件
				}
			}
		}
		
		return dao.deleteAll(c);
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid selectWorkPlanTask(PageParam param,String id,String userId){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtWorkPlanTask t where workPlanId=? ");
		List list=new ArrayList();
		list.add(id);
		if(StringUtils.isNotBlank(userId)){
			sb.append(" and userId=? ");
			list.add(userId);
		}
		data.setTotal((Long)dao.findOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by t.startTime asc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(t.id as id,t.startTime as startTime,t.endTime as endTime,t.userId as userId,t.files as files, t.content as content) "+sb.toString(),param.getPage(),param.getRows(),list);
	
		for(Map<String,Object> r:rows){
			r.put("userName", MyCache.getInstance().getTrueName((String)r.get("userId")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	
	public boolean addWorkPlanTask(XtWorkPlanTask t,String savePath,FileList files){
		
		//保存上传的文件
		String fileJson=FileUtils.uploadFilesToJson(savePath, files);
		if(fileJson==null){
			throw new MyRuntimeException("文件上传失败");
		}
		t.setFiles(fileJson);
		t.setUserId(ServletUtil.getMember().getId());
		
		return dao.save(t);
		
	}
	public String updateWorkPlanTask(XtWorkPlanTask t,String savePath,String[] uuids,FileList files){
		XtWorkPlanTask oldPt=dao.get(XtWorkPlanTask.class,t.getId());
		
		if(!oldPt.getUserId().equals(ServletUtil.getMember().getId())){
			//不是计划创建人 没有权限进行修改
			return MsgConfig.MSG_KEY_NODATA;
		}
		oldPt.setContent(t.getContent());
		oldPt.setEndTime(t.getEndTime());
		oldPt.setStartTime(t.getStartTime());
		
		//处理上传文件
		List<FileModel> oldFiles=JSON.parseArray(oldPt.getFiles(),FileModel.class);
		List<FileModel> saveFiles=FileUtils.uploadFiles(savePath, files);//上传文件
		if(saveFiles==null){
			throw new MyRuntimeException("文件上传失败");
		}
		if(uuids!=null&&uuids.length>0){
			for(FileModel m:oldFiles){
				for(int i=0,k=uuids.length;i<k;i++){
					if(m.getUuid().equals(uuids[i])){
						saveFiles.add(m);
						break;
					}
				}
			}
		}
		oldPt.setFiles(JSON.toJSONString(saveFiles));
		if(oldFiles!=null){
			oldFiles.removeAll(saveFiles);//移除保留的文件
			for(FileModel f:oldFiles){
				new File(savePath+"/"+f.getUuid()).delete();//删除附件
			}
		}
		
		return MsgConfig.MSG_KEY_SUCCESS;
		
	}
	
	public String deleteWorkPlanTask(String id,String savePath){
		//等待删除的对象集合
		
		XtWorkPlanTask t=dao.get(XtWorkPlanTask.class, id);
		if(t!=null){
			if(!t.getUserId().equals(ServletUtil.getMember().getId())){
				//不是计划创建人 没有权限进行修改
				return MsgConfig.MSG_KEY_NOPOWER;
			}
			List<FileModel> files=JSON.parseArray(t.getFiles(),FileModel.class);
			for(FileModel f:files){
				new File(savePath+"/"+f.getUuid()).delete();//删除附件
			}
			
			
		}
		return dao.delete(t)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
	}
	
	public DataGrid selectWorkPlanComment(PageParam param,String id){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from XtWorkPlanComment t where workPlanId=:pid ");
		Map map=new HashMap();
		map.put("pid",id);
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by t.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(t.id as id,t.userId as userId,t.content as content,t.createTime as createTime) "+sb.toString(),param.getPage(),param.getRows(),map);
	
		for(Map<String,Object> r:rows){
			r.put("userName", MyCache.getInstance().getTrueName((String)r.get("userId")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public String updateWorkPlanComment(XtWorkPlanComment c){
		XtWorkPlanComment oldC=dao.get(XtWorkPlanComment.class,c.getId());
		
		if(!oldC.getUserId().equals(ServletUtil.getMember().getId())){
			//不是计划创建人 没有权限进行修改
			return MsgConfig.MSG_KEY_NODATA;
		}
		oldC.setContent(c.getContent());
		
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	
	public String deleteWorkPlanComment(String id){
		
		XtWorkPlanComment c=dao.get(XtWorkPlanComment.class, id);
		if(c!=null){
			if(c.getUserId().equals(ServletUtil.getMember().getId())){
				//只能删除自己写的
				return dao.delete(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			}else{
				return MsgConfig.MSG_KEY_NOPOWER;
			}
			

		}
		return MsgConfig.MSG_KEY_NODATA;
	}
	
	
	
	
}
