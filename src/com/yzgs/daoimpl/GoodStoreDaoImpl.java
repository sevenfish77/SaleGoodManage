package com.yzgs.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.GoodStoreDao;
import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;
import com.yzgs.util.DateUtil;
@Repository
public class GoodStoreDaoImpl implements GoodStoreDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public GoodStore getGoodStoreById(String id) {
		
		return sqlSessionTemplate.selectOne(GoodStore.class.getCanonicalName()+".selectByPk",id);
	}

	@Override
	public boolean deleteGoodStoreById(String id) {
		return sqlSessionTemplate.delete(GoodStore.class.getCanonicalName()+".delete",id)>0?true:false;
	}

	@Override
	public boolean updateGoodStore(GoodStore GoodStore) {
		return sqlSessionTemplate.update(GoodStore.class.getCanonicalName()+".update",GoodStore)>0?true:false;
	}

	@Override
	public boolean addGoodStore(GoodStore GoodStore) {
		return sqlSessionTemplate.insert(GoodStore.class.getCanonicalName()+".create",GoodStore)>0?true:false;
	}
	
	 /**
	   * 分页查询
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<GoodStore> selectAllByPage(Page<GoodStore> pager, GoodStore t) {
		    List<GoodStore> list1=new ArrayList<GoodStore>();
			if(t==null){
				t=new GoodStore();
			}
			List<GoodStore> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
			for(GoodStore g:list){
				  System.out.println(DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date()));
				  if( DateUtil.checkTwoDate(g.getGoodValidDate(), new Date())&&DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date())>=5){
					  //商品有效期时间,大于当前日期
					  g.setBs(0);
				  }else if(DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date())>=0&&DateUtil.getDayByTwoDate(g.getGoodValidDate(),new Date())<=5){
					  g.setBs(1);
					  
				  }else{
					  
					  g.setBs(2);
				  }
				 list1.add(g);
			}
			
			pager.setResults(list1);
			return pager;
		}

	@Override
	public boolean cleanGoodStore(Map<String, Object> maps) {
		return sqlSessionTemplate.insert(GoodStore.class.getCanonicalName()+".cleanStore",maps)>0?true:false;
	}


}
