package com.oa.manager.crm.sell.action;

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
import com.oa.manager.crm.sell.bean.Chance;
import com.oa.manager.crm.sell.bean.CrmSellActivity;
import com.oa.manager.crm.sell.service.SellActivityService;

/**
 * 
 * 类名：SellActivityAction
 * 功能：客户活动
 * 详细：
 * 作者：Mafanghua
 * 版本：1.0
 * 日期：2013-12-13 上午9:45:02
 *
 */
@Controller
@RequestMapping("/activity")
public class SellActivityAction extends BaseAction {
	@Autowired
	private SellActivityService service;
	/**
	 * 跳转到客户活动管理页面
	 * @return
	 */
	@RequiresPermissions("activity:read")
	@RequestMapping("load")
	public String load(){
		
		return "crm/sell/activity/load";
	}
	/**
	 * 查询客户活动信息
	 * @param param
	 * @param activity
	 * @return
	 */
	@RequiresPermissions("activity:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,CrmSellActivity activity,String chanceid){
		activity.setChanceid(chanceid);
		return ajaxJsonEscape(service.selectActivity(param, activity));
	}
	/**
	 * 跳转到增加页面
	 * @param id  销售机会id
	 * @return
	 */
	@RequiresPermissions("activity:add")
	@RequestMapping("addPage")
	public String addPage(String id,ModelMap map){
		//机会信息
		Chance c = service.get(Chance.class, id);
		System.out.println(c.getId()+"机会ID");
		if(c==null){
			return NODATA;
		}
		map.put("c",c);
		
		//客户信息
		Info info = service.get(Info.class,c.getScInfo());
	
		if(info!=null){
			map.put("info",info);
		}
		//联系人信息
		CrmClientLinkman man = service.get(CrmClientLinkman.class,c.getScLinkman());
		
		if(man!=null){
			map.put("man",man);
		}
		
		return "crm/sell/activity/add";
	}
	/**
	 * 增加客户活动
	 * @param ac
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("activity:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid CrmSellActivity ac,Errors errors){
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		return ajaxDone(service.addActivity(ac));
	}
	@RequestMapping("find")
	public String find(String id,ModelMap map){
		//客户活动信息
		CrmSellActivity ac = service.get(CrmSellActivity.class, id);
		if(ac==null){
			return NODATA;
		}
		map.addAttribute("ac",ac);
		//销售机会信息
		Chance ch = service.get(Chance.class,ac.getChanceid());
		if(ch!=null){
			map.addAttribute("ch",ch);
		}
		//客户信息
		Info info = service.get(Info.class,ac.getAcDanwei());
		if(info!=null){
			map.addAttribute("info",info);
		}
		return "crm/sell/activity/find";
		
	}
}
