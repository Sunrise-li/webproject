package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;
import com.taotao.portal.service.SearchService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//去打广告内容
		String json = contentService.getContentAdList();
		model.addAttribute("ad1", json);
		return "index";
	}
	@RequestMapping("/search")
	public String search(@RequestParam("q")String keyword,
				@RequestParam(defaultValue="1")Integer page,
				@RequestParam(defaultValue="20")Integer rows,
				Model model
				) {
		//处理get乱码
		try {
			keyword = new String(keyword.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			keyword = "";
			e.printStackTrace();
		}
		SearchResult search = searchService.search(keyword, page, rows);
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages",search.getPageCount());
		model.addAttribute("itemList",search.getItemList());
		model.addAttribute("page", search.getCurPage());
		return "search";
	}
	
}
