package com.taotao.service;

import java.util.List;

import com.taotao.common.result.Result;
import com.taotao.pojo.TbItemCat;
public interface ItemCatService extends Service<TbItemCat>{
	public Result<List<TbItemCat>> getItemCatListById(long parentId);
}
