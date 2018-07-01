package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
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
		System.out.println(content.toString());
		return contentService.insertContent(content);
	}
	@RequestMapping("/edit")
	public @ResponseBody TaotaoResult editContent(TbContent content) {
		System.out.println(content.toString());
		return contentService.editContent(content);
	}
}
