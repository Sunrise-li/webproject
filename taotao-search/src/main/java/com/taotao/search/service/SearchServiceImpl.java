package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDao searchDao;
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//创建查询条件
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(keyword);
		//设置分页条件
		query.setStart((page-1)*rows);
		//设置默认搜索域
		query.set("df", "item_title");
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
		query.setHighlightSimplePost("</font>");
		//执行查询
		SearchResult result = searchDao.searcg(query);
		//计算页数
		Long recordCount = result.getRecordCount();
		int pageCount = (int)(recordCount /rows);
		if(recordCount % rows > 0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}
}
