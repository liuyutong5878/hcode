package com.hlb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

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
			Map<String,List<String>> reqmap = conn.getRequestProperties();
			conn.setRequestProperty("Referer", "http://adrm5pblpm.l77.yunpan.cn/lk/cfXb7hfxvBmev");
			conn.setRequestProperty("Host", "adrm5pblpm.l77.yunpan.cn");
			System.out.println(reqmap);
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
	
	public static void main(String[] args) {
		String s = "\u767b\u9646\u5931\u8d25";
		System.out.println(s);
	}
}
