<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
  	
    <meta charset="UTF-8">
    <title>新朋友注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  	<link rel="stylesheet" href="css/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/icon.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="js/jquery.min.js"></script>
  	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  </head>
  
  <body class="easyui-layout">
   <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<form action="register/register" method="post">
		   <div>
			  <div>
			    <table>
			    		<tr>
			    			<td>邮箱：</td>
			    			<td  colspan="2"><input type="text" name="userEmail" placeholder="邮箱" maxlength="20" class> </td>
			    		</tr>
			    		<tr>
			    			<td>密码：</td>
			    			<td colspan="2"><input type="text" name="userPassword" placeholder="密码" maxlength="10" class>  </td>
			    		</tr>
			    		<tr>
			    			<td>昵称：</td>
			    			<td  colspan="2"><input type="text" name="userNickName" placeholder="昵称"  maxlength="5" class> </td>
			    		</tr>
			    		<tr>
			    			<td><img src="<%=basePath%>Kaptcha.jpg"></td>
								<td><input type="text" name="captcha" placeholder="验证码" maxlength="4" class> </td>
			    		</tr>
			    		<tr>
			    			<td colspan="3" > 
			    				<input type="reset" value="取消" class="rc-button rc-button-submit">
			    				<input type="submit" value="注册" class="rc-button rc-button-submit">
			    			</td>
			    		</tr>
			    	</table>
			    </div>
		    </div>
	    </form>
	</div>
   
  </body>
</html>
