package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtils;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.service.ItemParamItemService;

@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamItem(@PathVariable Long itemId) {
		try {
			TbItemParamItem param = itemParamItemService.getItemParam(itemId);
			return TaotaoResult.ok(param);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
}
