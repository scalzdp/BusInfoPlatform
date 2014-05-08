<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>产品发布</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>产品发布</h1>
    <form action="PublishedProducts" method="post">
    	<table>
    		<tr>
    			<td>地址：</td>
    			<td><input name="address" type="text" placeholder="发布消息地址"> </td>
    		</tr>
    		<tr>
    			<td>联系电话：</td>
    			<td><input name="telephone" type="text" placeholder="联系电话"> </td>
    		</tr>
    		<tr>
    			<td>描述：</td>
    			<td><textarea name="description" rows="10" cols="20" placeholder="详述一下该信息的其他点呗"></textarea> </td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
