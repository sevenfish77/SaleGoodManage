<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理员登录页面</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/easyui.css"></link>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/pintuer.js"></script>
  </head>
  
  <body>
    <div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form  method="post">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>便利店管理系统后台管理中心</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big"   id="account" placeholder="登录账号"  />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" id="password"  placeholder="登录密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input input-big" id="code" placeholder="填写右侧的验证码" />
                           <img src="<%=basePath%>/manage/identifyCode" id="codeimage" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;"  onclick="changeCode();">  
                                                   
                        </div>
                    </div>
                </div>
                <div style="padding:30px;"><input type="button"  onclick="submitForm();" class="button button-block bg-main text-big input-big" value="登录"></div>
            </div>
            </form>          
        </div>
    </div>
</div>
<script type="text/javascript">
   function submitForm(){
	  
   		var  account=$('#account').val();
   		var  password=$('#password').val();
   		var  code=$('#code').val();
   		if(account.replace(/(^\s*)|(\s*$)/, "")==''){
   			$.messager.alert('提醒', '账号不能为空!', 'info'); 
   			return;
   		}
   		if(password.replace(/(^\s*)|(\s*$)/, "")==''){
   			$.messager.alert('提醒', '请输入密码!', 'info'); 
   			return;
   		}
   		if(code.replace(/(^\s*)|(\s*$)/, "")==''){
   			$.messager.alert('提醒', '请输入验证码!', 'info'); 
   			return;
   		}
   		$.post('<%=basePath%>manage/login',
   		   		{"account":account,"password":password,"code":code},
   			    function(data){
				if(data.code=="1"){
					window.location.href="<%=basePath%>manage/home";
                }else{
                	$("#codeimage").attr("src","<%=basePath%>manage/identifyCode?time="+new Date().getTime());
                   	$.messager.alert('提醒', data.msg, 'info'); 
                }
   		},'json');
	}
	
	function keyLogin(){
		 if (event.keyCode==13)  //回车键的键值为13
			 document.getElementById("loginbtn").click();
		}
	
	
	function changeCode(){
		
		$("#codeimage").attr("src","<%=basePath%>manage/identifyCode?time="+new Date().getTime());
		
	}
</script>
  </body>
</html>

