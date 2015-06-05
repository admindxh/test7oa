/**  
 * @Project: jxoa
 * @Title: GoodsmanagementAction.java
 * @Package com.oa.manager.administration.action
 * @date 2013-6-17 下午4:52:25
 * @Copyright: 2013 
 */
package com.oa.manager.administration.action;

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
import com.oa.manager.administration.service.IGoodsmanagementService;

/**
 * 
 * 类名：GoodsmanagementAction
 * 功能：行政办公--物品管理                                                                                                                                       
 * 详细：
 * 作者：QinXiaohua
 * 版本：1.0
 * 日期：2013-6-17 下午4:52:25
 *
 */
@Controller
@RequestMapping("goodsmanagerment")
public class GoodsmanagementAction extends BaseAction {
	@Autowired
	private IGoodsmanagementService igoodsmanagementservice;
	/**
	 * 跳转到物品管理 
	 * @return
	 */
	@RequiresPermissions("goods:read")
	@RequestMapping("load")
	public  String load(){
	
		return "administration/officemanagement/goodsmanagement/load";
	}
	/**
	 * 查询物品信息
	 * @param param
	 * @param bi
	 * @return
	 */
	@RequiresPermissions("goods:read")
	@RequestMapping("loadall")
	public ModelAndView loadall(PageParam param,BiBasicinformation bi){
		
		return ajaxJsonEscape(igoodsmanagementservice.load(bi,param));
	}
	
	/**
	 * 跳转到物理增加页面
	 * @return
	 */
	@RequiresPermissions("goods:add")
     @RequestMapping("addPage")
     public String addPage(){
    	 return "administration/officemanagement/goodsmanagement/add";
     }
     /**
      * 添加物品管理                                                                       
      * @param bi
      * @return
      */
	@RequiresPermissions("goods:add")
     @RequestMapping("add")
     public ModelAndView add(@Valid BiBasicinformation bi,Errors errors){
    		if(errors.hasErrors()) {  
    			ModelAndView mav=getValidationMessage(errors);
    			if(mav!=null)return mav;
            }
    		Member me=ServletUtil.getMember();
    		bi.setUserid(me.getId());
    		bi.setCreatedate(DateUtil.currentTimestamp());
   
    		return ajaxDone(igoodsmanagementservice.save(bi));
     }
     /**
      * 批量删除
      * @param ids
      * @return
      */
	@RequiresPermissions("goods:delete")
     @RequestMapping("delete")
     public ModelAndView delete(String[] ids){
    	return ajaxDone(igoodsmanagementservice.deletes(ids)); 
     }
     /**
      * 跳转到物品修改页面
      * @param id
      * @param map
      * @return
      */
	@RequiresPermissions("goods:update")
    @RequestMapping("updatePage")
    public String updatePage(String id,ModelMap map){
    	BiBasicinformation bi=igoodsmanagementservice.get(BiBasicinformation.class, id);
    	if(bi==null){
    		return NODATA;
    	}
    	map.addAttribute("bi",bi );
    	return "administration/officemanagement/goodsmanagement/update";
    }
    /**
     * 修改物品管理内容
     * @param bi
     * @return
     */
	@RequiresPermissions("goods:update")
    @RequestMapping("update")
    public ModelAndView update(@Valid BiBasicinformation bi,Errors errors){
    	if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
    	
    	return ajaxDone(igoodsmanagementservice.updateBi(bi));
    } 
        
    /**
     * 查看物品管理详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("lookpage")
    public String lookpage(String id,ModelMap map){
    	BiBasicinformation bi=igoodsmanagementservice.get(BiBasicinformation.class, id);
    	if(bi==null){
    		return NODATA;
    	}
    	map.addAttribute("bi",bi);
    	return "administration/officemanagement/goodsmanagement/show";
    }
    /**
     * 跳转到查找物品名称用于查找带回
     * @param bi
     * @param param
     * @param map
     * @return
     */
    @RequestMapping("lookUpPage")
    public String lookUpPage(){
    	
		return "administration/officemanagement/goodsmanagement/lookup";
    } 
    /**
     * 查询物品名称
     */
    @RequestMapping("lookUp")
	public ModelAndView lookUp(PageParam param,BiBasicinformation bi){
		
		return ajaxJsonEscape(igoodsmanagementservice.load(bi,param));
	}

}
