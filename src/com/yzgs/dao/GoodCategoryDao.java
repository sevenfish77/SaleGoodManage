package com.yzgs.dao;


import java.util.List;

import org.springframework.stereotype.Repository;
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.Page;

public interface GoodCategoryDao {
	
	  /**
	   * 根据id 查询GoodsCateGory对象
	   * @param id
	   * @return
	   */
	  public GoodCategory getGoodCategoryById(String id);
	  
	  /**
	   * 根据id 删除GoodsCateGory对象
	   * @param id
	   * @return
	   */
	  public boolean deleteGoodCategoryById(String id);
	  
	  
	  /**
	   * 更新商品分类
	   * @param goodCategory
	   * @return
	   */
	  public boolean updateGoodCategory(GoodCategory goodCategory);
	  
	  
	  /**
	   * 新增商品分类
	   * @param goodCategory
	   * @return
	   */
	  public boolean addGoodCategory(GoodCategory goodCategory);
	  
	  /**
	   * 分页查询GoodCategory方法
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<GoodCategory> selectAllByPage(Page<GoodCategory> pager, GoodCategory t);
      /**
       * 查询所有商品分类
       * @return
       */
	  public List<GoodCategory> getAllGoodCategory();
     /**
      * 判断该商品分类是否存在库存中,如果存在 不能删除
      * @param id
      * @return
      */
	public boolean checkGoodCategoryStore(String id);
}
