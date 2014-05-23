<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>所有发布的产品</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
	$(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
    
        var chart;
        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y = Math.random();
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: 'Live random data'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        dataPushLine(data,time,i);
                    }
                    return data;
                })()
            }]
        });
    });
    
});

	function dataPushLine(data,time,i){
		data.push({
	              x: time + i * 1000,
	              y: Math.random()
	          });
	}

	//发起ajax请求,然后将返回的数据进行出来
	function doAjaxBaiduMapHandler(url,dataMessage,typefunction){
			$(function() {
			jQuery.ajax({
				url : url,  //需要加./url 这样表示从根目录进行访问(可以完成访问，不然找不到访问数据)
				contentType : "application/json",//application/xml
				processData : true,//contentType为xml时，些值为false
				dataType : "json",//json--返回json数据类型；xml--返回xml
				data :dataMessage,
				success : function(data) {
					doAjaxSuccessFunction(data,typefunction);
				},
				error : function(e) {
				document.write('error'); //Js访问出错
				}
			});
		});
	}
    </script>
    <script src="js/highcharts.js"></script>
	<script src="js/exporting.js"></script>
</head>
<body>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
