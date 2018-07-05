package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRegistry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao{
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult searcg(SolrQuery query) throws Exception {
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取出查询结果
		SolrDocumentList doms = response.getResults();
		List<SearchItem> items = new ArrayList<>();
		for (SolrDocument dom : doms) {
			//创建SearchItem对象
			SearchItem item = new SearchItem();
			item.setCategory_name((String) dom.get("item_category_name"));
			item.setId((String) dom.get("id"));
			item.setImage((String) dom.get("item_image"));
			item.setPrice((Long) dom.get("item_price"));
			item.setSell_point((String) dom.get("item_sell_point"));
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(dom.get("id")).get("item_title");
			String itemTitle = "";
			if(list != null && list.size() > 0) {
				itemTitle = list.get(0);
			}else {
				itemTitle = (String) dom.get("item_title");
			}
			item.setTitle(itemTitle);
			//添加到列表
			items.add(item);
		}
		SearchResult result = new SearchResult();
		result.setItemList(items);
		System.out.println("查询结果---------------->"+JSON.toJSONString(items));
		//查询结果的总数
		result.setRecordCount(doms.getNumFound());
		return result;
	}
	
}
