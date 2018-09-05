<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="<%=basePath%>/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>/css/admin.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyui.css"></link>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
  
  </head>
  
  <body>
		<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改管理员密码</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
      <div class="form-group">
        <div class="label">
          <label for="sitename">管理员帐号：</label>
        </div>
        <div class="field">
          <label style="line-height:33px;">
              ${manage.name}
          </label>
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">原始密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" id="mpass"  size="50" placeholder="请输入原始密码" />       
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">新密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" id="newpass"  size="50" placeholder="请输入新密码"  />         
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">确认新密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" id="renewpass" size="50" placeholder="请再次输入新密码"  />          
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="updateMM();"> 提交</button>   
        </div>
      </div>      
    </form>
  </div>
</div>
<script type="text/javascript">
       var id=${manage.id};
       function updateMM(){
    	    var  mpass=$('#mpass').val();
      		var  newpass=$('#newpass').val();
      		var  renewpass=$('#renewpass').val();
      		if(mpass.replace(/(^\s*)|(\s*$)/, "")==''){
      			$.messager.alert('提醒', '原始密码不能为空!', 'info'); 
      			return;
      		}
      		if(newpass.replace(/(^\s*)|(\s*$)/, "")==''){
      			$.messager.alert('提醒', '新密码不能为空!', 'info'); 
      			return;
      		}
      		if(renewpass.replace(/(^\s*)|(\s*$)/, "")==''){
      			$.messager.alert('提醒', '重复密码不能为空!', 'info'); 
      			return;
      		}
      		if(renewpass!=newpass){
      			$.messager.alert('提醒', '两次密码输入不一致', 'info'); 
      			return;
      		}
      		$.post('<%=basePath%>manage/updateMmSubmit',
       		   		{"mpass":mpass,"newpass":newpass,"id":id},
       			    function(data){
    				if(data.code=="1"){
    					$.messager.alert('提醒', data.msg, 'info');
    					//调用退出登录逻辑
    					parent.window.location.href="<%=basePath%>manage/logout";
                    }else{
                    	
                       	$.messager.alert('提醒', data.msg, 'info'); 
                    }
       		},'json');
    	   
    	   
       }


</script>
</body>
</html>
