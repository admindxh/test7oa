/**  
 * @Project: jxoa
 * @Title: IMeetingService.java
 * @Package com.oa.manager.common_platform.service
 * @date 2013-5-9 上午10:25:16
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service;

import java.util.List;
import java.util.Map;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.common_platform.bean.Room;


/**
 * 
 * 类名：IMeetingService
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-5-9 上午10:25:16
 *
 */
public interface IRoomService extends IBaseService{
	/**
	 * 会议值条件查询
	 * @param param
	 * @param lv
	 * @return
	 */
	public DataGrid selectRoom(PageParam param,Room lv);
	
	/**
	 * 添加会议值
	 * @param lv
	 * @return
	 */
	public String addRoom(Room lv);
	/**
	 * 修改会议值
	 * @param lv
	 * @return
	 */
	public String updateRoom(Room lv);
	/**
	 * 批量删除会议值
	 * @param ids
	 * @return
	 */
	public String deleteRoom(String[] ids);
	/**
	 * 查询出所有会的 id，name
	 * @return
	 */
	public List<Map<String,Object>> selectAllRoom();
	
}
