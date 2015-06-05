package com.oa.manager.crm.sell.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.SearchFilterUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.crm.sell.bean.CrmSellActivity;
import com.oa.manager.crm.sell.service.SellActivityService;
@Service
public class SellActivityServiceImpl extends BaseServiceImpl implements SellActivityService{
		
	@Override
	public DataGrid selectActivity(PageParam param, CrmSellActivity activity) {
		
		DataGrid data = new DataGrid();
		StringBuffer sb = new StringBuffer(" from CrmSellActivity where 1=1 ");
		Map<String, Object> map=new HashMap<String, Object>();
		SearchFilterUtil.appendRules(sb,map, param, null);
		if(activity.getChanceid()!=null&&!"".equals(activity.getChanceid())&&!"null".equals(activity.getChanceid())){
			sb.append(" and chanceid='"+activity.getChanceid()+"'");
		}
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb.toString(),map));
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by acCreateTime desc");
		}
		@SuppressWarnings("unchecked")
		List<CrmSellActivity> rows = dao.findPage(sb.toString(),param.getPage(), param.getRows(),map);
		for(CrmSellActivity ac:rows){
			ac.setAcType(MyCache.getInstance().getSelectValue(ac.getAcType()));//活动方式
			ac.setAcDanwei((String)(dao.findOne("select clName from Info where id=?",ac.getAcDanwei())));//客户
		}
		data.setRows(rows);
		return data;
	}
	@Override
	public String addActivity(CrmSellActivity ac) {
		
		ac.setAcCreateMan(ServletUtil.getMember().getId());//创建人
		ac.setAcCreateTime(DateUtil.currentTimestamp());//创建时间
		if(dao.save(ac)){
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
	}
	
}
