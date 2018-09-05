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
					<td class="tdbg01" style="width:15%;"> 支出项目名称：</td>
	                <td class='tdbg02' style="width:35%;">
	                    ${otherProject.projectName}
	                
	                </td>
					<td class="tdbg01" style="width:15%;"> 支出项目金额：</td>
	                <td class='tdbg02' style="width:35%;">
	                      ${otherProject.projectPrice} 元
	                
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"> 支出项目时间：</td>
	                <td class='tdbg02' style="width:35%;">
	                     ${otherProject.projectDate}
	                
	                </td>
					<td class="tdbg01" style="width:15%;"> 支出项目备注：</td>
	                <td class='tdbg02' style="width:35%;">
	                      ${otherProject.projectRemark}
	                
	                </td>
				</tr>
			
			</table>
		
	</div>
</body>
</html>
