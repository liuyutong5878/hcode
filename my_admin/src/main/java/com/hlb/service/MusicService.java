package com.hlb.service;

import java.util.List;

import com.hlb.model.Music;
import com.hlb.model.MusicType;
import com.hlb.model.PageObject;

public interface MusicService {

	List<Music> listAll();
	
	Music insert(Music music);
	
	int update(Music music);
	
	int batchUpdate();
	
	Music getById(Integer id);
	
	List<MusicType> listAllTypes();
	
	int addToType(String musicIds,Integer typeId);
	
	PageObject<Music> listByPage(Integer pageNow);
	
	int del(String ids);
	
}
