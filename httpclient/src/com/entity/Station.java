package com.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName Station.java
 * @Description 车站信息
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月25日 下午3:27:35
 */
public class Station implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer index;  //索引
	private String pinyin;  //拼音
	private String stationName;  //站名
	private String code;  //编码
	private String pinyinAbbreviation;  //拼音的简写
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPinyinAbbreviation() {
		return pinyinAbbreviation;
	}
	public void setPinyinAbbreviation(String pinyinAbbreviation) {
		this.pinyinAbbreviation = pinyinAbbreviation;
	}
	
}
