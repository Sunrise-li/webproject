package com.taotao.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.result.Result;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Resource
	private ItemCatService itemCatService;
	/**
	 *	根据parentID查询出商品
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	public @ResponseBody List<EUTreeNode> getItemCatById(@RequestParam(value="id",defaultValue="0") Long parentId){
		System.out.println(parentId);
		return itemCatService.getItemCatListById(parentId);
	}
}
