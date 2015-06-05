/**  
 * @Project: jxoa
 * @Title: BaseConfig.java
 * @Package com.oa.commons.base
 * @date 2013-3-28 下午3:20:50
 * @Copyright: 2013 
 */
package com.oa.commons.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.ServletContextAware;

import com.oa.commons.util.DateUtil;
import com.oa.commons.util.DbBackupRes;
import com.oa.commons.util.ServletUtil;

/**
 * 
 * 类名：BaseConfig
 * 功能：基本配置文件加载
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-3-28 下午3:20:50
 *
 */

public class BaseConfig  implements ServletContextAware{
	
	private static Logger logger = Logger.getLogger(BaseConfig.class);
	
	/**
	 * classPath 路径
	 */
	public static String classPath="";
	/**
	 * web跟目录 webroot
	 */
	public static String webPath="";

	/**
	 * 一般上传文件的跟目录
	 */
	public static String uploadPath="";
	
	private ServletContext servletContext;
	
	/**
	 * 将webconfig配置文件转换成java对象 
	 */
	public static final  WebConfig webconfig = new WebConfig();
	
	
	/**
	 * 开发人员id
	 */
	private  String devName;
	/**
	 * 超级管理员id
	 */
	private  String saName;
	
	public static final MasConfig masconfig = new MasConfig(); 
	
	/**
	 * 将SerialConfig配置文件转换成java对象 
	 */
	public static final  SerialConfig serialconfig = new SerialConfig();
 	//私有的默认构造子
	private BaseConfig(){} 
	 //获取spring创建的bean对象
    public synchronized static BaseConfig getInstance() {
    	
        return ServletUtil.getApplicationContext().getBean(BaseConfig.class);
    }
	
	/**
	 * 初始化加载ini配置文件
	 * 
	 * @throws IOException
	 */
     
	public  void load()throws Exception{
		
		classPath=this.getClass().getResource("/").getPath();//获取classpath路径
		
		webPath= servletContext.getRealPath("/");//获取webroot跟目录;
		
		//设置一般文件上传目录
		uploadPath=webPath+"/upfiles/";
		
		System.out.println("webPath===="+webPath);
		
		
		
		//加载webconfig配置文件
		
		Map<String,String> map=readProperties("/config/webconfig.properties");
		//将配置文件信息赋值到 webconfig
		

		WebConfig web =BaseConfig.webconfig;
		
		web.setAllowIps(map.get("allow_ips"));
		web.setAppStart(Integer.parseInt(map.get("app_start")));
		web.setLimitIps(map.get("limit_ips"));
		web.setLoginEndTime(map.get("login_endTime"));
		web.setLoginStartTime(map.get("login_startTime"));
		web.setMsgwarnTime(Integer.parseInt(map.get("msgwarn_time")));
		web.setPwdErrorNum(Integer.parseInt(map.get("pwd_error_num")));
		web.setPwdErrorTime(Integer.parseInt(map.get("pwd_error_time")));
		
		//加载移动mas短信接口配置文件
		Map<String,String> masmap=readProperties("/config/masconfig.properties");
        //将配置文件信息赋值到webconfig
		MasConfig mas = BaseConfig.masconfig;
		mas.setOpen(masmap.get("open"));
		mas.setLpw(masmap.get("lpw"));
		mas.setLuser(masmap.get("luser"));
		mas.setOrgaddr(masmap.get("orgaddr"));
		mas.setPw(masmap.get("pw"));
		mas.setServiceid(masmap.get("serviceid"));
		mas.setUrl(masmap.get("url"));
		mas.setUser(masmap.get("user"));
		
	
		//初始化数据库备份恢复
		Map<String,String> db=readProperties("/config/db-backup-restore.properties");
		
		DbBackupRes.backupSql=db.get("backup-sql");
		DbBackupRes.restoreSql=db.get("restore-sql");
		DbBackupRes.dir=db.get("dir");
		DbBackupRes.savepath=db.get("savepath");
		
		//加载配置文件
		Map<String,String> serialmap=readProperties("/config/serialconfig.properties");
        //将配置文件信息赋值到webconfig
		SerialConfig serial = BaseConfig.serialconfig;
		serial.setClientname(serialmap.get("clientname"));
		serial.setClientcode(serialmap.get("clientcode"));
		serial.setValidstart(DateUtil.string2Date(serialmap.get("validstart")).getTime());
		serial.setValidend(DateUtil.string2Date(serialmap.get("validend")).getTime());
		serial.setValidday(serialmap.get("validday"));
		serial.setKey(serialmap.get("key"));
		serial.setUsetime(serialmap.get("usetime"));
		
	}
	
