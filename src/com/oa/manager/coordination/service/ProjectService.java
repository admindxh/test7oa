/**  
 * @Title ProjectService.java
 * @date 2013-12-10 上午9:34:15
 * @Copyright: 2013 
 */
package com.oa.manager.coordination.service;

import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.coordination.bean.Project;
import com.oa.manager.coordination.bean.ProjectFiles;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public interface ProjectService extends IBaseService{
	/**
	 *	项目条件分页查询
	 * @param param
	 * @return
	 */
	public DataGrid selectProject(PageParam param);
	/**
	 * 查询我能查看的项目
	 * @param param
	 * @return
	 */
	public DataGrid selectMyProject(PageParam param);
	/**
	 * 添加项目
	 * @param p
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param deptIds
	 * @param userIds
	 * @return
	 */
	public boolean addProject(Project p,Integer isSendMsg,String[] fzIds,String[] deptIds,String[] userIds);
	/**
	 * 查询项目
	 * @param id
	 * @return
	 */
	public Map<String,Object> findProjectById(String id);
	/**
	 * 修改项目
	 * @param p
	 * @param isSendMsg
	 * @param fzIds
	 * @param cyIds
	 * @param deptIds
	 * @param userIds
	 * @return
	 */
	public String updateProject(Project p,Integer isSendMsg,String[] fzIds,String[] deptIds,String[] userIds);
	/**
	 * 删除项目
	 * @param ids
	 * @return
	 */
	public boolean deleteProject(String[] ids);
	/**
	 * 条件分页查询项目文档
	 * @param param
	 * @param id
	 * @return
	 */
	public DataGrid selectProjectFiles(PageParam param,String id);
	/**
	 * 添加文档
	 * @param f
	 * @param savePath
	 * @param file
	 * @return
	 */
	public String addProjectFiles(ProjectFiles f,String savePath, FileList files);
	/**
	 * 修改文档
	 * @param f
	 * @param savePath
	 * @param file
	 * @return
	 */
	public String updateProjectFiles(ProjectFiles f,String[] uuids,String savePath,FileList files);
	/**
	 * 删除文档
	 * @param ids
	 * @param savePath
	 * @return
	 */
	public boolean deleteProjectFiles(String projectId,String[] ids,String savePath);
	/**
	 * 判断当前用户是不是对项目文档 有添加 删除 修改 权限
	 * 项目创建者 负责人 和任务执行人 有权限
	 * @param projectId
	 * @return	0:项目不存在 1:有 2:没有
	 */
	public int hasPowerForFile(String projectId);
	
	
}
