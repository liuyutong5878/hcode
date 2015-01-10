package com.util;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName AjavaInvokingFunction.java
 * @Description java调用js脚本方法
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月19日 下午12:43:00
 */

public class JsFunction {
	
	
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * 获得key和value
	 * @param responseString
	 * @return
	 */
	public static String value(String responseString, CloseableHttpClient client){
		// 获得一个JavaScript脚本引擎，也可以是ECMAScript脚本引擎
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String str = "";
				
		Pattern pattern = Pattern.compile("<script src=\"(/otn/dynamicJs/.*?)\" type=\"text/javascript\" xml:space=\"preserve\"></script>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(responseString);
		String strJs = "";
		String key = "";
		while(matcher.find()){
			strJs = matcher.group(1);
			System.out.println("动态js链接：https://kyfw.12306.cn"+strJs);
		}
		
		HttpGet httpGet = new HttpGet("https://kyfw.12306.cn"+strJs);
		try{
			HttpResponse httpResponse = client.execute(httpGet , CookiesUtil.getContext());
			HttpClientUtil.printResponse(httpResponse);
			String jsValue = HttpClientUtil.entityStr(httpResponse);
			
			pattern = Pattern.compile("var key=\'(.*?)\';", Pattern.CASE_INSENSITIVE);
			
			matcher = pattern.matcher(jsValue);
			
			while(matcher.find()){
				jsValue = matcher.group(1);
				System.out.println("动态key:"+jsValue);
			}
			
			key = jsValue;

			// 转换为Invocable
			Invocable invocableEngine = (Invocable) engine;

			// 调用外部脚本执行
			// 创建JS文件的File对象，并读入流
			File functionscript = new File(System.getProperty("user.dir")+FILE_SEPARATOR+"js"+FILE_SEPARATOR+"ticket.js");
			Reader reader = new FileReader(functionscript);
			// 开始执行ajava.js里的程序
			engine.eval(reader);
			// 不带参数调用sayHello方法
			str = invocableEngine.invokeFunction("key_value",key).toString();
			String[] keyVlues = str.split(":");
			str = invocableEngine.invokeFunction("encrypt",keyVlues[1],keyVlues[0]).toString();
			str = invocableEngine.invokeFunction("bin216",str).toString();
			str = invocableEngine.invokeFunction("encode32",str).toString();
			str = keyVlues[0]+":"+str;
			System.out.println("动态的key和value "+str);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		return str;
	}

	
	public static String test(String key){
		// 获得一个JavaScript脚本引擎，也可以是ECMAScript脚本引擎
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String str = "";
		
		try {
			// 转换为Invocable
			Invocable invocableEngine = (Invocable) engine;

			// 调用外部脚本执行
			// 创建JS文件的File对象，并读入流
			File functionscript = new File(System.getProperty("user.dir")+FILE_SEPARATOR+"js"+FILE_SEPARATOR+"ticket.js");
			Reader reader = new FileReader(functionscript);
			// 开始执行ajava.js里的程序
			engine.eval(reader);
			// 不带参数调用sayHello方法
			str = invocableEngine.invokeFunction("key_value",key).toString();
			String[] keyVlues = str.split(":");
			str = invocableEngine.invokeFunction("encrypt",keyVlues[1],keyVlues[0]).toString();
			str = invocableEngine.invokeFunction("bin216",str).toString();
			str = invocableEngine.invokeFunction("encode32",str).toString();
			str = keyVlues[0]+":"+str;
			System.out.println("动态的key和value "+str);
		} catch (Exception e) {
			
		}
		return str;
	}
	
	public static void main(String[] args){
		System.out.println(test("ODcwODAz"));
	}
	
}