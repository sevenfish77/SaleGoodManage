package com.yzgs.domain;

public class MonthMoneyTotal {
	
	private String month;
	
	private String ZSR;//总收入
	
	private String ZZC;//总支出
	
	private String ZLR;//总利润
	
	private String ifyl;//是否盈利
	
	

	public String getZLR() {
		return ZLR;
	}

	public void setZLR(String zLR) {
		ZLR = zLR;
	}

	public String getIfyl() {
		return ifyl;
	}

	public void setIfyl(String ifyl) {
		this.ifyl = ifyl;
	}

	

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getZSR() {
		return ZSR;
	}

	public void setZSR(String zSR) {
		ZSR = zSR;
	}

	public String getZZC() {
		return ZZC;
	}

	public void setZZC(String zZC) {
		ZZC = zZC;
	}
	
	

}
