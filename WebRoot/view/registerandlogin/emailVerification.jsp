<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户邮箱验证页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <fieldset>
    <legend>完成以下步骤验证你的邮箱</legend>
	    <form>
	    	<table>
	    		<tr>
	    			<td>注册邮箱：</td>
	    			<td>
	    				<input type="text" id="userEmail" name="userEmail" placeholder="邮箱" maxlength="20" class="easyui-validatebox textbox" data-options="validType:'email'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>邮箱验证码：</td>
	    			<td>
	    				<input type="text" id="emailVerificationCode" name=""emailVerificationCode" placeholder="验证码"  maxlength="5" class="easyui-validatebox textbox" data-options="required:true">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td><img src="<%=basePath%>/Kaptcha.jpg"></td>
					<td><input type="text" id="captcha" name="captcha" placeholder="验证码" maxlength="5" class="easyui-validatebox textbox" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
	    			<td colspan="2">
	    				<input id="submit" type="submit" value="完成验证">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
    </fieldset>
  </body>
</html>
