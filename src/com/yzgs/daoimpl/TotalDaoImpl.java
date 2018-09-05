package com.yzgs.daoimpl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.TotalDao;
import com.yzgs.domain.MonthGoodGuoQi;
@Repository
public class TotalDaoImpl implements TotalDao {
   
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	@Override
	public String getZSRByMonth(String i) {
		
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZSRByMonth",i);
	}

	@Override
	public String getZZCByMonth(String i) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZZCByMonth",i);
	}
    /**
     * 单个商品月销售额
     */
	@Override
	public String getZSRByMonthAndGoodId(Map maps) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZXSByMonthAndGoodId",maps);
	}
    /**
     * 单个商品的总进价成本
     */
	@Override
	public String getZJHZEByMonthAndGoodId(Map maps) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZJJByMonthAndGoodId",maps);
	}
    /**
     * 单个商品的过期成本,查询过期表
     */
	@Override
	public String getZGQZEByMonthAndGoodId(Map maps) {
		return sqlSessionTemplate.selectOne("com.yzgs.domain.Total.getZGQByMonthAndGoodId",maps);
	}

	/**
	 * 商品过期信息
	 * */
	@Override
	public List<MonthGoodGuoQi> getMonthGoodGuoQi(Map maps) {
		return sqlSessionTemplate.selectList("com.yzgs.domain.Total.getMonthGoodGuoQi",maps);
	}

}
