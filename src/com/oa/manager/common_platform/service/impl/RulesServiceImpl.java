/**  
 * @Project: jxoa
 * @Title: RulesServiceImpl.java
 * @Package com.oa.manager.common_platform.service.impl
 * @date 2013-6-3 上午10:19:19
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.RmRulesmanager;
import com.oa.manager.common_platform.service.IRulesService;

/**
 * 
 * 类名：RulesServiceImpl
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-3 上午10:19:19
 *
 */
@Service
public class RulesServiceImpl extends BaseServiceImpl implements IRulesService {
	/**
	 * 查询法规
	 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public DataGrid load(RmRulesmanager rm,PageParam param){
	DataGrid data=new DataGrid();
	StringBuffer sb=new StringBuffer(" from RmRulesmanager where 1=1");
	List list=new ArrayList();
	if(StringUtils.isNotBlank(rm.getRulesTitle())){
		sb.append(" and rules_title like ?");
		list.add("%"+rm.getRulesTitle()+"%");
	}
	if(StringUtils.isNotBlank(rm.getRulesTypeid())){
		sb.append(" and rules_typeid=?");
		list.add(rm.getRulesTypeid());
	}
	data.setTotal((Long)dao.findOne("select count(*)"+sb,list));
	if(StringUtils.isNotBlank(param.getSort())){
		param.appendOrderBy(sb);//排序
	}else{
		sb.append(" order by createdate desc ");
	}
	
	List<RmRulesmanager> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
	for(RmRulesmanager m:rows){
		m.setAuthor(MyCache.getInstance().getTrueName(m.getAuthor()));//发布人
		m.setRulesTypeid(MyCache.getInstance().getSelectValue(m.getRulesTypeid()));//法规类型
	}	
	data.setRows(rows);
	return data;
}


/**
 * 批量删除法规
 */
public boolean deletes(String[] ids){
	List<Object> c=new ArrayList<Object>();
	for(String id:ids){
		RmRulesmanager rm=dao.get(RmRulesmanager.class, id);
		c.add(rm);
	}
	return dao.deleteAll(c);
}
/**
 * 修改法规
 */
public String updaterule(RmRulesmanager rm){
	RmRulesmanager ra=dao.get(RmRulesmanager.class, rm.getId());
	if(ra!=null){
		try {
			BeanUtils.copyProperties(ra, rm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MsgConfig.MSG_KEY_SUCCESS;
	}
	else{
		return "msg.data.hasdelete";
	}
	
}
}
