/**  
 * @Project: jxoa
 * @Title: LoginAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-4-1 下午3:16:19
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.exception.MyRuntimeException;
import com.oa.commons.model.LoginInfo;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.model.RSAPublicKeyModel;
import com.oa.commons.util.RSAUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.service.ILoginService;

/**
 * 
 * 类名：LoginAction
 * 功能：登录模块
 * 详细：用户登录
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-1 下午3:16:19
 *
 */
@Controller
@RequestMapping("/sy_login")
public class LoginAction extends BaseAction{
	
	@Autowired
	private ILoginService service; 
	
	/**
	 * 跳转到登录页面
	 * @param map
	 * @return
	 */
	@RequestMapping("")
	public String tologin(HttpSession session){
		
		Member me=ServletUtil.getMember();
		//判断当前用户是否已经登陆
		if(me!=null){
			Map<String,OnLineUser> usersMap =ServletUtil.getOnLineUsers();
			OnLineUser om=usersMap.get(me.getId());
			if(om!=null){
				Map<String,LoginInfo> loginInfos=om.getLoginInfos();
				if(loginInfos.containsKey(session.getId())){
					//用户已经登陆
					return "has_login";
				}
			}
		}
		return "login";
	}
	/**
	 * 跳转到登录页面
	 * @param map
	 * @return
	 */
	@RequestMapping("getEncryption")
	public ModelAndView getEncryption(HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		//生成加密密钥
		String pwd=UUID.randomUUID().toString();
		session.setAttribute("jmpw", pwd);
		RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd); 
		
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		map.put("modulus",publicKey.getHexModulus());
		map.put("exponent",publicKey.getHexPublicExponent());
		return ajaxJsonEscape(map);
	}
	/**
	 * 用户登录
	 * @param vercode 验证码
	 * @param name
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("go")
	public ModelAndView login(String vercode,String name,String password,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		String ps=(String)request.getSession().getAttribute("jmpw");
		 
		if(StringUtils.isNotBlank(ps)){
			//解密
			String pwd=RSAUtils.decryptStringByJs(ps,password);
			 
			return service.updateLogin(vercode,name,URLDecoder.decode(pwd, "utf-8"),request,response);
		}else{
			return ajaxDoneError("msg.login.nojmcode");//加密信息获取失败，请重新
		}
	}
	

	/**
	 * 注销登陆
	 * @return
	 */
	@RequestMapping("out")
	public String out(){
		
		System.out.println("********注销登陆******");
		Subject currentUser =SecurityUtils.getSubject();
		currentUser.getSession().setAttribute("isOut",true);
		currentUser.logout();
		return "redirect:/sy_login.do";
	}
	/**
	 * 锁定账号 返回解锁界面
	 * @param session
	 * @return
	 */
	@RequestMapping("lock")
	public String lock(HttpSession session){
		
		session.setAttribute("lock", true);//session中标识锁定，只有解锁才能进行其他操作
		
		return "locking";
	}
	/**
	 * 获取解锁 时 密码传输加密密钥
	 * @param session
	 * @return
	 */
	@RequestMapping("unlockEncryptionInfo")
	public ModelAndView unlockEncryptionInfo(HttpSession session){
		Map<String,Object> map=new HashMap<String,Object>();
		//生成加密密钥
		String pwd=UUID.randomUUID().toString();
		session.setAttribute("unlockPwd", pwd);
		RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd); 
		
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		map.put("modulus",publicKey.getHexModulus());
		map.put("exponent",publicKey.getHexPublicExponent());
		
		return ajaxJsonEscape(map);
	}
	/**
	 * 解锁
	 * @param session
	 * @return
	 */
	@RequestMapping("unlock")
	public ModelAndView unlock(HttpSession session,String password){
		String ps=(String)session.getAttribute("unlockPwd");
		if(StringUtils.isNotBlank(ps)){
			//解密
			String pwd=RSAUtils.decryptStringByJs(ps,password);
			return ajaxDone(service.unlock(session,pwd));
		}else{
			throw new MyRuntimeException("解锁失败!请刷新页面重试！");
		}
	}
	
	/**
	 * 获取验证码
	 * @param session
	 * @param response
	 */
	@RequestMapping("imgNum")
	public void getImg(HttpSession session,HttpServletResponse response){
		
		ServletOutputStream out=null;
		try{ 
			 DefaultKaptcha captchaProducer=(DefaultKaptcha)ServletUtil.getApplicationContext().getBean("captchaProducer");
			
			 response.setDateHeader("Expires", 0);     
	         response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");    
	         response.addHeader("Cache-Control", "post-check=0, pre-check=0");    
	         response.setHeader("Pragma", "no-cache");    
	         response.setContentType("image/jpeg");    
	         String capText = captchaProducer.createText();    
	         session.setAttribute("imgCode", capText);    
	         BufferedImage bi = captchaProducer.createImage(capText);
	         out = response.getOutputStream();
	         ImageIO.write(bi, "jpg", out);
            
             out.flush(); 
             
         }catch (Exception e) {
			
		 }
		 finally{    
             if(out!=null){
            	 try {
					out.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
             }
        
         }    
		
		
		
		
	}
	
	
	
	
	
	
}
