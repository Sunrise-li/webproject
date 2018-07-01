package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;

@Service
public class TbItemParamServiceImpl implements TbItemParamService{

	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Override
	public EUDataGridResult getItemParamList(int currentPage,int pageSize) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(currentPage, pageSize);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setTotal((long)new PageInfo<>(list).getTotal());
		result.setRows(list);
		return	result ;
	}
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		//根据cid查询规格参数模板
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list != null && list.size() > 0) {
			TbItemParam itemParam = list.get(0);
			return TaotaoResult.ok(itemParam);
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		Date date = new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	/**
	 * 更具规格参数模板删除
	 */
	@Override
	public TaotaoResult deleteItemParam(String paramData) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		return null;
	}

}
