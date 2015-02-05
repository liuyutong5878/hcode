package com.music.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.music.model.Attachment;
import com.music.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public int add(Attachment entity) {
		
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		
		return 0;
	}

	@Override
	public int update(Attachment entity) {
		return 0;
	}

	@Override
	public Attachment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attachment> getListByCondition(Attachment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attachment> getAll() {
		String sql = "select * from t_attachment";
		List<Attachment> attachments = jdbc.query(sql, new AttachmentRowMapper());
		return attachments;
	}
	
	private class AttachmentRowMapper implements RowMapper<Attachment>{
		@Override
		public Attachment mapRow(ResultSet rs, int arg1) throws SQLException {
			Attachment attach = new Attachment();
			attach.setId(rs.getInt("id"));
			attach.setUri(rs.getString("uri"));
			attach.setFileName(rs.getString("fileName"));
			attach.setFileSize(rs.getFloat("fileSize"));
			attach.setDownloadUrl(rs.getString("downloadUrl"));
			attach.setDownloadAble(rs.getBoolean("downloadAble"));
			attach.setAddTime(rs.getString("addTime"));
			return attach;
		}
	}
}
