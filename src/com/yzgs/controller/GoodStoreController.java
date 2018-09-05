package com.yzgs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.yzgs.domain.GoodStore;
import com.yzgs.domain.Page;
import com.yzgs.domain.Response;
import com.yzgs.domain.Result;
import com.yzgs.domain.SaleProperties;
import com.yzgs.domain.ServiceToActionMsg;
import com.yzgs.service.GoodService;
import com.yzgs.service.GoodStoreService;
import com.yzgs.service.SaleRecordService;

@Controller
@RequestMapping({ "goodStore" })
public class GoodStoreController {
	@Autowired
	GoodStoreService goodStoreService;
	@Autowired
	GoodService goodService;
	

	/**
	 * 取库存管理主界面
	 * @return
	 */
	@RequestMapping({"toGoodStoreManage"})
	public ModelAndView toWebUserManage(){
		List<Good> lists=goodService.getAllGood();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("admin/goodStoreManage");
		modelAndView.addObject("lists", lists);
		return modelAndView;
		
	}
	
	/**
	 * 分页查询操作
	 */
	@RequestMapping({"selectAllGoodStoreByPage"})
	  public  void  selectAllGoodStoreByPage(HttpServletRequest req,
			  HttpServletResponse resp,
			  GoodStore GoodStore,@RequestParam int page,@RequestParam int rows
			  ){
		
		Page<GoodStore> pager = new Page<GoodStore>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(GoodStore == null){
			GoodStore=new GoodStore();
		}
				 params.put("storePh", GoodStore.getStorePh());
		 params.put("goodId", GoodStore.getGoodId());
	  
		pager.setParams(params);
		if(page==0){
			page=1;
		}
		pager.setPageNo(page);
		pager.setPageSize(rows);
		pager.setT(GoodStore);
		ServiceToActionMsg<GoodStore> GoodStores = goodStoreService.selectAllGoodStoreByPage(pager);
		if (GoodStores.getStatusCode()) {
			Response.reSponseJson(resp,GoodStores.getPage().toString());
		} else {
			Response.reSponseJson(resp,GoodStores.toJson());
		}

	}
	
	
	/**
	 * 删除库存
	 * @return
	 */
	@RequestMapping({"deleteGoodStore"})
	@ResponseBody
	public Result deleteGoodStore(@RequestParam String id){
		 Result result=null;
		 if(goodStoreService.deleteGoodStoreById(id)){
			 
			 result=new Result("1", "删除库存成功");
		 }else{
			 result=new Result("-1", "删除库存失败");
			 
		 }
		return result;
		
	}
	
	/**
	 * 清理库存,写过期信息表 cleanGoodStore
	 * ?goodId='+goodId+'&goodNum='+goodNum+'&goodJprice='+goodJprice+'&storePh='+storePh,
	 */
	@RequestMapping({"cleanGoodStore"})
	@ResponseBody
	public Result cleanGoodStore(@RequestParam String goodId,
			@RequestParam String goodNum,
			@RequestParam String goodJprice,
			@RequestParam String storePh
			){
		 Result result=null;
		 if(Integer.parseInt(goodNum)==0){
			 
			 result=new Result("-1", "库存商品为0,不需要清除");
		 }else{
		 
		 Map<String, Object> maps=new HashMap<String, Object>();
		 maps.put("id", UUID.randomUUID().toString());
		 maps.put("goodId", goodId);
		 maps.put("goodNum", goodNum);
		 maps.put("goodJprice", goodJprice);
		 maps.put("storePh", storePh);
		 maps.put("sumMoney",Double.parseDouble(goodJprice)*Integer.parseInt(goodNum));
		 maps.put("goodGuoQiDate", new Date());
		
		 if(goodStoreService.cleanGoodStore(maps)){
			 result=new Result("1", "清理库存成功");
		 }else{
			 result=new Result("-1", "清理库存失败");
			 
		 }
		 }
		return result;
		
	}
	
	
	
	
	/**
	 * 取库存新增或者修改界面
	 * @return
	 */
	@RequestMapping({"toGoodStoreAddOrUpdate"})
	public ModelAndView toGoodStoreAddOrUpdate(HttpServletRequest req,
			  HttpServletResponse resp){
		List<Good> lists=goodService.getAllGood();
		GoodStore goodStore=null;
		ModelAndView modelAndView=new ModelAndView();
		String id=req.getParameter("id");
		if(id!=null){
			//根据singer_id获取WebSinger对象
			goodStore=goodStoreService.getGoodStoreById(id);
		}
		modelAndView.addObject("goodStore", goodStore);
		modelAndView.addObject("lists", lists);
		modelAndView.setViewName("admin/goodStoreAddOrUpdate");
		return modelAndView;
		
	}
	
	
	//新增或者修改商品提交操作
			@RequestMapping({"GoodStoreAddOrUpdateSubmit"})
			@ResponseBody
			public Result GoodStoreAddOrUpdateSubmit(HttpServletRequest req,
					  HttpServletResponse resp,GoodStore goodStore){
				//根据webSinger对象的singer_id,判断是添加 还是修改
				Good g=goodService.getGoodById(goodStore.getGoodId());
		
				Result result=null;
				String goodStore_id=goodStore.getId();
				if(goodStore_id==null||goodStore_id.equals("")){
					goodStore_id=UUID.randomUUID().toString();
					goodStore.setId(goodStore_id);
					goodStore.setGoodSumMoney(goodStore.getStoreNum()*goodStore.getGoodJPrice());
					goodStore.setStoreDate(new Date());
					
//库存提示判断					
					//添加库存,校验商品数量 是否低于 该商品数据表物品数量下限（库存下限信息比较判断）
					if(goodStore.getStoreNum()<g.getStoreMin()){
						
						 result=new Result("-1", "添加物品数量少于商品下限");
						
					}else{
				
					
					
					if(goodStoreService.addGoodStore(goodStore)){
						 result=new Result("1", "添加库存成功");
					}else{
						 result=new Result("-1", "添加库存失败");
					};
					}
				}else{
					if(goodStore.getStoreNum()<g.getStoreMin()){
						
						 result=new Result("-1", "修改物品数量少于商品下限");
						
					}else{
					if(goodStoreService.updateGoodStore(goodStore)){
						 result=new Result("1", "修改库存成功");
					}else{
						result=new Result("-1", "修改库存失败");
					}};
				}	
				return result;
			}
		/**
		 * 去入库信息查看页面
		 * @return
		 */
		@RequestMapping({"viewGoodStore"})
		public ModelAndView viewWebAlbmDetail(HttpServletRequest req,
				  HttpServletResponse resp,@RequestParam String id){
			
			List<Good> lists=goodService.getAllGood();
			GoodStore goodStore=null;
			ModelAndView modelAndView=new ModelAndView();
			goodStore=goodStoreService.getGoodStoreById(id);
			modelAndView.addObject("goodStore", goodStore);
			modelAndView.addObject("lists", lists);
			modelAndView.setViewName("admin/goodStoreView");
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
