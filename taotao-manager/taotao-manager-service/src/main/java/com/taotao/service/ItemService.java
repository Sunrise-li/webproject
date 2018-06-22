package com.taotao.service;

import com.taotao.pojo.TbItem;

public interface ItemService extends Service<TbItem>{
	TbItem getItemById(long itemId);
}
