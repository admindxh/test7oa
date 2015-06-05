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

import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.GroupAddressBook;
import com.oa.manager.personalOffice.bean.PublicAddressBook;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personalOffice.service.IPublicGroupService;

/**
 * 
 * 类名：GroupAddressBookServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-23 下午02:42:07
 *
 */
@Service
public class PublicGroupServiceImpl extends BaseServiceImpl implements IPublicGroupService{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectGroup(PageParam param, PublicGroup g) {
		
		DataGrid data = new DataGrid();

		
		Member me = ServletUtil.getMember();
		StringBuffer sb = new StringBuffer("from PublicGroup p where 1=1");
		List list = new ArrayList();
		// 查询条件
		if (g.getGaName() != null && !"".equals(g.getGaName())) {
			sb.append(" and p.gaName like ? ");
			list.add( "%"+g.getGaName()+"%" );

		}
		
		data.setTotal((Long) dao.findUniqueOne("select count(*)" + sb, list));
		List<PublicGroup> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), list);
		data.setRows(rows);

		return data;
	}



	
	public String addGroup(PublicGroup g) {
		Object obj=dao.findOne("from PublicGroup g where  g.gaName=? ",g.getGaName());
		if(obj==null){
			if (dao.save(g)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.Group.unique";//数据库已有值
		}
	}

	@Override
	public String updateGroup(PublicGroup g) {
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
			List<PublicAddressBook> list=dao.find("from PublicAddressBook where puGrouping=?",id);
			if(list.size()==0){
				PublicGroup g = dao.get(PublicGroup.class, id);
				c.add(g);
				
			}else{
				
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
	public PublicGroup selectGroup(String id) {
		return dao.get(PublicGroup.class, id);
	}
//*********************************安卓**********************************
	/**
	 * id:主键
	 * name:分组名称
	 * remark：描述
	 */
	@Override
	public List<Map<String, Object>> selectPublicGroup(PageParam param) {
		return dao.findPage("select new Map(g.id as id,g.gaName as name,g.gaRemark as remark) from PublicGroup g",param.getPage(),param.getRows());
	}
	@Override
	public Long totlepublicGroup() {
		return (Long) dao.findUniqueOne("select count(*) from PublicGroup ");
	}
}
