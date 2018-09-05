<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/demo/demo.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_blue.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_page.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style-search.css"/>
<html>
<head>
</head>
<body>
   <center>
   
     <font color="red" >商品结算中心(输入入库编号,购买数量,自动获取商品单价、折扣系数、应付金额)</font>
   </center>
	<div class="nrBody" style="height:80%;overflow-y:auto;overflow-x:hidden;position:relative;">
	    <button onclick="addSale()">新增记录</button>
		<form action=""  method="post" id="saleRecordAdd_frm">
		     <input type="hidden" name="id" value="${saleRecord.id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="saletable" align="center">
			        <tr align="center">
			           <td width="10%">
			                        消费日期
			           </td>
			           <td width="10%">
			                      入库编号
			           </td>
			           <td width="10%">
			                     购买数量
			           </td>
			            <td width="10%">
			                     商品名称
			           </td>
			           <td width="10%">
			                     商品单价
			           </td>
			           <td width="10%">
			                     商品折扣系数
			           </td>
			            <td width="10%">
			                      应付金额
			           </td>
			            <td width="10%">
			                      操作
			           </td>
			        </tr>
		
					<tr  class="tr-list">
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-datebox" id="saleDate_0" name="saleRecords[0].saleDate" value='${currentDate}' style="width: 90%;"  /></td>
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-textbox" id="storePh_0" name="saleRecords[0].storePh"  style="width: 90%;"  /></td>
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-textbox" id="saleNum_0" name="saleRecords[0].saleNum"  style="width: 90%;"  /></td>
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-textbox" id="name_0" readonly='readonly' style="width: 90%;"  /></td>
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-textbox" id="price_0" readonly='readonly'  style="width: 90%;"  /></td>
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-textbox" id="xs_0"  readonly='readonly' style="width: 90%;"  />
					    <input type="hidden" id="salePrice_0" name="saleRecords[0].salePrice"> 
					   </td>
					   <td class="tdbg01" width="10%"><input  type="text"  class="easyui-textbox"  id="saleMoney_0" name="saleRecords[0].saleMoney" readonly="readonly" style="width: 90%;"  /></td>
                        <td class="tdbg01" width="10%" align="center"><button  type="button" onclick="calMoney(0)" >计算<button></td>
				    </tr>
			        
			        
			        
				
			</table>
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable"  align="center">
			   <tr>
			           
			           <td colspan="8">
			                      金额总计:<span><input  type="text"  class="easyui-textbox"   id="sumJe" readonly="readonly" style="width: 100px;" /></span><button type="button"  onclick="saveSale()">结账</button>
			           </td>
			        </tr>
			</table>
		</form>
	</div>
	
