<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'searchproduct.jsp' starting page</title>

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
	<style type="text/css">
		body{ background:#EEEEEE;margin:0; padding:0; font-family:"微软雅黑", Arial, Helvetica, sans-serif; }
		a{ color:#006600; text-decoration:none;}
		a:hover{color:#990000;}
		.top{ margin:5px auto; color:#990000; text-align:center;}
		.info select{ border:1px #993300 solid; background:#FFFFFF;}
		.info{ margin:5px; text-align:center;}
		.info #show{ color:#3399FF; }
		.bottom{ text-align:right; font-size:12px; color:#CCCCCC; width:1000px;}
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;float:left;}
		
	</style>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="js/area.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){ _init_area()});
    	
    	function clickToDisplay(button){
    		//TODO:提交数据
    		submitForm();
    	    if($("#listDisplay").css("display")=="block"){
    	    	button.value="列表显示";
	    		$("#listDisplay").css("display","none");
	    		$("#mapDisplay").css("display","block");
    		}else{
    			button.value="地图显示";
	    		$("#listDisplay").css("display","block");
	    		$("#mapDisplay").css("display","none");
    		}
    	}
    	
    	function submitForm(){
    		$("#search").form("submit");
    	}
    </script>
   
  </head>
  
  <body>
	<form id="search" action="SearchProducts" method="post">
	    <fieldset>
	    	<legend>查询条件</legend>
	    	<table>
	    		<tr>
	    			<td><select id="s_province" name="province"></select></td>
	    			<td><select id="s_city" name="city" ></select></td>
	    			<td><select id="s_county" name="county"></td>
	    			<td><select name="actiontypeid">
			    					<option value="0">购物</option>
			    					<option value="1">旅游</option>
			    					<option value="2">聚餐</option>
			    			</select>
			    	</td>
	    			<td>起时间：</td><td><input name="beginDateTime" class="easyui-datetimebox" required class="textBox"></td>
	    			<td>止时间：</td><td><input name="endDateTime" class="easyui-datetimebox" required class="textBox"></td>
	    			<td><input id="searchDisplay" type="button" value="列表显示" onclick="clickToDisplay(this)"/></td>
	    		</tr>
	    	</table>
	    	<input type="hidden" id="lngSpan" />
	    	<input type="hidden" id="latSpan" />
	    </fieldset>
    </form>
    <div>
     <fieldset>
	    	<legend>查询结果</legend>
	    	<div id="listDisplay">列表显示
				<table id="tt" style="width:700px;height:400px"
				   title="DataGrid - CardView" singleSelect="true" fitColumns="true" remoteSort="false"
				        url="<%=basePath%>someonepublishmessage" pagination="true" sortOrder="desc">
				    <thead>
				        <tr>
				            <th field="dateTime" width="80" sortable="true">时间</th>
				            <th field="description" width="120" sortable="true">描述</th>
				            <th field="latitude" width="80" sortable="true">纬度</th>
				            <th field="longitude" width="250" sortable="true">经度</th>
				            <th field="telephone" width="60" sortable="true">联系电话</th>
				        </tr>
				    </thead>
				</table> 
				<script type="text/javascript">
					var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
				        renderRow: function(target, fields, frozen, rowIndex, rowData){
				            var cc = [];
				            cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px;border:0;">');
				            if (!frozen){
				                //var aa = rowData.itemid.split('-');
				                //var img = 'shirt' + aa[1] + '.gif';
				                var aa = rowData.dateTime;
				                var img = 'loading.gif';
				                cc.push('<img src="css/images/' + img + '" style="width:150px;float:left">');
				                cc.push('<div style="float:left;margin-left:20px;">');
				                for(var i=0; i<fields.length; i++){
				                    var copts = $(target).datagrid('getColumnOption', fields[i]);
				                    cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');
				                }
				                cc.push('</div>');
				            }
				            cc.push('</td>');
				            return cc.join('');
				        }
				    });
				    $(function(){
				        $('#tt').datagrid({
				            view: cardview
				        });
				    });
				</script> 
				<style type="text/css">
				   .c-label{
				       display:inline-block;
				       width:100px;
				       font-weight:bold;
				   }
				</style>  
	    	</div>
	    	
	    	<div id="mapDisplay" style="display:none;width:70%;hight:70%">
	    	地图显示
	    		<div id="allmap"></div>
	    	</div>
	    	
	 </fieldset>
	</div>
  </body>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=x2MhgDZIHVXhQwWLGhc98Qar"></script>
  <script>
	// 百度地图API功能
	var ZoomLevel =15;
	var lat = '${latcenter}';
	var lng = '${lngcenter}';
	var map = new BMap.Map("allmap");            // 创建Map实例
	var point = new BMap.Point(lng, lat);    // 创建点坐标
	map.centerAndZoom(point,ZoomLevel);                     // 初始化地图,设置中心点坐标和地图级别。
	//添加地图平移缩放控件
	map.addControl(new BMap.NavigationControl());

        //显示信息窗口
        function showinfomessage(marker,point,data){
        var infoWindow ;
		     var opts = {  
		          width : 100,     // 信息窗口宽度  
		          height: 60,     // 信息窗口高度    
		      }  ;
		      /*if(data.specificactivity.length>0){
		     	infoWindow = new BMap.InfoWindow(data.specificactivity[0].actiondetails, opts);  // 创建信息窗口对象
		     }else{
		     	infoWindow = new BMap.InfoWindow("此处尚未发现，任何活动迹象", opts);  // 创建信息窗口对象
		     }*/
		     marker.addEventListener("click", function(){ 
		     map.openInfoWindow(infoWindow, point);      // 打开信息窗口 
		    });  
		}   
	
	//向地图上面添加覆盖物
	  function addMarker(point,data){  // 创建图标对象 
		// 创建标注对象并添加到地图 
		 var marker = new BMap.Marker(point, {title:data.location.locationName});  
		 map.addOverlay(marker);  
		 showinfomessage(marker,point,data);
		}
		
		
		// 向地图添加标注  
		var bounds = map.getBounds();   //获取地图的经度和维度的跨度
		 $("#lngSpan").val(bounds.Ad - bounds.Dd);  
		$("#latSpan").val(bounds.Cd - bounds.zd); 
		//当地图拖动的时候获取拖动之后的地图中心点
		map.addEventListener("dragend",function(){
			//var center = map.getCenter(); //获得地图的中心
			var bounds = map.getBounds();   //获取地图的经度和维度的跨度
		});
		
	map.enableScrollWheelZoom();    		 //启用滚轮放大缩小
	var isWheelZoom =true;
	map.addEventListener("zoomend",function(){
		if(isWheelZoom){
			var center = map.getCenter(); //获得地图的中心  
			//根据这个获取范围，然后发起请求查询符合条件的数据然后进行显示。
			bounds = map.getBounds();   //获取地图的经度和维度的跨度
			isWheelZoom=false;
			map.centerAndZoom(center,ZoomLevel);			
		}else{
			isWheelZoom = true;
		}
	});
  </script>
</html>
