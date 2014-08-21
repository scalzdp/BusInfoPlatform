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
		#message_alert{
			color:red;
			font-size: 3pt;
			width:inherit;
			height:inherit;
		}

	 </style>
  </head>
  
  <body>
    	<div class="pic_uploading">
			<form action="<%=basePath %>fileUpload"  enctype="multipart/form-data" method="post">
				<input type="hidden" name="realactivityID" id="realactivityID" value="${realactivityID}"/>
				<div id="preview">预览</div>
				<div>
					<table>
						<tr>
							<td>
						    	<input type="file" name="imgFile" id="imgFile" onchange="preview(this)" />
						    </td>
						    <td>
						    	<input type="submit" value="保存" />
						    </td>
						    <td>
						   		<font id="message_alert"></font>
				   			</td>
				   		</tr>
				   	</table>
			   	</div>
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
				 
				 
				 function getElementsByClassName (className) {
				    var all = document.all ? document.all : document.getElementsByTagName('*');
				    var elements = new Array();
				    for ( var e = 0; e < all.length; e ++ ) {
				      if (all[e].className == className) {
				        elements[elements.length] = all[e];
				        break ;
				      }
				    }
				    return elements; 
				}
				
				window.onload = function(){
					setAlertMessage();
				 }
				 function setAlertMessage(){
				 	var f = document.getElementById("message_alert");
					f.innerHTML="请点击图片列表";
				 	var his = getElementsByClassName("imgHis");
				 	for(var i=0;i<his.length;i++){
				 		if(his[i].title=='主显示图片'){
				 			f.innerHTML="OK,Profect";
				 		}
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
			
			.box{ width:50px; height:50px; position:relative;}
			.right{display:block;top:0;width:10px; height:10px;position:absolute;
			}
			.right span{position:relative;}
			.right{right:0;}
			.right{ 
				float:right;
				background:url(../css/icons/delete.jpg)
				}
			.left{ z-index:99999;}
		</style>
		<div class="pic_uploaded">
			<% List<PictureVO> pictureVOs = (List<PictureVO>)request.getAttribute("pictureVO");%>
			<% for(PictureVO p:pictureVOs) {
					if(p.getIsMain()==1){ %>
					<div class="box">
						<div><img class="imgHis" title="主显示图片" alt="我已尽力了~!~" onmouseover="this.style.cursor='hand'" name="<%=p.getId() %>" src="/Img/<%=p.getPicMaxPath() %>" onclick="tomoddle(this)"></div>
						<a class="right"  href="#" title="删除我" ></a>
					</div>
					</span>
				<%} else{%>
					<div class="box">
						<div><img class="imgHis" title="点击我成为主显图片" alt="我已尽力了~!~" onmouseover="this.style.cursor='hand'" name="<%=p.getId() %>" src="/Img/<%=p.getPicMaxPath() %>" onclick="tomoddle(this)"></div>
						<a class="right"  href="#" title="删除我" ></a>
					</div>
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
		             	setAlertMessage();
		             }
				});
			}
		</script>
  </body>
</html>
