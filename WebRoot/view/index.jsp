<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>欢迎来到xxx</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" href="css/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/icon.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="js/jquery.min.js"></script>
  	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  	<style type="text/css">
  		.panel-header{
  			  padding: 10px;
  			  position: relative;
  		}
  	</style>

  </head>
  
  <body class="easyui-layout">
   <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
		<jsp:include page="template/north.jsp"/> 
	</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:200px;padding:10px;">
		<jsp:include page="template/west.jsp"/>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">
		<jsp:include page="template/east.jsp"/>
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
