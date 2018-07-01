package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatgoryService {
	public List<EUTreeNode> getContentCatList(Long parentId);
	
	public TaotaoResult insertContentCategory(Long parentId,String name);
	
	public TaotaoResult deleteContentCategory(Long parentId,Long id);

	public TaotaoResult deleteContentCategory(Long id);
	
	public TaotaoResult updateConentCategory(Long id,String name);

}
