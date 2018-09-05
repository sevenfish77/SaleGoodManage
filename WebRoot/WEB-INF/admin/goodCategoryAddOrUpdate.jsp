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
		<form action=""  method="post" id="goodCategoryAdd_frm">
		     <input type="hidden" name="id" value="${goodCategory.id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品分类编号：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="categoryBh" name="categoryBh"  style="width: 90%;"  value="${goodCategory.categoryBh}"/>
	                </td>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品分类名称：</td>
	                <td class='tdbg02' style="width:35%;">
	                	<input  type="text"  class="easyui-textbox" id="categoryName" name="categoryName"  style="width: 90%;"  value="${goodCategory.categoryName}"/>
	                </td>
				</tr>
				
				
				
			</table>
		</form>
	</div>
	
</body>


</html>
