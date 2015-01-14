package com.hlb.utils;

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
	
	public static String getDateTimeStr(Date date){
		
		return "";
	}
	
	
	public static void main(String[] args) {
		for(int i=0; i<50; i++){
			System.out.println(getRandomStr(5));
		}
	}
	
}
