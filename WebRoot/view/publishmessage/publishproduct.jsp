<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		#inputMessage{width: 45%; position:relative; left:10px; height: 45%;overflow: hidden;margin:0;}
		#list-publish-message{width:45%;position:relative;top:10px;left:10px;height:55%;overflow:hidden;margin:0;}
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
	<script type="text/javascript">
	   function setDataGridData(){
		    $('#publishproductsgrid').datagrid({
	            fit:true,
	            pageNumber:1,
	            pageList:[10,20,50],
	            url:'<%=basePath%>someonepublishmessage',
	            nowrap: false,
	            striped: true,
	            collapsible:true,
	            remoteSort: false,
	            columns:[[
	                    {title:'时间',field:'dateTime',width:100},
	                    {title:'描述',field:'description',width:150},
	                    {title:'纬度',field:'latitude',width:50},
	                    {title:'经度',field:'longitude',width:50},
	                    {title:'联系电话',field:'telephone',width:100}
	                ]],
	            pagination:true,
	            singleSelect:true,
	            rownumbers:true
	        });
        
	        var p = $('#publishproductsgrid').datagrid('getPager');  
		    p.pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5, 10, 15],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
		    }); 
	    }
	    $(document).ready(function(){
	    	setDataGridData();
	    });
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
	    	<input type="hidden" id="lat" name="latitude">
    		<input type="hidden" id="lng" name="longitude">
		    <fieldset>
		    	<legend>填写的信息</legend>
		    	<table>
		    		<tr>
		    			<td>请选择活动类型：</td>
		    			<td>
			    			<select name="actiontypeid">
			    					<option value="1">购物</option>
			    					<option value="2">旅游</option>
			    					<option value="3">聚餐</option>
			    			</select>
			    		</td>
		    		</tr>
		    		<tr>
		    			<td>地址：</td>
		    			<td>
		    				<input type="text"  name="location"  id="location" readonly="readonly" placeholder="在地图点击并拖动到想要的位置" class="easyui-validatebox textbox textBox" data-options="required:true"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>开始时间：</td>
		    			<td>
		    				<input name="dateTime" class="easyui-datetimebox" required class="textBox">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>联系电话：</td>
		    			<td><input name="telephone" type="text" placeholder="联系电话" class="easyui-validatebox textbox textBox" data-options="required:true"> </td>
		    		</tr>
		    		<tr>
		    			<td>描述：</td>
		    			<td><textarea name="description" rows="5" cols="10" placeholder="详述一下该信息的其他点呗"  class="easyui-validatebox textbox textBox" data-options="required:true"></textarea> </td>
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
    <div id="list-publish-message">
    	<fieldset>
    		<legend>展示所有发布的信息</legend>
    		<!-- 利用分页进行展示 --><a href="#" class="easyui-linkbutton" onclick="mapMark()">地图标注</a>
			     <table id="publishproductsgrid" class="easyui-datagrid" style="width:500px;height:200px"
			           title="Load Data" iconCls="icon-save"
			           rownumbers="true" pagination="true">
			    </table>
    	</fieldset>
    </div>
  </body>
</html>


<script>
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
		 setValue(e);
		 marker.enableDragging();    //可拖拽
		 marker.addEventListener("dragend", function(e){ 
				setValue(e);//设置显示
		 });
		 //marker click
		 marker.addEventListener("click",function(e){
		 	setValue(e);
		 });
	});
	
	function setValue(e){
		var gc = new BMap.Geocoder(); 
		var pt = e.point;
		gc.getLocation(pt, function(rs){
	        var addComp = rs.addressComponents;
	        //alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
	        document.getElementById("location").value= addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
	    });
	    document.getElementById("lat").value =e.point.lat;
		document.getElementById("lng").value =e.point.lng;
	}
	
	function mapMark(){
			var row = $('#publishproductsgrid').datagrid('getSelected');
			if (row){
				 var point = new BMap.Point(row.longitude,row.latitude);//默认
				 // 创建标注对象并添加到地图  
				 var marker = new BMap.Marker(point);
				 var label = new BMap.Label(row.description,{offset:new BMap.Size(20,-10)});
		 		 marker.setLabel(label);
				 map.addOverlay(marker); 
			}
		}
		
 
	/* function loadScript() {
	   var script = document.createElement("script");
	   script.src = "http://api.map.baidu.com/api?v=2.0&ak=x2MhgDZIHVXhQwWLGhc98Qar";
	   document.body.appendChild(script);
	}
 
	window.onload = loadScript; */
	
</script>
