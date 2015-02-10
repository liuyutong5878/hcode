package com.music.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.music.core.model.PageObject;
import com.music.core.model.Singer;
import com.music.core.service.SingerService;


/**
 *@author hlib
 *@version 1.0 
 */
public abstract class CommonSingerServiceImpl implements SingerService{

	protected JdbcTemplate jdbc;
	
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
	public PageObject<Singer> listByPage(Singer condition,Integer pageNow) {
		StringBuffer sql = new StringBuffer("select * from t_singer where 1=1 ");
		List<Object> params = new ArrayList<>();
		if(condition != null){
			if(!StringUtils.isEmpty(condition.getName())){
				sql.append("and `name` like ? ");
				params.add("%"+condition.getName()+"%");
			}
		}
		String countSql = sql.toString().replace("select * ", "select count(1) ");
		Integer totalCount = jdbc.queryForObject(countSql,params.toArray(),Integer.class);
		PageObject<Singer> page = new PageObject<Singer>(totalCount,pageNow);
		sql.append(" limit ?,?");
		params.add(page.getPageSize()*(pageNow-1));
		params.add(page.getPageSize());
		List<Singer> singers = null;
		try {
			singers = jdbc.query(sql.toString(), params.toArray(new Object[]{}), new BeanPropertyRowMapper<Singer>(Singer.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setList(singers);
		return page;
	}

	@Override
	public List<Singer> listAll() {
		return jdbc.query("select * from t_singer", new BeanPropertyRowMapper<Singer>(Singer.class));
	}

	@Override
	public Singer getById(Integer id) {
		List<Singer> singers = jdbc.query("select * from t_singer where id = ? ", new Object[]{id}, new BeanPropertyRowMapper<Singer>(Singer.class));
		if(singers != null && singers.size() > 0) return singers.get(0);
		return null;
	}

	
	@Override
	public Singer getUnknowSinger() {
		List<Singer> singers = jdbc.query("select * from t_singer where `name` like ? ", new Object[]{"未知%"}, new BeanPropertyRowMapper<Singer>(Singer.class));
		if(singers != null && singers.size() > 0){
			return singers.get(0);
		}
		return null;
	}
	
	@Override
	public int setHot(String singerIds) {
		String sql = "update t_singer set isHot = 1 where id in(?)";
		return jdbc.update(sql, singerIds);
	}
	
	public abstract void setJdbc(JdbcTemplate jdbc);
}
