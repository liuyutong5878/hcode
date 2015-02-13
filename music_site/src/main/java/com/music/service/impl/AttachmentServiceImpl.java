package com.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.music.core.service.impl.CommonAttachmentServiceImpl;

@Service
public class AttachmentServiceImpl extends CommonAttachmentServiceImpl{

	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

}
