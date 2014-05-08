<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>所有发布的产品</title>

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

  </head>
  
  <body>
     <table id="datagrid" class="easyui-datagrid" style="width:700px;height:250px"
           title="Load Data" iconCls="icon-save"
           rownumbers="true" pagination="true">
    </table>
    <script type="text/javascript">
   	$('#datagrid').datagrid({
            fit:true,
            pageNumber:1,
            pageList:[10,20,50],
            url:'<%=basePath%>paging',
            nowrap: false,
            striped: true,
            collapsible:true,
            remoteSort: false,
            columns:[[
                    {title:'Item ID',field:'UserVO.id',width:300},
                    {title:'Product ID',field:'UserVO.userEmail',width:150},
                    {title:'List Price',field:'UserVO.userPassword',width:150},
                    {title:'Unit Cost',field:'UserVO.userNickName',width:150},
                    {title:'Attribute',field:'UserVO.captcha',width:150}
                ]],
            pagination:true,
            singleSelect:true,
            rownumbers:true
        });
        
        
        var p = $('#datagrid').datagrid('getPager');  
	    p.pagination({  
	        pageSize: 5,//每页显示的记录条数，默认为10  
	        pageList: [5, 10, 15],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    }); 
    </script>
  </body>
</html>
