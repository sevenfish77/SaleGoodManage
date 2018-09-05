package com.yzgs.service;

import org.springframework.stereotype.Service;

import com.yzgs.domain.Manage;


public  interface ManageService {
     /**
      * 通过账号,查询Manage对象service,
      * @param accout
      * @return
      */
     public Manage getManageByAccount(String accout);
     
     /**
      * 更新管理员密码service
      * @param manage
      * @return
      */
     public boolean updateManagePass(Manage manage);

}
