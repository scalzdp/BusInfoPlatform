<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>email 已经被注册了</title>
<script type="text/javascript">
	var timer=null;
	var totalTime=10;
	$(document).ready(function(){
		timedCount();
	});
	
	function timedCount(){
		
		setTimeout("timedCount()",1000);
		totalTime--;
		$("#labMessage").text("还剩下"+totalTime+"秒，页面将跳回登录页面！");
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
<div class="captcha-message">
		<h1>用Email已经被注册了！</h1>
		<div>
			<label id="labMessage"></label>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="returnPage()">如果没及时跳转请点击我来跳转</a>
		</div>
	</div>
</body>
</html>