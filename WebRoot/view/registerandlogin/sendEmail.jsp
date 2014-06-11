<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'sendEmail.jsp' starting page</title>

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
  	<legend>获取邮箱的注册验证号码</legend>
	    <form action="sendEmail" method="post">
	    	<table>
	    		<tr>
	    			<td>请输入你的注册邮箱：</td>
	    			<td>
	    			<input type="text" id="userEmail" name="userEmail" placeholder="邮箱" maxlength="20">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td><img src="<%=basePath%>/Kaptcha.jpg"></td>
					<td><input type="text" id="captcha" name="captcha" placeholder="验证码" maxlength="5" class="easyui-validatebox textbox" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
					<td colspan="2">
						<input id="submit" type="submit" value="发送邮件" >
					</td>
				</tr>
	    	</table>
	    </form>
    </fieldset>
  </body>
</html>
