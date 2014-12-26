package com.hlb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	/**
	 * http get����
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
				conn.disconnect(); //�ͷ����ӣ�����Ӱ��־�����?
//				conn.getInputStream().close();//�ͷ����ӣ�����Ӱ��־�����
			}
		}
	}
	
	/**
	 * http post���ύ
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
//			conn.setInstanceFollowRedirects(true);	//�Զ������ض���
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
			System.out.println("post����ֵ>>>>>>>>>" + resCode + "--" + lines + "---" + responseMsg);
			return lines;
		} catch (IOException e) {
			throw e;
		}finally{
			if(conn != null && conn.getInputStream() != null){
				conn.getInputStream().close(); //ֻ�ͷ�ʵ����Դ
			}
		}
	}
}
