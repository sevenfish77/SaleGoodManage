package com.yzgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yzgs.domain.Good;
import com.yzgs.domain.MonthGoodGuoQi;
import com.yzgs.domain.MonthGoodMoneyTotal;
import com.yzgs.domain.MonthMoneyTotal;
import com.yzgs.service.GoodService;
import com.yzgs.service.TotalService;



/**
 * 统计分析控制器
 * @author lenovo
 * 下午4:00:15
 */

@Controller
@RequestMapping({ "total" })
public class TotalController {
	
	@Autowired
	TotalService totalService;
	@Autowired
	GoodService goodService;
	
	/**
	 * 去月支出,月收入统计界面
	 * @return
	 */
	@RequestMapping({ "toMonthMoneyTotal" })
	//跳转到统计界面-月支出
	public  ModelAndView  toMonthMoneyTotal(){
		//实例化一个视图
		ModelAndView modelAndView=new ModelAndView();
		//设置视图名
		modelAndView.setViewName("admin/monthMoneyTotal");
		//返回视图
		return modelAndView;
	}

	
	/**
	 * 统计月利润  showMonthMoneyTotal
	 */
	@RequestMapping({ "showMonthMoneyTotal" })
	//跳转到统计界面-月利润
	public  ModelAndView  showMonthMoneyTotal(@RequestParam String startMonth,
			@RequestParam String endMonth){//获取开始月份、结束月份参数
		//创建一个List对象,存储每个月的统计数据
		Integer startMonth_I=Integer.parseInt(startMonth);
	    Integer endMonth_I=Integer.parseInt(endMonth);
		List<String> months=new ArrayList<String>();
		//添加月份
		months.add(startMonth);
		while(startMonth_I<endMonth_I){//当开始月份小于结束月份时一直在while中循环
			startMonth_I=startMonth_I+1;//开始月份+1
			months.add(startMonth_I.toString()); //添加月份
		}
		//当开始月份大于结束月份，不再添加月份，直接进行可视化操作
		List<MonthMoneyTotal> lists= totalService.getMonthMoneyTotal(months);
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("lists", lists);
		modelAndView.setViewName("admin/showMonthMoneyTotal");
		return modelAndView;
	}
	
	
	
	/**
	 * 单件商品月利润统计
	 * @return
	 */
	@RequestMapping({ "toMonthGoodMoneyTotal" })
	public  ModelAndView  toMonthGoodMoneyTotal(){
		//查询所有商品信息
		List<Good> lists=goodService.getAllGood();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("lists", lists);
		modelAndView.setViewName("admin/monthGoodMoneyTotal");
		return modelAndView;
	}
	
	
	/**
	 * 统计单件商品月利润  showMonthGoodMoneyTotal
	 */
	@RequestMapping({ "showMonthGoodMoneyTotal" })
	public  ModelAndView  showMonthGoodMoneyTotal(@RequestParam String startMonth,
			@RequestParam String endMonth,@RequestParam String goodId){
		//创建一个List对象,存储每个月的统计数据
		Integer startMonth_I=Integer.parseInt(startMonth);
	    Integer endMonth_I=Integer.parseInt(endMonth);
		List<String> months=new ArrayList<String>();
		months.add(startMonth);
		while(startMonth_I<endMonth_I){
			startMonth_I=startMonth_I+1;
			months.add(startMonth_I.toString());
		}
		
		List<MonthGoodMoneyTotal> lists= totalService.getMonthGoodMoneyTotal(months,goodId);
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("lists", lists);
		modelAndView.setViewName("admin/showMonthGoodMoneyTotal");
		return modelAndView;
	}
	
	
	/**
	 * 商品过期率统计
	 * 
	 * 过期统计头部页面
	 * @return
	 */
	@RequestMapping({ "toMonthGuoQiTotal" })
	public  ModelAndView  toMonthGuoQiTotal(){
		//需要查询所有商品信息
		List<Good> lists=goodService.getAllGood();
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//封装数据，用于页面渲染
		modelAndView.addObject("lists", lists);
		//转发到monthGuoQiTotal.jsp文件
		modelAndView.setViewName("admin/monthGuoQiTotal");//过期统计头部页面
		//返回视图和数据
		return modelAndView;
	}
	
	
	
	/**
	 * 统计单件月过期率
	 * @RequestParam是传递参数
	 * 
	 * 过期统计列表显示页面
	 */
	@RequestMapping({ "showMonthGoodGuoQiTotal" })
	public  ModelAndView  showMonthGoodGuoQiTotal(@RequestParam String startMonth,
			@RequestParam String endMonth,@RequestParam String goodId){
		
		
		
		//就是将String字符类型数据转换为Integer整型数据
		Integer startMonth_I=Integer.parseInt(startMonth);
	    Integer endMonth_I=Integer.parseInt(endMonth);
	    
	    //创建list对象months，存储月份
		List<String> months=new ArrayList<String>();
		months.add(startMonth);
		
		//while循环
		while(startMonth_I<=endMonth_I){//当开始月份小于结束月份时一直在while中循环
			startMonth_I=startMonth_I+1;//开始月份+1
			months.add(startMonth_I.toString());//添加月份(将Integer整型数据转换为String字符类型数据)
		}
		
		
		//当开始月份大于结束月份，不再添加月份，直接进行可视化操作
		
		
		//创建一个List对象,存储每个月的过期统计数据
		List<MonthGoodGuoQi> lists= totalService.getMonthGoodGuoQi(months,goodId);//根据months，goodId进行三表关联查询
		
		//构造ModelAndView对象
		ModelAndView modelAndView=new ModelAndView();
		//封装数据，用于页面渲染
		modelAndView.addObject("lists", lists);
		//过期统计列表显示页面，showMonthGoodGuoQiTotal.jsp
		modelAndView.setViewName("admin/showMonthGoodGuoQiTotal");
		//返回视图和数据
		return modelAndView;
	}
	
}
