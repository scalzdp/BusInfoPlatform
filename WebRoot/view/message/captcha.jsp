<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Captcha 错误</title>
<script type="text/javascript">
	var timer=null;
	var totalTime=10;
	$(document).ready(function(){
		timedCount();
	});
	
	function timedCount(){
		
		setTimeout("timedCount()",1000);
		totalTime--;
		$("#labMessage").text("还剩下"+totalTime+"秒，页面将跳回注册页面！");
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
<style type="text/css">
    	.captcha-message{
    		  padding: 5px;
  			  position: relative;
  			  cellpadding:5
    	}
    </style>
</head>
<body>
	<div class="captcha-message">
		<h3>验证码错误!</h3>
		<div>
			<label id="labMessage"></label>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="returnPage()">如果没及时跳转请点击我来跳转</a>
		</div>
	</div>
</body>
</html>