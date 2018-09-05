package com.yzgs.domain;

import java.util.List;
import java.util.Map;


public class ServiceToActionMsg<T> {
	
	/** 状态码 */
	private Boolean statusCode;
     
    private String errorMsg;
	/** 返回对象 */
	private T t;
	/** 返回集合对象 */
	private List<T> dataList;
	/** Map类型对象 */
	private Map<String, String> dataMap;
	/** 分页对象 */
	private Page<T> page;
	/**
	 * 操作描述，构造时即编写
	 * */
	private String description;
	/**
	 * 操作成功返回的信息
	 */
	private String successMsg = "操作成功";
	public ServiceToActionMsg() {
	}

	public ServiceToActionMsg(String description,Boolean statusCode) {
		this.statusCode = statusCode;
		this.description = description;
	}
	
	public ServiceToActionMsg(Boolean statusCode) {
		this.statusCode = statusCode;
	}

	public Boolean getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Boolean statusCode) {
		this.statusCode = statusCode;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String toJson() {
			if (statusCode) {
				return "{\"StatusCode\":" + statusCode + ",\"SuccessMsg\":\"" + successMsg + "\"}";
			} else {
				return "{\"StatusCode\":" + statusCode + ",\"ErrorMsg\":\"" + errorMsg + "\"}";
			}
	}

}
