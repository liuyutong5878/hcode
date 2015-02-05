package com.music.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.core.model.Music;
import com.music.core.model.MusicType;
import com.music.core.service.MusicService;

@Controller
@RequestMapping("/discovery")
public class DiscoveryController {

	@Autowired
	private MusicService musicService;

	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request, Model model) {
		String type = request.getParameter("typeId");
		MusicType musicType = musicService.getTypeById(Integer.parseInt(type));
		model.addAttribute("type", musicType);
		List<Music> musics = musicService.getMusicsByTypeId(musicType.getId());
		model.addAttribute("musics", musics);
		return new ModelAndView("music/music-list");
	}

}
