package com.hlb.model;

public class Attachment {

	private Integer id;
	
	private String uri;
	
	private String downloadUrl;
	
	private boolean downloadAble;
	
	private String fileName;
	
	private double fileSize;
	
	private String addTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public boolean isDownloadAble() {
		return downloadAble;
	}

	public void setDownloadAble(boolean downloadAble) {
		this.downloadAble = downloadAble;
	}
	
}
