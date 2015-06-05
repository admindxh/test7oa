/**  
 * @Project: jxoa
 * @Title: TableCustomServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-6-17 上午11:57:51
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyTableCustom;
import com.oa.manager.system.service.ITableCustomService;

/**
 * 
 * 类名：TableCustomServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-17 上午11:57:51
 *
 */
@Service
public class TableCustomServiceImpl extends BaseServiceImpl implements ITableCustomService{

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectTableCustoms(PageParam param,SyTableCustom t){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from SyTableCustom t where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(t.getTbType()!=null){
			sb.append(" and t.tbType = ? ");
			list.add(t.getTbType());	
		}
		if(StringUtils.isNotBlank(t.getFieldName())){
			sb.append(" and t.fieldName like ? ");
			list.add("%"+t.getFieldName()+"%");
		}
		if(StringUtils.isNotBlank(t.getFieldTitle())){
			sb.append(" and t.fieldTitle like ? ");
			list.add("%"+t.getFieldTitle()+"%");
		}
		if(StringUtils.isNotBlank(t.getFieldAnotherTitle())){
			sb.append(" and t.fieldAnotherTitle like ? ");
			list.add("%"+t.getFieldAnotherTitle()+"%");
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		//排序规则
		if(StringUtils.isNotBlank(param.getSort())){
			
			param.appendOrderBy(sb);//排序
			
		}else{
			sb.append(" order by t.fieldSort asc");
		}
		data.setRows(dao.findPage(sb.toString(),param.getPage(),param.getRows(),list));
		return data;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SyTableCustom> selectTableCustom(short type){
		return dao.find("from SyTableCustom t where t.tbType=? order by t.fieldSort asc",type);
	}
	
	public String addTableCustom(SyTableCustom tc){
		
		Object obj=dao.findOne("from SyTableCustom t where t.tbType=? and  t.fieldName=? ",tc.getTbType(),tc.getFieldName());
		if(obj==null){
			return dao.save(tc)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		}else{
			return "msg.SyTableCustom.unique";//已有此字段
		}
	}
	public String updateDevTableCustom(SyTableCustom tc){
		Object obj=dao.findOne("from SyTableCustom t where t.tbType=? and  t.fieldName=? and t.id!=?",tc.getTbType(),tc.getFieldName(),tc.getId());
		if(obj==null){
			return dao.update(tc)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;
		}else{
			return "msg.SyTableCustom.unique";//已有此字段
		}
	}
	public String updateTableCustom(SyTableCustom tc){
		SyTableCustom oldtc=dao.get(SyTableCustom.class,tc.getId());
		if(oldtc==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		oldtc.setFieldSort(tc.getFieldSort());
		oldtc.setFieldAnotherTitle(tc.getFieldAnotherTitle());
		oldtc.setIsExport(tc.getIsExport());
		oldtc.setIsPrint(tc.getIsPrint());
		//oldtc.setIsShow(tc.getIsShow());
		
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	public boolean deleteTableCustom(String[] ids){
	
		for(String id:ids){
			dao.delete("delete SyTableCustom where id=?",id);
		}
	
		return true;
	}
	
}
