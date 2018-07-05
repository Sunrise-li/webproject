package com.taotao.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.component.JedisClient;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private JedisClient jedis;
	/** * #Redis中商品的KEY
	REDIS_ITEM_KEY=REDIS_ITEM_KEY
	#商品基础信息
	ITEM_BASE_INFO_KEY=ITEM_BASE_INFO_KEY
	#商品介绍
	ITEM_DESC_KEY=ITEM_DESC_KEY
	#商品规格参数
	ITEM_PARAM_KEY=ITEM_PARAM_KEY
	#商品在redis中的默认生存时间
	ITEM_EXPIRE_SECOND=86400
 */
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${ITEM_PARAM_KEY}")
	private String ITEM_PARAM_KEY;
	@Value("${ITEM_EXPIRE_SECOND}")
	private Integer ITEM_EXPIRE_SECOND;
	@Override
	public TbItemParamItem getItemParam(Long itemId) {
	
		//先查询缓存
		try {
			String resultJson = jedis.get(REDIS_ITEM_KEY + ":" + ITEM_PARAM_KEY + ":" + itemId);
			if(resultJson != null && resultJson != "") {
				TbItemParamItem itemParam = JSON.parseObject(resultJson, TbItemParamItem.class);
				return itemParam;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//通过商品ID查询出商品的规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		//设置查询条件
		example.createCriteria().andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		//取规格参数
		if(list != null && list.size() > 0 ) {
			TbItemParamItem itemparam = list.get(0);
			try {
				//转换为json加入缓存
				jedis.set(REDIS_ITEM_KEY + ":" + ITEM_PARAM_KEY + ":" + itemId, JSON.toJSONString(itemparam));
				//设置过期时间
				jedis.expire(REDIS_ITEM_KEY + ":" + ITEM_PARAM_KEY + ":" + itemId, ITEM_EXPIRE_SECOND);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return itemparam;
		}
		return null;
	}

}
