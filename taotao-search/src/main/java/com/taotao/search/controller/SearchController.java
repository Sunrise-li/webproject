package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtils;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/q")
	@ResponseBody
	public TaotaoResult search(@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="20")Integer rows) {
		try {
			if(page < 1) {
				page = 1;
			}
			keyword = new String(keyword.getBytes("ISO8859-1"),"UTF-8");
			SearchResult searchResult = searchService.search(keyword, page, rows);
			return TaotaoResult.ok(searchResult);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
 
}
