package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.PageResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService extends Service<TbItem>{
	TbItem getItemById(long itemId);
	EUDataGridResult getItemList(int page,int pageSize);
	TaotaoResult createItem(TbItem item,String desc);
}
