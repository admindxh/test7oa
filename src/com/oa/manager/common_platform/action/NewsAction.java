/**  
 * @Project: jxoa
 * @Title: NewsAction.java
 * @Package com.oa.manager.common_platform.action
 * @date 2013-5-19 上午10:25:25
 * @Copyright: 2013 
 */
package com.oa.manager.common_platform.action;


import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.common_platform.bean.Newscomment;
import com.oa.manager.common_platform.bean.Newsmanagement;
import com.oa.manager.common_platform.service.INewsService;

/**
 * 
 * 类名：NewsAction
 * 功能：查询新闻
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-5-19 上午10:25:25
 *
 */
@Controller
@RequestMapping("news")
public class NewsAction extends BaseAction{
	@Autowired
	private INewsService inewsService;
	/**
	 * 查询新闻----跳转到新闻管理界面
	 * 
	 * @return
	 * 
	 */
	@RequiresPermissions("news:read")
	@RequestMapping("loadnews")
	public String loadnews(){

		return "common_platform/news/load";
	}
	@RequiresPermissions("news:read")
	@RequestMapping("loadallnews")
	public ModelAndView loadallnews(PageParam param,Newsmanagement ng){
		
		
		return ajaxJsonEscape(inewsService.newsload(param, ng));
	}
	
	
	/**
	 * 删除新闻
	 * 
	 * @return
	 */
	@RequiresPermissions("news:delete")
	@RequestMapping("delete")
	public ModelAndView delete(String[] ids){
		return ajaxDone(inewsService.deletenews(ids));
	}
	/**
	 * 跳转到增加新闻
	 * @return
	 */
	@RequiresPermissions("news:add")
	@RequestMapping("addPage")
	public String jumpadd(){
		return "common_platform/news/add";
	}
	/**
	 *查询点击修改按扭时传过来要修改的记录 
	 * @param id
	 * @param mn
	 * @param map
	 * @return
	 */
	@RequiresPermissions("news:update")
	@RequestMapping("updatePage")
	public String jumpupdate(String id,ModelMap map){
		Newsmanagement ng=inewsService.get(Newsmanagement.class, id);
		if(ng==null){
		return NODATA;
		}
		map.addAttribute("news",ng );
		return "common_platform/news/update";
	}
	/**
	 * 增加新闻
	 * @param mn
	 * @param errors
	 * @param request
	 * @return
	 */
	@RequiresPermissions("news:add")
	@RequestMapping("add")
	public ModelAndView addnews(@Valid Newsmanagement mn, Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
        Member me=ServletUtil.getMember();
        mn.setPromulgator(me.getId());
        mn.setStatus(0);
		if(mn.getStick()==null){
			mn.setStick(0);
		}
        mn.setHits(0);
		mn.setContents(0);
		mn.setCreatedtime(DateUtil.currentTimestamp());
	    mn.setModifiedate(DateUtil.currentTimestamp());
		return ajaxDone(inewsService.save(mn));
	}
	/**
	 * 修改新闻
	 * @param mn
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("news:update")
	@RequestMapping("update")
	public ModelAndView update(Newsmanagement mn){

	    
		return ajaxDone(inewsService.updatenews(mn));
	}
	/**
	 * 跳转到新闻内容查看页面
	 * @param id
	 * @param param
	 * @param map
	 * @return
	 */
	@RequiresPermissions("news:read")
	@RequestMapping("look")
	public String look(String id,PageParam param,ModelMap map){
		
		map.addAttribute("n",inewsService.updateSelectOne(id));
		Newsmanagement ng=inewsService.get(Newsmanagement.class, id);
		if(ng==null){
			return NODATA;
		}
		map.addAttribute("news",inewsService.selectNewscomment(id, param) );
		map.addAttribute("total",inewsService.selectNewscommentCount(id));
		map.addAttribute("pageSize",param.getRows());
		map.addAttribute("pageNumber",param.getPage());
		return "common_platform/news/looknews";
	}
	/**
	 * 加载评论
	 * @param id
	 * @param param
	 * @param map
	 * @return
	 */
	@RequiresPermissions("news:read")
	@RequestMapping("contents")
	public String contents(String id,PageParam param,ModelMap map){
		map.addAttribute("n",inewsService.updateSelectOne(id));
		map.addAttribute("news", inewsService.selectNewscomment(id, param));
		map.addAttribute("total",inewsService.selectNewscommentCount(id));
		return "common_platform/news/contents";
	}
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	@RequiresPermissions("news:management")
	@RequestMapping("deletecontent")
	public ModelAndView deletecontent(String id,String newsid){ 
	     return	ajaxDone(inewsService.deletecontent(id,newsid));
	}
	/**
	 * 跳转到回复回复本贴
	 * @param id
	 * @param param
	 * @param nc
	 * @param ng
	 * @param map
	 * @return
	 */
	@RequiresPermissions("news:answer")
	@RequestMapping("answer")
	public String answer(){
		return "common_platform/news/answer"; 
	}
	/**
	 * 回复帖子
	 * @param nc
	 * @return
	 */
	@RequiresPermissions("news:answer")
	@RequestMapping("answernewscomment")
	public ModelAndView answernewscomment(@Valid Newscomment nc, Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		  nc.setConmmentsdate(DateUtil.currentTimestamp());
		  Member me=ServletUtil.getMember();
		  nc.setConmmentsnameId(me.getId());
	      return  ajaxDone(inewsService.updatecontent(nc));
	}
	/**
	 * 终止新闻
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("news:stop")
	@RequestMapping("stop")
	public ModelAndView stop(String[] ids){
		return ajaxDone(inewsService.updatestop(ids));
	}
	/**
	 * 生成新闻
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("news:generate")
	@RequestMapping("generate")
	public ModelAndView generate(String[] ids){
		return ajaxDone(inewsService.updategenerate(ids));
	}
	/**
	 * 跳转管理评论
	 * @return
	 */
	@RequiresPermissions("news:management")
	@RequestMapping("managementcontent")
	public String managementcontent(String id,PageParam param,ModelMap map){
		Newsmanagement nc=inewsService.get(Newsmanagement.class, id);
		if(nc==null){
			return NODATA;
		}
		map.addAttribute("news", inewsService.selectNewscomment(id, param));
		//param.setAllCount(inewsService.selectNewscommentCount(id));
		//map.addAttribute("fromParam", param);
		map.addAttribute("total",inewsService.selectNewscommentCount(id));
		map.addAttribute("pageSize",param.getRows());
		map.addAttribute("pageNumber",param.getPage());
		
		
		return "common_platform/news/managementcontents"; 	
	}
	@RequiresPermissions("news:management")
	@RequestMapping("mangementcontentload")
	public String mangementcontentload(String id,PageParam param,ModelMap map){
		
		map.addAttribute("news", inewsService.selectNewscomment(id, param));
		
		
		return "common_platform/news/showmanagements";
	}
	/**
	 * 加载个人办公的新闻
	 * @param param
	 * @param ng
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequiresPermissions("news:read")
	@RequestMapping("load_news")
	public String load_news(){
		
		return "common_platform/news/news";
	}
	@RequiresPermissions("news:read")
	@RequestMapping("loadpernews")
	public ModelAndView loadpernews(PageParam param,Newsmanagement ng){
		ng.setStatus(1);
		
		return ajaxJsonEscape(inewsService.newsload(param, ng));
	}
}
