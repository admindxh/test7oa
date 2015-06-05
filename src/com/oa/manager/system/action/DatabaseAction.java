/**  
 * @Title: DatabaseAction.java
 * @date 2013-8-22 下午3:52:29
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.DataGrid;
import com.oa.commons.util.DbBackupRes;
import com.oa.commons.util.EncodeUtils;
import com.oa.commons.util.FileUtils;

/**
 * 数据库管理
 * 备份 恢复
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
@Controller
@RequestMapping("/database")
public class DatabaseAction extends BaseAction{

	/**
	 * 查询备份目录下的所有文件
	 * @param map
	 * @return
	 */
	@RequiresPermissions("database:read")
	@RequestMapping("load")
	public String load(){
		
		return "system/database/load";
	
	}
	/**
	 * 查询备份目录下的所有文件
	 * @param map
	 * @return
	 */
	@RequiresPermissions("database:read")
	@RequestMapping("query")
	public ModelAndView query(){
		List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
		File node=new File(DbBackupRes.savepath);
		File[] fs=node.listFiles();
		long cd=0;
		if(fs!=null){
			List<File> files=Arrays.asList(fs);
			
			Collections.sort(files, new Comparator<File>() {
				@Override
				public int compare(File f1, File f2) {
					if(f1.lastModified() < f2.lastModified()){	
						return 1;
					}else if(f1.lastModified() > f2.lastModified()){
						return -1;
					}
					return 0;
				}
			 
			 });
			
			for(File f:files){
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("name", f.getName());
				m.put("size", FileUtils.getHumanReadableFileSize(f.length()));
				m.put("time", f.lastModified());
				list.add(m);
				cd+=f.length();
			}
		}
		List<Map<String,Object>>footer=new ArrayList<Map<String,Object>>();
		Map<String,Object> footerMap=new HashMap<String,Object>();
		footerMap.put("name", "总文件大小:");
		footerMap.put("size", FileUtils.getHumanReadableFileSize(cd));
		footerMap.put("op", true);
		footer.add(footerMap);
		
		DataGrid data=new DataGrid();
		data.setRows(list);
		data.setTotal(0L);
		data.setFooter(footer);
		return ajaxJsonEscape(data);
	
	}
	/**
	 * 导出备份
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("database:export")
    @RequestMapping(value = "/export")   
    public String down(String name,HttpServletRequest request,HttpServletResponse response){  
    	OutputStream toClient=null;
    	try{
    		String newName=EncodeUtils.strISO2UTF(name);
    		
	    	String path=DbBackupRes.savepath+"/"+newName;
	        File file = new File(path);  
	        if(!file.exists()){
	        	//不存在
	        	request.setAttribute("name", newName);
	        	return "download_error";//返回下载文件不存在
	        }
	        response.setContentType("application/octet-stream"); 
	        // 根据不同浏览器 设置response的Header
	        String userAgent = request.getHeader("User-Agent").toLowerCase();
	        
	        if(userAgent.indexOf("msie")!=-1){
	        	//ie浏览器
	        	
	        	response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name,"ISO-8859-1")); 
	        	
	        }else{
	        	response.addHeader("Content-Disposition", "attachment;filename=" + name);  
	        }
	        
	        response.addHeader("Content-Length", ""+file.length());   
	        //以流的形式下载文件  
	        InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	        byte[] buffer = new byte[fis.available()];  
	        fis.read(buffer);  
	        fis.close();  
	        toClient = new BufferedOutputStream(response.getOutputStream());  
	        toClient.write(buffer);  
	        toClient.flush(); 
	        return null;
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  response.reset(); 
	    	  return "exception";//返回异常页面
	      }finally{
			if(toClient!=null){
	           	 try {
	           		toClient.close();
				  } catch (IOException e) {
						e.printStackTrace();
					}
	            }
			}
        
    }  
    /**
	 * 批量删除备份
	 * @param names
	 * @return
	 */
	@RequiresPermissions("database:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] names){
		
		for(String name:names){
			File file = new File(DbBackupRes.savepath+"/"+name);
			file.delete();
		}
		
		return ajaxDoneSuccess();
	}
	
	/**
	 * 恢复数据
	 * @param names
	 * @return
	 */
	@RequiresPermissions("database:restore")
	@RequestMapping("restore")
	public ModelAndView restore(String name){
		
		DbBackupRes.restoreDatabase(new File(DbBackupRes.savepath+"/"+EncodeUtils.strISO2UTF(name)));
		
		return ajaxDoneSuccess();
	}
	/**
	 * 上传恢复文件页面
	 * @return
	 */
	@RequiresPermissions("database:uploadRestore")
	@RequestMapping("uploadFilePage")
	public String uploadFilePage(){
		
		return "system/database/upload_file";
	}
	/**
	 * 上传文件被恢复数据
	 * @param file
	 * @return
	 */
	@RequiresPermissions("database:uploadRestore")
	@RequestMapping("uploadFile/restore")
	public ModelAndView uploadRestore(@RequestParam MultipartFile file){
		
		if(file.isEmpty()){
			return ajaxDoneError();
		}
		ModelAndView view=FileUtils.validateFile(file, 1024L*1024*500, null, new String[]{"exe"});
		if(view!=null)return view;
		
		File newFile=new File(DbBackupRes.savepath+"/"+file.getOriginalFilename());
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		DbBackupRes.restoreDatabase(newFile);
		
		return ajaxDoneSuccess();
	}
	/**
	 * 备份数据页面
	 * @return
	 */
	@RequiresPermissions("database:backup")
	@RequestMapping("backupPage")
	public String backupPage(){
		
		return "system/database/backup";
	}
	/**
	 * 备份数据
	 * 虽然提示成功，但是后台一直运行备份数据库，不能直接下载，需等到数据库备份完成
	 * @param name
	 * @return
	 */
	@RequiresPermissions("database:backup")
	@RequestMapping("backup")
	public ModelAndView backupDatabase(String name){
		
		DbBackupRes.backupDatabase(name);
		
		return ajaxDoneSuccess();
	}
	
}
