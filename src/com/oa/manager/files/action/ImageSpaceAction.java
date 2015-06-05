/**  
 * @Title PersonalGalleryAction.java
 * @date 2013-12-25 下午4:38:01
 * @Copyright: 2013 
 */
package com.oa.manager.files.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.files.bean.ImageSpace;
import com.oa.manager.files.bean.ImageSpaceImg;
import com.oa.manager.files.service.ImageSpaceService;

/**
 * 图库空间管理
 * @author LiuJincheng
 * @version 1.0
 */
@Controller
@RequestMapping("/imageSpace")
public class ImageSpaceAction extends BaseAction{
	
	@Autowired
	private ImageSpaceService service; 
	/**
	 * 文件保存的目录
	 */
	public static final String SAVEPATH="image_space";
	//定义图片类型后缀,只允许上传此类型的图片
	public static final String[] IMGTYPE=new String[]{"png","jpg","jpeg","gif"};
	//默认word中图片保存的类型标识 
	public static final short  WORD_IMG_TYPE=1;
	//默认word中图片保存的类型 名称
	public static final String  WORD_IMG_NAME="word图片转存";
	//默认word中图片保存的类型 描述
	public static final String  WORD_IMG_REMARK="系统自动创建，word图片转存的保存目录！";
	/**
	 * 图片空间首页
	 * @return
	 */
	@RequestMapping("load")
	public String load(PageParam param,ImageSpaceImg m,ModelMap map){
		m.setUserId(ServletUtil.getMember().getId());
		
		map.addAttribute("list",service.selectImg(param, m));
		map.addAttribute("total",service.selectImgCount(m));
		
		return "files/imageSpace/load";
	}
	/**
	 * 图片管理页面
	 * @return
	 */
	@RequestMapping("loadGl")
	public String loadGl(){
		
		return "files/imageSpace/query";
	}
	/**
	 * 条件 分页查询图片
	 * @param param
	 * @param m
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("query")
	public ModelAndView query(PageParam param,ImageSpaceImg m,Date startDate,Date endDate){
	
		m.setUserId(ServletUtil.getMember().getId());
		
		return ajaxJsonEscape(service.selectImg(param, m, startDate, endDate));
	}
	
	/**
	 * 条件 分页查询图片
	 * @param param
	 * @param m
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping("view/query")
	public String viewQuery(PageParam param,ImageSpaceImg m,Date startDate,Date endDate,ModelMap map){
	
		m.setUserId(ServletUtil.getMember().getId());
		
		map.addAttribute("list",service.selectImg(param, m));
		
		
		return "files/imageSpace/view/img_list";
	}
	
	
	/**
	 * 添加图片页面
	 * @return
	 */
	@RequestMapping("addPage")
	public String addFilePage(){
		
		return "files/imageSpace/add";
	
	}
	/**
	 * 添加图片
	 * @param m
	 * @param errors
	 * @param file
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add(@Valid ImageSpaceImg m,Errors errors,@RequestParam MultipartFile file){
		
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		if(file.isEmpty()){
			return ajaxDoneError();
		}
		ModelAndView view=FileUtils.validateFile(file, 1024L*1024*10, IMGTYPE, null);
		if(view!=null)return view;
		return ajaxDone(service.addImg(m, SAVEPATH, file));
	}
	/**
	 * 查看图片页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("show")
	public String show(String id,ModelMap map){
		ImageSpaceImg m=service.get(ImageSpaceImg.class, id);
		if(m==null){
			return NODATA;
		}
		map.addAttribute("m",m);
		
		return "files/imageSpace/show";
		
	}
	/**
	 * 修改图片页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		ImageSpaceImg m=service.get(ImageSpaceImg.class, id);
		if(m==null){
			return NODATA;
		}
		if(!ServletUtil.getMember().getId().equals(m.getUserId())){
			return NOPOWER;
		}
		map.addAttribute("m",m);
		
		return "files/imageSpace/update";
		
	}
	/**
	 * 修改图片
	 * @param m
	 * @param errors
	 * @return
	 */
	@RequestMapping("update")
	public ModelAndView update(@Valid ImageSpaceImg m,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.updateImg(m));
	}
	/**
	 * 删除图片
	 * @param ids
	 * @return
	 */
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteImg(ids));
	}
	
	
	
	
	
	@RequestMapping("type/load")
	public String loadType(){
		
		return "files/imageSpace/type/load";
	}
	
	@RequestMapping("type/query")
	public ModelAndView query(PageParam param,ImageSpace m){
		
		m.setUserId(ServletUtil.getMember().getId());
		return ajaxJsonEscape(service.selectType(param, m));
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("type/addPage")
	public String addTypePage(){
		
		return "files/imageSpace/type/add";
	}
	
	@RequestMapping("type/add")
	public ModelAndView addType(@Valid ImageSpace m,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		
		return ajaxDone(service.addType(m));
		
	}
	
	@RequestMapping("type/updatePage")
	public String updateTypePage(String id,ModelMap map){
		ImageSpace m=service.get(ImageSpace.class, id);
		if(m==null){
			return NODATA;
		}
		map.addAttribute("m",m);
		
		return "files/imageSpace/type/update";
	}
	
	@RequestMapping("type/update")
	public ModelAndView updateType(@Valid ImageSpace m,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		return ajaxDone(service.updateType(m));
		
	}
	
	@RequestMapping("type/del")
	public ModelAndView deleteType(String[] ids){
		
		return ajaxDone(service.deleteTypes(ids));
	
	}
	
	/**
	 * 下拉列表 查询出图库类型的名称 id 
	 * @return
	 */
	@RequestMapping("type/list")
	public ModelAndView typeList(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		map.put("list", service.selectTypeList(ServletUtil.getMember().getId()));
		
		return ajaxJsonEscape(map);
	}
	/**
	 * 百度编辑器 专用 图片上传
	 * @param m
	 * @param errors
	 * @param dir
	 * @param pictitle
	 * @param file
	 * @return
	 */
	@RequestMapping("ueditor/add")
	public ModelAndView addForUeditor(String dir,String pictitle,@RequestParam MultipartFile file){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state", "SUCCESS");
		if(file.isEmpty()){
			map.put("state", "未包含上传的图片");
		}
		//验证上传文件类型
		String type=file.getContentType();
    	if(type==null||"application/octet-stream".equals(type)){
    		type=FileUtils.getFileExt(file.getOriginalFilename());//获取文件后缀
    	}
    	if(type!=null){
    		boolean has=false;
    		for(String t:IMGTYPE){
    			if(t.equals(type)){
    				has=true;
    				break;
    			}
    		}
    		if(!has){
    			map.put("state", "只能上传图片类型,例：文件后缀为:png,jpg,jpeg,gif格式的图片!");
    		}
    	}else{
    		map.put("state", "只能上传图片类型,例：文件后缀为:png,jpg,jpeg,gif格式的图片!");
    	}
		ImageSpaceImg img=new ImageSpaceImg();
		img.setName(pictitle);
		img.setType(dir);
	
		if(!service.addImg(img, SAVEPATH, file)){
			map.put("state", "文件上传失败");
		}
		map.put("title", pictitle);
		map.put("original", file.getOriginalFilename());
		map.put("url",img.getSavePath() );
		
		return ajaxJsonEscape(map);
	}
	/**
	 * 百度编辑器 专用 图片上传 word图片转存
	 * @param m
	 * @param errors
	 * @param dir
	 * @param pictitle
	 * @param file
	 * @return
	 */
	@RequestMapping("ueditor/wordImage/add")
	public ModelAndView addForUeditorWordImg(String pictitle,@RequestParam MultipartFile file){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state", "SUCCESS");
		if(file.isEmpty()){
			map.put("state", "未包含上传的图片");
		}
		//验证上传文件类型
		String type=file.getContentType();
    	if(type==null||"application/octet-stream".equals(type)){
    		type=FileUtils.getFileExt(file.getOriginalFilename());//获取文件后缀
    	}
    	if(type!=null){
    		boolean has=false;
    		for(String t:IMGTYPE){
    			if(t.equals(type)){
    				has=true;
    				break;
    			}
    		}
    		if(!has){
    			map.put("state", "只能上传图片类型,例：文件后缀为:png,jpg,jpeg,gif格式的图片!");
    		}
    	}else{
    		map.put("state", "只能上传图片类型,例：文件后缀为:png,jpg,jpeg,gif格式的图片!");
    	}
		ImageSpaceImg img=new ImageSpaceImg();
		img.setName(pictitle);
		img.setType(service.addDefaultType(ServletUtil.getMember().getId(), WORD_IMG_TYPE, WORD_IMG_NAME, WORD_IMG_REMARK));
	
		if(!service.addImg(img, SAVEPATH, file)){
			map.put("state", "文件上传失败");
		}
		map.put("title", pictitle);
		map.put("original", file.getOriginalFilename());
		map.put("url",img.getSavePath() );
		
		return ajaxJsonEscape(map);
	}
	
	@RequestMapping("ueditor/imageManager")
	public ModelAndView imageManager(PageParam param,ImageSpaceImg m){
		param.setRows(10);//每次查询10条图片
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		
		m.setUserId(ServletUtil.getMember().getId());
		List<ImageSpaceImg> list=service.selectImg(param, m);
		List<String> paths=new ArrayList<String>();
		for(ImageSpaceImg img:list){
			paths.add(img.getSavePath());
		}
		
		return ajaxText(StringUtils.join(paths,"ue_separate_ue"));
	}
	/**
	 * 图片查找带回
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(){
		
		
		//默认单选
		return "files/imageSpace/lookup";
	}
	
	
}
