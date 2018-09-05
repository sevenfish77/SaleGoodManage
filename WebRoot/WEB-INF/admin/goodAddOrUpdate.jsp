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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_blue.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layout_page.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style-search.css"/>
<html>
<head>
</head>
<body>
	<div class="nrBody" style="height:200px;overflow-y:auto;overflow-x:hidden;position:relative;">
		<form action=""  method="post" id="goodAdd_frm">
		     <input type="hidden" name="id" value="${good.id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品分类</td>
	                <td class='tdbg02' style="width:35%;">
	                   <select id="goodCategoryId" name="goodCategoryId" style="width:200px;" >
					       <option value="">==请选择商品分类==</option>
					       <!-- 商品类型信息，下拉选择 -->
			               <c:forEach items="${lists}" var="goodCategory1">
			               		<!-- value值，表单被提交时被发送到服务器的值 -->
			                   <option value="${goodCategory1.id}">${goodCategory1.categoryName}</option>
			               </c:forEach>
			           
			           </select>
	                  
	                </td>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品名称：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="goodName" name="goodName"  style="width: 90%;"  value="${good.goodName}"/>
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品编号：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="goodBh" name="goodBh"  style="width: 90%;"  value="${good.goodBh}"/>
	                </td>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品单价：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="goodPrice" name="goodPrice"  style="width: 90%;"  value="${good.goodPrice}"/>
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>库存下限：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="storeMin" name="storeMin"  style="width: 90%;"  value="${good.storeMin}"/>
	                </td>
					<td class="tdbg01" style="width:15%;"></td>
	                <td class='tdbg02' style="width:35%;">
	                	
	                </td>
				</tr>
			
				
				
				
			</table>
		</form>
	</div>
	 <script type="text/javascript">
	                
		            var id="${good.goodCategoryId}";//赋值
		           
		           $("#goodCategoryId").val(id);//复选框点击的时候，获得下拉值id
    </script>
</body>


</html>
