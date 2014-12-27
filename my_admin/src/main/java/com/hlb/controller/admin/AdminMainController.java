package com.hlb.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlb.model.User;


@Controller
@RequestMapping("/admin/main")
public class AdminMainController {
	
	private String pfolder = "/admin";
	
	@RequestMapping("/showLogin")
	public String showLogin(){
		//ÑéÖ¤Âë
		return pfolder + "/login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(User user){
		System.out.println("µÇÂ½");
		if(!"admin".equals(user.getUserName())){
			return "redirect:/admin/main/showLogin.htm";
		}else{
			return "redirect:/admin/main/index.htm";
		}
	}
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("===================");
		return pfolder + "/index";
	}
	
}
