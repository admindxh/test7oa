/**  
 * @Title ProjectServiceImpl.java
 * @date 2013-12-10 上午9:34:37
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
import com.oa.manager.coordination.bean.Project;
import com.oa.manager.coordination.bean.ProjectFiles;
import com.oa.manager.coordination.bean.ProjectUser;
import com.oa.manager.coordination.service.ProjectService;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl implements ProjectService{
	
	public DataGrid selectProject(PageParam param){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from Project p where createUserId=:createUserId ");
		Map map=new HashMap();
		map.put("createUserId",ServletUtil.getMember().getId());
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by p.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select new Map(p.id as id,p.name as name,p.type as type,p.status as status,p.startTime as startTime,p.endTime as endTime,p.createTime as createTime,p.importance as importance) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			r.put("type", MyCache.getInstance().getSelectValue((String)r.get("type")));
			r.put("importance", MyCache.getInstance().getSelectValue((String)r.get("importance")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public DataGrid selectMyProject(PageParam param){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from Project p,ProjectUser u where u.projectId=p.id and (u.tableId=:uid or u.tableId=:did) ");
		Map map=new HashMap();
		map.put("uid", ServletUtil.getMember().getId());
		map.put("did", ServletUtil.getMember().getDeptId());
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(DISTINCT p.id)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by p.createTime desc ");
		}
		List<Map<String,Object>> rows = dao.findPage(" select DISTINCT new Map(p.id as id,p.name as name,p.type as type,p.status as status,p.startTime as startTime,p.endTime as endTime,p.createTime as createTime,p.importance as importance,p.createUserId as createUserId) "+sb.toString(),param.getPage(),param.getRows(),map);
		for(Map<String,Object> r:rows){
			r.put("type", MyCache.getInstance().getSelectValue((String)r.get("type")));
			r.put("importance", MyCache.getInstance().getSelectValue((String)r.get("importance")));
			r.put("createUserName", MyCache.getInstance().getTrueName((String)r.get("createUserId")));
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public boolean addProject(Project p,Integer isSendMsg,String[] fzIds,String[] deptIds,String[] userIds){
		
		p.setCreateUserId(ServletUtil.getMember().getId());
		p.setCreateTime(DateUtil.currentTimestamp());
		dao.save(p);
		
		//等待保存的对象集合
		List<Object> c=new ArrayList<Object>();
		Set<String> sendIds=new HashSet<String>();//需要发送消息提醒的用户
	
		//关联用户类型  1:负责人，2:参与人.3:批注领导,4:发布部门,5:发布人员
		// 负责人 参与人 批注领导 会发送消息提醒
		for(String id:fzIds){
			ProjectUser pu=new ProjectUser();
			pu.setType((short)1);
			pu.setTableId(id);
			pu.setProjectId(p.getId());
			c.add(pu);
			sendIds.add(id);
		}
		for(String id:deptIds){
			ProjectUser pu=new ProjectUser();
			pu.setType((short)2);
			pu.setTableId(id);
			pu.setProjectId(p.getId());
			c.add(pu);
		}
		for(String id:userIds){
			ProjectUser pu=new ProjectUser();
			pu.setType((short)3);
			pu.setTableId(id);
			pu.setProjectId(p.getId());
			c.add(pu);
		}
		if(isSendMsg!=null&&isSendMsg==1){
			saveMsgWarn(31,p.getId() , sendIds,null);//发送消息提醒
		}
		return dao.saveOrUpdateAll(c);
			
	}
	public Map<String,Object> findProjectById(String id){
		
		Project p=dao.get(Project.class, id);
		if(p!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("p", p);
			List<String> fzIds=new ArrayList<String>();//负责人
			List<String> deptIds=new ArrayList<String>();//部门
			List<String> userIds=new ArrayList<String>();//用户
			List<ProjectUser> list=dao.find("from ProjectUser where projectId=?",id);
			
			for(ProjectUser pu:list){
				if(1==pu.getType()){
					fzIds.add(pu.getTableId());
				}else if(2==pu.getType()){
					deptIds.add(pu.getTableId());
				}else{
					userIds.add(pu.getTableId());
				}
			}
			// 判断当前用户 是不是 负责人  
			String userId=ServletUtil.getMember().getId();
			if(fzIds.contains(userId)){
				map.put("fz", true);//负责人 有修改工作计划的权限
			}
			
			
			map.put("fzLst",fzIds);
			
			
			map.put("fzIds", StringUtils.join(fzIds,","));
		
		
			map.put("deptIds",  StringUtils.join(deptIds,","));
			map.put("userIds",  StringUtils.join(userIds,","));
			
			map.put("id",p.getId());
			
			return map;
			
		}
		return null;
	}
	public String updateProject(Project p,Integer isSendMsg,String[] fzIds,String[] deptIds,String[] userIds){
		
		Project oldPro=dao.get(Project.class, p.getId());
		
		if(oldPro==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		oldPro.setName(p.getName());
		oldPro.setContent(p.getContent());
		oldPro.setStartTime(p.getStartTime());
		oldPro.setEndTime(p.getEndTime());
		oldPro.setType(p.getType());
		oldPro.setStatus(p.getStatus());
		oldPro.setImportance(p.getImportance());
		
		Set<String> sendIds=new HashSet<String>();//需要发送消息提醒的用户
		
		
		//负责人处理
		List<String> fzIdsOld=new ArrayList<String>();//负责人
	
		List<String> deptIdsOld=new ArrayList<String>();//部门
		List<String> userIdsOld=new ArrayList<String>();//用户
		List<ProjectUser> list=dao.find("from ProjectUser where projectId=?",oldPro.getId());
		for(ProjectUser pu:list){
			if(1==pu.getType()){//负责人
				fzIdsOld.add(pu.getTableId());
				boolean has=false;
				for(int i=0,k=fzIds.length;i<k;i++){
					if(pu.getTableId().equals(fzIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(pu);
				}else{
					sendIds.add(pu.getTableId());
				}
				
			}else if(2==pu.getType()){//部门
				deptIdsOld.add(pu.getTableId());
				boolean has=false;
				for(int i=0,k=deptIds.length;i<k;i++){
					if(pu.getTableId().equals(deptIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(pu);
				}
			}else{//用户
				userIdsOld.add(pu.getTableId());
				boolean has=false;
				for(int i=0,k=userIds.length;i<k;i++){
					if(pu.getTableId().equals(userIds[i])){
						has=true;
						break;
					}
				}
				if(!has){
					//删除
					dao.delete(pu);
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
				ProjectUser pu=new ProjectUser();
				pu.setType((short)1);
				pu.setTableId(fzIds[i]);
				pu.setProjectId(p.getId());
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
				ProjectUser pu=new ProjectUser();
				pu.setType((short)4);
				pu.setTableId(deptIds[i]);
				pu.setProjectId(p.getId());
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
				ProjectUser pu=new ProjectUser();
				pu.setType((short)5);
				pu.setTableId(userIds[i]);
				pu.setProjectId(p.getId());
				c.add(pu);
			}
		}
		if(isSendMsg!=null&&isSendMsg==1){
			//发送消息提醒
			saveMsgWarn(31,p.getId() , sendIds,null);
		}
		
		return dao.saveOrUpdateAll(c)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
	
	}
	
	public boolean deleteProject(String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			Project p=dao.get(Project.class, id);
			if(p!=null){
				c.add(p);
				
			}
		}
		return dao.deleteAll(c);
	}
	
	
	public DataGrid selectProjectFiles(PageParam param,String id){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from ProjectFiles f where projectId=:projectId ");
		Map map=new HashMap();
		map.put("projectId",id);
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long)dao.findOne("select count(*)"+sb,map));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by f.createTime desc ");
		}
		List<ProjectFiles> rows = dao.findPage(" select f "+sb.toString(),param.getPage(),param.getRows(),map);
		for(ProjectFiles r:rows){
			
			r.setCreateUserId(MyCache.getInstance().getTrueName(r.getCreateUserId()));
			
		}	
		
		data.setRows(rows);
	
		return data ;
		
	}
	public String addProjectFiles(ProjectFiles f,String savePath, FileList files){
		
		//赋初始值
		f.setCreateUserId(ServletUtil.getMember().getId());
		f.setCreateTime(DateUtil.currentTimestamp());
		dao.save(f);
		//最后保存上传的文件
		String fileJson=FileUtils.uploadFilesToJson(savePath, files);
		if(fileJson==null){
			throw new MyRuntimeException("文件上传失败");
		}
		f.setFiles(fileJson);
		return MsgConfig.MSG_KEY_SUCCESS;
		
	}
	public String updateProjectFiles(ProjectFiles f,String[] uuids,String savePath,FileList files){
		ProjectFiles old=dao.get(ProjectFiles.class, f.getId());
		if(old==null)return MsgConfig.MSG_KEY_NODATA;
		
		//判断是否有修改操作权限
		int power=hasPowerForFile(old.getProjectId());
		if(power==0){
			throw new MyRuntimeException("文件关联的项目不存在，可能已被删除！请刷新再试!");
		}
		if(power==2){
			throw new MyRuntimeException("没有权限，只有项目创建者,负责人,任务执行人才有修改权限！");
		}
		
		old.setTitle(f.getTitle());
		old.setContent(f.getContent());	
		
		//处理附件
		List<FileModel> oldFiles=JSON.parseArray(old.getFiles(),FileModel.class);
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
		old.setFiles(JSON.toJSONString(saveFiles));
		if(oldFiles!=null){
			oldFiles.removeAll(saveFiles);//去除需要保留的文件，然后删除不需要的文件
			for(FileModel fm:oldFiles){
				new File(savePath+"/"+fm.getUuid()).delete();//删除附件
			}
		}
		return MsgConfig.MSG_KEY_SUCCESS;

	}
	public boolean deleteProjectFiles(String projectId,String[] ids,String savePath){
		
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			ProjectFiles p=dao.get(ProjectFiles.class, id);
			if(p!=null&&projectId.equals(p.getProjectId())){
				c.add(p);
				List<FileModel> files=JSON.parseArray(p.getFiles(),FileModel.class);
				for(FileModel f:files){
					new File(savePath+"/"+f.getUuid()).delete();//删除附件
				}
			}
		}
		
		return dao.deleteAll(c);
	}
	public int hasPowerForFile(String projectId){
		Project p=dao.get(Project.class, projectId);
		if(p==null){
			return 0;
		}
		if(p.getCreateUserId().equals(ServletUtil.getMember().getId())){//项目创建者
			return 1;
		}else if(dao.findOne("from ProjectUser where projectId =? and type=1 and tableId=?",projectId, ServletUtil.getMember().getId())!=null){///项目负责人
			return 1;
		}else if(dao.findOne("from ProjectTaskUser where projectId =?  and  userId=?",projectId, ServletUtil.getMember().getId())!=null){//项目任务执行人
			
			return 1;
		}
		return 2;
	}
	

}
