package com.hlb.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Test1 {

	public static void main(String[] args) {
		ConcurrentMap<String, String> cmap = new ConcurrentHashMap<String, String>();
		
		cmap.put("37wan", "pay1");
		cmap.put("37wan", "pay2");
		
		System.out.println(cmap);
		
	}
	
}
