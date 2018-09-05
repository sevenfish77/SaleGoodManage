package com.yzgs.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yzgs.domain.Good;
import com.yzgs.domain.Page;
import com.yzgs.domain.ServiceToActionMsg;



public interface  GoodService {
	//泛型  就是说list 匹配一个模型给他，这个list里面只能存  Good类型的对象
	public ServiceToActionMsg<Good> selectAllGoodByPage(Page<Good> pager);

	public boolean deleteGoodById(String id);
	
	public Good getGoodById(String id);
	
	public boolean addGood(Good good);
	
	public boolean updateGood(Good good);
   
	public List<Good> getAllGood();//泛型  就是说list 匹配一个模型给他
								   //这个list里面只能存  Good类型的对象

	public boolean checkGoodStore(String id);

}
