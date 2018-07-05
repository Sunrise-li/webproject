package com.taotao.common.pojo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse response;
	private static HttpGet get;
	private static HttpPost post;
	private static HttpEntity httpEntity;
	 
	public HttpClientUtils() {
		// TODO Auto-generated constructor stub
	}
	public static String get(String url){
		return get(url, null);
	}
	public static String get(String url,Map<String, ?> params){
		try {
			if(params != null && params.size() > 0) {
				Set<String> keys = params.keySet();
				Object[] objs = keys.toArray();
				url +="?"+objs[0]+"="+params.get(objs[0]);
				for (int i = 1; i < objs.length; i++) {
					url += "&"+objs[i]+"="+params.get(objs[i]);
				}
				System.out.println(url);
			}
			httpClient = HttpClients.createDefault();
			//设置请求url
			get = new HttpGet(url);
			
			//执行请求
			response = httpClient.execute(get);
			httpEntity = response.getEntity();
			return EntityUtils.toString(httpEntity);
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return null;
	}
	private static void close() {
		try {
			response.close();
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String post(String url,Map<String, String> map) {
		String result = null;
		httpClient = HttpClients.createDefault();
		post = new HttpPost(url);
		//判断有没有参数
		if(map.isEmpty() || map.size() > 0) {
			List<NameValuePair> list = new ArrayList<>();
			//将参数存放在List
			for (Map.Entry<String, String> entry: map.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			StringEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(list,"utf-8");
				//设置请求参数
				post.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//执行请求
		try {
			response = httpClient.execute(post);
			httpEntity = response.getEntity();
			result = EntityUtils.toString(httpEntity);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return null;
	}
/*	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("keywords", "手机");
		map.put("page", "1");
		map.put("rows","100");
		HttpClientUtils.get("http://localhost:8083/search/q", map);
	}*/
}
