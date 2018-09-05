package com.yzgs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.yzgs.domain.Good;
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.OtherProject;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodService;
import com.yzgs.service.OtherProjectService;
import com.yzgs.serviceimpl.OtherProjectServiceImpl;




@Controller
@RequestMapping({ "otherProject" })
public class OtherProjectController {
	
	@Autowired
	OtherProjectService otherProjectService;
	
	/**
	 * 取商品管理主界面
	 * @return
	 */
	@RequestMapping({"toOtherProjectManage"})
	public ModelAndView toOtherProjectManage(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin/otherProjectManage");
		return modelAndView;
		
	}
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllOtherProjectByPage"})
	  public  void  selectAllOtherProjectByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  OtherProject otherProject,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<OtherProject> pager = new Page<OtherProject>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(otherProject == null){
			otherProject=new OtherProject();
		}
		
		 params.put("projectName", otherProject.getProjectName());
		// params.put("goodCategoryName", Good.getGoodCategoryName());
	  
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(otherProject);
		ServiceToActionMsg<OtherProject> otherProjects = otherProjectService.selectAllOtherProjectByPage(pager);
		if (otherProjects.getStatusCode()) {
			Response.reSponseJson(resp,otherProjects.getPage().toString());
		} else {
			Response.reSponseJson(resp,otherProjects.toJson());
		}

	}
	
	/**
	 * 删除其他支出项目
	 * @return
	 */
	@RequestMapping({"deleteOtherProject"})
	@ResponseBody
	public Result deleteOtherProject(@RequestParam String id){
		 Result result=null;
		 if(otherProjectService.deleteOtherProjectById(id)){
			 
			 result=new Result("1", "删除其他支出项目成功");
		 }else{
			 result=new Result("-1", "删除其他支出项目失败");
			 
		 }
		return result;
		
	}
	
	
	/**
	 * 去支出项目信息查看页面
	 * @return
	 */
	@RequestMapping({"viewOtherProjectDetail"})
	public ModelAndView viewOtherProjectDetail(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String id){
		
		OtherProject otherProject=null;
		ModelAndView modelAndView=new ModelAndView();
		otherProject=otherProjectService.getOtherProjectById(id);
		modelAndView.addObject("otherProject", otherProject);
	
		modelAndView.setViewName("admin/otherProjectView");
		return modelAndView;
		
	}
	
	/**
	 * 取其他项目支出新增或者修改界面
	 * @return
	 */
	@RequestMapping({"toOtherProjectAddOrUpdate"})
	public ModelAndView toOtherProjectAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		
		OtherProject otherProject=null;
		ModelAndView modelAndView=new ModelAndView();
		String id=req.getParameter("id");
		if(id!=null&&!id.equals("")){
			
			otherProject=otherProjectService.getOtherProjectById(id);
		}
		modelAndView.addObject("otherProject", otherProject);
	
		modelAndView.setViewName("admin/otherProjectAddOrUpdate");
		return modelAndView;
		
	}
	
	
	
	//新增或者修改商品提交操作
			@RequestMapping({"OtherProjectAddOrUpdateSubmit"})
			@ResponseBody
			public Result OtherProjectAddOrUpdateSubmit(HttpServletRequest req,
					  HttpServletResponse resp,OtherProject otherProject){
				
				Result result=null;
				String otherProject_id=otherProject.getId();
				if(otherProject_id==null||otherProject_id.equals("")){
					otherProject_id=UUID.randomUUID().toString();
					otherProject.setId(otherProject_id);
					if(otherProjectService.addOtherProject(otherProject)){
						 result=new Result("1", "添加其他支出项目信息成功");
					}else{
						 result=new Result("-1", "添加其他支出项目信息失败");
					};
				}else{
					if(otherProjectService.updateOtherProject(otherProject)){
						 result=new Result("1", "修改其他支出项目信息成功");
					}else{
						result=new Result("-1", "修改其他支出项目信息失败");
					};
				}	
				return result;
			}
		
			/**
			 * 去入库详情界面
			 * @return
			 */
			@RequestMapping({"viewOtherProject"})
			public ModelAndView viewWebAlbmDetail(HttpServletRequest req,
					  HttpServletResponse resp,@RequestParam String id){
				//需要查询所有商品类型
			
				OtherProject otherProject=null;
				ModelAndView modelAndView=new ModelAndView();
				otherProject= otherProjectService.getOtherProjectById(id);
				modelAndView.addObject("otherProject", otherProject);
			
				modelAndView.setViewName("admin/otherProjectView");
				return modelAndView;
				
			}
	
	/**
	 * 解决前段日期类型传递时,到达不了后台
	 * @param binder
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
