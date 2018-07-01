package com.taotao.common.pojo;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class ItemCatResult {
	private List data;
	
	public List getData() {
		return data;
	}
	
	public void setData(List data) {
		this.data = data;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSON.toJSONString(this);
	}
}
