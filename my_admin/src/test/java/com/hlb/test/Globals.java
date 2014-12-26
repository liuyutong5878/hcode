package com.hlb.test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.lang.ArrayUtils;
 
class Globals {
 static ForkJoinPool fjPool = new ForkJoinPool();
}
 
class Sum extends RecursiveTask {
    static final int SEQUENTIAL_THRESHOLD = 5000;
 
    int low;
    int high;
    int[] array;
 
Sum(int[] arr, int lo, int hi) {
    array = arr;
    low = lo;
    high = hi;
}
 
protected Long compute() {
    if(high - low <= SEQUENTIAL_THRESHOLD) {
        long sum = 0;
    for(int i=low; i < high; ++i)
       sum += array[i];
       return sum;
    } else {
        int mid = low + (high - low) / 2;
        Sum left = new Sum(array, low, mid);
        Sum right = new Sum(array, mid, high);
        left.fork();
        long rightAns = right.compute();
        long leftAns = (long) left.join();
        return leftAns + rightAns;
    }
}
 
static long sumArray(int[] array) {
    return Globals.fjPool.invoke(new Sum(array,0,array.length));
}

public static void main(String[] args) {
	List<Integer> intList = new ArrayList<>();
	for(int i=1; i<500001; i++){
		intList.add(i);
	}
	Integer[] arr = intList.toArray(new Integer[]{});
	int[] intArr = ArrayUtils.toPrimitive(arr);
	long st = System.currentTimeMillis();
//	long sum = sumArray(intArr);
//	System.out.println("开始时间--" + st);
//	
//	System.out.println("并行计算结果"+sum+"--------耗时---" + (System.currentTimeMillis() - st) + " ms");
//	
//	System.out.println("结束时间---" +System.currentTimeMillis());
	
	long sum = 0;
	for(int a : intArr){
		sum += a;
	}
	System.out.println("普通计算结果--" + sum + "----耗时---" + (System.currentTimeMillis() - st) + " ms");
	
}

}