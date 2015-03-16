package com.music.utils;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateManager {

	
	@Autowired
	private DataSource dataSource;
	
	public JdbcTemplate getTemplate(){
		
		JdbcTemplate jdbc = new JdbcTemplate(null);
		return jdbc;
	}
}
