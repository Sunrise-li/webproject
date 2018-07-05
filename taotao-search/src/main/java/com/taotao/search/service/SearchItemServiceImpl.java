package com.taotao.search.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.pojo.SearchResult;

@Service
public class SearchItemServiceImpl implements SearchItemService{
	@Autowired
	private HttpSolrServer solrServer;
	@Autowired
	private ItemMapper itemMapper;
	@Override
	public TaotaoResult importItems() throws Exception {
		//查询出所有商品信息
		List<SearchItem> items = itemMapper.getItemList();
		//将信息添加到文档对象
		for (SearchItem item : items) {
			//创建文档对象
			SolrInputDocument dom = new SolrInputDocument();
			dom.addField("id", item.getId());
			dom.addField("item_title", item.getTitle());
			dom.addField("item_sell_point", item.getSell_point());
			dom.addField("item_price", item.getPrice());
			dom.addField("item_image", item.getImage());
			dom.addField("item_category_name", item.getCategory_name());
			dom.addField("item_desc", item.getItem_desc());
			//写入索引库
			solrServer.add(dom);
		}
		//提交
		solrServer.commit();
		return 	TaotaoResult.ok();
	}

	
}
