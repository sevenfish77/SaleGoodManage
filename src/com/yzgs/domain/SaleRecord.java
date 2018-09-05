package com.yzgs.domain;

import java.util.Date;

/**
 * 销售记录
 * @author lenovo
 * 下午3:41:21
 */
public class SaleRecord {
	
	private String id;
	
	private Date saleDate;
	
	private String saleDate_min;//开始时间
	
	private String saleDate_max;//结束时间
	
	private String storePh;
	
	private int saleNum;
	
	private Double salePrice;
	
	private Double saleMoney;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getStorePh() {
		return storePh;
	}

	public void setStorePh(String storePh) {
		this.storePh = storePh;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}

	public String getSaleDate_min() {
		return saleDate_min;
	}

	public void setSaleDate_min(String saleDate_min) {
		this.saleDate_min = saleDate_min;
	}

	public String getSaleDate_max() {
		return saleDate_max;
	}

	public void setSaleDate_max(String saleDate_max) {
		this.saleDate_max = saleDate_max;
	}



	
	

}
