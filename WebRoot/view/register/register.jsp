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
    <style type="text/css">
    	.register-table{
    		  padding: 5px;
  			  position: relative;
  			  cellpadding:5
    	}
    </style>
    <style scoped="scoped">
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
    <script>
		function submitForm(){
			$('#registerMessage').form('submit');
			alert($("#userEmail").validatebox('isValid'));
			$('#submitform').click();
			//$("content").parent.innerHTML;
			$("content").parent.reload(true);
		}
		function clearForm(){
			$('#registerMessage').form('clear');
		}
	</script>
  </head>
  
  <body id="content">
  	<div class="easyui-panel" title="给自己一个响亮名字哈" style="width:600px">
  	 <div style="padding:10px 60px 20px 60px">
		<form id="registerMessage" action="register" method="post" >
	    	<table class="register-table">
	    		<tr>
	    			<td>邮箱：</td>
	    			<td  colspan="2"><input type="text" id="userEmail" name="userEmail" placeholder="邮箱" maxlength="20" class="easyui-validatebox textbox" data-options="validType:'email'"> </td>
	    		</tr>
	    		<tr>
	    			<td>密码：</td>
	    			<td colspan="2"><input type="text" name="userPassword" placeholder="密码" maxlength="10" class="easyui-validatebox textbox" data-options="required:true">  </td>
	    		</tr>
	    		<tr>
	    			<td>昵称：</td>
	    			<td colspan="2"><input type="text" name="userNickName" placeholder="昵称"  maxlength="5" class="easyui-validatebox textbox" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
	    			<td><img src="<%=basePath%>/Kaptcha.jpg"></td>
						<td><input type="text" name="captcha" placeholder="验证码" maxlength="5" class="easyui-validatebox textbox" data-options="required:true"> </td>
	    		</tr>
	    		<tr>
	    			<td colspan="3" > 
	    				 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">注册</a>
	    				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重填写</a>
	    				
	    				<input id="submitform" type="submit" value="提交" style="display:none;">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	  </div>
  	</div>
  </body>
</html>
