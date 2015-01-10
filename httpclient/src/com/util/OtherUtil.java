package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName OtherUtil.java
 * @Description 工具类-席别信息
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月25日 下午5:39:36
 */
public class OtherUtil {

	/**
	 * 席别
	 * @return
	 */
	public static Map<String, String> seatingType(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("商务座", "SWZ");
		map.put("特等座", "TZ");
		map.put("一等座", "ZY");
		map.put("二等座", "ZE");
		map.put("高级软卧", "GR");
		map.put("软卧", "RW");
		map.put("硬卧", "YW");
		map.put("动卧", "SRRB");
		map.put("高级动卧", "YYRW");
		map.put("软座", "RZ");
		map.put("硬座", "YZ");
		map.put("无座", "WZ");
		return map;
	}

	/**
	 * 返回席别的代码
	 * @param seatingTypeName
	 * @return
	 */
	public static String seatingTypeCode(String seatingTypeName){
		return seatingType().get(seatingTypeName);
	}
}
