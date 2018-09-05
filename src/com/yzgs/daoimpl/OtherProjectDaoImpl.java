package com.yzgs.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzgs.dao.OtherProjectDao;
import com.yzgs.domain.OtherProject;
import com.yzgs.domain.Page;
@Repository
public class OtherProjectDaoImpl implements OtherProjectDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public OtherProject getOtherProjectById(String id) {
		
		return sqlSessionTemplate.selectOne(OtherProject.class.getCanonicalName()+".selectByPk",id);
	}

	@Override
	public boolean deleteOtherProjectById(String id) {
		return sqlSessionTemplate.delete(OtherProject.class.getCanonicalName()+".delete",id)>0?true:false;
	}

	@Override
	public boolean updateOtherProject(OtherProject OtherProject) {
		return sqlSessionTemplate.update(OtherProject.class.getCanonicalName()+".update",OtherProject)>0?true:false;
	}

	@Override
	public boolean addOtherProject(OtherProject OtherProject) {
		return sqlSessionTemplate.insert(OtherProject.class.getCanonicalName()+".create",OtherProject)>0?true:false;
	}
	
	 /**
	   * 分页查询
	   * @param pager
	   * @param t
	   * @return
	   */
	  public Page<OtherProject> selectAllByPage(Page<OtherProject> pager, OtherProject t) {
			if(t==null){
				t=new OtherProject();
			}
			List<OtherProject> list = sqlSessionTemplate.selectList(t.getClass().getCanonicalName()+".selectForPage", pager);
			pager.setResults(list);
			return pager;
		}


}
