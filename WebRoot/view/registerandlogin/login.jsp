<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
	<style type="text/css">
    	.register-table{
    		  padding: 5px;
  			  position: relative;
  			  cellpadding:5
    	}
    </style>
    <script>
		function submitForm(){
			$('#login').form('submit');
			var result = $("#userEmail").validatebox('isValid')&&$("#userPassword").validatebox('isValid')&&$("#captcha").validatebox('isValid');
			if(result){
				$('#submitform').click(); //因为上面已经提交了表单这里的的type=submit表单，就不用再次提交表单了。
			}
		}
		function registerUser(){
			//page redirect to register page
			
		}
	</script>
</head>
<body>
	<div style="padding:10px 60px 20px 60px">
		<form id="login" action="login" method="post">
			<table class="register-table">
	    		<tr>
	    			<td>邮箱：</td>
	    			<td  colspan="2"><input type="text" id="userEmail" name="userEmail" placeholder="邮箱" maxlength="20" class="easyui-validatebox textbox" data-options="validType:'email'"> </td>
	    		</tr>
	    		<tr>
	    			<td>密码：</td>
	    			<td colspan="2"><input type="text" id="userPassword" name="userPassword" placeholder="密码" maxlength="10" class="easyui-validatebox textbox" data-options="required:true">  </td>
	    		</tr>
	    		<tr>
	    			<td><img src="<%=basePath%>/Kaptcha.jpg"></td>
					<td><input type="text" id="captcha" name="captcha" placeholder="验证码" maxlength="5" class="easyui-validatebox textbox" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
		    			<td colspan="3" > 
		    				 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>
		    				 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="registerUser()">注册</a>
		    				
		    				<input id="submitform" type="submit" value="提交" style="display:none;">
		    			</td>
		    		</tr>
	    	</table>
		</form>
	</div>
</body>
</html>