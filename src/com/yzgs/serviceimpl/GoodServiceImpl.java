package com.yzgs.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.GoodDao;
import com.yzgs.domain.Good;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodService;
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
	GoodDao goodDao;
	 
	@Override
	public ServiceToActionMsg<Good> selectAllGoodByPage(Page<Good> pager) {
		 ServiceToActionMsg<Good> serviceToActionMsg = new ServiceToActionMsg<Good>(false);
		 try {
			
			pager=goodDao.selectAllByPage(pager,pager.getT());
			serviceToActionMsg.setDataList(pager.getResults());
			serviceToActionMsg.setPage(pager);
			serviceToActionMsg.setStatusCode(true);
		} catch (Exception e) {
			serviceToActionMsg.setErrorMsg("分页查询失败");
		   
		}
		 return serviceToActionMsg;
	}

	@Override
	public boolean deleteGoodById(String id) {
		
		return goodDao.deleteGoodById(id);
	}

	@Override
	public Good getGoodById(String id) {
		
		return goodDao.getGoodById(id);
	}

	@Override
	public boolean addGood(Good good) {
		
		return goodDao.addGood(good);
	}

	@Override
	public boolean updateGood(Good good) {
		return goodDao.updateGood(good);
	}

	@Override
	public List<Good> getAllGood() {
		return goodDao.getAllGood();
	}

	@Override
	public boolean checkGoodStore(String id) {
		// TODO Auto-generated method stub
		return goodDao.checkGoodStore(id);
	}

}
