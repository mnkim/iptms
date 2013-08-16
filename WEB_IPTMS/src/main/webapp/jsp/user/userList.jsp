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
<script type="text/javascript" src="<c:url value="/resources/js/common/page.js"/>"></script>

<link href="<c:url value="/resources/css/demos.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/jquery.ui.all.css"/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/js/uiDatePicker/jquery.ui.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/uiDatePicker/jquery.ui.datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/uiDatePicker/jquery.ui.datepicker-ko.js"/>"></script>

<script type="text/javascript">

//페이징
var pageNavi = <c:choose><c:when test="${empty page_navi}">null</c:when><c:otherwise>${page_navi}</c:otherwise></c:choose>;
var pageNo = <c:choose><c:when test="${empty page_no}">null</c:when><c:otherwise>${page_no}</c:otherwise></c:choose>;

$(document).ready(function() {
	
	printPageNavigation(pageNavi, "page_navi", "frm", pageNo);
	
	$(".delete").click (function() {
		var userId = $(this).attr("userId");
		document.location.href = "userDelete.html?userId=" + userId;
	});
	
	$(".passwd").click (function() {
		var userId = $(this).attr("userId");
		
		$(this).hide();
		
		$(".newPwd_" + userId).val($(this).val());
		$(".newPwd_" + userId).show();
		$(".newPwd_" + userId).focus();
		
		$(".modify").unbind("click");
		$(".modify_" + userId).attr("style", "cursor:pointer");
		$(".modify_" + userId).click(function(){
			// alert(userId);	
			
			$('input[name=userId]').val(userId);
			$('input[name=passwd]').val($(".newPwd_" + userId).val());
			
			$("#form1").submit();
		});
		
	});
	
	$(".newPwd").blur (function() {
		var userId = $(this).attr("userId");
		
		$(this).hide();
		
		$(".passwd_" + userId).show();
		$(".modify_" + userId).attr("style", "cursor:mouse");
	});
	
	$("#btnSave").click (function() {
		document.location.href = "userEmpty.html";
	});
	
	$("#btnSearch").click (function() {
		// alert(strSearch);
		$("#frm").submit();
	});
	
	$("#excelExport").click (function() {
		// document.location.href = "excelExport.html";
		$("#frm").action = "excelExport.html";
		$("#frm").submit();
		
	});

});


$(function() {  // datapicker 사용시작
	  $.datepicker.setDefaults({
	    //monthNames: ['년 1월','년 2월','년 3월','년 4월','년 5월','년 6월','년 7월','년 8월','년 9월','년 10월','년 11월','년 12월'],
	    //dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    //showMonthAfterYear:true,
	    dateFormat: 'yy-mm-dd',  // 년월일 표시방법  yy-mm-dd 또는 yymmdd
	    buttonImageOnly: true,
	    showOn: 'both',
	    buttonText: "달력",
	    buttonImage: '/iptms/resources/images/btn_calendar.gif'
	  });
	 
	  // 시작날짜와 끝나는 날짜를 함께 선택해서 사용할때
	  var dates = $( "#datepicker_from, #datepicker_to" ).datepicker({
	     
	    //defaultDate: "+1w",  // 기본선택일이 1 week 이후가 선택되는 옵션
	    changeMonth: true,
	    numberOfMonths: 1,  // 한눈에 보이는 월달력수
	    onSelect: function( selectedDate ) {
	      var option = this.id == "datepicker_from" ? "minDate" : "maxDate",
	      instance = $( this ).data( "datepicker" ),
	      date = $.datepicker.parseDate(
	        instance.settings.dateFormat ||
	        $.datepicker._defaults.dateFormat,
	        selectedDate, instance.settings );
	      dates.not( this ).datepicker( "option", option, date );
	    }
	  });
	});  // datapicker 사용 끝
	
</script>

</head>

<body>

	<form id="form1" method="post" action="userUpdate.html">
	<input type="hidden" name="userId" value="" />
	<input type="hidden" name="passwd" value="" />
	</form>
	
