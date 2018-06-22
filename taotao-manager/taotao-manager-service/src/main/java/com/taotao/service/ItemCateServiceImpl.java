package com.taotao.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;

import com.taotao.common.result.Result;
import com.taotao.common.result.ResultGenerator;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCateServiceImpl implements ItemCatService{
	@Resource
	private TbItemCatMapper itemCatMapper;

	
	
	@Override
	public void save(TbItemCat model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(List<TbItemCat> models) {
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
	public void update(TbItemCat model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TbItemCat findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TbItemCat findBy(String fieldName, Object value) throws TooManyResultsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbItemCat> findByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbItemCat> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<TbItemCat>> getItemCatListById(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return ResultGenerator.getSuccessResult(list);
	}


}
