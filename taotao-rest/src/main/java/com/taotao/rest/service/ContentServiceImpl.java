package com.taotao.rest.service;

import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.component.JedisClient;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;

	
	
	@Override
	public List<TbContent> getContentList(Long cid) {
		
		//查询数据之前首先要查询缓存
		try {
			String result = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
			if(result != "" && result != null) {
				//如果缓存中有
				//将json转换为List返回
				List<TbContent> list = JSON.parseArray(result, TbContent.class);
				System.out.println("查询了缓存-------------");
				
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(cid);
		List<TbContent> content = contentMapper.selectByExample(example);
		//在返回结果之前将数据存入redis
		try {
			//使用hash存储
			//定义规范一个key hash中每个项是cid
			//value是list
			String json = JSON.toJSONString(content);
			System.out.println("转成-------->"+json);
			jedisClient.hset(REDIS_CONTENT_KEY, cid+"", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}


	/**
	 * 缓存同步
	 */
	@Override
	public TaotaoResult syncContent(Long cid) {
		
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		System.out.println("同步缓存成功--------》");
		return TaotaoResult.ok();
	}

}
