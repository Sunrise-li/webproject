package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	public EUDataGridResult getContentList(Long categoryId,Integer page,Integer rows);
	
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	public TaotaoResult insertContent(TbContent content);
	
	/**
	 * 编辑内容
	 * @param content
	 * @return
	 */
	public TaotaoResult editContent(TbContent content);
}
