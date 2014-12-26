package com.hlb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	/**
	 * http get请求
	 * @param addr
	 * @return
	 * @throws IOException
	 */
	public static String get(String addr) throws IOException{
		HttpURLConnection conn = null;
		try {
			URL url = new URL(addr);
			conn =  (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);
			int n;
			char[] cbuf = new char[1024];
			StringWriter lines = new StringWriter();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"utf-8");
			while((n = isr.read(cbuf)) != -1){
				lines.write(cbuf, 0, n);
			}
			return lines.toString();
		}catch (IOException e) {
			throw e;
		}finally{
			if(conn != null && conn.getInputStream() != null){
				conn.disconnect(); //释放连接，可能影响持久连接?
//				conn.getInputStream().close();//释放连接，不会影响持久连接
			}
		}
	}
	
	/**
	 * http post体提交
	 * @param addr
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String post(String addr, String param) throws IOException{
		HttpURLConnection conn = null;
		try {
			URL url = new URL(addr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);
//			conn.setInstanceFollowRedirects(true);	//自动处理重定向
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.getOutputStream().write(param.getBytes());
			conn.connect();
			int resCode = conn.getResponseCode();
			String lines = "";
			String responseMsg = conn.getResponseMessage();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while(reader.ready()){
				lines += reader.readLine();
			}
			reader.close();
			System.out.println("post返回值>>>>>>>>>" + resCode + "--" + lines + "---" + responseMsg);
			return lines;
		} catch (IOException e) {
			throw e;
		}finally{
			if(conn != null && conn.getInputStream() != null){
				conn.getInputStream().close(); //只释放实例资源
			}
		}
	}
}
