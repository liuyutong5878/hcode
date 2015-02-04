package com.hlb.service;

import java.util.List;

import com.hlb.model.PageObject;
import com.hlb.model.Singer;

/**
 *@author hlib
 *@version 1.0 : 2015��2��3������8:39:28
 */

public interface SingerService {
	
	Singer getByName(String name);
	
	List<Singer> getHotSingers();
	
	PageObject<Singer> listByPage(Integer pageNow);
	
	List<Singer> listAll();
	
	Singer getById(Integer id);
	
	/**���δ֪����*/
	Singer getUnknowSinger();
}
