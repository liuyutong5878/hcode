package com.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.core.service.impl.CommonCountryServiceImpl;


/**
 *@author hlib
 *@version 1.0 : 2015��2��13������12:16:46
 */
@Service
public class CountryServiceImpl extends CommonCountryServiceImpl{

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
