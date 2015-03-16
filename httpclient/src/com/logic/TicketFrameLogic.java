package com.logic;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JLabel;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import com.constant.Constants;
import com.entity.Contacts;
import com.entity.Station;
import com.google.gson.Gson;
import com.util.CookiesUtil;
import com.util.HttpClientUtil;
import com.util.JsFunction;
import com.util.Lunar;
import com.util.TimeUtil;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName TicketFrameLogic.java
 * @Description 订票窗口的逻辑处理程序类
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月23日 下午3:10:47
 */
public class TicketFrameLogic {

	private static SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Gson gson = new Gson();
	private static List<Contacts> contacts = new ArrayList<Contacts>();   //联系人列表
	
	private static ConcurrentHashMap<String, Station> mapStation = new ConcurrentHashMap<String, Station>(); //车站信息Map(根据站名)
	private static ConcurrentHashMap<String, Station> mapStation2 = new ConcurrentHashMap<String, Station>(); //车站信息Map(简拼/全拼/汉字)
	private static ConcurrentHashMap<String, Contacts> mapContacts = new ConcurrentHashMap<String, Contacts>();  //联系人Map
	
	static class MyTask extends TimerTask{ 
		private JLabel lblLabel;
		public MyTask(JLabel lblLabel){
			this.lblLabel = lblLabel;
		}
        public void run(){     
        	lblLabel.setText(sdf2.format(new Date())); 
        }     
    } 
	
