<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
       function collapseAll(){
           $('#tt').tree('collapseAll');
       }
       function expandAll(){
           $('#tt').tree('expandAll');
       }
       function expandTo(){
           var node = $('#tt').tree('find',113);
           $('#tt').tree('expandTo', node.target).tree('select', node.target);
       }
       function getSelected(){
           var node = $('#tt').tree('getSelected');
           if (node){
               var s = node.text;
               if (node.attributes){
                   s += ","+node.attributes.p1+","+node.attributes.p2;
               }
               alert(s);
           }
       }
   </script>
</head>
<body>
<h2>菜单</h2>
<div style="margin:20px 0;">
        <a href="#" class="easyui-linkbutton" onclick="collapseAll()">CollapseAll</a>
        <a href="#" class="easyui-linkbutton" onclick="expandAll()">ExpandAll</a>
        <a href="#" class="easyui-linkbutton" onclick="expandTo()">ExpandTo</a>
        <a href="#" class="easyui-linkbutton" onclick="getSelected()">GetSelected</a>
    </div>
	<div style="margin:20px 0;"></div>
	<div style="padding:5px">
        <ul id="tt" class="easyui-tree" data-options="url:'json/tree_data1.json',method:'get',animate:true"></ul>
    </div>
</body>
</html>