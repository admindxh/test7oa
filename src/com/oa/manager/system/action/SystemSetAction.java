/**  
 * @Project: jxoa
 * @Title: SystemSetAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-6-25 上午11:52:54
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.config.WebConfig;

/**
 * 
 * 类名：SystemSetAction
 * 功能：系统设置
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-25 上午11:52:54
 *
 */
@Controller
public class SystemSetAction extends BaseAction{
	
	/**
	 * 跳转到系统设置页面
	 * @return
	 */
	@RequiresPermissions("systemSet:read")
	@RequestMapping("systemSet/updatePage")
	public String setPage(ModelMap map){
		
		map.put("c",BaseConfig.webconfig);
	
		return "system/system_set/update";
	
	}
	/**
	 * 跳转到系统设置页面
	 * @return
	 */
	@RequiresPermissions("systemSet:update")
	@RequestMapping("systemSet/update")
	public ModelAndView updatePage(WebConfig newconf){
		
		if(!checkIps(newconf.getAllowIps())) {  
			ModelAndView mav = new ModelAndView(MsgConfig.PAGE_AJAXDONE);
			mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"允许的ip地址格式错误。例：192.168.1.1,192.168.1.2,或者可以用*匹配任意，例:192.168.1.*,多个ip可以用,隔开");
			return mav;
        }
		if(!checkIps(newconf.getLimitIps())) {  
			ModelAndView mav = new ModelAndView(MsgConfig.PAGE_AJAXDONE);
			mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"限制的ip地址格式错误。例：192.168.1.1,192.168.1.2,或者可以用*匹配任意，例:192.168.1.*,多个ip可以用,隔开");
			return mav;
        }
		return ajaxDone(BaseConfig.getInstance().updateWebconfig(newconf));
	
	}
	
	private boolean checkIps(String str_ips){
		
		if(StringUtils.isNotBlank(str_ips)){
			
			
			String[] ips=str_ips.split(",");
			
			for(String ip:ips){
			
				String[] z_ipd=ip.split("\\.");
				
				for(int i=0;i<z_ipd.length;i++){
					String ipd=z_ipd[i];
					if(!Pattern.matches("[0-9]*|[*]",ipd)){
						return false;
					}
					
					
				}
			}
			
		}
		return true;
	}
	
}
