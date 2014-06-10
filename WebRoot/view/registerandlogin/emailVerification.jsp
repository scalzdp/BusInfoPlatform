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
	<script type="text/javascript">
		function submitVerficationForm(){
			$('#verficationForm').form('submit');
			var result = $("#userEmail").validatebox('isValid')&&$("#emailVerificationCode").validatebox('isValid')&&$("#captcha").validatebox('isValid');
			if(result){
				$('#submit').click(); //因为上面已经提交了表单这里的的type=submit表单，就不用再次提交表单了。
			}
		}
	</script>
  </head>
  
  <body>
    <fieldset>
    <legend>完成以下步骤验证你的邮箱</legend>
	    <form id="verficationForm"  action="verification" method="post">
	    	<table>
	    		<tr>
	    			<td>注册邮箱：</td>
	    			<td>
	    				<input type="text" id="userEmail" name="userEmail" placeholder="邮箱" maxlength="20" value="${userEmail} ">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>邮箱验证码：</td>
	    			<td>
	    				<input type="text" id="emailVerificationCode" name="emailVerificationCode" placeholder="验证码"  maxlength="20" class="easyui-validatebox textbox" data-options="required:true">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td><img src="<%=basePath%>/Kaptcha.jpg"></td>
					<td><input type="text" id="captcha" name="captcha" placeholder="验证码" maxlength="5" class="easyui-validatebox textbox" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
	    			<td colspan="2">
	    				 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitVerficationForm()">完成验证</a>
	    				<input id="submit" type="submit" value="完成验证" style="display:none;">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
    </fieldset>
  </body>
</html>
