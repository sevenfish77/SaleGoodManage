package com.yzgs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;

public interface  GoodCategoryService {
	
	public ServiceToActionMsg<GoodCategory> selectAllGoodCategoryByPage(Page<GoodCategory> pager);

	public boolean deleteGoodCategoryById(String id);
	
	public GoodCategory getGoodCategoryById(String id);
	
	public boolean addGoodCategory(GoodCategory GoodCategory);
	
	public boolean updateGoodCategory(GoodCategory GoodCategory);
	
	public List<GoodCategory> getAllGoodCategory();

	public boolean checkGoodCategoryStore(String id);

}
