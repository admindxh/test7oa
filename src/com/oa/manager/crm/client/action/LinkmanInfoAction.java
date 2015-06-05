package com.oa.manager.crm.client.action;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.manager.crm.client.bean.CrmClientLinkman;
import com.oa.manager.crm.client.bean.Info;
import com.oa.manager.crm.client.service.ILinkmanInfoService;
/**
 * 
 * 类名：LinkmanInfoAction
 * 功能：客户联系人
 * 详细：
 * 作者：Mafanghua
 * 版本：1.0
 * 日期：2013-12-04 上午9:45:02
 *
 */
@Controller
@RequestMapping("/linkman")
public class LinkmanInfoAction extends BaseAction{
		
	    @Autowired
		private ILinkmanInfoService service;
	    
	    /**
	     * 跳转到客户联系人页面
	     * @return
	     */
	    @RequiresPermissions("linkman:read")
	    @RequestMapping("load")
	    public String load(){
	    	return "crm/client/contact/load";
	    }
	    /**
	     * 查询客户联系人信息
	     * @param param
	     * @param linkman
	     * @return
	     */
	    @RequiresPermissions("linkman:read")
	    @RequestMapping("query")
	    public ModelAndView query(PageParam param,CrmClientLinkman linkman,String info){
	    	linkman.setCustomerId(info);
	    	return ajaxJsonEscape(service.selectLinkmanInfo(param, linkman));
	    }
	    /**
	     * 跳转到增加页面
	     * @return
	     */
	    @RequiresPermissions("linkman:add")
	    @RequestMapping("addPage")
	    public String addPage(){
	    	
	    	return "crm/client/contact/add";
	    }
	    /**
	     * 增加客户联系人
	     * @param linkman
	     * @param errors
	     * @return
	     */
	    @RequiresPermissions("linkman:add")
	    @RequestMapping("add")
	    public ModelAndView add(@Valid CrmClientLinkman linkman, Errors errors){
	    	if (errors.hasErrors()) {
				ModelAndView mav = getValidationMessage(errors);
				if (mav != null)
					return mav;
			}
	    	return ajaxDone(service.addLinkman(linkman));
	    }
	    /**
	     * 删除客户联系人
	     * @param ids 联系人id数组
	     * @return
	     */
	    @RequiresPermissions("linkman:delete")
	    @RequestMapping("del")
	    public ModelAndView del(String [] ids){
	    	
	    	return ajaxDone(service.deleteLinkman(ids));
	    }
	    /**
	     * 跳转到联系人修改页面
	     * @param id 联系人id
	     * @param map
	     * @return
	     */
	    @RequiresPermissions("linkman:update")
	    @RequestMapping("updatePage")
	    public String updatePage(String id,ModelMap map){
	    	 CrmClientLinkman linkman = service.get(CrmClientLinkman.class,id);
	    	 if(linkman==null){
	    		 return NODATA;
	    	 }
	    	 Info info = service.get(Info.class,linkman.getCustomerId());
	    	 if(info!=null){
		    		map.addAttribute("info",info);//客户信息
		     }
	    	 map.addAttribute("linkman",linkman);//联系人信息
	    	 return "crm/client/contact/update";
	    }
	    /**
	     * 修改客户联系人
	     * @param linkman
	     * @param errors
	     * @return
	     */
	    @RequiresPermissions("linkman:update")
	    @RequestMapping("update")
	    public ModelAndView update(@Valid CrmClientLinkman linkman,Errors errors ){
	    	
	    	if(errors.hasErrors()) {  
				ModelAndView mav=getValidationMessage(errors);
				if(mav!=null)return mav;
		    }
	    	return ajaxDone(service.updateLinkman(linkman));
	    }
	    /**
	     * 查看联系人详情
	     * @return
	     */
	    @RequestMapping("find")
	    public String find(String id,ModelMap map){
	    	CrmClientLinkman man = service.get(CrmClientLinkman.class, id);
	    	if(man==null){
	    		return NODATA;
	    	}
	    	Info info = service.get(Info.class,man.getCustomerId());
	    	if(info!=null){
	    		map.addAttribute("info",info);//客户信息
	    	}
	    	map.addAttribute("man",man);//联系人信息
	    	return "crm/client/contact/find";
	    }
	    /**
		 * 跳转到高级检索
		 * @return
		 */
		@RequestMapping("searchTag")
		public String searchTag(String search,ModelMap map) {
			
			return "crm/client/contact/search";
		}
		
		/**
		 * 跳转到联系人查找带回
		 * @return
		 */
		@RequestMapping("lookUpPage")
		public String lookUpPage(){
			return "crm/client/contact/lookup_customer";
		}
		/**
		 * 查询客户名称
		 * @param param
		 * @param o
		 * @return
		 */
		@RequestMapping("lookUp")
		public ModelAndView lookUp(CrmClientLinkman l,String info,PageParam param){
			l.setCustomerId(info);
			return ajaxJsonEscape(service.selectLinkmanInfo(param,l));
		}
}
