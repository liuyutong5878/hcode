package com.music.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.model.PageObject;
import com.music.model.Singer;
import com.music.service.SingerService;


/**
 *@author hlib
 *@version 1.0 
 */
@Service
public class SingerServiceImpl implements SingerService{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Singer getByName(String name) {
		String sql = "select * from t_singer where `name` = ?";
		List<Singer> singers = jdbc.query(sql, new Object[]{name}, new BeanPropertyRowMapper<Singer>(Singer.class));
		if(singers != null && singers.size() > 0){
			return singers.get(0);
		}
		return null;
	}

	@Override
	public List<Singer> getHotSingers() {
		String sql = "select * from t_singer where isHot = 1";
		return jdbc.query(sql, new BeanPropertyRowMapper<Singer>(Singer.class));
	}

	@Override
	public PageObject<Singer> listByPage(Integer pageNow) {
		Integer totalCount = jdbc.queryForObject("select count(1) from t_singer",Integer.class);
		PageObject<Singer> page = new PageObject<Singer>(totalCount);
		String sql = "select * from t_singer limit ?,?";
		List<Singer> singers = jdbc.query(sql, new Object[]{page.getPageSize()*(pageNow-1),page.getPageSize()}, new BeanPropertyRowMapper<Singer>(Singer.class));
		page.setList(singers);
		return page;
	}

	@Override
	public List<Singer> listAll() {
		return jdbc.query("select * from t_singer", new BeanPropertyRowMapper<Singer>(Singer.class));
	}

	@Override
	public Singer getById(Integer id) {
		List<Singer> singers = jdbc.query("select * from t_", new Object[]{id}, new BeanPropertyRowMapper<Singer>(Singer.class));
		if(singers != null && singers.size() > 0) return singers.get(0);
		return null;
	}

	
	@Override
	public Singer getUnknowSinger() {
		List<Singer> singers = jdbc.query("select * from t_singer where `name` = ? ", new Object[]{"未知歌手"}, new BeanPropertyRowMapper<Singer>(Singer.class));
		if(singers != null && singers.size() > 0){
			return singers.get(0);
		}
		return null;
	}

}
