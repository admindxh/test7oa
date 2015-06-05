/**  
 * @Project: jxoa
 * @Title: LoginServiceImpl.java
 * @Package com.oa.manager.system.service.impl
 * @date 2013-4-1 下午3:23:32
 * @Copyright: 2013 
 */
package com.oa.manager.system.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.config.WebConfig;
import com.oa.commons.model.IpInfo;
import com.oa.commons.model.LoginInfo;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.util.AppUtil;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.FileUtils;
import com.oa.commons.util.IpUtil;
import com.oa.commons.util.MD5Util;
import com.oa.commons.util.SerialNumberUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.ILoginService;

/**
 * 
 * 类名：LoginServiceImpl
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-4-1 下午3:23:32
 *
 */

@Service
public class LoginServiceImpl extends BaseServiceImpl implements ILoginService{
	

	public ModelAndView updateLogin(String vercode,String name,String password,HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView("ajaxMessage");
		HttpSession session=request.getSession();
		//获取登录ip 
		String loginIp=IpUtil.getIpAddr(request);
		
		//0.验证系统是否过期 验证系统注册信息  判断注册码是否有效
	
		boolean iskey = SerialNumberUtil.verificationkey(BaseConfig.serialconfig.getClientname(),
				  BaseConfig.serialconfig.getClientcode(), BaseConfig.serialconfig.getValidstart(),
				  BaseConfig.serialconfig.getValidend(), BaseConfig.serialconfig.getValidday(),
				  BaseConfig.serialconfig.getKey());
		if(!true){
			//系统不可用,已过期
			mav.setViewName("ajaxDone");
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"抱歉，您的系统已过期，无法进行操作！请联系管理员！");
			return mav;
		}
		
		//1.验证系统防火墙，例：ip,时间 等访问限制，先排除开发人员，超级管理员
		if(!name.equals(BaseConfig.getInstance().getDevName())||!name.equals(BaseConfig.getInstance().getSaName())){
			WebConfig wc=BaseConfig.webconfig;
			//先判断系统是否禁止登陆
			if(wc.getAppStart()!=1){
				//禁止登陆系统
				mav.setViewName("ajaxDone");
				mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				mav.addObject(MsgConfig.MESSAGE,"系统已设置禁止访问！请联系管理员！");
				return mav;
				
			}
			//判断是否在可以登录的时间范围内
			if(!AppUtil.checkLoginTime(new Date(), wc.getLoginStartTime(), wc.getLoginEndTime())){
				//不符合使用范围
				mav.setViewName("ajaxDone");
				mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				mav.addObject(MsgConfig.MESSAGE,"系统只能在"+ wc.getLoginStartTime()+" 至 "+wc.getLoginEndTime()+"之间才能访问！");
				return mav;
			}
			//进行ip验证
			if(StringUtils.isNotBlank(wc.getAllowIps())&&!AppUtil.checkIp(wc.getAllowIps(), loginIp)){
				
				mav.setViewName("ajaxDone");
				mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				mav.addObject(MsgConfig.MESSAGE,"系统已设置ip限制！");
				return mav;
			}else{
				if(StringUtils.isNotBlank(wc.getLimitIps())&&AppUtil.checkIp(wc.getLimitIps(), loginIp)){
					mav.setViewName("ajaxDone");
					mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
					mav.addObject(MsgConfig.MESSAGE,"系统已设置ip限制！");
					return mav;
				}	
			}
		}
		/*
		//2.验证验证码是否正确
		if(!((String)session.getAttribute("imgCode")).equalsIgnoreCase(vercode)){
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"msg.validation.code.match");//验证码错误
			return mav;
		}*/
		
		SyUsers u=(SyUsers)dao.findOne("from SyUsers where userName = ?",name);
		if(u==null){
			System.out.println("不存在此用户");
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"msg.login.failure");//用户名或密码错误， 请重新登录

