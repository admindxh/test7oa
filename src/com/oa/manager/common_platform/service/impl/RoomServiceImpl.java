/**  
 * @Project: jxoa
 * @Title: MeetingServiceImpl.java
 * @Package com.oa.manager.common_platform.service.impl
 * @date 2013-5-9 上午10:29:47
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.Room;
import com.oa.manager.common_platform.service.IRoomService;


/**
 * 
 * 类名：MeetingServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-9 上午10:29:47
 *
 */
@Service(value="roomServiceImpl")
public class RoomServiceImpl extends BaseServiceImpl implements IRoomService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid selectRoom(PageParam param, Room lv) {
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from Room lv where 1=1 ");
		List list=new ArrayList();
		//查询条件
		if(StringUtils.isNotBlank(lv.getMrName())){
			sb.append(" and lv.mrName like ? ");
			list.add("%"+lv.getMrName()+"%");
		}
		data.setTotal((Long)dao.findUniqueOne("select count(*)"+sb,list));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);//排序

		} else {
			sb.append(" order by mrNo ");
		}
		
		List<Room> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		

		data.setRows(rows);
		
		return data;
		
	}

	

	@Override
	public String addRoom(Room lv) {
		Object obj=dao.findOne("from Room where  mrName=?  ",lv.getMrName());
		if(obj==null){
			
			if(dao.save(lv)){
//				saveLog("添加会议室", "名称:"+lv.getMrName()+",容纳人数:"+lv.getMrNum());
				return MsgConfig.MSG_KEY_SUCCESS;
			}else{
				return MsgConfig.MSG_KEY_FAIL;
			}
		}else{
			return "msg.room.unique";//已有该会议室
		}
	}

	@Override
	public String updateRoom(Room r) {
		if (dao.update(r)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public String deleteRoom(String[] ids) {
		boolean boo = false;
		for (String id : ids) {
			List list=dao.find("from Meeting where MRid=?",id);
			if(list.size()>0){
				
				return "msg.room.del";
			}else{
				Room r = dao.get(Room.class, id);
				dao.delete(r);
				return MsgConfig.MSG_KEY_SUCCESS;
			}
		}
		return MsgConfig.MSG_KEY_FAIL;
	}
	@Override
	public List<Map<String,Object>> selectAllRoom() {
		
		return dao.find("select new Map(m.id as id,m.mrName as name)from Room m");
	}

}
