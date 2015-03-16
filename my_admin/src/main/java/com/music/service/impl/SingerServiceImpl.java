package com.music.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.music.core.model.Singer;
import com.music.core.service.impl.CommonSingerServiceImpl;


/**
 *@author hlib
 *@version 1.0 : 2015年2月3日下午8:44:50
 */
@Service
public class SingerServiceImpl extends CommonSingerServiceImpl{

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Singer saveSinger(final Singer singer) {
		final String sql = "insert into t_singer(name,englishName,sex,district,iconId,stype,isHot,countryId,profile) values(?,?,?,?,?,?,?,?,?)";
		KeyHolder kh = new GeneratedKeyHolder();
		jdbc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1,singer.getName());
				ps.setString(2, singer.getEnglishName());
				ps.setInt(3, singer.getSex());
				ps.setString(4, singer.getDistrict());
				ps.setInt(5, singer.getIconId());
				ps.setInt(6, singer.getStype());
				ps.setInt(7, singer.getIsHot());
				ps.setInt(8, singer.getCountryId());
				ps.setString(9, singer.getProfile());
				return ps;
			}
		}, kh);
		singer.setId(kh.getKey().intValue());
		return singer;
	}
	
	@Override
	public Singer getByName(String name) {
		return super.getByName(name);
	}
	
	@Override
	public List<Singer> searchByName(String name) {
		String sql = "select * from t_singer where name like ?";
		List<Singer> singers = jdbc.query(sql, new Object[]{"%"+name+"%"}, new BeanPropertyRowMapper<Singer>(Singer.class));
		return singers;
	}
	
}
