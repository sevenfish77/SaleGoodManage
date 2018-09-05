package com.yzgs.domain;

import java.util.Date;

/**
 * 商品销售实体,主要是根据入库批号去获取商品名称,商品单价,商品折扣系数等属性返回给页面
 * @author lenovo
 * 上午10:07:38
 */
public class SaleProperties {
	
	private String name;
	
	private int kcNum;//库存数量
	
	private int goodNumXx;//商品库存下限
	
	private Double price;
	
	private Double xs;
	
	private Date goodValidDate;//商品有效日期

	private int bs;//判断是否过期
	
	
	
	
	public int getBs() {
		return bs;
	}

	public void setBs(int bs) {
		this.bs = bs;
	}

	public Date getGoodValidDate() {
		return goodValidDate;
	}

	public void setGoodValidDate(Date goodValidDate) {
		this.goodValidDate = goodValidDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getXs() {
		return xs;
	}

	public void setXs(Double xs) {
		this.xs = xs;
	}

	public int getKcNum() {
		return kcNum;
	}

	public void setKcNum(int kcNum) {
		this.kcNum = kcNum;
	}

	public int getGoodNumXx() {
		return goodNumXx;
	}

	public void setGoodNumXx(int goodNumXx) {
		this.goodNumXx = goodNumXx;
	}
	
	
	
	

}
