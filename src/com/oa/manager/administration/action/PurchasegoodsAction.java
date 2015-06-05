/**  
 * @Project: jxoa
 * @Title: PurchasegoodsAction.java
 * @Package com.oa.manager.administration.action
 * @date 2013-6-18 下午2:40:45
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
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.administration.bean.BiBasicinformation;
import com.oa.manager.administration.bean.BiShopping;
import com.oa.manager.administration.service.IPurchasegoodsService;


/**
 * 
 * 类名：PurchasegoodsAction
 * 功能：行政办公--物品购买
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-18 下午2:40:45
 *
 */
@Controller
@RequestMapping("purchasegoods")
public class PurchasegoodsAction extends BaseAction  {
		@Autowired
		private IPurchasegoodsService ipurchasegoodsservice;
		/**
		 * 跳转到查询物品购买
		 * @return
		 */
		@RequiresPermissions("shop:read")
		@RequestMapping("load")
		public String load(){

			 return "administration/officemanagement/purchasegoods/load";
		}
		@RequiresPermissions("shop:read")
		@RequestMapping("loadall")
		public ModelAndView loadall(BiShopping bs,PageParam param,Date startDate,Date endDate){
			
			return ajaxJsonEscape(ipurchasegoodsservice.load(bs,param,startDate,endDate));
			
		}
		
		/**
		 * 跳转到增加物品页面  
		 * @return
		 */
		@RequiresPermissions("shop:add")
		@RequestMapping("addPage")
		public String addPage(){
			return "administration/officemanagement/purchasegoods/add";
		}
		/**
		 * 增加物品购买
		 * @param bs
		 * @param errors
		 * @return
		 */
		@RequiresPermissions("shop:add")
		@RequestMapping("add")
		public ModelAndView add(@Valid BiShopping bs,Errors errors){
			if(errors.hasErrors()) {  
				ModelAndView mav=getValidationMessage(errors);
				if(mav!=null)return mav;
		    }
			Member me=ServletUtil.getMember();
			bs.setUserid(me.getId());
			
			bs.setCreatedate(DateUtil.currentTimestamp());
			bs.setTotalmonety(bs.getUnitprice()*bs.getAmount());
			
			return  ajaxDone(ipurchasegoodsservice.saveshopping(bs));
		}
		/**
		 * 跳转到修改页面      
		 * @param id
		 * @return
		 */
		@RequiresPermissions("shop:update")
		@RequestMapping("updatePage")
		public String updatePage(String id,ModelMap map){
			BiShopping bs=ipurchasegoodsservice.get(BiShopping.class, id);
			if(bs==null){
				return NODATA;
			}
			BiBasicinformation bi=ipurchasegoodsservice.get(BiBasicinformation.class, bs.getUsingnameId());
			if(bi!=null){
				map.addAttribute("bi",bi);
			}
			map.addAttribute("bs",bs);
			return "administration/officemanagement/purchasegoods/update";
		}
		/**
		 * 修改物品购买信息
		 * @param bs
		 * @param errors
		 * @return
		 */
		@RequiresPermissions("shop:update")
		@RequestMapping("update")
		public ModelAndView update(@Valid BiShopping bs,Errors errors){
			if(errors.hasErrors()) {  
				ModelAndView mav=getValidationMessage(errors);
				if(mav!=null)return mav;
		    }
			return ajaxDone(ipurchasegoodsservice.updateshop(bs));         
		} 
		/**
		 * 批量删除
		 * @param ids
		 * @return
		 */
		@RequiresPermissions("shop:delete")
		@RequestMapping("deletes")
		public ModelAndView deletes(String[] ids){
			  return ajaxDone(ipurchasegoodsservice.deletes(ids));
		}
		/**
		 * 查看详情页面
		 * @param id
		 * @param map
		 * @return
		 */
		@RequestMapping("lookPage")
		public String lookPage(String id,ModelMap map){
			BiShopping bs=ipurchasegoodsservice.get(BiShopping.class, id);
			if(bs==null){
				return NODATA;
			}
			BiBasicinformation bi=ipurchasegoodsservice.get(BiBasicinformation.class, bs.getUsingnameId());
			if(bi!=null){
				map.addAttribute("bi",bi);
			}
			map.addAttribute("bs",bs);
			return "administration/officemanagement/purchasegoods/show";
		}
		
}
                                                                                                      