package com.music.core.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.music.core.model.Attachment;
import com.music.core.service.AttachmentService;

public abstract class CommonAttachmentServiceImpl implements AttachmentService{
	
	protected JdbcTemplate jdbc;

	@Override
	public int deleteById(Integer id) {
		
		return 0;
	}

	@Override
	public Attachment update(Attachment entity) {
		return entity;
	}

	@Override
	public Attachment getById(Integer id) {
		String sql = "select * from t_attachment where id = ? ";
		List<Attachment> attachs = jdbc.query(sql, new Object[]{id}, new BeanPropertyRowMapper<Attachment>(Attachment.class));
		if(attachs != null && attachs.size() > 0) {
			return attachs.get(0);
		}
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

	@Override
	public Attachment addByReturnKey(final Attachment entity) {
		final String sql = "insert into t_attachment(uri,downloadUrl,downloadAble,fileName,fileSize,addTime,type,uid) values(?,?,?,?,?,?,?,?)";
		
		KeyHolder kh = new GeneratedKeyHolder();
		jdbc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, entity.getUri());
				ps.setString(2, entity.getDownloadUrl());
				ps.setBoolean(3, entity.isDownloadAble());
				ps.setString(4, entity.getFileName());
				ps.setDouble(5, entity.getFileSize());
				ps.setString(6, entity.getAddTime());
				ps.setInt(7, entity.getType());
				ps.setString(8, entity.getUid());
				return ps;
			}
		}, kh);
		entity.setId(kh.getKey().intValue());
		return entity;
	}
	
	public abstract void setJdbc(JdbcTemplate jdbc);
	
}
