<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/common/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery-2.0.3.min.js"/>"></script>
<script type="text/javascript">

$(document).ready(function() {
	$("#insert").click ( function () {
		alert("a");	
		$("#form1").submit();
	});
});
</script>

</head>
<body>
	userList
	<form id="form1" method="post" action="userSave.html" >
	<input type="text" name="userId" id="userId" />
	<input type="text" name="passwd" id="passwd" />
</form>	
	<input type="button" id="insert" value="등록" />

</body>
</html>