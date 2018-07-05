package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtils;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/base/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		try {
			TbItem item = itemService.getItemById(itemId);
			System.out.println(JSON.toJSONString(item));
			return TaotaoResult.ok(item);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500,ExceptionUtils.getStackTrace(e));
		}
		
	}
}
