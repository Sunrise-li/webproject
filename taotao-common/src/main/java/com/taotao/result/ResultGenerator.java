package com.taotao.result;

/**
 * 生成相应结果
 * @author Sunrise
 *
 */
public class ResultGenerator {
	public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
	
	public static Result getSuccessResult() {
		return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
	}
	
	public static	 <T> Result<T> getSuccessResult(T data){
		return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
	}
	public static Result getFailResult(String message) {
		return new Result().setCode(ResultCode.FAIL).setMessage(message);
	}

}