</body>
 <script type="text/javascript">
    var rownum=1;
 
    function addSale(){
    	var html="<tr class='tr-list'>"
      		+" <td class='tdbg01' width='10%'><input  type='text'  class='easyui-datebox' id='saleDate_"+rownum+"' name='saleRecords["+rownum+"].saleDate' value='${currentDate}' style='width: 90%;'  /></td>"
      		+"<td class='tdbg01' width='10%'><input  type='text'  class='easyui-textbox' id='storePh_"+rownum+"' name='saleRecords["+rownum+"].storePh'  style='width: 90%;'  /></td>"
      		+"<td class='tdbg01' width='10%'><input  type='text'  class='easyui-textbox' id='saleNum_"+rownum+"' name='saleRecords["+rownum+"].saleNum'  style='width: 90%;'  /></td>"
      		+" <td class='tdbg01' width='10%'><input  type='text'  class='easyui-textbox' id='name_"+rownum+"'  readonly='readonly'  style='width: 90%;'  /></td>"
      		+" <td class='tdbg01' width='10%'><input  type='text'  class='easyui-textbox' id='price_"+rownum+"' readonly='readonly'  style='width: 90%;'  /></td>"
      		+" <td class='tdbg01' width='10%'><input  type='text'  class='easyui-textbox' id='xs_"+rownum+"' readonly='readonly'  style='width: 90%;'  /></td>"
      		+"<input type='hidden' id='salePrice_"+rownum+"' name='saleRecords[0].salePrice'> "
      		+"<td class='tdbg01' width='10%'><input  type='text'  class='easyui-textbox' id='saleMoney_"+rownum+"' name='saleRecords["+rownum+"].saleMoney'  readonly='readonly'  style='width: 90%;'  /></td>"
            +"<td class='tdbg01' width='10%' align='center'><button type='button' onclick='calMoney("+rownum+")' >计算</button><button type='button' onclick='deleteRecord(this)' >删除</button></td>"
     	 
      		
      	
      		rownum++;
      		
      $("#saletable").append(html);		//在saletable表格下方动态添加新的输入框代码
     
      $.parser.parse();		// 解析整个页面	解析EasyUI组件

    	
    	
    }
    
    function calMoney(xh){
    	 //第一步,获取该xh的输入的入库批号
    	
    	 var storePh=$("#storePh_"+xh).val();
    	 var saleNum=$("#saleNum_"+xh).val();
    	
    	 if(storePh=="" ){
    		 $.messager.alert("提示信息","入库批号不能为空","info");
    		 return;
    	 }
    	 if(saleNum=="" ){
    		 $.messager.alert("提示信息","销售数量不能为空","info");
    		 return;
    	 }
    	
    	 //发送异步请求,获取其他信息（通过入库批号获取商品信息）
    	 $.post('<%=basePath%>saleRecord/getSalePropertiesByStorePh',
    		   		{"storePh":storePh},
    			    function(data){
    		   		
 				       if(data.name==null){
 				    	  $.messager.alert("提示信息","根据批号未找到信息,请检查批号","info");
 				    	   
 				       }else{
 				    	  if(data.bs=="1"){
 				    		 $.messager.alert("提示信息","该批号的商品已经过期","info");
 				    		 return;
 				    		  
 				    		  
 				    	  }
 //库存告罄
 				    	  //进行库存数量判断,判断库存数量是否有这么多
 				    	  if(data.kcNum<saleNum){
 				    		  
 				    		 $.messager.alert("提示信息","库存中该商品数量为:"+data.kcNum+",请减少购买数量","info");
 				    		 return;
 				    		  
 				    	  }
 				    	
						  //将通过入库批号获取商品信息返回前端页面
						  
 				    	  var saleMoney=$("#saleMoney_"+xh).textbox('getValue');	//应付金额
				    	   if(saleMoney==""){
				    		   saleMoney=0;
				    	   }
 				    	   //赋值和计算操作---->给input控件赋值
 				    	   //toFixed() 方法可把 Number 四舍五入为指定小数位数的数字。
 				    	   $("#name_"+xh).textbox('setValue',data.name);				//商品名称
 				    	   $("#salePrice_"+xh).val(data.price*data.xs.toFixed(2));		//商品单价*商品折扣系数（隐藏不显示）
 				    	   $("#price_"+xh).textbox('setValue',data.price);				//商品单价
 				    	  $("#xs_"+xh).textbox('setValue',data.xs);						//商品折扣系数
 				    	   var je=(data.price*saleNum*data.xs).toFixed(2);				//商品单价*购买		
 				    	   $("#saleMoney_"+xh).textbox('setValue',je);					//应付金额
 				    	
 				    	   //累计加总金额
 				    	   var sumJe=$("#sumJe").textbox('getValue');		//金额总计
 				    	   if(sumJe==""){
 				    		   sumJe=0;
 				    	   }
 				    	  //获取这个序号的金额,先减 在加
 				    	  $("#sumJe").textbox('setValue',(Number(sumJe)-Number(saleMoney)+Number(je)).toFixed(2));
 				    	   
							
							
						   //检查商品的库存
				    	   if(data.kcNum-saleNum<data.goodNumXx){
				    		  $.messager.alert("提示信息","此次购买成功后,该商品库存数量低于设定值,请及时进货","info");
				    		   
				    		   
				    	   }
 				       }
    		},'json');
    	 
    	 
    	 
    	
    }
    
    function deleteRecord(obj){
    	
    	 $(obj).parents(".tr-list").remove(); 
    	
    }
    
    function saveSale(){
    	var bs=0;
    	 //先进行校验,查看所有应付金额,只要有一个为空,则不能结账
    	$("[id^=saleMoney_]").each( function () { 
    		
		  var value=$(this).val();
		  //如果为空给bs赋值
		  if(value==""){
			  bs=bs+1;
			  
		  }
		
	   });
		
		//如果应付金额为空
    	if(bs!=0){
    		 $.messager.alert('提醒', '未全部点击计算,不能结账', 'info'); 
    		 return;
    		
    	}
    	
    	//否则，调用控制器里结算的方法--->新增销售记录
    	 $.post('<%=basePath%>saleRecord/SaleRecordAddOrUpdateSubmit',
    			 $("#saleRecordAdd_frm").serialize(),	//通过序列化表单值
 			    function(data){
	    		 if(data.code=="1"){
	    			 $.messager.alert('提醒', data.msg, 'info'); //"结账成功"
	    			 //重新定位到结账页面
	    			//延迟一秒,刷新列表
 					setTimeout("reflash()",1000); 
	             }else{
	             
	                	$.messager.alert('提醒', data.msg, 'info'); 	//"结账失败"
	             }
				      
 		},'json');
    	
    	
    }
  
    //刷新重新进入商品结账界面
    function reflash(){
    
    	   var requestUrl="<%=basePath%>saleRecord/toSaleRecordAddOrUpdate";
			 $("#mainFrame",parent.document).attr("src",requestUrl);
    	
    }
   
 
 </script>

</html>
