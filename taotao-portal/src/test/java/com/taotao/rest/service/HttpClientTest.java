package com.taotao.rest.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.pojo.TbItem;
/**
 * HttpClient的使用
 * @author Sunrise
 *
 */
public class HttpClientTest {
	
	/**
	 * GET请求
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void httpGet() throws ClientProtocolException, IOException {
		//1.创建一个可关闭的HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建HttpGet对象，需要一个请求URL
		/*HttpGet get = new HttpGet();
		String url = "http://localhos:8081/rest/item/cat/list";
		get.setURI(URI.create(url));*/
		HttpGet get = new HttpGet( "http://localhost:8081/rest/item/cat/list");
		//执行请求 返回CloseableHttpResponse 对象
		CloseableHttpResponse response = httpClient.execute(get);
		//接收返回结果 HttpEntity对象
		HttpEntity httpEntity = response.getEntity();
		//取出响应结果
		String json = EntityUtils.toString(httpEntity);
		System.out.println(json);
		
		//关闭response HttpClient
		response.close();
		httpClient.close();
	}
	
	/**
	 * POST请求
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void httpPost() throws ClientProtocolException, IOException {
		//创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建HttpPost对象，需要指定一个url
		HttpPost post = new HttpPost("http://localhost:8080/item/list");
		//创建一个List模拟表单;，list中每个元素是一个NameValuePair对象
		List<NameValuePair> formList = new ArrayList<>();
		formList.add(new BasicNameValuePair("page", "1"));
		formList.add(new BasicNameValuePair("rows", "10"));
		//把表单包装到StrongEntity对象
		StringEntity entity = new UrlEncodedFormEntity(formList,"utf-8");
		post.setEntity(entity);
		//执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		//接收返回结果
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		EUDataGridResult eur = JSON.parseObject(result, EUDataGridResult.class);
		
		//关闭资源
		response.close();
		httpClient.close();
		
				
	
	}

}
