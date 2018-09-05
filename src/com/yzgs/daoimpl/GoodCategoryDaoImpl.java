package com.yzgs.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.GoodCategoryDao;
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.Page;
@Repository
public class GoodCategoryDaoImpl implements GoodCategoryDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public GoodCategory getGoodCategoryById(String id) {
		
		return sqlSessionTemplate.selectOne(GoodCategory.class.getCanonicalName()+".selectByPk",id);
	}

	@Override
	public boolean deleteGoodCategoryById(String id) {
		return sqlSessionTemplate.delete(GoodCategory.class.getCanonicalName()+".delete",id)>0?true:false;
	}

	@Override
	public boolean updateGoodCategory(GoodCategory goodCategory) {
		return sqlSessionTemplate.update(GoodCategory.class.getCanonicalName()+".update",goodCategory)>0?true:false;
	}

	@Override
	public boolean addGoodCategory(GoodCategory goodCategory) {
		return sqlSessionTemplate.insert(GoodCategory.class.getCanonicalName()+".create",goodCategory)>0?true:false;
	}
	
	 /**
	   * 分页查询
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<GoodCategory> selectAllByPage(Page<GoodCategory> pager, GoodCategory t) {
			if(t==null){
				t=new GoodCategory();
			}
			List<GoodCategory> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
			pager.setResults(list);
			return pager;
		}

	@Override
	public List<GoodCategory> getAllGoodCategory() {
		return sqlSessionTemplate.selectList(GoodCategory.class.getCanonicalName()+".selectAllGoodCategory");
	}

	@Override
	public boolean checkGoodCategoryStore(String id) {
		int i=sqlSessionTemplate.selectOne(GoodCategory.class.getCanonicalName()+".checkGoodCategoryStore",id);
		return i>0?true:false;
	}


}
