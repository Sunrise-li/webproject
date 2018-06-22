package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.result.Result;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/itemCat")
public class ItemCatController {
	
	@Resource
	private ItemCatService itemCatService;
	/**
	 *	根据parentID查询出商品
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/{parentId}")
	public @ResponseBody Result getItemCatById(@PathVariable Long parentId){
		return itemCatService.getItemCatListById(parentId);
	}
}
