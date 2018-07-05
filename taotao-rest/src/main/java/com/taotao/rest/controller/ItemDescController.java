package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.rest.service.ItemDescService;

/**
 * 商品详情
 * @author Sunrise
 *
 */
@Controller
public class ItemDescController {

	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		try {
			TbItemDesc desc = itemDescService.getItemDescById(itemId);
			System.out.println("rest------------------>"+JSON.toJSONString(desc));
			return TaotaoResult.ok(desc);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
}
