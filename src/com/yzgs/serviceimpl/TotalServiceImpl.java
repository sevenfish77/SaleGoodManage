package com.yzgs.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.TotalDao;
import com.yzgs.domain.MonthGoodGuoQi;
import com.yzgs.domain.MonthGoodMoneyTotal;
import com.yzgs.domain.MonthMoneyTotal;
import com.yzgs.service.TotalService;
@Service
public class TotalServiceImpl implements TotalService {
	@Autowired
	TotalDao totalDao;

	@Override
	public List<MonthMoneyTotal> getMonthMoneyTotal(List<String> months) {
		List<MonthMoneyTotal> lists=new ArrayList<MonthMoneyTotal>();
		for(String i:months){
			String ZSR=totalDao.getZSRByMonth(i);
			String ZZC=totalDao.getZZCByMonth(i);
			MonthMoneyTotal moneyTotal=new MonthMoneyTotal();
			moneyTotal.setZSR(ZSR);
			moneyTotal.setZZC(ZZC);
			moneyTotal.setZLR(String.valueOf((Double.parseDouble(ZSR)-Double.parseDouble(ZZC))));
			moneyTotal.setIfyl(Double.parseDouble(ZSR)>Double.parseDouble(ZZC)?"是":"否");
			
			moneyTotal.setMonth(i);
			
			
			lists.add(moneyTotal);
		}
		return lists;
	}

	@Override
	public List<MonthGoodMoneyTotal> getMonthGoodMoneyTotal(
			List<String> months, String goodId) {
		List<MonthGoodMoneyTotal> lists=new ArrayList<MonthGoodMoneyTotal>();
		for(String i:months){
			
			MonthGoodMoneyTotal monthGoodMoneyTotal=new MonthGoodMoneyTotal();
			Map maps=new HashMap<String, String>();
			maps.put("month", i);
			maps.put("goodId", goodId);
			String goodZSR=totalDao.getZSRByMonthAndGoodId(maps);
			//设置单个商品月销售额
			monthGoodMoneyTotal.setZXSJE(Double.parseDouble(goodZSR));
			//查询单个商品总进价总额
			String goodZJHJE=totalDao.getZJHZEByMonthAndGoodId(maps);
			monthGoodMoneyTotal.setZJHJE(Double.parseDouble(goodZJHJE));
			//查询单个商品总过期废物
			String goodZGQJE=totalDao.getZGQZEByMonthAndGoodId(maps);
			monthGoodMoneyTotal.setZGQJE(Double.parseDouble(goodZGQJE));
			
			monthGoodMoneyTotal.setZXSLR(Double.parseDouble(goodZSR)-Double.parseDouble(goodZJHJE)-Double.parseDouble(goodZGQJE));
			monthGoodMoneyTotal.setMonth(i);
			lists.add(monthGoodMoneyTotal);
		}
		return lists;
	}

	@Override
	public List<MonthGoodGuoQi> getMonthGoodGuoQi(List<String> months,
			String goodId) {
		List<MonthGoodGuoQi> lists=new ArrayList<MonthGoodGuoQi>();
		for(String i:months){
			
			
			Map maps=new HashMap<String, String>();
			maps.put("month", i);
			maps.put("goodId", goodId);
			List<MonthGoodGuoQi> list1=totalDao.getMonthGoodGuoQi(maps);
			
			lists.addAll(list1);
			
		}
		return lists;
	}

}
