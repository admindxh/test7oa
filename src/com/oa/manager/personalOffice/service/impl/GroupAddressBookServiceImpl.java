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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.GroupAddressBook;
import com.oa.manager.personalOffice.bean.PersonalAddressBook;
import com.oa.manager.personalOffice.service.IGroupAddressBookService;

/**
 * 
 * 类名：GroupAddressBookServiceImpl 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2013-5-23
 * 下午02:42:07
 * 
 */
@Service
public class GroupAddressBookServiceImpl extends BaseServiceImpl implements
		IGroupAddressBookService {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectGroup(PageParam param, GroupAddressBook g) {
		DataGrid data = new DataGrid();

		Member me = ServletUtil.getMember();
		StringBuffer sb = new StringBuffer(
				"from GroupAddressBook p where p.grUid=? ");
		List list = new ArrayList();
		list.add(me.getId());
		// 查询条件
		if (g.getGrName() != null && !"".equals(g.getGrName())) {
			sb.append(" and p.grName like ? ");
			list.add("%"+ g.getGrName()+"%" );

		}
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb, list));
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {

//			sb.append(" order by p.grNo ");

		}
		List<GroupAddressBook> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), list);

		data.setRows(rows);

		return data;
	}

	

	public String addGroup(GroupAddressBook g) {
		// Object
		// obj=dao.findOne("from Public p where  p.puName=? and p.puPhone=?",p.getPuName(),p.getPuPhone());
		Object obj = dao.findOne(
				"from GroupAddressBook g where  g.grName=? and g.grUid=?",
				g.getGrName(), g.getGrUid());
		if (obj == null) {
			Member me = ServletUtil.getMember();
			g.setGrUid(me.getId());
			if (dao.save(g)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		} else {
			return "msg.Group.unique";// 数据库已有值
		}
	}

	@Override
	public String updateGroup(GroupAddressBook g) {
		if (dao.update(g)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public String deleteGroup(String[] ids) {
		Collection c = new ArrayList();
		for (String id : ids) {
			List<PersonalAddressBook> list = dao.find(
					"from PersonalAddressBook where peGrouping=?", id);
			if (list.size() == 0) {
				GroupAddressBook g = dao.get(GroupAddressBook.class, id);
				c.add(g);

			} else {
				GroupAddressBook g = dao.get(GroupAddressBook.class, id);
				return "msg.group.delete";
			}
		}
		if (dao.deleteAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public GroupAddressBook selectGroup(String id) {
		return dao.get(GroupAddressBook.class, id);
	}
//***************************************安卓****************************************
	/**
	 * id :主键
	 * name:分组名称
	 * remark:描述
	 */
	
	@Override
	public List<Map<String, Object>> selectAllPersonGroup(PageParam param) {
		
		return dao.findPage("select new Map(a.id as id,a.grName as name,a.grRemark as remark) from GroupAddressBook a where a.grUid=? ",param.getPage(),param.getRows(),"402881f73e1c4ba4013e1c4c08470001");//ServletUtil.getMember().getId()
	}
	@Override
	public Long totlePersonGroup() {
		
		return (Long) dao.findUniqueOne("select count(*) from GroupAddressBook a where a.grUid=? ","402881f73e1c4ba4013e1c4c08470001");//
	}
	
}
