/**
 * 
 */
package com.oa.manager.personnel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.SearchFilterUtil;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personnel.bean.Staff;
import com.oa.manager.personnel.service.IStaffService;

/**
 * 
 * 类名：StaffServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年6月8日 15:23:23
 *
 */
@Service
public class StaffServiceImpl extends BaseServiceImpl implements IStaffService{

	@Override
	public DataGrid selectStaff(PageParam
			param, Staff s,Date staDate,Date endDate,String status) {
		
		DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer("from Staff  where psStatus=:status ");
//		List list = new ArrayList();
		Map map=new HashMap();
		map.put("status", status);
//		list.add(status);
		//条件查询的 还没写
//		if(s.getPsName()!=null&&!"".equals(s.getPsName())){
//			sb.append(" and psName like ? ");
//			list.add("%"+s.getPsName()+"%");
//		}
//		if(s.getPsHomeTown()!=null&&!"".equals(s.getPsHomeTown())){
//			sb.append(" and psHomeTown like ? ");
//			list.add("%" + s.getPsHomeTown() + "%");
//		}
//		if(s.getPsSex()!=null&&!"".equals(s.getPsSex())){
//			
//			sb.append(" and psSex = ? ");
//			list.add( s.getPsSex());
//		}
//		if(staDate!=null&&!"".equals(staDate)){
//			sb.append(" and psEntryDate >= ? ");
//			list.add( staDate);
//		}
//		if(endDate!=null&&!"".equals(endDate)){
//			sb.append(" and psEntryDate <= ? ");
//			list.add( endDate);
//		}
//		
//		if(s.getPsAge()!=null&&s.getPsAge()!=0){
//			sb.append(" and psAge = ? ");
//			list.add( s.getPsAge());
//		}
//		if(s.getPsPresentAddress()!=null&&!"".equals(s.getPsPresentAddress())){
//			sb.append(" and psPresentAddress like ? ");
//			list.add("%" + s.getPsPresentAddress() + "%");
//		}
//		
//		if(s.getPsHomePhone()!=null&&!"".equals(s.getPsHomePhone())){
//			sb.append(" and psHomePhone like ? ");
//			list.add("%" + s.getPsHomePhone() + "%");
//		}
//		
//		if(s.getPsPresentAddress()!=null&&!"".equals(s.getPsPresentAddress())){
//			sb.append(" and psPresentAddress like ? ");
//			list.add("%" + s.getPsPresentAddress() + "%");
//		}
//		
//		if(s.getPsHomePhone()!=null&&!"".equals(s.getPsHomePhone())){
//			sb.append(" and psHomePhone like ? ");
//			list.add("%" + s.getPsHomePhone() + "%");
//		}
//		if(s.getPsPhone()!=null&&!"".equals(s.getPsPhone())){
//			sb.append(" and psPhone like ? ");
//			list.add("%" + s.getPsPhone() + "%");
//		}
//		
//		if(s.getPsOther()!=null&&!"".equals(s.getPsOther())){
//			sb.append(" and psOther like ? ");
//			list.add("%" + s.getPsOther() + "%");
//		}
//		if(s.getPsAccountAddress()!=null&&!"".equals(s.getPsAccountAddress())){
//			sb.append(" and psAccountAddress like ? ");
//			list.add("%" + s.getPsAccountAddress() + "%");
//		}
//		
//		if(s.getPsEthnic()!=null&&!"".equals(s.getPsEthnic())){
//			sb.append(" and psEthnic like ? ");
//			list.add("%" + s.getPsEthnic() + "%");
//		}
//		
//		if(s.getPsCard()!=null&&!"".equals(s.getPsCard())){
//			sb.append(" and psCard like ? ");
//			list.add("%" + s.getPsCard() + "%");
//		}
//		if(s.getPsUserId()!=null&&!"".equals(s.getPsUserId())){
//			sb.append(" and psUserId = ? ");
//			list.add( s.getPsUserId());
//		}
//		if(s.getPsWage()!=null&&!"".equals(s.getPsWage())){
//			sb.append(" and psWage like ? ");
//			list.add("%" + s.getPsWage() + "%");
//		}
//		
//		if(s.getPsSocial()!=null&&!"".equals(s.getPsSocial())){
//			sb.append(" and psSocial like ? ");
//			list.add("%" + s.getPsSocial() + "%");
//		}
//		
//		if(s.getPsEdu()!=null&&!"".equals(s.getPsEdu())){
//			sb.append(" and psEdu like ? ");
//			list.add("%" + s.getPsEdu() + "%");
//		}
//		if(s.getPsSpecialty()!=null&&!"".equals(s.getPsSpecialty())){
//			sb.append(" and psSpecialty like ? ");
//			list.add("%" + s.getPsSpecialty() + "%");
//		}
//		
//		if(s.getPsSchool()!=null&&!"".equals(s.getPsSchool())){
//			sb.append(" and psSchool like ? ");
//			list.add("%" + s.getPsSchool() + "%");
//		}
//		
//		
//		
//		if(s.getPsType()!=null&&!"".equals(s.getPsType())){
//			sb.append(" and psType like ? ");
//			list.add("%" + s.getPsType() + "%");
//		}
//		if(s.getPsPost()!=null&&!"".equals(s.getPsPost())){
//			sb.append(" and psPost like ? ");
//			list.add("%" + s.getPsPost() + "%");
//		}
		
		SearchFilterUtil.appendRules(sb, map, param, null);
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb, map));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by psEntryDate desc");
		}
		
		List<PublicGroup> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), map);
		data.setRows(rows);

		return data;
		
		
		
	}
	
	@Override
	public String addStaff(Staff s) {
		if(s.getPsUserId()!=null&&!"".equals(s.getPsUserId())){
		List l=dao.find("from Staff where psUserId=?",s.getPsUserId());
		if(l.size()>0){
			return "msg.personnel.users";
		}else{
		Object obj=dao.findOne("from Staff  where psName=? and psCard=?",s.getPsName(),s.getPsCard());
		if(obj==null){
			if (dao.save(s)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.personnel.value";//数据库已有值
		}
	}
		}else{
			Object obj=dao.findOne("from Staff  where psName=? and psCard=?",s.getPsName(),s.getPsCard());
			if(obj==null){
				if (dao.save(s)) {
					return MsgConfig.MSG_KEY_SUCCESS;
				} else {
					return MsgConfig.MSG_KEY_FAIL;
				}
			}else{
				return "msg.personnel.value";//数据库已有值
			}
		}
	}

	@Override
	public Map updatePage(String id) {
		Staff staff=(Staff) dao.findOne("from Staff where id=?",id);
		String roleid=staff.getPsUserId();
//		SyUsers role=null;
//			role=(SyUsers) dao.findOne("from SyUsers where id=?",roleid);
		Map map=new HashMap();
		map.put("ps",staff);
		map.put("sta", staff.getPsStatus());
		return map;
	}

	@Override
	public String update(Staff s) {
		if(s.getPsUserId()!=null&&!"".equals(s.getPsUserId())){
		List l=dao.find("from Staff where psUserId=? and id<>?",s.getPsUserId(),s.getId());
		if(l.size()>0){
			return "msg.personnel.users";
		}else{
			if (dao.update(s)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		}
		}else{
			
				if (dao.update(s)) {
					return MsgConfig.MSG_KEY_SUCCESS;
				} else {
					return MsgConfig.MSG_KEY_FAIL;
				}
			
		}
	}
	
	@Override
	public boolean deleteStaff(String[] ids) {
		for (String id : ids) {
			Staff s = dao.get(Staff.class, id);
			dao.delete(s);
		}
		
		return true;
	}
	@Override
	public String updateStatus(Staff s) {
		
		Staff sta=dao.get(Staff.class, s.getId());
		
		if(sta!=null){
			sta.setPsStatus(s.getPsStatus());
			sta.setPsResReason(s.getPsResReason());
			sta.setPsResDate(s.getPsResDate());
			sta.setPsResRemark(s.getPsResRemark());
			sta.setPsRetReason(s.getPsRetReason());
			sta.setPsRetDate(s.getPsRetDate());
			sta.setPsRetRemark(s.getPsRetRemark());
			
			
			return MsgConfig.MSG_KEY_SUCCESS;
			
		}else{
			return MsgConfig.MSG_KEY_NODATA;
		}
	}
}
