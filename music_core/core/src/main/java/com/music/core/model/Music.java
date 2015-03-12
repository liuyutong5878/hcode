package com.music.core.model;

public class Music {

	private Integer id;
	
	private String name;
	
	/**音乐时长*/
	private String time;
	
	private String singer;
	
	private Integer singerId;
	
	private String extension;
	
	private String uri;
	
	private String downloadUrl;
	
	private String addTime;
	
	private Integer language;
	
	/**是否置于首页*/
	private Integer isIndex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(Integer isIndex) {
		this.isIndex = isIndex;
	}

	public Integer getSingerId() {
		return singerId;
	}

	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}
	
}
