package com.music.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String showLogin(HttpServletRequest request){
		String type = request.getParameter("type");
		if("0".equals(type)){
			request.setAttribute("msg", "用户名或密码错误");
		}
		return "/login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(User user,HttpServletRequest request){
		if(!userService.isExist(user)){
			return "redirect:/main/showLogin.htm?type=0";
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
	
	@RequestMapping("/{userId}/userEdit")
	public String userEdit(@PathVariable Integer userId, Model model){
		WebUser user = webUserService.getById(userId);
		model.addAttribute("user", user);
		return "/user/user_edit";
	}
	
	@RequestMapping("/userSave")
	public String userSave(WebUser webUser){
		WebUser rtUser = null;
		if(null == webUser.getId()){ //add
			rtUser = webUserService.addByReturnKey(webUser);
		}else{  //update
			rtUser = webUserService.update(webUser);
		}
		return "redirect:/main/"+rtUser.getId()+"/userEdit.htm";
	}
	
	@RequestMapping("/{userId}/userDel")
	public String userDel(@PathVariable Integer userId){
		webUserService.deleteById(userId);
		return "redirect:/main/showUserList.htm";
	}
	
}
