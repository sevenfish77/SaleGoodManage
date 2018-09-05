package com.yzgs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yzgs.domain.Manage;
import com.yzgs.domain.Result;
import com.yzgs.service.ManageService;
import com.yzgs.util.CodeUtil;
import com.yzgs.util.Md5Util;




/**
 * 管理员控制器
 * @author lenovo
 * 下午7:02:41
 */
@Controller
@RequestMapping({ "manage" })
public class ManageController {
	
	@Autowired
	ManageService manageService;
	
	@RequestMapping({"login"})
	@ResponseBody
	public Result manageLogin(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam String account,@RequestParam String password,@RequestParam String code){
		    System.out.println(account);
		       Result result=null;
		     //第一步,进行验证吗校验
		     if(!req.getSession().getAttribute("indentifyCode").equals(code)){
		    	 //验证码不一致
		    	 result=new Result("-1","验证码错误");
		     }else{
		    	 //根据账号获取Manage实体
		    	 Manage manage=manageService.getManageByAccount(account);
		    	 if(manage==null){
		    		 //账号不存在
		    		 result=new Result("-1","账号不存在");
		    	 }else{
		    		 //进行密码判断
		    		 if(!manage.getPassword().equals(Md5Util.getMD5(password))){
		    			 
		    			 //密码错误
		    			result=new Result("-1","密码错误");
		    		 }else{
		    			 //登录成功,将Manage对象放人session里面
		    			 req.getSession().setAttribute("manage", manage);
		    			 result=new Result("1","登录成功");
		    			 
		    		 } 
		    	 }
		    	 
		     }
		  return result;
		
	}
	/**
	 * 验证码
	 */
	@RequestMapping({"identifyCode"})
	public void identifyCode(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		 CodeUtil.createCode(req, resp);
		
		
	}
	
	/**
	 * 主页面
	 */
	@RequestMapping({"home"})
	public ModelAndView home(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		 ModelAndView modelAndView=new ModelAndView();
		 modelAndView.setViewName("admin/home");
		 return modelAndView;
	}
	
	
	/**
	 * 主页面
	 */
	@RequestMapping({"updateMm"})
	public ModelAndView updateMm(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		 ModelAndView modelAndView=new ModelAndView();
		 modelAndView.addObject("manage", req.getSession().getAttribute("manage"));
		 modelAndView.setViewName("admin/updateMm");
		 return modelAndView;
	}
	
	/**
	 * 修改密码提交控制器,输入参数 原密码,新密码,管理员账号
	 */
	@RequestMapping({"updateMmSubmit"})
	@ResponseBody
	public Result updateMmSubmit(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam String mpass,@RequestParam String newpass,@RequestParam String id) throws Exception{
		 Result result=null; 
		//获取Session里面的Manage对象
		 Manage manage=(Manage) req.getSession().getAttribute("manage");
		 if(manage==null){
			 result=new Result("-1","未登陆,不能修改密码"); 
		 }else{
			 if(Md5Util.getMD5(mpass).equals(manage.getPassword())){
				   //进行密码修改操作
				 Manage manage1=new Manage();
				 manage1.setId(id);
				 manage1.setPassword(Md5Util.getMD5(newpass));
				 if(!manageService.updateManagePass(manage1)){
					 result=new Result("-1","修改密码失败"); 
					 
				 }else{
					 
					 result=new Result("1","修改密码成功"); 
				 }
			 }else{
				 
				 result=new Result("-1","原密码输入错误,请检查"); 
				 
			 }
			 
			 
			 
		 }
		 return result;
	}
	
	/**
	 * 退出登录,清除session里面的值
	 * @param req
	 * @param resp
	 */
	@RequestMapping({"logout"})
	public void logout(HttpServletRequest req,HttpServletResponse resp){
		try{
		    req.getSession().removeAttribute("manage");
		    resp.sendRedirect(req.getContextPath()+"/admin.jsp"); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
