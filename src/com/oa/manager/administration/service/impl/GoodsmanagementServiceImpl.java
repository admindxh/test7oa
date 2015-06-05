/**  
 * @Project: jxoa
 * @Title: GoodsmanagementServiceImpl.java
 * @Package com.oa.manager.administration.service.impl
 * @date 2013-6-17 下午4:55:12
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service.impl;

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
import com.oa.manager.administration.bean.BiBasicinformation;
import com.oa.manager.administration.service.IGoodsmanagementService;

/**
 * 
 * 类名：GoodsmanagementServiceImpl
 * 功能：行政办公--物品管理   
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-17 下午4:55:12
 *
 */
@Service
public class GoodsmanagementServiceImpl extends BaseServiceImpl implements IGoodsmanagementService {
		/**
		 * 查询物品管理
		 * @param bi
		 * @param param
		 * @return
		 */
	   @SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid load(BiBasicinformation bi,PageParam param){
		   
		   DataGrid data=new DataGrid();
		   StringBuffer sb=new StringBuffer("from BiBasicinformation b where 1=1");
		   List list=new ArrayList();
		   if(StringUtils.isNotBlank(bi.getUsingname())){
			  sb.append(" and b.usingname like ?");
			  list.add("%"+bi.getUsingname()+"%");
		   }
		   if(StringUtils.isNotBlank(bi.getTypeId())){
			   sb.append(" and b.typeId=?");
			   list.add(bi.getTypeId());
		   }
		   
		   data.setTotal((Long)dao.findOne("select count(*)"+sb.toString(),list));
		   if(StringUtils.isNotBlank(param.getSort())){
			   param.appendOrderBy(sb);//排序
		   }else{
			   sb.append(" order by b.createdate desc ");
		   }
		   
		   
		   List<Map<String,Object>> rows=dao.findPage("select new Map(b.id as id,b.usingname as usingname,b.typeId as typeId,b.specificationstype as specificationstype," +
					"b.danwei as danwei,b.currentinventory as currentinventory,b.userid as userid,b.createdate as createdate) "
					+sb.toString(),param.getPage(),param.getRows(),list);
		   
		   for(Map<String,Object> map:rows){
				map.put("userName",MyCache.getInstance().getTrueName((String)map.get("userid")));//创建人
				map.put("typeId", MyCache.getInstance().getSelectValue((String)map.get("typeId")));//类别   
			}	
		   data.setRows(rows);
		   
		   return data;
	   }
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean deletes(String[] ids){
		List<Object> c=new ArrayList<Object>();
		for(String id:ids){
			BiBasicinformation bi=dao.get(BiBasicinformation.class, id);
			if(bi!=null){
			c.add(bi);
			}
		}
		return dao.deleteAll(c);
	} 
	/**
	 * 更新物品管理时用到
	 */
	public String updateBi(BiBasicinformation bi){
		BiBasicinformation bf=dao.get(BiBasicinformation.class, bi.getId());
		if(bf==null){
			return "msg.update.content";
		}
		bf.setUsingname(bi.getUsingname());
		bf.setTypeId(bi.getTypeId());
		bf.setSpecificationstype(bi.getSpecificationstype());
		bf.setDanwei(bi.getDanwei());
		bf.setRemark(bi.getRemark());
		bf.setCurrentinventory(bi.getCurrentinventory());
		return  MsgConfig.MSG_KEY_SUCCESS;
		
	
	}
}
