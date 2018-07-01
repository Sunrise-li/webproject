package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public EUDataGridResult getContentList(Long categoryId,Integer page,Integer rows) {
		System.out.println("getContentList--------->"+categoryId+"---->"+page+"------>"+ rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		System.out.println("开始分页----------");
		//分页
		PageHelper.startPage(page,rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		System.out.println("有"+list.size()+"条数据");
		for (TbContent tbContent : list) {
			System.out.println(tbContent.toString());
		}
		System.out.println("查询成功");
		EUDataGridResult result = new EUDataGridResult();
	
		result.setRows(list);
		result.setTotal(new PageInfo<>(list).getTotal());
		
		return result;
	}

	/**
	 * 新增内容
	 */
	@Override
	public TaotaoResult insertContent(TbContent content) {
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}
	/**
	 * 编辑内容
	 */
	@Override
	public TaotaoResult editContent(TbContent content) {
		contentMapper.updateByPrimaryKeyWithBLOBs(content);
		return TaotaoResult.ok();
	}

}
