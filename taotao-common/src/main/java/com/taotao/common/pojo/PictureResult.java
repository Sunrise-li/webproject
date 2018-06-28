package com.taotao.common.pojo;

import com.alibaba.fastjson.JSON;

public class PictureResult {
	private int error;
	private String url;
	private String message;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSON.toJSONString(this);
	}
}
