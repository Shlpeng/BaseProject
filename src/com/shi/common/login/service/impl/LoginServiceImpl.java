package com.shi.common.login.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shi.common.login.mapper.LoginDao;
import com.shi.common.login.model.LoginUser;
import com.shi.common.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public void insertUser(LoginUser loginUser) {
		loginDao.insertUser(loginUser);
	}

	@Override
	public List<LoginUser> selectUserByMap(Map<String, String> params) {
		return loginDao.selectUserByMap(params);
	}

	@Override
	public void delUser(String id) {
		loginDao.delUser(id);
	}

	@Override
	public void updateUser(LoginUser loginUser) {
		loginDao.updateUser(loginUser);
	}

}
