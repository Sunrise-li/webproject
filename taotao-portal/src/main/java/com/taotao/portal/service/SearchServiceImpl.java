package com.taotao.portal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.HttpClientUtils;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SEARCH_SERVER_URL}")
	private String SEARCH_SERVER_URL;
	
	@Override
	public SearchResult search(String keyword, Integer page, Integer rows) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("keyword", keyword);
		params.put("page", page);
		params.put("rows", rows);
		//执行请求
		String json = HttpClientUtils.get(SEARCH_SERVER_URL, params);
		//将请求的数据转换为java对象
		TaotaoResult taotaoResult = JSON.parseObject(json, TaotaoResult.class);
		System.out.println("taotaoResult----------------->"+JSON.toJSONString(taotaoResult));
		//将data数据转换为SearchResult对象
		SearchResult searchResult = JSON.parseObject(JSON.toJSONString(taotaoResult.getData()), SearchResult.class);
		System.out.println("searchResult------------------->"+JSON.toJSONString(searchResult));
		return searchResult;
	}

}
