<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bip.vo.UserVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.s-user-border{
		position:relative;
		float:right;
		top:10;
		left:10;
		height:100%;
		width:10%;
		background-position:center 0;
		background-repeat:no-repeat;
		background-attachment:fixed;
		background-size:cover;
		-webkit-background-size:cover;/*Webkit浏览器引擎 */
		-o-background-size:cover;	/*Presto浏览器引擎 */
		
	}
	.s-user-message-border{
		position:relative;
		float:right;
		top:0;
		//left:50%;
		height:75%;
		width:3%;
	}
	.user-name-top{
		font-weight: bold;
  		outline: none;
	}
	.user-register{
		font-weight:bold;
		outline:none;
	}
	.user-login{
		font-weight:bold;
		outline:none;
	}
</style>
<script>
function MM_over(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "block";
	mSubObj.style.backgroundColor = "#a60";
}
function MM_out(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "none";
	
}
</script>
</head>
<body>
	<div>
		<p id="u_sp" class="s-user-border" style="display: block;">
		 <%
		 	UserVO loginvo = (UserVO)request.getAttribute("user_session_key");
		 %>
		 <% if(loginvo!=null){%>
			<div onmouseover="MM_over(this)" onmouseout="MM_out(this)" class="s-user-message-border">
				<a id="username" href="#"><%=loginvo.getUserNickName() %></a>
				<div id="loginout" style="width:40px;height:20px;display:none;position:relative;left:0;top:-5;"><p><a href="login">退出</a></p></div>
			</div>
			<%}else{%>
				<a class="user-register" href="register">注册</a>|<a class="user-login" href="login">登录</a>
			<%} %>
		</p>
	</div>
</body>
</html>