/**  
 * @Project: jxoa
 * @Title: NewsService.java
 * @Package com.oa.manager.common_platform.service.impl
 * @date 2013-5-19 上午11:14:34
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Newscomment;
import com.oa.manager.common_platform.bean.Newsmanagement;
import com.oa.manager.common_platform.service.INewsService;

/**
 * 
 * 类名：NewsService
 * 功能：新闻管理
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-5-19 上午11:14:34
 *
 */
@Service
public  class NewsServiceImpl extends BaseServiceImpl implements INewsService {
	
	/**
	 *加载查询 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid newsload(PageParam param,Newsmanagement ng){
		DataGrid data=new DataGrid();
		StringBuffer sb=new StringBuffer("from Newsmanagement n  where 1=1");
		List list=new ArrayList();
		if(ng.getStatus()!=null){
			sb.append(" and n.status=?");
			list.add(ng.getStatus());
		}
		if(StringUtils.isNotBlank(ng.getNewstitle())){
			sb.append(" and n.newstitle like ?");
			list.add("%"+ng.getNewstitle()+"%");
		} 
		if(StringUtils.isNotBlank(ng.getTypeid())&&ng.getTypeid()!="全部类型"){
			sb.append(" and n.typeid=?");
			list.add(ng.getTypeid());
		}
		if(ng.getStartDate()!=null){
			sb.append(" and n.createdtime>=?");
			list.add(ng.getStartDate());
		}
		if(ng.getEndDate()!=null){
			sb.append(" and n.createdtime<=?");
			list.add(ng.getEndDate());
		}
		data.setTotal((Long)dao.findOne("select count(*)"+sb,list));
		if(StringUtils.isNotBlank(param.getSort())){
			param.appendOrderBy(sb);//排序
		}else{
			sb.append(" order by n.createdtime, n.stick desc ");
		}
		
		List<Newsmanagement> rows=dao.findPage(sb.toString(),param.getPage(),param.getRows(),list);
		for(Newsmanagement m:rows){//发布人
			m.setPromulgator(MyCache.getInstance().getTrueName(m.getPromulgator()));//发布人
			m.setTypeid(MyCache.getInstance().getSelectValue(m.getTypeid()));//新闻类型
			m.setPlotsummary("");
			m.setNewsplot("");
			m.setModifiedate(null);
			m.setAllowcomment(null);
			
		}	
		
		data.setRows(rows);
		return data ;
	}
	/**
	 * 删除新闻
	 */
	@Override
	public boolean deletenews(String[] ids){
		List<Object> c=new ArrayList<Object>();
		for (String id:ids){
			Newsmanagement ng=dao.get(Newsmanagement.class, id);
			if(ng!=null){
				c.add(ng);
			}
		}
		return dao.deleteAll(c);
	}
	/**
	 * 查询评论
	 */
	public List selectNewscomment(String id,PageParam param){
		
		return dao.findPage("select nc from Newscomment nc where nc.newsid=?  order by conmmentsdate desc",param.getPage(),param.getRows(),id);
	}
	
	/**
	 * 查询评论总条数
	 */
	public Long selectNewscommentCount(String id){
		return (Long)dao.findOne("select count(nc.newsid) from Newscomment nc where nc.newsid =?",id);
	}
	/**
	 * 删除评论
	 */
	@SuppressWarnings("unchecked")
	public String deletecontent(String id,String newsid){
		Newsmanagement ng=dao.get(Newsmanagement.class, newsid);
		if(ng!=null){
		 ng.setContents(ng.getContents()-1);
		 dao.delete("delete from Newscomment where id=?",id);
		 return MsgConfig.MSG_KEY_SUCCESS;
		}
		else{
			return "msg.data.hasdelete";
		}

	}
	/**
	 * 评论总条数
	 */
	public Long contentCount(String id){
		return (Long)dao.findOne("select count(nc.answernewscomments) from Newscomment nc where nc.newsid =?",id);
	}
	/**
	 *修改成终止 
	 */
	public boolean updatestop(String[] ids){
		for(String id:ids){
			dao.update("update Newsmanagement set status=? where id=?",0,id);	
		}
		return true;
	}
	/**
	 * 修改成生成
	 */
	public boolean updategenerate(String[] ids){   
		for(String id:ids){
			dao.update("update Newsmanagement set status=? where id=?",1,id);	
		}
		return true;
	}
	/**
	 * 修改点击数
	 */
	public Newsmanagement updateSelectOne(String id){
		Newsmanagement ng=dao.get(Newsmanagement.class, id);
		if(ng!=null){	
		ng.setHits(ng.getHits()+1);//当点击新闻以后新闻的点击数自动增1
		}
		return ng;
	}
	/**
	 *修改评论数
	 */
	public String  updatecontent(Newscomment nc){
		Newsmanagement ng=dao.get(Newsmanagement.class,nc.getNewsid());
		if(ng!=null){
		ng.setContents(ng.getContents()+1);//当评论以后评论条自动增1
		dao.save(nc);
		return MsgConfig.MSG_KEY_SUCCESS;
		}
		return "msg.data.hasdelete";
	}
	/**
	 * 修改新闻
	 */
	public String updatenews(Newsmanagement ng){
		Member me=ServletUtil.getMember();
		Newsmanagement na=dao.get(Newsmanagement.class, ng.getId());
		if(na==null){
			return "msg.data.hasdelete";
//			try {
//				BeanUtils.copyProperties(na, ng);
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return MsgConfig.MSG_KEY_SUCCESS;
		}
		if(ng.getStick()==null){
			na.setStick(0);
		}else{
			na.setStick(1);
		}
		na.setNewstitle(ng.getNewstitle());
		na.setTypeid(ng.getTypeid());
		na.setAllowcomment(ng.getAllowcomment());
		na.setPlotsummary(ng.getPlotsummary());
		na.setNewsplot(ng.getNewsplot());
		na.setStatus(ng.getStatus());
		na.setCreatedtime(ng.getCreatedtime());
		na.setHits(ng.getHits());
		na.setContents(ng.getContents());
		na.setPromulgator(ng.getPromulgator());
		na.setModifyByname(me.getId());
		na.setModifiedate(DateUtil.currentTimestamp());//修改时间
		 if(dao.update(na)){
			 return MsgConfig.MSG_KEY_SUCCESS;
		 }else{
			 return MsgConfig.MSG_KEY_FAIL;
		 }
	}
}
