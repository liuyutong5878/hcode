package com.music.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.music.core.model.Singer;
import com.music.core.service.SingerService;

/**
 * @author hlib
 * @version 1.0 : 2015年2月4日下午5:27:23
 */

@Controller
@RequestMapping("/singer")
public class SingerController {

	@Autowired
	private SingerService singerService;

	@ResponseBody
	@RequestMapping("/listHot")
	public String listHotSingers(HttpServletRequest request){
		List<Singer> hotSingers = singerService.getHotSingers();
		return new Gson().toJson(hotSingers);
	}
	
}
