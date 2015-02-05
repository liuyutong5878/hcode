package com.music.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.model.User;
import com.music.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private JdbcTemplate jdbc;
	
	public int add(User user) {
		String sql  = "insert into admin_user(username, password) values(?, ?)";
		int row = jdbc.update(sql, user.getUserName(), user.getPassword());
		return row;
	}

	public int deleteById(Integer id) {
		String sql = "delete from admin_user where id = ?";
		int row = jdbc.update(sql, id);
		return row;
	}

	public int update(User user) {
		String sql = "update admin_user set username = ?, password = ? where id = ?";
		int row = jdbc.update(sql, user.getUserName(), user.getPassword(), user.getId());
		return row;
	}

	public User getById(Integer id) {
		String sql = "select id,username,password from admin_user where id = ?";
		User user = jdbc.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<User>());
		return user;
	}

	public List<User> getListByCondition(User user) {
		StringBuffer sql = new StringBuffer("select id, username, password from admin_user where 1=1 ");
		List<String> params = null;
		if(user != null){
			params = new ArrayList<>();
			if(user.getId() != null){
				sql.append(" and id = ?");
				params.add(user.getId()+"");
			}
			if(StringUtils.isNotBlank(user.getUserName())){
				sql.append(" and username like ? ");
				params.add(user.getUserName());
			}
		}
		List<User> ulist = jdbc.queryForList(sql.toString(), params.toArray(), User.class);
		return ulist;
	}

	public boolean isExist(User user) {
		String sql = "select id, username, password from admin_user where username = ? and password = ?";
		List<User> users = jdbc.query(sql, new Object[]{user.getUserName(), user.getPassword()},new BeanPropertyRowMapper<User>(User.class));
		if(users == null || users.size() <= 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<User> getAll() {
		return null;
	}
}
