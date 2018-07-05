package com.taotao.portal.service;


import com.taotao.common.pojo.SearchResult;

public interface SearchService {
	public SearchResult search(String keyword,Integer page,Integer rows);
}
