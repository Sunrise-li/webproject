package com.taotao.common.pojo;

import com.alibaba.fastjson.JSON;
import com.taotao.common.result.Result;

public class PageResult<T> extends Result<T>{
	
	private long total;
	
	
	public PageResult(Result result) {
		super(result);
	}
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSON.toJSONString(this);
	}
}
