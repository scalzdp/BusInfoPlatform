<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- This is vertical page , will show three part of this page : noth,center,south -->
	<link rel="stylesheet" href="css/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/icon.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="js/jquery.min.js"></script>
  	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  	<style type="text/css">
  		.panel-header{
  			  padding: 20px;
  			  position: relative;
  		}
  	</style>
</head>
<body class="easyui-layout">
   <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
		<jsp:include page="template/north.jsp"/> 
	</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">
		<jsp:include page="template/south.jsp"/>
	</div>
	<div data-options="region:'center',title:'Center'">
		<%
		try{ %>
		<!-- 这里的pagename直接存放在model里面使用 -->
			<jsp:include page="${pagename}"  flush="true"></jsp:include>
		<%}
		catch(IOException e){ %>
			<jsp:include page="template/default.jsp"/> 
		<%} %>
	</div>
  </body>
</html>