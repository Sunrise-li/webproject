package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtils;
import com.taotao.search.service.SearchItemService;

@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAll() {
		TaotaoResult result = null;
		try {
			result = searchItemService.importItems();
		} catch (Exception e) {
			e.printStackTrace();
			return result.build(500, ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
}
