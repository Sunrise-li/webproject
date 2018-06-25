package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.PageResult;
import com.taotao.common.result.Result;
import com.taotao.common.result.ResultGenerator;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public @ResponseBody TbItem getItemById(@PathVariable Long itemId) {
		System.out.println("--------");
		TbItem item =  itemService.getItemById(itemId);
		return item;
	}
	@RequestMapping("/all")
	public @ResponseBody Result<List<TbItem>> findAllItem(){
		Result<List<TbItem>> result = ResultGenerator.getSuccessResult(itemService.findAll());
		System.out.println(result.getData().size());
		System.out.println(result.toString());
		return result; 
	}
	@RequestMapping("/item/list")
	public @ResponseBody EUDataGridResult getPageItemList(Integer page,Integer rows){
		return itemService.getItemList(page, rows);
		
	}
}
