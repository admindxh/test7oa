/**  
 * @Project: jtoa
 * @Title: JobServiceImpl.java
 * @Package com.oa.manager.personnel.service.impl
 * @date 2013-10-9 下午02:04:25
 * @Copyright: 2013 
 */
package com.oa.manager.personnel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.manager.personalOffice.bean.EmFiles;
import com.oa.manager.personalOffice.bean.EmInboxFile;
import com.oa.manager.personalOffice.bean.EmOutboxFile;
import com.oa.manager.personalOffice.bean.PublicGroup;
import com.oa.manager.personnel.bean.Deal;
import com.oa.manager.personnel.bean.Job;
import com.oa.manager.personnel.bean.PeFile;
import com.oa.manager.personnel.bean.Staff;
import com.oa.manager.personnel.service.IDealService;
import com.oa.manager.personnel.service.IJobService;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.bean.SyUsers;

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
public class DealServiceImpl extends BaseServiceImpl implements IDealService {

	@Override
	public DataGrid select(PageParam param, Deal d) {
		DataGrid data = new DataGrid();
		
		StringBuffer sb = new StringBuffer("from Deal where 1=1");
		List list = new ArrayList();
		
		if(d.getPdName()!=null&&!"".equals(d.getPdName())){
			sb.append(" and pdName like ? ");
			list.add("%"+d.getPdName()+"%");
		}
		if(d.getPdUid()!=null&&!"".equals(d.getPdUid())){
			sb.append(" and pdUid = ? ");
			list.add(d.getPdUid() );
		}
		if(d.getPdType()!=null&&!"".equals(d.getPdType())){
			sb.append(" and pdType = ? ");
			list.add(d.getPdType() );
		}
		if(d.getPdStatus()!=null&&!"".equals(d.getPdStatus())){
			sb.append(" and pdStatus = ? ");
			list.add(d.getPdStatus() );
		}
		
		
		if(d.getPdStart()!=null&&!"".equals(d.getPdStart())){
			sb.append(" and pdStart>=? ");
			list.add(d.getPdStart() );
		}
		if(d.getPdEnd()!=null&&!"".equals(d.getPdEnd())){
			sb.append(" and pdEnd <=? ");
			list.add(d.getPdEnd() );
		}
		data.setTotal((Long) dao.findUniqueOne("select count(*) " + sb, list));
		
		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by pdDate desc");
		}
		
		List<Deal> rows = dao.findPage(sb.toString(),
				param.getPage(), param.getRows(), list);
		
		
		for ( Deal r : rows) {

			if (r.getPdUid() != null
					&& !"".equals(r.getPdUid())) {
				r.setPdUid(MyCache.getInstance().getTrueName(r.getPdUid()));
				
				
					r.setPdType(MyCache.getInstance().getSelectValue(r.getPdType()));
				
					r.setPdStatus(MyCache.getInstance().getSelectValue(r.getPdStatus()));
				
				r.setPdStaffId(MyCache.getInstance().getTrueName(r.getPdStaffId()));
				
			}
		}
		
		data.setRows(rows);

		return data;
		
	}

	@Override
	public String add(Deal d,String savePath,HttpServletRequest request,FileList files) {
		
		// 上传文件
		List<MultipartFile> fileList = files.getFile();
//		先保存文件
		String did=(String)dao.saveReturnId(d);
		// 等待要保存的附件对象集合
		List<Object> c = new ArrayList<Object>();
		if (fileList != null) {
			for (MultipartFile f : fileList) {
				if (f != null && !f.isEmpty()) {
					String uuid = FileUtils.getUUID();// uuid作为保存时的文件名
					String ext = FileUtils.getFileExt(f.getOriginalFilename());// 获取文件后缀
					// 保存文件
					File newFile = new File(savePath + "/" + uuid + "." + ext);

					try {
						f.transferTo(newFile);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					PeFile pf=new PeFile();
					pf.setPeName(f.getOriginalFilename());
					pf.setPeNewName(uuid);
					pf.setPeExt(ext);
					pf.setPeSize((int)f.getSize());
					pf.setPeDid(did);
					c.add(pf);
				}
			}

		}
		
		if (dao.saveOrUpdateAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	

	@Override
	public String update(Deal d,String savePath,HttpServletRequest request,FileList files) {
		
		
		// 上传文件
		List<MultipartFile> fileList = files.getFile();
//		先保存文件
	
		// 等待要保存的附件对象集合
		List<Object> c = new ArrayList<Object>();
		c.add(d);
		if (fileList != null) {
			for (MultipartFile f : fileList) {
				if (f != null && !f.isEmpty()) {
					String uuid = FileUtils.getUUID();// uuid作为保存时的文件名
					String ext = FileUtils.getFileExt(f.getOriginalFilename());// 获取文件后缀
					// 保存文件
					File newFile = new File(savePath + "/" + uuid + "." + ext);

					try {
						f.transferTo(newFile);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					PeFile pf=new PeFile();
					pf.setPeName(f.getOriginalFilename());
					pf.setPeNewName(uuid);
					pf.setPeExt(ext);
					pf.setPeSize((int)f.getSize());
					pf.setPeDid(d.getId());
					c.add(pf);
				}
			}

		}
		
		
		
		
		if (dao.saveOrUpdateAll(c)) {
			return MsgConfig.MSG_KEY_SUCCESS;
		} else {
			return MsgConfig.MSG_KEY_FAIL;
		}
	}

	@Override
	public boolean delete(String[] ids) {
		for (String id : ids) {
			Deal j = dao.get(Deal.class, id);
			dao.delete(j);
		}
		
		return true;
	}

	@Override
	public Map selectID(String id) {
		 
		
		Map map = new HashMap();
		map.put("deal", dao.get(Deal.class, id));
		map.put("file", dao.find("from PeFile where peDid=?",id));
		map.put("size", dao.find("from PeFile where peDid=?",id).size());
		return map;
	}

	@Override
	public boolean deleteFile(String id, String savepath) {
		PeFile pe = dao.get(PeFile.class, id);
		File file = new File(savepath + "/" + pe.getPeNewName() + "."
					+ pe.getPeExt());
			file.delete();
		return true;
		
	}

	

}
