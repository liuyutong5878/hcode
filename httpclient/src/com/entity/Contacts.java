package com.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName Contacts.java
 * @Description 联系人信息
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月25日 下午3:27:23
 */
public class Contacts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String code;  //联系人编码
	private String passenger_name;  //姓名
	private String sex_code;  //性别编码  M-男  F-女
	private String sex_name;  //性别
	private String born_date;  //出生时间
	private String country_code;  //国家代码
	private String passenger_id_type_code;  //证件类型编码  1-二代身份证    C-港澳通行证   G-台湾通行证   B-护照 
	private String passenger_id_type_name;  //证件类型名称
	private String passenger_id_no; //证件号码
	private String passenger_type;  //旅客类型 1-成人  2-儿童  3-学生  4-残疾军人、伤残人民警察
	private String passenger_flag;
	private String passenger_type_name;  //旅客类型名称
	private String mobile_no;  //手机号码
	private String phone_no;  //电话号码
	private String email;  //电子邮箱
	private String address;  //户籍所在地址
	private String postalcode;  //邮编
	private String first_letter;  //姓名拼音的开头字母（大写）
	private String recordCount;
	private String total_times;
	private String index_id;  //索引
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassenger_name() {
		return passenger_name;
	}
	public void setPassenger_name(String passenger_name) {
		this.passenger_name = passenger_name;
	}
	public String getSex_code() {
		return sex_code;
	}
	public void setSex_code(String sex_code) {
		this.sex_code = sex_code;
	}
	public String getSex_name() {
		return sex_name;
	}
	public void setSex_name(String sex_name) {
		this.sex_name = sex_name;
	}
	public String getBorn_date() {
		return born_date;
	}
	public void setBorn_date(String born_date) {
		this.born_date = born_date;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getPassenger_id_type_code() {
		return passenger_id_type_code;
	}
	public void setPassenger_id_type_code(String passenger_id_type_code) {
		this.passenger_id_type_code = passenger_id_type_code;
	}
	public String getPassenger_id_type_name() {
		return passenger_id_type_name;
	}
	public void setPassenger_id_type_name(String passenger_id_type_name) {
		this.passenger_id_type_name = passenger_id_type_name;
	}
	public String getPassenger_id_no() {
		return passenger_id_no;
	}
	public void setPassenger_id_no(String passenger_id_no) {
		this.passenger_id_no = passenger_id_no;
	}
	public String getPassenger_type() {
		return passenger_type;
	}
	public void setPassenger_type(String passenger_type) {
		this.passenger_type = passenger_type;
	}
	public String getPassenger_flag() {
		return passenger_flag;
	}
	public void setPassenger_flag(String passenger_flag) {
		this.passenger_flag = passenger_flag;
	}
	public String getPassenger_type_name() {
		return passenger_type_name;
	}
	public void setPassenger_type_name(String passenger_type_name) {
		this.passenger_type_name = passenger_type_name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getFirst_letter() {
		return first_letter;
	}
	public void setFirst_letter(String first_letter) {
		this.first_letter = first_letter;
	}
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public String getTotal_times() {
		return total_times;
	}
	public void setTotal_times(String total_times) {
		this.total_times = total_times;
	}
	public String getIndex_id() {
		return index_id;
	}
	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}
	
}
