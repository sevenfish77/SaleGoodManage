package com.yzgs.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;

public interface  GoodStoreService {
	
	public ServiceToActionMsg<GoodStore> selectAllGoodStoreByPage(Page<GoodStore> pager);

	public boolean deleteGoodStoreById(String id);
	
	public GoodStore getGoodStoreById(String id);
	
	public boolean addGoodStore(GoodStore GoodStore);
	
	public boolean updateGoodStore(GoodStore GoodStore);

	public boolean cleanGoodStore(Map<String, Object> maps);

}
