<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				<tr>
					<td class="tdbg01" style="width:15%;"> 入库批号：</td>
	                <td class='tdbg02' style="width:35%;">
	                    ${goodStore.storePh}
	                
	                </td>
					<td class="tdbg01" style="width:15%;"> 商品名称：</td>
	                <td class='tdbg02' style="width:35%;">
	                      ${goodStore.goodName}
	                
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"> 商品进价：</td>
	                <td class='tdbg02' style="width:35%;">
	                    ${goodStore.goodJPrice}元
	                
	                </td>
					<td class="tdbg01" style="width:15%;"> 商品数量：</td>
	                <td class='tdbg02' style="width:35%;">
	                     ${goodStore.storeNum}
	                
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"> 商品有效日期：</td>
	                <td class='tdbg02' style="width:35%;">
	                    <fmt:formatDate value="${goodStore.goodValidDate}" pattern="yyyy-MM-dd"/>
	                </td>
					<td class="tdbg01" style="width:15%;"> 折扣系数：</td>
	                <td class='tdbg02' style="width:35%;">
	                     ${goodStore.goodXs}
	                
	                </td>
				</tr>
			
			</table>
		
	</div>
</body>
</html>
