var charts;
var chartsDiv="";
var xAxis = "";
function initChart(chartsDiv, title, sereis, xAxis, dataLabel, mouseTracking) {
	chartsOptions.chart.renderTo = chartsDiv;
	this.xAxis = xAxis;
	chartsOptions.title.text = title;
	chartsOptions.series = sereis;
	chartsOptions.plotOptions.line.dataLabels.enabled = dataLabel;
	chartsOptions.plotOptions.line.enableMouseTracking = mouseTracking;
	$(document).ready(function() {
		charts = new Highcharts.Chart(chartsOptions);
	});
}
var chartsOptions = {
	chart: {
		renderTo: chartsDiv,
	    type: 'line',
	    animation: false,
	    marginRight: 10,
	    events: {
	    }
	},
	title: {
	    text: ''
	},
    tooltip: {
        shared: true
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: false
            },
            enableMouseTracking: true
        }
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
		name: ''
	}]
};
var lastReceivedData = "";
function setData(response) {
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
			//x축에 시간을 표시한다.
			charts.xAxis[0].categories.push(eval("tmp." + xAxis));
			for (var i=0; i < charts.series.length; i++) {
				if (type=="INIT") {
					charts.series[i].addPoint(eval("tmp." + chartsOptions.series[i].dataColumn));
				}
				else {
					charts.series[i].addPoint(eval("tmp." + chartsOptions.series[i].dataColumn), true, true);
				}
			}
		}
		else {
			//중복된 데이터가 들어 온 경우 뿌려주지 않는다.
		}
	}
}