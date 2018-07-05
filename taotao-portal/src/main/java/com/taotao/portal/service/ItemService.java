package com.taotao.portal.service;

import com.taotao.pojo.TbItem;

public interface ItemService {
	public TbItem getItemById(Long itemId);
	
	public String getItemDesc(Long itemId);
	
	public String getItemParam(Long itemId);
}
