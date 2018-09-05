<%@page contentType="text/html; charset=UTF-8"%>

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
			<td class="search_head_td head_width15">商品类型名称：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_categoryName" name="categoryName"   class="search_input"/>
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
 <!-- 菜单按钮 ,增加,删除,修改 -->
<div id="tb">
<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addGoodCategory()">增加</a>
</div>

 
<script type="text/javascript">
	 
	
	//初始化表格
	$(function(){
	    $('#progressdiv').progressbar('setValue', 0.0);       
	    $('#resultDatas').datagrid({
	        autoRowHeight:true,
	        rownumbers:true,
		    fitColumns:true,
		    pagination:true,
		    singleSelect:false,
		    cache:false,
		    //toolbar : "#optoolbar",
			idField:'id',
			columns:[[
		      
				{field:'categoryBh',title:'商品类型编号',width:120,align:'center',
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.id+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'categoryName',title:'商品类型名称',width:80,align:'center'},
				
				{field:'cz',title:'操作',width:140,align:'center',formatter:function(value,row,index){
					if(row.categoryName == undefined||row.categoryName=='') return;
					
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.id+"')><a onclick=viewDetail('"+row.id+"')>详情</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=updateGoodCategory('"+row.id+"'))><a onclick=updateGoodCategory('"+row.id+"')>修改</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteGoodCategory('"+row.id+"'))><a onclick=deleteGoodCategory('"+row.id+"')>删除</a>";
					return del;

					
	        	}
					
				
				}

		    ]],
		    url:"<%=path%>/goodCategory/selectAllGoodCategoryByPage",
		   
	    	onLoadSuccess:function(data){//清空勾选项,定位第一行
     			$('#resultDatas').datagrid('clearChecked'); 
     			$('#resultDatas').datagrid('scrollTo',0);
     		},
     		toolbar: '#tb'
     		
		});
	 });
    
	 //查询表单   
    function submitForm(){
      
       
		$('#resultDatas').datagrid({
			url:"<%=path%>/goodCategory/selectAllGoodCategoryByPage?time="+new Date().getTime(),
			queryParams: {
				  
				   'categoryName':$("#query_categoryName").val(),
				  
				
				 
				   
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
	
	
	
	function deleteGoodCategory(id){
	   
	 
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			         $.post('<%=path%>/goodCategory/deleteGoodCategory',
			   		   		{"id":id},
			   			function(data){
							if(data.code=="1"){
			                  	$('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '删除商品分类成功', 'info'); 
			                }else{
			                   	$.messager.alert('提醒', data.msg, 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}

	
	

	function addGoodCategory() {
		$("<div id='addGoodCategory'/>").dialog({
            title: '新增商品分类',    
		    width: 700,    
		    height: 300,  
		    href:'<%=path%>/goodCategory/toGoodCategoryAddOrUpdate?id=', 
		    closed: false,    
		    cache: false, 
		    modal: true,
		    onClose:function(){
		    	
		    	$(this).dialog('destroy');
		    },
		    buttons:[{
				text:'保存',
				handler:function(){
					
					// 第一步 进行必填项校验
					  var categoryName=$("#categoryName").textbox('getValue');
					  var categoryBh=$("#categoryBh").textbox('getValue');
					 
			    	  if(categoryName ==""){ 
			    		  $.messager.alert("提示信息","商品分类名称不能为空","info");
			    		  return ;
			    	  }

			    	  if(categoryBh ==""){ 
			    		  $.messager.alert("提示信息","商品分类编号不能为空","info");
			    		  return ;
			    	  }
	
	                  // 进行post请求
	                  $.post('<%=path%>/goodCategory/GoodCategoryAddOrUpdateSubmit',
	                		  $("#goodCategoryAdd_frm").serialize(),
	             			function(data){
	                	        
	          				if(data.code=="1"){
	          					 $('#resultDatas').datagrid('reload');
	          					 $("#addGoodCategory").dialog('destroy');
	          					 $.messager.alert("提示信息","保存成功","info");
                                  
	                          }else{
	                        	  $.messager.alert("提示信息","保存失败","info");
	                          }
	             		},'json');
			    	  
					
				}
			},{
				text:'关闭',
				handler:function(){
					$("#addGoodCategory").dialog('destroy');
				}
			}]

        });
	}
	
	function updateGoodCategory(id) {
		 
				
		$("<div id='updateGoodCategory'/>").dialog({
	                title: '修改商品分类信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/goodCategory/toGoodCategoryAddOrUpdate?id='+id,  
				    closed: false,    
				    cache: false, 
				    modal: true,
				    onClose:function(){
				    	
				    	$(this).dialog('destroy');
				    },
				    buttons:[{
						text:'修改',
						handler:function(){
							// 第一步 进行必填项校验
							  var categoryName=$("#categoryName").textbox('getValue');
							  var categoryBh=$("#categoryBh").textbox('getValue');
							  if(categoryName ==""){ 
					    		  $.messager.alert("提示信息","商品分类名称不能为空","info");
					    		  return ;
					    	  }

					    	  if(categoryBh ==""){ 
					    		  $.messager.alert("提示信息","商品分类编号不能为空","info");
					    		  return ;
					    	  }
			                 
			                  $.post('<%=path%>/goodCategory/GoodCategoryAddOrUpdateSubmit',
			                		  $("#goodCategoryAdd_frm").serialize(),
			             			function(data){
			                	        
			          				if(data.code=="1"){
			          					 $('#resultDatas').datagrid('reload');
			          					 $("#updateGoodCategory").dialog('destroy');
			          					 $.messager.alert("提示信息","修改成功","info");
		                                  
			                          }else{
			                        	  $.messager.alert("提示信息","修改失败","info");
			                          }
			             		},'json');
					    	  
							
						}
					},{
						text:'关闭',
						handler:function(){
							$("#updateGoodCategory").dialog('destroy');
						}
					}]

	            });
	}
	
	
	
     
		
		  function viewDetail(id){
			  $("<div id='viewGoodCategory'/>").dialog({
	               title: '查看商品分类信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/goodCategory/viewGoodCategory?id='+id, 
				    closed: false,    
				    cache: false, 
				    modal: true,
				    onClose:function(){
				    	
				    	$(this).dialog('destroy');
				    }
				});
	     }
		
		
	

</script>
</body>
</html>

