<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bip.vo.PictureVO" %>
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
  	<script type="text/javascript" src="../js/jquery.min.js"></script>
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
			    <input type="file" name="imgFile" id="imgFile" onchange="preview(this)" />
			    <input type="submit" value="保存" /><br>
			   仅提供jpg、jpeg、bmp、gif和png图片
			    <script type="text/javascript">
			    function preview(file)  
				 {  
					 var prevDiv = document.getElementById('preview');
					 if (file.files && file.files[0])  
					 {  
						var reader = new FileReader();  
						reader.onload = function(evt){  
							 prevDiv.innerHTML = '<img class="img" src="' + evt.target.result + '" />';
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
		<style>
			.imgHis{
			 width:50px;  
			 height:50px;
			}
		</style>
		<div class="pic_uploaded">
			<% List<PictureVO> pictureVOs = (List<PictureVO>)request.getAttribute("pictureVO");%>
			<% for(PictureVO p:pictureVOs) {
					if(p.getIsMain()==1){ %>
					<img class="imgHis" title="主显示图片" alt="我已尽力了~!~" onmouseover="this.style.cursor='hand'" name="<%=p.getId() %>" src="<%=basePath %><%=p.getPicMaxPath() %>" onclick="tomoddle(this)">
				<%} else{%>
					<img class="imgHis" title="点击我成为主显图片" alt="我已尽力了~!~" onmouseover="this.style.cursor='hand'" name="<%=p.getId() %>" src="<%=basePath %><%=p.getPicMaxPath() %>" onclick="tomoddle(this)">
			<%}
			} %>
		</div>
		<script>
			function tomoddle(file){
				var prevDiv = document.getElementById('preview');
			 	prevDiv.innerHTML = '<img class="img" src="' + file.src  + '" />';
			 	setMainPic(file.name,$('#realactivityID').val());
			}
			function setMainPic(id,realactivityID){
				$.ajax({
					 type: "POST",
		             url: "<%=basePath %>setMainPic",
		             data: {ID:id,RID:realactivityID},
		             dataType: "json",
		             success: function(data){
		             }
				});
			}
		</script>
  </body>
</html>
