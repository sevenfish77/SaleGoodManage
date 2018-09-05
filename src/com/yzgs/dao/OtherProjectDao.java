package com.yzgs.dao;

import com.yzgs.domain.OtherProject;
import com.yzgs.domain.Page;

public interface OtherProjectDao {
	
	  /**
	   * 根据id 查询GoodsCateGory对象
	   * @param id
	   * @return
	   */
	  public OtherProject getOtherProjectById(String id);
	  
	  /**
	   * 根据id 删除GoodsCateGory对象
	   * @param id
	   * @return
	   */
	  public boolean deleteOtherProjectById(String id);
	  
	  
	  /**
	   * 更新商品分类
	   * @param OtherProject
	   * @return
	   */
	  public boolean updateOtherProject(OtherProject OtherProject);
	  
	  
	  /**
	   * 新增商品分类
	   * @param OtherProject
	   * @return
	   */
	  public boolean addOtherProject(OtherProject OtherProject);
	  
	  /**
	   * 分页查询OtherProject方法
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<OtherProject> selectAllByPage(Page<OtherProject> pager, OtherProject t);
}
