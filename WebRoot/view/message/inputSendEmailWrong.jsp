<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'inputSendEmailWrong.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
		var timer=null;
		var totalTime=10;
		$(document).ready(function(){
			timedCount();
		});
		
		function timedCount(){
			
			setTimeout("timedCount()",1000);
			totalTime--;
			$("#labMessage").text("还剩下"+totalTime+"秒，页面将跳回验证页面！");
			if(totalTime==0){
				returnPage();
			}
		}
		
		function returnPage(){
			//通过js跳转到注册页面
			if(timer!=null){
				clearTimeout(timer);
			}
			history.back();
		}
	</script>
  </head>
  
  <body>
    <h1>对不起你的邮箱输入错误，请核对后再次输入！</h1>
    <div>
			<label id="labMessage"></label>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="returnPage()">如果没及时跳转请点击我来跳转</a>
		</div>
  </body>
</html>
