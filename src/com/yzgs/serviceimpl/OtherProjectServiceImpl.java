package com.yzgs.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzgs.dao.OtherProjectDao;
import com.yzgs.domain.OtherProject;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.OtherProjectService;
@Service
public class OtherProjectServiceImpl  implements OtherProjectService{
	
	 @Autowired
		OtherProjectDao otherProjectDao;
		 
		@Override
		public ServiceToActionMsg<OtherProject> selectAllOtherProjectByPage(Page<OtherProject> pager) {
			 ServiceToActionMsg<OtherProject> serviceToActionMsg = new ServiceToActionMsg<OtherProject>(false);
			 try {
				
				pager=otherProjectDao.selectAllByPage(pager,pager.getT());
				serviceToActionMsg.setDataList(pager.getResults());
				serviceToActionMsg.setPage(pager);
				serviceToActionMsg.setStatusCode(true);
			} catch (Exception e) {
				serviceToActionMsg.setErrorMsg("分页查询失败");
			   
			}
			 return serviceToActionMsg;
		}

		@Override
		public boolean deleteOtherProjectById(String id) {
			
			return otherProjectDao.deleteOtherProjectById(id);
		}

		@Override
		public OtherProject getOtherProjectById(String id) {
			
			return otherProjectDao.getOtherProjectById(id);
		}

		@Override
		public boolean addOtherProject(OtherProject OtherProject) {
			
			return otherProjectDao.addOtherProject(OtherProject);
		}

		@Override
		public boolean updateOtherProject(OtherProject OtherProject) {
			return otherProjectDao.updateOtherProject(OtherProject);
		}


}
