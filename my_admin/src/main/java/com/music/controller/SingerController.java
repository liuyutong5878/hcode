package com.music.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.music.core.model.PageObject;
import com.music.core.model.Singer;
import com.music.core.service.SingerService;

/**
 *@author hlib
 *@version 1.0 : 2015年2月3日下午8:38:47
 */

@Controller
@RequestMapping("/singer")
public class SingerController {

	@Autowired
	private SingerService singerService;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/showList")
	public String showList(){
		
		return "/singer/singer_list";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		String pageNow = request.getParameter("pageNow");
		String singerName = request.getParameter("singerName");
		Singer condition = new Singer();
		condition.setName(singerName);
		PageObject<Singer> page = singerService.listByPage(condition,Integer.parseInt(pageNow));
		return gson.toJson(page);
	}
	
	@ResponseBody
	@RequestMapping("/{ids}/setHot")
	public String setHot(@PathVariable String ids){
		int rows = singerService.setHot(ids);
		return gson.toJson("成功更新" + rows + "条记录");
	}
}
