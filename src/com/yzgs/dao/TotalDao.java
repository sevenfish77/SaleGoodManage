package com.yzgs.dao;

import java.util.List;
import java.util.Map;

import com.yzgs.domain.MonthGoodGuoQi;

public interface TotalDao {

	public String getZSRByMonth(String i);

	public String getZZCByMonth(String i);

	public String getZSRByMonthAndGoodId(Map maps);

	public String getZJHZEByMonthAndGoodId(Map maps);

	public String getZGQZEByMonthAndGoodId(Map maps);

	public List<MonthGoodGuoQi> getMonthGoodGuoQi(Map maps);

}
