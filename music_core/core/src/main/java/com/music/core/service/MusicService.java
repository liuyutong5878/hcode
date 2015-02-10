package com.music.core.service;

import java.util.List;

import com.music.core.model.Music;
import com.music.core.model.MusicType;
import com.music.core.model.PageObject;

public interface MusicService {

	List<Music> listAll();
	
	Music insert(Music music);
	
	int update(Music music);
	
	int updateMusicLib();
	
	Music getById(Integer id);
	
	List<MusicType> listAllTypes();
	
	int addToType(String musicIds,Integer typeId);
	
	PageObject<Music> listByPage(Music condition, Integer pageNow);
	
	int del(String ids);
	
	MusicType getTypeById(Integer typeId);
	
	List<Music> getMusicsByTypeId(Integer typeId);
	
	List<Music> getMusicBySingerId(Integer singerId);
	
	List<Music> getMusicBySingerOrMusicName(String name);
	
}
