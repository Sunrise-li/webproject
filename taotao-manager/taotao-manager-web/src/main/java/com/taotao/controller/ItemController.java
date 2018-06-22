package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.taotao.pojo.TbItem;
import com.taotao.result.Result;
import com.taotao.result.ResultGenerator;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
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
}
