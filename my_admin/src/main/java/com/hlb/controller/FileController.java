package com.hlb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hlb.model.Attachment;
import com.hlb.service.AttachmentService;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private AttachmentService attachmentService;
	
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
				
				attachment.setAddTime("");
				
				//保存文件
				File serverFile = new File("D://tmp/attachment" + fileName);
				if(!serverFile.exists()){
					serverFile.createNewFile();
					FileCopyUtils.copy(file.getBytes(), serverFile);
				}else{
					System.out.println("文件" +fileName+ "已存在-----");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
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
	
	
	
	public String deleteFile(HttpServletRequest request){
		
		return "";
	}
}
