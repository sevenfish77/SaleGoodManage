package com.yzgs.daoimpl;



import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.ManageDao;
import com.yzgs.domain.Manage;
@Repository
public class ManageDaoImpl implements ManageDao{
   
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Manage getManageByAccount(String account) {
		
		return sqlSessionTemplate.selectOne(getName()+".getManageByAccount", account);
	}
	
	
	
	public String getName(){
		
		return Manage.class.getCanonicalName();
	}



	public boolean updateManagePass(Manage manage) {
		if(sqlSessionTemplate.update(getName()+".updateMm", manage)>0){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	

}
