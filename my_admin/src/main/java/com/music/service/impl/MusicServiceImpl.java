package com.music.service.impl;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.core.model.Music;
import com.music.core.model.Singer;
import com.music.core.service.SingerService;
import com.music.core.service.impl.CommonMusicServiceImpl;
import com.music.util.SystemUtil;

@Service
public class MusicServiceImpl extends CommonMusicServiceImpl{

	private Logger log = Logger.getLogger(MusicServiceImpl.class);
	
	@Autowired
	private SingerService singerService;

	@Override
	public int updateMusicLib() {
		String musicPath = SystemUtil.getProp("musicPath");
		File file = new File(musicPath);
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
			if(f.isDirectory()) continue;
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
				 + "'" + singer.getId() + "','" + time + "','\\\\"+f.getName().replace("'", "")+"','" 
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
	public int del(String ids) {
		String sql = "delete from t_music where id in(" + ids + ")";
		return jdbc.update(sql);
	}
	
	@Override
	public Music insert(Music music) {
		// TODO Auto-generated method stub
		return super.insert(music);
	}
	
	@Override
	public int update(Music music) {
		// TODO Auto-generated method stub
		return super.update(music);
	}

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
}
