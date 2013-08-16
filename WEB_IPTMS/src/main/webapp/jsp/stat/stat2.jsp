<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/common/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/charts/chart2.js"/>"></script>

<script type="text/javascript">
	var series = [
  		{
  			name: "RX",
  			data: [],
  			dataColumn: "trafficRxCnt"
  		},
  		{
  			name: "TX",
  			data: [],
  			dataColumn: "trafficTxCnt"
  		},
  		{
  			name: "메모리",
  			data: [],
  			dataColumn: "memPer"
  		}
  	];
     	

	var series2 = [
		{
			name: "메모리",
			data: [],
			dataColumn: "memPer"
		},
  		{
  			name: "CPU",
  			data: [],
  			dataColumn: "cpuPer"
  		}
  	];
	
 	//차트 데이터 읽어 옴. 
    function loadInitData(type) {
    	var postString = "type=" +type;
    	$.ajax({
    		type: "POST",
    		data: postString,
    		cache: false,
    		url: "/iptms/ajax/chart" + getRequestSuffix(),
    		cache: false,
    		success: function(response) {
    			setData(response); 
    		},
    		error:function(request,status,error){   
    			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); 
    		}
    	});
    } 
    
	loadInitData("INIT");
	//div, chart 이름, 초기데이터, 시리즈, x축 데이터 컬럼, 그래프 값 보임, 마우스 오버 값 보임
	initChart("container", "192.168.1.121 차트 그래프", series, "regDateTime", false, true)
	//initChart("container2", "192.168.1.121 차트 그래프222", series2, "regDateTime", false, true)
	
    setInterval(function() {
    	loadInitData("CURR");
    }, 5000);

	
</script>
	
</head>
<body>
	sf
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	sf
	<br>
	<br>
	sdf
	<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	sdf
</body>
</html>