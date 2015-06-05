/**  
 * @Project: jxoa
 * @Title: FormServiceImpl.java
 * @Package com.oa.manager.workFlow.service.impl
 * @date 2013-7-7 下午2:27:32
 * @Copyright: 2013 
 */
package com.oa.manager.workFlow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.workFlow.bean.WfForm;
import com.oa.manager.workFlow.service.IFormService;

/**
 * 
 * 类名：FormServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-7 下午2:27:32
 *
 */
@Service
public class FormServiceImpl extends BaseServiceImpl implements IFormService{

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectWfForm(PageParam param,WfForm wf){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from WfForm wf where 1=1 ");
		List list=new ArrayList();
		//查询条件
		
		if(StringUtils.isNotBlank(wf.getFormName())){
			sb.append(" and wf.formName like ? ");
			list.add("%"+wf.getFormName()+"%");	
		}
		if(wf.getType()!=null){
			sb.append(" and wf.type= ? ");
			list.add(wf.getType());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		List<Map> rows=dao.findPage("select new Map(wf.id as id,wf.type as type,wf.formName as formName,wf.updateTime as updateTime, wf.updateUserId as updateUserId," +
				"wf.formDesc as formDesc)"+sb.toString(),param.getPage(),param.getRows(),list);
		for(Map r:rows){
			r.put("updateUserName",MyCache.getInstance().getTrueName((String)r.get("updateUserId")));
		}
		data.setRows(rows);
		return data;
		
	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long selectWfFormCount(WfForm wf){
		
		StringBuffer sb=new StringBuffer("select count(*)from WfForm wf where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(wf.getFormName())){
			sb.append(" and wf.formName like ? ");
			list.add("%"+wf.getFormName()+"%");	
		}
		if(wf.getType()!=null){
			sb.append(" and wf.type= ? ");
			list.add(wf.getType());	
		}
		
		return (Long)dao.findUniqueOne(sb.toString(),list);
		
	}
	
	
	public String addWfForm(WfForm wf){
		
		Object obj=dao.findOne("from WfForm where formName=? ",wf.getFormName());
		if(obj==null){
			String id=(String)dao.saveReturnId(wf);
			if(StringUtils.isNotBlank(id)){
				//saveLog("添加表单设计", "表单名称:"+wf.getFormName());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.form.unique";//此表单名称已存在
		}	
		
		
	}
	
	public String updateWfForm(WfForm wf){
		Object obj=dao.findOne("from WfForm where id!=? and formName=? ",wf.getId(),wf.getFormName());
		if(obj==null){
			if(dao.update(wf)){
				saveLog("表单设计", "表单名称:"+wf.getFormName());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.role.unique";//此角色名称已存在
		}
	}
	public boolean deleteWfForm(String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			WfForm wf=dao.get(WfForm.class, id);
			if(wf!=null){
				saveLog("删除表单设计", "删除名称:"+wf.getFormName());
				c.add(wf);
			}
		}
		return dao.deleteAll(c);
	}
	
	
	
	
	
	
	
	
	
	
}
