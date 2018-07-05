package com.taotao.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.component.JedisClient;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
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
	@Value("${ITEM_BASE_INFO_KEY}")
	private String ITEM_BASE_INFO_KEY;
	@Value("${ITEM_EXPIRE_SECOND}")
	private Integer ITEM_EXPIRE_SECOND;
	/**
	 * 查询商品信息
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		//先查redis缓存
		try {
			String resultJson = jedis.get(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" +itemId);
			//判断数据是否存在
			if(resultJson!="" && resultJson != null) {
				//将json转换为java对象
				TbItem item = JSON.parseObject(resultJson, TbItem.class);
				return item;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//查询item
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//将java对象转为json
		//存入redis缓存中
		try {
			jedis.set(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" +itemId, JSON.toJSONString(item));
			//设置商品数据在redis中的生存时间
			jedis.expire(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" +itemId, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

}
