/**  
 * @Project: jxoa
 * @Title: GoodsreceivingImpl.java
 * @Package com.oa.manager.administration.service.impl
 * @date 2013-6-18 下午2:44:20
 * @Copyright: 2013 
 */
package com.oa.manager.administration.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.bean.BiBasicinformation;
import com.oa.manager.administration.bean.BiGoodsreceiving;
import com.oa.manager.administration.service.IGoodsreceivingService;

/**
 * 
 * 类名：GoodsreceivingImpl
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-18 下午2:44:20
 *
 */
@Service
public class GoodsreceivingServiceImpl extends BaseServiceImpl implements IGoodsreceivingService {
	/**
	 * 查询物品领用记录
	 * @param bv
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid load(BiGoodsreceiving bv,PageParam param,Date startDate,Date endDate){ 
		
		DataGrid data=new DataGrid();
		
		StringBuffer sb=new StringBuffer(" from BiGoodsreceiving bv, BiBasicinformation bi where bv.usingnameid=bi.id ");
		List list= new ArrayList();
		if(StringUtils.isNotBlank(bv.getUsingnameid())){
			sb.append(" and bi.usingname like ?");
			list.add("%"+bv.getUsingnameid()+"%");
		}
		if(StringUtils.isNotBlank(bv.getRecipients())){
			sb.append(" and  bv.recipients  = ?");
			list.add(bv.getRecipients());
		}
		if(startDate!=null){
			sb.append(" and bv.createdate >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and bv.createdate <=? ");
			list.add(endDate);	
		}
		 data.setTotal((Long)dao.findOne("select count(*)"+sb.toString(),list));
		 if(StringUtils.isNotBlank(param.getSort())){
			 param.appendOrderBy(sb);//排序
		 }else{
			 sb.append(" order by bv.createdate desc ");
		 }
		 
		 List<Map<String,Object>> rows=dao.findPage("select new Map(bv.id as id,bv.usingnameid as usingnameid,bi.usingname as usingname,bv.usenumber as usenumber," +
					"bv.userid as userid,bv.createdate as createdate,bv.recipients as recipients) "+sb.toString(),param.getPage(),param.getRows(),list);
		 for(Map<String,Object> map:rows){
				map.put("userName",MyCache.getInstance().getTrueName((String)map.get("recipients")));//创建人
				map.put("deptName",MyCache.getInstance().getDeptNameByUserId((String)map.get("recipients")));
		 }	
		 data.setRows(rows);
		  
		return data ;
	}
	
	/**
	 * 增加物品领用
	 */
	public String savereceving(BiGoodsreceiving bv){
		
		
		Member me=ServletUtil.getMember();
		bv.setUserid(me.getId());
		bv.setCreatedate(DateUtil.currentTimestamp());
		BiBasicinformation bi=dao.get(BiBasicinformation.class, bv.getUsingnameid());
		if(bi.getCurrentinventory()>=bv.getUsenumber()){
			
			bi.setCurrentinventory(bi.getCurrentinventory()-bv.getUsenumber());
		
			dao.save(bv);
		    return   MsgConfig.MSG_KEY_SUCCESS; 
		}else{
			 return "msg.basicinformation.currentinventory";
		}
		
	}
	
	/**
	 * 批量删除物品领用
	 */
	public String deletes(String[] ids){
		List<Object> list=new ArrayList<Object>();
		for(String id:ids){
			BiGoodsreceiving bv=dao.get(BiGoodsreceiving.class, id);
			if(bv!=null){
			list.add(bv);
			}
		}
		if(dao.deleteAll(list)){
			return MsgConfig.MSG_KEY_SUCCESS;
		}
		else{
			return MsgConfig.MSG_KEY_FAIL;
		}
	}
	/**
	 * 修改物品领用信息
	 * @param bv
	 * @return
	 */
	public String updatereceving(BiGoodsreceiving bv){
		BiGoodsreceiving bs=dao.get(BiGoodsreceiving.class, bv.getId());
		if(bs==null){
			return MsgConfig.MSG_KEY_NODATA;
		}
		BiBasicinformation bi=dao.get(BiBasicinformation.class, bs.getUsingnameid());
		
	    int kc=bi.getCurrentinventory()+bs.getUsenumber()-bv.getUsenumber();
	
	    if(kc>=0){
		    bi.setCurrentinventory(kc);
		    bs.setUsenumber(bv.getUsenumber());
		    bs.setRecipients(bv.getRecipients());
		    bs.setReceiveTime(bv.getReceiveTime());
		    
	        return   MsgConfig.MSG_KEY_SUCCESS; 
	  	}else{
	  		return "msg.basicinformation.currentinventory";
	  	}
	}
}
