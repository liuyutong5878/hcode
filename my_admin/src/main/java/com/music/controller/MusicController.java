package com.music.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.music.core.model.Music;
import com.music.core.model.MusicType;
import com.music.core.model.PageObject;
import com.music.core.service.MusicService;

@Controller
@RequestMapping("/music")
public class MusicController {

	@Autowired
	private MusicService musicService;
	
	private Logger loger = Logger.getLogger(MusicController.class);
	
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
		String name = request.getParameter("name");
		String isIndex = request.getParameter("isIndex");
		Gson gson = new Gson();
		Music condition  = new Music();
		condition.setName(name);
		if(StringUtils.isNotBlank(isIndex)){
			condition.setIsIndex(Integer.parseInt(isIndex));
		}
		PageObject<Music> page = musicService.listByPage(condition,Integer.parseInt(pageNow));
		return gson.toJson(page);
	}
	
	@ResponseBody
	@RequestMapping("/{ids}/del")
	public String del(@PathVariable String ids,HttpServletRequest request){
		int rows = musicService.del(ids);
		Gson gson = new Gson();
		return gson.toJson("已成功删除" + rows + "条记录！");
	}
	
	@ResponseBody
	@RequestMapping("/{ids}/setIndex")
	public String setIndex(@PathVariable String ids,HttpServletRequest request){
		boolean b = musicService.addToIndex(ids);
		if(b) return "success";
		return "failed";
	}
	
	@RequestMapping("/{musicId}/edit")
	public String edit(@PathVariable Integer musicId, Model model){
		Music music = musicService.getById(musicId);
		model.addAttribute("music", music);
		return "/music/music_edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Music music){
		Music rtMusic = null;
		if(music.getId() == null){ //add
			rtMusic = musicService.insert(music);
		}else{ //update
			rtMusic = musicService.update(music);
		}
		return "redirect:/music/"+rtMusic.getId()+"/edit.htm";
	}
}
