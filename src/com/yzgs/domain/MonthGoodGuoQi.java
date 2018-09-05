package com.yzgs.domain;
/**
 * 过期滤实体对象
 * @author lenovo
 * 下午7:06:46
 */
public class MonthGoodGuoQi {
	
	private String month;//月份
	
	private String goodName;//商品名称
	
	private String storePh;//批次
	
	private Double storeJhs;//进货数量
	
	private Double storeGqs;//过期数量
	
	private String storeGql;//过期率

	 
	
	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStorePh() {
		return storePh;
	}

	public void setStorePh(String storePh) {
		this.storePh = storePh;
	}

	public Double getStoreJhs() {
		return storeJhs;
	}

	public void setStoreJhs(Double storeJhs) {
		this.storeJhs = storeJhs;
	}

	public Double getStoreGqs() {
		return storeGqs;
	}

	public void setStoreGqs(Double storeGqs) {
		this.storeGqs = storeGqs;
	}

	public String getStoreGql() {
		return storeGql;
	}

	public void setStoreGql(String storeGql) {
		this.storeGql = storeGql;
	}
	
	
	

}
