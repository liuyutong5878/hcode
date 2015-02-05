package com.music.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SystemUtil {

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
	
	
	
	public static void main(String[] args) {
		/*for(int i=0; i<50; i++){
			System.out.println(getRandomStr(5));
		}*/
		String s = getDateTimeStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(s);
	}
	
}
