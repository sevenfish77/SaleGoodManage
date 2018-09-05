package com.yzgs.service;



import com.yzgs.domain.OtherProject;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;

public interface  OtherProjectService {
	
	public ServiceToActionMsg<OtherProject> selectAllOtherProjectByPage(Page<OtherProject> pager);

	public boolean deleteOtherProjectById(String id);
	
	public OtherProject getOtherProjectById(String id);
	
	public boolean addOtherProject(OtherProject OtherProject);
	
	public boolean updateOtherProject(OtherProject OtherProject);

}
