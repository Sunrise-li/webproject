package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private TbItemParamService itemParamService;
	
	@RequestMapping("/list")
	public @ResponseBody EUDataGridResult getItemParamList(Integer page,Integer rows) {
		return itemParamService.getItemParamList(page,rows);
	}
	@RequestMapping("/query/itemcatid/{cid}")
	public @ResponseBody TaotaoResult getItemCatByCid(@PathVariable Long cid){
		System.out.println("cid----------------------->"+cid);
		return itemParamService.getItemParamByCid(cid);
	}
	@RequestMapping("/save/{cid}")
	public @ResponseBody TaotaoResult insertItemParam(@PathVariable Long cid ,String paramData){
		System.out.println(paramData);
		return itemParamService.insertItemParam(cid, paramData);
		
	}
}
