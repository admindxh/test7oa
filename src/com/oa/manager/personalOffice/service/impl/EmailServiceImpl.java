/**  
 * @Project: jxoa
 * @Title: EmailServiceImpl.java
 * @Package com.oa.manager.personalOffice.service.impl
 * @date 2013-5-28 下午03:50:27
 * @Copyright: 2013 
 */
package com.oa.manager.personalOffice.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.FileList;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personalOffice.bean.EmDraftbox;
import com.oa.manager.personalOffice.bean.EmDraftboxFile;
import com.oa.manager.personalOffice.bean.EmFiles;
import com.oa.manager.personalOffice.bean.EmInbox;
import com.oa.manager.personalOffice.bean.EmInboxFile;
import com.oa.manager.personalOffice.bean.EmInboxOutboxUser;
import com.oa.manager.personalOffice.bean.EmOutbox;
import com.oa.manager.personalOffice.bean.EmOutboxFile;
import com.oa.manager.personalOffice.bean.Email;
import com.oa.manager.personalOffice.service.IEmailService;
import com.oa.manager.system.bean.SyUsers;

/**
 * 
 * 类名：EmailServiceImpl 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2013-5-28 下午03:50:27
 * 
 */
@Service
public class EmailServiceImpl extends BaseServiceImpl implements IEmailService {
	@Override
	public boolean addDraft(EmOutbox eo, String userIds, String savePath,
			HttpServletRequest request, FileList files, String edId,
			String eoId, String[] oldIds) {

		// List<Object> cc=new ArrayList<Object>();
		// EmDraftbox ed=dao.get(EmDraftbox.class, edId);
		// cc.add(ed);
		// List<EmDraftboxFile>
		// edfList=dao.find("from EmDraftboxFile where emDraftbox.id=?",edId);
		// for(int i=0;i<edfList.size();i++){
		// EmDraftboxFile edf=edfList.get(i);
		// cc.add(edf);
		// EmFiles ef=dao.get(EmFiles.class, edf.getEmFiles().getId());
		// cc.add(ef);
		// }
		// //删除有关草稿箱的记录
		// dao.deleteAll(cc);

		// 等待保存的对象集合
		List<Object> c = new ArrayList<Object>();
		int num = 0; // 引用量
		// 上传文件

		List<MultipartFile> fileList = files.getFile();

		// 先保发件箱表
		String eoid = (String) dao.saveReturnId(eo);
		// 保存到收件箱
		EmInbox ei = new EmInbox();
		ei.setEiName(eo.getEoName());
		ei.setEiDate(eo.getEoDate());
		ei.setEiDetail(eo.getEoDetail());
		ei.setEiUid(eo.getEoUid());
		String eiid = (String) dao.saveReturnId(ei);

		// 关联接收人员
		if (StringUtils.isNotBlank(userIds)) {
			String[] ids = userIds.split(",");
			for (String id : ids) {
				EmInboxOutboxUser eiou = new EmInboxOutboxUser();
				eiou.setEmInbox(ei);
				eiou.setEmOutbox(eo);
				SyUsers u = new SyUsers();
				u.setId(id);
				eiou.setSyUsers(u);
				eiou.setIoStatus("未读");
				c.add(eiou);
				num++;
			}
		}
		if (eoId != null && !"".equals(eoId)) {

			if (oldIds != null && !"".equals(oldIds)) {

				for (int i = 0; i < oldIds.length; i++) {
					int nu = 0;
					EmFiles ef = dao.get(EmFiles.class, oldIds[i]);
					// 保存发件箱和上传文件表的关联表
					EmOutboxFile eof = new EmOutboxFile();
					eof.setEmFiles(ef);
					eof.setEmOutbox(eo);
					c.add(eof);
					// 保存收件箱和上传文件表的关联表
					EmInboxFile eif = new EmInboxFile();
					eif.setEmFiles(ef);
					eif.setEmInbox(ei);
					c.add(eif);
					nu = 2;
					// 修改引用数量
					ef.setEfNum(nu);
					c.add(ef);
				}
			}
		} else {
			if (oldIds != null && !"".equals(oldIds)) {
				for (int i = 0; i < oldIds.length; i++) {
					int nu = 0;
					EmFiles ef = dao.get(EmFiles.class, oldIds[i]);
					// 保存发件箱和上传文件表的关联表
					EmOutboxFile eof = new EmOutboxFile();
					eof.setEmFiles(ef);
					eof.setEmOutbox(eo);
					c.add(eof);
					// 保存收件箱和上传文件表的关联表
					EmInboxFile eif = new EmInboxFile();
					eif.setEmFiles(ef);
					eif.setEmInbox(ei);
					c.add(eif);
					nu = 2;
					// 修改引用数量
					ef.setEfNum(nu);
					c.add(ef);
				}
			}
		}

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

					EmFiles ef = new EmFiles();
					ef.setEfExt(ext);
					ef.setEfName(f.getOriginalFilename());
					ef.setEfNewName(uuid);
					ef.setEfSize((int) f.getSize());
					ef.setEfNum(num);

					// 先上传文件
					String efid = (String) dao.saveReturnId(ef);
					// 保存发件箱和上传文件表的关联表
					EmOutboxFile eof = new EmOutboxFile();
					eof.setEmFiles(ef);
					eof.setEmOutbox(eo);
					c.add(eof);
					// 保存收件箱和上传文件表的关联表
					EmInboxFile eif = new EmInboxFile();
					eif.setEmFiles(ef);
					eif.setEmInbox(ei);
					c.add(eif);
				}
			}

		}

		return dao.saveOrUpdateAll(c);
		// return true;
	}

	@Override
	public boolean addEmail(EmOutbox eo, String userIds, String type,
			String id, String savePath, HttpServletRequest request,
			FileList files, String[] oldIds) {
		int num = 1; // 引用量
		// 上传文件
		List<MultipartFile> fileList = files.getFile();

		// 先保发件箱表
		String eoid = (String) dao.saveReturnId(eo);
		// 保存到收件箱
		EmInbox ei = new EmInbox();
		ei.setEiName(eo.getEoName());
		ei.setEiDate(eo.getEoDate());
		ei.setEiDetail(eo.getEoDetail());
		ei.setEiUid(eo.getEoUid());
		String eiid = (String) dao.saveReturnId(ei);
		// 等待保存的对象集合
		List<Object> c = new ArrayList<Object>();
		// 关联接收人员
		if (StringUtils.isNotBlank(userIds)) {
			String[] ids = userIds.split(",");
			for (String d : ids) {
				EmInboxOutboxUser eiou = new EmInboxOutboxUser();
				eiou.setEmInbox(ei);
				eiou.setEmOutbox(eo);
				SyUsers u = new SyUsers();
				u.setId(d);
				eiou.setSyUsers(u);
				eiou.setIoStatus("未读");
				c.add(eiou);
				num++;
			}
		}

		// 查询数据库 草稿箱的附件表
		// List<EmFiles> l = new ArrayList<EmFiles>();
		// if (type.equals("in")) {
		// l = dao.find(
		// "select ef from EmInboxFile edf,EmFiles ef where edf.emFiles.id=ef.id and edf.emInbox.id=?",
		// id);
		// } else if (type.equals("out")) {
		// l = dao.find(
		// "select ef from EmOutboxFile edf,EmFiles ef where edf.emFiles.id=ef.id and edf.emOutbox.id=?",
		// id);
		// }
		if (oldIds != null && !"".equals(oldIds)) {
			for (int i = 0; i < oldIds.length; i++) {
				int nu = 0;
				EmFiles ef = dao.get(EmFiles.class, oldIds[i]);
				// 保存发件箱和上传文件表的关联表
				EmOutboxFile eof = new EmOutboxFile();
				eof.setEmFiles(ef);
				eof.setEmOutbox(eo);
				c.add(eof);
				// 保存收件箱和上传文件表的关联表
				EmInboxFile eif = new EmInboxFile();
				eif.setEmFiles(ef);
				eif.setEmInbox(ei);
				c.add(eif);
				nu = 2;
				// 修改引用数量
				ef.setEfNum(nu);
				c.add(ef);
			}
		}
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

					EmFiles ef = new EmFiles();
					ef.setEfExt(ext);
					ef.setEfName(f.getOriginalFilename());
					ef.setEfNewName(uuid);
					ef.setEfSize((int) f.getSize());
					ef.setEfNum(num);

					// 先上传文件
					String efid = (String) dao.saveReturnId(ef);
					// ef.setId(efid);
					// 保存发件箱和上传文件表的关联表
					EmOutboxFile eof = new EmOutboxFile();
					eof.setEmFiles(ef);
					eof.setEmOutbox(eo);
					c.add(eof);
					// 保存收件箱和上传文件表的关联表
					EmInboxFile eif = new EmInboxFile();
					eif.setEmFiles(ef);
					eif.setEmInbox(ei);
					c.add(eif);
				}
			}

		}

		return dao.saveOrUpdateAll(c);
	}

	@Override
	public Map selectEmail(String id) {
		Member me = ServletUtil.getMember();
		Email em = new Email();
		List efList = dao
				.find("select ef from EmInboxFile eif,EmFiles ef where eif.emFiles.id=ef.id and eif.emInbox.id=?",
						id);
		EmInboxOutboxUser eiou = (EmInboxOutboxUser) dao
				.findOne(
						"from EmInboxOutboxUser eiou where eiou.emInbox.id=? and eiou.syUsers.id=?",
						id, me.getId());

		EmInbox ei = (EmInbox) dao.findOne("from EmInbox where id=?", id);
		// 修改内容
		// SyUsers users=(SyUsers) dao.findOne("from SyUsers where id=?",
		// ei.getEiUid());
		// String detail=ei.getEiDetail();
		// detail="<p>&nbsp;</p><p>&nbsp;</p><p><strong>------------------ 原始邮件 ------------------</strong> </p><p><strong> 发件人："+users.getUserName()+"</strong></p><p> <strong>发送时间："+ei.getEiDate()+"</strong></p><p><strong> 主题："+ei.getEiName()+"</strong></p><p>"+detail+"</p><p><strong>------------------ End ------------------</strong> </p>";
		// ei.setEiDetail(detail);
		em.setEmInbox(ei);
		em.setEmInboxOutboxUser(eiou);
		Map map = new HashMap();
		map.put("emFile", efList);
		map.put("email", em);
		map.put("size", efList.size());

		map.put("uid", ei.getEiUid());
		map.put("date", ei.getEiDate());
		map.put("name", ei.getEiName());
		map.put("detail", ei.getEiDetail());
		return map;
	}

	@Override
	public Map findIn(String id) {
		Member me = ServletUtil.getMember();
		Email em = new Email();
		List efList = dao
				.find("select ef from EmInboxFile eif,EmFiles ef where eif.emFiles.id=ef.id and eif.emInbox.id=?",
						id);
		EmInboxOutboxUser eiou = (EmInboxOutboxUser) dao
				.findOne(
						"from EmInboxOutboxUser eiou where eiou.emInbox.id=? and eiou.syUsers.id=?",
						id, me.getId());

		EmInbox ei = (EmInbox) dao.findOne("from EmInbox where id=?", id);
		// //修改内容
		// SyUsers users=(SyUsers) dao.findOne("from SyUsers where id=?",
		// ei.getEiUid());
		em.setEmInbox(ei);
		em.setEmInboxOutboxUser(eiou);
		Map map = new HashMap();
		map.put("emFile", efList);
		map.put("email", em);
		map.put("size", efList.size());
		return map;
	}
	public Long selectNumber(){
		Member me = ServletUtil.getMember();
		return (Long)dao.findUniqueOne("select count(eiou) from EmInboxOutboxUser eiou where eiou.syUsers.id=? and  eiou.ioStatus='未读' ",
						 me.getId());
		
	}
	public boolean updateStatus(String id) {
		Member me = ServletUtil.getMember();
		EmInboxOutboxUser eiou = (EmInboxOutboxUser) dao
				.findOne(
						"select eiou from EmInboxOutboxUser eiou where eiou.emInbox.id=? and eiou.syUsers.id=?",
						id, me.getId());

		eiou.setIoStatus("已读");
		return dao.update(eiou);
	}

	@Override
	public Map selectOutbox(String id) {
		String name = "";
		String uid = "";
		Member me = ServletUtil.getMember();
		Email em = new Email();
		List efList = dao
				.find("select ef from EmOutboxFile eif,EmFiles ef where eif.emFiles.id=ef.id and eif.emOutbox.id=?",
						id);
		List<EmInboxOutboxUser> eiouList = dao.find(
				"from EmInboxOutboxUser eiou where eiou.emOutbox.id=? ", id);

		// List<SyUsers> uList=dao.find("from SyUsers");
		for (int i = 0; i < eiouList.size(); i++) {
			EmInboxOutboxUser eiou = eiouList.get(i);
			// for(int j=0;j<uList.size();j++){
			// SyUsers user=uList.get(j);
			// if(eiou.getSyUsers().getId().equals(user.getId())){
			if ("".equals(uid)) {
				// name=user.getUserName();
				uid = eiou.getSyUsers().getId();
			} else {
				// name=name+","+user.getUserName();
				uid = uid + "," + eiou.getSyUsers().getId();
			}

		}

		EmOutbox ei = (EmOutbox) dao.findOne("from EmOutbox where id=?", id);
		// //修改内容
		// SyUsers users=(SyUsers) dao.findOne("from SyUsers where id=?",
		// ei.getEoUid());

		em.setEmOutbox(ei);
		Map map = new HashMap();
		map.put("emFile", efList);
		map.put("email", em);
		map.put("size", efList.size());
		map.put("inName", name);
		map.put("inUid", uid);
		return map;
	}

	@Override
	public boolean saveEmail(EmOutbox eo, String userIds, String savePath,
			HttpServletRequest request, FileList files, String[] oldIds,
			String type, String getId) {
		// 上传文件
		List<MultipartFile> fileList = files.getFile();
		// 保存到草稿箱
		EmDraftbox ed = new EmDraftbox();
		ed.setId(eo.getId());
		ed.setEdName(eo.getEoName());
		ed.setEdDate(eo.getEoDate());
		ed.setEdDetail(eo.getEoDetail());
		ed.setEdOutUid(eo.getEoUid());
		ed.setEdInUid(userIds);
		dao.save(ed);

		// 等待保存的对象集合
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

					EmFiles ef = new EmFiles();
					ef.setEfExt(ext);
					ef.setEfName(f.getOriginalFilename());
					ef.setEfNewName(uuid);
					ef.setEfSize((int) f.getSize());
					ef.setEfNum(1);

					// 先上传文件
					String efid = (String) dao.saveReturnId(ef);
					// 保存发件箱和上传文件表的关联表
					EmDraftboxFile edf = new EmDraftboxFile();
					edf.setEmFiles(ef);
					edf.setEmDraftbox(ed);
					c.add(edf);
				}
			}

		}
		List<EmFiles> l = new ArrayList<EmFiles>();
		if (type.equals("in")) {
			l = dao.find(
					"select ef from EmInboxFile edf,EmFiles ef where edf.emFiles.id=ef.id and edf.emInbox.id=?",
					getId);
		} else if (type.equals("out")) {
			l = dao.find(
					"select ef from EmOutboxFile edf,EmFiles ef where edf.emFiles.id=ef.id and edf.emOutbox.id=?",
					getId);
		}
		if (oldIds != null && !"".equals(oldIds)) {
			for (int i = 0; i < l.size(); i++) {

				for (int j = 0; j < oldIds.length; j++) {
					if (l.get(i).getId().equals(oldIds[j])) {
						EmFiles ef = l.get(i);
						EmDraftboxFile edf = new EmDraftboxFile();
						edf.setEmFiles(ef);
						edf.setEmDraftbox(ed);
						c.add(edf);
					}
				}

			}
		}

		return dao.saveOrUpdateAll(c);
	}

	@Override
	public boolean update(EmOutbox eo, String userIds, String savePath,
			HttpServletRequest request, FileList files, String[] oldIds) {

		// 上传文件
		List<MultipartFile> fileList = files.getFile();
		// 保存到草稿箱
		EmDraftbox ed = new EmDraftbox();
		ed.setId(eo.getId());
		ed.setEdName(eo.getEoName());
		ed.setEdDate(eo.getEoDate());
		ed.setEdDetail(eo.getEoDetail());
		ed.setEdOutUid(eo.getEoUid());
		ed.setEdInUid(userIds);
		dao.update(ed);
		List<EmFiles> efList = dao
		.find("select ef from EmDraftboxFile edf,EmFiles ef where edf.emFiles.id=ef.id and edf.emDraftbox.id=?",
				eo.getId());
		if (oldIds != null && !"".equals(oldIds)) {
			EmFiles efs = new EmFiles();
			
			//修改前附件的保留删除
			Set<EmFiles> set= new HashSet();
			set.addAll(efList); 
			for (EmFiles e : efList) {
				for (int i = 0; i < oldIds.length; i++) {
					if (e.getId().equals(oldIds[i])) {
							set.remove(e);
							break;
					} 
				}
			}
			dao.deleteAll(set);
		}else{
			dao.deleteAll(efList);
			
		}

		// 等待保存的对象集合
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

					EmFiles ef = new EmFiles();
					ef.setEfExt(ext);
					ef.setEfName(f.getOriginalFilename());
					ef.setEfNewName(uuid);
					ef.setEfSize((int) f.getSize());
					ef.setEfNum(1);

					// 先上传文件
					String efid = (String) dao.saveReturnId(ef);
					// 保存发件箱和上传文件表的关联表
					EmDraftboxFile edf = new EmDraftboxFile();
					edf.setEmFiles(ef);
					edf.setEmDraftbox(ed);
					c.add(edf);
				}
			}

		}

		return dao.saveOrUpdateAll(c);
	}

	@Override
	public Map findDraftOne(String id) {

		EmDraftbox ed = (EmDraftbox) dao.findOne("from EmDraftbox where id=?",
				id);
		List<EmFiles> efList = dao
				.find("select ef from EmDraftboxFile edf,EmFiles ef where edf.emFiles.id=ef.id and edf.emDraftbox.id=?",
						id);
		// String name="";
		// String [] str = ed.getEdInUid().split(",");
		// for(int ii=0;ii<str.length;ii++) {
		// List<SyUsers> uList=dao.find("from SyUsers");
		// for(int j=0;j<uList.size();j++){
		// SyUsers user=uList.get(j);
		// if(str[ii].equals(user.getId())){
		// if("".equals(name)){
		// name=user.getUserName();
		// }else{
		// name=name+","+user.getUserName();
		// }
		//
		// }
		//
		// }
		// }
		Map map = new HashMap();
		map.put("inUid", ed.getEdInUid());
		map.put("ed", ed);
		map.put("efList", efList);
		return map;
	}

	@Override
	public boolean deleteEmail(String[] ids, int type, String savePath) {
		List<Object> c = new ArrayList<Object>();
		List<Object> cc = new ArrayList<Object>();
		for (String id : ids) {
			if (type == 1) {
				EmInbox e = dao.get(EmInbox.class, id);
				List<EmFiles> l = dao
						.find("select ef from EmInboxFile eif,EmFiles ef where eif.emFiles.id=ef.id and eif.emInbox.id=?",
								id);
				for (int i = 0; i < l.size(); i++) {
					EmFiles ef = l.get(i);
					int num = ef.getEfNum();
					if (num > 1) {
						ef.setEfNum((num - 1));
						c.add(ef);
					} else {
						cc.add(ef);
						File file = new File(savePath + "/" + ef.getEfNewName()
								+ "." + ef.getEfExt());
						file.delete();
					}

				}
				cc.add(e);

			} else if (type == 2) {
				EmOutbox e = dao.get(EmOutbox.class, id);

				List<EmFiles> l = dao
						.find("select ef from EmOutboxFile eof,EmFiles ef where eof.emFiles.id=ef.id and eof.emOutbox.id=?",
								id);
				for (int i = 0; i < l.size(); i++) {
					EmFiles ef = l.get(i);
					int num = ef.getEfNum();
					if (num > 1) {
						ef.setEfNum((num - 1));
						c.add(ef);
					} else {
						cc.add(ef);
						File file = new File(savePath + "/" + ef.getEfNewName()
								+ "." + ef.getEfExt());
						file.delete();
					}
				}
				cc.add(e);
			}
			if (type == 3) {
				EmDraftbox e = dao.get(EmDraftbox.class, id);
				List<EmFiles> l = dao
						.find("select ef from EmDraftboxFile eif,EmFiles ef where eif.emFiles.id=ef.id and eif.emDraftbox.id=?",
								id);
				for (int i = 0; i < l.size(); i++) {
					EmFiles ef = l.get(i);
					int num = ef.getEfNum();
					if (num > 1) {
						ef.setEfNum((num - 1));
						c.add(ef);
					} else {
						cc.add(ef);
						File file = new File(savePath + "/" + ef.getEfNewName()
								+ "." + ef.getEfExt());
						file.delete();
					}
				}
				cc.add(e);

			}

		}
		dao.saveOrUpdateAll(c);
		dao.deleteAll(cc);
		return true;

	}

	@Override
	public boolean deleteFile(String id, String savePath) {
		EmFiles ef = dao.get(EmFiles.class, id);
		int num = ef.getEfNum();
		if (num > 1) {
			ef.setEfNum((num - 1));
		} else {
			dao.delete(ef);

			File file = new File(savePath + "/" + ef.getEfNewName() + "."
					+ ef.getEfExt());
			file.delete();
		}
		return true;
	}

	@Override
	public DataGrid selectEmDraftbox(PageParam param, Email e) {

		DataGrid data = new DataGrid();

		List<EmDraftbox> emList = new ArrayList<EmDraftbox>();
		List<EmDraftbox> eiflist = new ArrayList<EmDraftbox>();
		List list = new ArrayList();
		Member me = ServletUtil.getMember();

		StringBuffer sb = new StringBuffer("from EmDraftbox where  edOutUid=?");
		list.add(me.getId());
		if (e.getName() != null && !"".equals(e.getName())) {
			sb.append(" and edName like ?");
			list.add("%"+e.getName()+"%");
		}
		if (e.getDate1() != null && !"".equals(e.getDate1())) {
			sb.append(" and edDate>=?");
			list.add(e.getDate1());
		}
		if (e.getDate2() != null && !"".equals(e.getDate2())) {
			sb.append(" and edDate<=?");
			list.add(e.getDate2());
		}

		data.setTotal((Long) dao.findUniqueOne("select count(*) " + sb, list));

		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {
			sb.append(" order by edDate  desc");
		}

		List<Map<String, Object>> l = dao
				.findPage(
						"select new Map(id as id,edName as edName,edInUid as edInUid,edDate as edDate ) "
								+ sb.toString(), param.getPage(),
						param.getRows(), list);

		for (Map<String, Object> r : l) {
			// System.err.println(r.get("edName").toString());

			if (r.get("edInUid").toString() != null
					&& !"".equals(r.get("edInUid").toString())) {
				r.put("edInUid", MyCache.getInstance().getTrueName(r.get("edInUid").toString()));
			}
		}

		data.setRows(l);

		return data;

	}

	@Override
	public DataGrid selectEmOutbox(PageParam param, Email e) {

		DataGrid data = new DataGrid();

		List<Email> emList = new ArrayList<Email>();
		List<EmInboxFile> eiflist = new ArrayList<EmInboxFile>();
		List list = new ArrayList();
		Member me = ServletUtil.getMember();

		StringBuffer sb = new StringBuffer("from EmOutbox where  eoUid=?");
		list.add(me.getId());

		if (e.getName() != null && !"".equals(e.getName())) {
			sb.append(" and eoName like ?");
			list.add("%"+e.getName()+"%");
		}
		if (e.getDate1() != null && !"".equals(e.getDate1())) {
			sb.append(" and eoDate>=?");
			list.add(e.getDate1());
		}
		if (e.getDate2() != null && !"".equals(e.getDate2())) {
			sb.append(" and eoDate<=?");
			list.add(e.getDate2());
		}
		data.setTotal((Long) dao.findUniqueOne("select count(*) " + sb, list));

		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {

			sb.append(" order by eoDate  desc");
		}

		List<Map<String, Object>> l = dao
				.findPage(
						"select new Map(id as id,eoName as eoName,eoUid as eoUid,eoDate as eoDate) "
								+ sb.toString(), param.getPage(),
						param.getRows(), list);

		for (Map<String, Object> r : l) {
			// EmOutbox eo = (EmOutbox) l.get(i);
			// System.err.println("=========================================:"+eo.getEoName());
			Email email = new Email();
			String uid = "";
			// 加载一下收件人
			List<EmInboxOutboxUser> eiouList = dao.find(
					"from EmInboxOutboxUser where emOutbox.id=?", r.get("id"));
			// List<SyUsers> uList=dao.find("from SyUsers");
			for (int ii = 0; ii < eiouList.size(); ii++) {

				EmInboxOutboxUser eiou = eiouList.get(ii);
				// for(int j=0;j<uList.size();j++){
				// SyUsers user=uList.get(j);
				// if(eiou.getSyUsers().getId().equals(user.getId())){
				if ("".equals(uid)) {
					uid = MyCache.getInstance().getTrueName( eiou.getSyUsers().getId());
				} else {
					uid = uid
							+ ","
							+ MyCache.getInstance().getTrueName( eiou.getSyUsers().getId());
				}

				// }

				// }

			}
			r.put("eoUid", uid);
		}

		data.setRows(l);

		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataGrid selectEmInbox(PageParam param, Email e, String status) {

		DataGrid data = new DataGrid();

		List list = new ArrayList();
		Member me = ServletUtil.getMember();

		StringBuffer sb = new StringBuffer(
				"from EmInboxOutboxUser eiou ,EmInbox ei where eiou.emInbox.id=ei.id and eiou.syUsers.id=?");
		list.add(me.getId());

		if (e.getName() != null && !"".equals(e.getName())) {
			sb.append(" and ei.eiName like ?");
			list.add("%"+e.getName()+"%");
		}
		if (e.getDate1() != null && !"".equals(e.getDate1())) {
			sb.append(" and ei.eiDate>=?");
			list.add(e.getDate1());
		}
		if (e.getDate2() != null && !"".equals(e.getDate2())) {
			sb.append(" and ei.eiDate<=?");
			list.add(e.getDate2());
		}
		if (status != null && !"".equals(status)) {
			sb.append(" and eiou.ioStatus =?");
			list.add(status);
		}

		data.setTotal((Long) dao.findUniqueOne("select count(ei) " + sb, list));

		if (StringUtils.isNotBlank(param.getSort())) {

			param.appendOrderBy(sb);// 排序

		} else {

			sb.append("order by ei.eiDate desc");
		}

		List<Map<String, Object>> rows = dao
				.findPage(
						"select new Map(ei.id as id,eiou.ioStatus as ioStatus,ei.eiName as eiName,ei.eiUid as eiUid ,ei.eiDate as eiDate ) "
								+ sb.toString(), param.getPage(),
						param.getRows(), list);

		for (Map<String, Object> r : rows) {
			// r.get("eiou");

			// EmInbox ei = (EmInbox) r.get("ei");
			// ei.setEmInboxFiles(ei.getEmInboxFiles());
			if (r.get("eiUid").toString() != null
					&& !"".equals(r.get("eiUid").toString())) {
				r.put("eiUid",MyCache.getInstance().getTrueName(r.get("eiUid").toString()));
			}
		}

		data.setRows(rows);

		return data;
		// return data;
	}

}
