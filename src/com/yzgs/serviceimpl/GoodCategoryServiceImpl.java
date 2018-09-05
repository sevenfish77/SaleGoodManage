package com.yzgs.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.GoodCategoryDao;
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodCategoryService;
@Service
public class GoodCategoryServiceImpl  implements GoodCategoryService{
	
	   @Autowired
		GoodCategoryDao goodCategoryDao ;
		 
		@Override
		public ServiceToActionMsg<GoodCategory> selectAllGoodCategoryByPage(Page<GoodCategory> pager) {
			 ServiceToActionMsg<GoodCategory> serviceToActionMsg = new ServiceToActionMsg<GoodCategory>(false);
			 try {
				
				pager=goodCategoryDao.selectAllByPage(pager,pager.getT());
				serviceToActionMsg.setDataList(pager.getResults());
				serviceToActionMsg.setPage(pager);
				serviceToActionMsg.setStatusCode(true);
			} catch (Exception e) {
				serviceToActionMsg.setErrorMsg("分页查询失败");
			   
			}
			 return serviceToActionMsg;
		}

		@Override
		public boolean deleteGoodCategoryById(String id) {
			
			return goodCategoryDao.deleteGoodCategoryById(id);
		}

		@Override
		public GoodCategory getGoodCategoryById(String id) {
			
			return goodCategoryDao.getGoodCategoryById(id);
		}

		@Override
		public boolean addGoodCategory(GoodCategory GoodCategory) {
			
			return goodCategoryDao.addGoodCategory(GoodCategory);
		}

		@Override
		public boolean updateGoodCategory(GoodCategory GoodCategory) {
			return goodCategoryDao.updateGoodCategory(GoodCategory);
		}

		@Override
		public List<GoodCategory> getAllGoodCategory() {
			return goodCategoryDao.getAllGoodCategory();
		}

		@Override
		public boolean checkGoodCategoryStore(String id) {
			
			return goodCategoryDao.checkGoodCategoryStore(id);
		}


}