			return mav;
		}
		
		//3. 验证用户是否被限制登陆
		if(u.getUserStatus()==(short)0){	
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			mav.addObject(MsgConfig.MESSAGE,"msg.login.restrict");//用户被限制登陆，请联系管理员
			return mav;
		}
		
		 IpInfo ipInfo=IpUtil.getIpInfo(loginIp);
		/*IpInfo ipInfo=new IpInfo();//模拟IP信息
		ipInfo.setIp(loginIp);
		ipInfo.setCountry("中国");
		ipInfo.setRegion("山东省");
		ipInfo.setCity("济南市");
		ipInfo.setIsp("联通");*/
		
		Timestamp loginTime=DateUtil.currentTimestamp();
		int num=BaseConfig.webconfig.getPwdErrorNum();
		int time=BaseConfig.webconfig.getPwdErrorTime();
		//4. 验证用户是否因密码多次输入错误，处于限制登录状态。 判断用户密码输入错误的次数是否达到指定次数
		if(u.getErrorCount()>=num){
			//当用户密码输入错误次数大于系统配置的错误次数，获取错误时间 进行判断
			long gotime=loginTime.getTime()-u.getErrorTime().getTime();
			if(gotime<time*60000){
				//如果冷却时间未到
				
				mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				mav.addObject(MsgConfig.MESSAGE,"msg.login.restricttime");//您已{0}次密码输入错误，请{1}之后再试！
				mav.addObject(MsgConfig.MESSAGEVALUES, num+","+time+"分钟");//占位符赋值
				return mav;
			}else{
				//如果冷却时间已到 将用户输入错误的次数重置
				u.setErrorCount((short)0);
				
			}
			
		}
		//5. 登录认证  验证密码
		if(MD5Util.MD5Validate(password, u.getUserPassword())){
			Subject currentUser =SecurityUtils.getSubject();
			UsernamePasswordToken token =new UsernamePasswordToken(u.getId(), password);
			currentUser.login(token);//登录认证 记录登陆信息
			System.out.println("****登陆成功*****");
			//6.1 登录成功 保持一些用户信息到session中
			Member me=new Member();//需要放入当前session 的用户信息
			me.setId(u.getId());
			me.setIpInfo(ipInfo);
			me.setLoginTime(loginTime);
			me.setDeptId(u.getDeptId());
			session.setAttribute("minfo",me); //将当前用户信息存入session中
			
			if(name.equals(BaseConfig.getInstance().getDevName())){
				session.setAttribute("dev", true);//当前登陆用户是开发者，拥有所有权限
			}else{
				session.setAttribute("dev", false);
			}
			if(name.equals(BaseConfig.getInstance().getSaName())){
				session.setAttribute("sa", true);//当前登陆用户是系统超级管理员
			}else{
				session.setAttribute("sa", false);
			}
			//6.2 保持一些用户登陆信息 到全局在线用户列表
			//获取全局 用户列表 将此次登录用户添加到用户在线列表中
			Map<String,OnLineUser> usersMap=ServletUtil.getOnLineUsers();
			
			OnLineUser onmy=usersMap.get(u.getId());
			if(onmy==null){
				onmy=new OnLineUser();
			}
			onmy.setId(u.getId());
			onmy.setTrueName(u.getTrueName());
			onmy.setDeptId(u.getDeptId());
			onmy.setSex(u.getUserSex());
			
			Map<String,LoginInfo> loginInfos=onmy.getLoginInfos();
			if(loginInfos==null){
				loginInfos=new HashMap<String,LoginInfo>();
			}
			LoginInfo loginInfo=new LoginInfo();
			loginInfo.setId(FileUtils.getUUID());
			loginInfo.setLoginType(1);
			loginInfo.setIpInfo(ipInfo);
			loginInfo.setLoginTime(loginTime);
			loginInfos.put(session.getId(),loginInfo );
			onmy.setLoginInfos(loginInfos);
			usersMap.put(u.getId(), onmy);//将当前用户信息放入在线用户列表
			session.setAttribute("loginType", 1);//标记此session登陆方式 用于退出时使用
			//6.3 记录登录日志
			SyLoginLog log=new SyLoginLog();
			log.setUserId(u.getId());
			log.setLoginType((short)1);
			log.setLoginDesc("登录成功");
			log.setIpInfoCountry(ipInfo.getCountry());
			log.setIpInfoRegion(ipInfo.getRegion());
			log.setIpInfoCity(ipInfo.getCity());
			log.setIpInfoIsp(ipInfo.getIsp());
			log.setLoginIp(loginIp);
			log.setLoginTime(loginTime);
			
			dao.save(log);//保存登录日志
			//6.4 保持用户本此登录时间 ip 等信息保持到数据库
			u.setLastLoginIp(loginIp);//登录ip
			u.setLastLoginTime(loginTime);//登录时间
			u.setErrorCount((short)0);//将密码错误次数重置为0
			dao.update(u);//更新用户
			
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
			mav.addObject(MsgConfig.MESSAGE,"msg.login.success");//登录成功
			
			session.removeAttribute("jmpw");//清除加密密码
			session.setAttribute("fromLogin",true);//标记刚执行登陆操作
			return mav;
		}else{
			//认证失败
			System.out.println("密码错误");
			//登录日志
			SyLoginLog log=new SyLoginLog();
			log.setUserId(u.getId());
			log.setLoginType((short)1);
			log.setLoginDesc("密码错误");
			log.setIpInfoCountry(ipInfo.getCountry());
			log.setIpInfoCity(ipInfo.getCity());
			log.setIpInfoIsp(ipInfo.getIsp());
			log.setIpInfoRegion(ipInfo.getRegion());
			log.setLoginIp(loginIp);
			log.setLoginTime(loginTime);
			
			dao.save(log);//保存登录日志
			
			u.setErrorTime(loginTime);
			u.setErrorCount((short)(u.getErrorCount()+1));
			
			dao.update(u);//更新用户
		
			mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);//用户名或密码错误， 请重新登录
			mav.addObject(MsgConfig.MESSAGE,"msg.login.failure");
			return mav;
		}
	
	}
	
	public Map updateLoginAndroid(String vercode,String name,String password,HttpServletRequest request,HttpServletResponse response){
		
		System.out.println(name);
		System.out.println(password);
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session=request.getSession();
		//获取登录ip 
		String loginIp=IpUtil.getIpAddr(request);
		
		//0.验证系统是否过期 验证系统注册信息  判断注册码是否有效
		
		boolean iskey = SerialNumberUtil.verificationkey(BaseConfig.serialconfig.getClientname(),
				  BaseConfig.serialconfig.getClientcode(), BaseConfig.serialconfig.getValidstart(),
				  BaseConfig.serialconfig.getValidend(), BaseConfig.serialconfig.getValidday(),
				  BaseConfig.serialconfig.getKey());
		
//		if(!iskey){
//			//系统不可用,已过期
//			map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
//			map.put(MsgConfig.MESSAGE,"抱歉，您的系统已过期，无法进行操作！请联系管理员！");
//			return map;
//		}
		
		//1.验证系统防火墙，例：ip,时间 等访问限制，先排除开发人员，超级管理员
		if(!name.equals(BaseConfig.getInstance().getDevName())||!name.equals(BaseConfig.getInstance().getSaName())){
			WebConfig wc=BaseConfig.webconfig;
			//先判断系统是否禁止登陆
			if(wc.getAppStart()!=1){
				//禁止登陆系统
				
				map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				map.put(MsgConfig.MESSAGE,"系统已设置禁止访问！请联系管理员！");
				return map;
				
			}
			//判断是否在可以登录的时间范围内
			if(!AppUtil.checkLoginTime(new Date(), wc.getLoginStartTime(), wc.getLoginEndTime())){
				//不符合使用范围
				map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				map.put(MsgConfig.MESSAGE,"系统只能在"+ wc.getLoginStartTime()+" 至 "+wc.getLoginEndTime()+"之间才能访问！");
				return map;
			}
			//进行ip验证
			if(StringUtils.isNotBlank(wc.getAllowIps())&&!AppUtil.checkIp(wc.getAllowIps(), loginIp)){
				map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				map.put(MsgConfig.MESSAGE,"系统已设置ip限制！");
				return map;
			}else{
				if(StringUtils.isNotBlank(wc.getLimitIps())&&AppUtil.checkIp(wc.getLimitIps(), loginIp)){
					map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
					map.put(MsgConfig.MESSAGE,"系统已设置ip限制！");
					return map;
				}	
			}
		}
		/*
		//2.验证验证码是否正确
		if(!((String)session.getAttribute("imgCode")).equalsIgnoreCase(vercode)){
			map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			map.put(MsgConfig.MESSAGE,"验证码错误");//验证码错误
			return map;
		}*/
		
		SyUsers u=(SyUsers)dao.findOne("from SyUsers where userName = ?",name);
		System.out.println("user"+u);
		if(u==null){
			System.out.println("不存在此用户");
			map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			map.put(MsgConfig.MESSAGE,"用户名或密码错误！");//用户名或密码错误， 请重新登录

			return map;
		}
		
		//3. 验证用户是否被限制登陆
		if(u.getUserStatus()==(short)0){	
			map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
			map.put(MsgConfig.MESSAGE,"您已被限制登录！");//用户被限制登陆，请联系管理员
			return map;
		}
		
		//IpInfo ipInfo=IpUtil.getIpInfo(loginIp);
		IpInfo ipInfo=new IpInfo();//模拟IP信息
		ipInfo.setIp(loginIp);
		ipInfo.setCountry("中国");
		ipInfo.setRegion("山东省");
		ipInfo.setCity("济南市");
		ipInfo.setIsp("联通");
		
		Timestamp loginTime=DateUtil.currentTimestamp();
		int num=BaseConfig.webconfig.getPwdErrorNum();
		int time=BaseConfig.webconfig.getPwdErrorTime();
		//4. 验证用户是否因密码多次输入错误，处于限制登录状态。 判断用户密码输入错误的次数是否达到指定次数
		if(u.getErrorCount()>=num){
			//当用户密码输入错误次数大于系统配置的错误次数，获取错误时间 进行判断
			long gotime=loginTime.getTime()-u.getErrorTime().getTime();
			if(gotime<time*60000){
				//如果冷却时间未到
				
				map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
				map.put(MsgConfig.MESSAGE,"您已"+num+"次密码输入错误，请"+time+"分钟之后再试!");//您已{0}次密码输入错误，请{1}之后再试！
				return map;
			}else{
				//如果冷却时间已到 将用户输入错误的次数重置
				u.setErrorCount((short)0);
				
			}
			
		}
		//5. 登录认证  验证密码
		if(MD5Util.MD5Validate(password, u.getUserPassword())){
			Subject currentUser =SecurityUtils.getSubject();
			UsernamePasswordToken token =new UsernamePasswordToken(u.getId(),password);
			currentUser.login(token);//登录认证 记录登陆信息
			System.out.println("****登陆成功*****");
			//6.1 登录成功 保持一些用户信息到session中
			Member me=new Member();//需要放入当前session 的用户信息
			me.setId(u.getId());
			me.setIpInfo(ipInfo);
			me.setLoginTime(loginTime);
			me.setDeptId(u.getDeptId());
			session.setAttribute("minfo",me); //将当前用户信息存入session中
			
			if(name.equals(BaseConfig.getInstance().getDevName())){
				session.setAttribute("dev", true);//当前登陆用户是开发者，拥有所有权限
			}else{
				session.setAttribute("dev", false);
			}
			if(name.equals(BaseConfig.getInstance().getSaName())){
				session.setAttribute("sa", true);//当前登陆用户是系统超级管理员
			}else{
				session.setAttribute("sa", false);
			}
			//6.2 保持一些用户登陆信息 到全局在线用户列表
			//获取全局 用户列表 将此次登录用户添加到用户在线列表中
			Map<String,OnLineUser> usersMap=ServletUtil.getOnLineUsers();
			
			OnLineUser onmy=usersMap.get(u.getId());
			if(onmy==null){
				onmy=new OnLineUser();
			}
			onmy.setId(u.getId());
			onmy.setTrueName(u.getTrueName());
			onmy.setDeptId(u.getDeptId());
			onmy.setSex(u.getUserSex());
			
			Map<String,LoginInfo> loginInfos=onmy.getLoginInfos();
			if(loginInfos==null){
				loginInfos=new HashMap<String,LoginInfo>();
			}
			LoginInfo loginInfo=new LoginInfo();
			loginInfo.setId(FileUtils.getUUID());
			loginInfo.setLoginType(2);
			loginInfo.setIpInfo(ipInfo);
			loginInfo.setLoginTime(loginTime);
			loginInfos.put(session.getId(),loginInfo );
			onmy.setLoginInfos(loginInfos);
			usersMap.put(u.getId(), onmy);//将当前用户信息放入在线用户列表
			session.setAttribute("loginType", 2);//标记此session登陆方式 用于退出时使用
			//6.3 记录登录日志
			SyLoginLog log=new SyLoginLog();
			log.setUserId(u.getId());
			log.setLoginType((short)2);
			log.setLoginDesc("登录成功");
			log.setIpInfoCountry(ipInfo.getCountry());
			log.setIpInfoRegion(ipInfo.getRegion());
			log.setIpInfoCity(ipInfo.getCity());
			log.setIpInfoIsp(ipInfo.getIsp());
			log.setLoginIp(loginIp);
			log.setLoginTime(loginTime);
			
			dao.save(log);//保存登录日志
			//6.4 保持用户本此登录时间 ip 等信息保持到数据库
			u.setLastLoginIp(loginIp);//登录ip
			u.setLastLoginTime(loginTime);//登录时间
			u.setErrorCount((short)0);//将密码错误次数重置为0
			dao.update(u);//更新用户
			
			map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
			map.put(MsgConfig.MESSAGE,"登录成功");//登录成功
			
			session.removeAttribute("jmpw");//清除加密密码
			session.setAttribute("fromLogin",true);//标记刚执行登陆操作
			return map;
		}else{
			//认证失败
			System.out.println("密码错误");
			//登录日志
			SyLoginLog log=new SyLoginLog();
			log.setUserId(u.getId());
			log.setLoginType((short)2);
			log.setLoginDesc("密码错误");
			log.setIpInfoCountry(ipInfo.getCountry());
			log.setIpInfoCity(ipInfo.getCity());
			log.setIpInfoIsp(ipInfo.getIsp());
			log.setIpInfoRegion(ipInfo.getRegion());
			log.setLoginIp(loginIp);
			log.setLoginTime(loginTime);
			
			dao.save(log);//保存登录日志
			
			u.setErrorTime(loginTime);
			u.setErrorCount((short)(u.getErrorCount()+1));
			
			dao.update(u);//更新用户
		
			map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);//用户名或密码错误， 请重新登录
			map.put(MsgConfig.MESSAGE,"用户名或密码错误！");
			return map;
		}
	}
	public boolean unlock(HttpSession session,String password){
	    SyUsers user=dao.get(SyUsers.class, ServletUtil.getMember().getId());
		if(MD5Util.MD5Validate(password, user.getUserPassword())){
			session.removeAttribute("unlockPwd");
			session.removeAttribute("lock");//解除锁定
			return true;
		}else{
			return false;
		}
	}
	
}
