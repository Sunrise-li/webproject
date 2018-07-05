package com.taotao.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.rest.component.JedisClient;

@Service
public class ItemDescServiceImpl implements ItemDescService{

	@Autowired
	private TbItemDescMapper itemDescMapper;
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
	@Value("${ITEM_DESC_KEY}")
	private String ITEM_DESC_KEY;
	@Value("${ITEM_EXPIRE_SECOND}")
	private Integer ITEM_EXPIRE_SECOND;
	@Override
	public TbItemDesc getItemDescById(Long itemId) {
		try {
			//首先查询缓存
			String resultJson = jedis.get(REDIS_ITEM_KEY + ":" + ITEM_DESC_KEY + ":" +itemId);
			//判断返回结构是否为空
			if(resultJson != null && resultJson != "") {
				//将返回的json数据转换为java对象
				TbItemDesc desc = JSON.parseObject(resultJson, TbItemDesc.class);
				return desc;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据商品Id查询出商品介绍
		TbItemDesc desc = itemDescMapper.selectByPrimaryKey(itemId);
		//加入缓存
		try {
			jedis.set(REDIS_ITEM_KEY + ":" + ITEM_DESC_KEY + ":" +itemId,JSON.toJSONString(desc));
			//设置生存时间
			jedis.expire(REDIS_ITEM_KEY + ":" + ITEM_DESC_KEY + ":" +itemId, ITEM_EXPIRE_SECOND);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desc;
	}
	
	
}
