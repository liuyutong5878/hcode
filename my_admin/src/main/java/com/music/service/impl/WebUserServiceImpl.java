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

import com.music.core.model.PageObject;
import com.music.model.WebUser;
import com.music.service.WebUserService;

@Service
public class WebUserServiceImpl implements WebUserService{

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public int deleteById(Integer id) {
		String sql = "delete from web_user where id = ? ";
		return jdbc.update(sql, id);
	}

	@Override
	public WebUser update(WebUser entity) {
		String sql = "update web_user set username = ? , password = ? , email = ?, sex = ? where id = ?";
		jdbc.update(sql, entity.getUserName(), entity.getPassword(), entity.getEmail(), entity.getSex(), entity.getId());
		return entity;
	}

	@Override
	public WebUser getById(Integer id) {
		String sql = "select id,username,password,email,sex from web_user where id = ? ";
		return jdbc.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<WebUser>(WebUser.class));
	}

	@Override
	public List<WebUser> getListByCondition(WebUser entity) {
		String sql = "select * from web_user";
		List<WebUser> ulist = jdbc.query(sql, new BeanPropertyRowMapper<WebUser>(WebUser.class));
		return ulist;
	}

	@Override
	public List<WebUser> getAll() {
		String sql = "select * from web_user";
		List<WebUser> ulist = jdbc.query(sql, new BeanPropertyRowMapper<WebUser>(WebUser.class));
		return ulist;
	}

	@Override
	public PageObject<WebUser> listByPage(Integer pageNow) {
		Integer totalCount = jdbc.queryForObject("select count(1) from web_user", Integer.class);
		PageObject<WebUser> page = new PageObject<WebUser>(totalCount,pageNow);
		String sql = "select * from web_user limit ?,?";
		List<WebUser> webUserList = jdbc.query(sql, new Object[]{page.getPageSize()*(pageNow-1),page.getPageSize()},
				new BeanPropertyRowMapper<WebUser>(WebUser.class));
		page.setList(webUserList);
		return page;
	}

	@Override
	public WebUser addByReturnKey(final WebUser entity) {
		final String sql = "insert into web_user(username, password, email, sex) values(?, ?, ?, ?)";
		KeyHolder kh = new GeneratedKeyHolder();
		jdbc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
				ps.setString(1, entity.getUserName());
				ps.setString(2, entity.getPassword());
				ps.setString(3, entity.getEmail());
				ps.setInt(4, entity.getSex());
				return ps;
			}
		}, kh);
		entity.setId(kh.getKey().intValue());
		return entity;
	}
}
