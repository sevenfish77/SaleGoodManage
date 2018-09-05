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
			<td class="search_head_td head_width15">入库批号：</td>
			<td class="search_content_td content_width15">
				<input type="text" id="query_storePh" name="storePh"   class="search_input"/>
			</td>
			<td class="search_head_td head_width15">商品名称：</td>
			<td class="search_content_td content_width15">
				<select id="query_goodId" name="goodId" style="width:200px;" >
					       <option value="">==请选择商品==</option>
			               <c:forEach items="${lists}" var="good">
			                   <option value="${good.id}">${good.goodName}</option>
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
  <div class="clear">
    <font color="red">已过有效期的库存商品显示红色,正常的显示绿色,快到商品有效期显示黄色(已5天时间为标准,即离商品有效期还有五天,黄色提醒)</font>
  </div>
  <div class="nrBox">
	<div class='m1'>
         <table id="resultDatas" width="100%" height="360px;" ></table>
    </div>
 <!-- 菜单按钮 ,增加,删除,修改 -->
<div id="tb">
<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addGoodStore()">增加</a>

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
		      
				{field:'storePh',title:'入库批号',width:40,align:'center',
					formatter:function(value,row,index){
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.id+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'goodName',title:'商品名称',width:30,align:'center'},
				{field:'goodJPrice',title:'商品进价',width:30,align:'center'},
				{field:'storeNum',title:'商品进货数量',width:50,align:'center'},
				{field:'kcNum',title:'商品剩余数量',width:50,align:'center'},
				{field:'goodValidDate',title:'商品有效日期',width:60,align:'center',formatter:function(value,row,index){
					
					return value.replace(' 00:00:00', '');;
	        	}},
			
				{field:'goodXs',title:'折扣系数',width:50,align:'center'},
				{field:'cz',title:'操作',width:70,align:'center',formatter:function(value,row,index){
					if(row.storePh == undefined||row.storePh=='') return;
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.id+"')><a onclick=viewDetail('"+row.id+"')>详情</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=updateGoodStore('"+row.id+"'))><a onclick=updateGoodStore('"+row.id+"')>修改</a>&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteGoodStore('"+row.id+"'))><a onclick=deleteGoodStore('"+row.id+"')>删除</a>";
					if(row.bs=="2"||row.bs=="1"){
						del=del+"&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=cleanGoodStore('"+row.goodId+"','"+row.kcNum+"','"+row.goodJPrice+"','"+row.storePh+"')><a onclick=cleanGoodStore('"+row.goodId+"','"+row.kcNum+"','"+row.goodJPrice+"','"+row.storePh+"')>清理库存</a>"
					}
					
					return del;
					
					
	        	}
					
				
				}

		    ]],
		    url:"<%=path%>/goodStore/selectAllGoodStoreByPage",
		    rowStyler: function(index,row){
		    	
				if (row.bs=="2"){
					return 'background-color:red';
				}
				if (row.bs=="1"){
					return 'background-color:yellow';
				}
				if (row.bs=="0"){
					return 'background-color:green';
				}
			},

		   
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
			url:"<%=path%>/goodStore/selectAllGoodStoreByPage?time="+new Date().getTime(),
			queryParams: {
				  
				   'storePh':$("#query_storePh").val(),
				    'goodId':$("#query_goodId").val()
				
				 
				   
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
	
	//清理库存功能
	function cleanGoodStore(goodId,goodNum,goodJprice,storePh){
		 $.messager.confirm('确认','您确认想要清理库存吗？',function(r){    
			    if (r){    
			         $.post('<%=path%>/goodStore/cleanGoodStore?goodId='+goodId+'&goodNum='+goodNum+'&goodJprice='+goodJprice+'&storePh='+storePh,
			   		   		{},
			   			function(data){
							if(data.code=="1"){
			                  	$('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '清理库存信息成功', 'info'); 
			                }else{
			                   	$.messager.alert('提醒', data.msg, 'info'); 
			                }
			   		});    
			    }    
			}); 
		
		
		
		
	}
	
	
	function deleteGoodStore(id){
	   
	 
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			         $.post('<%=path%>/goodStore/deleteGoodStore',
			   		   		{"id":id},
			   			function(data){
							if(data.code=="1"){
			                  	$('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '删除入库信息成功', 'info'); 
			                }else{
			                   	$.messager.alert('提醒', 删除入库信息失败, 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}

//添加库存
	function addGoodStore() {
		$("<div id='addGoodStore'/>").dialog({
            title: '新增库存',    
		    width: 700,    
		    height: 300,  
		    href:'<%=path%>/goodStore/toGoodStoreAddOrUpdate', 
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
					  var goodId=$("#goodId").val();
					  var storePh=$("#storePh").textbox('getValue');
					  if(goodId ==""){ 
			    		  $.messager.alert("提示信息","商品不能为空","info");
			    		  return ;
			    	  }

			    	  if(storePh ==""){ 
			    		  $.messager.alert("提示信息","入库批号不能为空","info");
			    		  return ;
			    	  }
	               
	                  // 进行post请求
	                  $.post('<%=path%>/goodStore/GoodStoreAddOrUpdateSubmit',
	                		  $("#goodStoreAdd_frm").serialize(),
	             			function(data){
	                	        
	          				if(data.code=="1"){
	          					 $('#resultDatas').datagrid('reload');
	          					 $("#addGoodStore").dialog('destroy');
	          					 $.messager.alert("提示信息","保存成功","info");
                                  
	                          }else{
	                        	  $.messager.alert("提示信息",data.msg,"info");
	                          }
	             		},'json');
			    	  
					
				}
			},{
				text:'关闭',
				handler:function(){
					$("#addGoodStore").dialog('destroy');
				}
			}]

        });
	}
	
	function updateGoodStore(id) {
		 
				
		$("<div id='updateGoodStore'/>").dialog({
	                title: '修改商品分类信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/goodStore/toGoodStoreAddOrUpdate?id='+id,  
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
							  var goodId=$("#goodId").val();
							  var storePh=$("#storePh").textbox('getValue');
							  if(goodId ==""){ 
					    		  $.messager.alert("提示信息","商品不能为空","info");
					    		  return ;
					    	  }

					    	  if(storePh ==""){ 
					    		  $.messager.alert("提示信息","入库批号不能为空","info");
					    		  return ;
					    	  }
			                 
			                  $.post('<%=path%>/goodStore/GoodStoreAddOrUpdateSubmit',
			                		  $("#goodStoreAdd_frm").serialize(),
			             			function(data){
			                	        
			          				if(data.code=="1"){
			          					 $('#resultDatas').datagrid('reload');
			          					 $("#updateGoodStore").dialog('destroy');
			          					 $.messager.alert("提示信息","修改成功","info");
		                                  
			                          }else{
			                        	  $.messager.alert("提示信息",data.msg,"info");
			                          }
			             		},'json');
					    	  
							
						}
					},{
						text:'关闭',
						handler:function(){
							$("#updateGoodStore").dialog('destroy');
						}
					}]

	            });
	}
	
	
     
		
		  function viewDetail(id){
			  $("<div id='viewGoodStore'/>").dialog({
	               title: '查看入库信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/goodStore/viewGoodStore?id='+id, 
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

