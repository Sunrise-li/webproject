package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.PageResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.result.Result;
import com.taotao.common.result.ResultGenerator;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/*@RequestMapping("/item/{itemId}")*/
	public @ResponseBody TbItem getItemById(@PathVariable Long itemId) {
		System.out.println("-----------------");
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
	/**
	 * 查询商品
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/list")
	public @ResponseBody EUDataGridResult getPageItemList(Integer page,Integer rows){
		return itemService.getItemList(page, rows);
	}
	/**
	 * 增加商品
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	@RequestMapping(value = "/item/save",method=RequestMethod.POST)
	public @ResponseBody TaotaoResult itemSave(TbItem item,String desc,String itemParams) {
		
		System.out.println(desc);
		return itemService.createItem(item, desc,itemParams);
	}
	
	/**
	 * 修改商品信息
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/item/update",method=RequestMethod.POST)
	public @ResponseBody TaotaoResult itemUpdate(TbItem item,String desc,String itemParams) {
		System.out.println(JSON.toJSONString(item));
		System.out.println(desc);
		System.out.println(itemParams);
		return itemService.updateItem(item, desc, itemParams);
	}
}
