/**  
 * @Title: DbBackupRes.java
 * @date 2013-8-22 下午2:28:18
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.oa.commons.exception.MyRuntimeException;

/**
 * 数据库备份恢复
 * @author	LiuJincheng
 * @version	 1.0
 *
 */
public class DbBackupRes {
	
	private static Logger logger = Logger.getLogger(DbBackupRes.class);
	
	/**
	 * 备份执行的语句
	 */
	public static  String backupSql;
	/**
	 * 恢复执行的语句
	 */
	public static String restoreSql;
	/**
	 * 数据库执行文件位置
	 */
	public static String dir;
	/**
	 * 备份文件保存位置
	 */
	public static String savepath;
	
	/**
	 * 备份数据 
	 * @param fileName 保存的文件名称
	 */
	public static void backupDatabase(String fileName){
	
	      try{
	  			File file=new File(savepath);
	  			if(!file.exists())file.mkdirs();//目录不存在创建目录
	  			Runtime.getRuntime().exec("cmd /c "+backupSql+savepath+"/"+fileName,null,new File(dir));
	  			System.out.println("备份完成");
	  			
	      } catch (Exception e) {   
	    	  
	    	  logger.error(e);
	    	  
	    	  System.out.println("备份失败");
	   
	    	  throw new MyRuntimeException("数据库备份失败");
	    	  
	      }
	     
		
	}
	/**
	 * 恢复数据
	 * @param file
	 */
	public static void restoreDatabase(File file){
		try{
			System.out.println("==="+restoreSql+file.getPath());
  			Runtime.getRuntime().exec("cmd /c "+restoreSql+file.getPath(),null,new File(dir));
  			System.out.println("恢复完成");
      
        } catch (Exception e) {   
    	  
    	  logger.error(e);
    	  
    	  System.out.println("恢复失败");
   
    	  throw new MyRuntimeException("数据库恢复失败");
    	  
       }
	}
	
	
	
	
}
