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
			<td class="search_head_td head_width15">销售开始时间：</td>
			<td class="search_content_td content_width15">
				<input type="text"  class="easyui-datebox" id="query_saleDate_min" name="saleDate_min"   class="search_input"/>
			</td>
			<td class="search_head_td head_width15">销售结束时间：</td>
			<td class="search_content_td content_width15">
				<input type="text" class="easyui-datebox" id="query_saleDate_max" name="saleDate_max"   class="search_input"   />
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
<!--  <a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addSaleRecord()">增加</a>-->
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
		      
				{field:'saleDate',title:'销售日期',width:120,align:'center',
					formatter:function(value,row,index){
						//日期截取
						value=value.replace(' 00:00:00', '');
						
						if(value==undefined||value=='') return;
						var del = "<a  onclick=viewDetail('"+row.id+"')>"+value+"</a>";
						return del;
		        	}},
				{field:'storePh',title:'入库编号',width:80,align:'center'},
				{field:'saleNum',title:'销售数量',width:80,align:'center'},
				{field:'salePrice',title:'销售单价',width:50,align:'center'},
				{field:'saleMoney',title:'销售总金额',width:50,align:'center'},
				{field:'cz',title:'操作',width:140,align:'center',formatter:function(value,row,index){
					if(row.storePh == undefined||row.storePh=='') return;
				
					var del = "<img src='<%=path%>/js/easyui/themes/icons/search.png' onclick=viewDetail('"+row.id+"')><a onclick=viewDetail('"+row.id+"')>详情&nbsp<img src='<%=path%>/js/easyui/themes/icons/edit_remove.png' onclick=deleteSaleRecord('"+row.id+"'))><a onclick=deleteSaleRecord('"+row.id+"')>删除</a>";
					return del;

					
	        	}
					
				
				}

		    ]],
		    url:"<%=path%>/saleRecord/selectAllSaleRecordByPage",
		   
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
			url:"<%=path%>/saleRecord/selectAllSaleRecordByPage?time="+new Date().getTime(),
			queryParams: {
				  
				   'saleDate_min':$("#query_saleDate_min").datebox('getValue'),
				   'saleDate_max':$("#query_saleDate_max").datebox('getValue'),
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
	
	
	
	function deleteSaleRecord(id){
	   
	 
		  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			         $.post('<%=path%>/saleRecord/deleteSaleRecord',
			   		   		{"id":id},
			   			function(data){
							if(data.code=="1"){
			                  	$('#resultDatas').datagrid('reload');
			                    $.messager.alert('提醒', '删除销售记录成功', 'info'); 
			                }else{
			                   	$.messager.alert('提醒', 删除销售记录失败, 'info'); 
			                }
			   		});    
			    }    
			}); 
		
	}


	function updateSaleRecord(id) {
		 
				
		$("<div id='updateSaleRecord'/>").dialog({
	                title: '修改消费记录信息',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/saleRecord/toSaleRecordAddOrUpdate?id='+id,  
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
							  var storePh=$("#storePh").textbox('getValue');
							  var saleNum=$("#saleNum").textbox('getValue');
							 
					    	  if(storePh ==""){ 
					    		  $.messager.alert("提示信息","入库编号不能为空","info");
					    		  return ;
					    	  }

					    	  if(saleNum ==""){ 
					    		  $.messager.alert("提示信息","商品数量不能为空","info");
					    		  return ;
					    	  }
			                 
			                  $.post('<%=path%>/saleRecord/SaleRecordAddOrUpdateSubmit',
			                		  $("#saleRecordAdd_frm").serialize(),
			             			function(data){
			                	        
			          				if(data.code=="1"){
			          					 $('#resultDatas').datagrid('reload');
			          					 $("#updateSaleRecord").dialog('destroy');
			          					 $.messager.alert("提示信息","修改成功","info");
		                                  
			                          }else{
			                        	  $.messager.alert("提示信息","修改失败","info");
			                          }
			             		},'json');
					    	  
							
						}
					},{
						text:'关闭',
						handler:function(){
							$("#updateSaleRecord").dialog('destroy');
						}
					}]

	            });
	}
	
	
	
     
		
		  function viewDetail(id){
			  $("<div id='viewSaleRecord'/>").dialog({
	               title: '查看销售记录详情',    
				    width: 700,    
				    height: 300,  
				    href:'<%=path%>/saleRecord/viewSaleRecordDetail?id='+id, 
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

