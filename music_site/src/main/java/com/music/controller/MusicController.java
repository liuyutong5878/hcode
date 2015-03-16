package com.music.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.music.core.model.Music;
import com.music.core.model.MusicType;
import com.music.core.model.Singer;
import com.music.core.service.MusicService;
import com.music.core.service.SingerService;
import com.music.util.SystemUtil;

@Controller
@RequestMapping("/music")
public class MusicController {

	@Autowired
	private MusicService musicService;
	
	@Autowired
	private SingerService singerService;
	
	private Logger log = Logger.getLogger(MusicController.class);

	@ResponseBody
	@RequestMapping(value = "/listTypes")
	public String listTypes(HttpServletRequest request) {
		List<MusicType> types = musicService.listAllTypes();
		Gson gson = new Gson();
		return gson.toJson(types);
	}

	@RequestMapping("/listBySinger")
	public String listBySinger(HttpServletRequest request, Model model){
		String singerId = request.getParameter("singerId");
		Singer singer = singerService.getById(Integer.parseInt(singerId));
		model.addAttribute("type", singer);
		List<Music> musics = musicService.getMusicBySingerId(Integer.parseInt(singerId));
		model.addAttribute("musics", musics);
		return "music/music-list";
	}
	
	/**
	 * 通过歌曲或歌手名关键字搜索
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search")
	public String search(HttpServletRequest request){
		String name = request.getParameter("name");
		
		List<Music> musics = musicService.getMusicBySingerOrMusicName(name);
		return new Gson().toJson(musics);
	}
	
	/**
	 * 下载接口
	 * 
	 * @param musicId
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/{musicId}/download")
	public void loadMusic(@PathVariable String musicId,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Music music = musicService.getById(Integer.parseInt(musicId));
		String filePath = SystemUtil.getProp("musicPath") + music.getUri();
		File file = new File(filePath);
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().replace(" ", "").getBytes("utf-8"),
						"ISO8859-1"));
		InputStream fis = null;
		OutputStream ops = null;
		response.setContentType("audio/mpeg");
		try {
			fis = new FileInputStream(file);
			ops = response.getOutputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				ops.write(b, 0, i);
			}
			ops.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (ops != null)
					ops.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
