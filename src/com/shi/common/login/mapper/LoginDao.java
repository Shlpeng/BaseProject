package com.shi.common.login.mapper;

import java.util.List;
import java.util.Map;

import com.shi.common.login.model.LoginUser;

public interface LoginDao {

	void insertUser(LoginUser loginUser);

	List<LoginUser> selectUserByMap(Map<String, String> params);

	void delUser(String id);

	void updateUser(LoginUser loginUser);

}
