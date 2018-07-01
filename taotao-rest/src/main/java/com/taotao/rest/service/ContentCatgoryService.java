package com.taotao.rest.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;

public interface ContentCatgoryService {
	public List<EUTreeNode> getContentCatList(Long parentId);
}
