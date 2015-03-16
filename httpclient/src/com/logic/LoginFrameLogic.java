package com.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import com.config.SysConfig;
import com.constant.Constants;
import com.google.gson.Gson;
import com.util.CookiesUtil;
import com.util.HttpClientUtil;
import com.util.JsFunction;

/**
 * @ClassName FrameLogin.java
 * @Description 登录窗口的逻辑处理程序类
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月17日 上午11:01:48
 */
public class LoginFrameLogic {
	
	private static Gson gson = new Gson();
	
	/**
	 * 得到cookie 并且返回随机的key
	 */
	public static String getCookieKeyAndValue(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(Constants.COOKIE_URL);
		String keyAndValue = "";
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			
			//打印头信息
			HttpClientUtil.printResponse(httpResponse);
			
			//得到Cookies
			CookiesUtil.setCookieStore(httpResponse);
			//将Cookies保存至上下文
			CookiesUtil.setContext();
			
			keyAndValue = JsFunction.value(HttpClientUtil.entityStr(httpResponse), client);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return keyAndValue;
	}
	
	
	/**
	 * 获取登陆验证码(文件形式)
	 */
	public static String getLoginRandCode() {
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(Constants.LOGIN_RAND_CODE_URL);
		File file = null;
		long rand = System.currentTimeMillis();
		try {
			HttpResponse httpResponse = client.execute(httpGet, CookiesUtil.getContext());
			System.out.println("获取验证码Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			
			HttpClientUtil.printResponse(httpResponse);
			InputStream in = httpResponse.getEntity().getContent();
			file = new File(SysConfig.getDefConfig().getCache_path(),"login_"+rand+".jpg");
			HttpClientUtil.streamPipeline(in, new FileOutputStream(file));
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(rand);
	}
	
	
	/**
	 * 获取登陆验证码(图片的二进制流形式)
	 * @return
	 */
	public static InputStream getInputStream(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(Constants.LOGIN_RAND_CODE_URL);
		InputStream in = null;
		try {
			HttpResponse httpResponse = client.execute(httpGet, CookiesUtil.getContext());
			System.out.println("获取验证码Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			
			HttpClientUtil.printResponse(httpResponse);
			in = httpResponse.getEntity().getContent();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}
	
	
	
	/**
	 * 验证登录的验证码
	 * @return
	 */
	public static String verLoginRandCode(String code){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		
		//提交表单的参数
		Map<String, String> formparamsMap = new HashMap<String, String>();
		formparamsMap.put("rand", "sjrand");
		formparamsMap.put("randCode", code);
		formparamsMap.put("randCode_validate", "");
		
		//httpclient模拟提交表单
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(HttpClientUtil.getParam(formparamsMap), Consts.UTF_8);
	    HttpPost httppost = new HttpPost(Constants.VER_LOGIN_RAND_CODE_URL);
	    httppost.setEntity(entity);
	    String entityStr = "";
	    try {
			HttpResponse httpResponse = client.execute(httppost, CookiesUtil.getContext());
			System.out.println("验证验证码Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			System.out.println("验证验证码返回结果："+entityStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return entityStr;
	}
	
	
	/**
	 * 登录验证
	 * @param user
	 * @param password
	 * @param code
	 * @param responseString
	 * @return
	 */
	public static String login(String userName, String password, String code, String key_value){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		
		String[] keys = key_value.split(":");
		//提交表单的参数
		Map<String, String> formparamsMap = new HashMap<String, String>();
		formparamsMap.put("loginUserDTO.user_name", userName);
		formparamsMap.put("randCode", code);
		formparamsMap.put("userDTO.password", password);
		formparamsMap.put("randCode_validate", "");
		formparamsMap.put("myversion", "");
		formparamsMap.put(keys[0], keys[1]);
		System.out.println("登录提交参数："+gson.toJson(formparamsMap));
		
		//httpclient模拟提交表单
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(HttpClientUtil.getParam(formparamsMap), Consts.UTF_8);
	    HttpPost httppost = new HttpPost(Constants.VER_LOGIN_NAME_PASSWORD_URL);
	    httppost.setEntity(entity);
	    String entityStr = "";
	    try {
			HttpResponse httpResponse = client.execute(httppost, CookiesUtil.getContext());
			System.out.println("登录Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			System.out.println("登录返回结果："+entityStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return entityStr;
	}
	
	/**
	 * 正式登录
	 */
	public static void login(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		Map<String, String> formparamsMap = new HashMap<String, String>();
		formparamsMap.put("_json_att", "");
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(HttpClientUtil.getParam(formparamsMap), Consts.UTF_8);
	    HttpPost httppost = new HttpPost(Constants.LOGIN_URL);
	    httppost.setEntity(entity);
	    String entityStr = "";
	    try {
			HttpResponse httpResponse = client.execute(httppost, CookiesUtil.getContext());
			System.out.println("正式登录Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			System.out.println("正式登录返回结果："+entityStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
