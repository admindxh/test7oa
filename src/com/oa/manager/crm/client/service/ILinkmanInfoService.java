package com.oa.manager.crm.client.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.crm.client.bean.CrmClientLinkman;

public interface ILinkmanInfoService extends IBaseService{
	/**
	 * 查询客户联系人信息	
	 * @param param
	 * @param linkman  联系人对象
	 * @return
	 */
	public DataGrid selectLinkmanInfo(PageParam param,CrmClientLinkman linkman);
	/**
	 * 增加联系人
	 * @param linkman
	 * @return
	 */
	public String addLinkman(CrmClientLinkman linkman);
	/**
	 * 删除客户联系人
	 * @param ids  联系人id数组
	 * @return
	 */
	public boolean deleteLinkman(String [] ids);
	/**
	 * 修改联系人
	 * @param linkman
	 * @return
	 */
	public String updateLinkman(CrmClientLinkman linkman);
	
}
