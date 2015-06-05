/**  
 * @Project: jtoa
 * @Title: IOnDutyService.java
 * @Package com.oa.manager.personnel.service
 * @date 2013-12-4 下午9:46:40
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.service;

import java.util.Date;

import org.springframework.ui.ModelMap;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.manager.personnel.bean.XtOnduty;

/**
 * 
 * 类名：IOnDutyService
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-12-4 下午2:46:40
 *
 */
public interface IOnDutyService extends IBaseService {

	/**
	 * 保存值班日记
	 * @param onduty
	 * @return
	 */
	public String saveOnDuty(XtOnduty  onduty,String savePath ,FileList files );
	
	/**
	 * 查询值班日记
	 * @param param
	 * @param onduty
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public DataGrid selectOnDuty(PageParam param,XtOnduty onduty, Date startDate,Date endDate);

	/**
	 * 根据id获取值班记录
	 * @param id
	 * @param map
	 * @return
	 */
	public ModelMap  selectOnDutyById(String id,ModelMap map);
	 
	
	/**
	 * 修改值班日记
	 * @param onduty
	 * @return
	 */
	public String updateOnDuty(XtOnduty  onduty,String savePath ,FileList files );
	
	/**
	 * 删除值班记录
	 * @param ids
	 * @return
	 */
	public boolean deleteOnDuty(String[] ids);
	/**
	 * 删除附件
	 * @param id
	 * @param SAVEPATH
	 * @return
	 */
	public String  deleteOnDutyFiles(String id,String SAVEPATH);
	
	/**
	 * 删除值班记录及附件
	 * 
	 * @param ids
	 * @param savePath
	 * @return
	 */
	public boolean deleteXtOnduty(String[] ids,String savePath);
}
