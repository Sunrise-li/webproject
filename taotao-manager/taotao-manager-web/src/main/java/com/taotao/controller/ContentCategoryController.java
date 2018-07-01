package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCatgoryService contentCatgoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> nodes = contentCatgoryService.getContentCatList(parentId);
		for (EUTreeNode euTreeNode : nodes) {
			System.out.println(euTreeNode.toString());
		}
		return nodes;
	}
	/**
	 * 添加节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCatgory(Long parentId,String name) {
		return contentCatgoryService.insertContentCategory(parentId, name);
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContentCatgory(Long id) {
		return contentCatgoryService.deleteContentCategory( id);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public void updateContentCatgory(Long id,String name) {
		contentCatgoryService.updateConentCategory(id, name);
	}
}
