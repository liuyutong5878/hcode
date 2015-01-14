package com.hlb.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.hlb.model.WebUser;
import com.hlb.service.WebUserService;

@Service
public class WebUserServiceImpl implements WebUserService{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public int add(WebUser entity) {
		String sql = "insert into web_user(username, password, email, sex) values(?, ?, ?, ?)";
		int row = jdbc.update(sql, entity.getUserName(), entity.getPassword(), entity.getEmail(), entity.getSex());
		return row;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(WebUser entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WebUser getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebUser> getListByCondition(WebUser entity) {
		String sql = "select * from web_user";
		List<WebUser> ulist = jdbc.query(sql, new WebUserRowMapper());
		return ulist;
	}

	@Override
	public List<WebUser> getAll() {
		String sql = "select * from web_user";
		List<WebUser> ulist = jdbc.query(sql,new WebUserRowMapper());
		return ulist;
	}
	
	private class WebUserRowMapper implements RowMapper<WebUser>{
		@Override
		public WebUser mapRow(ResultSet rs, int arg1) throws SQLException {
			WebUser wbuser = new WebUser();
			wbuser.setId(rs.getInt("id"));
			wbuser.setUserName(rs.getString("userName"));
			wbuser.setPassword(rs.getString("password"));
			wbuser.setEmail(rs.getString("email"));
			wbuser.setSex(rs.getInt("sex"));
			return wbuser;
		}
	}
}
