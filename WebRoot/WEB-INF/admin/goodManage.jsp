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
<body >
  <!--查询页面 -->
	<div class="searchinfo">
    	<form action="" id="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#d3dbe0">
		  <tr>
			<td class="search_head_td head_width15">商品名称：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_goodName" name="goodName"   class="search_input"/>
			</td>
			<td class="search_head_td head_width15">商品类型名称：</td>
			<td class="search_content_td content_width15">
			    <select id="query_goodCategoryName" name="goodCategoryName" style="width:200px;" >
					       <option value="">==请选择商品分类==</option>
					       <!-- 获取商品类型信息，每次取到数据存放于goodCategory变量-->
			               <c:forEach items="${lists}" var="goodCategory">
			               		<!-- value值，表单被提交时被发送到服务器的值 -->
			                   <option value="${goodCategory.categoryName}">${goodCategory.categoryName}</option>
			               </c:forEach>
			           
			   </select>
			
			</td>	
           		
		  </tr>
          
		   <tr>
			<td  colspan="6" class="search_button">
				<div class="btns m1">
					<ul>
						<li><input type="button" value="查询" class="select_btn"
							onclick="submitForm()" /></li>
						<li><input type="reset" value="清空" class="reset_btn"
							onclick="clearForm()" /></li>
					</ul>
				</div>
			</td>
		</tr>
		  
		</table>
		
        
        </form>   
	</div>
  <div class="clear"></div>
  <div class="nrBox">
	<div class='m1'>
         <table id="resultDatas" width="100%" height="360px;" ></table>
    </div>
 <!-- 菜单按钮 ,增加 -->
<div id="tb">
<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addGood()">增加</a>
</div>

 
<script type="text/javascript">
	 
	
	//初始化表格
	$(function(){
	    $('#progressdiv').progressbar('setValue', 0.0);   //进度条    
	    $('#resultDatas').datagrid({//表格形式展示数据
	        autoRowHeight:true,
	        rownumbers:true,
		    fitColumns:true,
		    pagination:true,
		    singleSelect:false,
		    cache:false,
		    //toolbar : "#optoolbar",
		    //类似主键
			idField:'id',
			columns:[[
		      
				{field:'goodBh',title:'商品编号',width:120,align:'center',
				//formatter调用公共函数
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.id+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'goodName',title:'商品名称',width:80,align:'center'},
				{field:'goodCategoryName',title:'商品类型名称',width:80,align:'center'},
				{field:'goodPrice',title:'商品单价',width:50,align:'center'},
				{field:'storeMin',title:'库存下限',width:50,align:'center'},
				{field:'cz',title:'操作',width:140,align:'center',formatter:function(value,row,index){
					if(row.goodBh == undefined||row.goodBh=='') return;
//详情，修改，删除	按钮			
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.id+"')><a onclick=viewDetail('"+row.id+"')>详情</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=updateGood('"+row.id+"'))><a onclick=updateGood('"+row.id+"')>修改</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteGood('"+row.id+"'))><a onclick=deleteGood('"+row.id+"')>删除</a>";
					return del;

					
	        	}
					
				
				}

		    ]],
//信息分页查询，显示		//返回json对象    
		    url:"<%=path%>/good/selectAllGoodByPage",
		   
	    	onLoadSuccess:function(data){//清空勾选项,定位第一行
     			$('#resultDatas').datagrid('clearChecked'); //我勾选了这个按钮
     			$('#resultDatas').datagrid('scrollTo',0);
     		},
     		toolbar: '#tb'
     		
		});
	 });
    
//查询表单   
    function submitForm(){
      
       
		$('#resultDatas').datagrid({
		 //根据当前时间，得到表单数据，清理缓存
			url:"<%=path%>/good/selectAllGoodByPage?time="+new Date().getTime(),
			//datagrid动态查询
			
			queryParams: {//queryParams这个属性的作用其实就是在url的请求中添加额外的参数
				  
				   'goodName':$("#query_goodName").val(),
				   'goodCategoryName':$("#query_goodCategoryName").val(),
				  				   
				}
		});
	}

//清空
	function clearForm() {
		$("#frm").form("clear");
		
	}
	$.ajaxSetup ({
		  cache: false //关闭AJAX相应的缓存
	});
	
	
