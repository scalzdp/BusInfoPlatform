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
	<link rel="stylesheet" href="css/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/icon.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
  	
  	<script type="text/javascript" src="js/jquery.min.js"></script>
  	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=x2MhgDZIHVXhQwWLGhc98Qar"></script>
	<style type="text/css">
		body, html {width: 100%;height: 100%;overflow: hidden;margin:0;}
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;float:left;}
		#map-field{width: 48%;height: 100%;overflow: hidden;margin:0;float:left;}
		#inputMessage{width: 45%; position:relative; left:10px; height: 100%;overflow: hidden;margin:0;}
		.textBox{
			width:200px;
		}
	</style>
	<script type="text/javascript">
		function submitForm(){
			$('#publishedProducts').form('submit');
		}
		function cancelForm(){
			$('#publishedProducts').form('clear');
		}
	</script>
  </head>
  
  <body>
    <h1>产品发布</h1>
    <fieldset id="map-field">
    	<legend>地图查看</legend>
    	<div id="allmap"></div>
    </fieldset>
    <div id="inputMessage">
	    <form id="publishedProducts" action="PublishedProducts" method="post">
		    <fieldset>
		    	<legend>填写的信息</legend>
		    	<table>
		    		<tr>
		    			<td>地址：</td>
		    			<td><label  id="location" ></label> </td>
		    		</tr>
		    		<tr>
		    			<td>开始时间：</td>
		    			<td>
		    				<input class="easyui-datetimebox" required class="textBox">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>联系电话：</td>
		    			<td><input name="telephone" type="text" placeholder="联系电话" class="easyui-validatebox textbox textBox" data-options="required:true"> </td>
		    		</tr>
		    		<tr>
		    			<td>描述：</td>
		    			<td><textarea name="description" rows="10" cols="20" placeholder="详述一下该信息的其他点呗"  class="easyui-validatebox textbox textBox" data-options="required:true"></textarea> </td>
		    		</tr>
		    		<tr>
		    			<td colspan="2" align="right">
		    				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认发布</a>
			    		 	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelForm()">取消从来</a>
		    			</td>
		    		</tr>
		    	</table>
	    	</fieldset>
	    </form>
    </div>
  </body>
</html>
<script>
	document.getElementById("location").innerHTML="在地图点击拖动，我会自动锁定位置的。";
	var ZoomLevel =15;
	var lat = '${latcenter}';
	var lng = '${lngcenter}';
	var iscreatr=false;
		//---------------------------------------------基础示例---------------------------------------------
		var map = new BMap.Map("allmap",{minZoom:12,maxZoom:20});            // 创建Map实例
		map.centerAndZoom(new BMap.Point(lng,lat),ZoomLevel);  //初始化时，即可设置中心点和地图缩放级别。
		//map.centerAndZoom("成都",13);                     // 初始化地图,设置中心点坐标和地图级别。
		map.enableScrollWheelZoom(true);//鼠标滑动轮子可以滚动
		
		map.addEventListener("click", function(e){
			if(iscreatr==true)return;
			//---------------------------------------------遮盖物---------------------------------------------
			iscreatr=true;
			 var point = new BMap.Point(e.point.lng ,e.point.lat);//默认
			 // 创建标注对象并添加到地图  
			 var marker = new BMap.Marker(point);  
			 var label = new BMap.Label("我是可以拖动的,点击我制动填充",{offset:new BMap.Size(20,-10)});
			 marker.setLabel(label);
			 map.addOverlay(marker);  
			 showValue(e);
			 marker.enableDragging();    //可拖拽
			 marker.addEventListener("dragend", function(e){ 
					//document.getElementById("r-result").innerHTML = e.point.lng + ", " + e.point.lat;//打印拖动结束坐标
					setValue(e);
			 });
			 //marker click
			 marker.addEventListener("click",function(e){
			 	showValue(e);
			 });
		});
		
		function setValue(e){
			var gc = new BMap.Geocoder(); 
			var pt = e.point;
			gc.getLocation(pt, function(rs){
		        var addComp = rs.addressComponents;
		        //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		        document.getElementById("location").innerHTML= addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
		    });
		}
		
		function showValue(e){
			var gcc = new BMap.Geocoder();
			var ptt = e.point;
			gcc.getLocation(ptt,function(rss){
				var message = rss.addressComponents;
				document.getElementById("location").innerHTML= message.province + ", " + message.city + ", " + message.district + ", " + message.street + ", " + message.streetNumber;
			});
		}
		
 
	/* function loadScript() {
	   var script = document.createElement("script");
	   script.src = "http://api.map.baidu.com/api?v=2.0&ak=x2MhgDZIHVXhQwWLGhc98Qar";
	   document.body.appendChild(script);
	}
 
	window.onload = loadScript; */
	
</script>
