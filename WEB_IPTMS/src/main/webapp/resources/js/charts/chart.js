var charts;

$(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
        });
        drawChart();
        loadInitData("INIT");
    });
        
    //초기 데이터 읽어 옴. 
    function loadInitData(type) {
    	var postString = "type=" +type;
    	$.ajax({
			type: "POST",
			data: postString,
			cache: false,
    		url: "/iptms/ajax/chart" + getRequestSuffix(),
			cache: false,
			success: function(response) {
				success(response); 
			},
			error:function(request,status,error){   
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); 
			}
    	});
    }
    
    var lastReceivedData = "";
    function success(response) {
    	var obj = jQuery.parseJSON(response);
		if (obj[0].success==false) {
			alert("error");
			return ;
		}
		
		var type = obj[0].type;
		
		for (var idx=0; idx<obj[0].data.length; idx++) {
			var tmp = obj[0].data[idx];
			if (lastReceivedData!=tmp.regDateTime) {
				lastReceivedData = tmp.regDateTime;				
				$("#received").append(JSON.stringify(tmp));

				//x축에 시간을 표시한다.
				charts.xAxis[0].categories.push(tmp.regDateTime);
				
				//초기데이터는 하고 이후에는 한다.
				if (type=="INIT") {
					charts.series[0].addPoint(tmp.memPer);
				}
				else {
					charts.series[0].addPoint(tmp.memPer, true, true);
				}
			}
			else {
				//$("#received").append("same" + lastReceivedData + "/" + tmp.regDateTime);
			}
		}
    }
    
    //차트를 그림.. 
    function drawChart() {
    	charts = new Highcharts.Chart({
            chart: {
            	renderTo: container,
                type: 'line',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                	load: function() {
                        setInterval(function() {
                        	loadInitData("CURR");
                        }, 5000);
                    }
                }
            },
            title: {
                text: '192.168.1.121 차트 그래프'
            },
            xAxis: [{
            	
	            	categories: [],
	            	tickmarkPlacement: 'on',
	                tickPixelInterval: 150,
	                tickInterval: 6,
            }],
            yAxis: {
                title: {
                    text: '값'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            legend: {
                enabled: true
            },
            exporting: {
                enabled: false
            },
            series: [{
            	name: "메모리 사용량",
                data: []
            }]            
        });    	
    }
});
