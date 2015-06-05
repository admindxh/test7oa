/**  
 * @Project: jxoa
 * @Title: ListenerServiceImpl.java
 * @Package com.oa.manager.workFlow.service.impl
 * @date 2013-8-12 上午10:55:12
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfWorkflowListener;
import com.oa.manager.workFlow.service.IListenerService;

/**
 * 
 * 类名：ListenerServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-8-12 上午10:55:12
 *
 */
@Service
public class ListenerServiceImpl extends BaseServiceImpl implements IListenerService {

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectWfWorkflowListener(PageParam param,WfWorkflowListener wl){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WfWorkflowListener wl where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(wl.getType()!=null){
			sb.append(" and wl.type=? ");
			list.add(wl.getType());
		}
		if(StringUtils.isNotBlank(wl.getName())){
			sb.append(" and wl.name like ? ");
			list.add("%"+wl.getName()+"%");	
		}
		if(StringUtils.isNotBlank(wl.getEvent())){
			sb.append(" and wl.event = ? ");
			list.add(wl.getEvent());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}
		List<WfWorkflowListener> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(WfWorkflowListener r:rows){
			
		}
		data.setRows(rows);
		return data;
		
	
	}
	//添加监听器
	public String addListener(WfWorkflowListener wl){
		
		//新增
		Object obj=dao.findOne("from WfWorkflowListener where name=? ",wl.getName());
		if(obj==null){
			saveLog("添加监听器", "名称:"+wl.getName());
			return dao.save(wl)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
			
			
		}else{
			
			return "msg.flowListener.name.unique";//流程名称重复！
		}
	
	}
	//修改监听器
	public String updateListener(WfWorkflowListener wl){
		Object obj=dao.findOne("from WfWorkflowListener where id!=? and name=? ",wl.getId(),wl.getName());
		if(obj==null){
			if(dao.update(wl)){
				saveLog("更新监听器", "名称:"+wl.getName());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.flowListener.name.unique";//流程名称重复！
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectWfWorkflowListener(WfWorkflowListener wl){
		
		StringBuffer sb=new StringBuffer("from WfWorkflowListener wl where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(wl.getType()!=null){
			sb.append(" and wl.type=? ");
			list.add(wl.getType());
		}
		if(StringUtils.isNotBlank(wl.getName())){
			sb.append(" and wl.name like ? ");
			list.add("%"+wl.getName()+"%");	
		}
		if(StringUtils.isNotBlank(wl.getEvent())){
			sb.append(" and wl.event = ? ");
			list.add(wl.getEvent());	
		}
		
	
		return dao.find(sb.toString(),list);
		
	
	}
	//删除监听
	public boolean deleteListener(String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			WfWorkflowListener wl=dao.get(WfWorkflowListener.class, id);
			if(wl!=null){
				saveLog("删除监听器", "名称:"+wl.getName());
				c.add(wl);
			}
		}
		return dao.deleteAll(c);
	}
	
}
