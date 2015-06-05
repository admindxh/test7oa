/**  
 * @Title ShiroAuthRealm.java
 * @date 2013-11-2 下午3:52:21
 * @Copyright: 2013 
 */
package com.oa.commons.security;

import java.util.Collection;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.oa.manager.system.service.IUserService;

/**
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public class ShiroAuthRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService; 
	
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println("登录认证"+getName());
		
		return new SimpleAuthenticationInfo(token.getUsername(), new String(token.getPassword()), getName());
		
		
	}
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始授权查询");
		String userId=(String)principals.getPrimaryPrincipal();
		Map<String,Collection<String>> map=userService.selectRolesPowers(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(map.get("roleIds"));
		info.addStringPermissions(map.get("powers"));
		return info;
		
	}
	

}
