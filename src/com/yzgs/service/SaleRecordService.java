package com.yzgs.service;

import java.util.List;

import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.SaleRecord;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;

public interface  SaleRecordService {
	
	public ServiceToActionMsg<SaleRecord> selectAllSaleRecordByPage(Page<SaleRecord> pager);

	public boolean deleteSaleRecordById(String id);
	
	public SaleRecord getSaleRecordById(String id);
	
	public boolean addSaleRecord(SaleRecord SaleRecord);
	
	public boolean updateSaleRecord(SaleRecord SaleRecord);

	public SaleProperties getSalePropertiesByStorePh(String storePh);

	public boolean saveSaleRecordList(List<SaleRecord> saleRecords);

}
