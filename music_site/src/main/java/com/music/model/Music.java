package com.music.model;

public class Music {

	private Integer id;
	
	private String name;
	
	/**音乐时长*/
	private String time;
	
	private String singer;
	
	private String extension;
	
	private String uri;
	
	private String downloadUrl;
	
	private String addTime;

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

	@Override
	public String toString() {
		return "Music [id=" + id + ", name=" + name + ", time=" + time
				+ ", singer=" + singer + ", extension=" + extension + ", uri="
				+ uri + ", downloadUrl=" + downloadUrl + ", addTime=" + addTime
				+ "]";
	}
	
}