<h5>${LOGIN_USER_ID}&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login/logout.html">logout</a></h5>	
	
	<table>
		<tr align=right>
			<td colspan=4>
				총 건수 : ${totCnt}
			</td>
		</tr>
	  	<tr>
	  		<th>아이디</th>
	  		<th>비밀번호</th>
	  		<th>수정</th>
	  		<th>삭제</th>
	  	</tr>
	  	<c:forEach items="${userInfoList}" var="list" varStatus="i">
	  	<tr>
	  		<td class="colTypeA">${list.userId}</td>
	  		<td>
	  			<input type="text" value="${list.passwd}" class="passwd passwd_${list.userId}" userId="${list.userId}"  style="border:0px;"/>
	  			<input type="text" value="${list.passwd}" class="newPwd newPwd_${list.userId}" userId="${list.userId}" style="display:none;"/>
	  		</td>
	  		<td class="modify modify_${list.userId}" userId="${list.userId}">수정</td>
	  		<td class="delete" userId="${list.userId}">삭제</td>
	  	</tr>
	  	</c:forEach>
  	</table>
  	
  	<form name="frm" id="frm" method="post">
  	<!-- 페이징 -->
	<table>
		<tr>
			<td align="center" id="page_navi"></td>
		</tr>
	</table>
	<!--// 페이징 -->
	
	<!-- 검색조건 -->
	<input type="text" name="strSearch" value="${userId}" />
	<input type="button" id="btnSearch" value="검색" />
	<input type="hidden" name="page_no" value="" />  
	<!-- // 검색조건 -->
	
	
	<input type="button" id="excelExport" value="엑셀출력" />
	</form>
	
	<input type="button" id="btnSave" value="등록">
	
	<input type="text" name="date1" style="width:72px;" readonly id="datepicker_from" value="${start_date}">
	<input type="text" name="date2" style="width:72px;" readonly id="datepicker_to" value="${finish_date}">
	
	<c:set value="${s_hour}" var="sHour"/>
	<select name="shour" class="gray">     
	<!-- FOR 문 시작  -->    
	<c:forEach begin="0" end="23" step="1" var="shour">            
	<!-- month 라는 값에 변수가 10보다 작을 경우  -->            
	<c:if test="${shour < 10}">                
	<!-- month 01,02,03,04 ... 08,09  로 바뀜 -->                
	<c:set value="0${shour}" var="shour"></c:set>           
	</c:if>            
	<!-- month 가 03 일 경우 선택 됨  -->            
	<option value="${shour}" <c:if test="${shour == sHour}"> selected </c:if> >${shour}시</option>    
	</c:forEach>
	</select> 
	
	<c:set value="${s_min}" var="sMin"/>
	<select name="smin" class="gray">     
	<!-- FOR 문 시작  -->    
	<c:forEach begin="0" end="59" step="1" var="smin">            
	<!-- month 라는 값에 변수가 10보다 작을 경우  -->            
	<c:if test="${smin < 10}">                
	<!-- month 01,02,03,04 ... 08,09  로 바뀜 -->                
	<c:set value="0${smin}" var="smin"></c:set>           
	</c:if>            
	<!-- month 가 03 일 경우 선택 됨  -->            
	<option value="${smin}" <c:if test="${smin == sMin}"> selected </c:if> >${smin}분</option>    
	</c:forEach>
	</select>
	
	~
	
	<c:set value="${f_hour}" var="fHour"/>
	<select name="fhour" class="gray">     
	<!-- FOR 문 시작  -->    
	<c:forEach begin="0" end="23" step="1" var="fhour">            
	<!-- month 라는 값에 변수가 10보다 작을 경우  -->            
	<c:if test="${fhour < 10}">                
	<!-- month 01,02,03,04 ... 08,09  로 바뀜 -->                
	<c:set value="0${fhour}" var="fhour"></c:set>           
	</c:if>            
	<!-- month 가 03 일 경우 선택 됨  -->            
	<option value="${fhour}" <c:if test="${fhour == fHour}"> selected </c:if> >${fhour}시</option>    
	</c:forEach>
	</select>
	 
	<c:set value="${f_min}" var="fMin"/>
	<select name="fmin" class="gray">     
	<!-- FOR 문 시작  -->    
	<c:forEach begin="0" end="59" step="1" var="fmin">            
	<!-- month 라는 값에 변수가 10보다 작을 경우  -->            
	<c:if test="${fmin < 10}">                
	<!-- month 01,02,03,04 ... 08,09  로 바뀜 -->                
	<c:set value="0${fmin}" var="fmin"></c:set>           
	</c:if>            
	<!-- month 가 03 일 경우 선택 됨  -->            
	<option value="${fmin}" <c:if test="${fmin == fMin}"> selected </c:if> >${fmin}분</option>    
	</c:forEach>
	</select>
	
	<br/>
	<br/>	
	
	
	
<!-- 
[ 팁1 ]
년도가 현재 기준년도에서 +10년 , -10년 표시되는것을 +50년 , -50년으로 늘리기
jquery.ui.datepicker.js 의 78라인을 수정
  수정전 : yearRange: 'c-10:c+10', // Range of years to display in drop-down,
  수정후 : yearRange: 'c-70:c+50', // Range of years to display in drop-down,
 

 -->

</body>
</html>