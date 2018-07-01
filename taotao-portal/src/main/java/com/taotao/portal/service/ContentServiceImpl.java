package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.HttpClientUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdNode;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	
	@Value("${REST_CONTENT_AD1_CID}")
	private String REST_CONTENT_AD1_CID;
	
	/**
	 * 获得大广告位的内容
	 */
	@Override
	public String getContentAdList() {
		//调用服务获得数据
		String json = HttpClientUtils.get(REST_BASE_URL+REST_CONTENT_URL+REST_CONTENT_AD1_CID);
		//把json转换成java对象
		System.out.println(json);
		TaotaoResult taotaoResult = JSON.parseObject(json, TaotaoResult.class);
		//取出data属性
		String data  = JSON.toJSONString(taotaoResult.getData());
		//将taotaoResult 转换为list
		List<TbContent> list = JSON.parseArray(data, TbContent.class);
		System.out.println(JSON.toJSONString(taotaoResult.getData()));
		//吧内容列表转换为AdNode列表
		List<AdNode> resultList = new ArrayList<>();
		for(TbContent content : list) {
			AdNode node = new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(content.getPic());
			
			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(content.getPic2());
			node.setAlt(content.getSubTitle());
			node.setHref(content.getUrl());
			resultList.add(node);
		}
		//需要把resultList转换成json数据
		
		return JSON.toJSONString(resultList);
	}
	public static void main(String[] args) {
		String json = HttpClientUtils.get("http://localhost:8081/rest/content/89");
		//把json转换成java对象
		System.out.println(json);
		TaotaoResult taotaoResult = JSON.parseObject(json, TaotaoResult.class);
		List<TbContent> list = JSON.parseArray(JSON.toJSONString(taotaoResult.getData()), TbContent.class);
		taotaoResult.setData(list);
		//取出data属性
		System.out.println(JSON.toJSONString(taotaoResult));
	}
}
