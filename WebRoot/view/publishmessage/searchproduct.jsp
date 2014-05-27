<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
	<style type="text/css">
		body{ background:#EEEEEE;margin:0; padding:0; font-family:"微软雅黑", Arial, Helvetica, sans-serif; }
		a{ color:#006600; text-decoration:none;}
		a:hover{color:#990000;}
		.top{ margin:5px auto; color:#990000; text-align:center;}
		.info select{ border:1px #993300 solid; background:#FFFFFF;}
		.info{ margin:5px; text-align:center;}
		.info #show{ color:#3399FF; }
		.bottom{ text-align:right; font-size:12px; color:#CCCCCC; width:1000px;}
	</style>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	 <script type="text/javascript" src="js/area.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){ _init_area()});
    	
    	function clickToDisplay(button){
    		//TODO:提交数据
    		
    	    if($("#listDisplay").css("display")=="block"){
    	    	button.value="地图显示";
	    		$("#listDisplay").css("display","none");
	    		$("#mapDisplay").css("display","block");
    		}else{
    			button.value="列表显示";
	    		$("#listDisplay").css("display","block");
	    		$("#mapDisplay").css("display","none");
    		}
    	}
    </script>
  </head>
  
  <body>
	<form>
	    <fieldset>
	    	<legend>查询条件</legend>
	    	<table>
	    		<tr>
	    			<td><select id="s_province" name="s_province"></select></td>
	    			<td><select id="s_city" name="s_city" ></select></td>
	    			<td><select id="s_county" name="s_county"></td>
	    			<td></td>
	    			<td></td><td></td>
	    			<td></td><td></td>
	    			<td><input type="button" value="列表显示" onclick="clickToDisplay(this)"/></td>
	    		</tr>
	    	</table>
	    </fieldset>
	    <fieldset>
	    	<legend>查询结果</legend>
	    	<div id="listDisplay" >列表显示</div>
	    	<div id="mapDisplay" style="display:none">地图显示</div>
	    </fieldset>
    </form>
  </body>
</html>
