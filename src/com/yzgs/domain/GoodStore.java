package com.yzgs.domain;

import java.util.Date;

/**
 * 商品库存实体
 * @author lenovo
 * 下午11:07:13
 */
public class GoodStore {
	
	  private String id;//主键,唯一
	  
	  private String storePh;//入库批号
	  
	  private String goodId;//商品编号
	  
	  private String goodName;//商品名称
	  
	  private Double goodJPrice;//商品进价
	  
	  private int storeNum;//商品数量
	  
	  private int kcNum;//本次商品库存剩余数量
	  
	  private Double goodSumMoney;//这次入库总金额
	  
	  private Date  goodValidDate;//商品有效日期
	  
	  private Date storeDate;//入库时间
	  
	  private Double goodXs;//折扣系数
	  
	  private int bs;//库存中商品的有效期表示,0 代表正常,1代表快到商品有效日期,2代表已过期
	  
	  
	  

	public int getBs() {
		return bs;
	}

	public void setBs(int bs) {
		this.bs = bs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStorePh() {
		return storePh;
	}

	public void setStorePh(String storePh) {
		this.storePh = storePh;
	}

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}


	public Double getGoodJPrice() {
		return goodJPrice;
	}

	public void setGoodJPrice(Double goodJPrice) {
		this.goodJPrice = goodJPrice;
	}

	

	public int getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}

	public Date getGoodValidDate() {
		return goodValidDate;
	}

	public void setGoodValidDate(Date goodValidDate) {
		this.goodValidDate = goodValidDate;
	}

	public Double getGoodXs() {
		return goodXs;
	}

	public void setGoodXs(Double goodXs) {
		this.goodXs = goodXs;
	}

	public Double getGoodSumMoney() {
		return goodSumMoney;
	}

	public void setGoodSumMoney(Double goodSumMoney) {
		this.goodSumMoney = goodSumMoney;
	}

	public Date getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}

	public int getKcNum() {
		return kcNum;
	}

	public void setKcNum(int kcNum) {
		this.kcNum = kcNum;
	}
	  
	  
	

}
