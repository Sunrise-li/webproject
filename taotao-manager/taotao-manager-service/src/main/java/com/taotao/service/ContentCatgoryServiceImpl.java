package com.taotao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService{
	@Autowired
	private TbContentCategoryMapper contentCatgoryMapper;
	@Override
	public List<EUTreeNode> getContentCatList(Long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		criteria.andStatusEqualTo(1);
		//执行查询
		List<TbContentCategory> list = contentCatgoryMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	/**
	 * 添加广告
	 */
	@Override
	public TaotaoResult insertContentCategory(Long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		Date date = new Date();
		contentCategory.setCreated(date);
		contentCategory.setIsParent(false);
		contentCategory.setName(name);;
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategory.setIsParent(false);
		contentCategory.setUpdated(date);
		int n = contentCatgoryMapper.insert(contentCategory);
		//返回ID
		long id = contentCategory.getId();
		System.out.println("插入数据的ID---->"+id);
		//取出父节点
		TbContentCategory parentNode = contentCatgoryMapper.selectByPrimaryKey(parentId);
		System.out.println("parentNode ------->"+parentNode.toString());
		//更新父节点
		if(!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			contentCatgoryMapper.updateByPrimaryKey(parentNode);
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteContentCategory(Long parentId, Long id) {
		/*TbContentCategory tcc = new TbContentCategory();
		tcc.setId(id);
		tcc.setStatus(0);
		contentCatgoryMapper.updateByPrimaryKey(tcc);
		//查询当前父节点下有没有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCatgoryMapper.selectByExample(example);
		//如果父节点下没有子节点
		if(list == null || list.size() < 1) {
			TbContentCategory parentNode = new TbContentCategory();
			//更新父节点
			parentNode.setId(parentId);
			parentNode.setIsParent(false);
			contentCatgoryMapper.updateByPrimaryKey(parentNode);
		}*/
		return TaotaoResult.ok();
	}
	/**
	 * 根据Id删除
	 */
	@Override
	public TaotaoResult deleteContentCategory(Long id) {
		TbContentCategoryExample childer = new TbContentCategoryExample();
		Date date = new Date();
		Criteria c1 = childer.createCriteria();
		c1.andIdEqualTo(id);
		//根据Id查出节点并保存父类Id
		List<TbContentCategory> list = contentCatgoryMapper.selectByExample(childer);
		Long parentId = 0l;
		if(list != null && list.size() > 0) {
			TbContentCategory currentNode = list.get(0);
			parentId = currentNode.getParentId();
			//1删除2正常
			currentNode.setStatus(2);
			currentNode.setUpdated(date);
			contentCatgoryMapper.updateByPrimaryKey(currentNode);
		}
		System.out.println("父类ID为---》"+parentId);
		//查询当前父节点下有没有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId).andStatusEqualTo(1);
		
		List<TbContentCategory> nodes = contentCatgoryMapper.selectByExample(example);
		System.out.println("当前父节点下有"+nodes.size()+"个子节点");
		//如果父节点下没有子节点
		if(nodes == null || nodes.size() < 1) {
			//查出父节点
			TbContentCategory parentNode = contentCatgoryMapper.selectByPrimaryKey(parentId);
			System.out.println(parentNode.toString());
			//更新父节点
			parentNode.setUpdated(date);
			parentNode.setIsParent(false);
			System.out.println(parentNode.toString());
			contentCatgoryMapper.updateByPrimaryKey(parentNode);
		}
		return TaotaoResult.ok();
	}
	/**
	 * 更具id修改name
	 */
	@Override
	public TaotaoResult updateConentCategory(Long id, String name) {
		TbContentCategory tb = new TbContentCategory();
		Date date = new Date();
		tb.setId(id);
		tb.setName(name);
		tb.setUpdated(date);
		int n = contentCatgoryMapper.updateByPrimaryKey(tb);
		System.out.println("更新了"+n+"条数据");
		return TaotaoResult.ok();
	}

}
