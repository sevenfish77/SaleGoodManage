package com.yzgs.service;

import java.util.List;

import com.yzgs.domain.MonthGoodGuoQi;
import com.yzgs.domain.MonthGoodMoneyTotal;
import com.yzgs.domain.MonthMoneyTotal;

public interface TotalService {

	List<MonthMoneyTotal> getMonthMoneyTotal(List<String> months);

	List<MonthGoodMoneyTotal> getMonthGoodMoneyTotal(List<String> months,
			String goodId);

	//泛型  就是说list 匹配一个模型给他，这个list里面只能存  MonthGoodGuoQi类型的对象
	List<MonthGoodGuoQi> getMonthGoodGuoQi(List<String> months, String goodId);

}
