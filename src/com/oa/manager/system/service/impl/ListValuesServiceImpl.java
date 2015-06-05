/**  
 * @Project: jxoa
 * @Title: ListValuesServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-4-15 下午2:36:49
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.service.IListValuesService;

/**
 * 
 * 类名：ListValuesServiceImpl
 * 功能：字典值管理 业务层实现
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-15 下午2:36:49
 *
 */
@Service
public class ListValuesServiceImpl extends BaseServiceImpl implements IListValuesService{
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectListValues(PageParam param,ListValues lv){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from ListValues lv where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(lv.getListValue())){
			sb.append(" and lv.listValue like ? ");
			list.add("%"+lv.getListValue()+"%");
		}
		if(lv.getListType()!=null){
			sb.append(" and lv.listType = ? ");
			list.add(lv.getListType());	
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		param.appendOrderBy(sb);//排序
		
		data.setRows(dao.findPage(sb.toString(),param.getPage(),param.getRows(),list));
		
		return data;
		
	}

	
	public String addListValues(ListValues lv){
		
		Object obj=dao.findOne("from ListValues where listType=? and  listValue=? ",lv.getListType(),lv.getListValue());
		if(obj==null){
			dao.save(lv);
			if(StringUtils.isNotBlank(lv.getId())){
				
				saveLog("添加字典值", "类型:"+lv.getListType()+",字典值:"+lv.getListValue());
				//删除字典值
				MyCache.getInstance().removeCache(MyCache.TYPE2LISTVALUES,lv.getListType());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.listvalue.unique";//此字典值已有
		}
	}

	public String updateListValues(ListValues lv){
		Object obj=dao.findOne("from ListValues where id!=? and listType=? and  listValue=? ",lv.getId(),lv.getListType(),lv.getListValue());
		if(obj==null){
			if(dao.update(lv)){
				saveLog("修改字典值", "类型:"+lv.getListType()+",字典值:"+lv.getListValue());
				
				//删除缓存
				MyCache.getInstance().removeCache(MyCache.LISTID2NAME,lv.getId());
				MyCache.getInstance().removeCache(MyCache.TYPE2LISTVALUES,lv.getListType());
				
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.listvalue.unique";//此字典值已有
		}
	}
	
	
	public boolean deleteListValues(String[] ids){
		//等待删除的对象集合
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			ListValues lv=dao.get(ListValues.class, id);
			if(lv!=null){
				saveLog("删除字典值", "字典值:"+lv.getListValue());
				c.add(lv);
				
				//删除缓存
				MyCache.getInstance().removeCache(MyCache.LISTID2NAME,lv.getId());
				MyCache.getInstance().removeCache(MyCache.TYPE2LISTVALUES,lv.getListType());
				
			}
			
		}
	
		return dao.deleteAll(c);
	}
	
	public List<ListValues> selectAllListValues(){
		
		return dao.find("from ListValues ");
		
	}
	

	public List selectListByType(Integer type){
		
		return dao.find("from ListValues lv where listType=? ",type);
		
	}
}
