package com.music.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import com.music.core.model.Attachment;
import com.music.core.model.PageObject;
import com.music.core.model.Singer;
import com.music.core.service.SingerService;
import com.music.util.SystemUtil;


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
	public Singer update(Singer singer) {
		String sql = "update t_singer set name = ?, englishName = ?, sex = ?, district = ?, iconId = ?, stype = ?, isHot = ?, countryId = ?, profile = ? where id = ?";
		jdbc.update(sql, new Object[]{singer.getName(),singer.getEnglishName(),singer.getSex(),singer.getDistrict(),singer.getIconId(),singer.getStype(),
				singer.getIsHot(),singer.getCountryId(),singer.getProfile(),singer.getId()});
		return singer;
	}
	
	
	@Override
	public void loadIcon(Attachment attach, HttpServletResponse response) {
		String fname = attach.getUid();
		Date addTime = SystemUtil.getDateByString(attach.getAddTime(), "yyyy-MM-dd HH:mm:ss");
		String path = SystemUtil.getProp("uploadPath")+File.separator+SystemUtil.getDateTimeStr(addTime, "yyyyMMdd")+File.separator+fname;
		File icon = new File(path);
		OutputStream ops = null;
		try {
			ops = response.getOutputStream();
			FileUtils.copyFile(icon, ops);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void setJdbc(JdbcTemplate jdbc);
}
