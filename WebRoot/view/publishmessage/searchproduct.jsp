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
    		if(jQuery("#s_province  option:selected").text()=="省份"){
    			alert("省份还没选择！");
    			return false;
    		}
    		if(jQuery("#s_city  option:selected").text()=="地级市"){
    			alert("地级市还没选择！");
    			return false;
    		}
    		if(jQuery("#s_county  option:selected").text()=="市、县级市"){
    			alert("市、县级市还没选择！");
    			return false;
    		} 
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
	    			<td><select id="actiontypeid" name="actiontypeid">
			    					<option value="0">购物</option>
			    					<option value="1">旅游</option>
			    					<option value="2">聚餐</option>
			    			</select>
			    	</td>
	    			<td>起时间：</td><td><input id="beginDateTime" name="beginDateTime" class="easyui-datetimebox" required class="textBox"></td>
	    			<td>止时间：</td><td><input id="endDateTime" name="endDateTime" class="easyui-datetimebox" required class="textBox"></td>
	    			<td>
	    				<input id="searchDisplay" type="button" value="查一查" onclick="clickToDisplay(this)"/>
	    			</td>
	    		</tr>
	    	</table>
	    	<input type="hidden" id="lngSpan" />
	    	<input type="hidden" id="latSpan" />
	    </fieldset>
    </form>
    <div>
     <fieldset>
	    	<legend>查询结果</legend>
	    	<div id="listDisplay" >列表显示
				<table id="tt" style="width:700px;height:450px"
				   title="DataGrid - CardView" singleSelect="true" fitColumns="true" remoteSort="false"
				         pagination="true" sortOrder="desc">
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
				        	url: '<%=basePath%>someonepublishmessage',
				        	//TODO:这里是ajax请求，在这个请求的时候存放数据提交到后台。
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
	    	
	 </fieldset>
	</div>
  </body>
</html>
