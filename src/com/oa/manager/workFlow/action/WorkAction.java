/**  
 * @Title: WorkAction.java
 * @date 2013-8-20 上午10:49:59
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.workFlow.bean.WfWork;
import com.oa.manager.workFlow.service.IWorkService;

/**
 * 工作流程 工作操作
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
@Controller
@RequestMapping("/work")
public class WorkAction extends BaseAction{
	
	@Autowired
	private IWorkService service; 
	
	/**
	 * 工作上传附件保存路径
	 */
	public static final String SAVEPATH=BaseConfig.uploadPath+"flow_work";
	

	/**
	 * 跳转到新建工作页面
	 * @return
	 */
	@RequiresPermissions("work:read")
	@RequestMapping("new/load")
	public String nowLoad(){
		
		return "workflow/process/new/load";
	
	}
	
	/**
	 * 新建工作 添加工作页面
	 * @param formId
	 * @param map
	 * @return
	 */
	@RequiresPermissions("work:add")
	@RequestMapping("addPage")
	public String addWorkPage(String key,ModelMap map){
		
		map.put("pd", service.getProcessDefinition(key));
		
		if(map.get("pd")==null){
			return NODATA;
		}
		
		return "workflow/process/new/add";
	
	}
	/**
	 * 新建工作，发起流程实例
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("work:add")
	@RequestMapping("add")
	public ModelAndView addNewWork(String id,String key,String workTitle,FileList files){

		//验证上传文件大小，格式
		List<MultipartFile> fs=files.getFile();
		if(fs!=null){
			for(MultipartFile file:fs){
				if(file!=null&&!file.isEmpty()){
					ModelAndView view=FileUtils.validateFile(file, 1024L*1024*500, null, new String[]{"exe"});
					if(view!=null)return view;
				}
			}
		}
		
		return ajaxJsonEscape(service.saveWork(id,key,workTitle,SAVEPATH,files));
	}
	
	
	/**
	 * 已发工作加载页面
	 * @return
	 */
	@RequiresPermissions("work:myStart")
	@RequestMapping("mystart/load")
	public String myProcessLoad(){
		
		
		return "workflow/process/mystart/load";
	
	}
	
	/**
	 * 查询我发起的流程,正在运行中流程实例
	 * @return
	 */
	@RequiresPermissions("work:myStart")
	@RequestMapping("mystart/run")
	public String queryRunning(){
		
		return "workflow/process/mystart/running";
	
	}
	/**
	 * 我的已发工作查询 默认加载运行中的流程实例
	 * @param param
	 * @param qm
	 * @param map
	 * @return
	 */
	@RequiresPermissions("work:myStart")
	@RequestMapping("mystart/run/query")
	public ModelAndView queryMyProcess(PageParam param,WfWork wf,Date startedAfter,Date startedBefore){
		
		wf.setUserId(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.queryRunProcessInstance(param, wf, startedAfter, startedBefore));
	
	}
	
	/**
	 * 查询我发起的流程 包括运行中，所有的
	 * @param param
	 * @param qm
	 * @param map
	 * @return
	 */
	@RequiresPermissions("work:myStart")
	@RequestMapping("mystart/his")
	public String historyLoad(){
		
		return "workflow/process/mystart/history";
	
	}
	/**
	 * 查询我发起的流程 包括运行中，所有的
	 * @param param
	 * @param qm
	 * @param map
	 * @return
	 */
	@RequiresPermissions("work:myStart")
	@RequestMapping("mystart/his/query")
	public ModelAndView queryHistory(PageParam param,WfWork wf,Date startedAfter,Date startedBefore,Date finishedAfter,Date finishedBefore){
		
		wf.setUserId(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.selectWork(param, wf, startedAfter, startedBefore, finishedAfter, finishedBefore));
	
	}
	
	
	/**
	 * 工作信息查询
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("show")
	public String showOneWork(String processInstanceId,ModelMap map){
		
		Map m=service.selectProcessDetails(processInstanceId);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
	
		return "workflow/process/mystart/show";
		
	}
	/**
	 * 工作信息查询 打印页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("print")
	public String printWork(String processInstanceId,ModelMap map){
		
		
		Map m=service.selectProcessDetails(processInstanceId);
		if(m==null){
			return NODATA;
		}
		map.addAllAttributes(m);
	
		return "workflow/process/mystart/show_print";
		
	}

	 /**
	  * 删除流程实例
	  * @param id	流程实例id
	  * @param text	删除原因
	  * @return
	  */
	@RequiresPermissions("work:deleteRun")
	@RequestMapping("del")
	public ModelAndView delete(String id,String text){
		
		return ajaxDone(service.deleteProcessInstance(id, text));
		
	}
	
	/**
     * 批量删除工作(流程实例,历史记录)
     * @param ids	
     * @param cascade	是否级联删除历史记录
     * @return
     */
	@RequiresPermissions("work:deleteHis")
	@RequestMapping("delHis")
	public ModelAndView deleteWork(String[] ids){
		
		return ajaxDone(service.deleteHistoricProcessInstance(ids));
		
	}
	
	
	/**
	 * 文件下载
	 * @param id 文件保存时名称
	 * @param name 文件原名
	 * @param ext  文件后缀
	 * @param request  
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/download")   
    public String down(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
       
    	return FileUtils.down(SAVEPATH, name, newName, ext,request,response);
        
    }  
	
    /**
	 * 根据流程定义id 查询流程实例
	 * @param param
	 * @param qm
	 * @param map
	 * @return
	 */
    @RequiresPermissions("work:monitor")
	@RequestMapping("monitor/processInstance/version")
	public String queryprocessInstanceByVersion(){

		return "workflow/flow/processInstance_version";
	
	}
  

	/**
	 * 跳转到流程监控页面
	 * @return
	 */
    @RequiresPermissions("work:monitor")
	@RequestMapping("monitor/load")
	public String monitorLoad(){
		
		return "workflow/process/monitor/load";
	
	}
	/**
	 * 流程监控 查询所有工作流程信息
	 * @param param
	 * @param wf
	 * @param startedAfter
	 * @param startedBefore
	 * @param finishedAfter
	 * @param finishedBefore
	 * @return
	 */
    @RequiresPermissions("work:monitor")
	@RequestMapping("monitor/processInstance/query")
	public ModelAndView queryProcessInstanceByKey(PageParam param,WfWork wf,Date startedAfter,Date startedBefore,Date finishedAfter,Date finishedBefore){
		DataGrid data=service.selectWork(param, wf, startedAfter, startedBefore, finishedAfter, finishedBefore);
		List<WfWork> rows=data.getRows();
		for(WfWork r:rows){
			r.setUserId(MyCache.getInstance().getTrueName(r.getUserId()));
		}
		return ajaxJsonEscape(data);
	
	}
	
}
