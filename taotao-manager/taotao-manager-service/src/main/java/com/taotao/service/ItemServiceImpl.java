package com.taotao.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.PageResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.result.Result;
import com.taotao.common.result.ResultGenerator;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
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

	@Override
	public TaotaoResult createItem(TbItem item, String desc,String itemParam) {
		//生成商品ID
		long itemId = IDUtils.genItemId();
		//不全TbItemID
		item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte)1);
		//创建时间和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//插入商品列表
		itemMapper.insert(item);
		//商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		System.out.println(TaotaoResult.ok());
		//添加商品规格参数处理
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(date);
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setUpdated(date);
		itemParamItemMapper.insert(itemParamItem);
		System.out.println(JSON.toJSONString(itemParamItem));
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item,String desc,String itemParams) {
		//修改商品
		Date date = new Date();
		item.setUpdated(date);
		itemMapper.updateByPrimaryKeySelective(item);
		//更新商品描述
		if(desc != null && desc != "") {
			TbItemDesc itemDesc = new TbItemDesc();
			itemDesc.setItemId(item.getId());
			itemDesc.setItemDesc(desc);
			itemDesc.setUpdated(date);
			itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		}
		//更新商品规格参数
		int start = itemParams.indexOf("[");
		int end = itemParams.lastIndexOf("]");
		String test = itemParams.substring(itemParams.indexOf("[")+1);
		test = test.substring(test.indexOf("]")+1);
	
		if(((end - start) > 1) && test !="") {
			TbItemParamItem param = new TbItemParamItem();
			param.setItemId(item.getId());
			param.setParamData(itemParams);
			param.setUpdated(date);
			itemParamItemMapper.updateByPrimaryItemKeySelective(param);
		}
		return TaotaoResult.ok();
	}
	public static void main(String[] args) {
		String str = "[]";
		String test = str.substring(str.indexOf("[")+1);
		test = test.substring(test.indexOf("]")+1);
		System.out.println(test);
	
	
	}

}