	/**
	 * 将properties配置文件转换为Map键值对
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public Map<String,String>  readProperties(String path) throws IOException{
		
		InputStream is = getClass().getResourceAsStream(path);
		Properties prop = new Properties();
		prop.load(is);
		is.close();
		return (Map)prop;
		
	}
	
	public synchronized boolean updateWebconfig(WebConfig newconf){
		InputStream is = null;   
        OutputStream fos = null; 
		try {
			BeanUtils.copyProperties(newconf,BaseConfig.webconfig);//修改属性对象
			
			//修改配置文件值
			is = getClass().getResourceAsStream("/config/webconfig.properties");
			System.out.println("==="+is);
			Properties prop = new Properties();
			prop.load(is);
			is.close();//修改之前必须关闭
			prop.setProperty("allow_ips", newconf.getAllowIps());
			prop.setProperty("app_start", ""+newconf.getAppStart());
			prop.setProperty("limit_ips", newconf.getLimitIps());
			prop.setProperty("login_endTime", newconf.getLoginEndTime());
			prop.setProperty("login_startTime", newconf.getLoginStartTime());
			prop.setProperty("msgwarn_time", ""+newconf.getMsgwarnTime());
			prop.setProperty("pwd_error_num", ""+newconf.getPwdErrorNum());
			prop.setProperty("pwd_error_time", ""+newconf.getPwdErrorTime());
			
			fos=new FileOutputStream(classPath+"/config/webconfig.properties");
			prop.store(fos, null);
			fos.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}finally{   
            try {   
                fos.close();   
                is.close();   
            } catch (IOException e) {   
                // TODO Auto-generated catch block   
                e.printStackTrace();   
            }   
        }   

	}

	public synchronized boolean updateMasconfig(MasConfig newconf){
		InputStream is = null;   
        OutputStream fos = null; 
		try {
			BeanUtils.copyProperties(newconf,BaseConfig.masconfig);//修改属性对象
			
			//修改配置文件值
			is = getClass().getResourceAsStream("/config/masconfig.properties");
			System.out.println("==="+is);
			Properties prop = new Properties();
			prop.load(is);
			is.close();//修改之前必须关闭
			prop.setProperty("open", newconf.getOpen());
			prop.setProperty("lpw", newconf.getLpw());
			prop.setProperty("luser", newconf.getLuser());
			prop.setProperty("orgaddr", newconf.getOrgaddr());
			prop.setProperty("pw", newconf.getPw());
			prop.setProperty("serviceid", newconf.getServiceid());
			prop.setProperty("url", newconf.getUrl());
			prop.setProperty("user", newconf.getUser()); 
			
			fos=new FileOutputStream(classPath+"/config/masconfig.properties");
			prop.store(fos, null);
			fos.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}finally{   
            try {   
                fos.close();   
                is.close();   
            } catch (IOException e) {   
                // TODO Auto-generated catch block   
                e.printStackTrace();   
            }   
        }   

	}
	public String getDevName() {
		return devName;
	}
	public String getSaName() {
		return saName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public void setSaName(String saName) {
		this.saName = saName;
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		 this.servletContext=arg0;
	}
	
	
	
	
}
