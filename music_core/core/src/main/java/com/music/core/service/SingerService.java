package com.music.core.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.music.core.model.Attachment;
import com.music.core.model.PageObject;
import com.music.core.model.Singer;


/**
 *@author hlib
 *@version 1.0 
 */

public interface SingerService {
	
	Singer getByName(String name);
	
	List<Singer> getHotSingers();
	
	PageObject<Singer> listByPage(Singer condition,Integer pageNow);
	
	List<Singer> listAll();
	
	Singer getById(Integer id);
	
	Singer getUnknowSinger();
	
	int setHot(String singerIds);
	
	Singer saveSinger(Singer singer);
	
	Singer update(Singer singer);
	
	void loadIcon(Attachment attachment, HttpServletResponse response);
	
	int del(Integer singerId);
	
	List<Singer> searchByName(String name);
	
}
