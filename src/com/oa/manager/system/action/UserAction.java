/**  
 * @Project: jxoa
 * @Title: UserAction.java
 * @Package com.oa.manager.system.action
 * @date 2013-3-28 下午2:04:58
 * @Copyright: 2013 
 */
package com.oa.manager.system.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oa.commons.base.BaseAction;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.LoginInfo;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.model.PageParam;
import com.oa.commons.model.RSAPublicKeyModel;
import com.oa.commons.util.ExcelUtils;
import com.oa.commons.util.RSAUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.IDeptService;
import com.oa.manager.system.service.IUserService;


/**
 * 
 * 类名：UserAction
 * 功能：系统管理--组织机构--用户管理
 * 详细：用户的增删改查
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-3-28 下午2:04:58
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction{
	
	@Autowired
	private IUserService service; 
	
	@Autowired
	private IDeptService deptService; //部门

	/**
	 * 跳转到用户管理页面
	 * @return
	 */
	@RequiresPermissions("user:read")
	@RequestMapping("load")
	public String load(){
		
		return "system/organize/user/load";
	}
	/**
	 * 条件分页查询用户
	 * @return
	 */
	@RequiresPermissions("user:read")
	@RequestMapping("queryUsers")
	public ModelAndView selectUsers(PageParam param,SyUsers user){
		
		return ajaxJsonEscape(service.selectUsers(param, user));
		
	}
	/**
	 * 跳转到新增用户界面
	 * @return
	 */
	@RequiresPermissions("user:add")
	@RequestMapping("addPage")
	public String addUsersPage(ModelMap map){
		//生成加密密钥
		String pwd=UUID.randomUUID().toString();
		ServletUtil.getSession().setAttribute("jmpw", pwd);
		RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd); 
		map.put("modulus",publicKey.getHexModulus());
		map.put("exponent",publicKey.getHexPublicExponent());
		return "system/organize/user/add";
	}
	/**
	 * 添加用户
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions("user:add")
	@RequestMapping("add")
	public ModelAndView add(@Valid SyUsers user,Errors errors,String roleIds) throws UnsupportedEncodingException{
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors);
			if(mav!=null)return mav;
        }
		//解密
		String pwd=RSAUtils.decryptStringByJs((String)ServletUtil.getSession().getAttribute("jmpw"),user.getUserPassword());
		user.setUserPassword(URLDecoder.decode(pwd, "utf-8"));
		
		return ajaxDone(service.addUser(user));
	}
	/**
	 * 跳转到用户编辑页面
	 * @return
	 */
	@RequiresPermissions("user:update")
	@RequestMapping("updatePage")
	public String updatePage(String id,ModelMap map){
		SyUsers user=service.get(SyUsers.class, id);
		if(user==null){
			return NODATA;
		}
		user.setUserPassword("");
		map.addAttribute("u",user);
		return "system/organize/user/update";
	}
	/**
	 * 修改用户
	 * @param dept
	 * @return
	 */
	@RequiresPermissions("user:update")
	@RequestMapping("update")
	public ModelAndView update(@Valid SyUsers u,Errors errors){
		if(errors.hasErrors()) {  
			ModelAndView mav=getValidationMessage(errors,"userPassword");
			if(mav!=null)return mav;
        }  
		return ajaxDone(service.updateUser(u));
		
	}
	/**
	 * 跳转到用户密码重置界面
	 * @return
	 */
	@RequiresPermissions("user:resetPassword")
	@RequestMapping("updatePwPage")
	public String updatePwPage(ModelMap map){
		//生成加密密钥
		String pwd=UUID.randomUUID().toString();
		ServletUtil.getSession().setAttribute("jmpw", pwd);
		RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd); 
		map.put("modulus",publicKey.getHexModulus());
		map.put("exponent",publicKey.getHexPublicExponent());
		return "system/organize/user/update_password";
	}
	/**
	 * 重置用户密码
	 * @param id
	 * @param userPassword
	 * @param pw
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions("user:resetPassword")
	@RequestMapping("updatePw")
	public ModelAndView updatePw(String id,String userPassword,String pwd) throws UnsupportedEncodingException{
		
		if(!userPassword.equals(pwd)){
			List<String> errorMessages=new ArrayList<String>();
			errorMessages.add("两次输入的新密码不一致！");
			ModelAndView mav = new ModelAndView("ajaxValidationMessage");
			mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
			mav.addObject("errorCount",1);
			mav.addObject(MsgConfig.MESSAGE,errorMessages);
			return mav;
		}
		
		//解密
		userPassword=RSAUtils.decryptStringByJs((String)ServletUtil.getSession().getAttribute("jmpw"),userPassword);
		userPassword=URLDecoder.decode(userPassword, "utf-8");
		
		
		return ajaxDone(service.updatePassword(id, userPassword));
	}
	/**
	 * 跳转到个人设置，用户修改密码
	 * @return
	 */
	@RequiresPermissions("user:updateMyPw")
	@RequestMapping("updateMyPwPage")
	public String updateMyPwPage(ModelMap map){
		//生成加密密钥
		String pwd=UUID.randomUUID().toString();
		ServletUtil.getSession().setAttribute("jmpw", pwd);
		RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd); 
		map.put("modulus",publicKey.getHexModulus());
		map.put("exponent",publicKey.getHexPublicExponent());
		return "personalOffice/user_set/update_password";
	}
	/**
	 * 修改我的密码
	 * @param oldPassword
	 * @param userPassword
	 * @param pw
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("updateMyPw")
	public ModelAndView updateMyPw(String oldPassword,String userPassword,String pwd) throws UnsupportedEncodingException{
		
		if(!userPassword.equals(pwd)){
			List<String> errorMessages=new ArrayList<String>();
			errorMessages.add("两次输入的新密码不一致！");
			ModelAndView mav = new ModelAndView("ajaxValidationMessage");
			mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
			mav.addObject("errorCount",1);
			mav.addObject(MsgConfig.MESSAGE,errorMessages);
			return mav;
		}
		
		//解密
		oldPassword=RSAUtils.decryptStringByJs((String)ServletUtil.getSession().getAttribute("jmpw"),oldPassword);
		oldPassword=URLDecoder.decode(oldPassword, "utf-8");
		
		userPassword=RSAUtils.decryptStringByJs((String)ServletUtil.getSession().getAttribute("jmpw"),userPassword);
		userPassword=URLDecoder.decode(userPassword, "utf-8");
		

		return ajaxDone(service.updateMyPassword(oldPassword, userPassword));
	}
	/**
	 * 删除用户
	 * @param dept
	 * @return
	 */
	@RequiresPermissions("user:delete")
	@RequestMapping("del")
	public ModelAndView delete(String[] ids){
		
		return ajaxDone(service.deleteUser(ids));
	}
	/**
	 * 查看用户详情
	 * @return
	 */
	@RequiresPermissions("user:read")
	@RequestMapping("show")
	public String showUser(String id,ModelMap map){
		
		SyUsers user=service.get(SyUsers.class, id);
		if(user==null){
			return NODATA;
		}
		map.addAttribute("u",user);
		
		return "system/organize/user/show";
	}
	
	/**
	 * 跳转到 用户--角色--修改页面 ，并 查询出所有的角色，和用户已有的角色
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("user:readRole")
	@RequestMapping("updateRolePage")
	public String updateRolesPage(){
		
		return "system/organize/user/update_role";
	
	}
	/**
	 * 跳转到 用户--角色--修改页面 ，并 查询出所有的角色，和用户已有的角色
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("user:readRole")
	@RequestMapping("updateRoleQuery")
	public ModelAndView updateRoleQuery(String id){
		
		return ajaxJsonEscape(service.selectUserRolesAndIds(id));
	
	}
	/**
	 * 修改用户角色分配
	 * @param userId
	 * @param addRoleIds	需要添加的角色id，多个用，隔开
	 * @param delRoleIds	需要删除的角色id，多个用，隔开
	 * @return
	 */
	@RequiresPermissions("user:updateRole")
	@RequestMapping("updateRole")
	public ModelAndView updatePowers(String userId,String[] addRoleIds,String[] delRoleIds){
		
		return ajaxDone(service.updateUserRoles(userId, addRoleIds, delRoleIds));
		
	}
	
	/**
	 * 查看用户权限页面
	 * @return
	 */
	@RequiresPermissions("user:readPower")
	@RequestMapping("showPowersPage")
	public String showUserPowersPage(){
		
		return "system/organize/user/show_powers";
	}
	/**
	 * 查询某用户拥有的所有权限
	 * @return
	 */
	@RequiresPermissions("user:readPower")
	@RequestMapping("showPowers")
	public ModelAndView showUserPowers(String id){
		
		return ajaxJsonEscape(service.selectUserPowers(id));
	}
	
	
	
	/**
	 * 用户，查找带回
	 * @param type 查到带回类型	1:单选，2:多选,3:手机号查询选择
	 * @param map
	 * @return
	 */
	@RequestMapping("lookUpPage")
	public String lookUpPage(Integer type){
		
		return "system/organize/user/lookup";
		
	}
	/**
	 * 用户，查找带回
	 * @param param
	 * @param user
	 * @param type 查到带回类型	1:单选，2:多选,3:手机号选择
	 * @param map
	 * @return
	 */
	@RequestMapping("lookUp")
	public ModelAndView lookUp(PageParam param,SyUsers user,int type){
		
		user.setUserStatus((short)1);
		if(type!=3){
			return ajaxJsonEscape(service.selectUsersLookUp(param, user));
		}else{
			return ajaxJsonEscape(service.selectUsersLookUpNumber(param, user));
		}
		
	}
	
	
	/**
	 * 查询用户姓名  流程设计时使用，传入多个id，返回用户姓名
	 * @param ids	用户id，多个用，隔开
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getTrueNamesById")
	public ModelAndView getUserNamesByIds(String ids){
		
		Map map=new HashMap();
		map.put(MsgConfig.STATUSCODE,MsgConfig.CODE_SUCCESS );
		
		map.put("names",MyCache.getInstance().getTrueName(ids));
		
		return ajaxJsonEscape(map);
	}
	/**
	 * 当前用户个人信息
	 * @return
	 */
	@RequestMapping("my/info")
	public String  myInfo(ModelMap map){
		Member me=ServletUtil.getMember();
		SyUsers user=service.get(SyUsers.class, me.getId());
		map.addAttribute("user",user);
		map.addAttribute("ipInfo",me.getIpInfo());
		map.addAttribute("roleIds",StringUtils.join(ServletUtil.getRoleIdsByUserId(me.getId()),","));
	
		return "personalOffice/user_set/my_info";
	}
	/**
	 * 个人登录管理 当前用户登录信息
	 * @return
	 */
	@RequiresPermissions("user:loginInfo")
	@RequestMapping("my/loginInfo")
	public String  myLoginInfo(HttpSession session,ModelMap map){
	
		Map<String, LoginInfo> infos=ServletUtil.getOnLineUsers().get(ServletUtil.getMember().getId()).getLoginInfos();
		Set<Entry<String,LoginInfo>> entrys=infos.entrySet();
		List<LoginInfo> loginInfos=new ArrayList<LoginInfo>();
		for(Entry<String,LoginInfo> e:entrys){
			loginInfos.add(e.getValue());
			if(session.getId().equals(e.getKey())){
				map.put("nowId", e.getValue().getId());
			}
		}
		map.put("loginInfos", loginInfos);
		
		return "personalOffice/user_set/login_info";
	}
	/**
	 * 个人登录管理 强制退出其他地方登录
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user:logoutOuther")
	@RequestMapping("my/loginInfo/logout")
	public ModelAndView logOut(String id){
		String userId=ServletUtil.getMember().getId();
		Map<String,OnLineUser> usersMap=ServletUtil.getOnLineUsers();
		Map<String, LoginInfo> infos=usersMap.get(userId).getLoginInfos();
		Set<Entry<String,LoginInfo>> entrys=infos.entrySet();
		for(Entry<String,LoginInfo> e:entrys){
			LoginInfo loginInfo=e.getValue();
			if(id.equals(loginInfo.getId())){
				infos.remove(e.getKey());
				if(infos.isEmpty()){
					usersMap.remove(userId);
				}
				break;
			}
		}
		return ajaxDoneSuccess();
	}
	
	/**
	 * 查询全部人员，和部门 全部人员数量  用于树结构显示
	 * @return
	 */
	@RequestMapping("allUsers")
	public ModelAndView alllUsers(){
		
		List<SyUsers> list=service.selectAllUsers();
		List<Map<String,Object>> users=new ArrayList<Map<String,Object>>();
		for(SyUsers u:list){
			Map<String,Object> user=new HashMap<String,Object>();
			user.put("id", u.getId());
			user.put("trueName", u.getTrueName());
			user.put("deptId", u.getDeptId());
			user.put("sex", u.getUserSex());
			users.add(user);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(MsgConfig.STATUSCODE, MsgConfig.CODE_SUCCESS);
		map.put("users", users);
		map.put("depts", deptService.selectAllDeptsMap());
		
		return ajaxJsonEscape(map);
	}
	
	
	
	/**
	 * 跳转到在线管理页面 查询出所有在线人员
	 * @return
	 */
	@RequiresPermissions("user:onLineUsers")
	@RequestMapping("onLineUsers/load")
	public String OnLineUserLoad(ModelMap map){
		
		List<OnLineUser> users=new ArrayList<OnLineUser>();
	
		Set<Entry<String,OnLineUser>> entrys=ServletUtil.getOnLineUsers().entrySet();
		
		for(Entry<String,OnLineUser> e:entrys){
			users.add(e.getValue());
		}
		map.addAttribute("users",users);
	
		return "system/onLineUsers";
	}
	/**
	 * 在线管理 强制退出用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user:logoutUser")
	@RequestMapping("allUsers/logout")
	public ModelAndView logOutUser(String id){
		
		Map<String,OnLineUser> usersMap=ServletUtil.getOnLineUsers();
		usersMap.remove(id);
		
		return ajaxDoneSuccess();
	}
	
	@RequiresPermissions("user:importUser")
	@RequestMapping("importUserPage")
	public String importUserPage(){
	
		  
		return "system/importUser";
	}
	
	
	@RequiresPermissions("user:importUser")
	@RequestMapping("importUser")
	public ModelAndView  importUser(@RequestParam MultipartFile file ){
		 
	 
		List<Map> lm = new ArrayList<Map>();
		try {  
			if(!file.isEmpty()){
				lm = ExcelUtils.readExcelAll(file.getInputStream());
			} 
			
			if(lm.size()>0){ 
				return ajaxDone(service.addUsers(lm));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
			 
		return null;
	}
	
	

}
