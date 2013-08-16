<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<title>login</title>
	   	
	<script type="text/javascript" src="<c:url value="/resources/js/common/common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/common/jquery-2.0.3.min.js"/>"></script>


	<!-- modernizr enables HTML5 elements and feature detects -->
	<script type="text/javascript">
	if(typeof document.compatMode!='undefined'&&document.compatMode!='BackCompat'){
	     cot_t1_DOCtp="_top:expression(document.documentElement.scrollTop+document.documentElement.clientHeight-this.clientHeight);_left:expression(document.documentElement.scrollLeft + document.documentElement.clientWidth - offsetWidth);}";
	 }else{
	     cot_t1_DOCtp="_top:expression(document.body.scrollTop+document.body.clientHeight-this.clientHeight);_left:expression(document.body.scrollLeft + document.body.clientWidth - offsetWidth);}";
	 }
	 var cot_tl_bodyCSS='* html {background:url(빈 이미지 blank.gif) fixed;background-repeat: repeat;background-position: right bottom;}';
	 var cot_tl_fixedCSS='#cot_tl_fixed{position:fixed;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'_position:absolute;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'z-index:1;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'width:100%;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'text-align:center;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'bottom:0px;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'right:0px;';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+'clip:rect(0 100% 100% 0);';
	 var cot_tl_fixedCSS=cot_tl_fixedCSS+cot_t1_DOCtp;
	 document.write('<style type="text/css">'+cot_tl_bodyCSS+cot_tl_fixedCSS+'</style>');
	
	
	
	
	var CONTEXT_PATH = '<c:out value="${pageContext.request.contextPath}"/>';    
		 
    // 초기화
    $(document).ready(function() {
      	$('#userId').keydown(function(event){
			if(event.keyCode == '13'){
				fn_login();
			}
		});

		$('#passwd').keydown(function(event){
			if(event.keyCode == '13'){
				fn_login();
			}
		});

		$('#userId').focus();
    });
    
    // 로그인
    function fn_login(){ 
    	
    	var param = "userId=" + $("#userId").val(); 
    	param += "&passwd=" + $("#passwd").val();
    	 
		var targetURL = CONTEXT_PATH + "/login/loginAjax.html";
		
		if(validate()){
			requestAjax(targetURL, param, loginCallback);
			
		}
    	
    }
    function loginCallback(result){ 
    	if(result == "fail"){
    		alert("아이디 또는 비밀번호 확인부탁드립니다.");
   			$('#userId').focus(); 
   		}else{
   			document.location.href = CONTEXT_PATH + "/user/userList.html";
   		}
    }
    
    // 유효성 체크
    function validate(){
    	
    	if(!nullCheck("아이디", $("#userId"))){
			return false;
		}
		
		if(!nullCheck("비밀번호", $("#pwd"))){
			return false;
		}
		
    	return true;
    }
    
</script>
</head>

<body>

<h5>${LOGIN_USER_ID}&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login/logout.html">logout</a></h5>
	
<form id="frm" >
<div id="content"> 
				
	    <h1>Member Login</h1>
		  <p><input type="text" id="userId" name="userId" placeholder="아이디"/></p>
		  <p><input type="password" id="passwd" name="passwd" placeholder="비밀번호"/></p>
		  <p><input type="button" id="btnLogin" value="로그인" onclick="fn_login();"/> 
	 
 
</div>
</form>

<div id="cot_tl_fixed" style="left:0px;width:100%;height:50px;background-color:silver;">
 하단 고정 레이어입니다.
 </div>
 
test<br>test<br>test<br>test<br>test<br>test<br>test<br>test<br>
 
test<br>test<br>test<br>test<br>test<br>test<br>test<br>test<br>
 
test<br>test<br>test<br>test<br>test<br>test<br>test<br>test<br>
 
test<br>test<br>test<br>test<br>test<br>test<br>test<br>test<br>



</body>
</html>