var chart;
var dataProvider = new Array();
var serial;

function createChart() {
    // SERIAL CHART
    chart = new AmCharts.AmSerialChart();

    chart.marginRight = 0;
    chart.autoMarginOffset = 5;
    chart.categoryField = "date";
    chart.startDuration = 0;
    chart.balloon.color = "#000000";


    // SCROLLBAR
    var chartScrollbar = new AmCharts.ChartScrollbar();
    chartScrollbar.graph = graph;
    chartScrollbar.scrollbarHeight = 30;
    chartScrollbar.color = "#FFFFFF";
    chartScrollbar.autoGridCount = true;
    chart.addChartScrollbar(chartScrollbar);

    // listen for "dataUpdated" event (fired when chart is rendered) and call zoomChart method when it happens
    chart.addListener("dataUpdated", zoomChart);

	var catAxis = chart.categoryAxis;
	catAxis.gridCount = dataProvider.length;
	catAxis.labelRotation = 90;

    // AXES
    // category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.fillAlpha = 1;
    categoryAxis.fillColor = "#FAFAFA";
    categoryAxis.gridAlpha = 0;
    categoryAxis.axisAlpha = 0;
    categoryAxis.gridPosition = "start";
    categoryAxis.position = "bottom";

    // GRAPHS
    var graph = new AmCharts.AmGraph();
    graph.title = "Register";
    graph.valueField = "value1";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);
	
    graph = new AmCharts.AmGraph();
    graph.title = "등록거부";
    graph.valueField = "value2";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);

    graph = new AmCharts.AmGraph();
    graph.title = "등록무응답";
    graph.valueField = "value3";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);

    graph = new AmCharts.AmGraph();
    graph.title = "등록인증실패";
    graph.valueField = "value4";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);

    graph = new AmCharts.AmGraph();
    graph.title = "등록완료";
    graph.valueField = "value5";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);

    graph = new AmCharts.AmGraph();
    graph.title = "등록TIMEOUT";
    graph.valueField = "value6";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);

    graph = new AmCharts.AmGraph();
    graph.title = "등록해지";
    graph.valueField = "value7";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);

    graph = new AmCharts.AmGraph();
    graph.title = "동시가입자";
    graph.valueField = "value8";
    graph.hidden = false;
    graph.balloonText = graph.title + " [ [[category]]: [[value]]]";
    graph.lineAlpha = 1;
//    graph.bullet = "round";
    chart.addGraph(graph);




    // CURSOR
    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.cursorPosition = "mouse";

chartCursor.categoryBalloonEnabled = true;
	
    chart.addChartCursor(chartCursor);






    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.markerType = "circle";
    legend.position = "right";
    chart.addLegend(legend);

    // WRITE
    chart.write("chartdiv");
}

// this method is called when chart is first inited as we listen for "dataUpdated" event
function zoomChart() {
    // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
if (dataProvider==null) return;
    chart.zoomToIndexes(dataProvider.length - 40, dataProvider.length - 1);
}

function loadCSV(file) {

    if (window.XMLHttpRequest) {

        // IE7+, Firefox, Chrome, Opera, Safari

        var request = new XMLHttpRequest();

    }

    else {

        // code for IE6, IE5

        var request = new ActiveXObject('Microsoft.XMLHTTP');

    }

    // load

    request.open('GET', file, false);

    request.send();

    parseCSV(request.responseText);

}


function parseCSV(data){ 
	//replace UNIX new lines
	data = data.replace (/\r\n/g, "\n");
	//replace MAC new lines
	data = data.replace (/\r/g, "\n");
	
	//split into rows
	var rows = data.split("\n");
	
	// create array which will hold our data:
	dataProvider = [];
	
	// loop through all rows
	for (var i = 1; i < rows.length; i++){
		// this line helps to skip empty rows


		if (rows[i]) {   
			if (i==0) {
				
			}               
			// our columns are separated by comma
			var column = rows[i].split(",");  
			// column is array now 
			// first item is date
			var date = column[0];
			// second item is value of the second column
			var value1 = column[1];
			var value2 = column[2];
			var value3 = column[3];
			var value4 = column[4];
			var value5 = column[5];
			var value6 = column[6];
			var value7 = column[7];
			var value8 = column[8];
			
				// create object which contains all these items:
				var dataObject = {date:date, value1:value1, value2:value2, value3:value3,
						value4:value4, value5:value5, value6:value6, value7:value7,
								value8:value8};
				// add object to dataProvider array
				dataProvider.push(dataObject);
		}
	}
	chart.dataProvider = dataProvider;
	chart.validateData();
}


