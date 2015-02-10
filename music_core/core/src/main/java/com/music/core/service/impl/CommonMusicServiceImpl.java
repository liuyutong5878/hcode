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
		String sql = "select tm.id,tm.name,ts.name as singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime from t_music tm left join t_singer ts on(tm.singerId=ts.id) where tm.id = ?";
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
	public PageObject<Music> listByPage(Music condition, Integer pageNow) {
		int totalCount = jdbc.queryForObject("select count(1) from t_music", Integer.class);
		PageObject<Music> page = new PageObject<Music>(totalCount,pageNow);
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
		return jdbc.queryForObject("select * from t_type where id = ?",new Object[]{typeId},new BeanPropertyRowMapper<MusicType>(MusicType.class));
	}

	@Override
	public List<Music> getMusicsByTypeId(Integer typeId) {
		String sql = "SELECT tm.id,tm.name,ts.name singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime,tm.language "
				+ " FROM t_music tm JOIN t_music_type tt ON(tt.`musicId`=tm.`id`) LEFT JOIN t_singer ts ON(ts.id = tm.`singerId`) WHERE tt.`typeId` = ?";
		return jdbc.query(sql, new Object[]{typeId}, new BeanPropertyRowMapper<Music>(Music.class));
	}

	@Override
	public int updateMusicLib() {
		return 0;
	}

	@Override
	public List<Music> getMusicBySingerId(Integer singerId) {
		String sql = "SELECT tm.id,tm.name,ts.name singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,tm.addTime,tm.language "
				+ " FROM t_music tm LEFT JOIN t_singer ts ON(ts.id = tm.`singerId`) WHERE ts.id = ?";
		return jdbc.query(sql, new Object[]{singerId}, new BeanPropertyRowMapper<Music>(Music.class));
	}
	
	@Override
	public List<Music> getMusicBySingerOrMusicName(String name) {
		String sql = "SELECT tm.id,tm.`name`,tm.`time`,tm.`uri`,tm.`extension`,tm.`downloadUrl`,tm.`addTime`,tm.`language`,ts.name singer "
				+ " FROM t_music tm LEFT JOIN t_singer ts ON(tm.`singerId` = ts.`id`) WHERE tm.`name` LIKE ? OR ts.`name` LIKE ? ";
		List<Music> musics = jdbc.query(sql, new Object[]{"%"+name+"%","%"+name+"%"}, new BeanPropertyRowMapper<Music>(Music.class));
		return musics;
	}
	
	public abstract void setJdbc(JdbcTemplate jdbc);
}
