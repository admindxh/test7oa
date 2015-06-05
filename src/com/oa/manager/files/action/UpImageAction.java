/**  
 * @Project: jtoa
 * @Title: UpImageAction.java
 * @Package com.oa.manager.files.action
 * @date 2014-1-17 下午6:38:14
 * @Copyright: 2014 
 */
package com.oa.manager.files.action;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.manager.files.bean.XtUpImage;
import com.oa.manager.files.bean.XtUpImageFiles;
import com.oa.manager.files.service.IUpImageService;

/**
 * 
 * 类名：UpImageAction
 * 功能：上传图片管理
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2014-1-17 下午6:38:14
 *
 */
@Controller
@RequestMapping("UpImage")
public class UpImageAction extends BaseAction {
	@Autowired
	private IUpImageService service;
	//保存路径
	public static final String SAVEPATH="up_image";
	//定义图片类型后缀,只允许上传此类型的图片
	public static final String[] IMGTYPE=new String[]{"png","jpg","jpeg","gif"};
	
	/**
	 * 上传图片管理
	 * @return
	 */
	@RequiresPermissions("UpImage:load")
	@RequestMapping("load")
	public String load(){
		
		return "files/upImage/load";
	}
	
	/**
	 * 上传图片查询
	 * @param param
	 * @param upImage
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequiresPermissions("UpImage:load")
	@RequestMapping("query")
	public ModelAndView query(PageParam param,XtUpImage m,Date startDate,Date endDate){
		
		
		return ajaxJsonEscape(service.selectImg(param, m, startDate, endDate));
	}
	/**
	 * 批量删除图片
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("UpImage:delete")
	@RequestMapping("del")
	public ModelAndView del_img(String [] ids){
		
		return ajaxDone(service.delete_img(ids, SAVEPATH));
	}
	/**
	 * 图片详情查看
	 * @param id
	 * @return
	 */
	@RequestMapping("find")
	public String find_img_info(String id,ModelMap map){
		
		XtUpImage img = service.get(XtUpImage.class, id);
		if(img==null){
			return NODATA;
		}
	    List<XtUpImageFiles> img_infoList = service.get_imgfilesList(id);
		
		map.addAttribute("infolist",img_infoList);
		map.addAttribute("img", img);
		map.addAttribute("savePath",SAVEPATH);
		return "files/upImage/find";
	}
	
	

}
