<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'usermanage.jsp' starting page</title>

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
    <h1>用户管理</h1>
    <form action="" method="">
    	<table>
    		<tr>
    			<td></td>
    			<td></td>
    		</tr>
    	</table>
    </form>
   <table id="tbList" class="easyui-datagrid" title="统计用户" style="width:800px;height:500px">
       <thead>
           <tr>
               <th data-options="field:'userEmail',width:80">电邮</th>
               <th data-options="field:'userAge',width:80,align:'right'">芳龄</th>
               <th data-options="field:'userNickName',width:80,align:'right'">绰号</th>
               <th data-options="field:'userBrithday',width:60">生日</th>
               <th data-options="field:'frequenedLocation',width:120,align:'center'">经常出没</th>
               <th data-options="field:'hobby',width:120,align:'center'">喜好</th>
           </tr>
       </thead>
<script type="text/javascript">
$('#tbList').datagrid({ 
			pagination: true,
			pageNumber:1,
            pageList:[10,20,50],
            url:'<%=basePath%>alluser',
            nowrap: false,
            striped: true,
            collapsible:true,
            remoteSort: false,
            onLoadSuccess: function (data) {
                if (data.rows.length > 0) {
                    //调用mergeCellsByField()合并单元格
                    mergeCellsByField("tbList", "userEmail");  //只将userEmail数据进行合并
                }
            }
        });

/**
* EasyUI DataGrid根据字段动态合并单元格
* 参数 tableID 要合并table的id
* 参数 colList 要合并的列,用逗号分隔(例如："name,department,office");
*/
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var alertStr = "";
    for (j = ColArray.length - 1; j >= 0; j--) {
        PerTxt = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt == CurTxt) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;
                
                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
                tTable.datagrid("mergeCells", { //根据ColArray[j]进行合并
                    index: i - tmpA,
                    field: "Ideparture",
                    rowspan: tmpA,
                    colspan: null
                });
               
                tmpA = 1;
            }
            PerTxt = CurTxt;
        }
    }
}
</script>
  </body>
</html>
