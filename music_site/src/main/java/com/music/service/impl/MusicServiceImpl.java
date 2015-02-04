package com.music.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.model.Music;
import com.music.model.MusicType;
import com.music.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public List<Music> listAll() {
		String sql = "select tm.id,tm.name,ts.name as singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime from t_music tm left join t_singer ts on(tm.singerId=ts.id)";
		
		List<Music> musics = jdbc.query(sql, new BeanPropertyRowMapper<Music>(Music.class));
		
		return musics;
	}
	
	@Override
	public Music getById(Integer id) {
		String sql = "select select tm.id,tm.name,ts.name as singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime from t_music tm left join t_singer ts on(tm.singerId=ts.id) from t_music where id = ?";
		Music music = jdbc.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Music>(Music.class));
		return music;
	}

	@Override
	public List<MusicType> listAllTypes() {
		String sql = "select id,name from t_type";
		List<MusicType> types = jdbc.query(sql, new BeanPropertyRowMapper<>(MusicType.class));
		return types;
	}

	@Override
	public MusicType getTypeById(Integer typeId) {
		String sql = "select id,name from t_type where id = ?";
		return jdbc.queryForObject(sql,new Object[]{typeId}, new BeanPropertyRowMapper<>(MusicType.class));
	}

	@Override
	public List<Music> getMusicsByTypeId(Integer typeId) {
		String sql = "SELECT tm.id,tm.name,ts.name singer,tm.time,tm.addTime,tm.downloadUrl,tm.extension FROM t_music tm JOIN t_music_type tmt ON(tmt.`musicId`=tm.`id`) left join t_singer ts on(ts.id=tm.singerId) where typeId = ?";
		List<Music> musics = jdbc.query(sql, new Object[]{typeId}, new BeanPropertyRowMapper<Music>(Music.class));
		return musics;
	}
}
