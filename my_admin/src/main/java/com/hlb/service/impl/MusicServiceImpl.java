package com.hlb.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hlb.model.Music;
import com.hlb.model.MusicType;
import com.hlb.model.PageObject;
import com.hlb.model.Singer;
import com.hlb.service.MusicService;
import com.hlb.service.SingerService;
import com.hlb.utils.SystemUtil;

@Service
public class MusicServiceImpl implements MusicService{

	private Logger log = Logger.getLogger(MusicServiceImpl.class);
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private SingerService singerService;
	
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
	public int batchUpdate() {
		File file = new File("E:/audio_lib");
		String sql = "";
		List<String> sqls = new ArrayList<>();
		String fname = "";
		MP3File mp3 = null;
		DecimalFormat df = new DecimalFormat("00");
		String time = "";
		String addTime = SystemUtil.getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		int rows = 0;
		Singer unknowSinger = singerService.getUnknowSinger();
		if(unknowSinger == null) return 0;
		Singer singer = null;
		for(File f : file.listFiles()){
			fname = f.getName().replace("`", "\\`").replace("'", "\'");
			try {
				mp3 = (MP3File) AudioFileIO.read(f);
				MP3AudioHeader audioHeader = mp3.getMP3AudioHeader();
				int sec = audioHeader.getTrackLength(); //时长 单位s
				time = df.format(sec/60) + ":" + df.format(sec%60);
			} catch (CannotReadException | IOException | TagException
					| ReadOnlyFileException | InvalidAudioFrameException e) {
				e.printStackTrace();
			}
			String singerName = "";
			try {
				singerName = fname.substring(0,fname.indexOf("-"));
			} catch (Exception e) {
				log.error("文件名不规范,未导入>>> " + fname);
				continue;
			}
			singer = singerService.getByName(singerName);
			if(singer == null) singer = unknowSinger;
			sql = "insert ignore into t_music(name,singerId,time,uri,extension,downloadUrl,`addTime`) values(replace(\""+fname.substring(fname.indexOf("-")+1, fname.lastIndexOf("."))+"\",\"'\",\"''\"),"
				 + "'" + singer.getId() + "','" + time + "',replace(\""+f.getPath().replace("\\", "\\\\")+"\",\"'\",\"''\"),'" 
				 + fname.substring(fname.lastIndexOf(".")+1) + "',replace(\""+f.getAbsolutePath().replace("\\", "\\\\")+"\",\"'\",\"''\"),'"+ addTime + "')";
			sqls.add(sql);
		}
		rows = jdbc.batchUpdate(sqls.toArray(new String[]{})).length;
		//批量设置下载地址
		String sql2 = "update t_music set downloadUrl = concat('/music/',id,'/download.htm')";
		jdbc.update(sql2);
		return rows;
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
		String sql = "delete from t_music where id in(" + ids + ")";
		return jdbc.update(sql);
	}
}
