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
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodCategoryService;
import com.yzgs.service.GoodService;




@Controller
@RequestMapping({ "good" })
public class GoodController {
	//@Autowired是用在加载实现service实现类
	//自动帮你把bean里面引用的对象的setter/getter方法省略，它会自动帮你set/get
	@Autowired
	GoodService goodService;
	@Autowired
	GoodCategoryService goodCategoryService;
	
	/**
	 * 取商品管理主界面
	 * 
	 * @return
	 */
	//设置想要跳转的父路径
	@RequestMapping({"toGoodManage"})
	public ModelAndView toWebUserManage(){
		//需要查询所有商品类型
		List<GoodCategory> lists=goodCategoryService.getAllGoodCategory();// List
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//封装数据，用于页面渲染
		modelAndView.addObject("lists", lists);
		//转发到goodManage.jsp文件
		modelAndView.setViewName("admin/goodManage");
		//返回视图和数据
		return modelAndView;
		
	}
	
	/**
	 * 分页查询操作
	 * 
	 * 
	 */
	@RequestMapping({"selectAllGoodByPage"})
	  public  void  selectAllGoodByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  Good Good,@RequestParam int page,@RequestParam int rows
			  ){
		
		//pager实体
		Page<Good> pager = new Page<Good>();
		//params对象，HashMap容器，调用put方法，
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(Good == null){
			Good=new Good();
		}
		
		 params.put("goodName", Good.getGoodName());//键值对，赋值
		 params.put("goodCategoryName", Good.getGoodCategoryName());
	  
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		//赋值
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(Good);
		
		ServiceToActionMsg<Good> Goods = goodService.selectAllGoodByPage(pager);
		if (Goods.getStatusCode()) {
			Response.reSponseJson(resp,Goods.getPage().toString());
		} else {
			Response.reSponseJson(resp,Goods.toJson());
		}

	}
	
	/**
	 * 删除商品
	 * 
	 * @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
	 * @RequestParam用于将请求参数区数据映射到功能处理方法的参数上，传递参数id。
	 * 
	 * @return
	 */
	@RequestMapping({"deleteGood"})
	@ResponseBody
	public Result deleteGood(@RequestParam String id){
		//构造result实体
		 Result result=null;
		 //需要后台校验,库存中还有的商品信息不能删除,
		 if(goodService.checkGoodStore(id)){			 
			 result=new Result("-1", "库存中存在该商品,不能删除");
			
		 }else{
			 //执行数据库删除操作
			 if(goodService.deleteGoodById(id)){
				 
				 result=new Result("1", "删除商品成功");
			 }else{
				 result=new Result("-1", "删除商品失败");
				 
			 }
		 }
		return result;//返回result结果
		
	}
	
	
	/**
	 * 去商品 详情
	 * 
	 * HttpServletRequest对象代表客户端的请求
	 * HttpServletResponse对象代表服务器的响应
	 * @RequestParam是传递参数id
	 * 
	 * @return
	 */
	@RequestMapping({"viewGoodDetail"})
	public ModelAndView viewWebAlbmDetail(HttpServletRequest req,
			  HttpServletResponse resp,@RequestParam String id){
		//good实体
		Good good=null;
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//通过id得到数据库信息
		good=goodService.getGoodById(id);
		//addObject封装数据，用于页面渲染
		modelAndView.addObject("good", good);
		//setViewName转发到goodView.jsp文件
		modelAndView.setViewName("admin/goodView");
		//返回视图和数据
		return modelAndView;
		
	}
	
	
	/**
	 * 取商品新增或者修改界面
	 * 
	 * HttpServletRequest对象代表客户端的请求
	 * HttpServletResponse对象代表服务器的响应
	 * 
	 * @return
	 */
	@RequestMapping({"toGoodAddOrUpdate"})
	public ModelAndView toGoodAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		//需要查询所有商品类型
		List<GoodCategory> lists=goodCategoryService.getAllGoodCategory();
		
		//good实体对象
		Good good=null;
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//获得的就是发过来参数页面的id
		String id=req.getParameter("id");
		//判断id对象是否存在，id对象值是否为空
		if(id!=null&&!id.equals("")){
			//通过id获取到商品信息于good对象
			good=goodService.getGoodById(id);
		}
		//addObject封装数据，用于页面渲染
		modelAndView.addObject("good", good);
		modelAndView.addObject("lists", lists);
		//setViewName转发到goodAddOrUpdate.jsp文件
		modelAndView.setViewName("admin/goodAddOrUpdate");
		//返回视图和数据
		return modelAndView;
		
	}
	
	
	
		/**
		 * 新增或者修改商品  提交操作
		 * 
		 * @Responsebody 后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。
		 * HttpServletRequest对象代表客户端的请求
		 * HttpServletResponse对象代表服务器的响应 
		 * 
		 * @return
		 */		
		@RequestMapping({"GoodAddOrUpdateSubmit"})
		@ResponseBody
		public Result GoodAddOrUpdateSubmit(HttpServletRequest req,
				  HttpServletResponse resp,Good good){
			//根据good对象的id,判断是添加 还是修改 
			
			//result实体对象
			Result result=null;
			//获取商品数据表商品id信息，赋予信息给good_id
			String good_id=good.getId();
			//判断id对象是否存在，id对象值是否为空
			if(good_id==null||good_id.equals("")){
				//自动生成主键
				good_id=UUID.randomUUID().toString();
				//给id赋值good_id
				good.setId(good_id);
				//添加商品信息
				if(goodService.addGood(good)){
					 result=new Result("1", "添加商品成功");
				}else{
					 result=new Result("-1", "添加商品失败");
				};
			}else{
				//修改商品信息
				if(goodService.updateGood(good)){
					 result=new Result("1", "修改商品成功");
				}else{
					result=new Result("-1", "修改商品失败");
				};
			}
			//返回结果
			return result;
		}
	
	/**
	 * 解决前段日期类型传递时,到达不了后台
	 * 
	 * 在需要日期转换的Controller中使用SpringMVC的注解@initbinder和Spring自带的WebDateBinder类来操作
	 * 
	 * @param binder
	 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) { 
		//SimpleDateFormat日期定义，设置日期格式
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    //严格限制时间转换
	    dateFormat.setLenient(false);  
	    //注册一个时间编辑器
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}

}
