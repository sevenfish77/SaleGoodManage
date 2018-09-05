package com.yzgs.dao;


import java.util.Map;

import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;

public interface GoodStoreDao {
	
	  /**
	   * 根据id 查询Good对象
	   * @param id
	   * @return
	   */
	  public GoodStore getGoodStoreById(String id);
	  
	  /**
	   * 根据id 删除Good对象
	   * @param id
	   * @return
	   */
	  public boolean deleteGoodStoreById(String id);
	  
	  
	  /**
	   * 更新商品分类
	   * @param Good
	   * @return
	   */
	  public boolean updateGoodStore(GoodStore goodStore);
	  
	  
	  /**
	   * 新增商品
	   * @param Good
	   * @return
	   */
	  public boolean addGoodStore(GoodStore goodStore);
	  
	  /**
	   * 分页查询Good方法
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<GoodStore> selectAllByPage(Page<GoodStore> pager, GoodStore t);

	public boolean cleanGoodStore(Map<String, Object> maps);
}
