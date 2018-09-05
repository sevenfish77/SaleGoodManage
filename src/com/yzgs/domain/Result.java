package com.yzgs.domain;
/**
 * 操作状态对象
 * @author lenovo
 * 下午3:50:55
 */
public class Result {
	
	private String code;//返回码
	
	private String msg;//信息

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Result(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Result() {
		super();
	
	}
	
	
	

}
