package com.taotao.service;

import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.PageResult;
import com.taotao.common.result.Result;
import com.taotao.common.result.ResultGenerator;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public void save(TbItem model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(List<TbItem> models) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIds(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TbItem model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TbItem findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TbItem findBy(String fieldName, Object value) throws TooManyResultsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbItem> findByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbItem> findAll() {
		// TODO Auto-generated method stub
		return itemMapper.findAllItem();
	}

	@Override
	public EUDataGridResult getItemList(int page, int pageSize) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, pageSize);
		List<TbItem> list = itemMapper.selectByExample(example);
	/*	PageResult<List<TbItem>> item = new PageResult<>(ResultGenerator.getSuccessResult(list));
		item.setTotal(new PageInfo<>(list).getTotal());
		System.out.println(item.toString());*/
		EUDataGridResult eu = new EUDataGridResult();
		eu.setRows(list);
		eu.setTotal(new PageInfo<>(list).getTotal());
		return eu;
	}

}
