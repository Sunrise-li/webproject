package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.HttpClientUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
/*	REST_BASE_URL=http://127.0.0.1:8081/rest
		REDIS_SYNC_URL=/sync/content/*/
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REDIS_SYNC_URL}")
	private String REDIS_SYNC_URL;
	
	@Autowired
	private ContentService contentService;
	@RequestMapping("/query/list")
	public @ResponseBody EUDataGridResult getContentList(@RequestParam(value="categoryId",defaultValue="1")Long categoryId,
			@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="rows",defaultValue="20")Integer rows){
		System.out.println(categoryId);
		System.out.println(rows);
		System.out.println(page);
		return contentService.getContentList(categoryId, page, rows);
	}
	
	@RequestMapping("/save")
	public @ResponseBody TaotaoResult saveContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		//调用rest服务同步缓存
		HttpClientUtils.get(REST_BASE_URL+REDIS_SYNC_URL+content.getCategoryId());	
		return result;
	}
	@RequestMapping("/edihttp://www.jd.com/allSort.aspxt")
	public @ResponseBody TaotaoResult editContent(TbContent content) {
		TaotaoResult result = contentService.editContent(content);
		//同步缓存数据
		HttpClientUtils.get(REST_BASE_URL+REDIS_SYNC_URL+content.getCategoryId());
		return result;
	}
}
