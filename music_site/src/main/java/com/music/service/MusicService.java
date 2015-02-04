package com.music.service;

import java.util.List;

import com.music.model.Music;
import com.music.model.MusicType;

public interface MusicService {

	List<Music> listAll();
	
	Music getById(Integer id);
	
	List<MusicType> listAllTypes();
	
	MusicType getTypeById(Integer typeId);
	
	List<Music> getMusicsByTypeId(Integer typeId);
	
}
