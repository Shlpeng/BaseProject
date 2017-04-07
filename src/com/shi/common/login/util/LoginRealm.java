package com.shi.common.login.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shi.common.login.model.LoginUser;
import com.shi.common.login.service.LoginService;

public class LoginRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phone", (String)token.getPrincipal());
		List<LoginUser> userList = loginService.selectUserByMap(params);
		if (userList != null && !userList.isEmpty()) {
			return new SimpleAuthenticationInfo(userList.get(0).getPhone(),
					userList.get(0).getPwd(), getName());
		}
		return null;
	}

}