//删除记录	
	function deleteGood(id){
		
	 
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){  
			    	//post请求功能  
			         $.post('<%=path%>/good/deleteGood',
			         		//通过id
			   		   		{"id":id},
			   			function(data){
							if(data.code=="1"){//后台返回值“1”
			                  	$('#resultDatas').datagrid('reload');//表格数据更新
			                    $.messager.alert('提醒', '删除商品成功', 'info'); 
			                }else{
			                   	$.messager.alert('提醒', data.msg, 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}

//增加商品
	function addGood() {
		$("<div id='addGood'/>").dialog({//window弹窗
            title: '新增商品',    
		    width: 700,    
		    height: 300,  
		    href:'<%=path%>/good/toGoodAddOrUpdate', 
		    closed: false,    
		    cache: false, 
		    modal: true,
		    onClose:function(){
		    	
		    	$(this).dialog('destroy');//关闭后销毁
		    },
		    buttons:[{
				text:'保存',
				handler:function(){
					
					// 第一步 进行必填项校验
					  var goodBh=$("#goodBh").textbox('getValue');//获取输入框内容，返回给后台
					  var goodName=$("#goodName").textbox('getValue');
					 
			    	  if(goodName ==""){ 
			    		  $.messager.alert("提示信息","商品名称不能为空","info");
			    		  return ;
			    	  }

			    	  if(goodBh ==""){ 
			    		  $.messager.alert("提示信息","商品编号不能为空","info");
			    		  return ;
			    	  }
	               
	                  // 进行post请求
	                  $.post('<%=path%>/good/GoodAddOrUpdateSubmit',
	                		  $("#goodAdd_frm").serialize(),//将表单里面的值全部序列化之后提交
	             			function(data){
	                	        
	          				if(data.code=="1"){
	          					 $('#resultDatas').datagrid('reload');//表格数据更新
	          					 $("#addGood").dialog('destroy');//保存后销毁
	          					 $.messager.alert("提示信息","保存成功","info");
                                  
	                          }else{
	                        	  $.messager.alert("提示信息","保存失败","info");
	                          }
	             		},'json');
			    	  
					
				}
			},{
				text:'关闭',
				handler:function(){
					$("#addGood").dialog('destroy');//关闭后销毁
				}
			}]

        });
	}
//修改商品	
	function updateGood(id) {
		 
				
		$("<div id='updateGood'/>").dialog({
	                title: '修改商品信息',    
				    width: 700,    
				    height: 300,  
				     //href= 后面是字符串，其中“?id" 是指参数名为"id"，这个参数名为"id"要带上某个值，就用+号连接，后面的变量id就是具体要带的具体值
				    href:'<%=path%>/good/toGoodAddOrUpdate?id='+id,  
				    closed: false,    
				    cache: false, 
				    modal: true,
				    onClose:function(){
				    	
				    	$(this).dialog('destroy');//关闭后销毁
				    },
				    buttons:[{
						text:'修改',
						handler:function(){
							// 第一步 进行必填项校验
							  var goodBh=$("#goodBh").textbox('getValue');
							  var goodName=$("#goodName").textbox('getValue');
							 
					    	  if(goodName ==""){ 
					    		  $.messager.alert("提示信息","商品名称不能为空","info");
					    		  return ;
					    	  }

					    	  if(goodBh ==""){ 
					    		  $.messager.alert("提示信息","商品编号不能为空","info");
					    		  return ;
					    	  }
			                 
			                  $.post('<%=path%>/good/GoodAddOrUpdateSubmit',
			                		  $("#goodAdd_frm").serialize(),//将表单里面的值全部序列化之后提交。
			             			function(data){
			                	        
			          				if(data.code=="1"){//后台返回“1”
			          					 $('#resultDatas').datagrid('reload');//表格数据更新
			          					 $("#updateGood").dialog('destroy');//修改确认后，销毁
			          					 $.messager.alert("提示信息","修改成功","info");
		                                  
			                          }else{
			                        	  $.messager.alert("提示信息","修改失败","info");
			                          }
			             		},'json');
					    	  
							
						}
					},{
						text:'关闭',
						handler:function(){
							$("#updateGood").dialog('destroy');//关闭后销毁
						}
					}]

	            });
	}
	
	
	
     
//商品详情		
		  function viewDetail(id){
			  $("<div id='viewGood'/>").dialog({
	               title: '查看商品信息',    
				    width: 700,    
				    height: 300,  
				     //href= 后面是字符串，其中“?id" 是指参数名为"id"，这个参数名为"id"要带上某个值，就用+号连接，后面的变量id就是具体要带的具体值
				    href:'<%=path%>/good/viewGoodDetail?id='+id, 
				    closed: false,    
				    cache: false, 
				    modal: true,
				    onClose:function(){
				    	
				    	$(this).dialog('destroy');//关闭后销毁
				    }
				});
	     }
		
		
	

</script>
</body>
</html>

