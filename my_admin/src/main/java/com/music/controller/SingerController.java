package com.music.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.music.core.model.Attachment;
import com.music.core.model.Country;
import com.music.core.model.PageObject;
import com.music.core.model.Singer;
import com.music.core.service.AttachmentService;
import com.music.core.service.CountryService;
import com.music.core.service.SingerService;
import com.music.util.SystemUtil;

/**
 *@author hlib
 *@version 1.0 : 2015年2月3日下午8:38:47
 */

@Controller
@RequestMapping("/singer")
public class SingerController {

	@Autowired
	private SingerService singerService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private AttachmentService attachService;
	
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
	
	@RequestMapping("/edit")
	public String editSinger(Model model, HttpServletRequest request){
		String idStr = request.getParameter("singerId");
		if(StringUtils.isNotBlank(idStr)){
			Integer id = Integer.parseInt(idStr);
			Singer singer = singerService.getById(id);
			model.addAttribute("singer", singer);
		}
		List<Country> countrys = countryService.getAll();
		model.addAttribute("countrys", countrys);
		return "/singer/singer_edit";
	}
	
	@RequestMapping("/{id}/loadIcon")
	public void loadIcon(@PathVariable Integer id, HttpServletResponse response){
		response.setContentType("image/jpeg");
		Attachment attach = attachService.getById(id);
		singerService.loadIcon(attach, response);
	}
	
	@RequestMapping("/save")
	public String saveSinger(Singer singer, HttpServletRequest request){
		
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		String path = SystemUtil.getProp("uploadPath") + File.separator + SystemUtil.getDateTimeStr(new Date(), "yyyyMMdd")+File.separator;
		String saveFileName = UUID.randomUUID().toString();
		MultipartFile source = req.getFile("file");
		
		Attachment returnAttach = null;
		if(!source.isEmpty()){
			File dest = new File(path+saveFileName);
			//保存文件到本地
			try {
				FileUtils.copyInputStreamToFile(source.getInputStream(), dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Attachment attach = new Attachment();
			attach.setAddTime(SystemUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
			attach.setDownloadAble(false);
			attach.setDownloadUrl("");
			attach.setFileName(source.getOriginalFilename());
			attach.setFileSize(source.getSize());
			attach.setUid(saveFileName);
			attach.setUri("");
			attach.setType(1);
			returnAttach = attachService.addByReturnKey(attach);
		}
		Singer rtSinger = null;
		if(singer != null){
			try {
				singer.setName(new String(singer.getName().getBytes("ISO8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(returnAttach != null) singer.setIconId(returnAttach.getId()); //保存头像id
			if(singer.getId() != null){
				rtSinger = singerService.update(singer);	//更新
			}else{
				rtSinger = singerService.saveSinger(singer);//新增
			}
		}
		return "redirect:/singer/"+rtSinger.getId()+"/edit.htm";
	}
	

	@RequestMapping("/{singerId}/del.htm")
	public String singerDel(@PathVariable Integer singerId){
		singerService.del(singerId);
		return "redirect:/singer/showList.htm";
	}
	
	@ResponseBody
	@RequestMapping(value="/search",produces="text/json;charset=UTF-8")
	public String search(HttpServletRequest request){
		String name = request.getParameter("keyword");
		String singerId = request.getParameter("singerId");
		String callback = request.getParameter("callback");
		
		Gson gson = new Gson();
		if(StringUtils.isNotBlank(singerId)){
			Singer singer = singerService.getById(Integer.parseInt(singerId));
			return callback + "(" + gson.toJson(singer) + ")";
		}else{
			List<Singer> singers = singerService.searchByName(name);
			return callback + "(" + gson.toJson(singers) + ")";
		}
	}
	
}
