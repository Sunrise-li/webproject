package com.taotao.common.result;


public class Result<T> {
	private int code;
	private String message;
	private T data;
	
	public Result() {
		// TODO Auto-generated constructor stub
	}
	public Result(Result<T> result) {
		this.code = result.getCode();
		this.message = result.getMessage();
		this.data = result.getData();
	}
	
	public Result setCode(ResultCode resultCode) {
		this.code = resultCode.code();
		return this;
	}
	

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public Result setData(T data) {
		this.data = data;
		return this;
	}

/*	@Override
	public String toString() {
		return 	JSON.toJSONString(this);
	}*/
	
	
	
}
