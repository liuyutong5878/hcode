package com.music.service;

import java.util.List;

import com.music.model.PageObject;
import com.music.model.Singer;


/**
 *@author hlib
 *@version 1.0 
 */

public interface SingerService {
	
	Singer getByName(String name);
	
	List<Singer> getHotSingers();
	
	PageObject<Singer> listByPage(Integer pageNow);
	
	List<Singer> listAll();
	
	Singer getById(Integer id);
	
	Singer getUnknowSinger();
}
