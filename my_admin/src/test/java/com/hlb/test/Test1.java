package com.hlb.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.ArrayUtils;

public class Test1 {

	public static void main(String[] args) {

		List<Integer> intList = new LinkedList<>();
		long ast = System.currentTimeMillis();
				
		
		for(int i=1; i<5000001; i++){
			intList.add(i);
		}
//		Integer[] arr = intList.toArray(new Integer[]{});
//		int[] intArr = ArrayUtils.toPrimitive(arr);
		System.out.println("初始化数组耗时-----" + (System.currentTimeMillis() - ast)/1000 + " s");
//		long st = System.currentTimeMillis();
//		long sum = sumArray(intArr);
//		System.out.println("开始时间--" + st);
	//	
//		System.out.println("并行计算结果"+sum+"--------耗时---" + (System.currentTimeMillis() - st) + " ms");
	//	
//		System.out.println("结束时间---" +System.currentTimeMillis());
//		long sum = 0;
//		for(int a : intArr){
//			sum += a;
//		}
//		System.out.println("普通计算结果--" + sum + "----耗时---" + (System.currentTimeMillis() - st) + " ms");
	}
}
