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
import com.yzgs.domain.GoodCategory;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodCategoryService;

@Controller
@RequestMapping({ "goodCategory" })
public class GoodCategoryController {
	@Autowired
	GoodCategoryService goodCategoryService;
	
	/**
	 * 取商品类型管理主界面
	 * @return
	 */

	@RequestMapping({"toGoodCategoryManage"})
	public ModelAndView toWebUserManage(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin/goodCategoryManage");
		return modelAndView;
		
	}
	
	
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllGoodCategoryByPage"})
	  public  void  selectAllGoodCategoryByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  GoodCategory GoodCategory,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<GoodCategory> pager = new Page<GoodCategory>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(GoodCategory == null){
			GoodCategory=new GoodCategory();
		}
		
		 params.put("categoryName", GoodCategory.getCategoryName());
	
	  
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(GoodCategory);
		ServiceToActionMsg<GoodCategory> GoodCategorys = goodCategoryService.selectAllGoodCategoryByPage(pager);
		if (GoodCategorys.getStatusCode()) {
			Response.reSponseJson(resp,GoodCategorys.getPage().toString());
		} else {
			Response.reSponseJson(resp,GoodCategorys.toJson());
		}

	}
	
	/**
	 * 删除商品分类
	 * @return
	 */
	@RequestMapping({"deleteGoodCategory"})
	@ResponseBody
	public Result deleteGoodCategory(@RequestParam String id){
		 Result result=null;
		 //先进行校验,看该分类是否有商品存在库存中
         if(goodCategoryService.checkGoodCategoryStore(id)){
			 
			 result=new Result("-1", "库存中存在该商品分类物品,不能删除");
			
		 }else{
		 
		 
			 if(goodCategoryService.deleteGoodCategoryById(id)){
				 
				 result=new Result("1", "删除商品分类成功");
			 }else{
				 result=new Result("-1", "删除商品分类失败");
				 
			 }
		 }
		return result;
		
	}
	
	/**
	 * 取商品分类新增或者修改界面
	 * @return toGoodCategoryAddOrUpdate
	 */
	@RequestMapping({"toGoodCategoryAddOrUpdate"})
	public ModelAndView toGoodCategoryAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		
		GoodCategory goodCategory=null;
		ModelAndView modelAndView=new ModelAndView();
		String id=req.getParameter("id");
		if(id!=null&&!id.equals("")){
			
			goodCategory=goodCategoryService.getGoodCategoryById(id);
		}
		modelAndView.addObject("goodCategory", goodCategory);
	
		modelAndView.setViewName("admin/goodCategoryAddOrUpdate");
		return modelAndView;
		
	}
	
	
	
	
	
	//新增或者修改商品分类提交操作
	@RequestMapping({"GoodCategoryAddOrUpdateSubmit"})
	@ResponseBody
	public Result GoodCategoryAddOrUpdateSubmit(HttpServletRequest req,
			  HttpServletResponse resp,GoodCategory goodCategory){
		//根据webSinger对象的singer_id,判断是添加 还是修改
		Result result=null;
		String goodCategory_id=goodCategory.getId();
		if(goodCategory_id==null||goodCategory_id.equals("")){
			goodCategory_id=UUID.randomUUID().toString();
			goodCategory.setId(goodCategory_id);
			if(goodCategoryService.addGoodCategory(goodCategory)){
				 result=new Result("1", "添加商品分类成功");
			}else{
				 result=new Result("-1", "添加商品分类失败");
			};
		}else{
			if(goodCategoryService.updateGoodCategory(goodCategory)){
				 result=new Result("1", "修改商品分类成功");
			}else{
				result=new Result("-1", "修改商品分类失败");
			};
		}	
		return result;
	}
	
	
	/**
	 * 查看商品分类详情
	 * @return
	 */
	@RequestMapping({"viewGoodCategory"})
	public ModelAndView viewWebAlbm(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String id){
		
		GoodCategory goodCategory=null;
		ModelAndView modelAndView=new ModelAndView();
		goodCategory=goodCategoryService.getGoodCategoryById(id);
		modelAndView.addObject("goodCategory", goodCategory);
		
		modelAndView.setViewName("admin/goodCategoryView");
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
