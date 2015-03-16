package com.util;


import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;

/**
 * @ClassName CookiesUtil.java
 * @Description Cookies工具类
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月17日 上午11:22:30
 */
public class CookiesUtil {
	
	private static CookieStore cookieStore = null;
	private static HttpClientContext context = null;
	
	
	/**
	 * 将Cookie写入上下文
	 */
	public static void setContext() {
		context = HttpClientContext.create();
		Registry<CookieSpecProvider> registry = RegistryBuilder
				.<CookieSpecProvider> create()
				.register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory()).build();
		context.setCookieSpecRegistry(registry);
		context.setCookieStore(cookieStore);
	}

	
	/**
	 * 得到Cookies
	 * @param httpResponse
	 */
	public static void setCookieStore(HttpResponse httpResponse) {
		cookieStore = new BasicCookieStore();
		Header[] headers = httpResponse.getHeaders("Set-Cookie");
		headers = httpResponse.getAllHeaders();
		if(headers != null && headers.length != 0){
			for (int i = 0; i < headers.length; i++) {
				String str_header = headers[i].getValue();
				if(str_header.contains("JSESSIONID")){
					String value = str_header.substring("JSESSIONID=".length(), str_header.indexOf(";"));
					BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", value);
					cookie.setVersion(0);
					cookie.setDomain("kyfw.12306.cn");
					cookie.setPath("/otn");
					cookieStore.addCookie(cookie);
				}else if(str_header.contains("BIGipServerotn")){
					String value = str_header.substring("BIGipServerotn=".length(), str_header.indexOf(";"));
					BasicClientCookie cookie_BIG = new BasicClientCookie("BIGipServerotn", value);
					cookie_BIG.setVersion(0);
					cookie_BIG.setDomain("kyfw.12306.cn");
					cookie_BIG.setPath("/");
					cookieStore.addCookie(cookie_BIG);
				}
			}
		}
		
	}
	

	public static HttpClientContext getContext() {
		return context;
	}
	
}
