package com.hlb.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlb.utils.HttpUtil;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/showIndex")
	public String index(){
		System.out.println("===================");
		return "index";
	}
	
	
	public static void main(String[] args) {
		String url = "http://yunpan.cn/cfXb7hfxvBmev";
		
//		http://yunpan.cn/cfXb7hfxvBmev £®Ã·»°¬Î£∫1f4f£©
		
		try {
			String htm = HttpUtil.get(url);
			System.out.println("∑µªÿ---" +  htm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
