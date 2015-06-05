/**
 * 
 */
package com.oa.manager.personnel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.model.FileList;
import com.oa.commons.model.Member;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.personnel.bean.Deal;
import com.oa.manager.personnel.service.IDealService;

/**
 * 
 * 类名：DealAction
 * 功能：人事管理--合同
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2013年6月7日 10:06:46
 *
 */
@Controller
@RequestMapping("/deal")
public class DealAction  extends BaseAction{
	
	@Autowired
	private IDealService service;
	
	/**
	 * 通知上传的保存路径
	 */
	private static final String SAVEPATH=BaseConfig.uploadPath+"deal";
	/**
	 * 加载
	 * @return
	 */
	@RequiresPermissions("deal:read")
	@RequestMapping("load")
	public String load() {
		
		return "personnel/deal/query";
	}
	@RequiresPermissions("deal:read")
	@RequestMapping("query")
	public ModelAndView query(PageParam param, Deal j) {
		return ajaxJsonEscape(service.select(param,j));

	}
	
	
	
	/**
	 * 跳转到招聘添加页面
	 * @return
	 */
	@RequiresPermissions("deal:add")
	@RequestMapping("addPage")
	public String addPage(ModelMap map) {
		
		return "personnel/deal/add";

	}
	/**
	 * 添加招聘信息
	 * @param p
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("deal:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid Deal d, Errors errors,HttpServletRequest request,FileList files) {
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		Member me = ServletUtil.getMember();
		d.setPdStaffId(me.getId());
		return ajaxDone(service.add (d,SAVEPATH,request,files));

	}
	/**
	 * 跳转到查看页面
	 * @param param
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("deal:read")
	@RequestMapping("find")
	public String find( String id, ModelMap map) {
		map.addAllAttributes(service.selectID(id));
		if(map.get("deal")==null){
			return NODATA;
		}
		return "personnel/deal/find";
	}
	/**
	 * 跳转到修改页面
	 * @param param
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("deal:update")
	@RequestMapping("updatePage")
	public String updatePage( String id, ModelMap map) {
		map.addAllAttributes(service.selectID(id));
		if(map.get("deal")==null){
			return NODATA;
		}
		return "personnel/deal/update";
	}
	/**
	 * 修改招聘信息
	 * @param s
	 * @param errors
	 * @return
	 */
	@RequiresPermissions("deal:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid Deal d, Errors errors,HttpServletRequest request,FileList files) {
		if (errors.hasErrors()) {
			ModelAndView mav = getValidationMessage(errors);
			if (mav != null)
				return mav;
		}
		
		Member me = ServletUtil.getMember();
		d.setPdStaffId(me.getId());
		return ajaxDone(service.update(d,SAVEPATH,request,files));
	}
	
	/**
	 * 删除招聘
	 */
	@RequiresPermissions("deal:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.delete(ids));
	
	}
	
	/**
	 * 文件下载
	 * @param id 文件保存时名称
	 * @param name 文件原名
	 * @param ext  文件后缀
	 * @param request  
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/download")   
    public String down(String newName,String name,String ext,HttpServletRequest request,HttpServletResponse response){  
    	return FileUtils.down(SAVEPATH, name, newName, ext,request,response);
        
    }  
	@RequestMapping("delFile")
	public ModelAndView delFile(String id){
		
		return ajaxDone(service.deleteFile(id,SAVEPATH));
	}
	
}
