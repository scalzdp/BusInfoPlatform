<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'fileUpload.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  	
	<style type="text/css"> 
	body, html {width: 500px;height: 500px;overflow:hidden; margin:0;} 
		 #preview, .img, img  
		 {  
			 width:300px;  
			 height:300px;  
		 }  
		 #preview  
		 {  
			border:1px solid #000;  
			position:relative;
			float:left;
		} 
		.pic_uploading{
			width: 70%;
			position:relative;
			float:left;
		}
		.pic_uploaded{
		
			width: 20%;
			height:300px;
			position:relative;
			left:-80px;
			float:right;
			border:1px solid #000;
		}
	 </style>
  </head>
  
  <body>
    	<div class="pic_uploading">
			<form action="<%=basePath %>fileUpload"  enctype="multipart/form-data" method="post">
				<input type="hidden" name="realactivityID" id="realactivityID" value="${realactivityID}"/>
				<div id="preview">预览</div>
			    <input type="file" onchange="preview(this)" />
			    <input type="submit" value="保存" />
			    <script type="text/javascript">
			    function preview(file)  
				 {  
					 var prevDiv = document.getElementById('preview');
					 if (file.files && file.files[0])  
					 {  
						var reader = new FileReader();  
						reader.onload = function(evt){  
							 prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
						}    
					 	reader.readAsDataURL(file.files[0]);  
					}  
					 else    
					 {  
					 	prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
					 } 
				 } 
			    </script> 
			</form>
		</div>
		<div class="pic_uploaded">
			已上传的图片
		</div>
  </body>
</html>
