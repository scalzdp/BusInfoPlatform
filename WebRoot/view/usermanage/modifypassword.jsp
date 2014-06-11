<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'modifypassword.jsp' starting page</title>

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
  
  <body>
  <div>
  	<h2>获取密码总共分为两步</h2>
  	<ul>
  		<li>获取邮箱验证码</li>
  		<li>填写注册邮箱并且填写邮箱验证码，密码将通过邮件发到注册邮箱里面</li>
  	</ul>
  </div>
  <fieldset>
  	<legend>获取邮箱验证码</legend>
	    <form>
	    	<table>
	    		<tr>
	    			<td>注册邮箱：</td>
	    			<td>
	    				<input class="easyui-validatebox textbox" type="text" name="email" data-options="required:true,validType:'email'"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2">
	    				<input type="submit" id="submit" value="邮箱验证码！"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
     </fieldset>
     <hr>
     <fieldset>
     	<legend>填写获取密码</legend>
     	<form>
     	<table>
     		<tr>
	    			<td>注册邮箱：</td>
	    			<td>
	    				<input class="easyui-validatebox textbox" type="text" name="email" data-options="required:true,validType:'email'"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>邮箱验证码：</td>
	    			<td>
	    				<input class="easyui-validatebox textbox" type="text" name="name" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2">
	    				<input type="submit" id="submit" value="获取密码！"/>
	    			</td>
	    		</tr>
	    		</table>
     	</form>
     </fieldset>
  </body>
</html>
