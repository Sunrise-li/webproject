package com.taotao.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.CatNode;
import com.taotao.common.pojo.ItemCatResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.component.JedisClient;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedis;
	@Value("${REDIS_ITEM_CAT_KEY}")
	private String REDIS_ITEM_CAT_KEY;
	@Override
	public ItemCatResult getItemCatList() {
		List<CatNode> nodes = getItemCatList(0l);
		
		ItemCatResult result = new ItemCatResult();
		result.setData(nodes);
		return result;
	}
	private List getItemCatList(Long parentId) {
		//首先查询缓存
		String value = jedis.hget(REDIS_ITEM_CAT_KEY, parentId+"");
		
		if(value != "" && value != null) {
			//将json转换为对象
			List<CatNode> list = JSON.parseArray(value, CatNode.class);
			System.out.println("查询了缓存------------------->"+value);
			return list;
		}
		//根据parentid查询列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> clist = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		for(TbItemCat cat : clist) {
			//判断是否为父节点
			if(cat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl("/producets/"+cat.getId()+".html");
				//如果是一级节点
				if(cat.getParentId() == 0) {
					node.setName("<a href='/producets/"+cat.getId()+".html'>"+cat.getName()+"</a>");
				}else {
					node.setName(cat.getName());
				}
				node.setItems(getItemCatList(cat.getId()));
				//将node添加到列表
				resultList.add(node);
			}else {
				//如果是叶子节点/
				String url = "/products/"+cat.getId()+".html|"+cat.getName()+"";
				resultList.add(url);
			}
		}
		//加入redis缓存
		//使用hash存储 parentId作为属性
		//将List转为json保存
		try {
			jedis.hset(REDIS_ITEM_CAT_KEY, parentId+"", JSON.toJSONString(resultList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
}
