/**  
 * @Project: jxoa
 * @Title: ListenerAction.java
 * @Package com.oa.manager.workFlow.action
 * @date 2013-8-12 上午10:54:37
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfWorkflowListener;
import com.oa.manager.workFlow.service.IListenerService;

/**
 * 
 * 类名：ListenerAction
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-8-12 上午10:54:37
 *
 */
@Controller
@RequestMapping("/flowListener")
public class ListenerAction extends BaseAction{
	@Autowired
	private IListenerService service; 
	
	/**
	 * 流程监听器查询
	 * @return
	 */
	@RequiresPermissions("flowListener:read")
	@RequestMapping("load")
	public String load(){
		
		return "workflow/flow/listener/load";
	
	}
	/**
	 * 流程监听器查询
	 * @param param
	 * @param wl
	 * @return
	 */
	@RequiresPermissions("flowListener:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,WfWorkflowListener wl){
		
		return ajaxJsonEscape(service.selectWfWorkflowListener(param, wl));
	}
	/**
	 * 跳转到监听器添加页面
	 * @return
	 */
	@RequiresPermissions("flowListener:add")
	@RequestMapping("addPage")
	public String addPage(ModelMap map){
		
		return "workflow/flow/listener/add";
	}
	/**
	 * 添加监听器
	 * @param wl
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("flowListener:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid WfWorkflowListener wl,Errors errors){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.addListener(wl));
	
	}
	/**
	 * 跳转到表单修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("flowListener:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		WfWorkflowListener wl=service.get(WfWorkflowListener.class, id);
		if(wl==null){
			return NODATA;
		}
		map.addAttribute("wl",wl);
		
		return "workflow/flow/listener/update";
	}
	/**
	 * 更新表单
	 * @param wf
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("flowListener:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid WfWorkflowListener wl,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateListener(wl));
		
	}
	/**
	 * 跳转到 查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		
		return "workflow/flow/listener/lookup";
		
	}
	/**
	 *  查找带回 查询 
	 * @param wl
	 * @param ids 已有的监听器id 不需要显示，可以先去除
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("lookUp")
	public ModelAndView lookUp(WfWorkflowListener wl,String[] ids){
		
		Map map=new HashMap();
		
		List<WfWorkflowListener> list=service.selectWfWorkflowListener(wl);
		List<WfWorkflowListener> del=new ArrayList();
		if(ids!=null){
			for(String id:ids){
				for(WfWorkflowListener ls:list){
					if(id.equals(ls.getId()))del.add(ls);
				}
			}
		}
		list.removeAll(del);
		map.put("rows",list);
		
		return ajaxJsonEscape(map);
	}
	
	/**
	 * 根据监听器id  获取监听器信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("getByIds")
	public ModelAndView get(String[] ids){
		
		List<WfWorkflowListener>list=new ArrayList<WfWorkflowListener>();
		for(String id:ids){
			WfWorkflowListener wl=service.get(WfWorkflowListener.class, id);
			if(wl!=null)list.add(wl);
		}
	
		Map<String,Object> map=new HashMap<String,Object>();
	
		map.put(MsgConfig.STATUSCODE,MsgConfig.CODE_SUCCESS );
		map.put("rows",list);
		
		return ajaxJsonEscape(map);
	
	}
	
	/**
	 * 删除监听器
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("flowListener:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteListener(ids));
	
	}
	
	
}
