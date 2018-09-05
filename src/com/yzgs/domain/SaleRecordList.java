package com.yzgs.domain;

import java.util.List;
/**
 * 构建一个对象,用来存储页面传过来的多个SaleRecord对象
 * @author lenovo
 * 上午10:52:21
 */
public class SaleRecordList {
	
	private List<SaleRecord> saleRecords;

	public List<SaleRecord> getSaleRecords() {
		return saleRecords;
	}

	public void setSaleRecords(List<SaleRecord> saleRecords) {
		this.saleRecords = saleRecords;
	}

	public SaleRecordList(List<SaleRecord> saleRecords) {
		super();
		this.saleRecords = saleRecords;
	}

	public SaleRecordList() {
		super();
	}
	
	
	

}
