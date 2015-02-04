package com.hlb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlb.model.User;
import com.hlb.model.WebUser;
import com.hlb.service.UserService;
import com.hlb.service.WebUserService;
import com.hlb.utils.ConstantUtil;

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
	
	@RequestMapping("/index")
	public String index(){
		return "/welcome";
	}
	
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request, Model model){
		List<WebUser> ulist = webUserService.getAll();
		model.addAttribute("ulist", ulist);
		return "/user/user_list";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(ConstantUtil.SESSION_USER);
		return "redirect:/main/showLogin.htm";
	}
	
}
