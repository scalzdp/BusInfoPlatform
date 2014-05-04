<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>404 - 文件没有找到</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/404/style.css">
	<link rel="stylesheet" href="css/404/blue.css">
	<script src="js/jquery.min.js"></script>
	<script src="js/404Error.js"></script>
  </head>
  
  <body>
    	<div id="error-container">
		<div id="error">
			<div id="pacman"></div>
		</div>		
		<div id="container">
			<div id="title">
				<h1>对不起, 你访问的页面不存在!</h1>
			</div>
			<div id="content">
				<div class="left">
					<p class="no-top">可能是如下原因引起了这个错误:</p>
					<ul>
						<li>URL输入错误</li>
						<li>失效连接</li>
						<li>...</li>
					</ul>
					<p>搜索本站获取您想要的信息！</p>
                    <form action="/search.asp" name="form1">
					<input type="text" placeholder="搜索本站..." /><input type="button" class="search" value="Search" onClick="form1.submit()" />
					</form>
                    <div class="clearfix"></div>
				</div>
				<div class="right">					
					<p class="no-top">你可以通过以下连接继续访问我们的网站：</p>
					<ul class="links">
						<li><a href="#">» 首页</a></li>
						<li><a href="#">» 关于我们</a></li>
						<li><a href="h#">» Sitemap</a></li>
						<li><a href="#">» 联系我们</a></li>
					</ul>
					<!-- <ul class="links">
						<li><a href="http://www.17558.net">» ASP源码</a></li>
						<li><a href="http://www.17558.net">» PHP源码</a></li>
						<li><a href="http://host.17558.net">» 免备案虚拟主机</a></li>
						<li><a href="http://kan.17558.net">» 最新电影下载</a></li>
					</ul>
					 -->
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div id="footer">
				© 2014  All rights reserved.
				<span class="hostorgcn">
					友情链接<a href="#" class="hostorgcn" target="_blank">好东西分享</a> 
				</span>
			</div>
		</div>
	</div>
  </body>
</html>
