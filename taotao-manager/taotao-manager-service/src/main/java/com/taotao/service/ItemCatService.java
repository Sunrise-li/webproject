package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.result.Result;
import com.taotao.pojo.TbItemCat;
public interface ItemCatService extends Service<TbItemCat>{
	public List<EUTreeNode> getItemCatListById(long parentId);
}
