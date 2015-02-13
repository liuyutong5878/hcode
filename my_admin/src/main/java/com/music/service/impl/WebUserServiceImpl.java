package com.music.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.core.model.PageObject;
import com.music.model.WebUser;
import com.music.service.WebUserService;

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
	public WebUser addByReturnKey(WebUser entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
