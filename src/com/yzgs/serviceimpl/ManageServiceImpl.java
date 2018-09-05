package com.yzgs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.ManageDao;
import com.yzgs.domain.Manage;
import com.yzgs.service.ManageService;
@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
	ManageDao manageDao;
	
	@Override
	public Manage getManageByAccount(String accout) {
		
		return manageDao.getManageByAccount(accout);
	}

	@Override
	public boolean updateManagePass(Manage manage) {
		
		return manageDao.updateManagePass(manage);
	}

}
