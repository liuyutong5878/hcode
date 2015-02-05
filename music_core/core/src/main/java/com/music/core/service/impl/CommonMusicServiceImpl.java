package com.music.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.music.core.model.Music;
import com.music.core.model.MusicType;
import com.music.core.model.PageObject;
import com.music.core.service.MusicService;

public abstract class CommonMusicServiceImpl implements MusicService{

	private Logger log = Logger.getLogger(CommonMusicServiceImpl.class);
	
	protected JdbcTemplate jdbc;
	
	@Override
	public List<Music> listAll() {
		
		String sql = "select tm.id,tm.name,ts.name as singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime from t_music tm left join t_singer ts on(tm.singerId=ts.id)";
		List<Music> musics = jdbc.query(sql, new BeanPropertyRowMapper<>(Music.class));
		
		return musics;
	}

	@Override
	public Music insert(Music music) {
		return null;
	}

	@Override
	public int update(Music music) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Music getById(Integer id) {
		String sql = "select tm.id,tm.name,ts.name as singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime from t_music tm left join t_singer ts on(tm.singerId=ts.id) where id = ?";
		Music music = jdbc.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Music>());
		return music;
	}

	@Override
	public List<MusicType> listAllTypes() {
		String sql = "select id,name from t_type";
		List<MusicType> types = jdbc.query(sql, new BeanPropertyRowMapper<>(MusicType.class));
		return types;
	}

	@Override
	public int addToType(String musicIds, Integer typeId) {
		String sql = "insert into t_music_type(musicId,typeId) values(?,?)";
		List<Object[]> vals = new ArrayList<>();
		for(String musicId : musicIds.split(",")){
			vals.add(new Object[]{musicId,typeId});
		}
		int[] rows = jdbc.batchUpdate(sql, vals);
		return rows.length;
	}

	@Override
	public PageObject<Music> listByPage(Integer pageNow) {
		int totalCount = jdbc.queryForObject("select count(1) from t_music", Integer.class);
		PageObject<Music> page = new PageObject<Music>(totalCount);
		page.setPageNow(pageNow);
		String sql = "select tm.id,tm.name,ts.name as singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime from t_music tm left join t_singer ts on(tm.singerId=ts.id) limit ?,?";
		List<Music> musics = jdbc.query(sql, new Object[]{page.getPageSize()*(pageNow-1),page.getPageSize()}, new BeanPropertyRowMapper<>(Music.class));
		page.setList(musics);
		return page;
	}

	@Override
	public int del(String ids) {
		return 0;
	}

	@Override
	public MusicType getTypeById(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Music> getMusicsByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMusicLib() {
		// TODO Auto-generated method stub
		return 0;
	}

	public abstract void setJdbc(JdbcTemplate jdbc);
}
