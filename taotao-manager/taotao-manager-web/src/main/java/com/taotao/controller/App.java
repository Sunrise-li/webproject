package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemService;
import com.taotao.service.TbItemParamService;
import com.taotao.service.TbItemParamServiceImpl;

@Controller
@RequestMapping("/test")
public class App {
	
	@Autowired
	private TbItemParamService itemParamService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemParamItemService itemParamItemService;
/*	@RequestMapping("/item/param/list")
	@ResponseBody
	public List<TbItemParam> getItemParamList(){
		return itemParamService.getItemParamList();
	}*/
	@RequestMapping(value="/item/{itemId}")
	public @ResponseBody String getHtml(@PathVariable Long itemId) {
		return itemParamItemService.getItemParamHtml(itemId);
	}
	@RequestMapping(value="/item/list",method=RequestMethod.POST)
	public @ResponseBody EUDataGridResult getPageItemList(Integer page,Integer rows){
		return itemService.getItemList(page, rows);
	}
}
