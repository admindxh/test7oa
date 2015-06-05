/**  
 * @Project: jxoa
 * @Title: PublicServiceImpl.java
 * @Package com.oa.manager.PublicOffice.addressBook.Public.service.impl
 * @date 2013-5-21 下午02:42:07
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.GroupAddressBook;
import com.oa.manager.personalOffice.bean.PersonalAddressBook;
import com.oa.manager.personalOffice.bean.PublicAddressBook;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personalOffice.service.IPublicAddressBookService;

/**
 * 
 * 类名：PublicAddressBookServiceImpl 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0
 * 日期：2013-5-23 下午02:42:07
 * 
 */
@Service
public class PublicAddressBookServiceImpl extends BaseServiceImpl implements
		IPublicAddressBookService {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectPublic(PageParam param, PublicAddressBook p) {
		DataGrid data = new DataGrid();

		StringBuffer sb = new StringBuffer("from PublicAddressBook  where 1=1 ");
		List list = new ArrayList();
		// 查询条件
		if (p.getPuName() != null && !"".equals(p.getPuName())) {
			sb.append(" and puName like ? ");
			list.add("%" + p.getPuName() + "%");

		}
		if (p.getPuGrouping() != null && !"".equals(p.getPuGrouping())&&!"0".equals(p.getPuGrouping() )) {
			sb.append(" and puGrouping = ? ");
			list.add(p.getPuGrouping());
		}
		if (p.getPuPhone() != null && !"".equals(p.getPuPhone())) {
			sb.append(" and puPhone like ? ");
			list.add("%" + p.getPuPhone() + "%");
		}
		if (p.getPuCompany() != null && !"".equals(p.getPuCompany())) {
			sb.append(" and puCompany like ? ");
			list.add("%" + p.getPuCompany() + "%");
		}
		if (p.getPuOfficPhone() != null && !"".equals(p.getPuOfficPhone())) {
			sb.append(" and puOfficPhone like ?");
			list.add("%" + p.getPuOfficPhone() + "%");
		}
		if (p.getPuHomePhone() != null && !"".equals(p.getPuHomePhone())) {
			sb.append(" and puHomePhone like ?");
			list.add("%" + p.getPuHomePhone() + "%");
		}
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb, list));

		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by puGrouping desc");
		}
		List<PublicAddressBook> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), list);

		 for(PublicAddressBook pa:rows){
		 pa.setPuGrouping(dao.get(PublicGroup.class,pa.getPuGrouping()).getGaName());
		
		
		 }

		data.setRows(rows);

		return data;
	}

	

	public String addPublic(PublicAddressBook p) {
		Object obj = dao.findOne(
				"from PublicAddressBook p where  p.puName=? and p.puPhone=?",
				p.getPuName(), p.getPuPhone());
		if (obj == null) {
			if (dao.save(p)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		} else {
			return "msg.Public.unique";// 数据库已有值
		}
	}

	@Override
	public String updatePublic(PublicAddressBook p) {
		if (dao.update(p)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public boolean deletePublic(String[] ids) {
		for (String id : ids) {
			PublicAddressBook p = dao.get(PublicAddressBook.class, id);
			dao.delete(p);
		}
		return true;
	}

	@Override
	public PublicAddressBook selectPublic(String id) {
		return dao.get(PublicAddressBook.class, id);
	}

	@Override
	public String addExcelPublic(List<PublicAddressBook> list, String group) {
		Collection c = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			PublicAddressBook p = list.get(i);
			p.setPuGrouping(group);
			c.add(p);
		}
		if (dao.saveOrUpdateAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public List<PublicGroup> selectGroup() {
		return dao.find("from PublicGroup");
	}
//*********************************************安卓******************************************************
	/**
	 * id:主键
	 * name:姓名
	 * sex:性别  直接显示即可
	 * phone:手机
	 */
	@Override
	public List<Map<String, Object>> selectPublicBookByGroupId(String groupId,PageParam param) {
		
		return dao.findPage("select new Map(p.id as id,p.puName as name,p.puSex as sex,p.puPhone as phone) from PublicAddressBook p where p.puGrouping=? ",param.getPage(),param.getRows(),groupId);
	}
	@Override
	public Long totlePublicBookByGroupId(String groupId) {
		
		return (Long) dao.findUniqueOne("select count(*) from PublicAddressBook p where p.puGrouping=? ", groupId);
	}
	/**
	 * id:主键
	 * name:姓名
	 * sex:性别  直接显示即可
	 * phone:手机
	 */
	@Override
	public List<Map<String, Object>> selectPublicBookByConditions(String phone,String name,PageParam param) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" from PublicAddressBook p  where 1=1 ");
		if(StringUtils.isNotBlank(phone)){
			sb.append(" and p.puPhone like ? ");
			list.add("%"+phone+"%");
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and p.puName like ? ");
			list.add("%"+name+"%");
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> al = dao.findPage(" select new Map(p.id as id,p.puName as name,p.puSex as sex,p.puPhone as phone)"+sb.toString(),param.getPage(),param.getRows(),list);
		return al;
	}
	@Override
	public Long totlePublicBookByConditions(String phone, String name) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" from PublicAddressBook p  where 1=1 ");
		if(StringUtils.isNotBlank(phone)){
			sb.append(" and p.puPhone like ? ");
			list.add("%"+phone+"%");
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and p.puName like ? ");
			list.add("%"+name+"%");
		}
		return (Long) dao.findUniqueOne("select count(*)"+sb.toString(),list);
	}
	
}
