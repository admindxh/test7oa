/**
 * 
 */
package com.oa.manager.personnel.action;

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
import com.oa.manager.personnel.bean.Staff;
import com.oa.manager.personnel.service.IStaffService;

/**
 * 
 * 类名：StaffAction
 * 功能：人事管理--人员管理
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年6月7日 10:06:46
 *
 */
@Controller
@RequestMapping("/staff")
public class StaffAction  extends BaseAction{
	
	@Autowired
	private IStaffService service;
	
	
	/**
	 * 加载
	 * @return
	 */
	@RequiresPermissions("staff:read")
	@RequestMapping("load")
	public String load( Staff s ,Date staDate,Date endDate , ModelMap map) {
		return "personnel/staff/load";
	}
	/**
	 * 加载在职员工
	 * @return
	 */
	@RequiresPermissions("staff:work")
	@RequestMapping("work")
	public String work() {
		
		return "personnel/staff/work";
	}
	@RequiresPermissions("staff:work")
	@RequestMapping("queryWork")
	public ModelAndView queryWork(PageParam param, Staff s,Date staDate,Date endDate) {
	
		return ajaxJsonEscape(service.selectStaff(param,s,staDate,endDate,"在职"));

	}
	/**
	 * 加载离职员工
	 * @return
	 */
	@RequiresPermissions("staff:res")
	@RequestMapping("res")
	public String res() {
		return "personnel/staff/res";
	}
	@RequiresPermissions("staff:res")
	@RequestMapping("queryRes")
	public ModelAndView queryRes(PageParam param, Staff s,Date staDate,Date endDate) {
	
		return ajaxJsonEscape(service.selectStaff(param,s,staDate,endDate,"离职"));

	}
	/**
	 * 加载退休员工
	 * @return
	 */
	@RequiresPermissions("staff:ret")
	@RequestMapping("ret")
	public String ret() {
		return "personnel/staff/ret";
	}
	@RequiresPermissions("staff:ret")
	@RequestMapping("queryRet")
	public ModelAndView queryRet(PageParam param, Staff s,Date staDate,Date endDate) {
	
		return ajaxJsonEscape(service.selectStaff(param,s,staDate,endDate,"退休"));

	}
	
	/**
	 * 跳转到高级检索
	 * @return
	 */
	@RequestMapping("searchTag")
	public String searchTag(String search,ModelMap map) {
		map.put("search", search);
		return "personnel/staff/search";

	}
	/**
	 * 跳转到员工添加页面
	 * @return
	 */
	@RequiresPermissions("staff:add")
	@RequestMapping("staffAdd")
	public String staffAdd() {
		return "personnel/staff/add";

	}
	/**
	 * 添加员工信息
	 * @param p
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("staff:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Staff p, Errors errors) {
	
		if (errors.hasErrors()) {

			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		String status=p.getPsStatus();
		if(status.equals("在职")){
			p.setPsResReason("");
			p.setPsResDate(null);
			p.setPsResRemark("");
			p.setPsRetReason("");
			p.setPsRetDate(null);
			p.setPsRetRemark("");
		}else if(status.equals("离职")){
			p.setPsRetReason("");
			p.setPsRetDate(null);
			p.setPsRetRemark("");
		}else if(status.equals("退休")){
			p.setPsResReason("");
			p.setPsResDate(null);
			p.setPsResRemark("");
		}
		Member me = ServletUtil.getMember();
		p.setPsUid(me.getId());
		p.setPsInDate(DateUtil.currentTimestamp());
		return ajaxDone(service.addStaff(p));

	}
	/**
	 * 跳转到查看页面
	 * @param param
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("staff:read")
	@RequestMapping("find")
	public String find(String id, ModelMap map) {

		map.addAllAttributes(service.updatePage(id));
		if(map.get("ps")==null){
			return NODATA;
		}
		return "personnel/staff/find";
	}
	/**
	 * 跳转到修改页面
	 * @param param
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("staff:update")
	@RequestMapping("updatePage")
	public String updatePage(String id, ModelMap map) {

		map.addAllAttributes(service.updatePage(id));
		if(map.get("ps")==null){
			return NODATA;
		}
		return "personnel/staff/update";
	}
	/**
	 * 修改人员
	 * @param s
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("staff:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Staff p, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		String status=p.getPsStatus();
		if(status.equals("在职")){
			p.setPsResReason("");
			p.setPsResDate(null);
			p.setPsResRemark("");
			p.setPsRetReason("");
			p.setPsRetDate(null);
			p.setPsRetRemark("");
		}else if(status.equals("离职")){
			p.setPsRetReason("");
			p.setPsRetDate(null);
			p.setPsRetRemark("");
		}else if(status.equals("退休")){
			p.setPsResReason("");
			p.setPsResDate(null);
			p.setPsResRemark("");
		}
		return ajaxDone(service.update(p));
	}
	
	/**
	 * 删除
	 */
	@RequiresPermissions("staff:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
	
		return ajaxDone(service.deleteStaff(ids));
	
	}
	
	/**
	 * 跳转到员工状态更新页面
	 * @return
	 */
	@RequestMapping("updateStatusPage")
	public String updateStatusPage() {
		
		return "personnel/staff/updateStatus";

	}
	/**
	 * 修改人员状态
	 * @param s
	 * @param errors
	 * @return
	 */
	
	@RequestMapping("updateStatus")
	public ModelAndView updateStatus(@Valid Staff p, Errors errors) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		String status=p.getPsStatus();
		if(status.equals("在职")){
			p.setPsResReason("");
			p.setPsResDate(null);
			p.setPsResRemark("");
			p.setPsRetReason("");
			p.setPsRetDate(null);
			p.setPsRetRemark("");
		}else if(status.equals("离职")){
			p.setPsRetReason("");
			p.setPsRetDate(null);
			p.setPsRetRemark("");
		}else if(status.equals("退休")){
			p.setPsResReason("");
			p.setPsResDate(null);
			p.setPsResRemark("");
		}
		return ajaxDone(service.updateStatus(p));
	}
	
}
