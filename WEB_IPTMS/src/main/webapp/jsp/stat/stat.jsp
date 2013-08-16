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
<script type="text/javascript" src="<c:url value="/resources/js/charts/chart.js"/>"></script>	
</head>
<body>
	###############chart###############
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<br>
	<br>
	###############data###############
	<div id="received" style="min-width: 400px; height: 400px; margin: 0 auto"></div>

</body>
</html>