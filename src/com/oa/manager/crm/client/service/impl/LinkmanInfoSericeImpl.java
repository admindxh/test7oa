package com.oa.manager.crm.client.service.impl;

import java.util.ArrayList;
import java.util.Collection;
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
import com.oa.manager.crm.client.bean.CrmClientLinkman;
import com.oa.manager.crm.client.bean.Info;
import com.oa.manager.crm.client.service.ILinkmanInfoService;
@Service
public class LinkmanInfoSericeImpl extends BaseServiceImpl implements ILinkmanInfoService {
		
	@Override
	public DataGrid selectLinkmanInfo(PageParam param, CrmClientLinkman linkman) {
		DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer(" from CrmClientLinkman l,Info f where l.customerId=f.id ");
		Map<String, Object> map=new HashMap<String, Object>();
		if(linkman.getCustomerId()!=null&&!"".equals(linkman.getCustomerId())&&!"null".equals(linkman.getCustomerId())){
			sb.append(" and l.customerId='"+linkman.getCustomerId()+"'");
		}
		
		
		SearchFilterUtil.appendRules(sb,map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb.toString(),map));
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by l.createtime,l.liType,l.liSpecies desc");
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> rows = dao.findPage("select new Map(l.id as id,l.liName as name,l.liSex as sex,l.liType as type,l.liSpecies as species,"
				+"l.liPost as post,l.liTelephone as phone,l.createMan as createman,"
				+"l.createtime as createtime,f.clName as customerName)"
				+sb.toString(),param.getPage(), param.getRows(),map);
		for(Map<String,Object> m:rows){
			m.put("userName",MyCache.getInstance().getTrueName((String)(m.get("createman"))));//创建人
			m.put("manType",MyCache.getInstance().getSelectValue((String)(m.get("type"))));//类型
			m.put("manSpecies",MyCache.getInstance().getSelectValue((String)(m.get("species"))));//种类
		}
		data.setRows(rows);
		return data ;
	}
	@Override
	public String addLinkman(CrmClientLinkman linkman){
		
		linkman.setCreateMan(ServletUtil.getMember().getId());//创建人
		linkman.setCreatetime(DateUtil.currentTimestamp());//创建时间
		if(dao.save(linkman)){
			return MsgConfig.MSG_KEY_SUCCESS;
			
		}else{
			return MsgConfig.MSG_KEY_FAIL;
		}
	}
	@Override
	public boolean deleteLinkman(String[] ids) {
		List<CrmClientLinkman> list = new ArrayList<CrmClientLinkman>(); 
		for(String id :ids){
			CrmClientLinkman linkman = dao.get(CrmClientLinkman.class, id);
			list.add(linkman);
		}
		return dao.deleteAll(list);
	}
	@Override
	public String updateLinkman(CrmClientLinkman linkman) {
		CrmClientLinkman man = dao.get(CrmClientLinkman.class,linkman.getId());//更新前的联系人
		if(man==null){
			return "msg.update.content";//数据已不存在
		}
		man.setLiName(linkman.getLiName());//姓名
		man.setLiSex(linkman.getLiSex());//性别
		man.setLiType(linkman.getLiType());//类型
		man.setLiSpecies(linkman.getLiSpecies());//种类
		man.setCustomerId(linkman.getCustomerId());//客户ID
		man.setLiBusiness(linkman.getLiBusiness());//负责业务
		man.setLiDept(linkman.getLiDept());//部门
		man.setLiPost(linkman.getLiPost());//职务
		man.setLiTelephone(linkman.getLiTelephone());//电话
		man.setLiAppellation(linkman.getLiAppellation());//称谓
		man.setLiEmail(linkman.getLiEmail());//邮箱
		man.setLiFax(linkman.getLiFax());//传真
		man.setLiMobilePhone(linkman.getLiMobilePhone());//手机
		man.setLiMsnQq(linkman.getLiMsnQq());//msn/qq
		man.setLiBirthday(linkman.getLiBirthday());//出生日期
		man.setLiZipCode(linkman.getLiZipCode());//邮编
		man.setLiHomeaddress(linkman.getLiHomeaddress());//家庭住址
		man.setLiHomephone(linkman.getLiHomephone());//家庭电话
		man.setRemark(linkman.getRemark());//备注
		man.setUpdateMan(ServletUtil.getMember().getId());//最后修改人id
		man.setUpdatetime(DateUtil.currentTimestamp());//最后修改时间
		if(dao.update(man)){
			return MsgConfig.MSG_KEY_SUCCESS;
		}else{
			return 	MsgConfig.MSG_KEY_FAIL;
		}
	}
}
