package com.shi.common.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shi.common.login.model.LoginUser;
import com.shi.common.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);  

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView registerByGet(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("common/login/register");
		return mv;
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registerByPost(LoginUser loginUser){
		ModelAndView mv=new ModelAndView();
		mv.addObject("loginUser", loginUser);
		try{
			Map<String,String> params = new HashMap<String,String>();
			params.put("phone", loginUser.getPhone());
			List<LoginUser> userList = loginService.selectUserByMap(params);
			if(userList != null && !userList.isEmpty()){
				throw new Exception("#phoneException#此手机号码已经被注册，请更换手机号码");
			}
			loginUser.setId(UUID.randomUUID().toString());
			loginService.insertUser(loginUser);
			mv.setViewName("common/login/index");
		}catch(Exception e){
			logger.error(e);
			int index = e.getMessage().indexOf("#phoneException#");
			if(index != -1){
				mv.addObject("exception", e.getMessage().substring(index+"#phoneException#".length()));
			}
			mv.setViewName("common/login/register");
		}
		return mv;
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateUser(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			List<LoginUser> userList = loginService.selectUserByMap(new HashMap<String,String>());
			userList.get(0).setEmail("1234");
			loginService.updateUser(userList.get(0));
			map.put("flag", true);
		}catch(Exception e){
			logger.error(e);
			map.put("flag", false);
		}
		return map;
	}	
	
	@RequestMapping(value="/delUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delUser(@RequestParam("id")String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			loginService.delUser(id);
			map.put("flag", true);
		}catch(Exception e){
			logger.error(e);
			map.put("flag", false);
		}
		return map;
	}
}
