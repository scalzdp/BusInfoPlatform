<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到这里</title>
</head>
	<body>
		<h1>欢迎来到这里</h1>
		<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		</div>
		<div style="margin:10px 0;"></div>
		<div class="easyui-accordion" style="width:500px;height:300px;">
			<div title="提示内容更新" data-options="href:'.../message/userpassworderror.jsp'" style="padding:10px">
				
			</div>
			<div title="使用简介" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
				<h3 style="color:#0099FF;">Accordion for jQuery</h3>
				<p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
			</div>
			<div title="获得帮助" data-options="iconCls:'icon-help'" style="padding:10px;">
				<p>The accordion allows you to provide multiple panels and display one at a time. Each panel has built-in support for expanding and collapsing. Clicking on a panel header to expand or collapse that panel body. The panel content can be loaded via ajax by specifying a 'href' property. Users can define a panel to be selected. If it is not specified, then the first panel is taken by default.</p> 		
			</div>
			
		</div>
	</body>
</html>