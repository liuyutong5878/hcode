package com.constant;

public class Constants {
	
	/**
	 * 获取cookie的连接
	 */
	public static final String COOKIE_URL = "https://kyfw.12306.cn/otn/login/init";
	
	/**
	 * 获取登录验证码
	 */
	public static final String LOGIN_RAND_CODE_URL = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";
	
	/**
	 * 获取提交订单验证码
	 */
	public static final String ORDER_RAND_CODE_URL = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=passenger&rand=randp";

	/**
	 * 验证登录验证码
	 */
	public static final String VER_LOGIN_RAND_CODE_URL = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
	
	/**
	 * 验证用户名和密码
	 */
	public static final String VER_LOGIN_NAME_PASSWORD_URL = "https://kyfw.12306.cn/otn/login/loginAysnSuggest";

	/**
	 * 登录
	 */
	public static final String LOGIN_URL = "https://kyfw.12306.cn/otn/login/userLogin";
	
	/**
	 * 获得常用联系人
	 */
	public static final String CONTACTS_URL = "https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs";
	
	
	/**
	 * 注册
	 */
	public static final String REGISTER_URL = "https://kyfw.12306.cn/otn/regist/init";
	
	
	/**
	 * 获得站名
	 */
	public static final String STATION_URL = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.8247";
	
	
	/**
	 * 查询列车信息
	 */
	public static final String TRAIN_INFO_URL = "https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";

	
	/**
	 * 检查用户
	 */
	public static final String CHECKUSER_URL = "https://kyfw.12306.cn/otn/login/checkUser";  //post提交
	
	
	/**
	 * 提交订单
	 */
	public static final String  SUBMITORDERREQUEST_URL = "https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest";  //post请求
	
	
	/**
	 * 登录成功加载页面
	 */
	public static final String LEFTTICKET_INIT_URL = "https://kyfw.12306.cn/otn/leftTicket/init";
}
