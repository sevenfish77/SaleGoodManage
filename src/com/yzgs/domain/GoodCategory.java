package com.yzgs.domain;
/**
 * 商品分类实体
 * @author lenovo
 * 下午10:31:34
 */
public class GoodCategory {
	
	  private String id;//分类id 主键
	  
	  private String categoryBh;//商品类型编号
	  
	  private String categoryName;//商品类型名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryBh() {
		return categoryBh;
	}

	public void setCategoryBh(String categoryBh) {
		this.categoryBh = categoryBh;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	  
	  
	
	

}
