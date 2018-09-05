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
		<form action=""  method="post" id="goodStoreAdd_frm">
		     <input type="hidden" name="id" value="${goodStore.id}">
			<table width="100%" cellpadding="0" cellspacing="1" border="0" class="inTable" id="table-inTable" align="center">
				
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>入库批号：</td>
	                <td class='tdbg02' style="width:35%;">
	                <input  type="text"  class="easyui-textbox" id="storePh" name="storePh"  style="width: 90%;"  value="${goodStore.storePh}"/>
	                   
	                
	                </td>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品名称：</td>
	                <td class='tdbg02' style="width:35%;">
	                
	                 <select id="goodId" name="goodId" style="width:200px;" >
					       <option value="">==请选择商品==</option>
			               <c:forEach items="${lists}" var="good">
			                   <option value="${good.id}">${good.goodName}</option>
			               </c:forEach>
			           
			           </select>
	               
	                
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品进价：</td>
	                <td class='tdbg02' style="width:35%;">
	                 <input  type="text"  class="easyui-textbox" id="goodJPrice" name="goodJPrice"  style="width: 90%;"  value="${goodStore.goodJPrice}"/>元
	                    
	                
	                </td>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品数量：</td>
	                <td class='tdbg02' style="width:35%;">
	                 <input  type="text"  class="easyui-textbox" id="storeNum" name="storeNum"  style="width: 90%;"  value="${goodStore.storeNum}"/>
	                    
	                
	                </td>
				</tr>
				<tr>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>商品有效日期：</td>
	                <td class='tdbg02' style="width:35%;">
	                  <input  type="text"  class="easyui-datebox" id="goodValidDate" name="goodValidDate"  style="width: 90%;"  value="<fmt:formatDate value="${goodStore.goodValidDate}" pattern="yyyy-MM-dd"/>"/>
	                    
	                </td>
					<td class="tdbg01" style="width:15%;"><font color="red">*</font>折扣系数：</td>
	                <td class='tdbg02' style="width:35%;">
	                  <input  type="text"  class="easyui-textbox" id="goodXs" name="goodXs"  style="width: 90%;"  value="${goodStore.goodXs}"/>
	                    
	                
	                </td>
				</tr>
				 <script type="text/javascript">
	                
		            var id="${goodStore.goodId}";
		           
		           $("#goodId").val(id);
                 </script>
				
			</table>
		</form>
	</div>
	
</body>


</html>
