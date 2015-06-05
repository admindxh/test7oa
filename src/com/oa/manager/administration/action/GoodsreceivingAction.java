/**  
 * @Project: jxoa
 * @Title: GoodsreceivingAction.java
 * @Package com.oa.manager.administration.action
 * @date 2013-6-18 下午2:43:19
 * @Copyright: 2013 
 */
package com.oa.manager.administration.action;

import java.util.Date;

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
import com.oa.manager.administration.bean.BiBasicinformation;
import com.oa.manager.administration.bean.BiGoodsreceiving;
import com.oa.manager.administration.service.IGoodsreceivingService;

/**
 * 
 * 类名：GoodsreceivingAction
 * 功能：物品领用
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-18 下午2:43:19
 *
 */
@Controller
@RequestMapping("goodsreceiving")
public class GoodsreceivingAction extends BaseAction  {
	@Autowired
	private IGoodsreceivingService   igoodsreceivingService;
	/**
	 *跳转到物品领用界面
	 * @param bv
	 * @param param
	 * @return
	 */
	@RequiresPermissions("receive:read")
	@RequestMapping("load")
	public String load(){
		
		return "administration/officemanagement/goodsreceiving/load";
	}
	/**
	 * 分页查询物品领用信息
	 * @return
	 */
	@RequiresPermissions("receive:read")
	@RequestMapping("loadall")
	public ModelAndView loadall(BiGoodsreceiving bv,PageParam param,Date startDate,Date endDate){
		
		return ajaxJsonEscape(igoodsreceivingService.load(bv,param,startDate,endDate));
	}
	
	
	/**
	 * 跳到添加页面         
	 * @return
	 */
	@RequiresPermissions("receive:add")
	@RequestMapping("addPage")
	public String addPage(){
		return "administration/officemanagement/goodsreceiving/add";
	}
	/**
	 * 增加物品领用
	 * @param bv
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("receive:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid BiGoodsreceiving bv,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(igoodsreceivingService.savereceving(bv));
	}
	/**
	 * 跳转到修改页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("receive:update")
	@RequestMapping("updatePage")
	public String upadatePage(String id,ModelMap map){
		BiGoodsreceiving bv=igoodsreceivingService.get(BiGoodsreceiving.class, id);
		if(bv==null){
			return NODATA;
		}
		BiBasicinformation bi=igoodsreceivingService.get(BiBasicinformation.class, bv.getUsingnameid());
		if(bi!=null){
			map.addAttribute("bi",bi);
		}
		map.addAttribute("bv",bv);
		return "administration/officemanagement/goodsreceiving/update";
	}
	/**
	 * 修改物品领用
	 * @param bv
	 * @param errors
	 * @param num
	 * @return
	 */
	@RequiresPermissions("receive:update")
	@RequestMapping("update")
	 public ModelAndView update(@Valid BiGoodsreceiving bv,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }	
		return ajaxDone(igoodsreceivingService.updatereceving(bv));
		
	} 
	/**
	 * 批量删除
	 * @param ids    
	 * @return
	 */
	@RequiresPermissions("receive:delete")
	@RequestMapping("deletes")
	public ModelAndView deletes(String[] ids){
		return ajaxDone(igoodsreceivingService.deletes(ids));
	}
	@RequestMapping("lookPage")
	public String lookPage(String id,ModelMap map){
		BiGoodsreceiving bv=igoodsreceivingService.get(BiGoodsreceiving.class, id);
		if(bv==null){
			return NODATA;
		}
		BiBasicinformation bi=igoodsreceivingService.get(BiBasicinformation.class, bv.getUsingnameid());
		if(bi!=null){
			map.addAttribute("bi",bi);
		}
		
		map.addAttribute("bv",bv);
		return "administration/officemanagement/goodsreceiving/show";
	}
}
