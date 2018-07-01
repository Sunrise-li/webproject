package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface TbItemParamService {
	public EUDataGridResult getItemParamList(int currentPage,int pageSize);
	
	public TaotaoResult getItemParamByCid(long cid);
	
	public TaotaoResult insertItemParam(Long cid,String paramData);
	
	public TaotaoResult deleteItemParam(String paramData);
}
