package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtils;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/{cid}")
	public @ResponseBody TaotaoResult getContentList(@PathVariable Long cid) {
		System.out.println("content------------->"+cid);
		List<TbContent> list = contentService.getContentList(cid);
		return TaotaoResult.ok(list);
	}
	@RequestMapping("/sync/content/{cid}")
	public @ResponseBody TaotaoResult syncContent(@PathVariable Long cid) {
		try {
			TaotaoResult result = contentService.syncContent(cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		
	}
}
