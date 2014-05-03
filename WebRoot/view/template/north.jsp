<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bip.vo.UserVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p id="u_sp" class="s-weather-border" style="display: block;">
	 <%
	 	UserVO loginvo = (UserVO)request.getAttribute("user_session_key");
	 %>
	 <% if(loginvo!=null){%>
		<a class="user-name-top" id="s_username_top" href="http://i.baidu.com"><%=loginvo.getUserNickName() %></a>|<a id="s_i_msg" href="http://i.baidu.com/msg/messages/list/">消息</a>|<a href="/gaoji/preferences.html">搜索设置</a>|<a id="s_tradditional_link" class="last" href="/home/page/show/classic">传统首页</a></p>
		<%}else{%>
			<a class="user-register" href="#">注册</a>|<a class="user-login" href="#">登录</a>
		<%} %>
</body>
</html>