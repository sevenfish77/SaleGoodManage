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
   
	<div class="nrBody" style="height:90%;overflow-y:auto;overflow-x:hidden;position:relative;">
	
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="saletable" align="center">
			        <tr align="center">
			           <td width="20%">
			                        月份
			           </td>
			           <td width="20%">
			                      进货批号
			           </td>
			           <td width="20%">
			                    本批次进货数量
			           </td>
			           <td width="20%">
			                    本批次过期数量
			           </td>
			           <td width="20%">
			                    过期率(%)
			           </td>
			        </tr>
			        <!-- 获取过期统计lists信息，每次取到数据存放于total变量-->
		            <c:forEach items="${lists}" var="total">
		              
		              <tr  align="center" >
					
					
					   <td class="tdbg01" width="20%">${total.month}</td>
					   <td class="tdbg01" width="20%">${total.storePh}</td>
					   <td class="tdbg01" width="20%">${total.storeJhs}</td>
					   <td class="tdbg01" width="20%">${total.storeGqs}</td>
					   <td class="tdbg01" width="20%">${total.storeGql}</td>
					 
				       </tr>
			          
			        </c:forEach>
			           
					
			        
			        
			        
				
			</table>
		</div>
	
</body>
 

</html>
