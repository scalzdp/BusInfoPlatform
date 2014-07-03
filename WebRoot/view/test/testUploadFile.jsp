<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'testUploadFile.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">  
	 #preview, .img, img  
	 {  
		 width:200px;  
		 height:200px;  
	 }  
	 #preview  
	 {  
		border:1px solid #000;  
	}  
 </style>
  </head>
  
  <body>
   		
    
    <form action="fileUploadTest" enctype="multipart/form-data" method="post">
    <div id="preview"></div>
	    <input type="file" name="imgFile" id="imgFile" onchange="preview(this)" />
	    <input type="submit" />
    </form>
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
    <img src="./UploadImg/1404352311424/Img/1404352311424_max_aa1.png" />
    <img src="G:/JavaWork/Img/_DSC0275.jpg" />
  </body>
</html>
