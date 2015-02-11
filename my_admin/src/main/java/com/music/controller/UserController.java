package com.music.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.music.core.model.PageObject;
import com.music.model.User;
import com.music.model.WebUser;
import com.music.service.UserService;
import com.music.service.WebUserService;
import com.music.utils.ConstantUtil;

@Controller
@RequestMapping("/main")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private WebUserService webUserService;
	
	@RequestMapping("/showLogin")
	public String showLogin(){
		
		return "/login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(User user,HttpServletRequest request){
		if(!userService.isExist(user)){
			return "redirect:/main/showLogin.htm";
		}else{
			request.getSession().setAttribute(ConstantUtil.SESSION_USER, user);
			return "redirect:/main/index.htm";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(ConstantUtil.SESSION_USER);
		return "redirect:/main/showLogin.htm";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "/welcome";
	}
	
	@RequestMapping("/showUserList")
	public String showUserList(){
		
		return "/user/user_list";
	}
	
	@ResponseBody
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request){
		String pageNow = request.getParameter("pageNow");
		PageObject<WebUser> page = webUserService.listByPage(Integer.parseInt(pageNow));
		return new Gson().toJson(page);
	}
}
