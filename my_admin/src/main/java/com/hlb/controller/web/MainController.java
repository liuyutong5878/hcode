package com.hlb.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/web/main")
public class MainController {
	
	@RequestMapping("/showIndex")
	public String index(){
		System.out.println("===================");
		return "index";
	}
	
}
