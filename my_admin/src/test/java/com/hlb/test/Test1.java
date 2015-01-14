package com.hlb.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hlb.model.WebUser;
import com.hlb.service.WebUserService;
import com.hlb.utils.SystemUtil;



@ContextConfiguration(locations={"/applicationContext.xml","/dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Test1 {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private WebUserService webUserService;
	
	@Test
	public void testJdbc(){
		
		String sql = "insert into admin_user(username,password) values(?,?)";
		int rt = jdbc.update(sql, "admin2","administrator");
		
	}

	@Test
	public void addWebUser(){
		WebUser wu = null;
		
		for(int i=0; i<50; i++){
			wu = new WebUser();
			wu.setUserName(SystemUtil.getRandomStr(5));
			wu.setPassword("pwd_" + SystemUtil.getRandomStr(6));
			wu.setEmail(SystemUtil.getRandomStr(3)+"@37.com");
			wu.setSex(2);
			webUserService.add(wu);
		}
	}
	
}
