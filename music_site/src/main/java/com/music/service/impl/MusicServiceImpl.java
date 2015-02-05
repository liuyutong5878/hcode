package com.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.core.service.impl.CommonMusicServiceImpl;

/**
 *@author hlib
 *@version 1.0 : 2015年2月5日下午4:23:26
 */

@Service
public class MusicServiceImpl extends CommonMusicServiceImpl{

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
