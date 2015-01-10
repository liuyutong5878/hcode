package com.util;

import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @ClassName HttpClientUtil.java
 * @Description httpclient同时支持http、https协议
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月18日 下午5:08:34
 */
public class HttpClientUtil {
	
	/**
	 * 支持http、https协议
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				//信任所有
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
			
		} catch (KeyManagementException e) {
			e.printStackTrace();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
			
		}
		return HttpClients.createDefault();
	}
	
	
	
	/**
	 * 打印HttpResponse中的相关信息
	 * @param httpResponse
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
		// 响应状态
		//System.out.println("status:" + httpResponse.getStatusLine());
		System.out.println("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}
	}

	
	/**
	 * 获取响应消息实体
	 * @param httpResponse
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String entityStr(HttpResponse httpResponse) throws ParseException, IOException{
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 判断响应实体是否为空
		String responseString = "";
		if (entity != null) {
			responseString = EntityUtils.toString(entity);
		}
		return responseString;
	}
	
	
	
	/**
	 * InputStream转byte数组
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static byte[] getBytes(InputStream is) throws Exception {
		byte[] data = null;
		Collection<byte[]> chunks = new ArrayList<byte[]>();
		byte[] buffer = new byte[1024 * 1000];
		int read = -1;
		int size = 0;

		while ((read = is.read(buffer)) != -1) {
			if (read > 0) {
				byte[] chunk = new byte[read];
				System.arraycopy(buffer, 0, chunk, 0, read);
				chunks.add(chunk);
				size += chunk.length;
			}
		}

		if (size > 0) {
			ByteArrayOutputStream bos = null;
			try {
				bos = new ByteArrayOutputStream(size);
				for (Iterator<byte[]> itr = chunks.iterator(); itr.hasNext();) {
					byte[] chunk = (byte[]) itr.next();
					bos.write(chunk);
				}
				data = bos.toByteArray();
			} finally {
				if (bos != null) {
					bos.close();
				}
			}
		}
		return data;
	}

	
	/**
	 * byte数组转Image
	 * 
	 * @param bytes
	 * @return
	 */
	public static Image bytesToImage(byte[] bytes) {
		Image image = Toolkit.getDefaultToolkit().createImage(bytes);
		try {
			MediaTracker mt = new MediaTracker(new Label());
			mt.addImage(image, 0);
			mt.waitForAll();
		} catch (InterruptedException e) {
		}
		return image;
	}
	
	
	/**
	 * 将验证码的二进制流输出
	 * @param in
	 * @param os
	 * @throws IOException
	 */
	public static void streamPipeline(InputStream in, OutputStream os) throws IOException {
		byte[] buff = new byte[1024];
		int len = 0;
		while ((len = in.read(buff)) > -1) {
			os.write(buff, 0, len);
		}
		close(os, in);
	}

	/**
	 * 关闭流
	 * @param closeables
	 */
	private static void close(Closeable... closeables) {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				try {
					closeable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 返回提交表单时需要的参数格式
	 * @param parameterMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<NameValuePair> getParam(Map<String, String> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<?> it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry parmEntry = (Entry) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
		}
		return param;
	}
}
