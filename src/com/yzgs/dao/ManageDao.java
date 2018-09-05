package com.yzgs.dao;

import org.springframework.stereotype.Repository;

import com.yzgs.domain.Manage;

public  interface ManageDao {
	
	public Manage getManageByAccount(String account);
	
	public boolean updateManagePass(Manage manage);

}
