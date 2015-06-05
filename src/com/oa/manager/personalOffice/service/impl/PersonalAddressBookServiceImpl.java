/**  
 * @Project: jxoa
 * @Title: PersonalServiceImpl.java
 * @Package com.oa.manager.personalOffice.addressBook.Personal.service.impl
 * @date 2013-5-21 下午02:42:07
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.oa.manager.personalOffice.service.IPersonalAddressBookService;
import com.oa.manager.system.bean.ListValues;

/**
 * 
 * 类名：PersonalAddressBookServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-21 下午02:42:07
 *
 */
@Service
public class PersonalAddressBookServiceImpl extends BaseServiceImpl implements IPersonalAddressBookService{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectPersonal(PageParam param, PersonalAddressBook p) {
		
		DataGrid data=new DataGrid();
		
		Member me = ServletUtil.getMember();
		StringBuffer sb = new StringBuffer("from PersonalAddressBook  where peUid=? ");
		List list = new ArrayList();
		list.add(me.getId());
		// 查询条件
		
		if (p.getPeName() != null && !"".equals(p.getPeName())) {
			sb.append(" and peName like ? ");
			list.add("%"+p.getPeName()+"%");

		}
		if (p.getPeGrouping() != null && !"".equals(p.getPeGrouping())&&!"0".equals(p.getPeGrouping())) {
			sb.append(" and peGrouping = ? ");
			list.add( p.getPeGrouping());

		}
		if( p.getPePhone()!= null && !"".equals(p.getPePhone())){
			sb.append(" and pePhone like ? ");
			list.add("%"+p.getPePhone()+"%");
		}
		if(p.getPeCompany()!= null && !"".equals(p.getPeCompany())){
			sb.append(" and peCompany like ? ");
			list.add("%"+p.getPeCompany()+"%");
		}
		if(p.getPeOfficPhone()!= null && !"".equals(p.getPeOfficPhone())){
			sb.append(" and peOfficPhone like ?");
			list.add("%"+p.getPeOfficPhone()+"%");
		}
		if(p.getPeHomePhone()!= null && !"".equals(p.getPeHomePhone())){
			sb.append(" and peHomePhone like ?");
			list.add("%"+p.getPeHomePhone()+"%");
		}
		
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		
		if (StringUtils.isNotBlank(param.getSort())) {
			
			param.appendOrderBy(sb);//排序

		} else {
			sb.append(" order by peGrouping desc");
		}
		List<PersonalAddressBook> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		
		for(PersonalAddressBook pa:rows){
			pa.setPeGrouping(dao.get(GroupAddressBook.class,pa.getPeGrouping()).getGrName());
			
			
			}
		
		
		data.setRows(rows);
		
		return data;
	}


	
	public String addPersonal(PersonalAddressBook p) {
		Member me = ServletUtil.getMember();
		Object obj=dao.findOne("from PersonalAddressBook p where p.peUid=?  and p.peName=? and p.pePhone=?",me.getId(),p.getPeName(),p.getPePhone());
		if(obj==null){
			p.setPeUid(me.getId());
			if (dao.save(p)) {
				return MsgConfig.MSG_KEY_SUCCESS;
			} else {
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.personal.unique";//数据库已有值
		}
	}

	@Override
	public String updatePersonal(PersonalAddressBook p) {
		if (dao.update(p)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public boolean deletePersonal(String[] ids) {
		for (String id : ids) {
			PersonalAddressBook p = dao.get(PersonalAddressBook.class, id);
			dao.delete(p);
		}
		return true;
	}

	@Override
	public PersonalAddressBook selectPersonal(String id) {
		return dao.get(PersonalAddressBook.class, id);
	}

	@Override
	public List selectGroup() {
		Member me = ServletUtil.getMember();
		return dao.find("from GroupAddressBook p where p.grUid=? order by p.grNo ",me.getId());
	}

	//*************************************安卓**************************************
	/**
	 * id :主键
	 * name：姓名
	 * sex：性别    男  女 直接展示即可
	 * phone：手机
	 */
	@Override
	public List<Map<String, Object>> queryPersonByGroup(String groupId,PageParam page) {
		
		return dao.findPage("select new Map(p.id as id,p.peName as name,p.peSex as sex,p.pePhone as phone) from PersonalAddressBook p where p.peGrouping=? ",page.getPage(),page.getRows(), groupId);
	}
	@Override
	public Long totlePersonByGroup(String groupId) {
		
		return (Long) dao.findUniqueOne("select count(*) from PersonalAddressBook p where p.peGrouping=? ",groupId);
	}
	/**
	 * id :主键
	 * name：姓名
	 * sex：性别    男  女 直接展示即可
	 * phone：手机
	 */
	@Override
	public List<Map<String, Object>>  selectPersonByConditions(String phone,String name,PageParam param) {
		
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" from PersonalAddressBook p  where 1=1 ");
		if(StringUtils.isNotBlank(phone)){
			sb.append(" and p.pePhone like ? ");
			list.add("%"+phone+"%");
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and p.peName like ? ");
			list.add("%"+name+"%");
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> al = dao.findPage(" select new Map(p.id as id,p.peName as name,p.peSex as sex,p.pePhone as phone)"+sb.toString(),param.getPage(),param.getRows(),list);
		return al;
	}
	@Override
	public Long totlePersonBookByConditions(String phone, String name) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" from PersonalAddressBook p  where 1=1 ");
		if(StringUtils.isNotBlank(phone)){
			sb.append(" and p.pePhone like ? ");
			list.add("%"+phone+"%");
		}
		if(StringUtils.isNotBlank(name)){
			sb.append(" and p.peName like ? ");
			list.add("%"+name+"%");
		}
		return (Long) dao.findUniqueOne("select count(*)"+sb.toString(), list);
	}
	
}
