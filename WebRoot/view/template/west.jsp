<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		var convertJson;
		//use jquery read json file
		$.getJSON("<%=basePath%>json/tree_convert.json",function(data){
				convertJson = data;
				//return data[id].action;
			});
			
       function collapseAll(){
           $('#tt').tree('collapseAll');
       }
       function expandAll(){
           $('#tt').tree('expandAll');
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
       function addPanel(){
			index++;
			$('#tab').tabs('add',{
				title: 'Tab'+index,
				content: '<div style="padding:10px">Content'+index+'</div>',
				closable: true
			});
		}
		function removePanel(){
			var tab = $('#tab').tabs('getSelected');
			if (tab){
				var index = $('#tab').tabs('getTabIndex', tab);
				$('#tab').tabs('close', index);
			}
		}
		
		function getAction(id){
				return convertJson[id].action;
				//return data[id].action;
		}
		
		var index = 0;
		
       
       //只有使用ifream方式加载
       $(document).ready(function(){
	       $('#tt').tree({
				onClick: function(node){
					if(node.id>=0){
						//alert(node.id);  // alert node text property when clicked
						addTab(node.text,"<%=basePath%>"+getAction(node.id)+"","");  //this place input request action
						index++;
					}
				}
			});
       });
       
		
	function addTab(title, href,icon){
		var tt = $('#tab');
		if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab    	
	        tt.tabs('select', title);
	        refreshTab({tabTitle:title,url:href});
		} else {
	    	if (href){
		    	var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';
	    	} else {
		    	var content = '未实现';
	    	}
	    	tt.tabs('add',{
		    	title:title,
		    	closable:true,
		    	content:content,
		    	//iconCls:icon||'icon-default'
	    	});
		}
	}
	/**    
	 * 刷新tab
	 * @cfg 
	 *example: {tabTitle:'tabTitle',url:'refreshUrl'}
	 *如果tabTitle为空，则默认刷新当前选中的tab
	 *如果url为空，则默认以原来的url进行reload
	 */
	function refreshTab(cfg){
		var refresh_tab = cfg.tabTitle?$('#tab').tabs('getTab',cfg.tabTitle):$('#tab').tabs('getSelected');
		if(refresh_tab && refresh_tab.find('iframe').length > 0){
			var _refresh_ifram = refresh_tab.find('iframe')[0];
			var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
			//_refresh_ifram.src = refresh_url;
			_refresh_ifram.contentWindow.location.href=refresh_url;
		}
	}
   </script>
</head>
<body>
<h2>菜单</h2>
<div style="margin:20px 0;">
        <a href="#" class="easyui-linkbutton" onclick="collapseAll()">收起</a>
        <a href="#" class="easyui-linkbutton" onclick="expandAll()">展开</a>
    </div>
	<div style="margin:20px 0;"></div>
	<div style="padding:5px">
        <ul id="tt" class="easyui-tree" data-options="url:'json/tree_data1.json',method:'get',animate:true"></ul>
    </div>
</body>
</html>