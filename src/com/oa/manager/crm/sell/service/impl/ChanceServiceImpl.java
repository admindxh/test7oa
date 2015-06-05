/**  
 * @Project: jtoa
 * @Title: ChanceServiceImpl.java
 * @Package com.oa.manager.crm.sell.service.impl
 * @date 2013-12-11 下午04:16:21
 * @Copyright: 2013 
 */
package com.oa.manager.crm.sell.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
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
import com.oa.commons.util.SearchFilterUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.crm.client.bean.CrmClientLinkman;
import com.oa.manager.crm.client.bean.Info;
import com.oa.manager.crm.sell.bean.Chance;
import com.oa.manager.crm.sell.service.IChanceService;

/**
 * 
 * 类名：ChanceServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-12-11 下午04:16:21
 *
 */
@Service
public class ChanceServiceImpl extends BaseServiceImpl implements IChanceService{

	@Override
	public DataGrid selectChance(PageParam param, Chance c) {
		DataGrid data = new DataGrid();
		
//		StringBuffer sb = new StringBuffer("from Chance c,Info o,CrmClientLinkman n where c.scInfo=o.id and o.id=n.customerId and  c.scStaff=:clStaff ");
		StringBuffer sb=new StringBuffer("from Chance  where  (scStaff like:Staff or scBranch like:Branch) ");
		Map<String, Object> map=new HashMap<String, Object>();	
		Member me = ServletUtil.getMember();
		map.put("Staff", "%"+me.getId()+"%");
		map.put("Branch", "%"+me.getDeptId()+"%");
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(distinct id) " + sb, map));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by scDate desc");
		}
		
		List<Chance> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), map);
		
		for ( Chance r : rows) {
			r.setScSource(MyCache.getInstance().getSelectValue(r.getScSource()));
			r.setScStatus(MyCache.getInstance().getSelectValue(r.getScStatus()));
			Object o=dao.findOne("select clName from Info where id='"+r.getScInfo()+"'");
			if(o!=null){
				r.setInfo(o.toString());
			}else{
				r.setInfo("");
			}
			if(StringUtils.isNotBlank(r.getScLinkman())){
			CrmClientLinkman n=(CrmClientLinkman) dao.findOne("from CrmClientLinkman where id='"+r.getScLinkman()+"'");
			if(n!=null){
				r.setLinkman(n.getLiName());
				r.setPhone(n.getLiTelephone());
			}else{
				r.setLinkman("");
				r.setPhone("");
			}
			}
		}
		data.setRows(rows);

		return data;
	}

	@Override
	public Chance selectID(String id) {
		Chance chance=dao.get(Chance.class,id);
		chance.setInfo(dao.findOne("select clName from Info where id='"+chance.getScInfo()+"'").toString());
		CrmClientLinkman n=(CrmClientLinkman) dao.findOne("from CrmClientLinkman where id='"+chance.getScLinkman()+"'");
		if(n!=null){
			chance.setLinkman(n.getLiName());
			chance.setPhone(n.getLiTelephone());
		}else{
			chance.setLinkman("");
			chance.setPhone("");
		}
		return chance;
	}

	@Override
	public String addChance(Chance c) {
		Object obj=dao.findOne("from Chance  where scInfo=?  and scLinkman=?  and scName=?",c.getScInfo(),c.getScLinkman(),c.getScName());
		if(obj==null){
			Member me = ServletUtil.getMember();
			Timestamp ts = new Timestamp(new Date().getTime());
			c.setScCreate(me.getId());
			c.setScCreateDate(ts);
			if(c.getScBranch()!=""||c.getScStaff()!=""){
				c.setScDate(ts);
			}
			
			if (dao.save(c)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.personal.unique";//数据库已有值
		}
	}

	@Override
	public String updateChance(Chance c) {
		
		if (dao.update(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public boolean deleteChance(String[] ids) {
		for (String id : ids) {
			Chance c = dao.get(Chance.class, id);
			dao.delete(c);
		}
		return true;
	}

	@Override
	public String updateAllocation(String id, String scStaff,String scBranch,String scPicture) {
		Chance c=dao.get(Chance.class, id);
		Timestamp ts = new Timestamp(new Date().getTime());
		c.setScDate(ts);
		c.setScStaff(scStaff);
		c.setScBranch(scBranch);
		c.setScPicture(scPicture);
		if (dao.update(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}



	@Override
	public DataGrid lookUpChance(PageParam param, Chance c) {
	DataGrid data = new DataGrid();
		
//		StringBuffer sb = new StringBuffer("from Chance c,Info o,CrmClientLinkman n where c.scInfo=o.id and o.id=n.customerId and  c.scStaff=:clStaff ");
		StringBuffer sb=new StringBuffer("from Chance  where  1=1 ");
		Map<String, Object> map=new HashMap<String, Object>();	
		Member me = ServletUtil.getMember();
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(distinct id) " + sb, map));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by scDate desc");
		}
		
		List<Chance> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), map);
		
		for ( Chance r : rows) {
			r.setScSource(MyCache.getInstance().getSelectValue(r.getScSource()));
			r.setScStatus(MyCache.getInstance().getSelectValue(r.getScStatus()));
			r.setInfo(dao.findOne("select clName from Info where id='"+r.getScInfo()+"'").toString());
			CrmClientLinkman n=(CrmClientLinkman) dao.findOne("from CrmClientLinkman where id='"+r.getScLinkman()+"'");
			if(n!=null){
				r.setLinkman(n.getLiName());
				r.setPhone(n.getLiTelephone());
			}else{
				r.setLinkman("");
				r.setPhone("");
			}
		}
		data.setRows(rows);

		return data;
	}

}
