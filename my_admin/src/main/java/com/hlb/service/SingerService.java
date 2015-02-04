package com.hlb.service;

import java.util.List;

import com.hlb.model.PageObject;
import com.hlb.model.Singer;

/**
 *@author hlib
 *@version 1.0 : 2015年2月3日下午8:39:28
 */

public interface SingerService {
	
	Singer getByName(String name);
	
	List<Singer> getHotSingers();
	
	PageObject<Singer> listByPage(Integer pageNow);
	
	List<Singer> listAll();
	
	Singer getById(Integer id);
	
	/**获得未知歌手*/
	Singer getUnknowSinger();
}
