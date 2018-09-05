package com.yzgs.dao;

import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.SaleRecord;
import com.yzgs.domain.Page;

public interface SaleRecordDao {
	
	  /**
	   * 根据id 查询SaleRecord对象
	   * @param id
	   * @return
	   */
	  public SaleRecord getSaleRecordById(String id);
	  
	  /**
	   * 根据id 删除SaleRecord对象
	   * @param id
	   * @return
	   */
	  public boolean deleteSaleRecordById(String id);
	  
	  
	  /**
	   * 更新商品分类
	   * @param SaleRecord
	   * @return
	   */
	  public boolean updateSaleRecord(SaleRecord SaleRecord);
	  
	  
	  /**
	   * 新增商品
	   * @param SaleRecord
	   * @return
	   */
	  public boolean addSaleRecord(SaleRecord SaleRecord);
	  
	  /**
	   * 分页查询SaleRecord方法
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<SaleRecord> selectAllByPage(Page<SaleRecord> pager, SaleRecord t);

	  public SaleProperties getSalePropertiesByStorePh(String storePh);
}
