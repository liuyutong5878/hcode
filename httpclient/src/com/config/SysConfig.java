package com.config;

/**
 * 
 * @ClassName SysConfig.java
 * @Description 资源文件路径配置
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月16日 下午3:49:46
 */
public class SysConfig {
    private String cache_path = System.getProperty("user.dir")+System.getProperty("file.separator")+"randCode";
    private String img_path = System.getProperty("user.dir")+System.getProperty("file.separator")+"images";
    
    private static SysConfig instance = null;
    
    public static SysConfig getDefConfig() {
        if (null == instance) {
            instance = new SysConfig();
        }
        return instance;
    }

	public String getCache_path() {
		return cache_path;
	}

	public String getImg_path() {
		return img_path;
	}
	
	
    
}
