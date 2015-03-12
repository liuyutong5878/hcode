package com.music.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.music.core.model.Attachment;
import com.music.core.service.AttachmentService;
import com.music.core.service.MusicService;
import com.music.util.SystemUtil;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private AttachmentService attachmentService;
	
	private Logger loger = Logger.getLogger(FileController.class);
	
	@Autowired
	private MusicService musicService;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest request){
		MultipartHttpServletRequest fileRequest =  (MultipartHttpServletRequest) request;
		MultipartFile  file =  fileRequest.getFile("file");
		String fileName = file.getOriginalFilename();
		if(!file.isEmpty()){
			try {
				//保存文件信息
				Attachment attachment = new Attachment();
				attachment.setFileName(fileName);
				attachment.setFileSize(file.getSize());
				attachment.setUri("/attachment/" + fileName);
				attachment.setDownloadUrl("");
				attachment.setDownloadAble(false);
				attachment.setAddTime(SystemUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
				
				//保存文件
				File serverFile = new File(SystemUtil.getProp("uploadPath") + fileName);
				if(!serverFile.exists()){
					serverFile.createNewFile();
					FileCopyUtils.copy(file.getBytes(), serverFile);
				}else{
					loger.error("文件【"  + fileName + "】已存在");
				}
			} catch (IOException e) {
				loger.error("文件上传出错-->> "+e);
			}
		}
		return "/attachment/file_list";
	}
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		
		List<Attachment> attachments = attachmentService.getAll();
		model.addAttribute("attachments", attachments);
		
		return "/attachment/file_list";
	}
	
	@ResponseBody
	@RequestMapping(value="/updateMusicLib")
	public String updateMusicLib(HttpServletRequest request){
		int rows = 0;
		Gson gson = new Gson();
		rows = musicService.updateMusicLib();
		return gson.toJson("成功更新" + rows + "条记录！"); 
	}
	
	
	public String deleteFile(HttpServletRequest request){
		//TODO delete file
		return "";
	}
}
