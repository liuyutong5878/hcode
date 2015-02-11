package com.music.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.music.core.model.Music;
import com.music.core.service.MusicService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private MusicService musicService;

	@RequestMapping("/showList")
	public String showList() {

		return "music/list";
	}

	@RequestMapping("/welcome")
	public String index(HttpServletRequest request) {

		return "/index.html";
	}

	/**
	 * 首页初始化
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/init")
	public String indexInit(HttpServletRequest request) {
		
		List<Music> list = musicService.listIndexMusic(null);
		Gson gson = new Gson();
		System.out.println("user device----" + request.getHeader("USER-AGENT"));
		return gson.toJson(list);
	}

	/**
	 * 加载更多
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fetchMore", produces = "application/json;charset=utf-8")
	public String fetchMore(HttpServletRequest request) {
		System.out.println("加载更多////////////");
		return "";
	}
	
	public static void main(String[] args) {
		
		int[] arr = {2,1,6,8,9};
		
		for(int a : arr){
			System.out.println(a);
		}
		
		int max = 0;
		for(int i=0; i<arr.length; i++){
			max = arr[i]; 
			if((i+1) < arr.length){
				if(arr[i] < arr[i+1]){
					max = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = max;
				}
			}
		}
		System.out.println("===============");
		for(int a : arr){
			System.out.println(a);
		}
	}
	
	

}
