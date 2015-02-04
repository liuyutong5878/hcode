package com.hlb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hlb.model.Music;
import com.hlb.model.MusicType;
import com.hlb.model.PageObject;
import com.hlb.service.MusicService;

@Controller
@RequestMapping("/music")
public class MusicController {

	@Autowired
	private MusicService musicService;
	
	@ResponseBody
	@RequestMapping(value="/listTypes")
	public String listTypes(HttpServletRequest request){
		List<MusicType> types = musicService.listAllTypes();
		Gson gson = new Gson();
		return gson.toJson(types);
	}
	
	@ResponseBody
	@RequestMapping("/addToType")
	public String addToType(HttpServletRequest request){
		String musicId = request.getParameter("musicId");
		String typeId = request.getParameter("typeId");
		int row = musicService.addToType(musicId, Integer.parseInt(typeId));
		Gson gson = new Gson();
		if(row <= 0){
			return gson.toJson("添加失败");
		}else{
			return gson.toJson("添加成功");
		}
	}
	
	@RequestMapping("/showList")
	public String showList(HttpServletRequest request){
		
		return "/music/music_list";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public String musicList(HttpServletRequest request, Model model){
		String pageNow = request.getParameter("pageNow");
		Gson gson = new Gson();
		PageObject<Music> page = musicService.listByPage(Integer.parseInt(pageNow));
		return gson.toJson(page);
	}
	
	@ResponseBody
	@RequestMapping("/{ids}/del")
	public String del(@PathVariable String ids,HttpServletRequest request){
		int rows = musicService.del(ids);
		Gson gson = new Gson();
		return gson.toJson("已成功删除" + rows + "条记录！");
	}
	
}
