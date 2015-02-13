package com.music.core.service.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.music.core.model.Country;
import com.music.core.service.CountryService;

/**
 *@author hlib
 *@version 1.0 : 2015年2月13日下午12:05:43
 */
public abstract class CommonCountryServiceImpl implements CountryService{

	protected JdbcTemplate jdbc;
	
	@Override
	public List<Country> getAll() {
		String sql = "select * from t_country";
		List<Country> countrys = jdbc.query(sql, new BeanPropertyRowMapper<Country>(Country.class)); 
		return countrys;
	}
	
	public abstract void setJdbc(JdbcTemplate jdbc);

}
