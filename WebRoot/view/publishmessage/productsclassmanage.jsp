<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>产品类型管理</title>

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
      <h1>信息管理</h1>
	<table id="tab1"></table>
    <script type="text/javascript">
  		$(document).ready(function () {
            var EUGrid = $("#tab1"); //datagrid
            EUGrid.datagrid(
                {
                    title: "所有发布的信息",
                    iconCls: "icon-edit",
                    rownumbers: true,
                    singleSelect: true,
                    url: '<%=basePath%>allpublishmessage',
                    columns: [[
                        { field: "realactivityID", title: "活动编号", hidden: true },
                        { field: "dateTime", title: "时间"},
                        { field: "description", title: "描述", editor: "text" },
                        { field: "latitude", title: "纬度"},
                        { field: "longitude", title: "经度"},
                        { field: "telephone", title: "电话", editor: "numberbox" },
                        {
                            field: "Op", title: "操作", formatter: function (value, row, index) {
                                if (row.editing) {
                                    return "<input type=\"button\" onclick=\"save(this," + index + ")\" value=\"Save\"/><input type=\"button\" onclick=\"cancel(this," + index + ")\" value=\"Cancel\"/>"
                                }
                                else {
                                    return "<input type=\"button\" onclick=\"edit(this," + index + ")\" value=\"Edit\"/><input type=\"button\" onclick=\"del(" + index + ")\" value=\"Delete\"/>"
                                }
                            }
                        }
                    ]],
                    pagination: true,
                    pageSize: 20
                });

            getRow = function (index) {
                return EUGrid.datagrid("getRows")[index];
            };
            edit = function (target, index) {
                //小技巧：用cancelEdit来触发单元格的formatter
                getRow(index).editing = true;
                EUGrid.datagrid("beginEdit", index);
                EUGrid.datagrid("cancelEdit", index);
                EUGrid.datagrid("beginEdit", index);
            };
            del = function (index) {
                $.messager.confirm("Confirm", "Are you sure you want to delete record?", function (r) {
                    if (r) {
                        var row = getRow(index);
                        EUGrid.datagrid("reload", { id: row["realactivityID"] });
                        //TODO:在这里发起ajax请求删除相应的数据
                        EUGrid.datagrid("options").queryParams = null;//清空参数
                    }
                });
            };
            save = function (target, index) {
                var telephone, description;
                var editor = EUGrid.datagrid("getEditor", { index: index, field: "telephone" });
                telephone = $(editor.target).val();
                editor = EUGrid.datagrid("getEditor", { index: index, field: "description" });
                description = $(editor.target).val();
                //description = $(editor.target).numberbox("getValue");

                getRow(index).editing = false;//取消编辑状态
                $.messager.confirm("Confirm", "Send data(" + telephone + "," + description + ") to server?", function (r) {
                    if (r) {
                    	var rows = EUGrid.datagrid("getChanges","updated");
                    	 EUGrid.datagrid("endEdit", index);
                		var rowstr = JSON.stringify(rows);
		                jQuery.ajax({
		                	url:'./published/saveedit',
		                	type:'get',
		                	contentType:'application/json',
		                	processData : true,  //contentType为xml时，些值为false
		                	dataType:'json',
		                	data:{postdata1:encodeURI(rowstr)}, //这里的数据无法post获取？？
		                	success:function(data){
		                	
		                	},
		                	error:function(data){
		                	}
		                });
                        EUGrid.datagrid("acceptChanges");
                        //reload
                    }
                    else {
                        EUGrid.datagrid("cancelEdit", index);
                        EUGrid.datagrid("rejectChanges");
                    }
                });
            };
            cancel = function (target, index) {
                getRow(index).editing = false;//取消编辑状态
                EUGrid.datagrid("cancelEdit", index);
            };
        });
        
        function postDataToDB(){
        		
        }
    </script>
  </body>
</html>
