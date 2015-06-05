/**  
 * @Project: jtoa
 * @Title: JobServiceImpl.java
 * @Package com.oa.manager.personnel.service.impl
 * @date 2013-10-9 下午02:04:25
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personnel.bean.Job;
import com.oa.manager.personnel.service.IJobService;

/**
 * 
 * 类名：JobServiceImpl
 * 功能：
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013-10-9 下午02:04:25
 *
 */
@Service
public class JobServiceImpl extends BaseServiceImpl implements IJobService {

	@Override
	public DataGrid selectJob(PageParam param, Job j) {
		DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer("from Job where 1=1");
		List list = new ArrayList();
		
		if(j.getPjName()!=null&&!"".equals(j.getPjName())){
			sb.append(" and pjName like ? ");
			list.add("%"+j.getPjName()+"%");
		}
		if(j.getPjEducation()!=null&&!"".equals(j.getPjEducation())){
			sb.append(" and pjEducation = ? ");
			list.add(j.getPjEducation() );
		}
		if(j.getPjPost()!=null&&!"".equals(j.getPjPost())){
			sb.append(" and pjPost = ? ");
			list.add(j.getPjPost() );
		}
		if(j.getPjJobStatus()!=null&&!"".equals(j.getPjJobStatus())){
			sb.append(" and pjJobStatus = ? ");
			list.add(j.getPjJobStatus() );
		}
		
		data.setTotal((Long) dao.findUniqueOne("select count(*) " + sb, list));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by pjDate desc");
		}
		
		List<PublicGroup> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), list);
		data.setRows(rows);

		return data;
	}

	@Override
	public String add(Job j) {
		if (dao.save(j)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	

	@Override
	public String update(Job s) {
		if (dao.update(s)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public boolean delete(String[] ids) {
		for (String id : ids) {
			Job j = dao.get(Job.class, id);
			dao.delete(j);
		}
		
		return true;
	}

	@Override
	public Job selectID(String id) {
		return dao.get(Job.class, id);
	}

}
