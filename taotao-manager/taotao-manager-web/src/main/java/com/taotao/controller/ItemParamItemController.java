package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.Module;
import com.taotao.service.ItemParamItemService;

@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/{itemId}")
	public String getParamItemHtml(@PathVariable Long itemId,Model model) {
		String html = itemParamItemService.getItemParamHtml(itemId);
		model.addAttribute("html",html);
		return "itemparam";
	}
}
