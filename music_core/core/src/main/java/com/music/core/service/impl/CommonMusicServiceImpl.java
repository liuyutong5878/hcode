package com.music.core.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.StringUtils;

import com.music.core.model.Music;
import com.music.core.model.MusicType;
import com.music.core.model.PageObject;
import com.music.core.service.MusicService;

public abstract class CommonMusicServiceImpl implements MusicService{

	private Logger log = Logger.getLogger(CommonMusicServiceImpl.class);
	
	protected JdbcTemplate jdbc;
	
	private static final String SQL_MUSIC  = "SELECT tm.id,ifnull(tm.isIndex,0) isIndex,tm.name,tm.singerId,ts.name singer,tm.time,tm.uri,tm.extension,tm.downloadUrl,"
			+ "tm.addTime,tm.language FROM t_music tm LEFT JOIN t_singer ts ON(ts.id = tm.`singerId`) where 1 = 1 ";
	
	@Override
	public List<Music> listAll() {
		List<Music> musics = jdbc.query(SQL_MUSIC, new BeanPropertyRowMapper<>(Music.class));
		return musics;
	}

	@Override
	public Music insert(Music music) {
		
		return null;
	}

	@Override
	public Music update(Music music) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Music getById(Integer id) {
		String where = " and tm.id = ? ";
		Music music = jdbc.queryForObject(SQL_MUSIC + where, new Object[]{id}, new BeanPropertyRowMapper<Music>(Music.class));
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
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<>();
		if(condition != null){
			if(!StringUtils.isEmpty(condition.getName())){
				where.append(" and tm.`name` LIKE ? OR ts.`name` LIKE ? ");
				params.add("%"+condition.getName()+"%");
				params.add("%"+condition.getName()+"%");
			}
			if(condition.getIsIndex() != null){
				where.append(" and tm.isIndex = ? ");
				params.add(condition.getIsIndex()+"");
			}
		}
		int totalCount = jdbc.query(SQL_MUSIC.replaceAll("SELECT.*FROM", "SELECT COUNT(1) FROM ") + where, params.toArray(), new ResultSetExtractor<Integer>(){
			@Override
			public Integer extractData(ResultSet rs) throws SQLException,DataAccessException {
				if(rs.next()){
					return rs.getInt(1);
				}
				return null;
			}});
		PageObject<Music> page = new PageObject<Music>(totalCount,pageNow);
		String limit = " limit ?,? ";
		params.add(page.getPageSize()*(pageNow-1));
		params.add(page.getPageSize());
		List<Music> musics = jdbc.query(SQL_MUSIC + where + limit, params.toArray(), new BeanPropertyRowMapper<>(Music.class));
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
		String sql = SQL_MUSIC.replace(" where 1 = 1", "") + " JOIN t_music_type tt ON(tt.`musicId`=tm.`id`) ";
		String where = " and tt.`typeId` = ? ";
		return jdbc.query(sql + where, new Object[]{typeId}, new BeanPropertyRowMapper<Music>(Music.class));
	}

	@Override
	public int updateMusicLib() {
		return 0;
	}

	@Override
	public List<Music> getMusicBySingerId(Integer singerId) {
		String where = " and ts.id = ? ";
		return jdbc.query(SQL_MUSIC + where, new Object[]{singerId}, new BeanPropertyRowMapper<Music>(Music.class));
	}
	
	@Override
	public List<Music> getMusicBySingerOrMusicName(String name) {
		String where = " and tm.`name` LIKE ? OR ts.`name` LIKE ? ";
		List<Music> musics = jdbc.query(SQL_MUSIC + where, new Object[]{"%"+name+"%","%"+name+"%"}, new BeanPropertyRowMapper<Music>(Music.class));
		return musics;
	}
	
	@Override
	public List<Music> listIndexMusic(Integer num) {
		String where = " and isIndex = 1 ";
		if(num != null && num > 0){
			where += " limit " + num;
		}
		List<Music> musics = jdbc.query(SQL_MUSIC + where, new BeanPropertyRowMapper<Music>(Music.class));
		return musics;
	}
	
	@Override
	public boolean addToIndex(String ids) {
		String sql = "update t_music set isIndex = 1 where id in ( ? )";
		int row = jdbc.update(sql, ids);
		if(row > 0) return true;
		return false;
	}
	
	public abstract void setJdbc(JdbcTemplate jdbc);
}
