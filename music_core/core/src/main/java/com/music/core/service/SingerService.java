package com.music.core.service;

import java.util.List;

import com.music.core.model.PageObject;
import com.music.core.model.Singer;


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
