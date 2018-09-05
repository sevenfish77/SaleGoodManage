package com.yzgs.domain;
/**
 * 商品实体
 * @author lenovo
 * 下午10:51:05
 */
public class Good {
     
	  private String id;//商品编号 主键
	  
	  private String goodBh;//商品编号
	  
	  private String goodName;//商品名称
	  
	  private double goodPrice;//商品进价
	  
	  private String goodCategoryId;//商品类型id
	               
	  private String goodCategoryName;//商品类型名称
	  
	  private int storeMin;//该商品的库存下限
	  
	  

	  
	  
		public int getStoreMin() {
		return storeMin;
	}

	public void setStoreMin(int storeMin) {
		this.storeMin = storeMin;
	}

		public String getGoodBh() {
		 return goodBh;
	    }

	   public void setGoodBh(String goodBh) {
		this.goodBh = goodBh;
	   }

		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
		public String getGoodName() {
			return goodName;
		}
	
		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}
	
		public double getGoodPrice() {
			return goodPrice;
		}
	
		public void setGoodPrice(double goodPrice) {
			this.goodPrice = goodPrice;
		}
	
		public String getGoodCategoryId() {
			return goodCategoryId;
		}
	
		public void setGoodCategoryId(String goodCategoryId) {
			this.goodCategoryId = goodCategoryId;
		}
	
		public String getGoodCategoryName() {
			return goodCategoryName;
		}
	
		public void setGoodCategoryName(String goodCategoryName) {
			this.goodCategoryName = goodCategoryName;
		}
	  
	  
}
