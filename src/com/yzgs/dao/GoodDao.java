package com.yzgs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.yzgs.domain.Good;
import com.yzgs.domain.Page;

public interface GoodDao {
	
	  /**
	   * 根据id 查询Good对象
	   * @param id
	   * @return
	   */
	  public Good getGoodById(String id);
	  
	  /**
	   * 根据id 删除Good对象
	   * @param id
	   * @return
	   */
	  public boolean deleteGoodById(String id);
	  
	  
	  /**
	   * 更新商品分类
	   * @param Good
	   * @return
	   */
	  public boolean updateGood(Good Good);
	  
	  
	  /**
	   * 新增商品
	   * @param Good
	   * @return
	   */
	  public boolean addGood(Good Good);
	  
	  /**
	   * 分页查询Good方法
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<Good> selectAllByPage(Page<Good> pager, Good t);
      /**
       * 查询所有商品
       * @return
       */
	  public List<Good> getAllGood();

	public boolean checkGoodStore(String id);
}
