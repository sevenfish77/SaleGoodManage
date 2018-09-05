<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="<%=basePath%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/admin.css">
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
  </head>
  
  <body style="background-color:#f2f9fd;">
		<div class="header bg-main">
		  <div class="logo margin-big-left fadein-top">
		    <h1><img src="<%=basePath%>images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />便利店管理系统后端中心</h1>
		  </div>
		  <div class="head-l" style="margin-left: 300px;"><a class="button button-little bg-red"  onclick="logout()" ><span class="icon-power-off"></span> 退出登录</a> </div>
		</div>
		<div class="leftnav">
		  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
		   <h2><span class="icon-user"></span>业务功能管理</h2>
		  <ul style="display:block">
		     <li><a href="<%=basePath%>saleRecord/toSaleRecordAddOrUpdate" target="right"><span class="icon-caret-right"></span>商品结账</a></li>
		     <li><a href="<%=basePath%>saleRecord/toSaleRecordManage" target="right"><span class="icon-caret-right"></span>便利店销售记录查看</a></li>
		     <li><a href="<%=basePath%>otherProject/toOtherProjectManage" target="right"><span class="icon-caret-right"></span>其他支出项目结算管理</a></li>
		  </ul>
		   <div class="leftnav-title"><strong><span class="icon-list"></span>统计与分析</strong></div>
		   <ul style="display:block">
		    <li><a href="<%=basePath%>total/toMonthMoneyTotal" target="right"><span class="icon-caret-right"></span>月收入、月支出、月盈利统计</a></li>

		    <li><a href="<%=basePath%>total/toMonthGoodMoneyTotal" target="right"><span class="icon-caret-right"></span>商品月利润统计</a></li>
		    <li><a href="<%=basePath%>total/toMonthGuoQiTotal" target="right"><span class="icon-caret-right"></span>商品过期率统计</a></li>
		  </ul>  
		  <h2><span class="icon-user"></span>后台基本管理</h2>
		  <ul style="display:block">
		    <li><a href="<%=basePath%>manage/updateMm" target="right"><span class="icon-caret-right"></span>管理员信息修改</a></li>
		    <li><a href="<%=basePath%>goodCategory/toGoodCategoryManage" target="right"><span class="icon-caret-right"></span>商品类型管理</a></li>
		    <li><a href="<%=basePath%>good/toGoodManage" target="right"><span class="icon-caret-right"></span>商品管理</a></li>
		    <li><a href="<%=basePath%>goodStore/toGoodStoreManage" target="right"><span class="icon-caret-right"></span>库存管理</a></li>
		   
		  </ul>   

		</div>
		<script type="text/javascript">
		$(function(){
		  $(".leftnav h2").click(function(){
			  $(this).next().slideToggle(200);	
			  $(this).toggleClass("on"); 
		  })
		  $(".leftnav ul li a").click(function(){
			    $("#a_leader_txt").text($(this).text());
		  		$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
		  })
		});
		</script>
		
		<div class="admin">
		  <iframe scrolling="auto" src="<%=basePath%>saleRecord/toSaleRecordManage" rameborder="0" id="mainFrame" name="right" width="100%" height="100%"></iframe>
		</div>
		
		</body>
		
		<script type="text/javascript">
		  function logout(){
			  //退出登录
			  window.location.href="<%=basePath%>manage/logout";
			  
			  
			  
		  }
		  
		  
		  //设置一个定时器,隔多长时间去查询后台,得到那些物品,那些批次的货物需要补充
		
		</script>
</html>
