/**  
 * @Project: jxoa
 * @Title: PurchasegoodsServiceImpl.java
 * @Package com.oa.manager.administration.service.impl
 * @date 2013-6-18 下午2:41:51
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
import com.oa.commons.model.PageParam;
import com.oa.manager.administration.bean.BiBasicinformation;
import com.oa.manager.administration.bean.BiShopping;
import com.oa.manager.administration.service.IPurchasegoodsService;

/**
 * 
 * 类名：PurchasegoodsServiceImpl
 * 功能：
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-18 下午2:41:51
 *
 */
@Service
public class PurchasegoodsServiceImpl extends BaseServiceImpl implements IPurchasegoodsService {
	/**
	 * 查询物品购买清单
	 * @param bs
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid load(BiShopping bs,PageParam param,Date startDate,Date endDate){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer(" from BiShopping bs, BiBasicinformation bi where bs.usingnameId=bi.id ");
		List list=new ArrayList();
		if(StringUtils.isNotBlank(bs.getUsingnameId())){
			sb.append(" and bi.usingname like ?");
			list.add("%"+bs.getUsingnameId()+"%");
		}
		if(StringUtils.isNotBlank(bs.getPurchaser())){
			sb.append(" and bs.purchaser = ?");
			list.add(bs.getPurchaser());
		}
		if(StringUtils.isNotBlank(bs.getUserid())){
			sb.append(" and bs.userid = ?");
			list.add(bs.getUserid());
		}
		if(startDate!=null){
			sb.append(" and bs.createdate >=? ");
			list.add(startDate);	
		}
		if(endDate!=null){
			sb.append(" and bs.createdate <=? ");
			list.add(endDate);	
		}
		
		data.setTotal((Long)dao.findOne("select count(*)"+sb.toString(),list));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by bs.createdate desc ");
		}
		
		 List<Map<String,Object>> rows=dao.findPage("select new Map(bs.id as id,bs.usingnameId as usingnameId,bi.usingname as name,bs.purchaser as purchaser,bs.unitprice as unitprice," +
					"bs.amount as amount,bs.totalmonety as totalmonety,bs.userid as userid,bs.createdate as createdate,bs.buyTime as buyTime) "
					+sb.toString(),param.getPage(),param.getRows(),list);
		 for(Map<String,Object> map:rows){
				
		 	map.put("buyName",MyCache.getInstance().getTrueName((String)map.get("purchaser")));//创建人
			
		}	
		 data.setRows(rows);
		 return data;
	}
	
	/**
	 * 增加物品购买
	 */            
     public String saveshopping(BiShopping bs){
    	 
    	  BiBasicinformation bi=dao.get(BiBasicinformation.class,bs.getUsingnameId());
    	 
    	  bi.setCurrentinventory(bi.getCurrentinventory()+bs.getAmount());
    	
    	
    	  if(dao.save(bs)){
    		 
    		 return MsgConfig.MSG_KEY_SUCCESS; 
    	 }else{
    		 return MsgConfig.MSG_KEY_FAIL;
    	 }
    	  
     }
     
     public boolean deletes(String[] ids){
    	 List<Object> list=new ArrayList<Object>();
    	 for(String id:ids){
    		 BiShopping bs=dao.get(BiShopping.class, id);
    		 if(bs!=null){
    		 list.add(bs);
    		 }
    	 }
    	 return dao.deleteAll(list);
     }
     /** 
      * 修改物品购买
      */
     public String  updateshop(BiShopping bs){
    	 BiShopping bp=dao.get(BiShopping.class,bs.getId());//原购买信息
    	 if(bp==null){
    		 return "msg.update.content";
    	 }
    	 BiBasicinformation bi=dao.get(BiBasicinformation.class, bp.getUsingnameId());//物品信息
    	 
    	 bi.setCurrentinventory(bi.getCurrentinventory()-bp.getAmount()+bs.getAmount());//修改物品库存
    	 
    	 //修改购买记录
    	 bp.setUnitprice(bs.getUnitprice());
    	 bp.setPurchaser(bs.getPurchaser());
    	 bp.setAmount(bs.getAmount());
    	 bp.setTotalmonety(bs.getUnitprice()*bs.getAmount());
    	 bp.setBuyTime(bs.getBuyTime());
    	 
    	 return MsgConfig.MSG_KEY_SUCCESS;
    	
    	 	
     }
    
    
}
