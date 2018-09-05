package com.yzgs.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.GoodDao;
import com.yzgs.domain.Good;
import com.yzgs.domain.Page;
@Repository
public class GoodDaoImpl implements GoodDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public Good getGoodById(String id) {
		
		return sqlSessionTemplate.selectOne(Good.class.getCanonicalName()+".selectByPk",id);
	}

	@Override
	public boolean deleteGoodById(String id) {
		return sqlSessionTemplate.delete(Good.class.getCanonicalName()+".delete",id)>0?true:false;
	}

	@Override
	public boolean updateGood(Good Good) {
		return sqlSessionTemplate.update(Good.class.getCanonicalName()+".update",Good)>0?true:false;
	}

	@Override
	public boolean addGood(Good Good) {
		return sqlSessionTemplate.insert(Good.class.getCanonicalName()+".create",Good)>0?true:false;
	}
	
	 /**
	   * 分页查询
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<Good> selectAllByPage(Page<Good> pager, Good t) {
			if(t==null){
				t=new Good();
			}
			List<Good> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
			pager.setResults(list);
			return pager;
		}

	@Override
	public List<Good> getAllGood() {
		
		return sqlSessionTemplate.selectList(Good.class.getCanonicalName()+".getAllGood");
	}

	@Override
	public boolean checkGoodStore(String id) {
	    int i=sqlSessionTemplate.selectOne(Good.class.getCanonicalName()+".selectStoreNumByGoodId",id);
		
	    return i>0?true:false;
	}


}
