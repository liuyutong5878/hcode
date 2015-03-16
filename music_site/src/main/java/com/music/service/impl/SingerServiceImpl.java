package com.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.core.service.impl.CommonSingerServiceImpl;

/**
 * @author hlib
 * @version 1.0
 */
@Service
public class SingerServiceImpl extends CommonSingerServiceImpl {

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