	public static void main(String[] args){
		resultTime();
	}
	
	
	/**
	 * 登录成功进入列车信息搜索界面，得到key和value
	 * @return
	 */
	public static String leftTicket_init_keyAndValue(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(Constants.LEFTTICKET_INIT_URL);
		String keyAndValue = "";
		try {
			HttpResponse httpResponse = client.execute(httpGet, CookiesUtil.getContext());
			
			//打印头信息
			HttpClientUtil.printResponse(httpResponse);
			
			keyAndValue = JsFunction.value(HttpClientUtil.entityStr(httpResponse), client);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return keyAndValue;
	}
	
	
	/**
	 * 返回可预定车票的时间区间
	 * @return
	 */
	public static String resultTime(){
		String nowTome = sdf.format(new Date());
		Date date = TimeUtil.getAfterDays(new Date(), 59);
		String afterTime = sdf.format(date);
		return nowTome + " - " + afterTime;
	}
	
	/**
	 * 返回可预定车票的时间区间
	 * @return
	 */
	public static String resultLunarTime(){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String nowTome = new Lunar(calendar, false).toString();
        calendar.add(Calendar.DAY_OF_MONTH, 59);
        String afterTime = new Lunar(calendar, false).toString();
		return nowTome + " - " + afterTime;
	}
	
	
	/**
	 * 返回当前时间
	 * @return
	 */
	public static void resultNewTime(JLabel lblLabel){
		Timer timer = new Timer();     
        timer.schedule(new MyTask(lblLabel), 0, 1000);
	}
	
	/**
	 * 获得常用联系人
	 * @return
	 */
	public static List<Contacts> getContactsList(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(Constants.CONTACTS_URL);
		String entityStr = "";
	    try {
			HttpResponse httpResponse = client.execute(httpGet, CookiesUtil.getContext());
			System.out.println("获取乘车人信息Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			
			//entityStr = "{\"validateMessagesShowId\":\"_validatorMessage\",\"status\":true,\"httpstatus\":200,\"data\":{\"isExist\":true,\"exMsg\":\"\",\"two_isOpenClick\":[\"93\",\"95\",\"97\",\"99\"],\"other_isOpenClick\":[\"91\",\"93\",\"98\",\"99\",\"95\",\"97\"],\"normal_passengers\":[{\"code\":\"5\",\"passenger_name\":\"丁雪飞\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1990-01-04 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421087199001043739\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"dingxuefei0104@163.com\",\"address\":\"湖北省松滋市\",\"postalcode\":\"434200\",\"first_letter\":\"\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"0\"},{\"code\":\"3\",\"passenger_name\":\"丁昌兵\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1968-10-14 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422196810143714\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"DCB\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"1\"},{\"code\":\"4\",\"passenger_name\":\"丁春容\",\"sex_code\":\"\",\"born_date\":\"1900-01-01 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422197201183766\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"DCR\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"2\"},{\"code\":\"6\",\"passenger_name\":\"丁麦玉\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"1979-04-14 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422197904143728\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13679885919\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"DMY\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"3\"},{\"code\":\"1\",\"passenger_name\":\"丁倩倩\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"1991-10-20 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421087199110203746\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"DQQ\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"4\"},{\"code\":\"2\",\"passenger_name\":\"丁小容\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"2013-02-19 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422197103283720\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"DXR\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"5\"},{\"code\":\"24\",\"passenger_name\":\"龚振\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1989-07-14 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421023198907148514\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13538788304\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"GZ\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"6\"},{\"code\":\"19\",\"passenger_name\":\"胡三萍\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"1972-12-24 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422197212243728\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13679885919\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"HSP\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"7\"},{\"code\":\"13\",\"passenger_name\":\"李俊杰\",\"sex_code\":\"\",\"born_date\":\"2014-12-19 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421083199102206511\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"LJJ\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"8\"},{\"code\":\"8\",\"passenger_name\":\"刘淼生\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1989-06-30 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"430524198906304073\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"LMS\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"9\"},{\"code\":\"9\",\"passenger_name\":\"劳燕霞\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"1900-01-01 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"440982199208276142\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13538788304\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"LYX\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"10\"},{\"code\":\"7\",\"passenger_name\":\"乔文春\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1968-12-21 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422196812214731\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13532143934\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"QWC\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"11\"},{\"code\":\"21\",\"passenger_name\":\"覃兆琼\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"1971-03-26 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422197103264829\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13532143934\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"QZQ\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"12\"},{\"code\":\"18\",\"passenger_name\":\"石从英\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"2014-08-24 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422427196711203680\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"SCY\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"13\"},{\"code\":\"22\",\"passenger_name\":\"邵定群\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1973-05-22 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"42262619730522811X\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"SDQ\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"14\"},{\"code\":\"17\",\"passenger_name\":\"田政勇\",\"sex_code\":\"\",\"born_date\":\"1900-01-01 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422422196810063773\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"TZY\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"15\"},{\"code\":\"15\",\"passenger_name\":\"王松\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1989-10-11 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421087198910114712\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"18664791107\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"WS\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"16\"},{\"code\":\"14\",\"passenger_name\":\"武田\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"2012-01-31 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"429004199304273685\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"WT\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"17\"},{\"code\":\"16\",\"passenger_name\":\"王玉兰\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"1964-02-17 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"362402196402170025\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"WYL\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"18\"},{\"code\":\"20\",\"passenger_name\":\"袁友才\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1988-08-24 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"429005198808245233\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"YYC\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"19\"},{\"code\":\"10\",\"passenger_name\":\"张华维\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1966-02-06 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"42242219660206371X\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"ZHW\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"20\"},{\"code\":\"12\",\"passenger_name\":\"张能干\",\"sex_code\":\"\",\"born_date\":\"1900-01-01 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"422121196612080094\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13798133462\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"ZNG\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"21\"},{\"code\":\"11\",\"passenger_name\":\"张田田\",\"sex_code\":\"F\",\"sex_name\":\"女\",\"born_date\":\"2014-08-02 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421087199601163726\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"15272604434\",\"phone_no\":\"\",\"email\":\"512499093@qq.com\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"ZTT\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"22\"},{\"code\":\"23\",\"passenger_name\":\"郑直\",\"sex_code\":\"M\",\"sex_name\":\"男\",\"born_date\":\"1988-12-16 00:00:00\",\"country_code\":\"CN\",\"passenger_id_type_code\":\"1\",\"passenger_id_type_name\":\"二代身份证\",\"passenger_id_no\":\"421087198812163211\",\"passenger_type\":\"1\",\"passenger_flag\":\"0\",\"passenger_type_name\":\"成人\",\"mobile_no\":\"13802775190\",\"phone_no\":\"\",\"email\":\"\",\"address\":\"\",\"postalcode\":\"\",\"first_letter\":\"ZZ\",\"recordCount\":\"24\",\"total_times\":\"99\",\"index_id\":\"23\"}],\"dj_passengers\":[]},\"messages\":[],\"validateMessages\":{}}";
			Type type = new TypeToken<JsonObject>(){}.getType();
			Type typeContacts = new TypeToken<List<Contacts>>(){}.getType();
			JsonObject jsonObject = gson.fromJson(entityStr, type);
			jsonObject = jsonObject.getAsJsonObject("data");
			contacts =  gson.fromJson(jsonObject.get("normal_passengers"), typeContacts);
			for(Contacts contact : contacts){
				mapContacts.put(contact.getPassenger_name(), contact);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return contacts;
	}

	
	/**
	 * 初始化车站信息
	 */
	public static void stationInfo(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(Constants.STATION_URL);
		String entityStr = "";
		HttpResponse httpResponse = null;
	    try {
	    	if(CookiesUtil.getContext() !=  null){
	    		httpResponse = client.execute(httpGet, CookiesUtil.getContext());
				System.out.println("初始化车站信息Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
	    	}else{
	    		httpResponse = client.execute(httpGet);
	    	}
			
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse).replace("var station_names ='", "");
			entityStr = entityStr.replace("';", "");
			
			String[] stations = entityStr.split("@");
			for(int i =1; i<stations.length; i++){
				String[] stationStr = stations[i].split("\\|");
				Station station = new Station();
				station.setIndex(Integer.parseInt(stationStr[5]));
				station.setPinyin(stationStr[3]);
				station.setStationName(stationStr[1]);
				station.setCode(stationStr[2]);
				station.setPinyinAbbreviation(stationStr[4]);
				mapStation.put(station.getStationName(), station);
				mapStation2.put(station.getPinyin()+"/"+station.getPinyinAbbreviation()+"/"+station.getStationName(), station);
			}
			System.out.println("车站数量"+mapStation.size());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查询列车信息
	 * @param from 开始城市
	 * @param to  结束城市
	 * @param time  时间
	 * @return
	 */
	public static String trainInfoStr(String from, String to, String time){
		
		from = mapStation.get(from).getCode();
		to = mapStation.get(to).getCode();
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		HttpGet httpGet = new HttpGet(String.format(Constants.TRAIN_INFO_URL, time, from, to));
		String entityStr = "";
		HttpResponse httpResponse =  null;
	    try {
			if(CookiesUtil.getContext() !=  null){
				httpResponse = client.execute(httpGet, CookiesUtil.getContext());
				System.out.println("查询列车信息Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
	    	}else{
	    		httpResponse = client.execute(httpGet);
	    	}
			
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return entityStr;
	}
	
	/**
	 * 根据搜索条件返回车站信息
	 * @param keyword
	 * @return
	 */
	public static List<Station> getStationList(String keyword){
		List<Station> stations = new ArrayList<Station>();
		Iterator<Entry<String, Station>> iter = mapStation2.entrySet().iterator();
		while (iter.hasNext()) {
			ConcurrentHashMap.Entry<String, Station> entry = (ConcurrentHashMap.Entry<String, Station>) iter.next();
			String key = entry.getKey();
			Station val = entry.getValue();
			if(key.contains(keyword)){
				stations.add(val);
			}
		}
		return stations;
	}
	
	
	
	/**
	 * 点击预定按钮，验证用户
	 * @return
	 */
	public static String getCheckUser(){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
	    HttpPost httppost = new HttpPost(Constants.CHECKUSER_URL);
	    String entityStr = "";
	    try {
			HttpResponse httpResponse = client.execute(httppost, CookiesUtil.getContext());
			System.out.println("点击预定按钮，验证用户Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			System.out.println("点击预定按钮，验证用户返回结果："+entityStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return entityStr;
	}
	
	
	
	/**
	 * 点击预定按钮
	 * @param secretStr
	 * @param key
	 * @param from
	 * @param to
	 * @param time
	 * @return
	 */
	public static String getReserveButton(String secretStr, String keyAndValue, String from, String to, String time){
		CloseableHttpClient client = HttpClientUtil.createSSLClientDefault();
		
		String[] keys = keyAndValue.split(":");
		//提交表单的参数
		Map<String, String> formparamsMap = new HashMap<String, String>();
		formparamsMap.put(keys[0], keys[1]);
		formparamsMap.put("myversion", "undefined");
		formparamsMap.put("secretStr", secretStr);
		formparamsMap.put("train_date", time);
		//formparamsMap.put("back_train_date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		formparamsMap.put("tour_flag", "dc");
		formparamsMap.put("purpose_codes", "ADULT");
		formparamsMap.put("query_from_station_name", from);
		formparamsMap.put("query_to_station_name", to);
		formparamsMap.put("undefined", null);
		System.out.println("点击预定按钮提交参数："+gson.toJson(formparamsMap));
		
		//httpclient模拟提交表单
	    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(HttpClientUtil.getParam(formparamsMap), Consts.UTF_8);
	    HttpPost httppost = new HttpPost(Constants.SUBMITORDERREQUEST_URL);
	    httppost.setEntity(entity);
	    String entityStr = "";
	    try {
			HttpResponse httpResponse = client.execute(httppost, CookiesUtil.getContext());
			System.out.println("点击预定按钮Cookies:" + CookiesUtil.getContext().getCookieStore().getCookies());
			HttpClientUtil.printResponse(httpResponse);
			entityStr = HttpClientUtil.entityStr(httpResponse);
			System.out.println("点击预定按钮返回结果："+entityStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return entityStr;
	}
	
	
	public static List<Contacts> getContacts() {
		return contacts;
	}

	public static ConcurrentHashMap<String, Station> getMapStation() {
		return mapStation;
	}

	public static ConcurrentHashMap<String, Contacts> getMapContacts() {
		return mapContacts;
	}

	public static ConcurrentHashMap<String, Station> getMapStation2() {
		return mapStation2;
	}
	
	
}
