package com.music.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class SystemUtil {

	public static Date getDateByString(String source,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getRandomStr(int length){
		String chars = "qwertyuiopasdfghjklzxcvbnm1234567890";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i=0; i < length; i++){
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}
	
	public static String getDateTimeStr(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dstr = "";
		try {
			dstr = sdf.format(date);
		} catch (Exception e) {
			
		}
		return dstr;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static String getProp(String key){
		String val = "";
		InputStream in = null;
		try {
			in = SystemUtil.class.getClassLoader().getResourceAsStream("keys.properties");
			System.out.println();
			Properties prop = new Properties();
			prop.load(in);
			val = (String) prop.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return val;
	}
	
	
	public static void main(String[] args) {

		Date date = getDateByString("2015-02-13 16:10:57", "yyyy-MM-dd HH:mm:ss");
		System.out.println(SystemUtil.getDateTimeStr(date, "yyyyMMdd"));
	}
	
}
